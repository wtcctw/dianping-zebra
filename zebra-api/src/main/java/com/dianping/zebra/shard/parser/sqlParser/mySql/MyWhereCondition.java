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
package com.dianping.zebra.shard.parser.sqlParser.mySql;

import com.dianping.zebra.shard.parser.condition.WhereCondition;

public class MyWhereCondition extends WhereCondition {
	private Object start =null;
	private Object range = null;
	public Object getStart() {
		return start;
	}
	public Object getRange() {
		return range;
	}
	public void setRange(Object range) {
		this.range = range;
	}
	public void setStart(Object start) {
		this.start = start;
	}
	
	
	public LimitInfo limitInfo = new LimitInfo();
	/**
	 * 记录limitClause信息
	 * @author qing.gu
	 *
	 */
	public static class LimitInfo {
		public int skipIdx = -1;	//索引
		public int rangeIdx = -1;
		public String skip;			//具体的文本	
		public String range;
	}
	


}
