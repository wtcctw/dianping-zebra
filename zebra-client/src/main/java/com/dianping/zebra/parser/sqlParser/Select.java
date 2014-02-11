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

import com.dianping.zebra.parser.condition.BindIndexHolder;
import com.dianping.zebra.parser.condition.ExpressionGroup;
import com.dianping.zebra.parser.condition.WhereCondition;
import com.dianping.zebra.parser.valueobject.Value;
import com.dianping.zebra.parser.valueobject.ValueObject.ValueType;

public class Select extends DMLCommon implements Value {
	// protected int skip = -1;
	// protected int max = -1;
	String tempWhereStr = null;
	boolean isSubSelect = false;
	boolean hasDistinct = false;

	public boolean hasDistinct() {
		return hasDistinct;
	}

	public void setHasDistinct(boolean hasDistinct) {
		this.hasDistinct = hasDistinct;
	}

	public boolean isSubSelect() {
		return isSubSelect;
	}

	public void setSubSelect(boolean isSubSelect) {
		this.isSubSelect = isSubSelect;
	}
	
	// protected TableNameImp tbName = new TableNameImp();
	protected WhereCondition where = null;
	protected Columns columns = new Columns();


	public Select() {
		where = new WhereCondition();
		where.setHolder(this);
	}

	public WhereCondition getWhere() {
		return where;
	}

	public Columns getColumns() {
		return columns;
	}

	public void setColumns(Columns columns) {
		this.columns = columns;
	}

	public void addColumn(String table, String column, String alias) {
		columns.addColumn(table, column, alias);
	}
	public void addColumn(Column col){
		columns.addColumn(col);
	}
	public void addAndWhereExpressionGroup(ExpressionGroup exp) {
		where.addAndExpression(exp);
	}

	public void appendParams(List<Object> params) {
		super.appendParams(params);
		where.appendParams(params);
	}

	public void appendSQL(StringBuilder sb) {
		if(isSubSelect){
		sb.append("(");
		}
		sb.append("SELECT ");
		columns.appendSQL(sb);
		sb.append(" FROM ");
		super.appendSQL(sb);
		where.appendSQL(sb);
		
		if(isSubSelect){
			sb.append(")");
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(isSubSelect){
		sb.append("(");
		}
		sb.append("SELECT ");
		columns.appendSQL(sb);
		sb.append(" FROM ");
		sb.append(super.toString());
		sb.append(where.toString());
		if(isSubSelect){
			sb.append(")");
		}
		return sb.toString();
	}
//	@Override
//	public Map<String, Comparative> getSubColumnsMap() {
//		return where.eval();
//	}

	public ValueType getType() {
		return ValueType.SubQuery;
	}

	public List<OrderByEle> getOrderByList() {
		return where.getOrderByColumns();
	}
	
	/**
	 * JOIN语句中可能有'?'，可能有多个JOIN语句，将WHERE中'?'的起始索引进行相应的偏移
	 */
	public void incrementBindIndex(int offset) {
		BindIndexHolder holder = where.getHolder();
		holder.setIndex(holder.getIndex() + offset);
	}

}
