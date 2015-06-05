package com.dianping.zebra.admin.dao;

import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dozer @ 6/1/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface ShardDumpTaskMapper {
    List<ShardDumpTaskEntity> getByExecutor(@Param("executor") String executor);

    List<ShardDumpTaskEntity> getByName(@Param("name") String name);

    void updateStatus(ShardDumpTaskEntity entity);

    void create(ShardDumpTaskEntity entity);

    void remove(@Param("id") int id);
}
