package com.dianping.zebra.biz.dao;

import java.util.List;

import com.dianping.zebra.biz.entity.MonitorHistoryEntity;

public interface MonitorHistoryMapper {
	public List<MonitorHistoryEntity> findAllHistory();

	public void insertMonitorHistory(MonitorHistoryEntity entity);
}
