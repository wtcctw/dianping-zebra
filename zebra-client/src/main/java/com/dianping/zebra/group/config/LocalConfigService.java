package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.exception.GroupConfigException;

public class LocalConfigService implements ConfigService {

	private Logger logger = LoggerFactory.getLogger(LocalConfigService.class);

	private String resourceFileName;

	private File resourceFile;

	private List<PropertyChangeListener> listeners = new CopyOnWriteArrayList<PropertyChangeListener>();

	private AtomicReference<Properties> props = new AtomicReference<Properties>();

	private AtomicLong lastModifiedTime = new AtomicLong(-1);

	public LocalConfigService(String resourceId) {
		this.resourceFileName = resourceId + ".properties";
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.listeners.add(listener);
	}

	private File getFile() {
		URL propUrl = getClass().getClassLoader().getResource(this.resourceFileName);
		if (propUrl != null) {
			return FileUtils.toFile(propUrl);
		} else {
			throw new GroupConfigException(String.format("config file[%s] doesn't exist.", this.resourceFileName));
		}
	}

	private long getLastModifiedTime() {
		long lastModifiedTime = -1;
		if (this.resourceFile.exists()) {
			lastModifiedTime = this.resourceFile.lastModified();

			if (lastModifiedTime > this.lastModifiedTime.get()) {
				return lastModifiedTime;
			}
		} else {
			logger.warn(String.format("config file[%s] doesn't exists.", this.resourceFileName));
		}

		return lastModifiedTime;
	}

	@Override
	public String getProperty(String key) {
		return props.get().getProperty(key);
	}

	@Override
	public void init() {
		try {
			this.props.set(loadConfig());
			this.resourceFile = getFile();
			this.lastModifiedTime.set(getLastModifiedTime());

			Thread updateTask = new Thread(new ConfigPeroidCheckerTask());
			updateTask.setDaemon(true);
			updateTask.setName("Thread-" + ConfigPeroidCheckerTask.class.getName());
			updateTask.start();
		} catch (Throwable e) {
			throw new GroupConfigException(e);
		}
	}

	private Properties loadConfig() throws IOException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.resourceFileName);
		Properties prop = new Properties();

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new GroupConfigException(String.format("properties file[%s] doesn't exists.", this.resourceFileName));
		}

		return prop;
	}

	class ConfigPeroidCheckerTask implements Runnable {
		private void notifyListeners(String key, Object oldValue, Object newValue, int type) {
			PropertyChangeEvent evt = new AdvancedPropertyChangeEvent(this, key, oldValue, newValue, type);

			for (PropertyChangeListener listener : listeners) {
				listener.propertyChange(evt);
			}
		}

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					long newModifiedTime = getLastModifiedTime();
					if (newModifiedTime > lastModifiedTime.get()) {
						Properties oldProps = props.get();
						Properties newProps = loadConfig();

						for (Entry<Object, Object> entry : newProps.entrySet()) {
							String key = (String) entry.getKey();
							Object oldValue = oldProps.get(key);
							Object newValue = entry.getValue();

							if (oldProps.containsKey(key)) {
								if (!newValue.equals(oldValue)) {
									notifyListeners(key, oldValue, newValue, 3);
								}
							} else {
								notifyListeners(key, oldValue, newValue, 2);
							}
						}

						for (Entry<Object, Object> entry : oldProps.entrySet()) {
							String key = (String) entry.getKey();

							if (!newProps.containsKey(key)) {
								notifyListeners(key, entry.getValue(), null, 3);
							}
						}

						props.set(loadConfig());
					}
				} catch (Throwable throwable) {
					if (logger.isDebugEnabled()) {
						logger.debug(String.format("fail to reload the datasource config[%s]", resourceFileName), throwable);
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
