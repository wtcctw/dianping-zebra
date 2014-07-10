/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-10-30
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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author danson.liu
 *
 */
public final class DefaultConnectionMetaAnalyzer implements ConnectionMetaAnalyzer {
	
	private static final ConnectionMetaAnalyzer 				INSTANCE 			= new DefaultConnectionMetaAnalyzer();
	
	private static final ConcurrentMap<String, String[]>		CACHE				= new ConcurrentHashMap<String, String[]>();	
	
	private DefaultConnectionMetaAnalyzer() {}

	@Override
	public String[] getDbAndSchema(Connection innerConnection) {
		try {
			DatabaseMetaData metaData = innerConnection.getMetaData();
			if (metaData != null) {
				String url = metaData.getURL();
				if (url != null) {
					String[] dbAndSchema = CACHE.get(url);
					if (dbAndSchema != null && dbAndSchema.length > 0) {
						return dbAndSchema;
					} else if (dbAndSchema != null && dbAndSchema.length == 0) {
						throw new RuntimeException("JdbcUrl[" + url +"] not supported by sql monitor temporarily.");
					}
					if (url.contains("mysql")) {
						dbAndSchema = analyzeMysqlUrl(url);
						CACHE.putIfAbsent(url, dbAndSchema);
						return dbAndSchema;
					} else if (url.contains("sqlserver")) {
						dbAndSchema = analyzeSqlServerUrl(url);
						CACHE.putIfAbsent(url, dbAndSchema);
						return dbAndSchema;
					} else if (url.contains("h2")) {
						dbAndSchema = analyzeH2Url(url);
						CACHE.putIfAbsent(url, dbAndSchema);
						return dbAndSchema;
					} else {
						CACHE.putIfAbsent(url, new String[0]);
						throw new RuntimeException("JdbcUrl[" + url +"] not supported by sql monitor temporarily.");
					}
					
				}
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException("GetDbAndSchema from connection object failed.", e);
		}
	}

	private String[] analyzeH2Url(String url) {
		int index = url.indexOf("://");
		String ipAndPort;
		String schema;
		if (index != -1) {
			String temp = url.substring(index + 3);
			index = temp.indexOf("/");
			ipAndPort = temp.substring(0, index);
			index = temp.lastIndexOf("/");
			int index2 = temp.indexOf(";");
			schema = temp.substring(index + 1, index2 != -1 ? index2 : temp.length());
		} else {
			ipAndPort = "h2db";
			schema = "h2schema";
		}
		return new String[] {ipAndPort, schema};
	}

	private String[] analyzeSqlServerUrl(String url) {
		int index = url.indexOf("://");
		String temp = url.substring(index + 3);
		index = temp.indexOf(";");
		String ipAndPort = temp.substring(0, index);
		String dbNameParam = "DatabaseName=";
		index = temp.indexOf(dbNameParam);
		if (index == -1) {
			dbNameParam = "databaseName=";
			index = temp.indexOf(dbNameParam);
		}
		int index2 = temp.indexOf(";", index);
		String schema = temp.substring(index + dbNameParam.length(), index2 != -1 ? index2 : temp.length());
		return new String[] {ipAndPort, schema};
	}

	private String[] analyzeMysqlUrl(String url) {
		int index = url.indexOf("://");
		String temp = url.substring(index + 3);
		index = temp.indexOf("/");
		String ipAndPort = temp.substring(0, index);
		int index2 = temp.indexOf("?");
		String schema = temp.substring(index + 1, index2 != -1 ? index2 : temp.length());
		return new String[] {ipAndPort, schema};
	}

	@Override
	public int getDSIdentity(Connection innerConnection) {
		String connectionClazz = innerConnection.getClass().getName();
		if (connectionClazz.contains("c3p0")) {
			return DS_C3P0;
		}
		if (connectionClazz.contains("dpdl")) {
			return DS_DPDL;
		}
		if (connectionClazz.contains("zebra")) {
			return DS_ZEBRA;
		}
		return DS_UNKNOWN;
	}

	public static ConnectionMetaAnalyzer getInstance() {
		return INSTANCE;
	}
	
}
