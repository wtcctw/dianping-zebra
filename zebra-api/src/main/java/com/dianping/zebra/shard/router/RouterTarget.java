/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-14
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.shard.router;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hao.zhu 
 * 
 */
public class RouterTarget {

	private String dbIndex;

	private List<String> sqls;

	public RouterTarget(String dbIndex) {
		this.dbIndex = dbIndex;
	}
	
	public String getDataSourceName() {
		return dbIndex;
	}

	public void setDataSourceName(String dbIndex) {
		this.dbIndex = dbIndex;
	}

	public List<String> getSqls() {
		return sqls;
	}

	public void setSqls(List<String> sqls) {
		this.sqls = sqls;
	}

	public void addSql(String sql) {
		if (this.sqls == null) {
			this.sqls = new ArrayList<String>();
		}
		this.sqls.add(sql);
	}
}
