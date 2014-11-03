package com.dianping.zebra.admin.admin.service;

import com.dianping.cat.Cat;
import com.dianping.zebra.group.config.ConfigService;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.util.StringUtils;

import java.beans.PropertyChangeListener;
import java.util.Map;

public class ConnectionServiceImpl implements ConnectionService {
	@Override
	public ConnectionResult getConnectionResult(String jdbcRef, final Map<String, String> configs) {
		ConnectionResult result = new ConnectionResult();

		GroupDataSource ds = null;
		try {
			ds = new GroupDataSource(jdbcRef);
			ds.setFilter("stat,cat");
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

			result.setCanConnect(true);
		} catch (Exception t) {
			Cat.logError(t);
			result.setCanConnect(false);
		} finally {
			if (ds != null) {
				try {
					ds.close();
				} catch (Exception ignore) {
				}
			}
		}

		hidePassword(ds.getConfig());
		result.setConfig(ds.getConfig());

		return result;
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
