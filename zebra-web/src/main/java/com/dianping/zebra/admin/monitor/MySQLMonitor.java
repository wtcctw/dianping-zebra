package com.dianping.zebra.admin.monitor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

public class MySQLMonitor {

	private MySQLConfig monitorConfig;

	private GroupDataSourceConfig originConfig;

	private DataSourceConfigManager configManager;

	private List<MySQLPingThread> monitorThreads = new ArrayList<MySQLPingThread>();

	private Map<String, DataSourceConfig> dataSourceConfigs = new HashMap<String, DataSourceConfig>();

	public MySQLMonitor(MySQLConfig monitorConfig) {
		this.monitorConfig = monitorConfig;
	}

	private void buildConfig() {
		GroupDataSourceConfig groupDataSourceConfig = configManager.getGroupDataSourceConfig();

		if (!originConfig.toString().equals(groupDataSourceConfig.toString())) {
			Map<String, DataSourceConfig> dataSourceConfigs = new HashMap<String, DataSourceConfig>();

			for (Map.Entry<String, DataSourceConfig> entryConfig : groupDataSourceConfig.getDataSourceConfigs().entrySet()) {
				String key = entryConfig.getKey();
				DataSourceConfig config = entryConfig.getValue();

				if (config.isCanRead()) {
					dataSourceConfigs.put(key, config);
				}
			}

			synchronized (this) {
				this.dataSourceConfigs = dataSourceConfigs;
				this.originConfig = groupDataSourceConfig;
			}
		}
	}

	public void connect() throws ClassNotFoundException {
		this.configManager = DataSourceConfigManagerFactory.getConfigManager("remote", monitorConfig.getJdbcRef());
		this.configManager.addListerner(new DalConfigChangedListener());
		this.originConfig = new GroupDataSourceConfig();

		Class.forName("com.mysql.jdbc.Driver");

		this.buildConfig();
		this.startThreads();
	}

	private void startThreads() {
		stopThreads();

		List<MySQLPingThread> tmpMonitorThreads = new ArrayList<MySQLPingThread>();
		for (DataSourceConfig dsConfig : this.dataSourceConfigs.values()) {
			MySQLPingThread ping = new MySQLPingThread(this.monitorConfig, dsConfig);
			ping.setName("Dal-Monitor-Slave-" + dsConfig.getId());
			ping.setDaemon(true);
			ping.start();

			tmpMonitorThreads.add(ping);
		}

		synchronized (monitorThreads) {
			this.monitorThreads = tmpMonitorThreads;
		}
	}

	private void stopThreads() {
		for (MySQLPingThread pingThread : monitorThreads) {
			pingThread.terminate();
		}
	}

	public class DalConfigChangedListener implements PropertyChangeListener {
		@Override
		public synchronized void propertyChange(PropertyChangeEvent evt) {
			buildConfig();
			startThreads();
		}
	}
}
