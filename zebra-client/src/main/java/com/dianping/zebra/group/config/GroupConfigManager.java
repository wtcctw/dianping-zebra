package com.dianping.zebra.group.config;

import java.util.Map;

import com.dianping.zebra.group.config.entity.DatasourceConfig;
import com.dianping.zebra.group.config.entity.GroupDatasourceConfig;

public interface GroupConfigManager {
	
	public void init();
	
	public Map<String, DatasourceConfig> getDatasourceConfigs();
	
	public GroupDatasourceConfig getGroupDatasourceConfig();

	public void addListerner(GroupConfigChangeListener listener);

	public void markDown(String id);

	public void markUp(String id);

	public Map<String, DatasourceConfig> getAvailableDatasources();

	public Map<String, DatasourceConfig> getUnAvailableDatasources();
}
