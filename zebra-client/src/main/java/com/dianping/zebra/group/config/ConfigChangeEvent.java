package com.dianping.zebra.group.config;

import java.util.Map;

public class ConfigChangeEvent {
	private Map<String, DatasourceConfig> datasourceConfigs;

	public ConfigChangeEvent(Map<String, DatasourceConfig> datasourceConfigs) {
		this.datasourceConfigs = datasourceConfigs;
	}

	public Map<String, DatasourceConfig> getDatasourceConfigs() {
		return datasourceConfigs;
	}

}
