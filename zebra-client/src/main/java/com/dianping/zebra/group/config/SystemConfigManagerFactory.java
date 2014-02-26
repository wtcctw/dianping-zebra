package com.dianping.zebra.group.config;

import java.util.HashMap;
import java.util.Map;

import com.dianping.zebra.group.exception.GroupConfigException;

public class SystemConfigManagerFactory {

	private static Map<String, SystemConfigManager> systemConfigManagers = new HashMap<String, SystemConfigManager>();

	public static SystemConfigManager getConfigManger(String configManagerType, String resourceId) {
		SystemConfigManager systemConfigManager = systemConfigManagers.get(resourceId);

		if (systemConfigManager == null) {
			synchronized (systemConfigManagers) {
				systemConfigManager = systemConfigManagers.get(resourceId);

				if (systemConfigManager == null) {
					if ("local".equalsIgnoreCase(configManagerType)) {
						systemConfigManager = new DefaultSystemConfigManager(resourceId, new LocalConfigService(resourceId));
					} else if ("remote".equalsIgnoreCase(configManagerType)) {
						systemConfigManager = new DefaultSystemConfigManager(resourceId, new RemoteConfigService(resourceId));
					} else {
						throw new GroupConfigException(
						      String.format("illegal systemConfigManagerType[%s]", configManagerType));
					}
					systemConfigManager.init();
					systemConfigManagers.put(resourceId, systemConfigManager);
				}

			}
		}

		return systemConfigManager;
	}
}
