package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map.Entry;

import com.dianping.zebra.Constants;
import com.dianping.zebra.config.ConfigService;
import com.dianping.zebra.group.config.system.entity.SqlFlowControl;
import com.dianping.zebra.group.config.system.entity.SystemConfig;
import com.dianping.zebra.group.config.system.transform.DefaultSaxParser;
import com.dianping.zebra.group.exception.IllegalConfigException;
import com.dianping.zebra.util.AppPropertiesUtils;
import com.dianping.zebra.util.StringUtils;

public class DefaultSystemConfigManager extends AbstractConfigManager implements SystemConfigManager {

	public static final String DEFAULT_LOCAL_CONFIG = "zebra.system";

	private SystemConfig systemConfig = new SystemConfig();

	public DefaultSystemConfigManager(ConfigService configService) {
		super(configService);
	}

	@Override
	public void addListerner(PropertyChangeListener listener) {
		listeners.add(listener);
	}

	private String getKey(String key) {
		return String.format("%s.%s.%s", Constants.DEFAULT_DATASOURCE_ZEBRA_PRFIX, "system", key);
	}

	@Override
	public SystemConfig getSystemConfig() {
		return this.systemConfig;
	}

	@Override
	public void init() {
		try {
			this.systemConfig = initSystemConfig();
		} catch (Exception e) {
			throw new IllegalConfigException(String.format(
			      "Fail to initialize DefaultSystemConfigManager with config file[%s].", DEFAULT_LOCAL_CONFIG), e);
		}
	}

	public SystemConfig initSystemConfig() {
		SystemConfig config = new SystemConfig();

		config.setRetryTimes(getProperty(getKey(Constants.ELEMENT_RETRY_TIMES), config.getRetryTimes()));
		buildFlowControl(config);

		return config;
	}

	private void buildFlowControl(SystemConfig config) {
		String flowControlConfig = getProperty(getKey(Constants.ELEMENT_FLOW_CONTROL), null);
		if (StringUtils.isNotBlank(flowControlConfig)) {
			try {
				SystemConfig flowControl = DefaultSaxParser.parse(flowControlConfig);
				String appName = AppPropertiesUtils.getAppName();

				if (!Constants.PHOENIX_APP_NO_NAME.equals(appName)) {
					for (Entry<String, SqlFlowControl> flowControlEntry : flowControl.getSqlFlowControls().entrySet()) {
						SqlFlowControl sqlFlowControl = flowControlEntry.getValue();
						String app = sqlFlowControl.getApp();

						if ("_global_".equalsIgnoreCase(app) || appName.equalsIgnoreCase(app)) {
							config.addSqlFlowControl(sqlFlowControl);
						}
					}
				} else {
					config.getSqlFlowControls().putAll(flowControl.getSqlFlowControls());
				}
			} catch (Exception ignore) {
			}
		}
	}

	protected void onPropertyUpdated(PropertyChangeEvent evt) {
		String key = evt.getPropertyName();

		synchronized (this.systemConfig) {
			SystemConfig config = this.systemConfig;

			if (key.equals(getKey(Constants.ELEMENT_RETRY_TIMES))) {
				config.setRetryTimes(getProperty(getKey(Constants.ELEMENT_RETRY_TIMES), config.getRetryTimes()));
			} else if (key.equals(getKey(Constants.ELEMENT_FLOW_CONTROL))) {
				buildFlowControl(config);
			}
		}
	}
}
