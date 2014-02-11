/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-17
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
package com.dianping.zebra.router.rule;

import java.util.List;
import java.util.Set;

import com.dianping.zebra.parser.sqlParser.DMLCommon;
import com.dianping.zebra.parser.sqlParser.Select;

/**
 * @author danson.liu
 *
 */
public class ShardMatchContext {

	private final DMLCommon dmlSql;
	
	private final List<Object> params;
	
	private Set<Object> colValues;
	
	private ShardMatchResult matchResult = new ShardMatchResult();
	
	public ShardMatchContext(DMLCommon dmlSql, List<Object> params) {
		this.dmlSql = dmlSql;
		this.params = params;
	}

	public ShardMatchResult getMatchResult() {
		return matchResult;
	}

	public DMLCommon getDmlSql() {
		return dmlSql;
	}
	
	public boolean onlyMatchMaster() {
		//是否只有Insert才需要强制匹配Master Rule?
		return !(dmlSql instanceof Select);
	}
	
	public boolean onlyMatchOnce() {
		return dmlSql instanceof Select;
	}

	public List<Object> getParams() {
		return params;
	}

	public Set<Object> getColValues() {
		return colValues;
	}

	public void setColValues(Set<Object> colValues) {
		this.colValues = colValues;
	}

}
