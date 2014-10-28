package com.dianping.zebra.group.filter.wall;

/**
 * Created by Dozer on 9/24/14.
 */

import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.zebra.group.filter.JdbcContext;
import com.dianping.zebra.group.util.StringUtils;
import junit.framework.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class WallFilterTest {
	@Test
	public void test_addId_to_Sql() throws SQLException {
		WallFilter filter = new WallFilter();
		JdbcContext metaData = new JdbcContext();
		metaData.setSql("select * from test");
		JdbcContext innerMetaData = new JdbcContext();
		innerMetaData.setDataSourceId("test-write-1");
		metaData.setRealJdbcContext(innerMetaData);

		///*test-write-1*/select * from test
		Assert.assertEquals("/*z:ec262bf8*/select * from test", filter.addIdToSql("select * from test", metaData));
	}

	@Test
	public void test_addId_to_Sql_with_avatar() throws SQLException {
		ExecutionContextHolder.getContext().add("sql_statement_name", "test.select");

		WallFilter filter = new WallFilter();
		JdbcContext metaData = new JdbcContext();
		metaData.setSql("select * from test");
		JdbcContext innerMetaData = new JdbcContext();
		innerMetaData.setDataSourceId("test-write-1");
		metaData.setRealJdbcContext(innerMetaData);

		///*test-write-1*/select * from test
		Assert.assertEquals("/*z:89f7fec5*/select * from test", filter.addIdToSql("select * from test", metaData));
	}

	@Test
	public void test_add_id_to_sql() throws SQLException {
		WallFilter filter = new WallFilter();
		JdbcContext metaData = new JdbcContext();
		filter.addIdToSql("select * from user", metaData);
	}

	@Test
	public void test_get_id_from_sql() {
		WallFilter filter = new WallFilter();
		Assert.assertEquals(filter.getIdFromSQL("select * from user/*z:7yhgtr45*/"), "7yhgtr45");
		Assert.assertEquals(filter.getIdFromSQL("select * from user/*z:7yhgtr45ty111*/"), null);
		Assert.assertEquals(filter.getIdFromSQL("select * from user/*z:7yhgtr45*/\\r\\n/*xxx*/"), "7yhgtr45");
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
