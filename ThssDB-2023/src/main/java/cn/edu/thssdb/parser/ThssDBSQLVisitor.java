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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;

public class ThssDBSQLVisitor extends SQLBaseVisitor<LogicalPlan> {

  private Manager manager;

  private Database database;

  public ThssDBSQLVisitor() {
    this.manager = Manager.getInstance();
  }

  //创建数据库
  @Override
  public LogicalPlan visitCreateDbStmt(SQLParser.CreateDbStmtContext ctx) {
    String name = ctx.databaseName().getText();
    manager.createDatabaseIfNotExists(name);
    manager.persist();
    return new CreateDatabasePlan(name);
  }

  // TODO: parser to more logical plan
  //删除数据库
  @Override
  public LogicalPlan visitDropDbStmt(SQLParser.DropDbStmtContext ctx) {
    String name = ctx.databaseName().getText();
    manager.deleteDatabase(name);
    manager.persist();
    return new DropDatabasePlan(name);
  }
  //切换数据库
  @Override
  public LogicalPlan visitUseDbStmt(SQLParser.UseDbStmtContext ctx) {
    String name = ctx.databaseName().getText();
    manager.switchDatabase(name);
    database = manager.getCurrentDatabase();
    return new UseDatabasePlan(name);

  }
  //创建表
  @Override
  public LogicalPlan visitCreateTableStmt(SQLParser.CreateTableStmtContext ctx){
    String name = ctx.tableName().getText();
    ArrayList<Column> columns = new ArrayList<Column>();

    List<SQLParser.ColumnDefContext> column_def = ctx.columnDef();
    int len = column_def.size();

    String constraint = ctx.tableConstraint().getText();
    String[] cons = constraint.split(" ");
    String primary = "";
    if(cons[0].toLowerCase().equals("primary")){    //判断出谁是主键
      String key = cons[1];
      primary = key.substring(4,key.length()-1);
    }

    for (int i = 0;i < len;i++){
      Column newcolumn = null;
      String column = column_def.get(i).getText().toLowerCase();
      String[] column_spilt = column.split(" ");
      if (column_spilt.length == 2 ){
        String attrname = column_spilt[0];
        String type = column_spilt[1].toLowerCase();
        if (attrname.equals(primary)){    //主键情况
          switch(type){
            case "int" :
              newcolumn = new Column(attrname, ColumnType.INT,1,false,0);
            case "long" :
              newcolumn = new Column(attrname, ColumnType.LONG,1,false,0);
            case "double" :
              newcolumn = new Column(attrname, ColumnType.DOUBLE,1,false,0);
            case "float" :
              newcolumn = new Column(attrname, ColumnType.FLOAT,1,false,0);
            default:
              if (type.substring(0,6).equals("string")){
                String max = type.substring(6,type.length()-1);
                newcolumn = new Column(attrname, ColumnType.STRING,1,false,Integer.parseInt(max));
              }else {
                throw new RuntimeException("error string");
              }
          }

        }{    //非主键情况
          switch(type){
            case "int" :
              newcolumn = new Column(attrname, ColumnType.INT,0,false,0);
            case "long" :
              newcolumn = new Column(attrname, ColumnType.LONG,0,false,0);
            case "double" :
              new Column(attrname, ColumnType.DOUBLE,0,false,0);
            case "float" :
              new Column(attrname, ColumnType.FLOAT,0,false,0);
            default:
              if (type.substring(0,6).equals("string")){
                String max = type.substring(6,type.length()-1);
                newcolumn = new Column(attrname, ColumnType.STRING,0,false,Integer.parseInt(max));
              }else {
                throw new RuntimeException("error string");
              }
          }
        }

      }else {                               //判断是否有关键字约束
        String attrname = column_spilt[0];
        String type = column_spilt[1].toLowerCase();
        String con = column_spilt[2].toLowerCase();
        if (con.equals("not null")){
          if (attrname.equals(primary)){    //主键情况
            switch(type){
              case "int" :
                newcolumn = new Column(attrname, ColumnType.INT,1,false,0);
              case "long" :
                newcolumn = new Column(attrname, ColumnType.LONG,1,false,0);
              case "double" :
                newcolumn = new Column(attrname, ColumnType.DOUBLE,1,false,0);
              case "float" :
                newcolumn = new Column(attrname, ColumnType.FLOAT,1,false,0);
              default:
                if (type.substring(0,6).equals("string")){
                  String max = type.substring(6,type.length()-1);
                  newcolumn = new Column(attrname, ColumnType.STRING,1,false,Integer.parseInt(max));
                }else {
                  throw new RuntimeException("error string");
                }
            }

          }{    //非主键情况
            switch(type){
              case "int" :
                newcolumn = new Column(attrname, ColumnType.INT,0,false,0);
              case "long" :
                newcolumn = new Column(attrname, ColumnType.LONG,0,false,0);
              case "double" :
                new Column(attrname, ColumnType.DOUBLE,0,false,0);
              case "float" :
                new Column(attrname, ColumnType.FLOAT,0,false,0);
              default:
                if (type.substring(0,6).equals("string")){
                  String max = type.substring(6,type.length()-1);
                  newcolumn = new Column(attrname, ColumnType.STRING,0,false,Integer.parseInt(max));
                }else {
                  throw new RuntimeException("error string");
                }
            }
          }
        }

      }
      if (newcolumn != null) {
        columns.add(newcolumn);
      }

    }
    Column[] all_column = new Column[columns.size()];
    for (int j = 0;j < columns.size() ; j++ ) {
      all_column[j] = columns.get(j);
    }
    this.database.create(name, all_column);
    this.database.quit();   //触发持久化


    return new CreateTablePlan(name);

  }



}
