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
package com.dianping.zebra.shard.parser.sqlParser.groupFunction;


import java.util.HashMap;
import java.util.Map;


public class GroupFunctionRegister {
	public static final GroupFunctionRegister reg=new GroupFunctionRegister();
	private final static Map<String, Class<? extends GroupFunction>> funcReg=new HashMap<String, Class<? extends GroupFunction>>();
	static{
		
		funcReg.put("COUNT", Count.class);
		funcReg.put("MAX", Max.class);
		funcReg.put("ISNULL", IsNull.class);
		funcReg.put("CONCAT", Concat.class);
		funcReg.put("SUM", Sum.class);
		funcReg.put("MIN", Min.class);
		
	}
	public boolean containsKey(String key){
		return  funcReg.containsKey(key);
	}
	public GroupFunction get(String key){
		GroupFunction cls=null;
		try {
			if(key==null||key.trim().equals("")){
				throw new IllegalArgumentException("group function不能为空");
			}
			cls=funcReg.get(key.toUpperCase()).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);	
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);	
		}
		return cls;
	}
	
}
