package com.dianping.zebra.migrate.task;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class TaskConfig {

    public final static String TASK_TYPE_MIGRATE = "migrate";

    public final static String TASK_TYPE_VERIFY= "verify";

    private int taskId;

    private String tableName;

    private String keyName;

    // where key >= keyStart
    private int keyStart;

    // where key < keyEnd
    private int keyEnd;

    //limit 1000
    private int pageSize;

    private int sleepSecondsAfterPerQuery;

    private String taskType;

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public int getKeyStart() {
        return keyStart;
    }

    public void setKeyStart(int keyStart) {
        this.keyStart = keyStart;
    }

    public int getKeyEnd() {
        return keyEnd;
    }

    public void setKeyEnd(int keyEnd) {
        this.keyEnd = keyEnd;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSleepSecondsAfterPerQuery() {
        return sleepSecondsAfterPerQuery;
    }

    public void setSleepSecondsAfterPerQuery(int sleepSecondsAfterPerQuery) {
        this.sleepSecondsAfterPerQuery = sleepSecondsAfterPerQuery;
    }
}