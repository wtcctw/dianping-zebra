package com.dianping.zebra.monitor;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/appcontext-test.xml")
public class SingleDataSourceC3P0AdapterSpringTest {

	@Autowired
	private DataSource ds;

	@Test
	@Ignore
	public void test_jdbc() throws SQLException, PropertyVetoException {
		Connection conn = ds.getConnection();
		Statement stm = conn.createStatement();
		ResultSet result = stm.executeQuery("select * from person");
		Assert.assertTrue(result.next());
		Assert.assertEquals("1", result.getString(1));
	}
}
