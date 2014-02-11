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

public class ColumnImp implements Column{
	String column=null;
	String table=null;
	String alias=null;
	public ColumnImp(String table,String column,String alias){
		this.column=column;
		this.alias=alias;
		this.table=table;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public void appendParams(List<Object> params) {
		
	}
	public void appendSQL(StringBuilder sb) {
		if(table!=null){
			sb.append(table);
			sb.append(".");
			}
			sb.append(column);
			if (alias != null) {
				sb.append(" AS ");
				sb.append(alias);
			}
		
	}
	
}
