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
import com.dianping.zebra.parser.valueobject.NoOperandFunctionObject;
import com.dianping.zebra.parser.valueobject.ValueObject;
import com.dianping.zebra.parser.valueobject.ValueObject.NO_OPERAND_FUNC;

public class Sysdate implements FunctionConvertor{
	
	public ValueObject handle(Object... obj) {
		return new NoOperandFunctionObject(NO_OPERAND_FUNC.SYSDATE);
	}
}
