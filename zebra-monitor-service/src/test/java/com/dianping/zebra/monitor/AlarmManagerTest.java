package com.dianping.zebra.monitor;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dianping.zebra.monitor.AlarmManager.AlarmContent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:config/spring/appcontext-*.xml")
@Ignore
public class AlarmManagerTest {
	
	@Autowired
	private AlarmManager manager;
	
	@Test
	public void testAlarm(){
		AlarmContent content = new AlarmContent("zebra-n1-write", "markDown");
		
		manager.alarm(content);
	}
}
