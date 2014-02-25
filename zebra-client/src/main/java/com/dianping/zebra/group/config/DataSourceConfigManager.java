package com.dianping.zebra.group.config;

import java.beans.PropertyChangeListener;
import java.util.Map;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public interface DataSourceConfigManager {
	
	public void init();
	
	public Map<String, DataSourceConfig> getDataSourceConfigs();
	
	public void addListerner(PropertyChangeListener listener);

	public void markDown(String id);

	public void markUp(String id);

	public Map<String, DataSourceConfig> getAvailableDataSources();

	public Map<String, DataSourceConfig> getUnAvailableDataSources();
}
