package cn.edu.thssdb.schema;

import cn.edu.thssdb.exception.DatabaseNotExistException;
import cn.edu.thssdb.utils.Global;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Manager {
  private HashMap<String, Database> databases;
  public Database currentDatabase;
  private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  public static Manager getInstance() {
    return Manager.ManagerHolder.INSTANCE;
  }

  public Manager() {
    // TODO
    databases = new HashMap<>();
    currentDatabase = null;
    File managerFolder = new File(Global.DBMS_DIR + File.separator + "data");
    if (!managerFolder.exists()) managerFolder.mkdirs();
    this.recover();
  }

  public void createDatabaseIfNotExists(String databaseName) {
    // TODO
    try {
      lock.writeLock().lock();
      if (!databases.containsKey(databaseName))
        databases.put(databaseName, new Database(databaseName));
      if (currentDatabase == null) {
        try {
          lock.readLock().lock();
          currentDatabase = databases.get(databaseName);
        } finally {
          lock.readLock().unlock();
        }
      }
    } finally {
      lock.writeLock().unlock();
    }
  }

  public void deleteDatabase(String databaseName) {
    try {
      lock.writeLock().lock();
      if (!databases.containsKey(databaseName)) throw new DatabaseNotExistException(databaseName);
      Database database = databases.get(databaseName);
      // database.dropDatabase();
      databases.remove(databaseName);
    } finally {
      lock.writeLock().unlock();
    }
  }

  public void switchDatabase(String databaseName) {
    // TODO
    try {
      lock.readLock().lock();
      if (!databases.containsKey(databaseName)) throw new DatabaseNotExistException(databaseName);
      currentDatabase = databases.get(databaseName);
    } finally {
      lock.readLock().unlock();
    }
  }

  public void persist() {
    try {
      FileOutputStream fos = new FileOutputStream(Manager.getManagerDataFilePath());
      OutputStreamWriter writer = new OutputStreamWriter(fos);
      for (String databaseName : databases.keySet()) writer.write(databaseName + "\n");
      writer.close();
      fos.close();
    } catch (Exception e) {
      // throw new FileIOException(Manager.getManagerDataFilePath());
      throw new RuntimeException();
    }
  }

  public void recover() {
    File managerDataFile = new File(Manager.getManagerDataFilePath());
    if (!managerDataFile.isFile()) return;
    try {
      System.out.println("try to recover manager");
      InputStreamReader reader = new InputStreamReader(new FileInputStream(managerDataFile));
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        System.out.println("recover database" + line);
        createDatabaseIfNotExists(line);
      }
      bufferedReader.close();
      reader.close();
    } catch (Exception e) {
      // throw new FileIOException(managerDataFile.getName());
      throw new RuntimeException();
    }
  }

  private static class ManagerHolder {
    private static final Manager INSTANCE = new Manager();

    private ManagerHolder() {}
  }

  public static String getManagerDataFilePath() {
    return Global.DBMS_DIR + File.separator + "data" + File.separator + "manager";
  }
}
