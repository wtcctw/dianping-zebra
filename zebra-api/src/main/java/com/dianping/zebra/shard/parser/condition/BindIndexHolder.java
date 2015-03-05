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
package com.dianping.zebra.shard.parser.condition;


public class BindIndexHolder {
	private int selfAdd = 0;
	/**
	 * 返回当前值，并自增where内的计数，用于添加绑定变量时的标识
	 * 
	 */
	public int selfAddAndGet() {
		int ret = selfAdd;
		selfAdd++;
		return ret;
	}
	public BindIndexHolder() {
	}
	public BindIndexHolder(int index) {
		this.selfAdd = index;
	}
	public void setIndex(int index) {
		this.selfAdd = index;
	}
	public int getIndex() {
		return selfAdd;
	}
}
