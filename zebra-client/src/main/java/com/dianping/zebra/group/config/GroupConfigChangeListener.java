package com.dianping.zebra.group.config;

import java.util.Map;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public interface GroupConfigChangeListener {

	public void onChange(BaseGroupConfigChangeEvent event);
	
	public void onActiveDataSourceChange(Map<String,DataSourceConfig> configs);

	public String getName();
}
