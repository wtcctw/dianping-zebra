package com.dianping.zebra.admin.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dianping.zebra.admin.manager.MHAAlarmManager;
import com.dianping.zebra.admin.util.AlarmContent;
import com.dianping.zebra.biz.entity.AlarmResource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:config/spring/local/appcontext-*.xml",
		"classpath*:config/spring/common/appcontext-*.xml" })
public class TestTemplate {

	@Autowired
	MHAAlarmManager testMHA;

	@Test
	public void TestMHA(){
		testMHA.alarm(AlarmResource.MARKDOWN, new AlarmContent("zebra-n1-write","MHA","MHA","127.0.0.1","MarkDown by MHA"));
	}
}
