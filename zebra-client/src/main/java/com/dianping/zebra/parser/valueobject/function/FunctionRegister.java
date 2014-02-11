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

import java.util.HashMap;
import java.util.Map;

import com.dianping.zebra.parser.valueobject.FunctionConvertor;

public class FunctionRegister {
	public final static Map<String, FunctionConvertor> funcReg=new HashMap<String, FunctionConvertor>();
	static{
		
		funcReg.put("NVL", new NVL());
		funcReg.put("SYSDATE", new Sysdate());
		funcReg.put("IFNULL", new IFNULL());
		funcReg.put("NOW", new Now());
	}
}
