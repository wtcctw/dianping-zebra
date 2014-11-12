package com.dianping.zebra.group.filter.wall;

/**
 * Created by Dozer on 9/24/14.
 */

import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.datasources.SingleConnection;
import com.dianping.zebra.group.filter.JdbcFilter;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class WallFilterTest {
	@Test
	public void test_addId_to_Sql() throws SQLException {
		WallFilter filter = new WallFilter();
		String sql = "select * from test";
		DataSourceConfig config = new DataSourceConfig();
		config.setId("test-write-1");
		SingleConnection conn = new SingleConnection(null, config, null, null,
			  Lists.<JdbcFilter>newArrayList(filter));

		///*test-write-1*/select * from test
		Assert.assertEquals("/*z:ec262bf8*/select * from test", filter.sql(conn, "select * from test", null));
	}

	@Test
	public void test_addId_to_Sql_with_avatar() throws SQLException {
		ExecutionContextHolder.getContext().add("sql_statement_name", "test.select");

		WallFilter filter = new WallFilter();
		String sql = "select * from test";
		DataSourceConfig config = new DataSourceConfig();
		config.setId("test-write-1");
		SingleConnection conn = new SingleConnection(null, config, null, null,
			  Lists.<JdbcFilter>newArrayList(filter));

		///*test-write-1*/select * from test
		Assert.assertEquals("/*z:89f7fec5*/select * from test", filter.sql(conn, "select * from test", null));
	}

	@Test(expected = SQLException.class)
	public void test_sql_in_blackList() throws SQLException {
		WallFilter filter = new WallFilter();
		WallFilter.blackList.add("7yhgtr45ty");
		filter.checkBlackList("select * from Test", "7yhgtr45ty");
	}

	@Test
	public void test_sql_not_in_blackList() throws SQLException {
		WallFilter filter = new WallFilter();
		WallFilter.blackList.add("7yhgtr45ty");
		filter.checkBlackList("select * from Test", "asdad2");

	}

	@Test
	public void test_load_blackList_from_config() throws SQLException {
		WallFilter filter = new WallFilter();
		filter.configManagerType = "local";
		filter.init();
		Assert.assertEquals(WallFilter.blackList.size(), 3);
		Assert.assertTrue(WallFilter.blackList.contains("aaa"));
		Assert.assertTrue(WallFilter.blackList.contains("bbb"));
		Assert.assertTrue(WallFilter.blackList.contains("ccc"));
	}
}
