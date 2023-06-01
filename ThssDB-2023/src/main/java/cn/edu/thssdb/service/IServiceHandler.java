package cn.edu.thssdb.service;

import cn.edu.thssdb.plan.LogicalGenerator;
import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.plan.impl.*;
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

  public static ArrayList<Row> getRowsValidForWhere (Table table, SQLParser.ConditionContext updateCondition) {
    ArrayList<Column> columns = table.columns;
    Iterator<Row> rowIterator = table.iterator();
    String attrName = null;
    String attrValue = null;
    int attrIndex = -1;
    SQLParser.ComparatorContext comparator = null;
    Entry compareValue = null;
    ArrayList<Row>rows = new ArrayList<Row>();

    if (updateCondition != null) {
      attrName = updateCondition.expression(0).comparer().columnFullName().columnName().getText().toLowerCase();
      attrValue = updateCondition.expression(1).comparer().literalValue().getText();
      for (int i = 0; i < columns.size(); ++i) {
        if (columns.get(i).getColumnName().equals(attrName)) {
          attrIndex = i;
        }
      }
      comparator = updateCondition.comparator();
      compareValue = parseEntry(attrValue, columns.get(attrIndex));
    }

    while (rowIterator.hasNext()){
      Row row = rowIterator.next();
      Entry columnValue = row.getEntries().get(attrIndex);
      boolean flag = false;
      if(comparator == null) {
        flag = true;
      } else if (comparator.LT() != null) {
        if (columnValue.compareTo(compareValue) < 0)
          flag = true;
      } else if(comparator.GT() != null) {
        if (columnValue.compareTo(compareValue) > 0)
          flag = true;
      } else if(comparator.LE() != null) {
        if (columnValue.compareTo(compareValue) <= 0)
          flag = true;
      } else if(comparator.GE() != null) {
        if (columnValue.compareTo(compareValue) >= 0)
          flag = true;
      } else if(comparator.EQ() != null) {
        if (columnValue.compareTo(compareValue) == 0)
          flag = true;
      } else if(comparator.NE() != null) {
        if (columnValue.compareTo(compareValue) != 0)
          flag = true;
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
        manager.deleteDatabase(drop_name);
        manager.persist();
        return new ExecuteStatementResp(StatusUtil.success(), false);
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

        String primary = ctx.tableConstraint().getChild(3).getText();
        System.out.println(primary);

        for (int i = 0; i < len; i++) {
          Column newcolumn = null;
          SQLParser.ColumnDefContext the_column = column_def.get(i);
          int num = the_column.getChildCount();

          if (num == 2) {
            String attrname = the_column.getChild(0).getText();
            String type = the_column.getChild(1).getText().toLowerCase();
            System.out.println(attrname);
            System.out.println(type);
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

            String attrname = the_column.getChild(0).getText();
            String type = the_column.getChild(1).getText().toLowerCase();
            System.out.println(attrname);
            System.out.println(type + num);
            String cons = the_column.getChild(2).getText();

            if (attrname.equals(primary)) { // 主键情况
              switch (type) {
                case "int":
                  System.out.println("hello");
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
          System.out.println(all_column[j]);
        }
        manager.getCurrentDatabase().create(ct_name, all_column);
        manager.getCurrentDatabase().quit(); // 触发持久化

        return new ExecuteStatementResp(StatusUtil.success(), false);
      case SHOW_TABLE:
        System.out.println("[DEBUG] " + plan);
        ShowTablePlan showTablePlan = (ShowTablePlan) plan;
        String tableName = showTablePlan.getTableName();
        manager.currentDatabase.getTable(tableName).showTableInfo();

        return new ExecuteStatementResp(StatusUtil.success(), false);
      case DROP_TABLE:
        System.out.println("[DEBUG] " + plan);
        DropTablePlan dropTablePlan = (DropTablePlan) plan;
        String dr_tableName = dropTablePlan.getTableName();
        manager.currentDatabase.drop(dr_tableName);

        return new ExecuteStatementResp(StatusUtil.success(), false);
      case DELETE_ROW:
        System.out.println("[DEBUG] " + plan);
        DeletePlan deletePlan = (DeletePlan) plan;
        SQLParser.DeleteStmtContext d_ctx = deletePlan.getctx();
        String d_tableName = d_ctx.tableName().children.get(0).toString();

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
        return new ExecuteStatementResp(StatusUtil.success(), false);
      default:
        return new ExecuteStatementResp(StatusUtil.success(), false);
    }
  }
}
