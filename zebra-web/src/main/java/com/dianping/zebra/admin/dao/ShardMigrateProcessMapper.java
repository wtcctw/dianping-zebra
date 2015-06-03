package com.dianping.zebra.admin.dao;

import com.dianping.zebra.admin.entity.ShardMigrateProcessEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dozer @ 6/3/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface ShardMigrateProcessMapper {
    List<ShardMigrateProcessEntity> getByName(@Param(value = "name") String name);

    void update(ShardMigrateProcessEntity entity);

    void create(ShardMigrateProcessEntity entity);
}
