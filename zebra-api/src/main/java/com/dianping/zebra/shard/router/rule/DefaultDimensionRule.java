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
package com.dianping.zebra.shard.router.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.router.rule.ShardEvalContext.ColumnValue;
import com.dianping.zebra.shard.router.rule.engine.GroovyRuleEngine;
import com.dianping.zebra.shard.router.rule.engine.RuleEngine;
import com.dianping.zebra.shard.router.rule.mapping.TableMappingManager;
import com.dianping.zebra.shard.router.rule.mapping.TableMapping;
import com.dianping.zebra.shard.router.rule.mapping.SimpleTableMappingManager;

/**
 * 
 * @author hao.zhu
 *
 */
public class DefaultDimensionRule extends AbstractDimensionRule {

	private RuleEngine dbRuleEngine;

	private RuleEngine tableRuleEngine;

	private List<DimensionRule> whiteListRules;

	private TableMappingManager tablesMappingManager;

	private Map<String, Set<String>> allDBAndTables = new HashMap<String, Set<String>>();

	public DefaultDimensionRule(TableShardDimensionConfig dimensionConfig) {
		this.isMaster = dimensionConfig.isMaster();
		this.needSync = dimensionConfig.isNeedSync();
		this.tablesMappingManager = new SimpleTableMappingManager(dimensionConfig.getTableName(),
				dimensionConfig.getDbIndexes(), dimensionConfig.getTbSuffix());
		this.allDBAndTables.putAll(this.tablesMappingManager.getAllMappings());
		this.dbRuleEngine = new GroovyRuleEngine(dimensionConfig.getDbRule());
		this.tableRuleEngine = new GroovyRuleEngine(dimensionConfig.getTbRule());
		this.initShardColumn(dimensionConfig.getDbRule());
	}

	@Override
	public Map<String, Set<String>> getAllDBAndTables() {
		return allDBAndTables;
	}

	@Override
	public ShardEvalResult eval(ShardEvalContext matchContext) {
		ShardEvalResult result = new ShardEvalResult();

		// eval whitelist first
		for (DimensionRule dimensionRule : this.whiteListRules) {
			ShardEvalResult eval = dimensionRule.eval(matchContext);

			for (Entry<String, Set<String>> entry : eval.getDbAndTables().entrySet()) {
				String db = entry.getKey();
				for (String table : entry.getValue()) {
					result.addDbAndTable(db, table);
				}
			}
		}

		for (ColumnValue evalContext : matchContext.getColumnValues()) {
			if (!evalContext.isUsed()) {
				evalContext.setUsed(true);

				Number dbPos = (Number) dbRuleEngine.eval(evalContext.getValue());
				TableMapping dataSource = tablesMappingManager.getTableMappingByIndex(dbPos.intValue());
				Number tablePos = (Number) tableRuleEngine.eval(evalContext.getValue());
				String table = dataSource.getTables().get(tablePos.intValue());

				result.addDbAndTable(dataSource.getDbIndex(), table);
			}
		}

		return result;
	}

	public void setExceptionalRules(List<DimensionRule> whiteListRules) {
		this.whiteListRules = whiteListRules;
		
		for (DimensionRule whiteListRule : this.whiteListRules) {
			Map<String, Set<String>> whiteListDBAndTables = whiteListRule.getAllDBAndTables();
			for (Entry<String, Set<String>> allDBAndTable : whiteListDBAndTables.entrySet()) {
				String db = allDBAndTable.getKey();
				if (!allDBAndTables.containsKey(db)) {
					allDBAndTables.put(db, new HashSet<String>());
				}
				allDBAndTables.get(db).addAll(allDBAndTable.getValue());
			}
		}
	}

	@Override
	public Set<String> getShardColumns() {
		return this.shardColumns;
	}
}
