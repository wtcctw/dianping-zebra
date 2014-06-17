package com.dianping.zebra.group.config;

import java.beans.PropertyChangeListener;
import java.util.Map;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

public interface DataSourceConfigManager {
	
	public void init();
	
	public GroupDataSourceConfig getGroupDataSourceConfig();
	
	public Map<String, DataSourceConfig> getDataSourceConfigs();
	
	public String getRouterStrategy();
	
	public boolean isTransactionForceWrite();
	
	public void addListerner(PropertyChangeListener listener);
}
