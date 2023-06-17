package cn.edu.thssdb.index;

import cn.edu.thssdb.exception.DuplicateKeyException;
import cn.edu.thssdb.exception.KeyNotExistException;
import cn.edu.thssdb.utils.Global;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class BPlusTreeLeafNode<K extends Comparable<K>, V> extends BPlusTreeNode<K, V> {

  public static int count;
  public int current_count;

  static {
    count = 0;
  }

  ArrayList<V> values;
  private BPlusTreeLeafNode<K, V> next;

  BPlusTreeLeafNode(int size) {
    keys = new ArrayList<>(Collections.nCopies((int) (1.5 * Global.fanout) + 1, null));
    values = new ArrayList<>(Collections.nCopies((int) (1.5 * Global.fanout) + 1, null));
    nodeSize = size;
    current_count = count;
    count += 1;
  }

  private void valuesAdd(int index, V value) {
    values = deserialize();
    for (int i = nodeSize; i > index; i--) values.set(i, values.get(i - 1));
    values.set(index, value);
    serialize();
  }

  private void valuesRemove(int index) {
    values = deserialize();
    for (int i = index; i < nodeSize - 1; i++) values.set(i, values.get(i + 1));
    serialize();
  }

  @Override
  boolean containsKey(K key) {
    return binarySearch(key) >= 0;
  }

  @Override
  V get(K key) {
    values = deserialize();
    int index = binarySearch(key);
    if (index >= 0) return values.get(index);
    serialize();
    throw new KeyNotExistException();
  }

  @Override
  void put(K key, V value) {
    int index = binarySearch(key);
    int valueIndex = index >= 0 ? index : -index - 1;
    if (index >= 0) throw new DuplicateKeyException();
    else {
      valuesAdd(valueIndex, value);
      keysAdd(valueIndex, key);
    }
  }

  @Override
  void remove(K key) {
    int index = binarySearch(key);
    if (index >= 0) {
      valuesRemove(index);
      keysRemove(index);
    } else throw new KeyNotExistException();
  }

  @Override
  K getFirstLeafKey() {
    return keys.get(0);
  }

  @Override
  BPlusTreeNode<K, V> split() {
    int from = (size() + 1) / 2;
    int to = size();
    BPlusTreeLeafNode<K, V> newSiblingNode = new BPlusTreeLeafNode<>(to - from);
    values = deserialize();
    for (int i = 0; i < to - from; i++) {
      newSiblingNode.keys.set(i, keys.get(i + from));
      newSiblingNode.values.set(i, values.get(i + from));
      keys.set(i + from, null);
      values.set(i + from, null);
    }
    serialize();
    newSiblingNode.serialize();
    nodeSize = from;
    newSiblingNode.next = next;
    next = newSiblingNode;
    return newSiblingNode;
  }

  @Override
  void merge(BPlusTreeNode<K, V> sibling) {
    int index = size();
    BPlusTreeLeafNode<K, V> node = (BPlusTreeLeafNode<K, V>) sibling;
    int length = node.size();
    values = deserialize();
    node.values = node.deserialize();
    for (int i = 0; i < length; i++) {
      keys.set(i + index, node.keys.get(i));
      values.set(i + index, node.values.get(i));
    }
    serialize();
    nodeSize = index + length;
    next = node.next;
  }

  public String getTreeLeafPath() {
    return Global.DBMS_DIR + "/data/BPlusTree/" + current_count;
  }

  public void serialize() {
    try {
      File tableFolder = new File(Global.DBMS_DIR + "/data/BPlusTree");
      if (!tableFolder.exists() ? !tableFolder.mkdirs() : !tableFolder.isDirectory())
        throw new RuntimeException();
      File tableFile = new File(this.getTreeLeafPath());
      if (!tableFile.exists() ? !tableFile.createNewFile() : !tableFile.isFile())
        throw new RuntimeException();
      FileOutputStream fileOutputStream = new FileOutputStream(this.getTreeLeafPath());
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      for (V value : values) {
        objectOutputStream.writeObject(value);
      }
      objectOutputStream.close();
      fileOutputStream.close();
      values = new ArrayList<>(Collections.nCopies((int) (1.5 * Global.fanout) + 1, null));
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  public ArrayList<V> deserialize() {
    try {
      File tableFile = new File(this.getTreeLeafPath());
      if (!tableFile.exists())
        return new ArrayList<>(Collections.nCopies((int) (1.5 * Global.fanout) + 1, null));
      FileInputStream fileInputStream = new FileInputStream(this.getTreeLeafPath());
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      ArrayList<V> valuesOnDisk = new ArrayList<>();
      Object tmpObj;
      while (fileInputStream.available() > 0) {
        tmpObj = objectInputStream.readObject();
        valuesOnDisk.add((V) tmpObj);
      }
      objectInputStream.close();
      fileInputStream.close();
      tableFile.delete();
      return valuesOnDisk;
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException();
    }
  }
}
