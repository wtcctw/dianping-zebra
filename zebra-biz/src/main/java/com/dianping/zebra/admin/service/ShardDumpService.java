package com.dianping.zebra.admin.service;

import com.dianping.zebra.admin.entity.ShardDumpDbEntity;
import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;

import java.util.List;

/**
 * Dozer @ 6/2/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface ShardDumpService {
    void updateTaskStatus(ShardDumpTaskEntity entity);

    List<ShardDumpTaskEntity> getTaskByIp(String ip);

    List<ShardDumpTaskEntity> getTaskByName(String name);

    List<ShardDumpDbEntity> getAllDb();

    void createDb(ShardDumpDbEntity entity);

    void deleteDb(int id);
}
