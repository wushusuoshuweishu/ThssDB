package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;

public class DropTablePlan extends LogicalPlan {

  String table_name;

  public DropTablePlan(String name) {
    super(LogicalPlanType.DROP_TABLE);
    this.table_name = name;
  }

  public String getTableName() {
    return this.table_name;
  }
}
