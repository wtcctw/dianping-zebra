/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-15
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
package com.dianping.zebra.shard.router.rule.mapping;

import java.util.ArrayList;
import java.util.List;

import com.dianping.zebra.shard.router.rule.engine.RuleEngine;
import com.dianping.zebra.shard.router.rule.engine.RuleEngineEvalContext;

/**
 * @author hao.zhu
 * 
 */
public class DBTablesMapping {

	private final String dbIndex;

	private List<String> tables = new ArrayList<String>(32);

	private RuleEngine tableRuleEngine;

	public DBTablesMapping(String dbIndex) {
		this.dbIndex = dbIndex;
	}

	public void addTables(String tableName) {
		this.tables.add(tableName);
	}

	public List<String> getTables() {
		return tables;
	}

	public void setTableRuleEngine(RuleEngine tableRuleEngine) {
		this.tableRuleEngine = tableRuleEngine;
	}

	public String getDbIndex() {
		return dbIndex;
	}

	public String evalTable(RuleEngineEvalContext context) {
		Number tablePos = (Number) tableRuleEngine.eval(context);
		return tables.get(tablePos.intValue());
	}
}
