package com.dianping.zebra.admin.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dianping.zebra.admin.manager.AlarmManager;
import com.dianping.zebra.admin.manager.AlarmManager.AlarmContent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:config/spring/appcontext-*.xml")
@Ignore
public class AlarmManagerTest {
	
	@Autowired
	private AlarmManager manager;
	
	@Test
	public void testAlarm(){
		AlarmContent content = new AlarmContent("mock-puma-task", "192.168.1.1", null);
		
		manager.alarm(content);
	}

}
