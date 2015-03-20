package com.dianping.zebra.admin.monitor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
	private MySQLMonitorThreadGroup monitorThreadGroup;

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
							monitorThreadGroup.startOrRefreshMonitor(dsConfig);
						} else {
							DataSourceConfig oldConfig = dataSourceConfigs.get(dsId);

							if (!dsConfig.toString().equals(oldConfig.toString())) {
								dataSourceConfigs.put(dsId, dsConfig);
								monitorThreadGroup.startOrRefreshMonitor(dsConfig);
							}
						}
					}
				}

				for (String dsId : groupDataSourceConfigs.get(jdbcRef).getDataSourceConfigs().keySet()) {
					if (!dataSourceConfigs.containsKey(dsId)) {
						dataSourceConfigs.remove(dsId);
						monitorThreadGroup.removeMonitor(dsId);
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
				monitorThreadGroup.startOrRefreshMonitor(dsConfig);
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
				monitorThreadGroup.removeMonitor(dsId);
			}
		}
	}

	@Override
   public Map<String, InstanceStatus> listStatus() {
		Map<String, MySQLMonitorThread> monitors = monitorThreadGroup.getMonitors();
		Map<String, InstanceStatus> result = new HashMap<String, InstanceStatus>();
		
		for(Entry<String,MySQLMonitorThread> entry : monitors.entrySet()){
			String dsId = entry.getKey();
			MySQLMonitorThread thread = entry.getValue();
			InstanceStatus status = new InstanceStatus();
			
			status.setDsId(dsId);
			status.setLastUpdateTime(thread.getLastUpdatedTime());
			status.setStatus(thread.getCurrentState().name());
			
			result.put(dsId, status);
		}
		
		return result;
   }
}
