package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.sql.SQLParser;

public class InsertPlan extends LogicalPlan {
  private SQLParser.InsertStmtContext ctx;

  public InsertPlan(SQLParser.InsertStmtContext ctx) {
    super(LogicalPlanType.INSERT_ROW);
    this.ctx = ctx;
  }

  public SQLParser.InsertStmtContext getctx() {
    return this.ctx;
  }
}
