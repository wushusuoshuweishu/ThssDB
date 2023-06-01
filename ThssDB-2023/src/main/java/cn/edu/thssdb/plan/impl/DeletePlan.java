package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.sql.SQLParser;

public class DeletePlan extends LogicalPlan {

  private SQLParser.DeleteStmtContext ctx;

  public DeletePlan(SQLParser.DeleteStmtContext ctx) {
    super(LogicalPlanType.DELETE_ROW);
    this.ctx = ctx;
  }

  public SQLParser.DeleteStmtContext getctx() {
    return this.ctx;
  }
}
