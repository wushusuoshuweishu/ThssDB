package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;

public class ShowTablePlan extends LogicalPlan {
  private final String tableName;

  public ShowTablePlan(String name) {
    super(LogicalPlanType.SHOW_TABLE);
    this.tableName = name;
  }

  public String getTableName() {
    return this.tableName;
  }
}
