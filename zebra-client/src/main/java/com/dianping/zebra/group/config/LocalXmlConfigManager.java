package com.dianping.zebra.group.config;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.dianping.zebra.group.config.database.entity.Database;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.config.datasource.transform.DefaultSaxParser;
import com.dianping.zebra.group.exception.ConfigException;

public class LocalXmlConfigManager implements GroupConfigManager, Runnable {

	private static final Logger logger = LoggerFactory.getLogger(LocalXmlConfigManager.class);

	private ReadWriteLock lock = new ReentrantReadWriteLock();

	private Lock rLock = lock.readLock();

	private Lock wLock = lock.writeLock();

	private GroupDataSourceConfig groupDatasourceConfig;

	private AtomicReference<Map<String, DataSourceConfig>> availableCache = new AtomicReference<Map<String, DataSourceConfig>>(
	      new HashMap<String, DataSourceConfig>());

	private AtomicReference<Map<String, DataSourceConfig>> unAvailableCache = new AtomicReference<Map<String, DataSourceConfig>>(
	      new HashMap<String, DataSourceConfig>());

	private List<GroupConfigChangeListener> listeners = new CopyOnWriteArrayList<GroupConfigChangeListener>();

	private AtomicLong lastModifiedTime = new AtomicLong(-1);

	private File configFile;

	public LocalXmlConfigManager(String xmlPath) {
		this.configFile = getFile(xmlPath);
	}

	@Override
	public void addListerner(GroupConfigChangeListener listener) {
		this.listeners.add(listener);
	}

	@Override
	public Map<String, DataSourceConfig> getAvailableDataSources() {
		rLock.lock();
		try {
			return this.availableCache.get();
		} finally {
			rLock.unlock();
		}
	}

	@Override
	public Map<String, DataSourceConfig> getDataSourceConfigs() {
		rLock.lock();
		try {
			return this.groupDatasourceConfig.getDataSourceConfigs();
		} finally {
			rLock.unlock();
		}
	}

	private File getFile(String xmlPath) {
		URL xmlUrl = getClass().getClassLoader().getResource(xmlPath);
		if (xmlUrl != null) {
			return FileUtils.toFile(xmlUrl);
		} else {
			throw new ConfigException(String.format("config file[%s] doesn't exists.", xmlPath));
		}
	}

	@Override
	public GroupDataSourceConfig getGroupDataSourceConfig() {
		rLock.lock();
		try {
			return this.groupDatasourceConfig;
		} finally {
			rLock.unlock();
		}
	}

	private long getMofieiedTime() {
		if (this.configFile.exists()) {
			return this.configFile.lastModified();
		} else {
			logger.warn(String.format("config file[%s] doesn't exists.", this.configFile));
		}

		return -1;
	}

	@Override
	public Map<String, DataSourceConfig> getUnAvailableDataSources() {
		rLock.lock();
		try {
			return this.unAvailableCache.get();
		} finally {
			rLock.unlock();
		}
	}

	public void init() {
		try {
			this.groupDatasourceConfig = loadConfigFromXml();

			if (this.groupDatasourceConfig == null) {
				throw new ConfigException(String.format("config file[%s] doesn't exists.", this.configFile));
			}

			this.availableCache.set(loadConfigFromXml().getDataSourceConfigs());
			this.lastModifiedTime.set(getMofieiedTime());

			Thread fileCheckerThread = new Thread(this);

			fileCheckerThread.setDaemon(true);
			fileCheckerThread.setName("Thread-" + LocalXmlConfigManager.class.getSimpleName());
			fileCheckerThread.start();

			logger.info("Successfully initialize LocalXmlConfigManager.");
		} catch (Throwable e) {
			throw new ConfigException(String.format("fail to initialize group datasources with config file[%s].",
			      this.configFile), e);
		}
	}

	private GroupDataSourceConfig loadConfigFromXml() throws SAXException, IOException {
		GroupDataSourceConfig configs = DefaultSaxParser.parse(FileUtils.readFileToString(this.configFile));

		if (configs != null) {
			for (Entry<String, DataSourceConfig> config : configs.getDataSourceConfigs().entrySet()) {
				File file = getFile(String.format("%s.xml", config.getKey()));
				Database database = com.dianping.zebra.group.config.database.transform.DefaultSaxParser.parseEntity(
				      Database.class, FileUtils.readFileToString(file));
				mergeAttributes(config.getValue(), database);
			}
		}

		return configs;
	}

