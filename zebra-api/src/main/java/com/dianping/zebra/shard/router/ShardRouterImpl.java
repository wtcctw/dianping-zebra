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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.antlr.runtime.RecognitionException;

import com.dianping.zebra.shard.jdbc.util.LRUCache;
import com.dianping.zebra.shard.parser.qlParser.DPMySQLParser;
import com.dianping.zebra.shard.parser.sqlParser.DMLCommon;
import com.dianping.zebra.shard.parser.sqlParser.Insert;
import com.dianping.zebra.shard.parser.sqlParser.Select;
import com.dianping.zebra.shard.parser.sqlParser.mySql.MyDelete;
import com.dianping.zebra.shard.parser.sqlParser.mySql.MySelect;
import com.dianping.zebra.shard.parser.sqlParser.mySql.MyUpdate;
import com.dianping.zebra.shard.parser.sqlParser.mySql.MyWhereCondition;
import com.dianping.zebra.shard.parser.valueObject.variable.BindVar;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.ShardMatchResult;
import com.dianping.zebra.shard.router.rule.TableShardRule;

/**
 * @author damon.zhu
 */
public class ShardRouterImpl implements ShardRouter {

	private RouterRule routerRule;

	private Map<String, DMLCommon> sqlParseCache = Collections.synchronizedMap(new LRUCache<String, DMLCommon>(1000));

	private static final String SKIP_MAX_STUB = "?";

	private List<RouterTarget> createTargetedSqls(Map<String, Set<String>> dbAndTables, boolean acrossTable, String sql,
	      DMLCommon dmlSql, String logicTable, int skip, int max) {
		sql = reconstructSqlLimit(acrossTable, sql, dmlSql, skip, max);

		List<RouterTarget> result = new ArrayList<RouterTarget>();
		for (Entry<String, Set<String>> entry : dbAndTables.entrySet()) {
			RouterTarget targetedSql = new RouterTarget(entry.getKey());

			for (String physicalTable : entry.getValue()) {
				targetedSql.addSql(replaceSqlTableName(sql, dmlSql, logicTable, physicalTable));
			}

			result.add(targetedSql);
		}

		return result;
	}

	private RouterResult enrichRouterTarget(RouterResult target, DMLCommon dmlSql, String generatedPK, int skip, int max) {
		target.setColumns(dmlSql instanceof Select ? ((Select) dmlSql).getColumns() : null);
		target.setGroupBys(dmlSql instanceof Select ? ((Select) dmlSql).getWhere().getGroupByColumns() : null);
		target.setHasDistinct(dmlSql instanceof Select ? ((Select) dmlSql).hasDistinct() : false);
		target.setOrderBys(dmlSql.getOrderByList());
		target.setGeneratedPK(generatedPK);
		target.setSkip(skip);
		target.setMax(max);

		return target;
	}

	private MyWhereCondition.LimitInfo getLimitInfo(DMLCommon dmlSql) {
		MyWhereCondition where = null;
		if (dmlSql instanceof MySelect) {
			where = (MyWhereCondition) ((MySelect) dmlSql).getWhere();
		} else if (dmlSql instanceof MyUpdate) {
			where = (MyWhereCondition) ((MyUpdate) dmlSql).getWhere();
		} else if (dmlSql instanceof MyDelete) {
			where = (MyWhereCondition) ((MyDelete) dmlSql).getWhere();
		}
		return where != null ? where.limitInfo : null;
	}

	private TableShardRule findTableShardRule(Set<String> relatedTables) throws ShardRouterException {
		Map<String, TableShardRule> tableShardRules = routerRule.getTableShardRules();
		int matchedTimes = 0;
		TableShardRule matchedTableShardRule = null;

		for (String relatedTable : relatedTables) {
			TableShardRule tmp = tableShardRules.get(relatedTable);
			if (tmp != null) {
				matchedTableShardRule = tmp;
				matchedTimes++;
			}
		}

		if (matchedTimes > 1) {
			throw new ShardRouterException("Matched more than one table shard rules is not supported now.");
		}

		if (matchedTableShardRule == null) {
			throw new ShardRouterException("Cannot find any table shard rule for table " + relatedTables);
		} else {
			return matchedTableShardRule;
		}
	}

	@Override
	public RouterRule getRouterRule() {
		return this.routerRule;
	}

	@Override
	public void init() {
	}

	private DMLCommon parseSql(String sql) throws RecognitionException, IOException {
		DMLCommon cachedResult = sqlParseCache.get(sql);

		if (cachedResult != null) {
			return cachedResult;
		} else {
			DMLCommon parsedResult = DPMySQLParser.parse(sql).obj;
			sqlParseCache.put(sql, parsedResult);

			return parsedResult;
		}
	}

