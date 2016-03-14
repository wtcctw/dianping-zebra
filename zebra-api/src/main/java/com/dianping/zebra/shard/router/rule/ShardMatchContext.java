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
package com.dianping.zebra.shard.router.rule;

import java.util.List;
import java.util.Map;

import com.dianping.zebra.shard.parser.SQLParsedResult;

public class ShardMatchContext {

	private final SQLParsedResult parseResult;

	private final List<Object> params;

	private List<Map<String, Object>> colValues;

	private ShardMatchResult matchResult = new ShardMatchResult();

	public ShardMatchContext(SQLParsedResult parseResult, List<Object> params) {
		this.parseResult = parseResult;
		this.params = params;
	}

	public ShardMatchResult getMatchResult() {
		return matchResult;
	}

	public SQLParsedResult getParseResult() {
		return parseResult;
	}

	public List<Object> getParams() {
		return params;
	}

	public List<Map<String, Object>> getColValues() {
		return colValues;
	}

	public void setColValues(List<Map<String, Object>> colValues) {
		this.colValues = colValues;
	}
}
