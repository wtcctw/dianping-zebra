package com.dianping.zebra.admin.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:config/spring/local/appcontext-*.xml",
      "classpath*:config/spring/common/appcontext-*.xml" })
public class JdbcRefTest {

	@Autowired
	MonitorController testMC;

	@Test
	public void TestMC() {
		System.out.println(testMC.getJdbcRefList());
	}
}
