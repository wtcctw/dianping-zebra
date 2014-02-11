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

import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author danson.liu
 *
 */
public interface DimensionRule {

	Pattern RULE_COLUMN_PATTERN = Pattern.compile("#(.+?)#");

	/**
	 * 
	 * @param matchContext
	 * @return 是否需要继续下一个规则的匹配
	 */
	boolean match(ShardMatchContext matchContext);
	
	Map<String, Set<String>> getAllDBAndTables();

}
