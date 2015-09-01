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
package com.dianping.zebra.shard.router.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.dianping.zebra.shard.jdbc.util.CollectionUtils;
import com.dianping.zebra.shard.parser.condition.ComparableAndInExpression;
import com.dianping.zebra.shard.parser.condition.Comparative;
import com.dianping.zebra.shard.parser.condition.Expression;
import com.dianping.zebra.shard.parser.condition.ExpressionGroup;
import com.dianping.zebra.shard.parser.condition.OrExpressionGroup;
import com.dianping.zebra.shard.parser.condition.WhereCondition;
import com.dianping.zebra.shard.parser.sqlParser.Column;
import com.dianping.zebra.shard.parser.sqlParser.DMLCommon;
import com.dianping.zebra.shard.parser.sqlParser.Delete;
import com.dianping.zebra.shard.parser.sqlParser.Insert;
import com.dianping.zebra.shard.parser.sqlParser.Select;
import com.dianping.zebra.shard.parser.sqlParser.Update;
import com.dianping.zebra.shard.parser.tableObject.TableName;
import com.dianping.zebra.shard.parser.tableObject.imp.TableNameSubQueryImp;
import com.dianping.zebra.shard.parser.valueObject.ValueObject;
import com.dianping.zebra.shard.router.ShardRouterException;

/**
 * 计算相关DML语句对于指定的column可能的值
 *
 * @author danson.liu
 */
public class ShardColumnValueUtil {

	public static Set<Object> eval(DMLCommon dmlSql, String table, String column, List<Object> params) {
		if (dmlSql instanceof Insert) {
			return evalInsert((Insert) dmlSql, column, params);
		}

		List<OrExpressionGroup> expGroups = buildExpression(dmlSql, table);
		if (expGroups == null || expGroups.isEmpty()) {
			return null;
		}

		EvalContext context = buildEvalContext(dmlSql, table, column, params);

		if (expGroups.size() == 1) {
			return eval(expGroups.get(0), context);
		}

		Expression exp = expGroups.get(0);
		for (int i = 1; i < expGroups.size() - 1; i++) {
			exp = new ExpressionGroup(exp, expGroups.get(i));
		}

		return evalAnd(exp, expGroups.get(expGroups.size() - 1), context);
	}

	private static EvalContext buildEvalContext(DMLCommon dmlSql, String table, String column, List<Object> params) {
		Set<String> tableAliases = dmlSql.getTableAliases(table);
		tableAliases.add(table);

		return new EvalContext(tableAliases, column, params);
	}

	private static List<OrExpressionGroup> buildExpression(DMLCommon dmlSql, String table) {
		WhereCondition whereCondition = null;

		if (dmlSql instanceof Select) {
			whereCondition = ((Select) dmlSql).getWhere();
		} else if (dmlSql instanceof Update) {
			whereCondition = ((Update) dmlSql).getWhere();
		} else if (dmlSql instanceof Delete) {
			whereCondition = ((Delete) dmlSql).getWhere();
		}
		List<OrExpressionGroup> expGroups = new ArrayList<OrExpressionGroup>();
		
		for (TableName tbName : dmlSql.getTbNames()) {
			if (tbName instanceof TableNameSubQueryImp && ((TableNameSubQueryImp) tbName).containsTable(table)) {
				expGroups.add(((TableNameSubQueryImp) tbName).getSubSelect().getWhere().getExpGroup());
			}
		}
		
		expGroups.add(whereCondition.getExpGroup());

		return expGroups;
	}

	private static Set<Object> evalAnd(Expression expLeft, Expression expRight, EvalContext context) {
		Set<Object> leftResult = eval(expLeft, context);
		Set<Object> rightResult = eval(expRight, context);

		return intersection(leftResult, rightResult);
	}

	private static Set<Object> evalOr(Expression expLeft, Expression expRight, EvalContext context) {
		Set<Object> leftResult = eval(expLeft, context);
		Set<Object> rightResult = eval(expRight, context);
		
		return union(leftResult, rightResult);
	}

