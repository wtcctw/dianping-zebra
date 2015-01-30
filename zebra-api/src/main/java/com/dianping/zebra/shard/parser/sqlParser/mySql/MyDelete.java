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

import java.util.List;

import com.dianping.zebra.shard.parser.sqlParser.Delete;

public class MyDelete extends Delete {
	private RangeSelector limit = null;

	public MyDelete() {
		where = new MyWhereCondition();
		where.setHolder(this);
		limit = new RangeSelector((MyWhereCondition)where);
	}

	public void appendParams(List<Object> params) {
		super.appendParams(params);
		limit.appendParams(params);
	}
	public int getSkip(List<Object> param) {
		return limit.getSkip(param);
	}
	public int getMax(List<Object> param) {
		return limit.getMax(param);
	}
	public void appendSQL(StringBuilder sb) {
		super.appendSQL(sb);
		limit.appendSQL(sb);
	}
}
