package com.dianping.zebra.group.manager;

import com.dianping.zebra.group.config.GroupConfigManager;

public class GroupDataSourceManagerFactory {

	private static GroupDataSourceManager manager;

	public static GroupDataSourceManager getGroupDataSourceManger(GroupConfigManager groupConfigManager) {
		if (manager == null) {
			synchronized (GroupDataSourceManagerFactory.class) {
				if (manager == null) {
					manager = new C3P0GroupDataSourceManager(groupConfigManager);
					manager.init();
				}
			}
		}

		return manager;
	}
}
