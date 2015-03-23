package com.dianping.zebra.admin.dao;

import java.util.List;

import com.dianping.zebra.admin.entity.MonitorHistoryEntity;

public interface MonitorHistoryMapper {
	public List<MonitorHistoryEntity> findAllHistory();

	public void insertMonitorHistory(MonitorHistoryEntity entity);
}
