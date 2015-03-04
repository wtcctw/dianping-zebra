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
}