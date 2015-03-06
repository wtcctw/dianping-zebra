package com.dianping.zebra.migrate.task;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class TaskConfig {

    public final static String TASK_TYPE_MIGRATE = "migrate";

    public final static String TASK_TYPE_VERIFY = "verify";

    private int taskId;

    private String tableName;

    private String keyName;

    // where key >= keyStart
    private int keyStart;

    // where key <= keyEnd
    private int keyEnd;

    //limit 1000
    private int pageSize;

    private int sleepSecondsAfterPerQuery;

    private String taskType;

    public String getTaskType() {
        return taskType;
    }

    public TaskConfig setTaskType(String taskType) {
        this.taskType = taskType;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public TaskConfig setTableName(String tableName) {
        this.tableName = tableName;
        return this;

    }

    public int getTaskId() {
        return taskId;
    }

    public TaskConfig setTaskId(int taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getKeyName() {
        return keyName;
    }

    public TaskConfig setKeyName(String keyName) {
        this.keyName = keyName;
        return this;
    }

    public int getKeyStart() {
        return keyStart;
    }

    public TaskConfig setKeyStart(int keyStart) {
        this.keyStart = keyStart;
        return this;
    }

    public int getKeyEnd() {
        return keyEnd;
    }

    public TaskConfig setKeyEnd(int keyEnd) {
        this.keyEnd = keyEnd;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public TaskConfig setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getSleepSecondsAfterPerQuery() {
        return sleepSecondsAfterPerQuery;
    }

    public TaskConfig setSleepSecondsAfterPerQuery(int sleepSecondsAfterPerQuery) {
        this.sleepSecondsAfterPerQuery = sleepSecondsAfterPerQuery;
        return this;
    }
}