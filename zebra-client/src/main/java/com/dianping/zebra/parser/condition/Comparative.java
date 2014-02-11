/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-21
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
package com.dianping.zebra.parser.condition;

/**
 * TODO Comment of Comparative
 * @author danson.liu
 *
 */
public class Comparative {
	
	public static final int GreaterThan = 1;
	public static final int GreaterThanOrEqual = 2;
	public static final int Equivalent = 3;
	public static final int NotEquivalent = 4;
	public static final int LessThan = 5;
	public static final int LessThanOrEqual = 6;
	public static final int Between = 7;
	
	private int comparison;
	
	private Object value;

	public Comparative(int comparison, Object value) {
		this.comparison = comparison;
		this.value = value;
	}

	public int getComparison() {
		return comparison;
	}

	public void setComparison(int comparison) {
		this.comparison = comparison;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
