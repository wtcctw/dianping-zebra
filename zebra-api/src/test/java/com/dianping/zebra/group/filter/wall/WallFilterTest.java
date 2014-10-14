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
	public void generateIdPerformanceTest() throws NoSuchAlgorithmException {
		long startTime = System.currentTimeMillis();
		for (int k = 0; k < 10000; k++) {
			StringUtils.sha1(String.valueOf(k % 10));
		}
		long time1 = System.currentTimeMillis() - startTime;

		WallFilter filter = new WallFilter();
		startTime = System.currentTimeMillis();
		for (int k = 0; k < 10000; k++) {
			JdbcContext metaData = new JdbcContext();
			metaData.setSql("select * from `Test` where id = " + String.valueOf(k % 10));
			filter.generateId(metaData);
		}

		long time2 = System.currentTimeMillis() - startTime;

		System.out.println(time1);
		System.out.println(time2);

		Assert.assertTrue(time2 < time1);
	}

	@Test
	public void test_addId_to_Sql() {
		WallFilter filter = new WallFilter();
		JdbcContext metaData = new JdbcContext();
		metaData.setSql("select * from test");
		JdbcContext innerMetaData = new JdbcContext();
		innerMetaData.setDataSourceId("test-write-1");
		metaData.setRealJdbcContext(innerMetaData);

		///*test-write-1*/select * from test
		Assert.assertEquals("/*z:d1d26296*/select * from test", filter.addIdToSql("select * from test", metaData));
	}

	@Test
	public void test_addId_to_Sql_with_avatar() {
		ExecutionContextHolder.getContext().add("sql_statement_name", "test.select");

		WallFilter filter = new WallFilter();
		JdbcContext metaData = new JdbcContext();
		metaData.setSql("select * from test");
		JdbcContext innerMetaData = new JdbcContext();
		innerMetaData.setDataSourceId("test-write-1");
		metaData.setRealJdbcContext(innerMetaData);

		///*test-write-1*/select * from test
		Assert.assertEquals("/*z:ea013b8c*/select * from test", filter.addIdToSql("select * from test", metaData));
	}

	@Test
	public void test_add_id_to_sql() {
		WallFilter filter = new WallFilter();
		JdbcContext metaData = new JdbcContext();
		filter.addIdToSql("select * from user", metaData);
	}

	@Test
	public void test_get_id_from_sql() {
		WallFilter filter = new WallFilter();
		Assert.assertEquals("7yhgtr45ty", filter.getIdFromSQL("select * from user/*z:7yhgtr45ty*/"));
		Assert.assertEquals(null, filter.getIdFromSQL("select * from user/*z:7yhgtr45ty111*/"));
		Assert.assertEquals("7yhgtr45ty", filter.getIdFromSQL("select * from user/*z:7yhgtr45ty*/\\r\\n/*xxx*/"));
	}

	@Test(expected = SQLException.class)
	public void test_sql_in_blackList() throws SQLException {
		WallFilter filter = new WallFilter();
		WallFilter.blackList.add("7yhgtr45ty");
		JdbcContext context = new JdbcContext();
		context.setSql("select * from Test/*z:7yhgtr45ty*/");
		filter.checkBlackList(context);
	}

	@Test
	public void test_sql_not_in_blackList() throws SQLException {
		WallFilter filter = new WallFilter();
		WallFilter.blackList.add("7yhgtr45ty");
		JdbcContext context = new JdbcContext();
		context.setSql("select * from Test/*z:7yhg5ty*/");
		filter.checkBlackList(context);
	}
}
