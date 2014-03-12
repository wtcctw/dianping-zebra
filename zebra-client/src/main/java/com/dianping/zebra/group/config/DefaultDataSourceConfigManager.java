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
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.exception.GroupConfigException;
import com.dianping.zebra.group.util.Splitters;

public class DefaultDataSourceConfigManager extends AbstractConfigManager implements DataSourceConfigManager {

	private Logger logger = LoggerFactory.getLogger(DefaultDataSourceConfigManager.class);

	private GroupDataSourceConfig groupDataSourceConfigCache = new GroupDataSourceConfig();

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

	private void clearDataSourceConfigs(String id) {
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

	private String getCustomizedKey(String key, String dsId) {
		return String.format("%s.%s.%s", this.resourceId, dsId, key);
	}

	@Override
	public Map<String, DataSourceConfig> getDataSourceConfigs() {
		this.rLock.lock();
		try {
			return this.groupDataSourceConfigCache.getDataSourceConfigs();
		} finally {
			this.rLock.unlock();
		}
	}

	private String getKey(String key, String dsId) {
		if (key.equals(this.resourceId)) {
			return key;
		} else {
			return String.format("%s.%s.%s", Constants.DEFAULT_DATASOURCE_RESOURCE_ID_PRFIX, dsId, key);
		}
	}

	private int getMergedPropertyValue(String key, String dsId, int defaultValue) {
		return getProperty(getCustomizedKey(key, dsId), getProperty(getKey(key, dsId), defaultValue));
	}

	private String getMergedPropertyValue(String key, String dsId, String defaultValue) {
		return getProperty(getCustomizedKey(key, dsId), getProperty(getKey(key, dsId), defaultValue));
	}

	@Override
	public String getRouterStrategy() {
		rLock.lock();
		try {
			return this.groupDataSourceConfigCache.getRouterStrategy();
		} finally {
			rLock.unlock();
		}
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

	private int getWeight(String dsValue) {
		if (dsValue.length() > 1) {
			try {
				return Integer.parseInt(dsValue.substring(1));
			} catch (Exception e) {
			}
		}

		return 1;
	}

	@Override
	public void init() {
		try {
			this.wLock.lock();
			try {
				this.groupDataSourceConfigCache = initDataSourceConfig();
				this.availableDsConfig.putAll(this.groupDataSourceConfigCache.getDataSourceConfigs());
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
		Map<String, String> splits = Splitters.by(',', ':').trim().split(appConfig);
		GroupDataSourceConfig groupDsConfig = new GroupDataSourceConfig();

		for (Entry<String, String> split : splits.entrySet()) {
			DataSourceConfig ds = groupDsConfig.findOrCreateDataSourceConfig(split.getKey());

			String dsId = split.getKey();
			String dsValue = split.getValue().trim();
			if (dsValue.indexOf('w') == 0) {
				ds.setReadonly(false);
			} else {
				ds.setReadonly(true);
				ds.setWeight(getWeight(dsValue));
			}

			ds.setConnectionCustomizeClassName(getMergedPropertyValue(Constants.ELEMENT_CONNECTION_CUSTOMIZE_CLASS_NAME,
			      dsId, ds.getConnectionCustomizeClassName()));
			ds.setDriverClass(getMergedPropertyValue(Constants.ELEMENT_DRIVER_CLASS, dsId, ds.getDriverClass()));
			ds.setId(dsId);
			ds.setInitialPoolSize(getMergedPropertyValue(Constants.ELEMENT_INITIAL_POOL_SIZE, dsId,
			      ds.getInitialPoolSize()));
			ds.setJdbcUrl(getMergedPropertyValue(Constants.ELEMENT_JDBC_URL, dsId, ds.getJdbcUrl()));
			ds.setMaxPoolSize(getMergedPropertyValue(Constants.ELEMENT_MAX_POOL_SIZE, dsId, ds.getMaxPoolSize()));
			ds.setMinPoolSize(getMergedPropertyValue(Constants.ELEMENT_MIN_POOL_SIZE, dsId, ds.getMinPoolSize()));
			ds.setPassword(getMergedPropertyValue(Constants.ELEMENT_PASSWORD, dsId, ds.getPassword()));
			ds.setCheckoutTimeout(getMergedPropertyValue(Constants.ELEMENT_CHECKOUT_TIMEOUT, dsId, ds.getCheckoutTimeout()));
			ds.setUser(getMergedPropertyValue(Constants.ELEMENT_USER, dsId, ds.getUser()));

			processProperties(ds, dsId);
		}

		groupDsConfig.setRouterStrategy(getProperty(this.resourceId + "." + Constants.ELEMENT_ROUTER_STRATEGY,
		      groupDsConfig.getRouterStrategy()));

		groupDsConfig.setTransactionForceWrite(getProperty(this.resourceId + "."
		      + Constants.ELEMENT_TRANSACTION_FORCE_WREITE, groupDsConfig.getTransactionForceWrite()));

		validateConfig(groupDsConfig.getDataSourceConfigs());

		return groupDsConfig;
	}

	@Override
	public boolean isTransactionForceWrite() {
		rLock.lock();
		try {
			return this.groupDataSourceConfigCache.getTransactionForceWrite();
		} finally {
			rLock.unlock();
		}
	}

	@Override
	public void markDown(String id) {
		this.wLock.lock();
		try {
			Map<String, DataSourceConfig> map = this.groupDataSourceConfigCache.getDataSourceConfigs();

			if (map.containsKey(id)) {
				if (this.availableDsConfig.containsKey(id)) {
					DataSourceConfig datasourceConfig = this.availableDsConfig.remove(id);
					this.unAvailableDsConfig.put(id, datasourceConfig);
				} else {
					this.unAvailableDsConfig.put(id, map.get(id));
				}
			} else {
				clearDataSourceConfigs(id);
			}
		} finally {
			this.wLock.unlock();
		}
	}

	@Override
	public void markUp(String id) {
		this.wLock.lock();
		try {
			Map<String, DataSourceConfig> map = this.groupDataSourceConfigCache.getDataSourceConfigs();

			if (map.containsKey(id)) {
				if (this.unAvailableDsConfig.containsKey(id)) {
					DataSourceConfig datasourceConfig = this.unAvailableDsConfig.remove(id);

					this.availableDsConfig.put(id, datasourceConfig);
				} else {
					this.availableDsConfig.put(id, map.get(id));
				}
			} else {
				clearDataSourceConfigs(id);
			}
		} finally {
			this.wLock.unlock();
		}
	}

	private void processProperties(DataSourceConfig ds, String dsId) {
		String systemProperies = getProperty(getKey(Constants.ELEMENT_PROPERTIES, dsId), null);
		if (systemProperies != null) {
			String customizedProperies = getProperty(getCustomizedKey(Constants.ELEMENT_PROPERTIES, dsId), "");
			Map<String, String> sysMap = Splitters.by(',', ':').trim().split(systemProperies);
			Map<String, String> customizedMap = Splitters.by(',', ':').trim().split(customizedProperies);

			for (Entry<String, String> property : sysMap.entrySet()) {
				Any any = new Any();
				String key = property.getKey();
				any.setName(key);
				if (customizedMap.containsKey(key)) {
					any.setValue(customizedMap.get(key));
				} else {
					any.setValue(property.getValue());
				}

				ds.getProperties().add(any);
			}
		}
	}

	@Override
	protected void updateProperties(PropertyChangeEvent evt) {
		if (evt instanceof AdvancedPropertyChangeEvent) {
			try {
				GroupDataSourceConfig newDataSourceConfigCache = initDataSourceConfig();
				validateConfig(newDataSourceConfigCache.getDataSourceConfigs());

				wLock.lock();
				try {
					Map<String, DataSourceConfig> newAvailableDsConfig = new HashMap<String, DataSourceConfig>();
					Map<String, DataSourceConfig> newUnAvailableDsConfig = new HashMap<String, DataSourceConfig>();

					for (Entry<String, DataSourceConfig> entry : newDataSourceConfigCache.getDataSourceConfigs().entrySet()) {
						if (!this.unAvailableDsConfig.containsKey(entry.getKey())) {
							newAvailableDsConfig.put(entry.getKey(), entry.getValue());
						} else {
							newUnAvailableDsConfig.put(entry.getKey(), entry.getValue());
						}
					}

					this.unAvailableDsConfig = newUnAvailableDsConfig;
					this.availableDsConfig = newAvailableDsConfig;
					this.groupDataSourceConfigCache = newDataSourceConfigCache;
				} finally {
					wLock.unlock();
				}
			} catch (Throwable e) {
				logger.warn("fail to update groupDataSourceConfig.", e);
			}
		}
	}

	private void validateConfig(Map<String, DataSourceConfig> dataSourceConfigs) {
		int readNum = 0, writeNum = 0;
		for (Entry<String, DataSourceConfig> entry : dataSourceConfigs.entrySet()) {
			if (entry.getValue().isReadonly()) {
				readNum += 1;
			} else {
				writeNum += 1;
			}
		}

		if (readNum < 1 || writeNum != 1) {
			throw new GroupConfigException(String.format("Not enough read or write dataSources[read:%s, write:%s].",
			      readNum, writeNum));
		}
	}
}
