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
import com.dianping.zebra.shard.parser.MySQLParserResult;
import com.dianping.zebra.shard.parser.SQLRewrite;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.ShardMatchResult;
import com.dianping.zebra.shard.router.rule.TableShardRule;

/**
 * @author damon.zhu
 */
public class ShardRouterImpl implements ShardRouter {

	private RouterRule routerRule;

	private TableShardRuleMarcher tableShardRuleMarcher;
	
	private SQLRewrite sqlRewrite;

//	private final String SKIP_MAX_STUB = "?";

	// private final Map<String, PreProcessedSql> preParsedSqlCache =
	// Collections
	// .synchronizedMap(new LRUCache<String, PreProcessedSql>(1000));
	//
	// private final Pattern pattern = Pattern.compile("\\n|`");

	@Override
	public RouterRule getRouterRule() {
		return this.routerRule;
	}

	@Override
	public RouterResult router(final String sql, List<Object> params) throws ShardRouterException, ZebraParseException {
		RouterResult routerResult = new RouterResult();

		// 1. pre-process sql
		// PreProcessedSql preProcessedSql = preProcess(sql);
		//
		// // 2. parse sql
		// DMLCommon dmlSql = null;
		// try {
		// dmlSql = parseSql(preProcessedSql.getPreParsedSql());
		// } catch (Exception e) {
		// throw new ShardRouterException(e);
		// }

		MySQLParserResult parseResult = MySQLParser.parse(sql);

		// 3. router
		TableShardRule tableShardRule = tableShardRuleMarcher.march(this.routerRule, parseResult, params);
		ShardMatchResult shardResult = tableShardRule.eval(parseResult, params);

		// 4. re-write target sql & build router result
		boolean acrossTable = isCrossTable(shardResult.getDbAndTables());

		routerResult.setAcrossTable(acrossTable);
		routerResult.setTargetedSqls(createTargetedSqls(shardResult.getDbAndTables(), acrossTable,
				parseResult, tableShardRule.getTableName()));
		routerResult.setNewParams(reconstructParams(params, acrossTable, parseResult, routerResult));

		return enrichRouterResult(routerResult, parseResult, tableShardRule == null ? null : tableShardRule.getGeneratedPK());
	}

	public void setRouterRule(RouterRule routerRule) {
		this.routerRule = routerRule;
		// TODO improve it
		this.tableShardRuleMarcher = new DefaultTableShardRuleMarcher();
		this.sqlRewrite = new DefaultSQLRewrite();
	}

	@Override
	public boolean validate(String sql) throws ZebraParseException, ShardRouterException {
//		DMLCommon dmlSql = null;
//
//		try {
//			dmlSql = parseSql(sql);
//		} catch (Exception e) {
//			throw new SyntaxException(e);
//		}
//
//		if (dmlSql != null) {
//			Set<String> relatedTables = dmlSql.getRelatedTables();
//			TableShardRule tableShardRule = findTableShardRule(relatedTables);
//
//			if (tableShardRule == null) {
//				throw new ShardRouterException("Cannot find any Shard Rule for table " + relatedTables);
//			} else {
//				return true;
//			}
//		} else {
//			return false;
//		}
		
		return false;
	}

	private List<RouterTarget> createTargetedSqls(Map<String, Set<String>> dbAndTables, boolean acrossTable,MySQLParserResult parseResult, String logicTable) {
//		String tmpSql = reconstructSqlLimit(acrossTable, parseResult, skip, max);

		List<RouterTarget> result = new ArrayList<RouterTarget>();
		for (Entry<String, Set<String>> entry : dbAndTables.entrySet()) {
			RouterTarget targetedSql = new RouterTarget(entry.getKey());

			for (String physicalTable : entry.getValue()) {
				String _sql = sqlRewrite.rewrite(parseResult, physicalTable);
//				String _sql = replaceSqlTableName(tmpSql, parseResult, logicTable, physicalTable);

				if (parseResult.getHintComment() != null) {
					targetedSql.addSql(parseResult.getHintComment() + _sql);
				} else {
					targetedSql.addSql(_sql);
				}
			}

			result.add(targetedSql);
		}

		return result;
	}

