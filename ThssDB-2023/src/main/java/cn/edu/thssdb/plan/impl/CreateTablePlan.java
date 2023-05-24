package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;

public class CreateTablePlan extends LogicalPlan {

  private String tablename;

  public CreateTablePlan(String name) {
    super(LogicalPlanType.CREATE_TABLE);
    this.tablename = name;
  }
}
