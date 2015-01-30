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
package com.dianping.zebra.shard.parser.valueObject.function;

import com.dianping.zebra.shard.parser.valueObject.FunctionConvertor;
import com.dianping.zebra.shard.parser.valueObject.TwoOperandsFunctionObject;
import com.dianping.zebra.shard.parser.valueObject.ValueObject;
import com.dianping.zebra.shard.parser.valueObject.ValueObject.TWO_OPERANDS_FUNC;

/**
 *
 * @author qing.gu
 */
public class IFNULL implements FunctionConvertor {

	public ValueObject handle(Object... obj) {
		if (obj.length != 2) {
			throw new IllegalArgumentException("IFNULL函数的参数必须有2个,但现在有"+obj.length);
		}
		return new TwoOperandsFunctionObject(TWO_OPERANDS_FUNC.IFNULL,obj[0],obj[1]);
	}

}
