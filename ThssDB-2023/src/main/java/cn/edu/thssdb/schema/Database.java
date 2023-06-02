package cn.edu.thssdb.schema;

import cn.edu.thssdb.exception.TableNoExistException;
import cn.edu.thssdb.query.QueryResult;
import cn.edu.thssdb.query.QueryTable;
import cn.edu.thssdb.utils.Global;

import java.io.*;
import java.util.ArrayList;
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

  public Table getTable(String tableName) {
    return tables.get(tableName);
  }

  private void persist() {
    // TODO
    for (Table table : this.tables.values()) {
      String filename = table.getTableMetaPath();
      ArrayList<Column> columns = table.columns;
      System.out.println("table_meta persist!");
      try {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        for (Column column : columns) outputStreamWriter.write(column.toString() + "\n");
        outputStreamWriter.close();
        fileOutputStream.close();
      } catch (Exception e) {
        // throw new FileIOException(filename);
        throw new RuntimeException();
      }
    }
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

  public void dropDatabase() {
    try {
      lock.writeLock().lock();
      for (Table table : this.tables.values()) {
        File file = new File(table.getTableMetaPath());
        file.delete();
        table.dropTable();
      }
      this.tables.clear();
      this.tables = null;
    } finally {
      lock.writeLock().unlock();
    }
  }

  public String select(QueryTable[] queryTables) {
    // TODO
    QueryResult queryResult = new QueryResult(queryTables);
    return null;
  }

  private void recover() {
    // TODO
    System.out.println("! try to recover database " + this.name);
    File tableFolder = new File(this.getDatabaseTableFolderPath());
    File[] files = tableFolder.listFiles();
    //        for(File f: files) System.out.println("...." + f.getName());
    if (files == null) return;

    for (File file : files) {
      if (!file.isFile() || !file.getName().endsWith(Global.META_SUFFIX)) continue;
      try {
        String fileName = file.getName();
        String tableName = fileName.substring(0, fileName.length() - Global.META_SUFFIX.length());
        if (this.tables.containsKey(tableName))
          // throw new DuplicateTableException(tableName);
          throw new RuntimeException();

        ArrayList<Column> columnList = new ArrayList<>();
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String readLine;
        while ((readLine = bufferedReader.readLine()) != null)
          columnList.add(Column.parseColumn(readLine));
        bufferedReader.close();
        reader.close();
        Table table = new Table(this.name, tableName, columnList.toArray(new Column[0]));

        for (Row row : table) System.out.println(row.toString());
        this.tables.put(tableName, table);
      } catch (Exception ignored) {
      }
    }
  }

  public void quit() {
    // TODO
    try {
      this.lock.writeLock().lock();
      for (Table table : this.tables.values()) table.persist();
      this.persist();
    } finally {
      this.lock.writeLock().unlock();
    }
  }

  public String getDatabasePath() {
    return Global.DBMS_DIR + File.separator + "data" + File.separator + this.name;
  }

  public String getDatabaseTableFolderPath() {
    return this.getDatabasePath() + File.separator + "tables";
  }

  public String getDatabaseLogFilePath(){
    return this.getDatabasePath() + File.separator + "log";
  }
}
