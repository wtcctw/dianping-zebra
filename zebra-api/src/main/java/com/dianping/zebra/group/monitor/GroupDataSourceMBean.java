package com.dianping.zebra.group.monitor;

import java.util.Map;

import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

public interface GroupDataSourceMBean {

	public Map<String, SingleDataSourceMBean> getReaderSingleDataSourceMBean();

	public SingleDataSourceMBean getWriteSingleDataSourceMBean();

	public GroupDataSourceConfig getConfig();
}
