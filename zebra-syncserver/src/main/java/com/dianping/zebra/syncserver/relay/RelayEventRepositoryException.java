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
package com.dianping.zebra.syncserver.relay;

/**
 * @author Leo Liang
 * 
 */
public class RelayEventRepositoryException extends Exception {

	private static final long	serialVersionUID	= -8721336053814101473L;

	public RelayEventRepositoryException() {
		super();
	}

	public RelayEventRepositoryException(String message) {
		super(message);
	}

	public RelayEventRepositoryException(Throwable throwable) {
		super(throwable);
	}

	public RelayEventRepositoryException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
