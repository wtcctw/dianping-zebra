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
		} catch (Throwable t) {
			Cat.logError(t);
			return false;
		} finally {
			if (ds != null) {
				try {
					ds.close();
				} catch (Throwable ignore) {
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
		} catch (Throwable t) {
			Cat.logError(t);
			return null;
		} finally {
			if (ds != null) {
				try {
					ds.close();
				} catch (Throwable ignore) {
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
//			String tmp = config;
//			tmp.replaceAll("<", "");
//			tmp.replaceAll("/>", "&gt;");
			
			
			this.config = config;
		}
	}
}
