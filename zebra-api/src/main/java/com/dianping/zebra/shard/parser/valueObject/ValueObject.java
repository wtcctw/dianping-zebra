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
package com.dianping.zebra.shard.parser.valueObject;

import java.util.List;

public abstract class ValueObject implements Value {
	protected boolean isNestSelect=false;
	
	public static enum NO_OPERAND_FUNC {
		/**
		 * sysdate
		 */
		SYSDATE,
		
		NULL,
		NOW
	}

	public static enum VAR_OPERANDS_FUNC {
		/**
		 * decode
		 */
		DECODE
	}

	public static enum TWO_OPERANDS_FUNC {
		/**
		 * oracle bitand(exp1,exp2)
		 */
		BITAND,
		/**
		 * oracle bitor(exp1,exp2)
		 */
		BITOR,
		/**
		 * tb bitxor_tb(exp1, exp2) 
		 */
		BITXOR_TB,
		/**
		 * oracle nvl(exp1,exp2)
		 */
		NVL,
		/**
		 * oracle/mysql concat(exp1,exp2)
		 */
		CONCAT,
		/**
		 *(exp1+exp2)
		 */
		ADD,
		/**
		 * (exp1-exp2)
		 */
		SUBTRACT,
		/**
		 * (exp1*exp2)
		 */
		MULTIPLY,
		/**
		 * (exp1/exp2)
		 */
		DIVIDE,
		/**
		 * IFNULL(exp1, exp2)
		 */
		IFNULL,
		MOD
	}
	public static enum ORA_FUN_TYPE{
		STR2VARLIST,STR2NUMLIST
	}
	public static enum ValueType {
		Simple, Column, NoOperandFunc, TwoOperandsFunc,OraNestFunc,VarOperandFunc,BindVar,SubQuery
	}

	public boolean isNestSelect() {
		return isNestSelect;
	}
	public String getNestedColName(){
		return null;
	}
	public Comparable<?> eval(List<Object> params) {
		return null;
	}

	

}
