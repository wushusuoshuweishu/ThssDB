package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.sql.SQLParser;

public class SelectPlan extends LogicalPlan {
  private SQLParser.SelectStmtContext ctx;

  public SelectPlan(SQLParser.SelectStmtContext ctx) {
    super(LogicalPlanType.SELECT_TABLE);
    this.ctx = ctx;
  }

  public SQLParser.SelectStmtContext getCtx() {
    return this.ctx;
  }
}
