package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;

public class UseDatabasePlan extends LogicalPlan {

  private String database;

  public UseDatabasePlan(String name) {
    super(LogicalPlanType.USE_DB);
    this.database = name;
  }
}
