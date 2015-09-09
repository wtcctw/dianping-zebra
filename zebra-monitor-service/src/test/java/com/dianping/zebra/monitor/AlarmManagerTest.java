package com.dianping.zebra.monitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dianping.zebra.biz.entity.AlarmResource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:config/spring/local/appcontext-*.xml",
      "classpath*:config/spring/common/appcontext-*.xml" })
public class AlarmManagerTest {
	
	@Autowired
	private AlarmManager manager;
	
	@Test
	public void testAlarm() {
		AlarmContent content = new AlarmContent("test-n1-read", "ZEBRA", "ZEBRA", "10.0.0.10", null,60);

		manager.alarm(AlarmResource.DELAY,content);
		
	}
}
