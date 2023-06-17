package cn.edu.thssdb.schema;

import cn.edu.thssdb.exception.DatabaseNotExistException;
import cn.edu.thssdb.utils.Global;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Manager {
  private HashMap<String, Database> databases;
  public Database currentDatabase;
  private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  public ArrayList<Long> transaction_sessions = new ArrayList<Long>();
  public ArrayList<Long> session_queue = new ArrayList<Long>();
  public HashMap<Long, ArrayList<String>> s_lock_dict =
      new HashMap<Long, ArrayList<String>>(); // 记录每个session取得了哪些表的s锁
  public HashMap<Long, ArrayList<String>> x_lock_dict = new HashMap<Long, ArrayList<String>>();

  public static Manager getInstance() {
    return Manager.ManagerHolder.INSTANCE;
  }

  public Manager() {
    // TODO
    databases = new HashMap<>();
    currentDatabase = null;
    File managerFolder = new File(Global.DBMS_DIR + File.separator + "data");
    String folderPath =
        Global.DBMS_DIR + File.separator + "data" + File.separator + "BPlusTree"; // 设置要删除的文件夹路径
    deleteFolder(new File(folderPath));
    if (!managerFolder.exists()) managerFolder.mkdirs();
    this.recover();
  }

  public Database getCurrentDatabase() {
    return currentDatabase;
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
      database.dropDatabase();
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
      throw new RuntimeException();
    }
  }

  public void recover() {
    File managerDataFile = new File(Manager.getManagerDataFilePath());
    if (!managerDataFile.isFile()) return;
    try {
      InputStreamReader reader = new InputStreamReader(new FileInputStream(managerDataFile));
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        createDatabaseIfNotExists(line);
        readLog(line);
      }
      bufferedReader.close();
      reader.close();
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

  public void readLog(String databaseName) {
    String logFilename = this.databases.get(databaseName).getDatabaseLogFilePath();
    File logFile = new File(logFilename);
    if (!logFile.isFile()) return;
    try {
      currentDatabase = databases.get(databaseName);
      InputStreamReader reader = new InputStreamReader(new FileInputStream(logFile));
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        long session = Long.parseLong(line.split("@")[0]);
        String statement = line.split("@")[1];
        // sqlHandler.evaluate(statement, session, true);
      }
      bufferedReader.close();
      reader.close();
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

  public static void deleteFolder(File folder) {
    if (folder.isDirectory()) {
      File[] files = folder.listFiles();
      if (files != null) {
        for (File file : files) {
          deleteFolder(file); // 递归调用删除子文件夹和文件
        }
      }
    }
    folder.delete(); // 删除空文件夹或文件
  }

  private static class ManagerHolder {
    private static final Manager INSTANCE = new Manager();

    private ManagerHolder() {}
  }

  public static String getManagerDataFilePath() {
    return Global.DBMS_DIR + File.separator + "data" + File.separator + "manager";
  }
}
