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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.dianping.zebra.shard.parser.util.Utils;
import com.dianping.zebra.shard.parser.valueObject.Value;

public class Columns implements Value{
	private  List<Column> columns=new ArrayList<Column>();
	public Column[] getColumns() {
		 return columns.toArray(new Column[columns.size()]);
		 
	}
	public List<Column> getColumnsList(){
		return columns;
	}
	public void setColumns(Column[] columns) {
		this.columns.addAll(Arrays.asList(columns));
	}
	public void setColumns(Collection<Column> cols){
		this.columns.addAll(cols);
	}
	public void addColumn(String table,String col,String alias){
		columns.add(new ColumnImp(table,col,alias));
	}
	public void addColumn(Column col){
		columns.add(col);
	}
	public void addColumnTabAndCol(String tab,String col){
			columns.add(new ColumnImp(tab,col,null));
	}
	public void appendParams(List<Object> params) {
		Utils.appendParams(columns, params);
		
	}
	public void appendSQL(StringBuilder sb) {

//		boolean comma = false;
//		for (Column col : columns) {
//			if (comma) {
//				sb.append(",");
//				
//			}
//			comma=true;
			Utils.appendSQLList(columns, sb);
			
//		}
	}
}
