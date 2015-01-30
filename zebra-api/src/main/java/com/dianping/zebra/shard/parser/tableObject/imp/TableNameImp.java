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
package com.dianping.zebra.shard.parser.tableObject.imp;

import java.util.List;

import com.dianping.zebra.shard.parser.tableObject.TableName;

public class TableNameImp implements TableName{
	private String alias;
//	private List<Hint> hints;
	private String tablename;
	private String schemaName;
	
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getTableName() {
		return tablename;
	}
	
	public void appendParams(List<Object> params) {
	}
	public void appendSQL(StringBuilder sb) {
		if(getSchemaName()!=null){
			sb.append(schemaName).append(".");
			}
			sb.append(tablename);
			if(getAlias()!=null){
			sb.append(" AS ").append(getAlias());
	}
	}
	@Override
	public int hashCode() {
		StringBuilder sb=new StringBuilder();
		sb.append(this.schemaName).append(".").append(this.tablename);
		return sb.toString().hashCode();
		
	}
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(!(obj instanceof TableNameImp)){
			return false;
		}
		TableNameImp imp=(TableNameImp)obj;
		return imp.tablename.equals(this.tablename)&&imp.schemaName.equals(this.schemaName);
	}
 
	public String getTableNameStr(){
		return tablename;
	}
}
