package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.exception.IllegalConfigException;
import com.dianping.zebra.group.util.Splitters;

public class DefaultDataSourceConfigManager extends AbstractConfigManager implements DataSourceConfigManager {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDataSourceConfigManager.class);

	private final char pairSeparator = ',';

	private final char keyValueSeparator = ':';

	private GroupDataSourceConfig groupDataSourceConfigCache = new GroupDataSourceConfig();

	public DefaultDataSourceConfigManager(String name, ConfigService configService) {
		super(name, configService);
	}

	@Override
	public void addListerner(PropertyChangeListener listener) {
		listeners.add(listener);
	}

	private String getBizKey(String key) {
		return String.format("%s.%s", Constants.DEFAULT_BUSSINESS_PRFIX, key);
	}

	@Override
	public synchronized Map<String, DataSourceConfig> getDataSourceConfigs() {
		return this.groupDataSourceConfigCache.getDataSourceConfigs();
	}

	private String getDsKey(String key, String dsId) {
		return String.format("%s.%s.%s", Constants.DEFAULT_DATASOURCE_PRFIX, dsId, key);
	}

	private String getGroupKey(String key) {
		return String.format("%s.%s", Constants.DEFAULT_GROUP_PRFIX, key);
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
	public synchronized String getRouterStrategy() {
		return this.groupDataSourceConfigCache.getRouterStrategy();
	}

	@Override
	public synchronized void init() {
		try {
			this.groupDataSourceConfigCache = initDataSourceConfig();
		} catch (Throwable e) {
			throw new IllegalConfigException(String.format(
			      "Fail to initialize DefaultDataSourceConfigManager with config file[%s].", this.name), e);
		}
	}

	private GroupDataSourceConfig initDataSourceConfig() throws SAXException, IOException {
		GroupDataSourceConfig groupDsConfig = new GroupDataSourceConfig();
		String config = configService.getProperty(getBizKey(this.name));
		List<String> parts = Splitters.by(pairSeparator).trim().split(config);

		for (String part : parts) {
			setUpPartConfig(groupDsConfig, part);
		}

		groupDsConfig
		      .setRouterStrategy(getProperty(String.format("%s.%s.%s", Constants.DEFAULT_DATASOURCE_PRFIX, this.name,
		            Constants.ELEMENT_ROUTER_STRATEGY), groupDsConfig.getRouterStrategy()));
		groupDsConfig.setTransactionForceWrite(getProperty(String.format("%s.%s.%s", Constants.DEFAULT_DATASOURCE_PRFIX,
		      this.name, Constants.ELEMENT_TRANSACTION_FORCE_WREITE), groupDsConfig.getTransactionForceWrite()));

		validateConfig(groupDsConfig.getDataSourceConfigs());

		return groupDsConfig;
	}

	@Override
	public synchronized boolean isTransactionForceWrite() {
		return this.groupDataSourceConfigCache.getTransactionForceWrite();
	}

	@Override
	protected synchronized void onPropertiesUpdated(PropertyChangeEvent evt) {
		if (evt instanceof AdvancedPropertyChangeEvent) {
			try {
				GroupDataSourceConfig newDataSourceConfigCache = initDataSourceConfig();
				validateConfig(newDataSourceConfigCache.getDataSourceConfigs());

				Map<String, DataSourceConfig> newAvailableDsConfig = new HashMap<String, DataSourceConfig>();
				Map<String, DataSourceConfig> newUnAvailableDsConfig = new HashMap<String, DataSourceConfig>();

				for (Entry<String, DataSourceConfig> entry : newDataSourceConfigCache.getDataSourceConfigs().entrySet()) {
					if (entry.getValue().getActive()) {
						newAvailableDsConfig.put(entry.getKey(), entry.getValue());
					} else {
						newUnAvailableDsConfig.put(entry.getKey(), entry.getValue());
					}
				}

				this.groupDataSourceConfigCache = newDataSourceConfigCache;
			} catch (Throwable e) {
				logger.warn("fail to update groupDataSourceConfig.", e);
			}
		}
	}

	private void processProperties(DataSourceConfig ds, String dsId) {
		String systemProperies = getProperty(getDsKey(Constants.ELEMENT_PROPERTIES, dsId), null);

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
		String readOrWriteDataSourceValue = configService.getProperty(getGroupKey(readOrWriteDataSourceKey));
		Map<String, String> pairs = Splitters.by(pairSeparator, keyValueSeparator).trim()
		      .split(readOrWriteDataSourceValue);

		for (Entry<String, String> pair : pairs.entrySet()) {
			String dsId = pair.getKey();
			DataSourceConfig ds = groupDsConfig.findDataSourceConfig(dsId);

			if (ds == null) {
				ds = groupDsConfig.findOrCreateDataSourceConfig(dsId);

				ds.setActive(getProperty(getDsKey(Constants.ELEMENT_ACTIVE, dsId), ds.getActive()));
				ds.setTestReadOnlySql(getProperty(getDsKey(Constants.ELEMENT_TEST_READONLY_SQL, dsId),
				      ds.getTestReadOnlySql()));
				ds.setDriverClass(getProperty(getDsKey(Constants.ELEMENT_DRIVER_CLASS, dsId), ds.getDriverClass()));
				ds.setId(dsId);
				ds.setInitialPoolSize(getProperty(getDsKey(Constants.ELEMENT_INITIAL_POOL_SIZE, dsId),
				      ds.getInitialPoolSize()));
				ds.setJdbcUrl(getProperty(getDsKey(Constants.ELEMENT_JDBC_URL, dsId), ds.getJdbcUrl()));
				ds.setMaxPoolSize(getProperty(getDsKey(Constants.ELEMENT_MAX_POOL_SIZE, dsId), ds.getMaxPoolSize()));
				ds.setMinPoolSize(getProperty(getDsKey(Constants.ELEMENT_MIN_POOL_SIZE, dsId), ds.getMinPoolSize()));
				ds.setPassword(getProperty(getDsKey(Constants.ELEMENT_PASSWORD, dsId), ds.getPassword()));
				ds.setCheckoutTimeout(getProperty(getDsKey(Constants.ELEMENT_CHECKOUT_TIMEOUT, dsId),
				      ds.getCheckoutTimeout()));
				ds.setUser(getProperty(getDsKey(Constants.ELEMENT_USER, dsId), ds.getUser()));

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
			throw new IllegalConfigException(String.format("Not enough read or write dataSources[read:%s, write:%s].",
			      readNum, writeNum));
		}
	}
}
