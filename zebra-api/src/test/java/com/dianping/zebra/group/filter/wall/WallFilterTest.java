package com.dianping.zebra.group.filter.wall;

/**
 * Created by Dozer on 9/24/14.
 */

import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Test;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.datasources.SingleConnection;
import com.dianping.zebra.group.filter.JdbcFilter;
import com.google.common.collect.Lists;

public class WallFilterTest {
	@Test
	public void test_addId_to_Sql() throws SQLException {
		WallFilter filter = new WallFilter();
		// String sql = "select * from test";
		DataSourceConfig config = new DataSourceConfig();
		config.setId("test-write-1");
		SingleConnection conn = new SingleConnection(null, config, null, Lists.<JdbcFilter> newArrayList(filter));

		// /*test-write-1*/select * from test
		Assert.assertEquals("/*id:ec262bf8*/select * from test", filter.sql(conn, "select * from test", true, null));
	}

	@Test
	public void test_addId_to_Sql_with_avatar() throws SQLException {
		WallFilter filter = new WallFilter();
		// String sql = "select * from test";
		DataSourceConfig config = new DataSourceConfig();
		config.setId("test-write-1");
		SingleConnection conn = new SingleConnection(null, config, null, Lists.<JdbcFilter> newArrayList(filter));

		// /*test-write-1*/select * from test
		Assert.assertEquals("/*id:ec262bf8*/select * from test", filter.sql(conn, "select * from test", true, null));
	}

	@Test(expected = SQLException.class)
	public void test_sql_in_blackList() throws SQLException {
		WallFilter filter = new WallFilter();
		WallFilter.getBlackList().add("7yhgtr45ty");
		filter.checkBlackList("select * from Test", "7yhgtr45ty");
	}

	@Test
	public void test_sql_not_in_blackList() throws SQLException {
		WallFilter filter = new WallFilter();
		WallFilter.getBlackList().add("7yhgtr45ty");
		filter.checkBlackList("select * from Test", "asdad2");

	}

	@Test
	public void test_load_blackList_from_config() throws SQLException {
		WallFilter filter = new WallFilter();
		filter.setConfigManagerType("local");
		filter.init();
		Assert.assertEquals(WallFilter.getBlackList().size(), 3);
		Assert.assertTrue(WallFilter.getBlackList().contains("aaa"));
		Assert.assertTrue(WallFilter.getBlackList().contains("bbb"));
		Assert.assertTrue(WallFilter.getBlackList().contains("ccc"));
	}
}
