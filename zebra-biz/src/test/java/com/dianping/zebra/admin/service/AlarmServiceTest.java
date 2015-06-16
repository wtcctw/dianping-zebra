package com.dianping.zebra.admin.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:config/spring/appcontext-*.xml")
@Ignore
public class AlarmServiceTest {
	
	@Autowired
	private AlarmService alarmService;

	@Test
	public void testSms(){
		boolean success = alarmService.sendSms("15216716460", "zebra test");
		
		System.out.println(success);
	}
	
	@Test
	public void testWexin(){
		boolean success = alarmService.sendWeixin("hao.zhu@dianping.com", "test", "test");
		
		System.out.println(success);
	}

}
