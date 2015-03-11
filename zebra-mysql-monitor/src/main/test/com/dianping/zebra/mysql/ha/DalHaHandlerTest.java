package com.dianping.zebra.mysql.ha;

import org.junit.Test;

public class DalHaHandlerTest {
	
	@Test
	public void testmarkup(){
		String dsId = "test";
		DalHaHandler.markup(dsId);
	}
	
	@Test
	public void testmarkdown(){
		String dsId = "test";
		DalHaHandler.markdown(dsId);
	}

}
