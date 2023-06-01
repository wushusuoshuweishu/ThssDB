package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.sql.SQLParser;

public class UpdateColumnPlan extends LogicalPlan {
  private final SQLParser.UpdateStmtContext ctx;

  public UpdateColumnPlan(SQLParser.UpdateStmtContext ctx) {
    super(LogicalPlanType.UPDATE_COLUMN);
    this.ctx = ctx;
  }

  public SQLParser.UpdateStmtContext getctx() {
    return this.ctx;
  }
}
