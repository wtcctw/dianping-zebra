package com.dianping.zebra.group.manager;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.config.GroupConfigManager;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public class DefaultGroupDataSourceManager implements GroupDataSourceManager {

	private Logger logger = LoggerFactory.getLogger(DefaultGroupDataSourceManager.class);

	private Map<String, Connection> connections = new HashMap<String, Connection>();

	private GroupConfigManager groupConfigManager;

	public DefaultGroupDataSourceManager(GroupConfigManager groupConfigManager) {
		this.groupConfigManager = groupConfigManager;
	}

	@Override
	public void init() {
		for (Entry<String, DataSourceConfig> entry : groupConfigManager.getAvailableDataSources().entrySet()) {
			String key = entry.getKey();
			try {
				createNewConnection(key, entry.getValue());
			} catch (RuntimeException e) {
				logger.error(String.format("fail to connect the database[%s]", key), e);
				throw e;
			}
		}
	}

	private void createNewConnection(String key, DataSourceConfig value) {

	}

	@Override
	public boolean isAvailable(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub

	}

}
