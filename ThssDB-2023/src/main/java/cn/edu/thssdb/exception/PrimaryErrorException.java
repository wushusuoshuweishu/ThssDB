package cn.edu.thssdb.exception;

public class PrimaryErrorException extends RuntimeException {
  private String name;

  private int flag;

  public PrimaryErrorException(String name, int flag) {
    super();
    this.name = name;
    this.flag = flag;
  }

  @Override
  public String getMessage() {
    if (flag == 1) {
      return "Exception: there no exists primary_keys of table " + name + "!";
    } else {
      return "Multi primary keys of table" + name + "!";
    }
  }
}
