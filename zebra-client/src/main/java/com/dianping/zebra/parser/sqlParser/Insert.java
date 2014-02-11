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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dianping.zebra.parser.util.Utils;

public class Insert extends DMLCommon{

	protected List<Object> valueObj=new ArrayList<Object>();
	
	protected Columns columns = new Columns();
	public void addColumn(String table, String column, String alias) {
		columns.addColumn(table, column, alias);
	}


	public void addColumnTandC(String tab, String col) {
		columns.addColumnTabAndCol(tab, col);
	}
	public Columns getColumns() {
		return columns;
	}

	public void setColumns(Columns columns) {
		this.columns = columns;
	}
	
	public void addValue(Object obj){
		this.valueObj.add(obj);
	}
	public List<Object> getValue(){
		return valueObj;
	}


	public void appendParams(List<Object> params) {
		super.appendParams(params);
		columns.appendParams(params);
		Utils.appendParams(valueObj, params);
		
	}

	public void appendSQL(StringBuilder sb) {
		sb.append("INSERT INTO ");
		
		super.appendSQL(sb);
		sb.append("(");
		columns.appendSQL(sb);
		sb.append(") ").append("VALUES (");
		Utils.appendSQLList(valueObj, sb);
		sb.append(")");
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		
		sb.append(super.toString());
		sb.append("(");
		columns.appendSQL(sb);
		sb.append(") ").append("VALUES (");
		Utils.listToString(valueObj, sb);
		sb.append(")");
		return sb.toString();
	}
	public List<OrderByEle> getOrderByList() {
		return Collections.emptyList();
	}

}
