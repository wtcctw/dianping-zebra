package com.dianping.zebra.group.config;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.exception.IllegalConfigException;

public final class SystemConfigManagerFactory {

	/*
	 * SystemConfigManagerFactory has only one instance of SystemConfigManager, which differ from DataSourceConfigManagerFactory who
	 * has its own DataSourceConfigManager for each GroupDataSource
	 */
	private static SystemConfigManager systemConfigManager;

	private SystemConfigManagerFactory() {
	}

	public static SystemConfigManager getConfigManger(String configManagerType) {
		if (systemConfigManager == null) {
			synchronized (SystemConfigManagerFactory.class) {
				if (systemConfigManager == null) {
					if (Constants.CONFIG_MANAGER_TYPE_LOCAL.equalsIgnoreCase(configManagerType)) {
						LocalConfigService configService = new LocalConfigService();
						configService.init();
						systemConfigManager = new DefaultSystemConfigManager(configService);
					} else if (Constants.CONFIG_MANAGER_TYPE_REMOTE.equalsIgnoreCase(configManagerType)) {
						LionConfigService configService = new LionConfigService();
						configService.init();
						systemConfigManager = new DefaultSystemConfigManager(configService);
					} else {
						throw new IllegalConfigException(String.format("illegal systemConfigManagerType[%s]",
						      configManagerType));
					}
					systemConfigManager.init();
				}

			}
		}

		return systemConfigManager;
	}
}
