package cn.edu.thssdb.service;

import cn.edu.thssdb.plan.LogicalGenerator;
import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.plan.impl.*;
import cn.edu.thssdb.query.QueryTable;
import cn.edu.thssdb.rpc.thrift.ConnectReq;
import cn.edu.thssdb.rpc.thrift.ConnectResp;
import cn.edu.thssdb.rpc.thrift.DisconnectReq;
import cn.edu.thssdb.rpc.thrift.DisconnectResp;
import cn.edu.thssdb.rpc.thrift.ExecuteStatementReq;
import cn.edu.thssdb.rpc.thrift.ExecuteStatementResp;
import cn.edu.thssdb.rpc.thrift.GetTimeReq;
import cn.edu.thssdb.rpc.thrift.GetTimeResp;
import cn.edu.thssdb.rpc.thrift.IService;
import cn.edu.thssdb.rpc.thrift.Status;
import cn.edu.thssdb.schema.*;
import cn.edu.thssdb.sql.SQLParser;
import cn.edu.thssdb.type.ColumnType;
import cn.edu.thssdb.utils.Global;
import cn.edu.thssdb.utils.StatusUtil;
import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static cn.edu.thssdb.schema.Column.parseEntry;

public class IServiceHandler implements IService.Iface {

  public static Manager manager;

  private static final AtomicInteger sessionCnt = new AtomicInteger(0);

  public IServiceHandler() {
    super();
    manager = Manager.getInstance();
  }

  @Override
  public GetTimeResp getTime(GetTimeReq req) throws TException {
    GetTimeResp resp = new GetTimeResp();
    resp.setTime(new Date().toString());
    resp.setStatus(new Status(Global.SUCCESS_CODE));
    return resp;
  }

  @Override
  public ConnectResp connect(ConnectReq req) throws TException {
    return new ConnectResp(StatusUtil.success(), sessionCnt.getAndIncrement());
  }

  @Override
  public DisconnectResp disconnect(DisconnectReq req) throws TException {
    return new DisconnectResp(StatusUtil.success());
  }

