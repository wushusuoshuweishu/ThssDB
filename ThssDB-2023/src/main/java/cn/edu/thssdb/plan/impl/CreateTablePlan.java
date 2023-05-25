package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.sql.SQLParser;

public class CreateTablePlan extends LogicalPlan {

  SQLParser.CreateTableStmtContext ctx;

  public CreateTablePlan(SQLParser.CreateTableStmtContext ctx) {
    super(LogicalPlanType.CREATE_TABLE);

    this.ctx = ctx;
  }

  public SQLParser.CreateTableStmtContext getCtx() {
    return ctx;
  };
}
