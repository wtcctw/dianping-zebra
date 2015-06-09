package com.dianping.zebra.admin.dao;

import com.dianping.zebra.admin.entity.ShardSyncTaskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dozer @ 6/8/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface ShardSyncTaskMapper {

    List<ShardSyncTaskEntity> getByExecutor(@Param("executor") String executor);

    List<ShardSyncTaskEntity> getByName(@Param("ruleName") String ruleName);

    int getCountByName(@Param("ruleName") String ruleName);

    void create(ShardSyncTaskEntity entity);

    void remove(@Param("id") int id);
}
