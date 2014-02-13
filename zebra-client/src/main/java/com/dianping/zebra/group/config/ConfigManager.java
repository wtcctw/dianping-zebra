package com.dianping.zebra.group.config;

import java.util.Map;

public interface ConfigManager {
	public Map<String, DatasourceConfig> getDatasourceConfigs();

	public void addListerner(ConfigChangeListener listener);
}
