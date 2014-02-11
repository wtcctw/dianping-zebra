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
 * @author Leo Liang
 * 
 */
public class LifeCycleException extends Exception {

	private static final long	serialVersionUID	= -1647902659395829435L;

	public LifeCycleException() {
		super();
	}

	public LifeCycleException(String message) {
		super(message);
	}

	public LifeCycleException(Throwable throwable) {
		super(throwable);
	}

	public LifeCycleException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
