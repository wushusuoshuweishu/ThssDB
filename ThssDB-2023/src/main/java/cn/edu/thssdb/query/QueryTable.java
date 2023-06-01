package cn.edu.thssdb.query;

import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;

import java.util.ArrayList;
import java.util.Iterator;

public class QueryTable implements Iterator<Row> {

  public ArrayList<Column> columns;
  public ArrayList<Row> rows;
  QueryTable(Table table) {
    // TODO    单个table的选择
    this.columns = new ArrayList<>();
    for (Column the_column : table.columns){
      Column newcolumn = new Column(table.tableName + '.' + the_column.getColumnName(),
              the_column.getColumnType(), the_column.getPrimary(), the_column.nonNullable(), the_column.getMaxLength());
      this.columns.add(newcolumn);
    }
    Iterator<Row> rowIterator = table.iterator();

    while (rowIterator.hasNext()){
      Row the_row =rowIterator.next();
      this.rows.add(the_row);
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
