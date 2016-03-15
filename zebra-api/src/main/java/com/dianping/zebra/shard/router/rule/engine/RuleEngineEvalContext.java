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
package com.dianping.zebra.shard.router.rule.engine;

import java.util.Map;

/**
 * 为了之后的RuleEngine扩展，所以提供该Context类
 * @author danson.liu
 */
public class RuleEngineEvalContext {

	private final Map<String, Object> valMap;

	public RuleEngineEvalContext(Map<String, Object> valMap) {
		this.valMap = valMap;
	}

	public Map<String, Object> getValMap() {
		return valMap;
	}
}
