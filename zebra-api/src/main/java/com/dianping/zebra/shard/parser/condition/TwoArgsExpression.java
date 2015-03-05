/**
 * Project: ${zebra-client.aid}
 * 
 * File Created at 2011-6-16 $Id$
 * 
 * Copyright 2010 dianping.com. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Dianping
 * Company. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with dianping.com.
 */
package com.dianping.zebra.shard.parser.condition;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dianping.zebra.shard.parser.exception.runtime.NotSupportException;
import com.dianping.zebra.shard.parser.util.Utils;
import com.dianping.zebra.shard.parser.valueObject.ColumnObject;
import com.dianping.zebra.shard.parser.valueObject.RowJepVisitor;
import com.dianping.zebra.shard.parser.valueObject.ValueObject;

public class TwoArgsExpression implements ComparableAndInExpression {
	

	public static enum RELATION {
		/**
		 * =
		 */
		EQ,
		/**
		 * >
		 */
		GT,
		/**
		 * <
		 */
		LT,
		/**
		 *
		 */
		IN,
		/**
		 * >=
		 */
		GT_EQ,
		/**
		 * <=
		 */
		LT_EQ,
		/**
		 * <>
		 */
		NOT_EQ,
		/**
		 * like '%'由使用者输入，决定是前面%,还是后面%还是前后都有
		 */
		LIKE, NOT_IN, NOT_LIKE, BETWEEN, NOT_BETWEEN
	}

	private Object left;
	private RELATION relation;
	private Object right;

	public RELATION getRelation() {
		return relation;
	}

	public Object getLeft() {
		return left;
	}

	public Object getRight() {
		return right;
	}

	public TwoArgsExpression(Object left, RELATION relation, Object right) {
		this.left = left;
		this.relation = relation;
		this.right = right;

	}

	private static String getReltaionString(RELATION relation) {
		switch (relation) {
		case EQ:
			return " = ";
		case GT:
			return " > ";

		case LT:
			return " < ";

		case GT_EQ:
			return " >= ";

		case LT_EQ:
			return " <= ";

		case NOT_EQ:
			return " <> ";

		case IN:
			return " in ";

		case LIKE:
			return " like ";
		case NOT_IN:
			return " not in ";
		case NOT_LIKE:
			return " not like ";
		case BETWEEN:
			return " BETWEEN ";
		case NOT_BETWEEN:
			return " NOT BETWEEN ";
		default:
			throw new RuntimeException("no supported relation:" + relation);
		}
	}

	public void appendParams(List<Object> params) {
		Utils.appendParams(left, params);
		Utils.appendParams(right, params);
	}

	public void appendSQL(StringBuilder sb) {
		switch (relation) {
		case EQ:
		case LT:
		case GT:
		case LT_EQ:
		case GT_EQ:
		case NOT_EQ:
		case NOT_LIKE:
		case LIKE:

			Utils.appendSQLList(left, sb);
			sb.append(getReltaionString(relation));
			Utils.appendSQLList(right, sb);
			break;
		case IN:
		case NOT_IN:

			Utils.appendSQLList(left, sb);
			sb.append(getReltaionString(relation));
			sb.append("(");
			Utils.appendSQLList(right, sb);
			sb.append(")");
			break;
		case BETWEEN:
		case NOT_BETWEEN:
			Utils.appendSQLList(left, sb);
			sb.append(getReltaionString(relation));
			Utils.appendSQLList(right, sb);
			break;

		default:
			throw new RuntimeException("not supported relation:" + relation);
		}

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		switch (relation) {
		case EQ:
		case LT:
		case GT:
		case LT_EQ:
		case GT_EQ:
		case NOT_EQ:
		case NOT_LIKE:
		case LIKE:

			Utils.listToString(left, sb);
			sb.append(getReltaionString(relation));
			Utils.listToString(right, sb);
			break;
		case IN:
		case NOT_IN:

			Utils.listToString(left, sb);
			sb.append(getReltaionString(relation));
			sb.append("(");
			Utils.listToString(right, sb);
			sb.append(")");
			break;
		case BETWEEN:
		case NOT_BETWEEN:
			Utils.appendSQLList(left, sb);
			sb.append(getReltaionString(relation));
			Utils.appendSQLList(right, sb);
			break;

		default:
			throw new RuntimeException("not supported relation:" + relation);
		}
		return sb.toString();
	}

	public void eval(RowJepVisitor visitor, boolean inAnd) {
		buildUpSqlJepList(visitor, left, relation, right, inAnd);
	}

	private void buildUpSqlJepList(RowJepVisitor visitor, Object left,
			RELATION relation, Object right, boolean inAnd) {
	}

	public String getColumn() {
		ColumnObject col = null;
		if (left instanceof ColumnObject) {
			col = (ColumnObject) left;
		}else
		{
			throw new NotSupportException("还不支持");
		}
		return col.getColumnName();
	}

	@Override
	public Set<Comparative> eval(Set<String> tableAliases, String column, List<Object> params) {
		return eval(left, relation, right, tableAliases, column, params);
	}
	
	private Set<Comparative> eval(Object left, RELATION relation, Object right, Set<String> tableAliases, String column, List<Object> params) {
		if (!(left instanceof ColumnObject)) {
			return Collections.emptySet();
		}
		ColumnObject columnObj = (ColumnObject) left;
		String table = columnObj.getTable();
		if (tableAliases.contains(table) && column.equalsIgnoreCase(columnObj.getColumnName())) {
			Set<Comparative> evalSet = new HashSet<Comparative>(10);
			Object evalVal = null;
			if (right instanceof ValueObject) {
				evalVal = ((ValueObject) right).eval(params);
			} else if (right instanceof List) {
				List<?> list = (List<?>) right;
				if (relation != RELATION.IN) {
					throw new NotSupportException("List value can be in 'IN' clause only.");
				}
				for (Object val : list) {
					evalSet.addAll(eval(left, RELATION.EQ, val, tableAliases, column, params));
				}
			} else if (right instanceof Comparable<?> || right instanceof BetweenPair) {
				//普通常量
				evalVal = right;
			}
			if (evalVal != null && (relation == RELATION.EQ || relation == RELATION.GT || relation == RELATION.GT_EQ
					|| relation == RELATION.NOT_EQ || relation == RELATION.LT || relation == RELATION.LT_EQ 
					|| relation == RELATION.BETWEEN)) {
				int comparison = ExpressionUtil.convertToComparison(relation);
				evalSet.add(new Comparative(comparison, evalVal));
			}
			return evalSet;
		}
		return Collections.emptySet();
	}

}