	private List<Object> reconstructParams(List<Object> params, boolean acrossTable, DMLCommon dmlSql, int skip, int max) {
		List<Object> newParams = null;
		if (params != null) {
			newParams = new ArrayList<Object>(params);
			//处理limit的参数
			if (acrossTable && !(dmlSql instanceof Insert) && max != RouterResult.NO_MAX && skip > 0) {
				MyWhereCondition where = (MyWhereCondition) ((MySelect) dmlSql).getWhere();
				Object start = where.getStart();
				if (start instanceof BindVar) {
					int index = ((BindVar) start).getIndex();
					newParams.set(index, 0);
				}
				Object range = where.getRange();
				if (range instanceof BindVar) {
					int index = ((BindVar) range).getIndex();
					newParams.set(index, max != -1 ? skip + max : -1);
				}
			}
		}
		
		return newParams;
	}

	private String reconstructSqlLimit(boolean acrossTable, String sql, DMLCommon dmlSql, int skip, int max) {
		// 有limit又有groupby的情况，那么需要去掉limit。by Leo Liang
		// Note: 其实应该加上一个过滤条件，groupby里面没有分区键
		if ((dmlSql instanceof Select) && acrossTable && (max != RouterResult.NO_MAX || skip != RouterResult.NO_SKIP)) {
			List<String> groupByColumns = ((Select) dmlSql).getWhere().getGroupByColumns();

			if (groupByColumns != null && groupByColumns.size() > 0) {
				int pos = sql.toLowerCase().lastIndexOf(" limit ");

				if (pos > 0) {
					return sql.substring(0, pos);
				} else {
					return sql;
				}
			}
		}

		// 没有groupby的情况
		if (acrossTable && !(dmlSql instanceof Insert) && max != RouterResult.NO_MAX && skip > 0) {
			MyWhereCondition.LimitInfo limitInfo = getLimitInfo(dmlSql);

			int skipLen = String.valueOf(skip).length();
			int maxLen = String.valueOf(max).length();
			boolean skipChanged = false;
			if (!SKIP_MAX_STUB.equals(limitInfo.skip)) {
				sql = sql.substring(0, limitInfo.skipIdx) + 0 + sql.substring(limitInfo.skipIdx + skipLen);
				skipChanged = true;
			}
			if (!SKIP_MAX_STUB.equals(limitInfo.range)) {
				int rangeIdx = skipChanged ? limitInfo.rangeIdx - (skipLen - 1) : limitInfo.rangeIdx;
				sql = sql.substring(0, rangeIdx) + (skip + max) + sql.substring(rangeIdx + maxLen);
			}
		}

		return sql;
	}

	private String replaceSqlTableName(String sql, DMLCommon dmlSql, String logicTable, String physicalTable) {
		List<Integer> tablePosList = dmlSql.getTablePos(logicTable);
		int tableLen = logicTable.length();

		for (Integer tablePos : tablePosList) {
			sql = sql.substring(0, tablePos) + physicalTable + sql.substring(tablePos + tableLen);
		}

		return sql;
	}

	@Override
	public RouterResult router(String sql, List<Object> params) throws ShardRouterException {
		try {
			RouterResult target = new RouterResult();
			DMLCommon dmlSql = parseSql(sql);
			int skip = dmlSql.getSkip(params);
			int max = dmlSql.getMax(params);
			Set<String> relatedTables = dmlSql.getRelatedTables();
			TableShardRule tableShardRule = findTableShardRule(relatedTables);

			ShardMatchResult result = tableShardRule.eval(dmlSql, params);
			Map<String, Set<String>> dbAndTables = result.getDbAndTables();
			boolean acrossTable = dbAndTables.size() > 1 || dbAndTables.entrySet().iterator().next().getValue().size() > 1;
			target.setTargetedSqls(createTargetedSqls(dbAndTables, acrossTable, sql, dmlSql,
			      tableShardRule.getTableName(), skip, max));
			target.setNewParams(reconstructParams(params, acrossTable, dmlSql, skip, max));

			return enrichRouterTarget(target, dmlSql, tableShardRule == null ? null : tableShardRule.getGeneratedPK(),
			      skip, max);
		} catch (Exception e) {
			throw new ShardRouterException("DataSource route failed, cause: ", e);
		}
	}

	public void setRouterRule(RouterRule routerRule) {
		this.routerRule = routerRule;
	}

	@Override
	public boolean validate(String sql) throws SyntaxException, ShardRouterException {
		DMLCommon dmlSql = null;

		try {
			dmlSql = parseSql(sql);
		} catch (Exception e) {
			throw new SyntaxException(e);
		}

		if (dmlSql != null) {
			Set<String> relatedTables = dmlSql.getRelatedTables();
			TableShardRule tableShardRule = findTableShardRule(relatedTables);

			if (tableShardRule == null) {
				throw new ShardRouterException("Cannot find any Shard Rule for table " + relatedTables);
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
}
