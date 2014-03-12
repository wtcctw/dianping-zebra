package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractConfigManager {

	class InnerPropertyChangeListener implements PropertyChangeListener {
		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			try {
				updateProperties(evt);
				notifyListeners(evt);
			} catch (Throwable e) {
				logger.warn("Illegal config file, apply old config!", e);
			}
		}
	}

	private Logger logger = LoggerFactory.getLogger(AbstractConfigManager.class);

	protected final String resourceId;

	protected final ConfigService configService;

	protected List<PropertyChangeListener> listeners = new CopyOnWriteArrayList<PropertyChangeListener>();

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

	public AbstractConfigManager(String resourceId, ConfigService configService) {
		this.resourceId = resourceId;
		this.configService = configService;
		this.configService.addPropertyChangeListener(new InnerPropertyChangeListener());
	}

	public boolean getProperty(String key, boolean defaultValue) {
		String value = configService.getProperty(key);

		if (StringUtils.isNotBlank(value)) {
			if (value.equals("true")) {
				return true;
			} else if (StringUtils.isNotBlank(value) && value.equals("false")) {
				return false;
			}
		}

		return defaultValue;
	}

	protected int getProperty(String key, int defaultValue) {
		String value = configService.getProperty(key);

		if (StringUtils.isNotBlank(value)) {
			return Integer.parseInt(value);
		} else {
			return defaultValue;
		}
	}

	protected String getProperty(String key, String defaultValue) {
		String value = configService.getProperty(key);

		if (StringUtils.isNotBlank(value)) {
			return value;
		} else {
			return defaultValue;
		}
	}

	private void notifyListeners(final PropertyChangeEvent evt) {
		try {
			for (final PropertyChangeListener listener : listeners) {
				Runnable task = new Runnable() {

					@Override
					public void run() {
						listener.propertyChange(evt);
					}
				};

				listenerNotifyThreadPool.submit(task);
			}
		} catch (Throwable e) {
			logger.warn("thread pool error!", e);
		}
	}

	protected abstract void updateProperties(PropertyChangeEvent evt);
}
