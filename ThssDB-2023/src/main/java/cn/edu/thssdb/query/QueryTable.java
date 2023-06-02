package cn.edu.thssdb.query;

import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Entry;
import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;
import cn.edu.thssdb.sql.SQLParser;

import java.util.ArrayList;
import java.util.Iterator;

public class QueryTable implements Iterator<Row> {

  public ArrayList<Column> columns;
  public ArrayList<Row> rows = new ArrayList<>();

  public QueryTable(Table table) {
    // TODO    单个table的选择
    System.out.println("wad" + table.toString());
    this.columns = new ArrayList<>();
    for (Column the_column : table.columns) {
      Column newcolumn =
          new Column(
              table.tableName + '.' + the_column.getColumnName(),
              the_column.getColumnType(),
              the_column.getPrimary(),
              the_column.nonNullable(),
              the_column.getMaxLength());
      this.columns.add(newcolumn);
    }

    for (Row the_row : table) {

      this.rows.add(the_row);
    }
  }

  public QueryTable(QueryTable x_table, QueryTable y_table, SQLParser.ConditionContext joinCon) {

    (this.columns = new ArrayList<>(x_table.columns)).addAll(y_table.columns);
    this.rows = new ArrayList<>();

    String xColumnName = null, yColumnName = null;
    int xColumnIndex = -1, yColumnIndex = -1;

    if (joinCon != null) {
      xColumnName = joinCon.expression(0).getText().toLowerCase();
      yColumnName = joinCon.expression(1).getText().toLowerCase();
      for (int i = 0; i < x_table.columns.size(); i++) {
        if (x_table.columns.get(i).getColumnName().equals(xColumnName)) {
          xColumnIndex = i;
        }
      }
      for (int i = 0; i < y_table.columns.size(); i++) {
        if (y_table.columns.get(i).getColumnName().equals(yColumnName)) {
          yColumnIndex = i;
        }
      }
    }

    // 判断是否连接起来两个ROW
    for (Row x_row : x_table.rows) {
      for (Row y_row : y_table.rows) {
        if (joinCon != null) {
          Entry leftRefValue = x_row.getEntries().get(xColumnIndex);
          Entry rightRefValue = y_row.getEntries().get(yColumnIndex);
          if (!leftRefValue.equals(rightRefValue)) {
            continue;
          }
        }
        Row new_row = new Row(x_row);
        new_row.getEntries().addAll(y_row.getEntries());
        this.rows.add(new_row);
      }
    }
  }

  @Override
  public boolean hasNext() {
    // TODO
    return true;
  }

  @Override
  public Row next() {
    // TODO
    return null;
  }
}
