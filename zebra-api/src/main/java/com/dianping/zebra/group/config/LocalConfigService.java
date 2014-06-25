package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.exception.IllegalConfigException;

public class LocalConfigService implements ConfigService {

	private static final Logger logger = LoggerFactory.getLogger(LocalConfigService.class);

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
			throw new IllegalConfigException(String.format("config file[%s] doesn't exist.", this.resourceFileName));
		}
	}

	private long getLastModifiedTime() {
		if (this.resourceFile.exists()) {
			return this.resourceFile.lastModified();
		} else {
			logger.warn(String.format("config file[%s] doesn't exist.", this.resourceFileName));
			return -1;
		}
	}

	@Override
	public String getProperty(String key) {
		return props.get().getProperty(key);
	}

	@Override
	public void init() {
		try {
			this.resourceFile = getFile();
			this.props.set(loadConfig());
			this.lastModifiedTime.set(getLastModifiedTime());

			Thread updateTask = new Thread(new ConfigPeroidCheckerTask());
			updateTask.setDaemon(true);
			updateTask.setName("Thread-" + ConfigPeroidCheckerTask.class.getName());
			updateTask.start();
		} catch (Throwable e) {
			logger.error("fail to initilize Local Config Manager for DAL",e);
			throw new IllegalConfigException(e);
		}
	}

	private Properties loadConfig() throws IOException {
		Properties prop = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(this.resourceFile);
			prop.load(inputStream);
		} catch (Throwable e) {
			throw new IllegalConfigException(String.format("fail to read properties file[%s]", this.resourceFileName), e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}

		return prop;
	}

	class ConfigPeroidCheckerTask implements Runnable {
		private void notifyListeners(String key, Object oldValue, Object newValue) {
			PropertyChangeEvent evt = new AdvancedPropertyChangeEvent(this, key, oldValue, newValue);

			for (PropertyChangeListener listener : listeners) {
				try {
					listener.propertyChange(evt);
				} catch (Throwable e) {
					logger.warn("fail to notify listener", e);
				}
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

						for (String key : newProps.stringPropertyNames()) {
							String oldValue = oldProps.getProperty(key);
							String newValue = newProps.getProperty(key);

							if (!StringUtils.equals(oldValue, newValue)) {
								notifyListeners(key, oldValue, newValue);
							}
						}

						lastModifiedTime.set(newModifiedTime);
						props.set(newProps);
					}
				} catch (Throwable throwable) {
					if (logger.isDebugEnabled()) {
						logger.debug(String.format("fail to reload the datasource config[%s]", resourceFileName), throwable);
					}
				}

				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					// ignore it
				}
			}
		}
	}
}