	@Override
	public void markDown(String id) {
		boolean hasChanged = false;
		this.wLock.lock();
		try {
			Map<String, DataSourceConfig> map = this.groupDatasourceConfig.getDataSourceConfigs();

			if (map.containsKey(id)) {
				if (this.availableCache.get().containsKey(id)) {
					DataSourceConfig datasourceConfig = this.availableCache.get().remove(id);
					hasChanged = true;
					this.unAvailableCache.get().put(id, datasourceConfig);
				} else {
					this.unAvailableCache.get().put(id, map.get(id));
				}
			} else {
				if (this.availableCache.get().containsKey(id)) {
					this.availableCache.get().remove(id);
					hasChanged = true;
				}
				if (this.unAvailableCache.get().containsKey(id)) {
					this.unAvailableCache.get().remove(id);
				}
			}
		} finally {
			this.wLock.unlock();
		}

		if (hasChanged) {
			notifyActiveDataSourceListeners();
		}
	}

	@Override
	public void markUp(String id) {
		boolean hasChanged = false;
		this.wLock.lock();
		try {
			Map<String, DataSourceConfig> map = this.groupDatasourceConfig.getDataSourceConfigs();

			if (map.containsKey(id)) {
				if (this.unAvailableCache.get().containsKey(id)) {
					DataSourceConfig datasourceConfig = this.unAvailableCache.get().remove(id);

					this.availableCache.get().put(id, datasourceConfig);
					hasChanged = true;
				} else {
					this.availableCache.get().put(id, map.get(id));
					hasChanged = true;
				}
			} else {
				if (this.availableCache.get().containsKey(id)) {
					this.availableCache.get().remove(id);
					hasChanged = true;
				}
				if (this.unAvailableCache.get().containsKey(id)) {
					this.unAvailableCache.get().remove(id);
				}
			}
		} finally {
			this.wLock.unlock();
		}

		if (hasChanged) {
			notifyActiveDataSourceListeners();
		}
	}

	private void mergeAttributes(DataSourceConfig config, Database database) {
		config.setJdbcUrl(database.getJdbcUrl());
		config.setUser(database.getUser());
		config.setDriverClass(database.getDriverClass());
		config.setPassword(database.getPassword());
		config.setMinPoolSize(database.getMinPoolSize());
		config.setMaxPoolSize(database.getMaxPoolSize());
		config.setInitialPoolSize(database.getInitialPoolSize());
	}

	private void notifyActiveDataSourceListeners() {
		GroupDataSourceConfig availableDataSource = new GroupDataSourceConfig();
		availableDataSource.getDataSourceConfigs().putAll(getAvailableDataSources());
		BaseGroupConfigChangeEvent event = new ActiveDataSourceChangeEvent(availableDataSource);

		notifyListeners(event);
	}

	private void notifyListeners(BaseGroupConfigChangeEvent event) {
		for (GroupConfigChangeListener listener : this.listeners) {
			try {
				listener.onChange(event);
			} catch (Throwable e) {
				logger.error(String.format("error to notify the listener %s", listener.getName()), e);
			}
		}
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				long newModifiedTime = getMofieiedTime();
				if (newModifiedTime > this.lastModifiedTime.get()) {
					this.lastModifiedTime.set(newModifiedTime);
					GroupDataSourceConfig newConfig = loadConfigFromXml();

					if (newConfig != null && !this.groupDatasourceConfig.toString().equals(newConfig.toString())) {
						BaseGroupConfigChangeEvent event = new BaseGroupConfigChangeEvent(this.groupDatasourceConfig);
						notifyListeners(event);
					}

					wLock.lock();
					try {
						this.groupDatasourceConfig.mergeAttributes(newConfig);
					} finally {
						wLock.unlock();
					}
				}
			} catch (Throwable throwable) {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("fail to reload the datasource config[%s]", this.configFile), throwable);
				}
			}

			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				// ignore it
			}
		}
	}
}
