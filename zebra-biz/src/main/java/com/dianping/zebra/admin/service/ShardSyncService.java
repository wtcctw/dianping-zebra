package com.dianping.zebra.admin.service;

import com.dianping.zebra.admin.entity.ShardSyncTaskEntity;

import java.util.List;

/**
 * Dozer @ 6/2/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface ShardSyncService {

    void createTask(ShardSyncTaskEntity entity);

    void removeTask(int id);

    List<ShardSyncTaskEntity> getTaskByIp(String ip);

    List<ShardSyncTaskEntity> getTaskByName(String name);
}
