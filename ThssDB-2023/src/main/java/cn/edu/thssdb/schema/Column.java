package cn.edu.thssdb.schema;

import cn.edu.thssdb.type.ColumnType;

public class Column implements Comparable<Column> {
  private String name;
  private ColumnType type;
  private int primary;
  private boolean notNull;
  private int maxLength;

  public Column(String name, ColumnType type, int primary, boolean notNull, int maxLength) {
    this.name = name;
    this.type = type;
    this.primary = primary;
    this.notNull = notNull;
    this.maxLength = maxLength;
  }

  @Override
  public int compareTo(Column e) {
    return name.compareTo(e.name);
  }

  public static Column parseColumn(String s) {
    String[] sArray = s.split(",");
    return new Column(
        sArray[0],
        ColumnType.valueOf(sArray[1]),
        Integer.parseInt(sArray[2]),
        Boolean.parseBoolean(sArray[3]),
        Integer.parseInt(sArray[4]));
  }

  public boolean is_primary() {
    return this.primary == 1;
  }
  public boolean nonNullable(){return this.notNull;}
  public ColumnType getColumnType(){return this.type;}
  public int getMaxLength(){return this.maxLength;}


  public String toString() {
    return name + ',' + type + ',' + primary + ',' + notNull + ',' + maxLength;
  }
}
