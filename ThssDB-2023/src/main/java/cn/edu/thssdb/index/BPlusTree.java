package cn.edu.thssdb.index;

import cn.edu.thssdb.utils.Pair;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.CRC32;

public final class BPlusTree<K extends Comparable<K>, V> implements Iterable<Pair<K, V>> {

  BPlusTreeNode<K, V> root;
  // 添加一个缓存，用于存储最近访问的B+树节点数据
  private Map<K, V> cache = new HashMap<>();
  // 添加一个批次大小变量，表示每批次需要处理的最大请求数
  private int batchSize = 100;

  private int size;

  public BPlusTree() {
    root = new BPlusTreeLeafNode<>(0);
  }

  public int size() {
    return size;
  }

  public V get(K key) {
    if (key == null) throw new IllegalArgumentException("argument key to get() is null");
    // 优先从缓存中读取节点数据
    if (cache.containsKey(key)) {
      return cache.get(key);
    }

    V value = root.get(key);
    // 将查询得到的节点数据添加到缓存
    cache.put(key, value);
    return value;
    // return root.get(key);
  }

  public void update(K key, V value) {
    root.remove(key);
    root.put(key, value);
    // 将节点数据添加到缓存
    cache.put(key, value);
  }

  public void put(K key, V value) {
    if (key == null) throw new IllegalArgumentException("argument key to put() is null");
    root.put(key, value);
    size++;
    // 每 batchSize 个写请求，进行一次写入磁盘的批处理
    if (size % batchSize == 0) {
      flushToDisk();
    }
    checkRoot();
  }

  public void remove(K key) {
    if (key == null) throw new IllegalArgumentException("argument key to remove() is null");
    root.remove(key);
    size--;

    // 每 batchSize 个写请求，进行一次写入磁盘的批处理
    if (size % batchSize == 0) {
      flushToDisk();
    }

    if (root instanceof BPlusTreeInternalNode && root.size() == 0) {
      root = ((BPlusTreeInternalNode<K, V>) root).children.get(0);
    }
    // 从缓存中移除该节点数据
    cache.remove(key);
  }

  public boolean contains(K key) {
    if (key == null) throw new IllegalArgumentException("argument key to contains() is null");
    // 优先从缓存中读取节点数据
    if (cache.containsKey(key)) {
      return true;
    }
    return root.containsKey(key);
  }
  // 将缓存中的数据持久化到磁盘
  private void flushToDisk() {
    try (RandomAccessFile file = new RandomAccessFile("tree.dat", "rw")) {
      // 数据写入缓冲区
      ByteBuffer buffer = ByteBuffer.allocate(4096);

      // 记录数据写入的起始位置
      long dataPosition = file.getFilePointer();

      // 遍历cache缓存，将数据写入缓冲区
      for (Map.Entry<K, V> entry : cache.entrySet()) {
        byte[] keyBytes = serializeKey(entry.getKey());
        byte[] valueBytes = serializeValue(entry.getValue());

        // 计算数据校验和
        CRC32 crc = new CRC32();
        crc.update(keyBytes);
        crc.update(valueBytes);
        long checkSum = crc.getValue();

        // 将校验和写入缓冲区
        buffer.putLong(checkSum);

        // 将key的长度和key的字节数据写入缓冲区
        buffer.putInt(keyBytes.length);
        buffer.put(keyBytes);

        // 将value的长度和value的字节数据写入缓冲区
        buffer.putInt(valueBytes.length);
        buffer.put(valueBytes);

        // 如果缓冲区已满，将缓冲区的数据写入磁盘
        if (!buffer.hasRemaining()) {
          buffer.flip();
          file.getChannel().write(buffer);
          buffer.clear();
        }
      }

      // 将剩余的缓冲区数据写入磁盘
      if (buffer.position() > 0) {
        buffer.flip();
        file.getChannel().write(buffer);
        buffer.clear();
      }

      // 记录数据写入的结束位置
      long dataEndPosition = file.getFilePointer();

      // 写入数据数量和数据写入的位置和结束位置
      buffer.putLong(cache.size());
      buffer.putLong(dataPosition);
      buffer.putLong(dataEndPosition);
      buffer.flip();

      // 将元数据写入文件头
      file.getChannel().write(buffer, 0);

      // 清空缓存
      cache.clear();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private byte[] serializeKey(K key) throws IOException {
    try (ByteBufferOutputStream baos = new ByteBufferOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos)) {
      oos.writeObject(key);
      return baos.getByteBuffer().array();
    }
  }

  private byte[] serializeValue(V value) throws IOException {
    try (ByteBufferOutputStream baos = new ByteBufferOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos)) {
      oos.writeObject(value);
      return baos.getByteBuffer().array();
    }
  }

  private void checkRoot() {
    if (root.isOverFlow()) {
      BPlusTreeNode<K, V> newSiblingNode = root.split();
      BPlusTreeInternalNode<K, V> newRoot = new BPlusTreeInternalNode<>(1);
      newRoot.keys.set(0, newSiblingNode.getFirstLeafKey());
      newRoot.children.set(0, root);
      newRoot.children.set(1, newSiblingNode);
      root = newRoot;
    }
  }

  @Override
  public BPlusTreeIterator<K, V> iterator() {
    return new BPlusTreeIterator<>(this);
  }
}

class ByteBufferOutputStream extends java.io.OutputStream {
  private ByteBuffer buf = ByteBuffer.allocate(4096);

  @Override
  public void write(int b) throws IOException {
    if (!buf.hasRemaining()) {
      flush();
    }
    buf.put((byte) b);
  }

  public ByteBuffer getByteBuffer() {
    return buf;
  }

  @Override
  public void close() throws IOException {
    flush();
    super.close();
  }

  @Override
  public void flush() throws IOException {
    buf.flip();
    while (buf.hasRemaining()) {
      // System.out.write(buf.get());
    }
    buf.clear();
  }
}
