package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

	private static final Logger logger = LoggerFactory.getLogger(DefaultDataSourceConfigManager.class);

	private GroupDataSourceConfig groupDataSourceConfigCache = new GroupDataSourceConfig();

	private Map<String, DataSourceConfig> availableDsConfig = new HashMap<String, DataSourceConfig>();

	private Map<String, DataSourceConfig> unAvailableDsConfig = new HashMap<String, DataSourceConfig>();

	private ReadWriteLock lock = new ReentrantReadWriteLock();

	private Lock rLock = lock.readLock();

	private Lock wLock = lock.writeLock();

	private char pairSeparator = ',';

	private char keyValueSeparator = ':';

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

	@Override
	public Map<String, DataSourceConfig> getDataSourceConfigs() {
		this.rLock.lock();
		try {
			return this.groupDataSourceConfigCache.getDataSourceConfigs();
		} finally {
			this.rLock.unlock();
		}
	}

	private String getKey(String key) {
		return String.format("%s.%s", Constants.DEFAULT_DATASOURCE_RESOURCE_ID_PRFIX, key);
	}

	private String getKey(String key, String dsId) {
		return String.format("%s.%s.%s", Constants.DEFAULT_DATASOURCE_RESOURCE_ID_PRFIX, dsId, key);
	}

	private int getReadWeight(String dsValue, int indexOfR, int indexOfW) {
		if (dsValue.length() > 1 && (indexOfW < indexOfR)) {
			return Integer.parseInt(dsValue.substring(indexOfR + 1));
		} else if (dsValue.length() > 1 && (indexOfW > indexOfR)) {
			return Integer.parseInt(dsValue.substring(indexOfR + 1, indexOfW));
		}

		return 1;
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
			      "Fail to initialize DefaultDataSourceConfigManager with config file[%s].", this.name), e);
		}
	}

	private GroupDataSourceConfig initDataSourceConfig() throws SAXException, IOException {
		GroupDataSourceConfig groupDsConfig = new GroupDataSourceConfig();
		String config = configService.getProperty(getKey(this.name));
		List<String> parts = Splitters.by(pairSeparator).trim().split(config);

		if (parts.size() != 2) {
			throw new GroupConfigException("invalid dataSource config : " + config);
		}

		setUpPartConfig(groupDsConfig, parts.get(0));
		setUpPartConfig(groupDsConfig, parts.get(1));

		groupDsConfig.setRouterStrategy(getProperty(String.format("%s.%s.%s",
		      Constants.DEFAULT_DATASOURCE_RESOURCE_ID_PRFIX, this.name, Constants.ELEMENT_ROUTER_STRATEGY),
		      groupDsConfig.getRouterStrategy()));
		groupDsConfig.setTransactionForceWrite(getProperty(String.format("%s.%s.%s",
		      Constants.DEFAULT_DATASOURCE_RESOURCE_ID_PRFIX, this.name, Constants.ELEMENT_TRANSACTION_FORCE_WREITE),
		      groupDsConfig.getTransactionForceWrite()));

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

	@Override
	protected void onPropertiesUpdated(PropertyChangeEvent evt) {
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

	private void processProperties(DataSourceConfig ds, String dsId) {
		String systemProperies = getProperty(getKey(Constants.ELEMENT_PROPERTIES, dsId), null);

		if (systemProperies != null) {
			Map<String, String> sysMap = Splitters.by(pairSeparator, keyValueSeparator).trim().split(systemProperies);

			for (Entry<String, String> property : sysMap.entrySet()) {
				Any any = new Any();
				any.setName(property.getKey());
				any.setValue(property.getValue());

				ds.getProperties().add(any);
			}
		}
	}

	private void setUpPartConfig(GroupDataSourceConfig groupDsConfig, String readOrWriteDataSourceKey) {
		String readOrWriteDataSourceValue = "";
		if (readOrWriteDataSourceKey.indexOf('#') != 0) {
			throw new GroupConfigException("invalid dataSource config : " + readOrWriteDataSourceKey);
		} else {
			readOrWriteDataSourceValue = configService.getProperty(getKey(readOrWriteDataSourceKey.substring(1)));
		}

		Map<String, String> pairs = Splitters.by(pairSeparator, keyValueSeparator).trim()
		      .split(readOrWriteDataSourceValue);

		for (Entry<String, String> pair : pairs.entrySet()) {
			String dsId = pair.getKey();
			DataSourceConfig ds = groupDsConfig.findDataSourceConfig(dsId);

			if (ds == null) {
				ds = groupDsConfig.findOrCreateDataSourceConfig(dsId);

				ds.setActive(getProperty(getKey(Constants.ELEMENT_ACTIVE, dsId), ds.getActive()));
				ds.setDriverClass(getProperty(getKey(Constants.ELEMENT_DRIVER_CLASS, dsId), ds.getDriverClass()));
				ds.setId(dsId);
				ds.setInitialPoolSize(getProperty(getKey(Constants.ELEMENT_INITIAL_POOL_SIZE, dsId),
				      ds.getInitialPoolSize()));
				ds.setJdbcUrl(getProperty(getKey(Constants.ELEMENT_JDBC_URL, dsId), ds.getJdbcUrl()));
				ds.setMaxPoolSize(getProperty(getKey(Constants.ELEMENT_MAX_POOL_SIZE, dsId), ds.getMaxPoolSize()));
				ds.setMinPoolSize(getProperty(getKey(Constants.ELEMENT_MIN_POOL_SIZE, dsId), ds.getMinPoolSize()));
				ds.setPassword(getProperty(getKey(Constants.ELEMENT_PASSWORD, dsId), ds.getPassword()));
				ds.setCheckoutTimeout(getProperty(getKey(Constants.ELEMENT_CHECKOUT_TIMEOUT, dsId), ds.getCheckoutTimeout()));
				ds.setUser(getProperty(getKey(Constants.ELEMENT_USER, dsId), ds.getUser()));

				processProperties(ds, dsId);
			}

			setUpReadOnlyAndWeight(ds, pair.getValue().toLowerCase());
		}
	}

	private void setUpReadOnlyAndWeight(DataSourceConfig ds, String value) {
		int indexOfW = value.indexOf('w');
		int indexOfR = value.indexOf('r');

		if (indexOfW != -1) {
			ds.setCanWrite(true);
		}

		if (indexOfR != -1) {
			ds.setCanRead(true);
			ds.setWeight(getReadWeight(value, indexOfR, indexOfW));
		}
	}

	private void validateConfig(Map<String, DataSourceConfig> dataSourceConfigs) {
		int readNum = 0, writeNum = 0;
		for (Entry<String, DataSourceConfig> entry : dataSourceConfigs.entrySet()) {
			if (entry.getValue().getCanRead()) {
				readNum += 1;
			}
			if (entry.getValue().getCanWrite()) {
				writeNum += 1;
			}
		}

		if (readNum < 1 || writeNum < 1) {
			throw new GroupConfigException(String.format("Not enough read or write dataSources[read:%s, write:%s].",
			      readNum, writeNum));
		}
	}
}
