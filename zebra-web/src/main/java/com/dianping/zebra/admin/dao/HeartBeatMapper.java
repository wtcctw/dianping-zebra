package com.dianping.zebra.admin.dao;

import com.dianping.zebra.admin.entity.HeartbeatEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public interface HeartbeatMapper {
    List<HeartbeatEntity> getHeartbeat(@Param("appName") String appName, @Param("ip") String ip, @Param("datasourceBeanName") String datasourceBeanName);

    int deleteHeartbeat(@Param("appName") String appName, @Param("ip") String ip, @Param("datasourceBeanName") String datasourceBeanName);

    int insertHeartbeat(HeartbeatEntity entity);

    int updateHeartbeat(HeartbeatEntity entity);
}
