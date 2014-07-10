/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-11-7
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
package com.dianping.zebra.monitor.sql;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dianping.hawk.jmx.HawkJMXUtil;
import com.dianping.hawk.protocol.Messages.SqlMsg;
import com.dianping.lion.client.ConfigCache;

/**
 * @author danson.liu
 *
 */
public class MonitorOptions {
	
	private static final 	Log 	logger 						= LogFactory.getLog(MonitorOptions.class);
	
	private static MonitorOptions 	instance 					= new MonitorOptions();
	
	private static final String		MONITOR_ENABLED_KEY			= "zebra.sqlmonitor.enabled";
	private static final String		MONITOR_PARAM_ENABLED_KEY	= "zebra.sqlmonitor.param.enabled";
	private static final String 	MONITOR_PARAM_THRESHOLD 	= "zebra.sqlmonitor.param.threshold";	//是否监控sql参数的sql耗时阀值, in ms

	
	private Boolean					monitorEnabledByJmx;
	private Boolean					monitorParamEnalbedByJmx;
	
	private Class<?>				lionInterface;
	
	private	boolean					isHawkApiValid;
	
	private MonitorOptions() {
		checkIfHawkApiValid();
		if (isHawkApiValid) {
			try {
				lionInterface = Class.forName("com.dianping.lion.client.ConfigCache");
			} catch (ClassNotFoundException e) {
				logger.warn("ConfigCache not found in classpath, so lion's control is unavailable.");
			}
			HawkJMXUtil.registerMBean("SqlMonitorOptions", this);
		}
	}

	private void checkIfHawkApiValid() {
		try {
			Class<?> hawkApi = Class.forName("com.dianping.hawk.HawkExtend");
			hawkApi.getMethod("logSql", SqlMsg.class);
			isHawkApiValid = true;
		} catch (ClassNotFoundException e) {
			logger.warn("Sql monitor function is disabled, because HawkExtend api not found.");
		} catch (NoSuchMethodException e) {
			logger.warn("Sql monitor function is disabled, because HawkExtend.logSql method not found, " +
					"maybe you need upgrade hawk artifact.");
		}
	}

	public static MonitorOptions getInstance() {
		return instance;
	}
	
	public boolean isMonitorEnabled() {
		if (!isHawkApiValid) {
			return false;
		}
		if (monitorEnabledByJmx != null) {
			return monitorEnabledByJmx;
		}
		Boolean monitorEnabled = null;
		if (lionInterface != null) {
			try {
				monitorEnabled = ConfigCache.getInstance().getBooleanProperty(MONITOR_ENABLED_KEY);
			} catch (Throwable e) {
			}
		}
		return monitorEnabled != null ? monitorEnabled : true;
	}
	
	public boolean isMonitorParamEnabled() {
		if (!isHawkApiValid) {
			return false;
		}
		if (monitorParamEnalbedByJmx != null) {
			return monitorParamEnalbedByJmx;
		}
		Boolean monitorParamEnabled = null;
		if (lionInterface != null) {
			try {
				monitorParamEnabled = ConfigCache.getInstance().getBooleanProperty(MONITOR_PARAM_ENABLED_KEY);
			} catch (Throwable e) {
			}
		}
		return monitorParamEnabled != null ? monitorParamEnabled : true;
	}

	public int getMonitorParamThreshold() {
		Integer monitorParamThreshold = null;
		if (lionInterface != null) {
			try {
				monitorParamThreshold = ConfigCache.getInstance().getIntProperty(MONITOR_PARAM_THRESHOLD);
			} catch (Throwable e) {
			}
		}
		return monitorParamThreshold != null ? monitorParamThreshold : -1;
	}
	
	public void setMonitorEnabledByJmx() {
		this.monitorEnabledByJmx = true;
	}
	
	public void setMonitorDisabledByJmx() {
		this.monitorEnabledByJmx = false;
	}
	
	public void clearMonitorControlByJmx() {
		this.monitorEnabledByJmx = null;
	}
	
	public void setMonitorParamEnabledByJmx() {
		this.monitorParamEnalbedByJmx = true;
	}
	
	public void setMonitorParamDisabledByJmx() {
		this.monitorParamEnalbedByJmx = false;
	}
	
	public void clearMonitorParamControlByJmx() {
		this.monitorParamEnalbedByJmx = null;
	}

}
