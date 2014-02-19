package com.dianping.zebra.group.config;

import java.util.HashMap;
import java.util.Map;

import com.dianping.zebra.group.exception.GroupConfigException;

public class GroupConfigManagerFactory {

	private static Map<String, GroupConfigManager> groupConfigManagers = new HashMap<String, GroupConfigManager>();

	public static GroupConfigManager getConfigManger(String configManagerType, String resourceId) {
		GroupConfigManager groupConfigManager = groupConfigManagers.get(configManagerType);

		if (groupConfigManager == null) {
			synchronized (groupConfigManagers) {
				groupConfigManager = groupConfigManagers.get(configManagerType);

				if (groupConfigManager == null) {
					if (LocalXmlConfigManager.ID.equals(configManagerType)) {
						groupConfigManager = new LocalXmlConfigManager(resourceId);
						groupConfigManager.init();
						groupConfigManagers.put(configManagerType, groupConfigManager);
					} else {
						throw new GroupConfigException(String.format("illegal configManagerType[%s]", configManagerType));
					}
				}

				return groupConfigManager;
			}
		} else {
			return groupConfigManager;
		}
	}
}
