package com.dianping.zebra.admin.admin.service;

import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

public interface ConnectionService {

	public ConnectionResult getConnectionResult(String jdbcRef, DalConfigService.GroupConfigModel modal);

	public static class ConnectionResult {
		private boolean canConnect;

		private GroupDataSourceConfig config;

		private String exception;

		public GroupDataSourceConfig getConfig() {
			return config;
		}

		public void setConfig(GroupDataSourceConfig config) {
			this.config = config;
		}

		public String getException() {
			return exception;
		}

		public void setException(String exception) {
			this.exception = exception;
		}

		public boolean isCanConnect() {
			return canConnect;
		}

		public void setCanConnect(boolean canConnect) {
			this.canConnect = canConnect;
		}
	}
}
