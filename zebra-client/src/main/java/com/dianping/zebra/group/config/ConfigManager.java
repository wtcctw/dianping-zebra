package com.dianping.zebra.group.config;

import java.util.Map;

import com.dianping.zebra.group.config.entity.DatasourceConfig;

public interface ConfigManager {
	public Map<String, DatasourceConfig> getDatasourceConfigs();

	public void addListerner(ConfigChangeListener listener);
}
