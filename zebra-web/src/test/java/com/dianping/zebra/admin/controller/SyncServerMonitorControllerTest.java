package com.dianping.zebra.admin.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:config/spring/appcontext-*.xml")
public class SyncServerMonitorControllerTest {
	
	@Autowired
	private SyncServerMonitorController controller;
	
//	@Test
	public void testMonitor(){
		
		controller.monitor();
		
	}

}
