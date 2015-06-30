package com.dianping.zebra.monitor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianping.zebra.biz.dto.InstanceStatusDto;
import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

@Service
public class MySQLMonitorManagerImpl implements MySQLMonitorManager {

	@Autowired
	private MySQLMonitorThreadGroup monitorThreadGroup;

	private Map<String, DataSourceConfig> dataSourceConfigs = new ConcurrentHashMap<String, DataSourceConfig>();

	private Map<String, GroupDataSourceConfig> groupDataSourceConfigs = new ConcurrentHashMap<String, GroupDataSourceConfig>();

	private Map<String, DataSourceConfigManager> configManagers = new ConcurrentHashMap<String, DataSourceConfigManager>();

	private synchronized DataSourceConfigManager findOrCreate(String jdbcRef) {
		if (!configManagers.containsKey(jdbcRef)) {
			DataSourceConfigManager configManager = DataSourceConfigManagerFactory.getConfigManager("remote", jdbcRef);

			configManagers.put(jdbcRef, configManager);

			return configManager;
		} else {
			return configManagers.get(jdbcRef);
		}
	}

	public class DalConfigChangedListener implements PropertyChangeListener {
		private String jdbcRef;

		public DalConfigChangedListener(String jdbcRef) {
			this.jdbcRef = jdbcRef;
		}

		@Override
		public synchronized void propertyChange(PropertyChangeEvent evt) {
			int pos = evt.getPropertyName().indexOf(jdbcRef);

			if (pos > 0) {
				DataSourceConfigManager configManager = findOrCreate(jdbcRef);
				GroupDataSourceConfig newGroupDataSourceConfig = configManager.getGroupDataSourceConfig();
				Map<String, DataSourceConfig> newDataSourceConfigs = newGroupDataSourceConfig.getDataSourceConfigs();

				// 添加或者更新
				for (Map.Entry<String, DataSourceConfig> entryConfig : newDataSourceConfigs.entrySet()) {
					String dsId = entryConfig.getKey();
					DataSourceConfig dsConfig = entryConfig.getValue();

					if (dsConfig.isCanRead()) {
						if (dataSourceConfigs.containsKey(dsId)) {
							DataSourceConfig oldConfig = dataSourceConfigs.get(dsId);
							if (!dsConfig.toString().equals(oldConfig.toString())) {
								dataSourceConfigs.put(dsId, dsConfig);
								monitorThreadGroup.startOrRefreshMonitor(dsConfig);
							}
						} else {
							dataSourceConfigs.put(dsId, dsConfig);
							monitorThreadGroup.startOrRefreshMonitor(dsConfig);
						}
					}
				}

				// 删除
				for (Entry<String, DataSourceConfig> entry : groupDataSourceConfigs.get(jdbcRef).getDataSourceConfigs()
				      .entrySet()) {
					String dsId = entry.getKey();
					if (entry.getValue().isCanRead()) {
						DataSourceConfig newDataSourceConfig = newDataSourceConfigs.get(dsId);

						if (newDataSourceConfig == null || !newDataSourceConfig.isCanRead()) {
							dataSourceConfigs.remove(dsId);
							monitorThreadGroup.removeMonitor(dsId);
						}
					}
				}

				groupDataSourceConfigs.put(jdbcRef, newGroupDataSourceConfig);
			}
		}
	}

	@Override
	public synchronized void addJdbcRef(String jdbcRef) {
		DataSourceConfigManager configManager = findOrCreate(jdbcRef);
		GroupDataSourceConfig groupDataSourceConfig = configManager.getGroupDataSourceConfig();

		for (Map.Entry<String, DataSourceConfig> entryConfig : groupDataSourceConfig.getDataSourceConfigs().entrySet()) {
			String dsId = entryConfig.getKey();
			DataSourceConfig dsConfig = entryConfig.getValue();

			if (dsConfig.isCanRead()) {
				dataSourceConfigs.put(dsId, dsConfig);

				monitorThreadGroup.startOrRefreshMonitor(dsConfig);
			}
		}

		this.groupDataSourceConfigs.put(jdbcRef, groupDataSourceConfig);
		configManager.addListerner(new DalConfigChangedListener(jdbcRef));
	}

	@Override
	public synchronized void removeJdbcRef(String jdbcRef) {
		DataSourceConfigManager configManager = findOrCreate(jdbcRef);
		GroupDataSourceConfig groupDataSourceConfig = configManager.getGroupDataSourceConfig();

		for (Map.Entry<String, DataSourceConfig> entryConfig : groupDataSourceConfig.getDataSourceConfigs().entrySet()) {
			String dsId = entryConfig.getKey();
			DataSourceConfig dsConfig = entryConfig.getValue();

			if (dsConfig.isCanRead()) {
				dataSourceConfigs.remove(dsId);
				monitorThreadGroup.removeMonitor(dsId);
			}
		}

		this.groupDataSourceConfigs.remove(jdbcRef);
		configManager.close();
		configManagers.remove(jdbcRef);
	}

	@Override
	public synchronized Map<String, InstanceStatusDto> listStatus() {
		Map<String, MySQLMonitorThread> monitors = monitorThreadGroup.getMonitors();
		Map<String, InstanceStatusDto> result = new HashMap<String, InstanceStatusDto>();

		for (Entry<String, MySQLMonitorThread> entry : monitors.entrySet()) {
			String dsId = entry.getKey();
			MySQLMonitorThread thread = entry.getValue();
			InstanceStatusDto status = new InstanceStatusDto();

			status.setDsId(dsId);
			status.setLastUpdateTime(thread.getLastUpdatedTime());
			status.setStatus(thread.getCurrentState().name());

			result.put(dsId, status);
		}

		return result;
	}
}
