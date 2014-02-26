package com.dianping.zebra.group.healthcheck;

import java.util.concurrent.atomic.AtomicReference;

import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.SystemConfigManager;

public class HealthCheckFactory {

	private static AtomicReference<HealthCheck> mysqlHealthCheck = new AtomicReference<HealthCheck>();

	public static HealthCheck getCheck(String type, DataSourceConfigManager configManager, SystemConfigManager systemConfigManager) {
		if ("mysql".equalsIgnoreCase(type)) {
			HealthCheck healthCheck = mysqlHealthCheck.get();
			if (healthCheck == null) {
				synchronized (mysqlHealthCheck) {
					healthCheck = mysqlHealthCheck.get();
					if (healthCheck == null) {
						healthCheck = new MysqlHealthCheckImpl(configManager, systemConfigManager);
						mysqlHealthCheck.set(healthCheck);
					}
				}
			}
			return mysqlHealthCheck.get();
		}
		return null;
	}
}
