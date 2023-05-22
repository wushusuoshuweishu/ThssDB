package cn.edu.thssdb.exception;

public class PrimaryErrorException extends RuntimeException {
  private String name;

  public PrimaryErrorException(String name) {
    super();
    this.name = name;
  }

  @Override
  public String getMessage() {
    return "Exception: there exists error primary_keys of table " + name + "!";
  }
}
