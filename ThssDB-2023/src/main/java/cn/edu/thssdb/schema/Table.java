package cn.edu.thssdb.schema;

import cn.edu.thssdb.exception.DuplicateKeyException;
import cn.edu.thssdb.exception.KeyNotExistException;
import cn.edu.thssdb.exception.PrimaryErrorException;
import cn.edu.thssdb.index.BPlusTree;
import cn.edu.thssdb.utils.Global;
import cn.edu.thssdb.utils.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static cn.edu.thssdb.type.ColumnType.STRING;

public class Table implements Iterable<Row> {
  ReentrantReadWriteLock lock;
  private String databaseName;
  public String tableName;
  public ArrayList<Column> columns;
  public BPlusTree<Entry, Row> index;
  private int primaryIndex;
  int sxlock = 0; // 记录x,s加锁情况
  public ArrayList<Long> s_lock_list = new ArrayList<Long>();
  public ArrayList<Long> x_lock_list = new ArrayList<Long>();

  public Table(String databaseName, String tableName, Column[] columns) {
    // TODO
    this.lock = new ReentrantReadWriteLock();
    this.databaseName = databaseName;
    this.tableName = tableName;
    this.columns = new ArrayList<>(Arrays.asList(columns));
    this.index = new BPlusTree<>();
    this.primaryIndex = -1;

    for (int i = 0; i < this.columns.size(); i++) {
      if (this.columns.get(i).is_primary()) {
        if (this.primaryIndex >= 0) throw new PrimaryErrorException(this.tableName, 0);
        this.primaryIndex = i;
      }
    }
    if (this.primaryIndex < 0) throw new PrimaryErrorException(this.tableName, 1);

    recover();
  }

  public int get_s_lock(long session) {
    int value = 0; // 返回-1代表加锁失败  返回0代表成功但未加锁  返回1代表成功加锁
    if (sxlock == 2) {
      if (x_lock_list.contains(session)) { // 自身已经有更高级的锁了 用x锁去读，未加锁
        value = 0;
      } else {
        value = -1; // 别的session占用x锁，未加锁
      }
    } else if (sxlock == 1) {
      if (s_lock_list.contains(session)) { // 自身已经有s锁了 用s锁去读，未加锁
        value = 0;
      } else {
        s_lock_list.add(session); // 其他session加了s锁 把自己加上
        sxlock = 1;
        value = 1;
      }
    } else if (sxlock == 0) {
      s_lock_list.add(session); // 未加锁 把自己加上
      sxlock = 1;
      value = 1;
    }
    return value;
  }

  public int get_x_lock(long session) {
    int value = 0; // 返回-1代表加锁失败  返回0代表成功但未加锁  返回1代表成功加锁
    if (sxlock == 2) {
      if (x_lock_list.contains(session)) { // 自身已经取得x锁
        value = 0;
      } else {
        value = -1; // 获取x锁失败
      }
    } else if (sxlock == 1) {
      value = -1; // 正在被其他s锁占用
    } else if (sxlock == 0) {
      x_lock_list.add(session);
      sxlock = 2;
      value = 1;
    }
    return value;
  }

  public void free_s_lock(long session) {
    if (s_lock_list.contains(session)) {
      s_lock_list.remove(session);
      if (s_lock_list.size() == 0) {
        sxlock = 0;
      } else {
        sxlock = 1;
      }
    }
  }

  public void free_x_lock(long session) {
    if (x_lock_list.contains(session)) {
      sxlock = 0;
      x_lock_list.remove(session);
    }
  }

