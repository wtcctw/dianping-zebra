package com.dianping.zebra.admin.mock;

import com.dianping.zebra.config.ConfigService;
import com.dianping.zebra.group.config.DefaultDataSourceConfigManager;
import com.dianping.zebra.group.config.SystemConfigManagerFactory;
import com.dianping.zebra.group.jdbc.GroupDataSource;

/**
 * Created by Dozer on 11/13/14.
 */
public class GroupDataSourceExtend extends GroupDataSource {

	@Override
	protected void initConfig() {
		this.groupConfig = buildGroupConfig();
		this.systemConfigManager = SystemConfigManagerFactory.getConfigManger(configType);
	}

	public void setDataSourceConfigManager(ConfigService configService) {
		this.dataSourceConfigManager = new DefaultDataSourceConfigManager(jdbcRef, configService);
		this.dataSourceConfigManager.init();
	}
}
