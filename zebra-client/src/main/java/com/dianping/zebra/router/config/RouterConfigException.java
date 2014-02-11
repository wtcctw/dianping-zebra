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
package com.dianping.zebra.router.config;

/**
 * @author danson.liu
 *
 */
public class RouterConfigException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9011665705066503654L;

	public RouterConfigException() {
		super();
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 */
	public RouterConfigException(String message) {
		super(message);
	}

	/**
	 * Constructs a new runtime exception with the specified detail message and
	 * cause.
	 */
	public RouterConfigException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new runtime exception with the specified cause.
	 */
	public RouterConfigException(Throwable cause) {
		super(cause);
	}
	
}
