/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-11-5
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

import java.util.List;

import com.dianping.hawk.protocol.Messages.SqlMsg;
import com.dianping.hawk.protocol.Messages.SqlMsg.Builder;

/**
 * @author danson.liu
 *
 */
public class SqlMsgBuilder {
	
	private Builder builder;

	private SqlMsgBuilder() {
		builder = SqlMsg.newBuilder();
	}
	
	public static SqlMsgBuilder newBuilder() {
		return new SqlMsgBuilder();
	}
	
	public SqlMsgBuilder setToken(String token) {
		if (token != null) {
			builder.setToken(token);
		}
		return this;
	}
	
	public SqlMsgBuilder setSql(String sql) {
		if (sql != null) {
			builder.setSql(sql);
		}
		return this;
	}
	
	public String getSql() {
		return builder.getSql();
	}
	
	public SqlMsgBuilder setCategory(String category) {
		if (category != null) {
			builder.setAlias(category);
		}
		return this;
		
	}
	
	public SqlMsgBuilder setDspath(int dspath) {
		builder.setDspath(dspath);
		return this;
	}
	
	public int getDspath() {
		return builder.getDspath();
	}
	
	public SqlMsgBuilder setCount(int count) {
		builder.setCount(count);
		return this;
	}
	
	public SqlMsgBuilder setTimeCost(int timeCost) {
		builder.setTimeCost(timeCost);
		return this;
	}
	
	public SqlMsgBuilder setWhen(long when) {
		builder.setWhen(when);
		return this;
	}
	
	public SqlMsgBuilder setServer(String server) {
		if (server != null) {
			builder.setServer(server);
		}
		return this;
	}
	
	public SqlMsgBuilder setDb(String db) {
		if (db != null) {
			builder.setDb(db);
		}
		return this;
	}
	
	public String getDb() {
		return builder.getDb();
	}
	
	public SqlMsgBuilder setSchema(String schema) {
		if (schema != null) {
			builder.setSchema(schema);
		}
		return this;
	}
	
	public String getSchema() {
		return builder.getSchema();
	}
	
	public SqlMsgBuilder addParams(String paramVal) {
		if (paramVal == null) {
			paramVal = "null";
		}
		builder.addParams(paramVal);
		return this;
	}
	
	public SqlMsgBuilder addAllSubSqls(Iterable<? extends SqlMsg> values) {
		builder.addAllSubSqls(values);
		return this;
	}
	
	public List<SqlMsg> getSubSqlsList() {
		return builder.getSubSqlsList();
	}
	
	public SqlMsgBuilder addSubSqls(SqlMsg subSql) {
		if (subSql != null) {
			builder.addSubSqls(subSql);
		}
		return this;
	}
	
	public SqlMsgBuilder clearSubSqls() {
		builder.clearSubSqls();
		return this;
	}
	
	public SqlMsg build() {
		return builder.build();
	}

}
