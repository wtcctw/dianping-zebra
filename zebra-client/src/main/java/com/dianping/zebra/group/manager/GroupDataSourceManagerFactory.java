package com.dianping.zebra.group.manager;

import com.dianping.zebra.group.config.DataSourceConfigManager;

public class GroupDataSourceManagerFactory {

	public static GroupDataSourceManager getGroupDataSourceManger(DataSourceConfigManager groupConfigManager,
	      String connectionPoolType) {
		C3P0GroupDataSourceManager manager = new C3P0GroupDataSourceManager(groupConfigManager);
		manager.init();
		return manager;
	}
}
