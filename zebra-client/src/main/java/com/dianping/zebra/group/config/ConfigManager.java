package com.dianping.zebra.group.config;

import java.util.Map;

import com.dianping.zebra.group.config.entity.DatasourceConfig;

public interface ConfigManager {
	public Map<String, DatasourceConfig> getDatasourceConfigs();

	public void addListerner(ConfigChangeListener listener);

	public void markDown(String id);

	public void markUp(String id);

	public Map<String, DatasourceConfig> getAvailableDatasources();

	public Map<String, DatasourceConfig> getUnAvailableDatasources();
}
