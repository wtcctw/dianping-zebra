package com.dianping.zebra.group.router;

import com.dianping.zebra.group.config.GroupConfigManager;

public class GroupDataSourceRouterFactory {

	public static GroupDataSourceRouter getDataSourceRouter(GroupConfigManager configManager) {

		GroupReadWriteDataSourceRouter readWriteDataSourcerouter = new GroupReadWriteDataSourceRouter(
		      configManager.getAvailableDataSources());

		configManager.addListerner(readWriteDataSourcerouter);

		return readWriteDataSourcerouter;
	}
}
