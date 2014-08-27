package com.dianping.zebra.admin.admin.service;

import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

public interface ConnectionService {

	public boolean canConnect(String jdbcRef);
	
	public GroupDataSourceConfig getConfig(String jdbcRef);
}
