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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.exception.ShardRouterException;
import com.dianping.zebra.shard.router.rule.engine.GroovyRuleEngine;
import com.dianping.zebra.shard.router.rule.engine.RuleEngine;
import com.dianping.zebra.shard.router.rule.engine.RuleEngineEvalContext;

public class DimensionRuleImpl extends AbstractDimensionRule {

	private String tableName;

	private RuleEngine ruleEngine;

	private List<DimensionRule> whiteListRules;

	private DataSourceProvider dataSourceProvider;

	private Map<String, Set<String>> allDBAndTables = new HashMap<String, Set<String>>();

	public Map<String, Set<String>> getAllDBAndTables() {
		return allDBAndTables;
	}

	public DataSourceProvider getDataSourceProvider() {
		return this.dataSourceProvider;
	}

	public RuleEngine getRuleEngine() {
		return ruleEngine;
	}

	public List<DimensionRule> getWhiteListRules() {
		return whiteListRules;
	}

	public void init(TableShardDimensionConfig dimensionConfig) {
		this.isMaster = dimensionConfig.isMaster();
		this.needSync = dimensionConfig.isNeedSync();
		this.tableName = dimensionConfig.getTableName();
		this.dataSourceProvider = new SimpleDataSourceProvider(dimensionConfig.getTableName(),
				dimensionConfig.getDbIndexes(), dimensionConfig.getTbSuffix(), dimensionConfig.getTbRule());
		this.allDBAndTables.putAll(this.dataSourceProvider.getAllDBAndTables());

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
		this.ruleEngine = new GroovyRuleEngine(dimensionConfig.getDbRule());
		this.initShardColumn(dimensionConfig.getDbRule());
	}

	@Override
	public boolean match(ShardMatchContext matchContext) {
		ShardMatchResult matchResult = matchContext.getMatchResult();
		List<Map<String, Object>> colValues = new LinkedList<Map<String, Object>>();

		for (String shardColumn : shardColumns) {
			// Insert必须要有主维度，否则这里抛错
			Set<Object> shardColValues = ShardColumnValueUtil.eval(matchContext.getParseResult(), tableName,
					shardColumn, matchContext.getParams());

			if (shardColumns.size() > 1 && shardColValues.size() > 1) {
				throw new ShardRouterException("Under multiple shard columns, more than one value for column["
						+ shardColumn + "] is not supported!");
			}

			for (Object col : shardColValues) {
				if (shardColumns.size() == 1) {
					Map<String, Object> cols = new HashMap<String, Object>();
					cols.put(shardColumn, col);

					colValues.add(cols);
				} else {
					if (colValues.isEmpty()) {
						Map<String, Object> cols = new HashMap<String, Object>();
						cols.put(shardColumn, col);

						colValues.add(cols);
					} else {
						Map<String, Object> cols = colValues.get(0);
						cols.put(shardColumn, col);
					}
				}
			}
		}

		boolean isSelect = matchContext.getParseResult().isSelect();
		if (colValues.isEmpty()) {
			if (matchContext.getParseResult().isInsert()) {
				throw new ShardRouterException("Router failed since there is no shard column value found.");
			}

			if (!isSelect && isMaster) {
				// 如果Update和Delete没有设置master维度，则设置主路由的所有表
				matchResult.setDbAndTables(allDBAndTables);
				matchResult.setDbAndTablesSetted(true);

				return false;
			} else {
				// Select没有带任何维度
				if (!matchResult.isPotentialDBAndTbsSetted()) {
					matchResult.setPotentialDBAndTbs(allDBAndTables);
					matchResult.setPotentialDBAndTbsSetted(true);
				}

				return true;
			}
		} else {
			matchContext.setColValues(colValues);

			for (DimensionRule whiteListRule : whiteListRules) {
				whiteListRule.match(matchContext);
			}

			if (!matchResult.isDbAndTablesSetted()) {
				for (Iterator<Map<String, Object>> iterator = colValues.iterator(); iterator.hasNext();) {
					RuleEngineEvalContext context = new RuleEngineEvalContext(iterator.next());

					Number dbPos = (Number) ruleEngine.eval(context);
					DataSourceBO dataSource = dataSourceProvider.getDataSource(dbPos.intValue());
					String table = dataSource.evalTable(context);

					matchResult.addDBAndTable(dataSource.getDbIndex(), table);
				}

				matchResult.setDbAndTablesSetted(true);
			}

			return !isSelect;
		}
	}

	public void setRuleEngine(RuleEngine ruleEngine) {
		this.ruleEngine = ruleEngine;
	}

	public void setWhiteListRules(List<DimensionRule> whiteListRules) {
		this.whiteListRules = whiteListRules;
	}
}
