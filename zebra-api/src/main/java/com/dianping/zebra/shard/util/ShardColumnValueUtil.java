/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 *
 * File Created at 2011-6-17
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
package com.dianping.zebra.shard.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLInListExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.expr.SQLValuableExpr;
import com.alibaba.druid.sql.ast.expr.SQLVariantRefExpr;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement.ValuesClause;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlDeleteStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlUpdateStatement;
import com.dianping.zebra.shard.exception.ShardRouterException;
import com.dianping.zebra.shard.parser.SQLParsedResult;
import com.dianping.zebra.shard.router.rule.ShardEvalContext;
import com.dianping.zebra.shard.router.rule.ShardEvalContext.ColumnValue;
import com.dianping.zebra.util.SqlType;

/**
 * 计算相关DML语句对于指定的column可能的值
 *
 * @author hao.zhu
 */
public class ShardColumnValueUtil {

	public static List<ColumnValue> eval(ShardEvalContext ctx, Set<String> shardColumns) {
		List<ColumnValue> result = new LinkedList<ColumnValue>();

		Map<Integer, Map<String, Object>> tmpResult = new LinkedHashMap<Integer, Map<String, Object>>();
		for (String shardColumn : shardColumns) {
			Set<Object> columnValues = eval(ctx.getParseResult(), ctx.getParams(), shardColumn);
			int index = 0;
			for (Object o : columnValues) {
				Map<String, Object> map = tmpResult.get(index);

				if (map == null) {
					map = new HashMap<String, Object>();
					tmpResult.put(index, map);
				}

				map.put(shardColumn, o);
				index++;
			}
		}

		for (Map<String, Object> columnValues : tmpResult.values()) {
			ColumnValue columnValue = new ColumnValue(columnValues);
			result.add(columnValue);
		}

		return result;
	}

	private static Set<Object> eval(SQLParsedResult parseResult, List<Object> params, String column) {
		if (parseResult.getType() == SqlType.INSERT) {
			return evalInsert(parseResult, column, params);
		}

		Set<Object> result = new LinkedHashSet<Object>();
		// TODO handle table in the future
		SQLExpr where = getWhere(parseResult);
		if (where != null) {
			LinkedHashMap<String, Object> pairs = new LinkedHashMap<String, Object>();

			eval(where, column, pairs);

			for (Entry<String, Object> pair : pairs.entrySet()) {
				String identifier = pair.getKey();
				Object value = pair.getValue();

				if (evalColumn(identifier, column)) {
					if (value instanceof SQLValuableExpr) {
						result.add(((SQLValuableExpr) value).getValue());
					} else if (value instanceof SQLVariantRefExpr) {
						SQLVariantRefExpr ref = (SQLVariantRefExpr) value;
						result.add(params.get(ref.getIndex()));
					}else if(value instanceof SQLInListExpr){
						SQLInListExpr inListExpr = (SQLInListExpr)value;
						
						for(SQLExpr expr : inListExpr.getTargetList()){
							if(expr instanceof SQLValuableExpr){
								result.add(((SQLValuableExpr) expr).getValue());
							}else{
								SQLVariantRefExpr ref = (SQLVariantRefExpr) expr;
								result.add(params.get(ref.getIndex()));
							}
						}
					}
				}
			}
		}

		return result;
	}

	private static SQLExpr getWhere(SQLParsedResult parseResult) {
		SQLExpr expr = null;
		SQLStatement stmt = parseResult.getStmt();

		if (parseResult.getType() == SqlType.SELECT) {
			MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) (((SQLSelectStatement) stmt).getSelect()).getQuery();
			expr = query.getWhere();
		} else if (parseResult.getType() == SqlType.UPDATE) {
			expr = ((MySqlUpdateStatement) stmt).getWhere();
		} else if (parseResult.getType() == SqlType.DELETE) {
			expr = ((MySqlDeleteStatement) stmt).getWhere();
		}

		return expr;
	}

	private static void eval(SQLExpr sqlExpr, String column, LinkedHashMap<String, Object> pairs) {
		SQLBinaryOpExpr where = null;
		if (sqlExpr instanceof SQLBinaryOpExpr) {
			where = (SQLBinaryOpExpr) sqlExpr;
		} else if(sqlExpr instanceof SQLInListExpr){
			SQLInListExpr inListExpr = (SQLInListExpr)sqlExpr;
			SQLIdentifierExpr indentifier = (SQLIdentifierExpr) inListExpr.getExpr();
			pairs.put(indentifier.getName(), inListExpr);
			return;
		}else {
			return;
		}

		SQLBinaryOperator operator = where.getOperator();

		if (operator != SQLBinaryOperator.Equality) {
			eval(where.getLeft(), column, pairs);
			eval(where.getRight(), column, pairs);

			return;
		} else {
			SQLExpr left = where.getLeft();
			SQLExpr right = where.getRight();

			if (left instanceof SQLIdentifierExpr) {
				SQLIdentifierExpr indentifier = (SQLIdentifierExpr) left;
				pairs.put(indentifier.getName(), right);
			} else if (left instanceof SQLPropertyExpr) {
				SQLPropertyExpr indentifier = (SQLPropertyExpr) left;
				pairs.put(indentifier.getName(), right);
			} else if (left instanceof SQLBinaryOpExpr) {
				eval((SQLBinaryOpExpr) left, column, pairs);
				eval((SQLBinaryOpExpr) right, column, pairs);
			}
		}
	}

	private static boolean evalColumn(String indentifier, String column) {
		return column.equals(indentifier) || ("`" + column + "`").equals(indentifier);
	}

	private static Set<Object> evalInsert(SQLParsedResult parseResult, String column, List<Object> params) {
		Set<Object> evalSet = new LinkedHashSet<Object>();
		MySqlInsertStatement stmt = (MySqlInsertStatement) parseResult.getStmt();

		List<SQLExpr> columns = stmt.getColumns();
		List<ValuesClause> valuesList = stmt.getValuesList();

		if (valuesList.size() > 1) {
			throw new ShardRouterException("Multipal rows insertion is currently unsupported!");
		}

		ValuesClause values = valuesList.get(0);
		for (int i = 0; i < columns.size(); i++) {
			SQLName columnObj = (SQLName) columns.get(i);
			if (evalColumn(columnObj.getSimpleName(), column)) {
				SQLExpr sqlExpr = values.getValues().get(i);
				if (sqlExpr instanceof SQLVariantRefExpr) {
					evalSet.add(params.get(i));
				} else if (sqlExpr instanceof SQLValuableExpr) {
					evalSet.add(((SQLValuableExpr) sqlExpr).getValue());
				}
				break;
			}
		}
		if (evalSet.isEmpty()) {
			throw new ShardRouterException("Router column[" + column + "] not found in the sql!");
		}

		return evalSet;
	}
}