  public static ArrayList<Row> getRowsValidForWhere(
      ArrayList<Column> columns,
      Iterator<Row> rowIterator,
      SQLParser.ConditionContext updateCondition) {
    String attrName = null;
    String attrValue = null;
    int attrIndex = 0;
    SQLParser.ComparatorContext comparator = null;
    Entry compareValue = null;
    ArrayList<Row> rows = new ArrayList<Row>();

    if (updateCondition != null) {
      if (updateCondition.expression(0).comparer().columnFullName().getChildCount() == 1) {
        attrName =
            updateCondition
                .expression(0)
                .comparer()
                .columnFullName()
                .columnName()
                .getText()
                .toLowerCase();
      } else {
        attrName =
            updateCondition.expression(0).comparer().columnFullName().getText().toLowerCase();
      }

      attrValue = updateCondition.expression(1).comparer().literalValue().getText();

      attrIndex = -1;
      for (int i = 0; i < columns.size(); ++i) {
        if (columns.get(i).getColumnName().toLowerCase().equals(attrName)) {
          attrIndex = i;
        }
      }
      comparator = updateCondition.comparator();
      compareValue = parseEntry(attrValue, columns.get(attrIndex));
    }

    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();
      Entry columnValue = row.getEntries().get(attrIndex);

      boolean flag = false;
      if (comparator == null) {
        flag = true;
      } else if (comparator.LT() != null) {
        if (columnValue.compareTo(compareValue) < 0) flag = true;
      } else if (comparator.GT() != null) {
        if (columnValue.compareTo(compareValue) > 0) flag = true;
      } else if (comparator.LE() != null) {
        if (columnValue.compareTo(compareValue) <= 0) flag = true;
      } else if (comparator.GE() != null) {
        if (columnValue.compareTo(compareValue) >= 0) flag = true;
      } else if (comparator.EQ() != null) {
        if (columnValue.compareTo(compareValue) == 0) flag = true;
      } else if (comparator.NE() != null) {
        if (columnValue.compareTo(compareValue) != 0) flag = true;
      }
      if (flag) {
        rows.add(row);
      }
    }
    return rows;
  }

  @Override
  public ExecuteStatementResp executeStatement(ExecuteStatementReq req) throws TException {
    if (req.getSessionId() < 0) {
      return new ExecuteStatementResp(
          StatusUtil.fail("You are not connected. Please connect first."), false);
    }
    // TODO: implement execution logic
    LogicalPlan plan = LogicalGenerator.generate(req.statement);
    switch (plan.getType()) {
      case CREATE_DB:
        System.out.println("[DEBUG] " + plan);
        CreateDatabasePlan the_plan = (CreateDatabasePlan) plan;
        String name = the_plan.getDatabaseName();
        manager.createDatabaseIfNotExists(name);
        manager.persist();
        return new ExecuteStatementResp(StatusUtil.success(), false);

      case DROP_DB:
        System.out.println("[DEBUG] " + plan);
        DropDatabasePlan drop_plan = (DropDatabasePlan) plan;
        String drop_name = drop_plan.getDatabaseName();
        try {
          manager.deleteDatabase(drop_name);
          manager.persist();
          return new ExecuteStatementResp(StatusUtil.success(), false);
        } catch (Exception e) {
          return new ExecuteStatementResp(StatusUtil.fail(e.getMessage()), false);
        }

      case USE_DB:
        System.out.println("[DEBUG] " + plan);
        UseDatabasePlan use_plan = (UseDatabasePlan) plan;
        String use_name = use_plan.getDatabase();
        manager.switchDatabase(use_name);
        return new ExecuteStatementResp(StatusUtil.success(), false);

      case CREATE_TABLE:
        System.out.println("[DEBUG] " + plan);
        CreateTablePlan ct_plan = (CreateTablePlan) plan;
        SQLParser.CreateTableStmtContext ctx = ct_plan.getCtx();
        Database database = manager.getCurrentDatabase();
        String ct_name = ctx.tableName().children.get(0).toString();

        ArrayList<Column> columns = new ArrayList<Column>();

        List<SQLParser.ColumnDefContext> column_def = ctx.columnDef();
        int len = column_def.size();

        String primary = ctx.tableConstraint().getChild(3).getText().toLowerCase();

        for (int i = 0; i < len; i++) {
          Column newcolumn = null;
          SQLParser.ColumnDefContext the_column = column_def.get(i);
          int num = the_column.getChildCount();

          if (num == 2) {
            String attrname = the_column.getChild(0).getText().toLowerCase();
            String type = the_column.getChild(1).getText().toLowerCase();

            if (attrname.equals(primary)) { // 主键情况
              switch (type) {
                case "int":
                  newcolumn = new Column(attrname, ColumnType.INT, 1, false, 0);
                  break;
                case "long":
                  newcolumn = new Column(attrname, ColumnType.LONG, 1, false, 0);
                  break;
                case "double":
                  newcolumn = new Column(attrname, ColumnType.DOUBLE, 1, false, 0);
                  break;
                case "float":
                  newcolumn = new Column(attrname, ColumnType.FLOAT, 1, false, 0);
                  break;
                default:
                  if (type.substring(0, 6).equals("string")) {
                    String max = type.substring(7, type.length() - 1);
                    newcolumn =
                        new Column(attrname, ColumnType.STRING, 1, false, Integer.parseInt(max));
                  } else {
                    throw new RuntimeException("error string");
                  }
              }
            } else { // 非主键情况
              switch (type) {
                case "int":
                  newcolumn = new Column(attrname, ColumnType.INT, 0, false, 0);
                  break;
                case "long":
                  newcolumn = new Column(attrname, ColumnType.LONG, 0, false, 0);
                  break;
                case "double":
                  newcolumn = new Column(attrname, ColumnType.DOUBLE, 0, false, 0);
                  break;
                case "float":
                  newcolumn = new Column(attrname, ColumnType.FLOAT, 0, false, 0);
                  break;
                default:
                  if (type.substring(0, 6).equals("string")) {
                    String max = type.substring(7, type.length() - 1);
                    newcolumn =
                        new Column(attrname, ColumnType.STRING, 0, false, Integer.parseInt(max));
                  } else {
                    throw new RuntimeException("error string");
                  }
              }
            }

          } else if (num >= 3) { // 判断是否有关键字约束

            String attrname = the_column.getChild(0).getText().toLowerCase();
            String type = the_column.getChild(1).getText().toLowerCase();

            String cons = the_column.getChild(2).getText();

            if (attrname.equals(primary)) { // 主键情况
              switch (type) {
                case "int":
                  newcolumn = new Column(attrname, ColumnType.INT, 1, true, 0);
                  break;
                case "long":
                  newcolumn = new Column(attrname, ColumnType.LONG, 1, true, 0);
                  break;
                case "double":
                  newcolumn = new Column(attrname, ColumnType.DOUBLE, 1, true, 0);
                  break;
                case "float":
                  newcolumn = new Column(attrname, ColumnType.FLOAT, 1, true, 0);
                  break;
                default:
                  if (type.substring(0, 6).equals("string")) {
                    String max = type.substring(7, type.length() - 1);

                    newcolumn =
                        new Column(attrname, ColumnType.STRING, 1, true, Integer.parseInt(max));
                  } else {
                    throw new RuntimeException("error string");
                  }
              }
            } else { // 非主键情况
              switch (type) {
                case "int":
                  newcolumn = new Column(attrname, ColumnType.INT, 0, true, 0);
                  break;
                case "long":
                  newcolumn = new Column(attrname, ColumnType.LONG, 0, true, 0);
                  break;
                case "double":
                  newcolumn = new Column(attrname, ColumnType.DOUBLE, 0, true, 0);
                  break;
                case "float":
                  newcolumn = new Column(attrname, ColumnType.FLOAT, 0, true, 0);
                  break;
                default:
                  if (type.substring(0, 6).equals("string")) {
                    String max = type.substring(7, type.length() - 1);
                    newcolumn =
                        new Column(attrname, ColumnType.STRING, 0, true, Integer.parseInt(max));
                  } else {
                    throw new RuntimeException("error string");
                  }
              }
            }
          }
          columns.add(newcolumn);
        }

        Column[] all_column = new Column[columns.size()];
        for (int j = 0; j < columns.size(); j++) {
          all_column[j] = columns.get(j);
        }
        manager.getCurrentDatabase().create(ct_name, all_column);
        manager.getCurrentDatabase().quit(); // 触发持久化

        return new ExecuteStatementResp(StatusUtil.success(), false);

      case SHOW_TABLE:
        System.out.println("[DEBUG] " + plan);
        ShowTablePlan showTablePlan = (ShowTablePlan) plan;
        String tableName = showTablePlan.getTableName();
        ArrayList<String> showTableInfoResult =
            manager.currentDatabase.getTable(tableName).showTableInfo();
        ExecuteStatementResp showTableResp = new ExecuteStatementResp(StatusUtil.success(), true);
        showTableResp.columnsList = showTableInfoResult;
        showTableResp.rowList = new ArrayList<>();
        return showTableResp;

      case DROP_TABLE:
        System.out.println("[DEBUG] " + plan);
        DropTablePlan dropTablePlan = (DropTablePlan) plan;
        String dr_tableName = dropTablePlan.getTableName().toLowerCase();
        manager.currentDatabase.drop(dr_tableName);
        manager.getCurrentDatabase().quit();

        return new ExecuteStatementResp(StatusUtil.success(), false);

      case DELETE_ROW:
        System.out.println("[DEBUG] " + plan);
        DeletePlan deletePlan = (DeletePlan) plan;
        SQLParser.DeleteStmtContext d_ctx = deletePlan.getctx();
        String d_tableName = d_ctx.tableName().children.get(0).toString().toLowerCase();

        Database d_database = manager.getCurrentDatabase();
        Table table = d_database.getTable(d_tableName);
        ArrayList<Column> d_columns = table.columns;

        Iterator<Row> rowIterator = table.iterator();

        if (d_ctx.K_WHERE() == null) {
          while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            table.delete(row);
          }
        } else {
          String attrName =
              d_ctx
                  .multipleCondition()
                  .condition()
                  .expression(0)
                  .comparer()
                  .columnFullName()
                  .columnName()
                  .getText()
                  .toLowerCase();
          String attrValue =
              d_ctx
                  .multipleCondition()
                  .condition()
                  .expression(1)
                  .comparer()
                  .literalValue()
                  .getText();
          SQLParser.ComparatorContext comparator =
              d_ctx.multipleCondition().condition().comparator();
          int columnIndex = -1;
          for (int j = 0; j < d_columns.size(); j++) { // 找到属性对应的索引columnindex
            if (attrName.equals(d_columns.get(j).getColumnName())) {
              columnIndex = j;
              break;
            }
          }
          Entry compareValue = parseEntry(attrValue, d_columns.get(columnIndex));
          while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Entry columnValue = row.getEntries().get(columnIndex);
            if (comparator.LT() != null) {
              if (columnValue.compareTo(compareValue) < 0) table.delete(row);
            } else if (comparator.GT() != null) {
              if (columnValue.compareTo(compareValue) > 0) table.delete(row);
            } else if (comparator.LE() != null) {
              if (columnValue.compareTo(compareValue) <= 0) table.delete(row);
            } else if (comparator.GE() != null) {
              if (columnValue.compareTo(compareValue) >= 0) table.delete(row);
            } else if (comparator.EQ() != null) {
              if (columnValue.compareTo(compareValue) == 0) table.delete(row);
            } else if (comparator.NE() != null) {
              if (columnValue.compareTo(compareValue) != 0) table.delete(row);
            }
          }
        }
        manager.getCurrentDatabase().quit();
        return new ExecuteStatementResp(StatusUtil.success(), false);
      case INSERT_ROW:
        InsertPlan insertPlan = (InsertPlan) plan;
        SQLParser.InsertStmtContext insert_ctx = insertPlan.getctx();
        String itablename = insert_ctx.tableName().children.get(0).toString().toLowerCase();
        List<SQLParser.ColumnNameContext> columnName = insert_ctx.columnName();
        List<SQLParser.ValueEntryContext> valueEntry = insert_ctx.valueEntry();
        Database i_base = manager.getCurrentDatabase();
        Table i_table = i_base.getTable(itablename);
        ArrayList<Column> columnslist = i_table.columns;

        if (columnName.size() == 0) {
          for (SQLParser.ValueEntryContext value : valueEntry) {
            if (value.literalValue().size() != columnslist.size()) {
              throw new RuntimeException("insert ROW don't match column!");
            }
            ArrayList<Entry> entries = new ArrayList<>();

            for (int i = 0; i < columnslist.size(); i++) {
              entries.add(parseEntry(value.literalValue(i).getText(), columnslist.get(i)));
            }
            Row insert_row = new Row(entries);
            i_table.insert(insert_row);
          }
        } else {
          ArrayList<Integer> columnn_index = new ArrayList<>();
          for (int i = 0; i < columnName.size(); i++) {
            for (int j = 0; j < columnslist.size(); j++) {
              if (columnName.get(i).getText().equals(columnslist.get(j).getColumnName())) {
                columnn_index.add(j);
                break;
              }
            }
          }
          for (SQLParser.ValueEntryContext value : valueEntry) {
            if (value.literalValue().size() != columnslist.size()) {
              throw new RuntimeException();
            }
            ArrayList<Entry> entries = new ArrayList<>();

            for (int i = 0; i < columnslist.size(); i++) {
              entries.add(parseEntry(value.literalValue(i).getText(), columnslist.get(i)));
            }
            Row insert_row = new Row(entries);
            i_table.insert(insert_row);
          }
        }
        manager.getCurrentDatabase().quit();
        return new ExecuteStatementResp(StatusUtil.success(), false);

      case UPDATE_COLUMN:
        System.out.println("[DEBUG] " + plan);
        UpdateColumnPlan updateColumnPlan = (UpdateColumnPlan) plan;
        SQLParser.UpdateStmtContext updateStmtCTX = updateColumnPlan.getctx();
        String updateColumnTableName = updateStmtCTX.tableName().children.get(0).toString();
        String updateColumnColumnName = updateStmtCTX.columnName().children.get(0).toString();
        String attrName =
            updateStmtCTX
                .multipleCondition()
                .condition()
                .expression(0)
                .comparer()
                .columnFullName()
                .columnName()
                .getText()
                .toLowerCase();
        String attrValue =
            updateStmtCTX
                .multipleCondition()
                .condition()
                .expression(1)
                .comparer()
                .literalValue()
                .getText();
        String filterComparer = updateStmtCTX.expression().comparer().literalValue().getText();
        Database updateColumnDatabase = manager.getCurrentDatabase();
        Table updateColumnDatabaseTable = updateColumnDatabase.getTable(updateColumnTableName);
        ArrayList<Row> updateRows =
            getRowsValidForWhere(
                updateColumnDatabaseTable.columns,
                updateColumnDatabaseTable.iterator(),
                updateStmtCTX.multipleCondition().condition());
        for (Row row : updateRows) {
          ArrayList<Entry> rowEntries = new ArrayList<Entry>(row.getEntries());
          int attrIndex = -1;
          for (int i = 0; i < updateColumnDatabaseTable.columns.size(); ++i) {
            if (updateColumnDatabaseTable.columns.get(i).getColumnName().equals(attrName)) {
              attrIndex = i;
            }
          }
          rowEntries.set(
              attrIndex, parseEntry(attrValue, updateColumnDatabaseTable.columns.get(attrIndex)));
          updateColumnDatabaseTable.update(
              row.getEntries().get(updateColumnDatabaseTable.getPrimaryIndex()),
              new Row(rowEntries));
        }
        manager.getCurrentDatabase().quit();
        return new ExecuteStatementResp(StatusUtil.success(), false);

      case SELECT_TABLE:
        SelectPlan select_plan = (SelectPlan) plan;
        SQLParser.SelectStmtContext s_ctx = select_plan.getCtx();

        SQLParser.TableQueryContext query = s_ctx.tableQuery().get(0);

        QueryTable queryTable = null;
        QueryTable x_table = null;
        QueryTable y_table = null;
        if (query.getChildCount() == 1) { // 单个table   success!
          queryTable =
              new QueryTable(
                  manager
                      .getCurrentDatabase()
                      .getTable(query.tableName(0).getText().toLowerCase()));
        } else {

          x_table =
              new QueryTable(
                  manager
                      .getCurrentDatabase()
                      .getTable(query.tableName(0).getText().toLowerCase()));
          y_table =
              new QueryTable(
                  manager
                      .getCurrentDatabase()
                      .getTable(query.tableName(1).getText().toLowerCase()));

          SQLParser.ConditionContext joincondition =
              s_ctx.tableQuery().get(0).multipleCondition().condition();
          queryTable = new QueryTable(x_table, y_table, joincondition);
        }
        // 对where中的条件的
        if (s_ctx.K_WHERE() != null) {
          SQLParser.ConditionContext selectCondition = s_ctx.multipleCondition().condition();

          ArrayList<Row> newRows =
              getRowsValidForWhere(queryTable.columns, queryTable.rows.iterator(), selectCondition);

          queryTable.rows = newRows;
        }
        // 对select进行选择

        List<SQLParser.ResultColumnContext> resultColumn = s_ctx.resultColumn();
        ArrayList<Integer> finalIndexs = new ArrayList<>();
        ArrayList<String> finalNames = new ArrayList<>();
        for (SQLParser.ResultColumnContext columnContext : resultColumn) {
          String s_columnName = columnContext.columnFullName().getText().toLowerCase();
          finalNames.add(s_columnName);
          int index = -1;
          for (int i = 0; i < queryTable.columns.size(); i++) {
            if (queryTable.columns.get(i).getColumnName().equals(s_columnName)) {
              index = i;
            }
          }
          finalIndexs.add(index);
        }
        ArrayList<Row> finalRows = new ArrayList<>();
        for (Row the_row : queryTable.rows) {
          ArrayList<Entry> finalRowEntry = new ArrayList<>();
          for (int index : finalIndexs) {
            try {
              finalRowEntry.add(the_row.getEntries().get(index));
            } catch (Exception e) {
              return new ExecuteStatementResp(StatusUtil.fail(e.getMessage()), false);
            }
          }
          finalRows.add(new Row(finalRowEntry));
        }

        List<List<String>> finalStr = new ArrayList<>();
        for (Row row : finalRows) {
          List<String> rowstr = new ArrayList<>();
          for (Entry entry : row.getEntries()) {
            rowstr.add(entry.toString());
          }
          finalStr.add(rowstr);
        }
        ExecuteStatementResp resp = new ExecuteStatementResp(StatusUtil.success(), true);
        resp.rowList = finalStr;
        resp.columnsList = finalNames;
        return resp;

      default:
        return new ExecuteStatementResp(StatusUtil.success(), false);
    }
  }
}
