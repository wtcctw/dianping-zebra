/**
 * Project: ${zebra-syncserver.aid}
 * 
 * File Created at 2011-7-27
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.syncserver.config;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 同步Server配置中心
 * 
 * @author Leo Liang
 * 
 */
public final class Config {
	private static final Logger	log								= Logger.getLogger(Config.class);

	private static final String	CONFIG_FILE						= "syncserver-config.properties";
	private static final String	KEY_CONNECTOR_PARAMS_URL		= "connector.url";
	private static final String	KEY_CONNECTOR_PARAMS_QUEUENAME	= "connector.queuename";

	private static Config		instance						= new Config();

	private ConnectorParams		connectorParams;
    private boolean             slave                          = false;

	private Config() {
		InputStream in = null;
		in = Config.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
		Properties prop = new Properties();
		try {
			prop.load(in);

			String connectorUrl = prop.getProperty(KEY_CONNECTOR_PARAMS_URL);
			String connectorQueuename = prop.getProperty(KEY_CONNECTOR_PARAMS_QUEUENAME);

			if (StringUtils.isBlank(connectorUrl) || StringUtils.isBlank(connectorQueuename)) {
				throw new IllegalArgumentException("ConnectorUrl and connectorQueuename can not be null.");
			}

			connectorParams = new ConnectorParams(connectorUrl, connectorQueuename);

		} catch (Exception e) {
			log.error("Load config failed.", e);
			throw new RuntimeException(e);
		}
	}

	public static Config getInstance() {
		return instance;
	}

	public ConnectorParams getConnectorParams() {
		return connectorParams;
	}

    public boolean isSlave() {
        return slave;
    }

    public void setSlave(boolean slave) {
        this.slave = slave;
    }

	public static class ConnectorParams {
		private String	connectorUrl;
		private String	queueName;

		public ConnectorParams(String connectorUrl, String queueName) {
			this.connectorUrl = connectorUrl;
			this.queueName = queueName;
		}

		/**
		 * @return the connectorUrl
		 */
		public String getConnectorUrl() {
			return connectorUrl;
		}

		/**
		 * @param connectorUrl
		 *            the connectorUrl to set
		 */
		public void setConnectorUrl(String connectorUrl) {
			this.connectorUrl = connectorUrl;
		}

		/**
		 * @return the queueName
		 */
		public String getQueueName() {
			return queueName;
		}

		/**
		 * @param queueName
		 *            the queueName to set
		 */
		public void setQueueName(String queueName) {
			this.queueName = queueName;
		}

	}

}
