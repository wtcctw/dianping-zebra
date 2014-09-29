package com.dianping.zebra.admin.admin.service;

import com.dianping.cat.Cat;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.jdbc.GroupDataSource;

public class ConnectionServiceImpl implements ConnectionService {

	@Override
	public boolean canConnect(String jdbcRef) {
		GroupDataSource ds = null;
		try {
			ds = new GroupDataSource(jdbcRef);
			ds.init();

			return true;
		} catch (Exception t) {
			Cat.logError(t);
			return false;
		} finally {
			if (ds != null) {
				try {
					ds.close();
				} catch (Exception ignore) {
				}
			}
		}
	}

	@Override
	public GroupDataSourceConfig getConfig(String jdbcRef) {
		GroupDataSource ds = null;
		try {
			ds = new GroupDataSource(jdbcRef);
			ds.init();

			return ds.getConfig();
		} catch (Exception t) {
			Cat.logError(t);
			return ds.getConfig();
		} finally {
			if (ds != null) {
				try {
					ds.close();
				} catch (Exception ignore) {
				}
			}
		}
	}

	public static class ConnectionStatus {
		private boolean isConnected;

		private String config;

		public boolean isConnected() {
			return isConnected;
		}

		public void setConnected(boolean isConnected) {
			this.isConnected = isConnected;
		}

		public String getConfig() {
			return config;
		}

		public void setConfig(String config) {
			this.config = config;
		}
	}
}
