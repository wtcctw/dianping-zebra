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
package com.dianping.zebra.router.rule;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

import com.dianping.zebra.router.config.RouterConfigException;

/**
 * @author danson.liu
 *
 */
public abstract class AbstractDimensionRule implements DimensionRule {
	
	protected Set<String> basedColumns = new HashSet<String>(2);
	
	protected boolean isMaster;

	public Set<String> getBasedColumns() {
		return basedColumns;
	}

	public void setBasedColumns(Set<String> basedColumns) {
		this.basedColumns = basedColumns;
	}

	public void initBasedColumns(String rule) {
		Matcher matcher = RULE_COLUMN_PATTERN.matcher(rule);
		while (matcher.find()) {
			basedColumns.add(matcher.group(1));
		}
		if (basedColumns.size() != 1) {
			throw new RouterConfigException("Sharding rule only support one column now.");
		}
	}

	public boolean isMaster() {
		return isMaster;
	}

	public void setMaster(boolean isMaster) {
		this.isMaster = isMaster;
	}
	
}
