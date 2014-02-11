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

public class OrderByEle implements Cloneable {
	private String table;
	private String name;
	private boolean isASC;
	public OrderByEle(String table,String name,boolean isASC) {
		this.table=table;
		this.name=name;
		this.isASC=isASC;
	}
	public String getTable() {
		return table;
	}
	public String getName() {
		return name;
	}
	public boolean isASC() {
		return isASC;
	}
	public Object clone()throws CloneNotSupportedException{
		return super.clone();
	}
	
}
