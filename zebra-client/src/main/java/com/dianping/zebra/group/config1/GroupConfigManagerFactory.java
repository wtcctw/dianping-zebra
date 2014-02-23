package com.dianping.zebra.group.config1;

import java.util.HashMap;
import java.util.Map;

import com.dianping.zebra.group.exception.GroupConfigException;

public class GroupConfigManagerFactory {

	private static Map<String, GroupConfigManager> groupConfigManagers = new HashMap<String, GroupConfigManager>();

	public static GroupConfigManager getConfigManger(String configManagerType, String resourceId) {
		GroupConfigManager groupConfigManager = groupConfigManagers.get(resourceId);

		if (groupConfigManager == null) {
			synchronized (groupConfigManagers) {
				groupConfigManager = groupConfigManagers.get(resourceId);

				if (groupConfigManager == null) {
					if ("local".equals(configManagerType)) {
						groupConfigManager = new LocalGroupConfigManager(resourceId);
					} else if ("remote".equals(configManagerType)) {
						groupConfigManager = new RemoteGroupConfigManager(resourceId);
					} else {
						throw new GroupConfigException(String.format("illegal groupConfigManagerType[%s]", configManagerType));
					}

					groupConfigManager.init();
					groupConfigManagers.put(resourceId, groupConfigManager);
				}
			}
		}

		return groupConfigManager;
	}
}
