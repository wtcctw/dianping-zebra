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
package com.dianping.zebra.parser.exception.runtime;

public class NotSupportException extends TDLRunTimeException{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1130122397745964828L;

	public NotSupportException(String msg) {
		super("not support yet."+msg);
		
	}
}
