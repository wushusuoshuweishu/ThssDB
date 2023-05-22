package cn.edu.thssdb.schema;

import cn.edu.thssdb.exception.TableNoExistException;
import cn.edu.thssdb.query.QueryResult;
import cn.edu.thssdb.query.QueryTable;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {

  private String name;
  private HashMap<String, Table> tables;
  ReentrantReadWriteLock lock;

  public Database(String name) {
    this.name = name;
    this.tables = new HashMap<>();
    this.lock = new ReentrantReadWriteLock();
    recover();
  }

  private void persist() {
    // TODO
  }

  public void create(String name, Column[] columns) {
    // TODO
    try {
      this.lock.writeLock().lock();
      Table table = new Table(this.name, name, columns);
      this.tables.put(name, table);
      this.persist();
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  public void drop(String name) {
    // TODO
    try {
      this.lock.writeLock().lock();
      if (!this.tables.containsKey(name)) throw new TableNoExistException(name);
      Table table = this.tables.get(name);
      String filename = table.getTableMetaPath();
      File file = new File(filename);
      if (file.isFile() && !file.delete())
        // throw new FileIOException(name + " _meta  when drop a table in database");
        throw new RuntimeException();
      table.dropTable(); // 文件处删除
      this.tables.remove(name);
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  public String select(QueryTable[] queryTables) {
    // TODO
    QueryResult queryResult = new QueryResult(queryTables);
    return null;
  }

  private void recover() {
    // TODO
  }

  public void quit() {
    // TODO
  }
}
