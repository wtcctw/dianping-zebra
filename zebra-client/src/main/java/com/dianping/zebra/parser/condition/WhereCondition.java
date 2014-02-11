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
package com.dianping.zebra.parser.condition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dianping.zebra.parser.sqlParser.OrderByEle;
import com.dianping.zebra.parser.valueobject.Value;

public  class WhereCondition implements Value{
	private BindIndexHolder holder = new BindIndexHolder();

	public final static WhereCondition NULL_WHERE_CONDITION = new WhereCondition();
	private final List<OrderByEle> orderByColumns = new ArrayList<OrderByEle>();
	private List<String> groupByColumns;
	private OrExpressionGroup expGroup = new OrExpressionGroup();



	public WhereCondition() {
	}

//	public WhereCondition(int index){
//		super(index);
//	}

	public void addGroupByColumn(String column) {
		if (null == groupByColumns) {
			groupByColumns = new LinkedList<String>();
		}
		groupByColumns.add(column);
	}
	
	public BindIndexHolder getHolder() {
		return holder;
	}

	public void setHolder(BindIndexHolder holder) {
		this.holder = holder;
	}
	
	public void addOrderByColumn(String table, String column) {
		this.addOrderByColumn(table, column, true);
	}

	public void addOrderByColumn(String  table, String column, boolean isAsc) {
		orderByColumns.add(new OrderByEle(table, column, isAsc));
	}

	public OrExpressionGroup getExpGroup() {
		return expGroup;
	}

	public List<OrderByEle> getOrderByColumns() {
		return this.orderByColumns;
	}

	public List<String> getGroupByColumns() {
		return this.groupByColumns;
	}

	public void clear() {
		expGroup = null;

		if (null != groupByColumns) {
			groupByColumns.clear();
		}

		if (null != orderByColumns) {
			orderByColumns.clear();
		}
	}
	public int selfAddAndGet(){
		return holder.selfAddAndGet();
	}
	public void addAndExpression(ExpressionGroup exp) {
		expGroup.addExpressionGroup(exp);
	}

	public void appendParams(List<Object> params) {
		expGroup.appendParams(params);
	}

	public void appendSQL(StringBuilder sb) {

		StringBuilder temp=new StringBuilder();
		expGroup.appendSQL(temp);
		if(temp.length()!=0){
			sb.append(" WHERE ");
			sb.append(temp);
		}
		if (orderByColumns.size() != 0) {
			sb.append(" ORDER BY ");
			boolean comma = false;
			for (OrderByEle ol : orderByColumns) {
				if (comma) {
					sb.append(",");
				}
				comma = true;
				if (ol.getTable() != null) {
					sb.append(ol.getTable()).append(".");
				}

				sb.append(ol.getName());
				if (ol.isASC()) {
					sb.append(" ASC ");
				} else {
					sb.append(" DESC ");
				}
			}
		}
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		StringBuilder temp=new StringBuilder();
		temp.append(expGroup.toString());
		if(temp.length()!=0){
			sb.append(" WHERE ");
			sb.append(temp);
		}
		if (orderByColumns.size() != 0) {
			sb.append(" ORDER BY ");

			boolean comma = false;
			for (OrderByEle ol : orderByColumns) {
				if (comma) {
					sb.append(",");
				}
				comma = true;
				if (ol.getTable() != null) {
					sb.append(ol.getTable()).append(".");
				}

				sb.append(ol.getName());
				if (ol.isASC()) {
					sb.append(" ASC ");
				} else {
					sb.append(" DESC ");
				}
			}
		}
		return sb.toString();
	}
//	public Map<String,Comparative> eval(){
//		RowJepVisitor visitor=new RowJepVisitor();
//		 expGroup.eval(visitor,false);
//		 for(Expression e:expGroup.expressions){
//			 
//		 }
//		evalExp(expGroup, visitor);
//		 return visitor.getComparable();
//	}
}
