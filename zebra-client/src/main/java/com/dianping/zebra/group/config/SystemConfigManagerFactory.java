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
						LocalConfigService configService = new LocalConfigService(resourceId);
						configService.init();
						systemConfigManager = new DefaultSystemConfigManager(resourceId, configService);
					} else if ("remote".equalsIgnoreCase(configManagerType)) {
						RemoteConfigService configService = new RemoteConfigService(resourceId);
						configService.init();
						systemConfigManager = new DefaultSystemConfigManager(resourceId, configService);
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
