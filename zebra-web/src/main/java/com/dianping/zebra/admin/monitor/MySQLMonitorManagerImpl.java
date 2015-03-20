package com.dianping.zebra.admin.monitor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

@Service
public class MySQLMonitorManagerImpl implements MySQLMonitorManager {

	@Autowired
	private MySQLMonitorThreadGroup monitorManager;

	private Map<String, DataSourceConfig> dataSourceConfigs = new ConcurrentHashMap<String, DataSourceConfig>();

	private Map<String, GroupDataSourceConfig> groupDataSourceConfigs = new ConcurrentHashMap<String, GroupDataSourceConfig>();

	public class DalConfigChangedListener implements PropertyChangeListener {
		private String jdbcRef;

		public DalConfigChangedListener(String jdbcRef) {
			this.jdbcRef = jdbcRef;
		}

		@Override
		public synchronized void propertyChange(PropertyChangeEvent evt) {
			int pos = evt.getPropertyName().indexOf(jdbcRef);

			if (pos > 0) {
				DataSourceConfigManager configManager = DataSourceConfigManagerFactory.getConfigManager("remote", jdbcRef);
				GroupDataSourceConfig newGroupDataSourceConfig = configManager.getGroupDataSourceConfig();
				Map<String, DataSourceConfig> newDataSourceConfigs = newGroupDataSourceConfig.getDataSourceConfigs();

				for (Map.Entry<String, DataSourceConfig> entryConfig : newDataSourceConfigs.entrySet()) {
					String dsId = entryConfig.getKey();
					DataSourceConfig dsConfig = entryConfig.getValue();

					if (dsConfig.isCanRead()) {
						if (!dataSourceConfigs.containsKey(dsId)) {
							dataSourceConfigs.put(dsId, dsConfig);
							monitorManager.startOrRefreshMonitor(dsConfig);
						} else {
							DataSourceConfig oldConfig = dataSourceConfigs.get(dsId);

							if (!dsConfig.toString().equals(oldConfig.toString())) {
								dataSourceConfigs.put(dsId, dsConfig);
								monitorManager.startOrRefreshMonitor(dsConfig);
							}
						}
					}
				}

				for (String dsId : groupDataSourceConfigs.get(jdbcRef).getDataSourceConfigs().keySet()) {
					if (!dataSourceConfigs.containsKey(dsId)) {
						dataSourceConfigs.remove(dsId);
						monitorManager.removeMonitor(dsId);
					}
				}
			}
		}
	}

	@Override
	public synchronized void addJdbcRef(String jdbcRef) {
		DataSourceConfigManager configManager = DataSourceConfigManagerFactory.getConfigManager("remote", jdbcRef);
		configManager.addListerner(new DalConfigChangedListener(jdbcRef));
		GroupDataSourceConfig groupDataSourceConfig = configManager.getGroupDataSourceConfig();

		this.groupDataSourceConfigs.put(jdbcRef, groupDataSourceConfig);

		for (Map.Entry<String, DataSourceConfig> entryConfig : groupDataSourceConfig.getDataSourceConfigs().entrySet()) {
			String key = entryConfig.getKey();
			DataSourceConfig dsConfig = entryConfig.getValue();

			if (dsConfig.isCanRead()) {
				dataSourceConfigs.put(key, dsConfig);
				monitorManager.startOrRefreshMonitor(dsConfig);
			}
		}
	}

	@Override
	public synchronized void removeJdbcRef(String jdbcRef) {
		DataSourceConfigManager configManager = DataSourceConfigManagerFactory.getConfigManager("remote", jdbcRef);
		GroupDataSourceConfig groupDataSourceConfig = configManager.getGroupDataSourceConfig();

		for (Map.Entry<String, DataSourceConfig> entryConfig : groupDataSourceConfig.getDataSourceConfigs().entrySet()) {
			String dsId = entryConfig.getKey();
			DataSourceConfig dsConfig = entryConfig.getValue();

			if (dsConfig.isCanRead()) {
				dataSourceConfigs.remove(dsId);
				monitorManager.removeMonitor(dsId);
			}
		}
	}
}
