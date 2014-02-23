package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.config.system.Constants;
import com.dianping.zebra.group.config.system.entity.SystemConfig;
import com.dianping.zebra.group.exception.GroupConfigException;

public class DefaultSystemConfigManager implements SystemConfigManager {

	private Logger logger = LoggerFactory.getLogger(DefaultSystemConfigManager.class);

	private final String resourceId;

	private final ConfigService configService;

	private AtomicReference<SystemConfig> systemConfig = new AtomicReference<SystemConfig>();

	private List<PropertyChangeListener> listeners = new CopyOnWriteArrayList<PropertyChangeListener>();

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

	public DefaultSystemConfigManager(String resourceId, ConfigService configService) {
		this.resourceId = resourceId;
		this.configService = configService;
	}

	@Override
	public void addListerner(PropertyChangeListener listener) {
		listeners.add(listener);
	}

	private int getProperty(SystemConfig config, String key, int defaultValue) {
		String value = configService.getProperty(key);

		if (StringUtils.isNotBlank(value)) {
			return Integer.parseInt(value);
		} else {
			return defaultValue;
		}
	}

	private String getProperty(SystemConfig config, String key, String defaultValue) {
		String value = configService.getProperty(key);

		if (StringUtils.isNotBlank(value)) {
			return value;
		} else {
			return defaultValue;
		}
	}

	@Override
	public SystemConfig getSystemConfig() {
		return this.systemConfig.get();
	}

	@Override
	public void init() {
		try {
			systemConfig.set(initSystemConfig());
			this.configService.init();
			this.configService.addPropertyChangeListener(new InnerPropertyChangeListener());
		} catch (Throwable e) {
			throw new GroupConfigException(String.format(
			      "fail to initialize LocalSystemConfigManager with config file[%s].", this.resourceId), e);
		}
	}

	public SystemConfig initSystemConfig() {
		SystemConfig config = new SystemConfig();

		config.setCookieDomain(getProperty(config, Constants.ELEMENT_COOKIE_DOMAIN, config.getCookieDomain()));
		config.setHealthCheckInterval(getProperty(config, Constants.ELEMENT_HEALTH_CHECK_INTERVAL,
		      config.getHealthCheckInterval()));
		config.setMaxErrorCounter(getProperty(config, Constants.ELEMENT_MAX_ERROR_COUNTER, config.getMaxErrorCounter()));
		config.setRetryTimes(getProperty(config, Constants.ELEMENT_RETRY_TIMES, config.getRetryTimes()));
		config.setRouterStrategy(getProperty(config, Constants.ELEMENT_ROUTER_STRATEGY, config.getRouterStrategy()));

		return config;
	}

	class InnerPropertyChangeListener implements PropertyChangeListener {
		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			try {
				systemConfig.set(initSystemConfig());
				notify(evt);
			} catch (Throwable e) {
				logger.warn("Illegal system config change,still apply old system config!", e);
			}
		}

		private void notify(final PropertyChangeEvent evt) {
			for (final PropertyChangeListener listener : listeners) {
				try {
					Runnable task = new Runnable() {

						@Override
						public void run() {
							listener.propertyChange(evt);
						}
					};

					listenerNotifyThreadPool.submit(task);
				} catch (Throwable e) {
					logger.error(String.format("error to notify the listener %s", listener.getClass()), e);
				}
			}
		}
	}
}
