package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.config.datasource.transform.BaseVisitor;
import com.dianping.zebra.group.exception.IllegalConfigException;
import com.dianping.zebra.group.util.Splitters;

public class DefaultDataSourceConfigManager extends AbstractConfigManager implements DataSourceConfigManager {

	private final char pairSeparator = '&';

	private final char keyValueSeparator = '=';

	private GroupDataSourceConfig groupDataSourceConfig;

	private GroupDataSourceConfigBuilder builder;

	private boolean verbose = false;

	private boolean isSingleDataSource;

	public DefaultDataSourceConfigManager(String name, ConfigService configService, boolean isSingleDataSource) {
		super(name, configService);
		this.isSingleDataSource = isSingleDataSource;
	}

	@Override
	public void addListerner(PropertyChangeListener listener) {
		listeners.add(listener);
	}

	@Override
	public GroupDataSourceConfig getGroupDataSourceConfig() {
		if (isSingleDataSource) {
			return null;
		}
		return initGroupDataSourceConfig();
	}

	@Override
	public DataSourceConfig getSingleDataSourceConfig() {
		if (!isSingleDataSource) {
			return null;
		}
		return initSingleDataSourceConfig();
	}

	@Override
	public synchronized String getRouterStrategy() {
		return this.groupDataSourceConfig.getRouterStrategy();
	}

	@Override
	public synchronized void init() {
		try {
			this.builder = new GroupDataSourceConfigBuilder();

			if (!isSingleDataSource) {
				this.groupDataSourceConfig = initGroupDataSourceConfig();
			}
		} catch (Throwable e) {
			throw new IllegalConfigException(String.format(
			      "Fail to initialize DefaultDataSourceConfigManager with config key[%s].", this.jdbcRef), e);
		}
	}

	private DataSourceConfig initSingleDataSourceConfig() {
		DataSourceConfig singleDataSourceConfig = new DataSourceConfig(jdbcRef);
		this.builder.visitDataSourceConfig(singleDataSourceConfig);

		return singleDataSourceConfig;
	}

	private GroupDataSourceConfig initGroupDataSourceConfig() {
		GroupDataSourceConfig groupDataSourceConfig = new GroupDataSourceConfig();
		this.builder.visitGroupDataSourceConfig(groupDataSourceConfig);

		return groupDataSourceConfig;
	}

	@Override
	public synchronized boolean isTransactionForceWrite() {
		return this.groupDataSourceConfig.getTransactionForceWrite();
	}

	@Override
	public boolean isWriteFirst() {
		return this.groupDataSourceConfig.isWriteFirst();
	}

	@Override
	protected synchronized void onPropertyUpdated(PropertyChangeEvent evt) {
		if (evt instanceof AdvancedPropertyChangeEvent) {
			if (!isSingleDataSource) {
				GroupDataSourceConfig newDataSourceConfigCache = initGroupDataSourceConfig();
				this.groupDataSourceConfig = newDataSourceConfigCache;
			}
		}
	}

	@Override
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
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

	class GroupDataSourceConfigBuilder extends BaseVisitor {

		private String getGroupDataSourceKey() {
			if (verbose) {
				return String.format("%s.%s.mapping", Constants.DEFAULT_DATASOURCE_GROUP_VERBOSE_PRFIX, jdbcRef);
			} else {
				return String.format("%s.%s.mapping", Constants.DEFAULT_DATASOURCE_GROUP_PRFIX, jdbcRef);
			}
		}

		private String getSingleDataSourceKey(String key, String dsId) {
			if (verbose) {
				return String.format("%s.%s.%s", Constants.DEFAULT_DATASOURCE_SINGLE_VERBOSE_PRFIX, dsId, key);
			} else {
				return String.format("%s.%s.jdbc.%s", Constants.DEFAULT_DATASOURCE_SINGLE_PRFIX, dsId, key);
			}
		}

		Map<String, ReadOrWriteRole> parseConfig(String config) {
			Map<String, ReadOrWriteRole> dataSources = new LinkedHashMap<String, ReadOrWriteRole>();

			StringBuilder name = new StringBuilder(20);
			StringBuilder role = new StringBuilder(5);

			boolean isName = false;
			for (int i = 0; i < config.length(); i++) {
				char c = config.charAt(i);

				if (c == '(') {
					isName = true;
				} else if (c == ')') {
					setNameAndRole(dataSources, name, role);

					isName = false;
					name.setLength(0);
					role.setLength(0);
				} else if (c == ':') {
					isName = false;
				} else if (c == ',') {
					if (name.length() > 0) {
						setNameAndRole(dataSources, name, role);

						isName = true;
						name.setLength(0);
						role.setLength(0);
					} else {
						isName = false;
					}
				} else {
					if (isName) {
						name.append(c);
					} else {
						role.append(c);
					}
				}
			}

			return dataSources;
		}

