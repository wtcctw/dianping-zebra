/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-11-1
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
package com.dianping.zebra.monitor.sql;

import java.util.Stack;

import org.apache.commons.pool.BasePoolableObjectFactory;

/**
 * @author danson.liu
 *
 */
public class SqlMsgBuilderStackPoolFactory extends BasePoolableObjectFactory {

	@Override
	public Object makeObject() throws Exception {
		return new Stack<SqlMsgBuilder>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void passivateObject(Object obj) throws Exception {
		Stack<SqlMsgBuilder> stack = (Stack<SqlMsgBuilder>) obj;
		stack.clear();
	}

}
