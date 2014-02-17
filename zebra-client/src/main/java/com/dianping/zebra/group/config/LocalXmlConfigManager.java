package com.dianping.zebra.group.config;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		URL xmlUrl = getClass().getClassLoader().getResource(xmlPath);
		if (xmlUrl != null) {
			this.configFile = FileUtils.toFile(xmlUrl);
		} else {
			throw new ConfigException(String.format("config file[%s] doesn't exists.", xmlPath));
		}
	}

	@Override
	public void addListerner(GroupConfigChangeListener listener) {
		listeners.add(listener);
	}

	@Override
	public Map<String, DataSourceConfig> getDatasourceConfigs() {
		return groupDatasourceConfig.getDataSourceConfigs();
	}

	private long getMofieiedTime() {
		if (configFile.exists()) {
			return configFile.lastModified();
		} else {
			logger.warn(String.format("config file[%s] doesn't exists.", configFile));
		}

		return -1;
	}

	public void init() {
		try {
			groupDatasourceConfig = loadConfigFromXml();

			if (groupDatasourceConfig == null) {
				throw new ConfigException(String.format("config file[%s] doesn't exists.", configFile));
			}

			availableCache.set(groupDatasourceConfig.getDataSourceConfigs());
			lastModifiedTime.set(getMofieiedTime());

			Thread fileCheckerThread = new Thread(this);

			fileCheckerThread.setDaemon(true);
			fileCheckerThread.setName("Thread-" + LocalXmlConfigManager.class.getSimpleName());
			fileCheckerThread.start();

			logger.info("Successfully initialize LocalXmlConfigManager.");
		} catch (Throwable e) {
			throw new ConfigException(String.format("fail to initialize group datasources with config file[%s].",
			      configFile), e);
		}
	}

	private GroupDataSourceConfig loadConfigFromXml() throws SAXException, IOException {
		return DefaultSaxParser.parse(FileUtils.readFileToString(configFile));
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				long newModifiedTime = getMofieiedTime();
				if (newModifiedTime > lastModifiedTime.get()) {
					this.lastModifiedTime.set(newModifiedTime);
					GroupDataSourceConfig newConfig = loadConfigFromXml();

					if (newConfig != null && !this.groupDatasourceConfig.toString().equals(newConfig.toString())) {
						this.groupDatasourceConfig = newConfig;
						notifyListeners();
					}
				}
			} catch (Throwable throwable) {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("fail to reload the datasource config[%s]", configFile), throwable);
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

	private void notifyListeners() {
		for (GroupConfigChangeListener listener : listeners) {
			BaseGroupConfigChangeEvent event = new BaseGroupConfigChangeEvent(this.groupDatasourceConfig);
			try {
				listener.onChange(event);
			} catch (Throwable e) {
				logger.error(String.format("error to notify the listener %s", listener.getName()), e);
			}
		}
	}

	@Override
	public void markDown(String id) {
		synchronized (dataSourceConfigCache) {
			if (datasourceConfigCache.get().containsKey(id)) {
				if (availableCache.get().containsKey(id)) {
					DataSourceConfig datasourceConfig = availableCache.get().remove(id);

					unAvailableCache.get().put(id, datasourceConfig);
				} else {
					unAvailableCache.get().put(id, datasourceConfigCache.get().get(id));
				}
			} else {
				if (availableCache.get().containsKey(id)) {
					availableCache.get().remove(id);
				}
				if (unAvailableCache.get().containsKey(id)) {
					unAvailableCache.get().remove(id);
				}
			}
		}
	}

	@Override
	public void markUp(String id) {
		synchronized (dataSourceConfigCache) {
			if (datasourceConfigCache.get().containsKey(id)) {
				if (unAvailableCache.get().containsKey(id)) {
					DataSourceConfig datasourceConfig = unAvailableCache.get().remove(id);

					availableCache.get().put(id, datasourceConfig);
				} else {
					availableCache.get().put(id, datasourceConfigCache.get().get(id));
				}
			} else {
				if (availableCache.get().containsKey(id)) {
					availableCache.get().remove(id);
				}
				if (unAvailableCache.get().containsKey(id)) {
					unAvailableCache.get().remove(id);
				}
			}
		}
	}

	@Override
	public Map<String, DataSourceConfig> getAvailableDatasources() {
		return availableCache.get();
	}

	@Override
	public Map<String, DataSourceConfig> getUnAvailableDatasources() {
		return unAvailableCache.get();
	}

	@Override
	public GroupDataSourceConfig getGroupDatasourceConfig() {
		return this.groupDatasourceConfig;
	}
}
