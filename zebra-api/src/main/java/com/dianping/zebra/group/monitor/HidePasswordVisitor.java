package com.dianping.zebra.group.monitor;

import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.config.datasource.transform.BaseVisitor;

public class HidePasswordVisitor extends BaseVisitor {

	private GroupDataSourceConfig config;

	private DataSourceConfig newConfig;

	public HidePasswordVisitor(GroupDataSourceConfig config) {
		this.config = config;
	}

	@Override
	public void visitDataSourceConfig(DataSourceConfig dataSourceConfig) {
		newConfig = config.findOrCreateDataSourceConfig(dataSourceConfig.getId());
		newConfig.mergeAttributes(dataSourceConfig);
		newConfig.setJdbcUrl(dataSourceConfig.getJdbcUrl());
		newConfig.setUsername(dataSourceConfig.getUsername());
		newConfig.setPassword("******");
		newConfig.setDriverClass(dataSourceConfig.getDriverClass());
		newConfig.setTestReadOnlySql(dataSourceConfig.getTestReadOnlySql());
		newConfig.setTimeWindow(dataSourceConfig.getTimeWindow());
		newConfig.setPunishLimit(dataSourceConfig.getPunishLimit());
		newConfig.setWarmupTime(dataSourceConfig.getWarmupTime());

		for (Any any : dataSourceConfig.getProperties()) {
			newConfig.getProperties().add(any);
		}
	}

	@Override
	public void visitGroupDataSourceConfig(GroupDataSourceConfig groupDataSourceConfig) {
		config.mergeAttributes(groupDataSourceConfig);

		for (DataSourceConfig dataSourceConfig : groupDataSourceConfig.getDataSourceConfigs().values()) {
			visitDataSourceConfig(dataSourceConfig);
		}
	}
}