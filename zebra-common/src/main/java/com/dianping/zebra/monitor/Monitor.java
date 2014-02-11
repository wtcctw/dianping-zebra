/**
 * Project: ${zebra-common.aid}
 * 
 * File Created at 2011-7-28
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
package com.dianping.zebra.monitor;

import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 监控事件发布类
 * 
 * @author Leo Liang
 * 
 */
public class Monitor {
	private static final Logger	commonLog		= Logger.getLogger("commonLog");
	private static final Logger	errorLog		= Logger.getLogger("errorLogger");
	private static final Logger	performanceLog	= Logger.getLogger("performanceLog");

	public static void notifyCommonEvent(String msg, Map<String, Object> params) {
		StringBuilder s = msg == null ? new StringBuilder("common event monitor log.") : new StringBuilder(msg);
		if (params != null) {
			s.append(" Params: ");
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				s.append(" ").append(entry.getKey()).append(": ").append(entry.getValue());
			}
		}
		commonLog.info(s.toString());
	}

	public static void notifyErrorEvent(String msg, Throwable e, Map<String, Object> params) {
		StringBuilder s = msg == null ? new StringBuilder("error event monitor log.") : new StringBuilder(msg);
		if (params != null) {
			s.append(" Params: ");
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				s.append(" ").append(entry.getKey()).append(": ").append(entry.getValue());
			}
		}
		if (e != null) {
			errorLog.error(s.toString(), e);
		} else {
			errorLog.error(s.toString());
		}
	}

	public static void notifyPerformanceEvent(long timeMillSecs, Map<String, Object> params) {
		StringBuilder s = new StringBuilder("[Zebra Performance]Performance monitor log. Time Elapsed: ").append(timeMillSecs)
				.append("ms");
		if (params != null) {
			s.append(" Context: ");
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				s.append(" ").append(entry.getKey()).append(": ").append(entry.getValue());
			}
		}
		performanceLog.info(s.toString());
	}

}
