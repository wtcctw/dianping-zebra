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
package com.dianping.zebra.shard.router;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.druid.sql.ast.expr.SQLVariantRefExpr;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock.Limit;
import com.dianping.zebra.shard.exception.ShardRouterException;
import com.dianping.zebra.shard.exception.ZebraParseException;
import com.dianping.zebra.shard.parser.DefaultSQLRewrite;
import com.dianping.zebra.shard.parser.MySQLParser;
import com.dianping.zebra.shard.parser.MySQLParseResult;
import com.dianping.zebra.shard.parser.SQLRewrite;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.ShardMatchResult;
import com.dianping.zebra.shard.router.rule.TableShardRule;

/**
 * @author damon.zhu
 */
public class DefaultShardRouter implements ShardRouter {

	private RouterRule routerRule;

	private TableShardRuleMarcher tableShardRuleMarcher;

	private SQLRewrite sqlRewrite;

	@Override
	public RouterRule getRouterRule() {
		return this.routerRule;
	}

	@Override
	public RouterResult router(final String sql, List<Object> params) throws ShardRouterException, ZebraParseException {
		RouterResult routerResult = new RouterResult();

		MySQLParseResult parseResult = MySQLParser.parse(sql);

		// 3. router
		TableShardRule tableShardRule = tableShardRuleMarcher.march(this.routerRule, parseResult, params);
		ShardMatchResult shardResult = tableShardRule.eval(parseResult, params);

		// 4. re-write target sql & build router result
		boolean acrossTable = isCrossTable(shardResult.getDbAndTables());

		routerResult.setAcrossTable(acrossTable);
		routerResult.setTargetedSqls(createTargetedSqls(shardResult.getDbAndTables(), acrossTable, parseResult,
				tableShardRule.getTableName()));
		routerResult.setNewParams(reconstructParams(params, acrossTable, parseResult, routerResult));

		return enrichRouterResult(routerResult, parseResult,
				tableShardRule == null ? null : tableShardRule.getGeneratedPK());
	}

	public void setRouterRule(RouterRule routerRule) {
		this.routerRule = routerRule;
		// TODO improve it
		this.tableShardRuleMarcher = new DefaultTableShardRuleMarcher();
		this.sqlRewrite = new DefaultSQLRewrite();
	}

	@Override
	public boolean validate(String sql) throws ZebraParseException, ShardRouterException {
		return false;
	}

	private List<RouterTarget> createTargetedSqls(Map<String, Set<String>> dbAndTables, boolean acrossTable,
			MySQLParseResult parseResult, String logicTable) {
		List<RouterTarget> result = new ArrayList<RouterTarget>();
		for (Entry<String, Set<String>> entry : dbAndTables.entrySet()) {
			RouterTarget targetedSql = new RouterTarget(entry.getKey());

			for (String physicalTable : entry.getValue()) {
				String _sql = sqlRewrite.rewrite(parseResult, physicalTable);

				String hintComment = parseResult.getRouterContext().getHintComment();
				if (hintComment != null) {
					targetedSql.addSql(hintComment + _sql);
				} else {
					targetedSql.addSql(_sql);
				}
			}

			result.add(targetedSql);
		}

		return result;
	}

	private RouterResult enrichRouterResult(RouterResult result, MySQLParseResult parserResult, String generatedPK) {
		if (result.getSkip() == Integer.MIN_VALUE) {
			result.setSkip(parserResult.getMergeContext().getOffset());
		}
		if (result.getMax() == Integer.MAX_VALUE) {
			result.setMax(parserResult.getMergeContext().getLimit());
		}

		result.setGeneratedPK(generatedPK);
		result.setOrderBy(parserResult.getMergeContext().getOrderBy());
		result.setSelectLists(parserResult.getMergeContext().getSelectLists());
		result.setGroupBys(parserResult.getMergeContext().getGroupByColumns());
		result.setHasDistinct(parserResult.getMergeContext().isDistinct());

		return result;
	}

	private boolean isCrossTable(Map<String, Set<String>> dbAndTables) {
		return dbAndTables.size() > 1 || dbAndTables.entrySet().iterator().next().getValue().size() > 1;
	}

	private List<Object> reconstructParams(List<Object> params, boolean acrossTable, MySQLParseResult parseResult,
			RouterResult rr) {
		List<Object> newParams = null;
		if (params != null) {
			newParams = new ArrayList<Object>(params);
			Limit limitExpr = parseResult.getMergeContext().getLimitExpr();
			if (limitExpr != null) {
				int offset = Integer.MIN_VALUE;
				if (limitExpr.getOffset() instanceof SQLVariantRefExpr) {
					SQLVariantRefExpr ref = (SQLVariantRefExpr) limitExpr.getOffset();
					offset = (Integer) newParams.get(ref.getIndex());
					newParams.set(ref.getIndex(), new Integer(0));// TODO
																	// ,improve
																	// it
																	// ,performance
																	// issue
					rr.setSkip(offset);
				}

				if (limitExpr.getRowCount() instanceof SQLVariantRefExpr) {
					SQLVariantRefExpr ref = (SQLVariantRefExpr) limitExpr.getRowCount();
					int limit = (Integer) newParams.get(ref.getIndex());
					newParams.set(ref.getIndex(), offset + limit);// TODO
																	// ,improve
																	// it
																	// ,performance
																	// issue
					rr.setMax(limit);
				}
			}
		}

		return newParams;
	}
}
