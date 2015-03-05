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
package com.dianping.zebra.shard.parser.valueObject;

import java.util.List;

/**
 * 列名包装字段,用于where内部
 *
 */
public class ColumnObject extends ValueObject {
	private String columnName;
	private String table;
	private String alias;
	
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public ValueType getType() {
		return ValueType.Column;
	}

	public ColumnObject(String columnName) {
		this.columnName = columnName;
	}

	public ColumnObject(String columnName,String table) {
		this.columnName = columnName;
		this.table=table;
	}
	
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void appendParams(List<Object> params) {
	}

	public void appendSQL(StringBuilder sb) {
		if(table!=null){
		sb.append(table);
		sb.append(".");
		}
		sb.append(columnName);
	}

	public String toString() {
		return columnName;
	}
	public String getNestedColName() {
		return getColumnName();
	}
}
