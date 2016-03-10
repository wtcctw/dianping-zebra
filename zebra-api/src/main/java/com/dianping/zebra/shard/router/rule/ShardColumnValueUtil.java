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

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
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
import com.dianping.zebra.shard.parser.MySQLParserResult;

/**
 * 计算相关DML语句对于指定的column可能的值
 *
 * @author danson.liu
 */
public class ShardColumnValueUtil {

	public static Set<Object> eval(MySQLParserResult parseResult, String table, String column, List<Object> params) {
		if (parseResult.isInsert()) {
			return evalInsert(parseResult, column, params);
		}

		Set<Object> result = new HashSet<Object>();
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
					}
				}
			}
		}

		return result;

		// List<OrExpressionGroup> expGroups = buildExpression(parseResult,
		// table);
		// if (expGroups == null || expGroups.isEmpty()) {
		// return null;
		// }
		//
		// EvalContext context = buildEvalContext(parseResult, table, column,
		// params);
		//
		// if (expGroups.size() == 1) {
		// return eval(expGroups.get(0), context);
		// }
		//
		// Expression exp = expGroups.get(0);
		// for (int i = 1; i < expGroups.size() - 1; i++) {
		// exp = new ExpressionGroup(exp, expGroups.get(i));
		// }
		//
		// return evalAnd(exp, expGroups.get(expGroups.size() - 1), context);
	}

	private static SQLExpr getWhere(MySQLParserResult parseResult) {
		SQLExpr expr = null;
		SQLStatement stmt = parseResult.getStmt();

		if (parseResult.isSelect()) {
			MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) (((SQLSelectStatement) stmt).getSelect()).getQuery();
			expr = query.getWhere();
		} else if (parseResult.isUpdate()) {
			expr = ((MySqlUpdateStatement) stmt).getWhere();
		} else if (parseResult.isDelete()) {
			expr = ((MySqlDeleteStatement) stmt).getWhere();
		}

		return expr;
	}

	private static void eval(SQLExpr sqlExpr, String column, LinkedHashMap<String, Object> pairs) {
		SQLBinaryOpExpr where = null;
		if (sqlExpr instanceof SQLBinaryOpExpr) {
			where = (SQLBinaryOpExpr) sqlExpr;
		} else {
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
			}

			// TODO ignore operator first,later to handler it

			if (right instanceof SQLIdentifierExpr) {
				SQLIdentifierExpr indentifier = (SQLIdentifierExpr) left;
				pairs.put(indentifier.getName(), right);
			} else if (left instanceof SQLPropertyExpr) {
				SQLPropertyExpr indentifier = (SQLPropertyExpr) left;
				pairs.put(indentifier.getName(), right);
			} else if (right instanceof SQLBinaryOpExpr) {
				eval((SQLBinaryOpExpr) right, column, pairs);
			}
		}
	}

	private static boolean evalColumn(String indentifier, String column) {
		return column.equals(indentifier) || ("`" + column + "`").equals(indentifier);
	}

	// private static EvalContext buildEvalContext(DMLCommon dmlSql, String
	// table, String column, List<Object> params) {
	// Set<String> tableAliases = dmlSql.getTableAliases(table);
	// tableAliases.add(table);
	//
	// return new EvalContext(tableAliases, column, params);
	// }
	//
	// private static List<OrExpressionGroup> buildExpression(DMLCommon dmlSql,
	// String table) {
	// WhereCondition whereCondition = null;
	//
	// if (dmlSql instanceof Select) {
	// whereCondition = ((Select) dmlSql).getWhere();
	// } else if (dmlSql instanceof Update) {
	// whereCondition = ((Update) dmlSql).getWhere();
	// } else if (dmlSql instanceof Delete) {
	// whereCondition = ((Delete) dmlSql).getWhere();
	// }
	// List<OrExpressionGroup> expGroups = new ArrayList<OrExpressionGroup>();
	//
	// for (TableName tbName : dmlSql.getTbNames()) {
	// if (tbName instanceof TableNameSubQueryImp && ((TableNameSubQueryImp)
	// tbName).containsTable(table)) {
	// expGroups.add(((TableNameSubQueryImp)
	// tbName).getSubSelect().getWhere().getExpGroup());
	// }
	// }
	//
	// expGroups.add(whereCondition.getExpGroup());
	//
	// return expGroups;
	// }
	//
	// private static Set<Object> evalAnd(Expression expLeft, Expression
	// expRight, EvalContext context) {
	// Set<Object> leftResult = eval(expLeft, context);
	// Set<Object> rightResult = eval(expRight, context);
	//
	// return intersection(leftResult, rightResult);
	// }
	//
	// private static Set<Object> evalOr(Expression expLeft, Expression
	// expRight, EvalContext context) {
	// Set<Object> leftResult = eval(expLeft, context);
	// Set<Object> rightResult = eval(expRight, context);
	//
	// return union(leftResult, rightResult);
	// }
	//
	// private static Set<Object> eval(Expression expression, EvalContext
	// context) {
	// if (expression instanceof OrExpressionGroup) {
	// OrExpressionGroup orExpression = ((OrExpressionGroup) expression);
	// List<Expression> expressions = orExpression.getExpressions();
	// if (expressions == null || expressions.isEmpty()) {
	// return Collections.emptySet();
	// }
	// if (expressions.size() == 1) {
	// return eval(expressions.get(0), context);
	// }
	//
	// return evalOr(expressions.get(0), expressions.get(1), context);
	// }
	//
	// if (expression instanceof ExpressionGroup) {
	// ExpressionGroup andExpression = ((ExpressionGroup) expression);
	// List<Expression> expressions = andExpression.getExpressions();
	// if (expressions == null || expressions.isEmpty()) {
	// return Collections.emptySet();
	// }
	// if (expressions.size() == 1) {
	// return eval(expressions.get(0), context);
	// }
	//
	// return evalAnd(expressions.get(0), expressions.get(1), context);
	// }
	//
	// if (expression instanceof ComparableAndInExpression) {
	// Set<Comparative> evalSet = ((ComparableAndInExpression)
	// expression).eval(context.tableAliases,
	// context.column, context.params);
	// /*
	// * 暂时只考虑EQ条件参与路由判断,以后有按照值范围分库分表策略时再考虑诸如>,>=,<,<=等情况，这些条件
	// * 牵扯到相互之间的merge，相对复杂，需要时再添加进来
	// */
	// return filterEQ(evalSet);
	// }
	//
	// return Collections.emptySet();
	// }
	//
	// @SuppressWarnings("unchecked")
	// private static Set<Object> intersection(Set<Object> leftResult,
	// Set<Object> rightResult) {
	// if (leftResult.isEmpty()) {
	// return rightResult;
	// }
	// if (rightResult.isEmpty()) {
	// return leftResult;
	// }
	//
	// return CollectionUtils.intersection(leftResult, rightResult);
	// }
	//
	// private static Set<Object> union(Set<Object> leftResult, Set<Object>
	// rightResult) {
	// if (leftResult.isEmpty() || rightResult.isEmpty()) {
	// return Collections.emptySet();
	// }
	// leftResult.addAll(rightResult);
	//
	// return leftResult;
	// }
	//
	// private static Set<Object> filterEQ(Set<Comparative> comparatives) {
	// if (comparatives.isEmpty()) {
	// return Collections.emptySet();
	// }
	//
	// Set<Object> filtered = new LinkedHashSet<Object>();
	//
	// for (Comparative comparative : comparatives) {
	// if (comparative.getComparison() == Comparative.Equivalent) {
	// filtered.add(comparative.getValue());
	// }
	// }
	//
	// return filtered;
	// }

	private static Set<Object> evalInsert(MySQLParserResult parseResult, String column, List<Object> params) {
		Set<Object> evalSet = new LinkedHashSet<Object>();
		MySqlInsertStatement stmt = (MySqlInsertStatement) parseResult.getStmt();

		// TODO how to handle batch?
		List<SQLExpr> columns = stmt.getColumns();
		ValuesClause values = stmt.getValues();
		for (int i = 0; i < columns.size(); i++) {
			SQLName columnObj = (SQLName) columns.get(i);
			if (column.equalsIgnoreCase(columnObj.getSimpleName())) {
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
			throw new ShardRouterException("Router column[" + column + "] not found in the sql");
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
