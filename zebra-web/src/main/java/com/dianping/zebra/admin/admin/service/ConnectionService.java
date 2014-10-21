package com.dianping.zebra.admin.admin.service;

import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

import java.util.Map;

public interface ConnectionService {

	public boolean canConnect(String jdbcRef,Map<String,String> configs);
	
	public GroupDataSourceConfig getConfig(String jdbcRef);
}
