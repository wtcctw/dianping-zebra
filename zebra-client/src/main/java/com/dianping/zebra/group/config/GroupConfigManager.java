package com.dianping.zebra.group.config;

import java.util.Map;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

public interface GroupConfigManager {
	
	public void init();
	
	public Map<String, DataSourceConfig> getDatasourceConfigs();
	
	public GroupDataSourceConfig getGroupDatasourceConfig();

	public void addListerner(GroupConfigChangeListener listener);

	public void markDown(String id);

	public void markUp(String id);

	public Map<String, DataSourceConfig> getAvailableDatasources();

	public Map<String, DataSourceConfig> getUnAvailableDatasources();
}
