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

import com.dianping.zebra.shard.config.RouterConfigException;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * @author danson.liu
 *
 */
public abstract class AbstractDimensionRule implements DimensionRule {

	protected String shardColumn;

	protected boolean isMaster;

	public void initShardColumn(String rule) {
		Set<String> shardColumns = new HashSet<String>();
		Matcher matcher = RULE_COLUMN_PATTERN.matcher(rule);
		
		while (matcher.find()) {
			shardColumns.add(matcher.group(1));
		}
		
		if (shardColumns.size() != 1) {
			throw new RouterConfigException("Sharding rule only support one column now.");
		}
		
		shardColumn = shardColumns.iterator().next();
	}

	public void setMaster(boolean isMaster) {
		this.isMaster = isMaster;
	}
}
