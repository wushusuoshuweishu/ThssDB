package cn.edu.thssdb.exception;

public class TableNoExistException extends RuntimeException {
  private String key;

  public TableNoExistException() {
    super();
    this.key = null;
  }

  public TableNoExistException(String key) {
    super();
    this.key = key;
  }

  @Override
  public String getMessage() {
    if (key == null) return "Exception: table doesn't exist!";
    else return "Exception: table \"" + this.key + "\" doesn't exist!";
  }
}
