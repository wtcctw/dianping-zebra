package com.dianping.zebra.biz.service;

import com.dianping.zebra.biz.entity.ShardDumpDbEntity;
import com.dianping.zebra.biz.entity.ShardDumpTaskEntity;

import java.util.List;

/**
 * Dozer @ 6/2/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface ShardDumpService {
    void updateTaskStatus(ShardDumpTaskEntity entity);

    void createTask(ShardDumpTaskEntity entity);

    void removeTask(int id);

    String getPrimaryKey(String instance, String db, String table);

    long getMaxIndex(String instance, String db, String table, String key);

    List<ShardDumpTaskEntity> getTaskByIp(String ip);

    List<ShardDumpTaskEntity> getTaskByName(String name);

    List<ShardDumpDbEntity> getAllDb();

    void createDb(ShardDumpDbEntity entity);

    void deleteDb(int id);
}