	private RouterResult enrichRouterResult(RouterResult result, MySQLParserResult parserResult, String generatedPK) {
//		result.setColumns(dmlSql instanceof Select ? ((Select) dmlSql).getColumns() : null);
//		result.setGroupBys(dmlSql instanceof Select ? ((Select) dmlSql).getWhere().getGroupByColumns() : null);
//		result.setHasDistinct(dmlSql instanceof Select ? ((Select) dmlSql).hasDistinct() : false);
//		result.setOrderBys(dmlSql.getOrderByList());
		if(result.getSkip() == Integer.MIN_VALUE){
			result.setSkip(parserResult.getOffset());
		}
		if(result.getMax() == Integer.MAX_VALUE){
			result.setMax(parserResult.getLimit());
		}
		
		result.setGeneratedPK(generatedPK);
		result.setOrderBy(parserResult.getOrderBy());
		result.setSelectLists(parserResult.getSelectLists());
		result.setGroupBys(parserResult.getGroupByColumns());
		result.setHasDistinct(parserResult.distinct());

		return result;
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

			if (matchedTimes > 1) {
				throw new ShardRouterException("Matched more than one table shard rules is not supported now.");
			}
		}

		if (matchedTableShardRule == null) {
			throw new ShardRouterException("Cannot find any table shard rule for table " + relatedTables);
		}

		return matchedTableShardRule;
	}

//	private MyWhereCondition.LimitInfo getLimitInfo(DMLCommon dmlSql) {
//		MyWhereCondition where = null;
//
//		if (dmlSql instanceof MySelect) {
//			where = (MyWhereCondition) ((MySelect) dmlSql).getWhere();
//		} else if (dmlSql instanceof MyUpdate) {
//			where = (MyWhereCondition) ((MyUpdate) dmlSql).getWhere();
//		} else if (dmlSql instanceof MyDelete) {
//			where = (MyWhereCondition) ((MyDelete) dmlSql).getWhere();
//		}
//
//		return where != null ? where.limitInfo : null;
//	}

	private boolean isCrossTable(Map<String, Set<String>> dbAndTables) {
		return dbAndTables.size() > 1 || dbAndTables.entrySet().iterator().next().getValue().size() > 1;
	}

