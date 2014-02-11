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
import com.dianping.zebra.parser.valueobject.TwoOperandsFunctionObject;
import com.dianping.zebra.parser.valueobject.ValueObject;
import com.dianping.zebra.parser.valueobject.ValueObject.TWO_OPERANDS_FUNC;

public class NVL implements FunctionConvertor {
	public ValueObject handle(Object... obj) {
		if (obj.length != 2) {
			throw new IllegalArgumentException("NVL函数的参数必须有2个,但现在有"+obj.length);
		}
		return new TwoOperandsFunctionObject(TWO_OPERANDS_FUNC.NVL,obj[0],obj[1]);
	}

}
