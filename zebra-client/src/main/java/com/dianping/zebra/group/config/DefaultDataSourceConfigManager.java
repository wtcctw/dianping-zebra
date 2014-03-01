package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.config.datasource.transform.DefaultSaxParser;
import com.dianping.zebra.group.exception.GroupConfigException;

public class DefaultDataSourceConfigManager extends AbstractConfigManager implements DataSourceConfigManager {

	private Logger logger = LoggerFactory.getLogger(DefaultDataSourceConfigManager.class);

	private Map<String, DataSourceConfig> dataSourceConfigCache = new HashMap<String, DataSourceConfig>();

	private Map<String, DataSourceConfig> availableDsConfig = new HashMap<String, DataSourceConfig>();

	private Map<String, DataSourceConfig> unAvailableDsConfig = new HashMap<String, DataSourceConfig>();

	private ReadWriteLock lock = new ReentrantReadWriteLock();

	private Lock rLock = lock.readLock();

	private Lock wLock = lock.writeLock();

	public DefaultDataSourceConfigManager(String resourceId, ConfigService configService) {
		super(resourceId, configService);
	}

	@Override
	public void addListerner(PropertyChangeListener listener) {
		listeners.add(listener);
	}

	private void cleanDataSourceConfigs(String id) {
		if (availableDsConfig.containsKey(id)) {
			availableDsConfig.remove(id);
		}
		if (unAvailableDsConfig.containsKey(id)) {
			unAvailableDsConfig.remove(id);
		}
	}

	@Override
	public Map<String, DataSourceConfig> getAvailableDataSources() {
		this.rLock.lock();
		try {
			return this.availableDsConfig;
		} finally {
			this.rLock.unlock();
		}
	}

	@Override
	public Map<String, DataSourceConfig> getDataSourceConfigs() {
		this.rLock.lock();
		try {
			return this.dataSourceConfigCache;
		} finally {
			this.rLock.unlock();
		}
	}

	@Override
	protected String getKey(String key) {
		if (key.equals(this.resourceId)) {
			return key;
		} else {
			return String.format("%s.%s", Constants.DEFAULT_DATASOURCE_PROPERTY, key);
		}
	}

	private String getKey(String key, String dsId) {
		return String.format("%s.%s", dsId, key);
	}

	@Override
	public Map<String, DataSourceConfig> getUnAvailableDataSources() {
		this.rLock.lock();
		try {
			return this.unAvailableDsConfig;
		} finally {
			this.rLock.unlock();
		}
	}

	@Override
	public void init() {
		try {
			this.wLock.lock();
			try {
				this.dataSourceConfigCache = initDataSourceConfig().getDataSourceConfigs();
				this.availableDsConfig.putAll(this.dataSourceConfigCache);
			} finally {
				this.wLock.unlock();
			}
		} catch (Throwable e) {
			throw new GroupConfigException(String.format(
			      "Fail to initialize DefaultDataSourceConfigManager with config file[%s].", this.resourceId), e);
		}
	}

	private GroupDataSourceConfig initDataSourceConfig() throws SAXException, IOException {
		String appConfig = configService.getProperty(this.resourceId);
		GroupDataSourceConfig config = DefaultSaxParser.parse(appConfig);
		Map<String, DataSourceConfig> dataSourceConfigs = config.getDataSourceConfigs();

		for (Entry<String, DataSourceConfig> entry : dataSourceConfigs.entrySet()) {
			String dsId = entry.getKey();
			DataSourceConfig dataSourceConfig = entry.getValue();

			dataSourceConfig.setConnectionCustomizeClassName(getProperty(
			      getKey(Constants.ELEMENT_CONNECTION_CUSTOMIZE_CLASS_NAME, dsId),
			      dataSourceConfig.getConnectionCustomizeClassName()));
			dataSourceConfig.setDriverClass(getProperty(getKey(Constants.ELEMENT_DRIVER_CLASS, dsId),
			      dataSourceConfig.getDriverClass()));
			dataSourceConfig.setId(dsId);
			dataSourceConfig.setInitialPoolSize(getProperty(getKey(Constants.ELEMENT_INITIAL_POOL_SIZE, dsId),
			      dataSourceConfig.getInitialPoolSize()));
			dataSourceConfig.setJdbcUrl(getProperty(getKey(Constants.ELEMENT_JDBC_URL, dsId),
			      dataSourceConfig.getJdbcUrl()));
			dataSourceConfig.setMaxPoolSize(getProperty(getKey(Constants.ELEMENT_MAX_POOL_SIZE, dsId),
			      dataSourceConfig.getMaxPoolSize()));
			dataSourceConfig.setMinPoolSize(getProperty(getKey(Constants.ELEMENT_MIN_POOL_SIZE, dsId),
			      dataSourceConfig.getMinPoolSize()));
			dataSourceConfig.setPassword(getProperty(getKey(Constants.ELEMENT_PASSWORD, dsId),
			      dataSourceConfig.getPassword()));
			dataSourceConfig.setCheckoutTimeout(getProperty(getKey(Constants.ELEMENT_CHECKOUT_TIMEOUT, dsId),
			      dataSourceConfig.getCheckoutTimeout()));
			dataSourceConfig.setUser(getProperty(getKey(Constants.ELEMENT_USER, dsId), dataSourceConfig.getUser()));
		}

		isValidConfig(dataSourceConfigs);

		return config;
	}

