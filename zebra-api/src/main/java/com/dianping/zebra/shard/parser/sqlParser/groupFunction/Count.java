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
package com.dianping.zebra.shard.parser.sqlParser.groupFunction;

public class Count extends CommonOneColumnFunction implements GroupFunction {

	private boolean hasDistinct = false;

	public boolean hasDistinct() {
		return hasDistinct;
	}

	public void setHasDistinct(boolean hasDistinct) {
		this.hasDistinct = hasDistinct;
	}

	@Override
	public String getFunctionName() {
		return "count";
	}

}
