package cn.edu.thssdb.query;

import cn.edu.thssdb.schema.Column;

import java.util.ArrayList;
import java.util.List;

class MetaInfo {

  private String tableName;
  private List<Column> columns;

  MetaInfo(String tableName, ArrayList<Column> columns) {
    this.tableName = tableName;
    this.columns = columns;
  }

  int columnFind(String name) {
    // TODO
    int size = columns.size();
    for (int i = 0; i < size; ++i)
      if (columns.get(i).getColumnName().equals(name))
        return i;
    return -1;
  }
}
