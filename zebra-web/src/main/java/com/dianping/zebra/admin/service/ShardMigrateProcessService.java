package com.dianping.zebra.admin.service;

import com.dianping.zebra.biz.entity.ShardMigrateProcessEntity;

/**
 * Dozer @ 6/3/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface ShardMigrateProcessService {

    ShardMigrateProcessEntity getProcessByName(String name);

    void updateProcess(ShardMigrateProcessEntity entity);
}
