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
package com.dianping.zebra.parser.sqlParser;

import java.util.List;

import com.dianping.zebra.parser.condition.ExpressionGroup;
import com.dianping.zebra.parser.condition.WhereCondition;
import com.dianping.zebra.parser.valueobject.Value;

public class Delete extends DMLCommon implements Value {
	protected WhereCondition where = null;

	public Delete() {
		where = new WhereCondition();
		where.setHolder(this);
	}

	public WhereCondition getWhere() {
		return where;
	}

	public void addAndWhereExpressionGroup(ExpressionGroup exp) {
		where.addAndExpression(exp);
	}

//	public Map<String, Comparative> getSubColumnsMap() {
//		return where.eval();
//	}
	public void appendParams(List<Object> params) {
		super.appendParams(params);
		where.appendParams(params);
	}

	public void appendSQL(StringBuilder sb) {
		sb.append("DELETE ").append("FROM ");
		super.appendSQL(sb);
		where.appendSQL(sb);

	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ").append("FROM ");
		sb.append(super.toString());
		sb.append(where.toString());
		return sb.toString();
	}

	public List<OrderByEle> getOrderByList() {
		return where.getOrderByColumns();
	}
}