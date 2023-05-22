package cn.edu.thssdb.schema;

import cn.edu.thssdb.common.Global;
import cn.edu.thssdb.exception.DuplicateTableException;
import cn.edu.thssdb.exception.FileIOException;
import cn.edu.thssdb.query.QueryResult;
import cn.edu.thssdb.query.QueryTable;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {

  private String name;
  private HashMap<String, Table> tables;
  private HashMap<String, Table> tableMap;
  private ArrayList<Table> droppedTables;
  ReentrantReadWriteLock lock;


  public Database(String name) {
    this.name = name;
    this.tables = new HashMap<>();
    this.lock = new ReentrantReadWriteLock();
    recover();
  }

  private void persist() {
    // TODO
    for (Table table : this.tableMap.values()) {
      String filename = table.getTableMetaPath();
      ArrayList<Column> columns = table.columns;
      try {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        for (Column column : columns)
          outputStreamWriter.write(column.toString() + "\n");
        outputStreamWriter.close();
        fileOutputStream.close();
      } catch (Exception e) {
        throw new FileIOException(filename);
      }
    }
  }

  public void create(String name, Column[] columns) {
    // TODO
  }

  public void drop() {
    // TODO
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
        String tableName = fileName.substring(0,fileName.length()-Global.META_SUFFIX.length());
        if (this.tableMap.containsKey(tableName))
          throw new DuplicateTableException(tableName);

        ArrayList<Column> columnList = new ArrayList<>();
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String readLine;
        while ((readLine = bufferedReader.readLine()) != null)
          columnList.add(Column.parseColumn(readLine));
        bufferedReader.close();
        reader.close();
        Table table = new Table(this.name, tableName, columnList.toArray(new Column[0]));
        System.out.println(table.toString());
        for(Row row: table)
          System.out.println(row.toString());
        this.tableMap.put(tableName, table);
      } catch (Exception ignored) {
      }
    }
  }

  public void quit() {
    // TODO
    try {
      this.lock.writeLock().lock();
      for (Table table : this.tableMap.values())
        table.persist();
      this.persist();
    } finally {
      this.lock.writeLock().unlock();
    }
  }
  public String getDatabasePath(){
    return Global.DBMS_DIR + File.separator + "data" + File.separator + this.name;
  }
  public String getDatabaseTableFolderPath(){
    return this.getDatabasePath() + File.separator + "tables";
  }
  public String getDatabaseLogFilePath(){
    return this.getDatabasePath() + File.separator + "log";
  }
}