		private void setNameAndRole(Map<String, ReadOrWriteRole> dataSources, StringBuilder name, StringBuilder role) {
			String key = name.toString().trim();
			String value = role.toString().trim();

			ReadOrWriteRole readOrWrite = dataSources.get(key);
			if (readOrWrite == null) {
				readOrWrite = new ReadOrWriteRole();
				dataSources.put(key, readOrWrite);
			}

			if (value.length() > 0) {
				readOrWrite.setRead(true);
				readOrWrite.setWeight(Integer.parseInt(value));
			} else {
				readOrWrite.setWrite(true);
			}
		}

		@Override
		public void visitDataSourceConfig(DataSourceConfig dsConfig) {
			String dsId = dsConfig.getId();

			dsConfig.setId(dsId);
			dsConfig.setActive(getProperty(getSingleDataSourceKey(Constants.ELEMENT_ACTIVE, dsId), dsConfig.getActive()));
			dsConfig.setTestReadOnlySql(getProperty(getSingleDataSourceKey(Constants.ELEMENT_TEST_READONLY_SQL, dsId),
			      dsConfig.getTestReadOnlySql()));
			dsConfig.setPunishLimit(getProperty(getSingleDataSourceKey(Constants.ELEMENT_PUNISH_LIMIT, dsId),
			      dsConfig.getPunishLimit()));
			dsConfig.setTimeWindow(getProperty(getSingleDataSourceKey(Constants.ELEMENT_TIME_WINDOW, dsId),
			      dsConfig.getTimeWindow()));
			dsConfig.setDriverClass(getProperty(getSingleDataSourceKey(Constants.ELEMENT_DRIVER_CLASS, dsId),
			      dsConfig.getDriverClass()));
			dsConfig.setJdbcUrl(getProperty(getSingleDataSourceKey(Constants.ELEMENT_JDBC_URL, dsId),
			      dsConfig.getJdbcUrl()));
			dsConfig.setPassword(getProperty(getSingleDataSourceKey(Constants.ELEMENT_PASSWORD, dsId),
			      dsConfig.getPassword()));
			dsConfig.setUser(getProperty(getSingleDataSourceKey(Constants.ELEMENT_USER, dsId), dsConfig.getUser()));

			String properies = getProperty(getSingleDataSourceKey(Constants.ELEMENT_PROPERTIES, dsId), null);

			if (properies != null) {
				Map<String, String> sysMap = Splitters.by(pairSeparator, keyValueSeparator).trim().split(properies);

				for (Entry<String, String> property : sysMap.entrySet()) {
					Any any = new Any();
					any.setName(property.getKey());
					any.setValue(property.getValue());

					dsConfig.getProperties().add(any);
				}
			}
		}

		@Override
		public void visitGroupDataSourceConfig(GroupDataSourceConfig groupDsConfig) {
			String config = configService.getProperty(getGroupDataSourceKey());

			if (config != null && config.length() > 0) {
				Map<String, ReadOrWriteRole> pairs = parseConfig(config);

				for (Entry<String, ReadOrWriteRole> pair : pairs.entrySet()) {
					String key = pair.getKey();
					ReadOrWriteRole role = pair.getValue();

					DataSourceConfig dataSource = groupDsConfig.findOrCreateDataSourceConfig(key);
					visitDataSourceConfig(dataSource);
					dataSource.setCanRead(role.isRead());
					dataSource.setWeight(role.getWeight());
					dataSource.setCanWrite(role.isWrite());
				}

				validateConfig(groupDsConfig.getDataSourceConfigs());
			}
		}
	}

	class ReadOrWriteRole {
		private boolean isRead;

		private boolean isWrite;

		private int weight;

		public int getWeight() {
			return weight;
		}

		public boolean isRead() {
			return isRead;
		}

		public boolean isWrite() {
			return isWrite;
		}

		public void setRead(boolean isRead) {
			this.isRead = isRead;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public void setWrite(boolean isWrite) {
			this.isWrite = isWrite;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(5);

			if (isWrite) {
				sb.append('w');
			}

			if (isRead) {
				sb.append('r');
				sb.append(weight);
			}

			return sb.toString();
		}
	}
}
