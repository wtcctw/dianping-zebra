package com.dianping.zebra.biz.dao;

import com.dianping.zebra.biz.entity.FlowControlEntity;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlowControlMapper {

    List<FlowControlEntity> findAllActiveFlowControlByEnv(@Param("env") String env);

    List<FlowControlEntity> findAllDeletedFlowControlByEnv(@Param("env") String env);

    void insertFlowControl(FlowControlEntity entity);

    void deleteFlowControl(@Param("sqlId") String sqlId, @Param("env") String env);
}