  private void recover() {
    // TODO
    try {
      this.lock.writeLock().lock();
      ArrayList<Row> rowsOnDisk = deserialize();
      for (Row row : rowsOnDisk) this.index.put(row.getEntries().get(this.primaryIndex), row);
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  public void insert(Row row) {
    // TODO
    try {
      this.lock.writeLock().lock();
      if (!this.isRowValid(row)) throw new RuntimeException();
      if (this.index.contains(row.getEntries().get(this.primaryIndex)))
        throw new DuplicateKeyException();
      this.index.put(row.getEntries().get(this.primaryIndex), row);

    } finally {
      this.lock.writeLock().unlock();
    }
  }

  public void delete(Row row) {
    // TODO
    try {
      this.lock.writeLock().lock();
      if (!this.index.contains(row.getEntries().get(this.primaryIndex)))
        throw new KeyNotExistException();
      this.index.remove(row.getEntries().get(this.primaryIndex));

    } finally {
      this.lock.writeLock().unlock();
    }
  }

  public void update(Entry primaryEntry, Row newRow) {
    // TODO
    try {
      this.lock.writeLock().lock();
      if (!this.isRowValid(newRow)) throw new RuntimeException();
      Entry newEntryValue = newRow.getEntries().get(this.primaryIndex);
      if (!primaryEntry.equals(newEntryValue)
          && this.index.contains(newRow.getEntries().get(this.primaryIndex)))
        throw new DuplicateKeyException();
      this.index.remove(primaryEntry);
      this.index.put(newEntryValue, newRow);
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  private void serialize() {
    // TODO
    try {
      File tableFolder = new File(this.getTableFolderPath());
      if (!tableFolder.exists() ? !tableFolder.mkdirs() : !tableFolder.isDirectory())
        throw new RuntimeException();
      File tableFile = new File(this.getTablePath());
      if (!tableFile.exists() ? !tableFile.createNewFile() : !tableFile.isFile())
        throw new RuntimeException();
      FileOutputStream fileOutputStream = new FileOutputStream(this.getTablePath());
      OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
      for (Row row : this) {
        String RowStr = row.toString();
        writer.write(RowStr + "\n");
      }

      writer.close();
      fileOutputStream.close();
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  private ArrayList<Row> deserialize() {
    // TODO
    try {
      File tableFolder = new File(this.getTableFolderPath());
      if (!tableFolder.exists() ? !tableFolder.mkdirs() : !tableFolder.isDirectory()) {
        throw new RuntimeException();
      }
      File tableFile = new File(this.getTablePath());
      if (!tableFile.exists()) return new ArrayList<>();
      FileInputStream fileInputStream = new FileInputStream(this.getTablePath());
      ArrayList<Row> rowsOnDisk = new ArrayList<>();
      InputStreamReader reader = new InputStreamReader(fileInputStream);
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        // 处理读取到的一行内容
        String[] entries_str = line.split(", ");
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
          entries.add(Column.recoverparseEntry(entries_str[i], columns.get(i)));
        }
        rowsOnDisk.add(new Row(entries));
      }
      // 关闭流
      bufferedReader.close();
      reader.close();
      return rowsOnDisk;
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  public void persist() {
    try {
      this.lock.writeLock().lock();
      serialize();
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  public void dropTable() {
    try {
      this.lock.writeLock().lock();
      File tableFolder = new File(this.getTableFolderPath());
      if (!tableFolder.exists() ? !tableFolder.mkdirs() : !tableFolder.isDirectory())
        throw new RuntimeException();
      File tableFile = new File(this.getTablePath());
      if (tableFile.exists() && !tableFile.delete()) throw new RuntimeException();
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  private Boolean isRowValid(Row row) {
    if (row.getEntries().size() != this.columns.size()) return Boolean.FALSE;
    for (int i = 0; i < row.getEntries().size(); i++) {
      String entryValueType = row.getEntries().get(i).getValueType();
      Column column = this.columns.get(i);
      if (entryValueType.equals(Global.ENTRY_NULL) && column.nonNullable()) {
        return Boolean.FALSE;
      } else {
        if (!entryValueType.equals(column.getColumnType().name())) return Boolean.FALSE;
        Comparable entryValue = row.getEntries().get(i).value;
        if (entryValueType.equals(STRING.name())
            && ((String) entryValue).length() > column.getMaxLength()) return Boolean.FALSE;
      }
    }
    return Boolean.TRUE;
  }

  public ArrayList<String> showTableInfo() {
    ArrayList<String> res = new ArrayList<>();
    for (Column column : this.columns) {
      res.add(column.toString() + '\n');
    }
    return res;
  }

  private class TableIterator implements Iterator<Row> {
    private Iterator<Pair<Entry, Row>> iterator;

    TableIterator(Table table) {
      this.iterator = table.index.iterator();
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public Row next() {
      return iterator.next().right;
    }
  }

  @Override
  public Iterator<Row> iterator() {
    return new TableIterator(this);
  }

  public String getTableFolderPath() {
    return Global.DBMS_DIR
        + File.separator
        + "data"
        + File.separator
        + databaseName
        + File.separator
        + "tables";
  }

  public String getTablePath() {
    return this.getTableFolderPath() + File.separator + this.tableName;
  }

  public String getTableMetaPath() {
    return this.getTablePath() + Global.META_SUFFIX;
  }

  public int getPrimaryIndex() {
    return this.primaryIndex;
  }
}
