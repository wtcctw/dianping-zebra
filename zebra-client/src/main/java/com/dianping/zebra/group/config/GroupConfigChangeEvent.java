package com.dianping.zebra.group.config;

import java.util.Map;

import com.dianping.zebra.group.config.entity.DatasourceConfig;
import com.dianping.zebra.group.config.entity.GroupDatasourceConfig;

public class GroupConfigChangeEvent {
	private GroupDatasourceConfig groupDatasourceConfig;

	public GroupConfigChangeEvent(GroupDatasourceConfig groupDatasourceConfig) {
		this.groupDatasourceConfig = groupDatasourceConfig;
	}

	public Map<String, DatasourceConfig> getDatasourceConfigs() {
		return this.groupDatasourceConfig.getDatasourceConfigs();
	}

	public GroupDatasourceConfig getGroupDatasourceConfig() {
		return this.groupDatasourceConfig;
	}

	@Override
	public String toString() {
		return "ConfigChangeEvent [groupDatasourceConfig=" + groupDatasourceConfig + "]";
	}
}
