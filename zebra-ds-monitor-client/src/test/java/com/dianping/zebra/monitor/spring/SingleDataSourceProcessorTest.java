package com.dianping.zebra.monitor.spring;

import javax.sql.DataSource;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dianping.zebra.monitor.sql.MonitorableDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/config/spring/appcontext-test.xml" })
public class SingleDataSourceProcessorTest {

	@Autowired
	private DataSource masterDs;

	@Test
	public void test_c3p0_datasource_to_single_datasource() {
		Assert.assertNotNull(masterDs);
	}
}
