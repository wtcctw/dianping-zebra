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
package com.dianping.zebra.parser.condition;

public class BetweenPair {

	public Object start;
	public Object end;

	public BetweenPair(Object start, Object end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return start + " AND " + end;
	}

}
