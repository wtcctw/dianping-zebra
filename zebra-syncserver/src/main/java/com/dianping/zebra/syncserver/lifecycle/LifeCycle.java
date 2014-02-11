/**
 * Project: ${zebra-syncserver.aid}
 * 
 * File Created at 2011-7-26
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
package com.dianping.zebra.syncserver.lifecycle;

/**
 * 生命周期
 * 
 * @author Leo Liang
 * 
 */
public interface LifeCycle {
	public void init() throws LifeCycleException;

	public void start() throws LifeCycleException;

	public void stop() throws LifeCycleException;
}
