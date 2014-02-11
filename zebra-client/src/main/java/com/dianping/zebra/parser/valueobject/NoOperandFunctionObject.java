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
package com.dianping.zebra.parser.valueobject;

import java.util.Date;
import java.util.List;

public class NoOperandFunctionObject extends ValueObject {
	private NO_OPERAND_FUNC func;

	
	public NoOperandFunctionObject(NO_OPERAND_FUNC func) {
		this.func = func;
	}

	public void appendParams(List<Object> params) {
	}

	public void appendSQL(StringBuilder sb) {
		switch (func) {
		case SYSDATE: {
			sb.append("sysdate()");
			break;
		}
		
		case NULL:{
			sb.append("null");
			break;
		}
		case NOW:
			sb.append("now()");
			break;
		default:
			throw new RuntimeException("NotSupported func type:" + func);
		}
	}

	public Comparable<?> eval(List<Object> params) {
		switch (func) {
		case SYSDATE:
			return new Date();
		case NULL:
			return null;
		case NOW:
			return new Date();
		default:
			throw new IllegalArgumentException("NoPoerandFunction");
		}
	}
	public ValueType getType() {
		return ValueType.NoOperandFunc;
	}

}
