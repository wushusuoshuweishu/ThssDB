package cn.edu.thssdb.plan;

public abstract class LogicalPlan {

  protected LogicalPlanType type;

  public LogicalPlan(LogicalPlanType type) {
    this.type = type;
  }

  public LogicalPlanType getType() {
    return type;
  }

  public enum LogicalPlanType {
    // TODO: add more LogicalPlanType
    CREATE_DB,
    DROP_DB,
    USE_DB,
    CREATE_TABLE,
    SHOW_TABLE,
    DROP_TABLE,
    DELETE_ROW,
    INSERT_ROW,
    UPDATE_COLUMN,
    SELECT_TABLE,
    BEGIN_TRANSACTION,
    COMMIT
  }
}
