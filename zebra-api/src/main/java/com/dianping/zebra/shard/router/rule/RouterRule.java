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
package com.dianping.zebra.shard.router.rule;

import java.util.HashMap;
import java.util.Map;

/**
 * @author danson.liu
 *
 */
public class RouterRule {
	
	private Map<String, TableShardRule2> tableShardRules = new HashMap<String, TableShardRule2>();

	public Map<String, TableShardRule2> getTableShardRules() {
		return tableShardRules;
	}

	public void setTableShardRules(Map<String, TableShardRule2> tableShardRules) {
		this.tableShardRules = tableShardRules;
	}

}
