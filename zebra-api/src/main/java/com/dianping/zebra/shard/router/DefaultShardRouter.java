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
import com.dianping.zebra.shard.exception.SQLParseException;
import com.dianping.zebra.shard.exception.ShardRouterException;
import com.dianping.zebra.shard.parser.DefaultSQLRewrite;
import com.dianping.zebra.shard.parser.SQLParsedResult;
import com.dianping.zebra.shard.parser.SQLParser;
import com.dianping.zebra.shard.parser.SQLRewrite;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.ShardEvalContext;
import com.dianping.zebra.shard.router.rule.ShardEvalResult;
import com.dianping.zebra.shard.router.rule.TableShardRule2;

/**
 * @author hao.zhu
 */
public class DefaultShardRouter implements ShardRouter {

	private SQLRewrite sqlRewrite = new DefaultSQLRewrite();

	private RouterRule routerRule;

	public DefaultShardRouter(RouterRule routerRule) {
		this(routerRule, new DefaultSQLRewrite());
	}

	public DefaultShardRouter(RouterRule routerRule, SQLRewrite sqlRewrite) {
		this.routerRule = routerRule;
		this.sqlRewrite = sqlRewrite;
	}

	@Override
	public RouterResult router(final String sql, List<Object> params) throws ShardRouterException, SQLParseException {
		RouterResult routerResult = new RouterResult();
		SQLParsedResult parsedResult = SQLParser.parse(sql);

		TableShardRule2 tableShardRule = findShardRule(parsedResult.getRouterContext(), params);
		ShardEvalResult shardResult = tableShardRule.eval(new ShardEvalContext(parsedResult, params));

		routerResult.setMergeContext(parsedResult.getMergeContext());
		routerResult.setSqls(buildSqls(shardResult.getDbAndTables(), parsedResult, tableShardRule.getTableName()));
		routerResult.setParams(buildParams(params, parsedResult, routerResult));

		return routerResult;
	}

	@Override
	public boolean validate(String sql) throws SQLParseException, ShardRouterException {
		return false;
	}

	@Override
	public RouterRule getRouterRule() {
		return this.routerRule;
	}

	private TableShardRule2 findShardRule(RouterContext context, List<Object> params) throws ShardRouterException {
		Map<String, TableShardRule2> tableShardRules = this.routerRule.getTableShardRules();
		TableShardRule2 tableShardRule = null;

		int matchedTimes = 0;
		for (String relatedTable : context.getTableSet()) {
			TableShardRule2 tmp = tableShardRules.get(relatedTable);
			if (tmp != null) {
				tableShardRule = tmp;
				matchedTimes++;
			}

			if (matchedTimes > 1) {
				throw new ShardRouterException("More than one table shard rules is not supported now.");
			}
		}

		if (tableShardRule == null) {
			throw new ShardRouterException("No table shard rule can be found for table " + context.getTableSet());
		}

		return tableShardRule;
	}

	private List<RouterTarget> buildSqls(Map<String, Set<String>> dbAndTables, SQLParsedResult parseResult,
			String logicTable) {
		List<RouterTarget> sqls = new ArrayList<RouterTarget>();

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

			sqls.add(targetedSql);
		}

		return sqls;
	}

	// TODO maybe consider putting into merge later
	private List<Object> buildParams(List<Object> params, SQLParsedResult parseResult, RouterResult rr) {
		List<Object> newParams = null;
		if (params != null) {
			newParams = new ArrayList<Object>(params);
			Limit limitExpr = parseResult.getMergeContext().getLimitExpr();
			if (limitExpr != null) {
				int offset = Integer.MIN_VALUE;
				if (limitExpr.getOffset() instanceof SQLVariantRefExpr) {
					SQLVariantRefExpr ref = (SQLVariantRefExpr) limitExpr.getOffset();
					offset = (Integer) newParams.get(ref.getIndex());
					newParams.set(ref.getIndex(), new Integer(0));
					rr.getMergeContext().setOffset(offset);
				}

				if (limitExpr.getRowCount() instanceof SQLVariantRefExpr) {
					SQLVariantRefExpr ref = (SQLVariantRefExpr) limitExpr.getRowCount();
					int limit = (Integer) newParams.get(ref.getIndex());
					if (offset != Integer.MIN_VALUE) {
						newParams.set(ref.getIndex(), offset + limit);
					}

					rr.getMergeContext().setLimit(limit);
				}
			}
		}

		return newParams;
	}
}
