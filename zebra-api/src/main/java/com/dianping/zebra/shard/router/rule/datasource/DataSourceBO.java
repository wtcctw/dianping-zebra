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
package com.dianping.zebra.shard.router.rule.datasource;

import java.util.ArrayList;
import java.util.List;

import com.dianping.zebra.shard.router.rule.engine.RuleEngine;
import com.dianping.zebra.shard.router.rule.engine.RuleEngineEvalContext;

/**
 * @author danson.liu
 * 
 */
public class DataSourceBO {

	private final String dbIndex;

	private List<String> physicalTables = new ArrayList<String>(80);

	private RuleEngine tableRuleEngine;

	public DataSourceBO(String dbIndex) {
		this.dbIndex = dbIndex;
	}

	public void addPhysicalTables(String tableName) {
		this.physicalTables.add(tableName);
	}

	public List<String> getPhysicalTables() {
		return physicalTables;
	}

	public void setTableRuleEngine(RuleEngine tableRuleEngine) {
		this.tableRuleEngine = tableRuleEngine;
	}

	public String getDbIndex() {
		return dbIndex;
	}

	public String evalTable(RuleEngineEvalContext context) {
		Number tablePos = (Number) tableRuleEngine.eval(context);
		return physicalTables.get(tablePos.intValue());
	}
}
