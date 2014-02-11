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
package com.dianping.zebra.router.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.dianping.zebra.router.config.ExceptionConfig;
import com.dianping.zebra.router.rule.engine.GroovyRuleEngine;
import com.dianping.zebra.router.rule.engine.RuleEngine;
import com.dianping.zebra.router.rule.engine.RuleEngineEvalContext;

/**
 * @author danson.liu
 *
 */
public class WhitelistDimensionRule extends AbstractDimensionRule {

	private String dataSource;
	
	private String table;
	
	private RuleEngine ruleEngine;
	
	public void init(ExceptionConfig exceptionConfig) {
		this.dataSource = exceptionConfig.getDb();
		this.table = exceptionConfig.getTable();
		String condition = exceptionConfig.getCondition();
		this.ruleEngine = new GroovyRuleEngine(condition);
		this.initBasedColumns(condition);
	}

	@Override
	public boolean match(ShardMatchContext matchContext) {
		ShardMatchResult matchResult = matchContext.getMatchResult();
		Set<Object> colValues = matchContext.getColValues();
		String basedColumn = basedColumns.iterator().next();
		for (Iterator<Object> iter = colValues.iterator(); iter.hasNext();) {
			//new map every time, for concurrent execute later
			Object colVal = iter.next();
			Map<String, Object> valMap = new HashMap<String, Object>();
			valMap.put(basedColumn, colVal);
			if ((Boolean) ruleEngine.eval(new RuleEngineEvalContext(valMap))) {
				colValues.remove(colVal);
				if (matchResult.isDbAndTablesSetted()) {
					matchResult.addSubDBAndTable(dataSource, table);
				} else {
					matchResult.addDBAndTable(dataSource, table);
				}
			}
		}
		return true;
	}

	@Override
	public Map<String, Set<String>> getAllDBAndTables() {
		Map<String, Set<String>> dbAndTables = new HashMap<String, Set<String>>(1);
		Set<String> tableSet = new HashSet<String>(1);
		tableSet.add(table);
		dbAndTables.put(dataSource, tableSet);
		return dbAndTables;
	}

}
