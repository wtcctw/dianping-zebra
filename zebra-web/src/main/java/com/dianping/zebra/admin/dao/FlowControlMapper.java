package com.dianping.zebra.admin.dao;

import com.dianping.zebra.admin.entity.FlowControlEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FlowControlMapper {

    List<FlowControlEntity> findAllActiveFlowControlByEnv(@Param("env") String env);

    List<FlowControlEntity> findAllDeletedFlowControlByEnv(@Param("env") String env);

    void insertFlowControl(FlowControlEntity entity);

    void deleteFlowControl(@Param("sqlId") String sqlId, @Param("env") String env);
}
