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
package com.dianping.zebra.shard.parser.sqlParser.groupFunction;

import java.util.List;

public abstract class CommonOneColumnFunction  implements GroupFunction{
	private String alias;
	private String column;
	private String table;
	
	public void appendParams(List<Object> params) {
		
	}
	public void appendSQL(StringBuilder sb) {
		sb.append(getFunctionName()+"(");
		if(table!=null){
			sb.append(table).append(".");
		}
		sb.append(column).append(")");
		if(alias != null && !alias.equals("")) {
			sb.append(" AS ").append(alias);
		}
	}
	public abstract String getFunctionName();
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
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
	};

}
