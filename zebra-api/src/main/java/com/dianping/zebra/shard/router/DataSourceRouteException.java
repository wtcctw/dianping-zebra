/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-16
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
package com.dianping.zebra.shard.router;

/**
 * @author danson.liu
 *
 */
public class DataSourceRouteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8724570679105220336L;

	public DataSourceRouteException() {
		super();
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 */
	public DataSourceRouteException(String message) {
		super(message);
	}

	/**
	 * Constructs a new runtime exception with the specified detail message and
	 * cause.
	 */
	public DataSourceRouteException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new runtime exception with the specified cause.
	 */
	public DataSourceRouteException(Throwable cause) {
		super(cause);
	}
	
}
