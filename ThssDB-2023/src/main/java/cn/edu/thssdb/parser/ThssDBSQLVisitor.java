/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.edu.thssdb.parser;

import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.plan.impl.CreateDatabasePlan;
import cn.edu.thssdb.plan.impl.CreateTablePlan;
import cn.edu.thssdb.plan.impl.DropDatabasePlan;
import cn.edu.thssdb.plan.impl.UseDatabasePlan;
import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Database;
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.sql.SQLBaseVisitor;
import cn.edu.thssdb.sql.SQLParser;
import cn.edu.thssdb.type.ColumnType;

import java.util.ArrayList;
import java.util.List;

public class ThssDBSQLVisitor extends SQLBaseVisitor<LogicalPlan> {

  private Manager manager;

  public ThssDBSQLVisitor() {
    this.manager = Manager.getInstance();
  }

  // 创建数据库
  @Override
  public LogicalPlan visitCreateDbStmt(SQLParser.CreateDbStmtContext ctx) {
    String name = ctx.databaseName().getText();
    manager.createDatabaseIfNotExists(name);
    System.out.println("go to create database");
    manager.persist();
    return new CreateDatabasePlan(name);
  }

  // TODO: parser to more logical plan
  // 删除数据库
  @Override
  public LogicalPlan visitDropDbStmt(SQLParser.DropDbStmtContext ctx) {
    String name = ctx.databaseName().getText();

    manager.deleteDatabase(name);
    manager.persist();
    return new DropDatabasePlan(name);
  }
  // 切换数据库
  @Override
  public LogicalPlan visitUseDbStmt(SQLParser.UseDbStmtContext ctx) {
    String name = ctx.databaseName().getText();
    manager.switchDatabase(name);
    return new UseDatabasePlan(name);
  }
  // 创建表
  @Override
  public LogicalPlan visitCreateTableStmt(SQLParser.CreateTableStmtContext ctx) {
    System.out.println("will create table");

    Database database = manager.getCurrentDatabase();
    String name = ctx.tableName().children.get(0).toString();

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
        }
        { // 非主键情况
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

                newcolumn = new Column(attrname, ColumnType.STRING, 1, true, Integer.parseInt(max));
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
                newcolumn = new Column(attrname, ColumnType.STRING, 0, true, Integer.parseInt(max));
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
    manager.getCurrentDatabase().create(name, all_column);
    manager.getCurrentDatabase().quit(); // 触发持久化

    return new CreateTablePlan(name);
  }
}
