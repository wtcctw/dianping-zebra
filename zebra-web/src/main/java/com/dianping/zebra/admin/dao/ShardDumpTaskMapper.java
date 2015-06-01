package com.dianping.zebra.admin.dao;

import com.dianping.zebra.admin.entity.ShardDumpDbEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dozer @ 6/1/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface ShardDumpTaskMapper {
    List<ShardDumpDbEntity> getByExecutor(@Param("executor") String executor);
}
