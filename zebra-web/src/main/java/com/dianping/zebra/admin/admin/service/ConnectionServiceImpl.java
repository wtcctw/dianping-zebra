package com.dianping.zebra.admin.admin.service;

import com.dianping.cat.Cat;
import com.dianping.zebra.group.config.ConfigService;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.util.StringUtils;

import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class ConnectionServiceImpl implements ConnectionService {

	@Override
	public boolean canConnect(String jdbcRef, final Map<String, String> configs) {
		GroupDataSource ds = null;
		try {
			ds = new GroupDataSource(jdbcRef);
			ds.setConfigService(new ConfigService() {
				@Override public void init() {

				}

				@Override public String getProperty(String key) {
					return configs.get(key);
				}

				@Override public void addPropertyChangeListener(PropertyChangeListener listener) {

				}
			});
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
		Connection conn = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			ds = new GroupDataSource(jdbcRef);
			ds.init();
			conn = ds.getConnection();
			stmt = conn.createStatement();
			result = stmt.executeQuery("select 1");
			result.next();
			result.getInt(1);

			hidePassword(ds.getConfig());
			return ds.getConfig();
		} catch (Exception t) {
			Cat.logError(t);
			hidePassword(ds.getConfig());
			return ds.getConfig();
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (Exception ignore) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception ignore) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ignore) {
				}
			}
			if (ds != null) {
				try {
					ds.close();
				} catch (Exception ignore) {
				}
			}
		}
	}

	public void hidePassword(GroupDataSourceConfig configs) {
		for (Map.Entry<String, DataSourceConfig> config : configs.getDataSourceConfigs().entrySet()) {
			config.getValue().setPassword(config.getValue().getPassword() != null ?
				StringUtils.repeat("*", config.getValue().getPassword().length()) :
				null);
		}
	}

	public static class ConnectionStatus {
		private String config;

		private boolean isConnected;

		public String getConfig() {
			return config;
		}

		public void setConfig(String config) {
			this.config = config;
		}

		public boolean isConnected() {
			return isConnected;
		}

		public void setConnected(boolean isConnected) {
			this.isConnected = isConnected;
		}
	}
}
