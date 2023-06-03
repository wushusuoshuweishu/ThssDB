package cn.edu.thssdb.schema;

import cn.edu.thssdb.type.ColumnType;
import cn.edu.thssdb.utils.Global;

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

  public String getColumnName() {
    return this.name;
  }

  public int getPrimary() {
    return this.primary;
  }

  public boolean nonNullable() {
    return this.notNull;
  }

  public ColumnType getColumnType() {
    return this.type;
  }

  public int getMaxLength() {
    return this.maxLength;
  }

  public static Entry parseEntry(String s, Column column) {
    ColumnType columnType = column.getColumnType();
    if (s.equals(Global.ENTRY_NULL)) {
      if (column.nonNullable()) throw new RuntimeException("wrong null");
      else {
        Entry tmp = new Entry(Global.ENTRY_NULL);
        tmp.value = null;
        return tmp;
      }
    }
    switch (columnType) {
      case INT:
        return new Entry(Integer.valueOf(s));
      case LONG:
        return new Entry(Long.valueOf(s));
      case FLOAT:
        return new Entry(Float.valueOf(s));
      case DOUBLE:
        return new Entry(Double.valueOf(s));
      case STRING:
        String sWithoutQuotes = s.substring(1, s.length() - 1);
        if (sWithoutQuotes.length() > column.getMaxLength())
          throw new RuntimeException("length wrong");
        return new Entry(sWithoutQuotes);
      default:
        Entry tmp = new Entry(Global.ENTRY_NULL);
        tmp.value = null;
        return tmp;
    }
  }

  public String toString() {
    return name + ',' + type + ',' + primary + ',' + notNull + ',' + maxLength;
  }
}