	private void isValidConfig(Map<String, DataSourceConfig> dataSourceConfigs) {
		int readNum = 0, writeNum = 0;
		for (Entry<String, DataSourceConfig> entry : dataSourceConfigs.entrySet()) {
			if (entry.getValue().isReadonly()) {
				readNum += 1;
			} else {
				writeNum += 1;
			}
		}

		if (readNum < 1 || writeNum != 1) {
			throw new GroupConfigException("Not enough read or write dataSources.");
		}
	}

	@Override
	public void markDown(String id) {
		this.wLock.lock();
		try {
			Map<String, DataSourceConfig> map = this.dataSourceConfigCache;

			if (map.containsKey(id)) {
				if (this.availableDsConfig.containsKey(id)) {
					DataSourceConfig datasourceConfig = this.availableDsConfig.remove(id);
					this.unAvailableDsConfig.put(id, datasourceConfig);
				} else {
					this.unAvailableDsConfig.put(id, map.get(id));
				}
			} else {
				cleanDataSourceConfigs(id);
			}
		} finally {
			this.wLock.unlock();
		}
	}

	@Override
	public void markUp(String id) {
		this.wLock.lock();
		try {
			Map<String, DataSourceConfig> map = this.dataSourceConfigCache;

			if (map.containsKey(id)) {
				if (this.unAvailableDsConfig.containsKey(id)) {
					DataSourceConfig datasourceConfig = this.unAvailableDsConfig.remove(id);

					this.availableDsConfig.put(id, datasourceConfig);
				} else {
					this.availableDsConfig.put(id, map.get(id));
				}
			} else {
				cleanDataSourceConfigs(id);
			}
		} finally {
			this.wLock.unlock();
		}
	}

	@Override
	protected void updateProperties(PropertyChangeEvent evt) {
		if (evt instanceof AdvancedPropertyChangeEvent) {
			try {
				Map<String, DataSourceConfig> config = initDataSourceConfig().getDataSourceConfigs();

				isValidConfig(config);

				wLock.lock();
				try {
					// update config cache in memory
					for (Entry<String, DataSourceConfig> entry : config.entrySet()) {
						String key = entry.getKey();
						DataSourceConfig dsConfig = entry.getValue();

						this.dataSourceConfigCache.put(key, dsConfig);
						this.availableDsConfig.put(key, dsConfig);

						if (this.unAvailableDsConfig.containsKey(key)) {
							this.unAvailableDsConfig.put(key, dsConfig);
						}
					}

					for (String key : this.dataSourceConfigCache.keySet()) {
						if (!config.containsKey(key)) {
							this.dataSourceConfigCache.remove(key);
						}
					}

					for (String key : this.availableDsConfig.keySet()) {
						if (!config.containsKey(key)) {
							this.availableDsConfig.remove(key);
						}
					}

					for (String key : this.unAvailableDsConfig.keySet()) {
						if (!config.containsKey(key)) {
							this.unAvailableDsConfig.remove(key);
						}
					}
				} finally {
					wLock.unlock();
				}
			} catch (Throwable e) {
				logger.warn("fail to update groupDataSourceConfig.", e);
			}
		}
	}
}
