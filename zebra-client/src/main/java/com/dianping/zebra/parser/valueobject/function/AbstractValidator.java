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
package com.dianping.zebra.parser.valueobject.function;

import com.dianping.zebra.parser.valueobject.FunctionConvertor;
import com.dianping.zebra.parser.valueobject.ValueObject;

public abstract class AbstractValidator implements FunctionConvertor{
	
	public void validParam(int targetSize,int currentSize){
		
	}
	public abstract int getSize();
	public abstract ValueObject handleThis(Object...obj);
		
		public ValueObject handle(Object... obj) {
		if(obj.length>getSize()){
			throw new IllegalArgumentException("辨识函数时参数大于该函数所限定的最大个数");
		}
		return handleThis(obj);
	}
}
