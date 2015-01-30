/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-14
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
package com.dianping.zebra.shard.router.config;

import java.util.List;

/**
 * @author danson.liu
 *
 */
public class TableShardDimensionConfig {
	
	private String dbRule;
	
	private String dbIndexes;
	
	private String tbRule;
	
	private String tbSuffix;
	
	private boolean isMaster;
	
	private List<ExceptionConfig> exceptions;

	private String tableName;

	public String getDbRule() {
		return dbRule;
	}

	public void setDbRule(String dbRule) {
		this.dbRule = dbRule;
	}

	public String getDbIndexes() {
		return dbIndexes;
	}

	public void setDbIndexes(String dbIndexes) {
		this.dbIndexes = dbIndexes;
	}

	public String getTbRule() {
		return tbRule;
	}

	public void setTbRule(String tbRule) {
		this.tbRule = tbRule;
	}

	public String getTbSuffix() {
		return tbSuffix;
	}

	public void setTbSuffix(String tbSuffix) {
		this.tbSuffix = tbSuffix;
	}

	public boolean isMaster() {
		return isMaster;
	}

	public void setMaster(boolean isMaster) {
		this.isMaster = isMaster;
	}

	public List<ExceptionConfig> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<ExceptionConfig> exceptions) {
		this.exceptions = exceptions;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}
	
}
