package com.dianping.zebra.admin.dao;

import com.dianping.zebra.admin.entity.HeartbeatEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface HeartBeatMapper {
    @Select("SELECT * FROM heartbeat")
    List<HeartbeatEntity> getAll();
}
