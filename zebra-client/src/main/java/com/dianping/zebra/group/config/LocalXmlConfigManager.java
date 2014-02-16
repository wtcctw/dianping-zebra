package com.dianping.zebra.group.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xml.sax.SAXException;

import com.dianping.zebra.group.config.entity.DatasourceConfig;
import com.dianping.zebra.group.config.entity.DatasourcesConfigs;
import com.dianping.zebra.group.config.transform.DefaultSaxParser;
import com.dianping.zebra.group.exception.ConfigException;

public class LocalXmlConfigManager implements ConfigManager, Runnable {

	private final String configPath;

	private Map<String, DatasourceConfig> configCache;

	private List<ConfigChangeListener> listeners;

	private long lastModifiedTime;

	public LocalXmlConfigManager(String propertiesPath) {
		this.configPath = propertiesPath;
	}

	@Override
	public synchronized void addListerner(ConfigChangeListener listener) {
		if (listeners == null) {
			listeners = new ArrayList<ConfigChangeListener>();
		}

		listeners.add(listener);
	}

	@Override
	public synchronized Map<String, DatasourceConfig> getDatasourceConfigs() {
		return configCache;
	}

	private long getMofieiedTime() {
		String path = getClass().getClassLoader().getResource(configPath).getPath();
		File file = new File(path);

		if (file.exists()) {
			return file.lastModified();
		}

		return -1;
	}

	public void init() {
		try {
			load();

			Thread fileCheckerThread = new Thread(this);
			fileCheckerThread.setDaemon(true);
			fileCheckerThread.setName(LocalXmlConfigManager.class.getSimpleName());

			fileCheckerThread.start();
			//TODO logger
		} catch (Throwable e) {
			throw new ConfigException(String.format("fail to initialize group datasources with config file[%s].",
			      configPath), e);
		}
	}

	private void load() throws SAXException, IOException {
		DatasourcesConfigs configs = DefaultSaxParser.parse(getClass().getClassLoader().getResourceAsStream(configPath));

		configCache = configs.getDatasourceConfigs();
		lastModifiedTime = getMofieiedTime();

	}

	@Override
	public void run() {
		while (true) {
			if (getMofieiedTime() > lastModifiedTime) {
				try {
					load();

					for (ConfigChangeListener listener : listeners) {
						ConfigChangeEvent event = new ConfigChangeEvent(configCache);
						listener.onChange(event);
					}
				} catch (Throwable throwable) {
					// TODO logger
				}
			}

			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				// ignore it
			}
		}
	}

}
