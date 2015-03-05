package com.dianping.zebra.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dianping.zebra.admin.entity.FlowControlEntity;

public interface FlowControlMapper {

	public List<FlowControlEntity> findAllActiveFlowControlByEnv(String env);
	
	public List<FlowControlEntity> findAllDeletedFlowControlByEnv(String env);

	public void insertFlowControl(FlowControlEntity entity);

	public void deleteFlowControl(@Param("sqlId")String sqlId, @Param("env")String env);
}