	private static Set<Object> eval(Expression expression, EvalContext context) {
		if (expression instanceof OrExpressionGroup) {
			OrExpressionGroup orExpression = ((OrExpressionGroup) expression);
			List<Expression> expressions = orExpression.getExpressions();
			if (expressions == null || expressions.isEmpty()) {
				return Collections.emptySet();
			}
			if (expressions.size() == 1) {
				return eval(expressions.get(0), context);
			}
			
			return evalOr(expressions.get(0), expressions.get(1), context);
		}
		
		if (expression instanceof ExpressionGroup) {
			ExpressionGroup andExpression = ((ExpressionGroup) expression);
			List<Expression> expressions = andExpression.getExpressions();
			if (expressions == null || expressions.isEmpty()) {
				return Collections.emptySet();
			}
			if (expressions.size() == 1) {
				return eval(expressions.get(0), context);
			}
			
			return evalAnd(expressions.get(0), expressions.get(1), context);
		}
		
		if (expression instanceof ComparableAndInExpression) {
			Set<Comparative> evalSet = ((ComparableAndInExpression) expression).eval(context.tableAliases, context.column,
			      context.params);
			/*
			 * 暂时只考虑EQ条件参与路由判断,以后有按照值范围分库分表策略时再考虑诸如>,>=,<,<=等情况，这些条件 牵扯到相互之间的merge，相对复杂，需要时再添加进来
			 */
			return filterEQ(evalSet);
		}
		
		return Collections.emptySet();
	}

	@SuppressWarnings("unchecked")
	private static Set<Object> intersection(Set<Object> leftResult, Set<Object> rightResult) {
		if (leftResult.isEmpty()) {
			return rightResult;
		}
		if (rightResult.isEmpty()) {
			return leftResult;
		}
		
		return CollectionUtils.intersection(leftResult, rightResult);
	}

	private static Set<Object> union(Set<Object> leftResult, Set<Object> rightResult) {
		if (leftResult.isEmpty() || rightResult.isEmpty()) {
			return Collections.emptySet();
		}
		leftResult.addAll(rightResult);
		
		return leftResult;
	}

	private static Set<Object> filterEQ(Set<Comparative> comparatives) {
		if (comparatives.isEmpty()) {
			return Collections.emptySet();
		}

		Set<Object> filtered = new LinkedHashSet<Object>();

		for (Comparative comparative : comparatives) {
			if (comparative.getComparison() == Comparative.Equivalent) {
				filtered.add(comparative.getValue());
			}
		}

		return filtered;
	}

	private static Set<Object> evalInsert(Insert insertSql, String column, List<Object> params) {
		Set<Object> evalSet = new LinkedHashSet<Object>();

		List<Column> columns = insertSql.getColumns().getColumnsList();
		for (int i = 0; i < columns.size(); i++) {
			Column columnObj = columns.get(i);
			if (column.equalsIgnoreCase(columnObj.getColumn())) {
				Object columnVal = insertSql.getValue().get(i);
				if (columnVal instanceof ValueObject) {
					Comparable<?> evalVal = ((ValueObject) columnVal).eval(params);
					if (evalVal != null) {
						evalSet.add(evalVal);
					}
				} else if (columnVal instanceof Comparable<?>) {
					evalSet.add(columnVal);
				}
				break;
			}
		}
		if (evalSet.isEmpty()) {
			throw new ShardRouterException("Router column[" + column + "] not found in insert sql[" + insertSql + "].");
		}

		return evalSet;
	}

	static class EvalContext {
		Set<String> tableAliases;

		String column;

		List<Object> params;

		public EvalContext(Set<String> tableAliases, String column, List<Object> params) {
			this.tableAliases = tableAliases;
			this.column = column;
			this.params = params;
		}
	}
}
