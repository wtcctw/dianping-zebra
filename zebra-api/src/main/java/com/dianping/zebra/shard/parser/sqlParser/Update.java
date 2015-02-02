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
package com.dianping.zebra.shard.parser.sqlParser;

import java.util.ArrayList;
import java.util.List;

import com.dianping.zebra.shard.parser.condition.ExpressionGroup;
import com.dianping.zebra.shard.parser.condition.WhereCondition;
import com.dianping.zebra.shard.parser.valueObject.ColumnObject;
import com.dianping.zebra.shard.parser.valueObject.Value;

public class Update extends DMLCommon implements Value{
	protected WhereCondition where = null;
	protected List<SetElement> setElements=new ArrayList<SetElement>();
	
	public void addSetElement(String colName,String table,Object obj){
		SetElement set=new SetElement();
		set.col=new ColumnObject(colName,table);
		set.value=obj;
		setElements.add(set);
	}
	public List<SetElement> getSetElements(){
		return setElements;
	}
	public Update() {
		where=new WhereCondition();
		where.setHolder(this);
	}

	public WhereCondition getWhere() {
		return where;
	}

	public void addAndWhereExpressionGroup(ExpressionGroup exp) {
		where.addAndExpression(exp);
	}
	public void appendParams(List<Object> params) {
		super.appendParams(params);
		for(SetElement ele:setElements){
			ele.appendParams(params);
		}
		where.appendParams(params);
	}
	public void appendSQL(StringBuilder sb) {
		sb.append("UPDATE ");
		super.appendSQL(sb);
		sb.append("SET ");
		boolean comma=false;
		for(SetElement ele:setElements){
			if(comma){
				sb.append(",");
			}
			comma=true;
			ele.appendSQL(sb);
		}
		where.appendSQL(sb);
		
	}
//	@Override
//	public Map<String, Comparative> getSubColumnsMap() {
//		return where.eval();
//	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(super.toString());
		sb.append("SET ");
		boolean comma=false;
		for(SetElement ele:setElements){
			if(comma){
				sb.append(",");
			}
			comma=true;
			ele.appendSQL(sb);
		}
		sb.append(where.toString());
		return sb.toString();
	}
	public List<OrderBy> getOrderByList() {
		return where.getOrderByColumns();
	}
}
