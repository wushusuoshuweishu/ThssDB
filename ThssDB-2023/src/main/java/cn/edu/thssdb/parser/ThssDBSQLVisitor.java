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
import cn.edu.thssdb.plan.impl.DropDatabasePlan;
import cn.edu.thssdb.plan.impl.UseDatabasePlan;
import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Database;
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.sql.SQLBaseVisitor;
import cn.edu.thssdb.sql.SQLParser;

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
    List<SQLParser.ColumnDefContext> column_def = ctx.columnDef();
    String constraint = ctx.tableConstraint().getText();
    int len = column_def.size();
    for (int i = 0;i < len;i++){
      String column = column_def.get(i).getText();
      String[] column_spilt = column.split(" ");
    }


    return null;

  }


}
