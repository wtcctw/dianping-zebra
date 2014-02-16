package com.dianping.zebra.group.config;

import java.util.Map;

import com.dianping.zebra.group.config.entity.DatasourceConfig;

public class ConfigChangeEvent {
	private Map<String, DatasourceConfig> datasourceConfigs;

	public ConfigChangeEvent(Map<String, DatasourceConfig> datasourceConfigs) {
		this.datasourceConfigs = datasourceConfigs;
	}

	public Map<String, DatasourceConfig> getDatasourceConfigs() {
		return datasourceConfigs;
	}

	@Override
	public String toString() {
		return "ConfigChangeEvent [datasourceConfigs=" + datasourceConfigs + "]";
	}
}
