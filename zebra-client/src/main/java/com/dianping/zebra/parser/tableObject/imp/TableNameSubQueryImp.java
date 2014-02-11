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
package com.dianping.zebra.parser.tableObject.imp;

import java.util.List;

import com.dianping.zebra.parser.sqlParser.Select;
import com.dianping.zebra.parser.tableObject.TableName;

public class TableNameSubQueryImp implements TableName{
	private String alias;
	private Select subSelect;
//	private List<Hint> hints;
	public String getAlias() {
		return alias;
	}
	
	public Select getSubSelect() {
		return subSelect;
	}

	public void setSubSelect(Select subSelect) {
		this.subSelect = subSelect;
	}

//	public List<Hint> getHints() {
//		return hints;
//	}
//
//	public void setHints(List<Hint> hints) {
//		this.hints = hints;
//	}

	public void appendParams(List<Object> params) {
		subSelect.appendParams(params);
		
	}

	public void appendSQL(StringBuilder sb) {
		subSelect.appendSQL(sb);
		if(alias!=null){
			sb.append(" ");
			sb.append(alias);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(subSelect.toString());
		if(alias!=null){
			sb.append(" AS ");
			sb.append(alias);
		}
		return sb.toString();
	}

	public void setAlias(String alias) {
		this.alias=alias;
		
	}

	public String getTableName() {
		return subSelect.getTableName();
	}

	public boolean containsTable(String table) {
		List<TableName> tbNames = subSelect.getTbNames();
		for (TableName tbName : tbNames) {
			if (tbName instanceof TableNameSubQueryImp) {
				if (((TableNameSubQueryImp) tbName).containsTable(table)) {
					return true;
				}
			} else if (tbName instanceof TableNameImp) {
				if (table.equals(tbName.getTableName())) {
					return true;
				}
			}
		}
		return false;
	}
	
}
