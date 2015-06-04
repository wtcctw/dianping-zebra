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
package com.dianping.zebra.shard.parser.exception.runtime;

public class DalRunTimeException extends RuntimeException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2139691156552402165L;
	public DalRunTimeException(String arg) {
		super(arg);
	}
	public DalRunTimeException() {
		super();
	}
    public DalRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }
	public DalRunTimeException(Throwable throwable){
		super(throwable);
	}
}
