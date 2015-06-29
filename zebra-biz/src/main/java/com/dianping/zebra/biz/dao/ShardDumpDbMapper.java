package com.dianping.zebra.biz.dao;

import com.dianping.zebra.biz.entity.ShardDumpDbEntity;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dozer @ 6/1/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface ShardDumpDbMapper {

    List<ShardDumpDbEntity> getDbByName(@Param("name") String name);

    List<ShardDumpDbEntity> getAll();

    void create(ShardDumpDbEntity entity);

    void delete(@Param("id") int id);
}