//	private DMLCommon parseSql(final String sql) throws RecognitionException, IOException {
//		DMLCommon cachedResult = parsedSqlCache.get(sql);
//
//		if (cachedResult != null) {
//			return cachedResult;
//		} else {
//			DMLCommon parsedResult = MySQLParser.parse(sql).obj;
//			parsedSqlCache.put(sql, parsedResult);
//
//			return parsedResult;
//		}
//	}

	// private PreProcessedSql preProcess(final String sql) {
	// PreProcessedSql preProcessedSql = preParsedSqlCache.get(sql);
	//
	// if (preProcessedSql == null) {
	// String realSql = pattern.matcher(sql).replaceAll("");
	// if (realSql.endsWith(";")) {
	// realSql = realSql.substring(0, realSql.length() - 1);
	// }
	//
	// String comment = SqlUtils.parseSqlComment(realSql);
	// realSql = StringUtils.stripComments(realSql, "'\"", "'\"", true, false,
	// true, true).trim();
	// preProcessedSql = new PreProcessedSql();
	// preProcessedSql.setComment(comment);
	// preProcessedSql.setFormattedSql(realSql);
	//
	// preParsedSqlCache.put(sql, preProcessedSql);
	// }
	//
	// return preProcessedSql;
	// }

	private List<Object> reconstructParams(List<Object> params, boolean acrossTable, MySQLParserResult parseResult,RouterResult rr) {
		List<Object> newParams = null;
		if (params != null) {
			newParams = new ArrayList<Object>(params);
			Limit limitExpr = parseResult.getLimitExpr();
			if (limitExpr != null) {
				int offset = Integer.MIN_VALUE;
				if(limitExpr.getOffset() instanceof SQLVariantRefExpr){
					SQLVariantRefExpr ref = (SQLVariantRefExpr)limitExpr.getOffset();
					offset = (Integer)newParams.get(ref.getIndex());
					newParams.set(ref.getIndex(), new Integer(0));//TODO ,improve it ,performance issue
					rr.setSkip(offset);
				}
				
				if(limitExpr.getRowCount() instanceof SQLVariantRefExpr){
					SQLVariantRefExpr ref = (SQLVariantRefExpr)limitExpr.getRowCount();
					int limit = (Integer)newParams.get(ref.getIndex());
					newParams.set(ref.getIndex(), offset + limit);//TODO ,improve it ,performance issue
					rr.setMax(limit);
				}
			}
			
			// 处理limit的参数
//			if (acrossTable && !parseResult.isInsert() && max != RouterResult.NO_MAX && skip > 0) {
//				MyWhereCondition where = (MyWhereCondition) ((MySelect) dmlSql).getWhere();
//				Object start = where.getStart();
//				if (start instanceof BindVar) {
//					int index = ((BindVar) start).getIndex();
//					newParams.set(index, 0);
//				}
//				Object range = where.getRange();
//				if (range instanceof BindVar) {
//					int index = ((BindVar) range).getIndex();
//					newParams.set(index, max != -1 ? skip + max : -1);
//				}
//			}
		}

		return newParams;
	}

	private String reconstructSqlLimit(boolean acrossTable, MySQLParserResult parseResult, int skip, int max) {
		// 有limit又有groupby的情况，那么需要去掉limit。by Leo Liang
		// Note: 其实应该加上一个过滤条件，groupby里面没有分区键
//		if (parseResult.isSelect() && acrossTable && (max != RouterResult.NO_MAX || skip != RouterResult.NO_SKIP)) {
//			List<String> groupByColumns = parseResult.getGroupByColumns();

//			if (groupByColumns != null && groupByColumns.size() > 0) {
//				int pos = sql.toLowerCase().lastIndexOf(" limit ");
//
//				if (pos > 0) {
//					return sql.substring(0, pos);
//				} else {
//					return sql;
//				}
//			}
//		}

		// 没有groupby的情况
//		if (acrossTable && !parseResult.isInsert() && max != RouterResult.NO_MAX && skip > 0) {
//			MyWhereCondition.LimitInfo limitInfo = getLimitInfo(parseResult);
//
//			int skipLen = String.valueOf(skip).length();
//			int maxLen = String.valueOf(max).length();
//			boolean skipChanged = false;
//			if (!SKIP_MAX_STUB.equals(limitInfo.skip)) {
//				sql = sql.substring(0, limitInfo.skipIdx) + 0 + sql.substring(limitInfo.skipIdx + skipLen);
//				skipChanged = true;
//			}
//			if (!SKIP_MAX_STUB.equals(limitInfo.range)) {
//				int rangeIdx = skipChanged ? limitInfo.rangeIdx - (skipLen - 1) : limitInfo.rangeIdx;
//				sql = sql.substring(0, rangeIdx) + (skip + max) + sql.substring(rangeIdx + maxLen);
//			}
//		}

//		return sql;
		return null;
	}

//	private String replaceSqlTableName(String sql, MySQLParserResult parseResult, String logicTable, String physicalTable) {
//		List<Integer> tablePosList = parseResult.getTablePos(logicTable);
//		int tableLen = logicTable.length();
//
//		for (Integer tablePos : tablePosList) {
//			sql = sql.substring(0, tablePos) + physicalTable + sql.substring(tablePos + tableLen);
//		}
//
//		return sql;
//	}

	class PreProcessedSql {
		private String preParsedSql;

		private String comment;

		public String getComment() {
			return comment;
		}

		public String getPreParsedSql() {
			return preParsedSql;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public void setFormattedSql(String preParsedSql) {
			this.preParsedSql = preParsedSql;
		}
	}
}
