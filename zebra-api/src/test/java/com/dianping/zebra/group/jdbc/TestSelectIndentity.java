package com.dianping.zebra.group.jdbc;

import com.dianping.zebra.group.performance.C3p0Case;
import com.dianping.zebra.group.performance.ZebraBaseCase;
import com.dianping.zebra.group.performance.ZebraCase;

public class TestSelectIndentity extends ZebraBaseCase{

	public TestSelectIndentity() throws Exception {
	   super("performance/appcontext-aop-core.xml");
//	   super("performance/appcontext-aop-core-c3p0.xml");
   }
	
	public static void main(String args[]) throws Exception{
		ZebraCase zebraCase = new ZebraCase();
		
		zebraCase.run();
		
//		C3p0Case zebraCase = new C3p0Case();
//
//		zebraCase.run();
	}
}
