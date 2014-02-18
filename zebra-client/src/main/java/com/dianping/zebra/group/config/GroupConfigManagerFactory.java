package com.dianping.zebra.group.config;

import java.util.HashMap;
import java.util.Map;

public class GroupConfigManagerFactory {

	private static Map<String, GroupConfigManager> groupConfigManagers = new HashMap<String, GroupConfigManager>();

	public static GroupConfigManager getConfigManger(String configManagerType, String resourceId) {
		GroupConfigManager groupConfigManager = groupConfigManagers.get(configManagerType);

		if (groupConfigManager == null) {
			synchronized (groupConfigManagers) {
				groupConfigManager = groupConfigManagers.get(configManagerType);

				if (groupConfigManager == null) {
					groupConfigManager = new LocalXmlConfigManager(resourceId);
					groupConfigManager.init();
					groupConfigManagers.put(configManagerType, groupConfigManager);
				}

				return groupConfigManager;
			}
		} else {
			return groupConfigManager;
		}
	}
}
