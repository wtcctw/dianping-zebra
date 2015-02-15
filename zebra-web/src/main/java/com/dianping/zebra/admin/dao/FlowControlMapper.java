package com.dianping.zebra.admin.dao;

import java.util.List;

import com.dianping.zebra.admin.entity.FlowControlEntity;

public interface FlowControlMapper {

	public List<FlowControlEntity> findAllActiveFlowControlByEnv(String env);

	public void insertFlowControl(FlowControlEntity entity);

	public void deleteFlowControl(String sqlId, String env);
}
