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
package com.dianping.zebra.shard.parser.valueObject.variable;

import java.util.List;

import com.dianping.zebra.shard.parser.valueObject.ValueObject;

public class BindVar extends ValueObject{
	private int index;
	public BindVar(int index) {
		this.index=index;
	}
	public int getIndex(){
		return this.index;
	}
	public void appendParams(List<Object> params) {
		
	}
	public void appendSQL(StringBuilder sb) {
		sb.append("?");
		
	}
	
	@Override
	public Comparable<?> eval(List<Object> params) {
		Object object = params.get(index);
		if (object instanceof Comparable<?>) {
			return (Comparable<?>) object;
		}
		return null;
	}

}
