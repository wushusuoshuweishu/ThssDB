package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;

public class DropDatabasePlan extends LogicalPlan {

  private String databaseName;

  public DropDatabasePlan(String name) {
    super(LogicalPlanType.DROP_DB);
    this.databaseName = name;
  }
}
