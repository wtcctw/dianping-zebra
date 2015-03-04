package com.dianping.zebra.migrate.task;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class TaskConfig {
    private int taskId;

    private String keyName;

    // where key >= keyStart
    private int keyStart;

    // where key < keyEnd
    private int keyEnd;

    //limit 1000
    private int limitPerQuery;

    private int sleepSecondsAfterPerQuery;

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

    public int getLimitPerQuery() {
        return limitPerQuery;
    }

    public void setLimitPerQuery(int limitPerQuery) {
        this.limitPerQuery = limitPerQuery;
    }

    public int getSleepSecondsAfterPerQuery() {
        return sleepSecondsAfterPerQuery;
    }

    public void setSleepSecondsAfterPerQuery(int sleepSecondsAfterPerQuery) {
        this.sleepSecondsAfterPerQuery = sleepSecondsAfterPerQuery;
    }
}