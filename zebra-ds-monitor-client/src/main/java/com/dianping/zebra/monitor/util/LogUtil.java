/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-10-29
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
package com.dianping.zebra.monitor.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dianping.hawk.Hawk;

/**
 * @author danson.liu
 *
 */
public class LogUtil {
	

	private static final 	Log 		logger 					= LogFactory.getLog(LogUtil.class);
	
	private static 			long 		logFactor1;
	private static 			long 		logFactor2;
	public static final 	int 		LOG_FACTOR_ROUND_VALUE 	= 300;
	
	private static final 	String		MONITOR_ERR_MSG 		= "Monitor sql execution failed.";		
	
	public static void logMonitorError(Throwable throwable) {
		try {
			if (logFactor1++ % LOG_FACTOR_ROUND_VALUE == 0) {
				logger.error(MONITOR_ERR_MSG, throwable);
				Hawk.log("sql", "monitor", null, null, throwable.getClass().getName(), MONITOR_ERR_MSG, getErrorString(throwable));
				logFactor1 = 1;
			}
		} catch (Throwable throwable2) {
			//do nothing...
		}
	}
	
	public static void logMonitorWarn(String msg, Throwable throwable) {
		try {
			if (logFactor2++ % LOG_FACTOR_ROUND_VALUE == 0) {
				if (throwable != null) {
					logger.warn(msg, throwable);
				} else {
					logger.warn(msg);
				}
				logFactor2 = 1;
			}
		} catch (Throwable e) {
			//do nothing...
		}
	}

	private static String getErrorString(Throwable throwable) {
		OutputStream out = null;
		PrintWriter writer = null;
		try {
			out = new ByteArrayOutputStream(3000);
			writer = new PrintWriter(out);
			throwable.printStackTrace(writer);
			writer.flush();
			return out.toString();
		} catch (Exception e2) {
			return throwable.getMessage();
		} finally {
			if (writer != null) {
				writer.close();
			}
			out = null;
			writer = null;
		}
	}
	
}
