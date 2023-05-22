package cn.edu.thssdb.schema;

import cn.edu.thssdb.exception.PrimaryErrorException;
import cn.edu.thssdb.index.BPlusTree;
import cn.edu.thssdb.utils.Global;
import cn.edu.thssdb.utils.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Table implements Iterable<Row> {
  ReentrantReadWriteLock lock;
  private String databaseName;
  public String tableName;
  public ArrayList<Column> columns;
  public BPlusTree<Entry, Row> index;
  private int primaryIndex;

  public Table(String databaseName, String tableName, Column[] columns) {
    // TODO
    this.lock = new ReentrantReadWriteLock();
    this.databaseName = databaseName;
    this.tableName = tableName;
    this.columns = new ArrayList<>(Arrays.asList(columns)); // 属性表
    this.index = new BPlusTree<>();
    this.primaryIndex = -1;

    for (int i = 0; i < this.columns.size(); i++) {
      if (this.columns.get(i).is_primary()) {
        if (this.primaryIndex >= 0) throw new PrimaryErrorException(this.tableName);
        this.primaryIndex = i;
      }
    }
    if (this.primaryIndex < 0) throw new PrimaryErrorException(this.tableName);
  }

  private void recover() {
    // TODO
  }

  public void insert() {
    // TODO
  }

  public void delete() {
    // TODO
  }

  public void update() {
    // TODO
  }

  private void serialize() {
    // TODO
  }

  private ArrayList<Row> deserialize() {
    // TODO
    return null;
  }

  public void dropTable() { // remove table data file
    try {
      this.lock.writeLock().lock();
      File tableFolder = new File(this.getTableFolderPath());
      if (!tableFolder.exists() ? !tableFolder.mkdirs() : !tableFolder.isDirectory())
        // throw new FileIOException(this.getTableFolderPath() + " when dropTable");
        throw new RuntimeException();
      File tableFile = new File(this.getTablePath());
      if (tableFile.exists() && !tableFile.delete())
        // throw new FileIOException(this.getTablePath() + " when dropTable");
        throw new RuntimeException();
    } finally {
      this.lock.writeLock().unlock();
    }
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
  // table的data文件路径
  public String getTablePath() {
    return this.getTableFolderPath() + File.separator + this.tableName;
  }
  // table中元数据路径
  public String getTableMetaPath() {
    return this.getTablePath() + Global.META_SUFFIX;
  }
}
