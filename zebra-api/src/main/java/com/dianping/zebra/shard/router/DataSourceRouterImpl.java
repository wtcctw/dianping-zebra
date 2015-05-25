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
import org.antlr.runtime.RecognitionException;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author danson.liu
 */
public class DataSourceRouterImpl implements DataSourceRouter {

	private RouterRule routerRule;

	private Map<String, DataSource> dataSourcePool;

	private DataSourceRepository dataSourceRepository;

	private Map<String, DMLCommon> sqlParseCache = Collections.synchronizedMap(new LRUCache<String, DMLCommon>(1000));

	private static final String SKIP_MAX_STUB = "?";

	@Override
	public RouterTarget getTarget(String sql, List<Object> params) throws DataSourceRouteException {
		try {
			RouterTarget target = new RouterTarget();
			DMLCommon dmlSql = parseSqlClause(sql);
			int skip = dmlSql.getSkip(params);
			int max = dmlSql.getMax(params);
			Set<String> relatedTables = dmlSql.getRelatedTables();
			TableShardRule tableShardRule = getAppliedShardRule(relatedTables);
			
			if (tableShardRule != null) {
				ShardMatchResult matchResult = tableShardRule.match(dmlSql, params);
				Map<String, Set<String>> dbAndTables = matchResult.getDbAndTables();
				boolean acrossTable = dbAndTables.size() > 1
				      || dbAndTables.entrySet().iterator().next().getValue().size() > 1;
				target.setTargetedSqls(createTargetedSqls(dbAndTables, acrossTable, sql, dmlSql,
				      tableShardRule.getTableName(), skip, max));
				target.setNewParams(reconstructParams(params, acrossTable, dmlSql, skip, max));
			} else {
				throw new DataSourceRouteException("Cannot find any Shard Rule for table " + relatedTables);
			}

			return enrichRouterTarget(target, dmlSql, tableShardRule == null ? null : tableShardRule.getGeneratedPK(),
			      skip, max);
		} catch (Exception e) {
			throw new DataSourceRouteException("DataSource route failed, cause: ", e);
		}
	}

	private DMLCommon parseSqlClause(String sql) throws RecognitionException, IOException {
		DMLCommon cachedResult = sqlParseCache.get(sql);
		if (cachedResult != null) {
			return cachedResult;
		}
		DMLCommon parsedResult = DPMySQLParser.parse(sql).obj;
		sqlParseCache.put(sql, parsedResult);
		return parsedResult;
	}

	private RouterTarget enrichRouterTarget(RouterTarget target, DMLCommon dmlSql, String generatedPK, int skip, int max) {
		target.setColumns(dmlSql instanceof Select ? ((Select) dmlSql).getColumns() : null);
		target.setGroupBys(dmlSql instanceof Select ? ((Select) dmlSql).getWhere().getGroupByColumns() : null);
		target.setHasDistinct(dmlSql instanceof Select ? ((Select) dmlSql).hasDistinct() : false);
		target.setOrderBys(dmlSql.getOrderByList());
		target.setGeneratedPK(generatedPK);
		target.setSkip(skip);
		target.setMax(max);
		return target;
	}

	private List<TargetedSql> createTargetedSqls(Map<String, Set<String>> dbAndTables, boolean acrossTable, String sql,
	      DMLCommon dmlSql, String table, int skip, int max) {

		Map<String, TargetedSql> targetedSqlMap = new HashMap<String, TargetedSql>();
		sql = reconstructSqlLimit(acrossTable, sql, dmlSql, skip, max);

		for (Entry<String, Set<String>> entry : dbAndTables.entrySet()) {
			NamedDataSource dataSource = getDataSource(entry.getKey(), dmlSql);
			if (!targetedSqlMap.containsKey(dataSource.identity)) {
				targetedSqlMap.put(dataSource.identity, new TargetedSql(dataSource));
			}
			TargetedSql targetedSql = targetedSqlMap.get(dataSource.identity);
			for (String physicalTable : entry.getValue()) {
				targetedSql.addSql(reconstructSql(sql, dmlSql, table, physicalTable));
			}
		}
		return new ArrayList<TargetedSql>(targetedSqlMap.values());
	}

	private String reconstructSql(String sql, DMLCommon dmlSql, String table, String physicalTable) {
		List<Integer> tablePosList = dmlSql.getTablePos(table);
		int tableLen = table.length();
		for (Integer tablePos : tablePosList) {
			sql = sql.substring(0, tablePos) + physicalTable + sql.substring(tablePos + tableLen);
		}
		return sql;
	}

	private String reconstructSqlLimit(boolean acrossTable, String sql, DMLCommon dmlSql, int skip, int max) {
		// 如果既有limit又有groupby，那么需要去掉limit。by Leo Liang
		// Note: 其实应该加上一个过滤条件，groupby里面没有分区键
		if ((dmlSql instanceof Select) && acrossTable && (max != RouterTarget.NO_MAX || skip != RouterTarget.NO_SKIP)) {
			if (((Select) dmlSql).getWhere().getGroupByColumns() != null
			      && !((Select) dmlSql).getWhere().getGroupByColumns().isEmpty()) {
				return sql.substring(0, sql.toLowerCase().lastIndexOf(" limit "));
			}
		}

		if (acrossTable && !(dmlSql instanceof Insert) && max != RouterTarget.NO_MAX && skip > 0) {
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

	private List<Object> reconstructParams(List<Object> params, boolean acrossTable, DMLCommon dmlSql, int skip, int max) {
		List<Object> newParams = null;
		if (params != null) {
			newParams = new ArrayList<Object>(params);
			if (acrossTable && !(dmlSql instanceof Insert) && max != RouterTarget.NO_MAX && skip > 0) {
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

	private NamedDataSource getDataSource(String dsName, DMLCommon dmlSql) {
		return dataSourceRepository.getDataSource(dsName);
	}

	private TableShardRule getAppliedShardRule(Set<String> relatedTables) throws DataSourceRouteException {
		Map<String, TableShardRule> shardRules = new HashMap<String, TableShardRule>(5);
		Map<String, TableShardRule> tableShardRules = routerRule.getTableShardRules();
		for (String relatedTable : relatedTables) {
			TableShardRule tableShardRule = tableShardRules.get(relatedTable);
			if (tableShardRule != null) {
				shardRules.put(relatedTable, tableShardRule);
			}
		}
		if (shardRules.size() > 1) {
			throw new DataSourceRouteException("Sql contains more than one shard-related table is not supported now.");
		}
		return !shardRules.isEmpty() ? shardRules.values().iterator().next() : null;
	}

	@Override
	public void setDataSourcePool(Map<String, DataSource> dataSourcePool) {
		this.dataSourcePool = dataSourcePool;
	}

	@Override
	public void init() {
		dataSourceRepository = new LocalDataSourceRepository(dataSourcePool);
	}

	public void setRouterRule(RouterRule routerRule) {
		this.routerRule = routerRule;
	}

}
