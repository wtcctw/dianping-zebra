package com.dianping.zebra.group.config1;

import java.util.Map;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

public class BaseGroupConfigChangeEvent {
	private GroupDataSourceConfig groupDatasourceConfig;

	public BaseGroupConfigChangeEvent(GroupDataSourceConfig groupDatasourceConfig) {
		this.groupDatasourceConfig = groupDatasourceConfig;
	}

	public Map<String, DataSourceConfig> getDatasourceConfigs() {
		return this.groupDatasourceConfig.getDataSourceConfigs();
	}

	public GroupDataSourceConfig getGroupDatasourceConfig() {
		return this.groupDatasourceConfig;
	}

	@Override
	public String toString() {
		return "ConfigChangeEvent [groupDatasourceConfig=" + groupDatasourceConfig + "]";
	}
}
