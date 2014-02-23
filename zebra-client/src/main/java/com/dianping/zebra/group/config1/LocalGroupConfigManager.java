package com.dianping.zebra.group.config1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
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
import com.dianping.zebra.group.exception.GroupConfigException;

public class LocalGroupConfigManager implements GroupConfigManager {

	public static final String ID = "local";

	private static final Logger logger = LoggerFactory.getLogger(LocalGroupConfigManager.class);

	private ReadWriteLock lock = new ReentrantReadWriteLock();

	private Lock rLock = lock.readLock();

	private Lock wLock = lock.writeLock();

	private GroupDataSourceConfig groupDatasourceConfig;

	private Map<String, DataSourceConfig> availableDsConfig = new HashMap<String, DataSourceConfig>();

	private Map<String, DataSourceConfig> unAvailableDsConfig = new HashMap<String, DataSourceConfig>();

	private List<GroupConfigChangeListener> listeners = new CopyOnWriteArrayList<GroupConfigChangeListener>();

	private AtomicLong lastModifiedTime = new AtomicLong(-1);

	private ExecutorService listenerNotifyThreadPool = Executors.newFixedThreadPool(5, new ThreadFactory() {

		private AtomicInteger id = new AtomicInteger(0);

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setDaemon(true);
			t.setName("Thread-Notify-" + id.incrementAndGet());

			return t;
		}
	});

	private File resourceId;

	public LocalGroupConfigManager(String resourceId) {
		this.resourceId = getFile(resourceId);
	}

	@Override
	public void addListerner(GroupConfigChangeListener listener) {
		this.listeners.add(listener);
	}

	private boolean cleanDataSourceConfigs(String id) {
		if (availableDsConfig.containsKey(id)) {
			availableDsConfig.remove(id);
			return true;
		}
		if (unAvailableDsConfig.containsKey(id)) {
			unAvailableDsConfig.remove(id);
		}
		return false;
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
			return this.groupDatasourceConfig.getDataSourceConfigs();
		} finally {
			this.rLock.unlock();
		}
	}

	private File getFile(String xmlPath) {
		URL xmlUrl = getClass().getClassLoader().getResource(xmlPath);
		if (xmlUrl != null) {
			return FileUtils.toFile(xmlUrl);
		} else {
			throw new GroupConfigException(String.format("config file[%s] doesn't exist.", xmlPath));
		}
	}

	@Override
	public GroupDataSourceConfig getGroupDataSourceConfig() {
		this.rLock.lock();
		try {
			return this.groupDatasourceConfig;
		} finally {
			this.rLock.unlock();
		}
	}

	private long getLastMofieiedTime() throws SAXException, IOException {
		long lastModifiedTime = -1;
		if (this.resourceId.exists()) {
			lastModifiedTime = this.resourceId.lastModified();

			if (lastModifiedTime > this.lastModifiedTime.get()) {
				return lastModifiedTime;
			}

			GroupDataSourceConfig configs = DefaultSaxParser.parse(FileUtils.readFileToString(this.resourceId));

			if (configs != null) {
				for (Entry<String, DataSourceConfig> config : configs.getDataSourceConfigs().entrySet()) {
					File file = getFile(String.format("%s.xml", config.getKey()));
					long time = file.lastModified();

					if (time > lastModifiedTime) {
						lastModifiedTime = time;
					}
				}
			}

		} else {
			logger.warn(String.format("config file[%s] doesn't exists.", this.resourceId));
		}

		return lastModifiedTime;
	}

	@Override
	public Map<String, DataSourceConfig> getUnAvailableDataSources() {
		rLock.lock();
		try {
			return this.unAvailableDsConfig;
		} finally {
			rLock.unlock();
		}
	}

	public void init() {
		try {
			this.groupDatasourceConfig = loadConfigFromXml();

			if (this.groupDatasourceConfig == null) {
				throw new GroupConfigException(String.format("config file[%s] doesn't exists.", this.resourceId));
			}

			this.availableDsConfig = loadConfigFromXml().getDataSourceConfigs();
			this.lastModifiedTime.set(getLastMofieiedTime());

			Thread task = new Thread(new ConfigPeroidCheckerTask());

			task.setDaemon(true);
			task.setName("Thread-" + LocalGroupConfigManager.class.getSimpleName());
			task.start();

			logger.info("Successfully initialize LocalXmlConfigManager.");
		} catch (Throwable e) {
			throw new GroupConfigException(String.format("fail to initialize LocalGroupGonfigManager with config file[%s].",
			      this.resourceId), e);
		}
	}

	private GroupDataSourceConfig loadConfigFromXml() throws SAXException, IOException {
		GroupDataSourceConfig configs = DefaultSaxParser.parse(FileUtils.readFileToString(this.resourceId));

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
		boolean isAvailDsChanged = false;
		this.wLock.lock();
		try {
			Map<String, DataSourceConfig> map = this.groupDatasourceConfig.getDataSourceConfigs();

			if (map.containsKey(id)) {
				if (this.availableDsConfig.containsKey(id)) {
					DataSourceConfig datasourceConfig = this.availableDsConfig.remove(id);
					isAvailDsChanged = true;
					this.unAvailableDsConfig.put(id, datasourceConfig);
				} else {
					this.unAvailableDsConfig.put(id, map.get(id));
				}
			} else {
				isAvailDsChanged = cleanDataSourceConfigs(id);
			}
		} finally {
			this.wLock.unlock();
		}

		if (isAvailDsChanged) {
			notifyActiveDataSourceListeners();
		}
	}

	@Override
	public void markUp(String id) {
		boolean isAvailDsChanged = false;
		this.wLock.lock();
		try {
			Map<String, DataSourceConfig> map = this.groupDatasourceConfig.getDataSourceConfigs();

			if (map.containsKey(id)) {
				if (this.unAvailableDsConfig.containsKey(id)) {
					DataSourceConfig datasourceConfig = this.unAvailableDsConfig.remove(id);

					this.availableDsConfig.put(id, datasourceConfig);
					isAvailDsChanged = true;
				} else {
					this.availableDsConfig.put(id, map.get(id));
					isAvailDsChanged = true;
				}
			} else {
				isAvailDsChanged = cleanDataSourceConfigs(id);
			}
		} finally {
			this.wLock.unlock();
		}

		if (isAvailDsChanged) {
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

	private void notifyListeners(final BaseGroupConfigChangeEvent event) {
		for (final GroupConfigChangeListener listener : this.listeners) {
			try {
				Runnable task = new Runnable() {

					@Override
					public void run() {
						listener.onChange(event);
					}
				};

				this.listenerNotifyThreadPool.submit(task);
			} catch (Throwable e) {
				logger.error(String.format("error to notify the listener %s", listener.getName()), e);
			}
		}
	}
	
	class ConfigPeroidCheckerTask implements Runnable {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					long newModifiedTime = getLastMofieiedTime();
					if (newModifiedTime > lastModifiedTime.get()) {
						lastModifiedTime.set(newModifiedTime);
						GroupDataSourceConfig newConfig = loadConfigFromXml();

						if (newConfig != null && !groupDatasourceConfig.toString().equals(newConfig.toString())) {
							BaseGroupConfigChangeEvent event = new BaseGroupConfigChangeEvent(newConfig);
							notifyListeners(event);
							boolean isAvailDsChanged = false;

							wLock.lock();
							try {
								GroupDataSourceConfig oldConfig = groupDatasourceConfig;
								groupDatasourceConfig = newConfig;

								for (String id : oldConfig.getDataSourceConfigs().keySet()) {
									if (!newConfig.getDataSourceConfigs().containsKey(id)) {
										isAvailDsChanged = cleanDataSourceConfigs(id);
									}
								}

								for (String id : newConfig.getDataSourceConfigs().keySet()) {
									if (!oldConfig.getDataSourceConfigs().containsKey(id)) {
										availableDsConfig.put(id, newConfig.getDataSourceConfigs().get(id));
										isAvailDsChanged = true;
									}
								}
							} finally {
								wLock.unlock();
							}

							if (isAvailDsChanged) {
								notifyActiveDataSourceListeners();
							}
						}
					}
				} catch (Throwable throwable) {
					if (logger.isDebugEnabled()) {
						logger.debug(String.format("fail to reload the datasource config[%s]", resourceId), throwable);
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
}
