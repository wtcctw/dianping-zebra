/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-17
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
package com.dianping.zebra.shard.router.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author danson.liu
 *
 */
public class ShardMatchResult {
	
	//private String basedColumn;
	
	private Map<String, Set<String>> dbAndTables = new HashMap<String, Set<String>>(4);
	private boolean dbAndTablesSetted;
	
	/**
	 * 附属维度涉及的库和表(目前采取异步消息的方式发出，由专门的server去执行数据同步任务)
	 */
	private Map<String, Set<String>> subDBAndTables = new HashMap<String, Set<String>>(4);
	
	private Map<String, Set<String>> potentialDBAndTbs = new HashMap<String, Set<String>>(4);
	private boolean potentialDBAndTbsSetted;

	public void addDBAndTable(String dataSource, String table) {
		if (!dbAndTables.containsKey(dataSource)) {
			dbAndTables.put(dataSource, new HashSet<String>());
		}
		dbAndTables.get(dataSource).add(table);
	}
	
	public void addDBAndTables(Map<String, Set<String>> dbAndTables) {
		for (Entry<String, Set<String>> entry : dbAndTables.entrySet()) {
			String db = entry.getKey();
			if (!this.dbAndTables.containsKey(db)) {
				this.dbAndTables.put(db, new HashSet<String>());
			}
			this.dbAndTables.get(db).addAll(entry.getValue());
		}
	}
	
	public Map<String, Set<String>> getDbAndTables() {
		return dbAndTables;
	}

	public void setDbAndTables(Map<String, Set<String>> dbAndTables) {
		this.dbAndTables = dbAndTables;
	}

	public boolean isDbAndTbsEmpty() {
		return dbAndTables.isEmpty();
	}

	public void addSubDBAndTables(Map<String, Set<String>> dbAndTables) {
		for (Entry<String, Set<String>> entry : dbAndTables.entrySet()) {
			String db = entry.getKey();
			if (!subDBAndTables.containsKey(db)) {
				subDBAndTables.put(db, new HashSet<String>());
			}
			subDBAndTables.get(db).addAll(entry.getValue());
		}
	}
	
	public void addSubDBAndTable(String dataSource, String table) {
		if (!subDBAndTables.containsKey(dataSource)) {
			subDBAndTables.put(dataSource, new HashSet<String>());
		}
		subDBAndTables.get(dataSource).add(table);
	}

	public Map<String, Set<String>> getSubDBAndTables() {
		return subDBAndTables;
	}

	boolean isDbAndTablesSetted() {
		return dbAndTablesSetted;
	}

	void setDbAndTablesSetted(boolean dbAndTablesSetted) {
		this.dbAndTablesSetted = dbAndTablesSetted;
	}

	Map<String, Set<String>> getPotentialDBAndTbs() {
		return potentialDBAndTbs;
	}

	void setPotentialDBAndTbs(Map<String, Set<String>> potentialDBAndTbs) {
		this.potentialDBAndTbs = potentialDBAndTbs;
	}

	boolean isPotentialDBAndTbsSetted() {
		return potentialDBAndTbsSetted;
	}

	void setPotentialDBAndTbsSetted(boolean potentialDBAndTbsSetted) {
		this.potentialDBAndTbsSetted = potentialDBAndTbsSetted;
	}

//	public String getBasedColumn() {
//		return basedColumn;
//	}
//
//	public void setBasedColumn(String basedColumn) {
//		this.basedColumn = basedColumn;
//	}
}
