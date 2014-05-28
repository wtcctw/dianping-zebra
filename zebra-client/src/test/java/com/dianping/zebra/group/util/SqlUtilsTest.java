package com.dianping.zebra.group.util;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class SqlUtilsTest {

	@Test
	public void testSelect() throws SQLException {
		String sql = "select * from xx";
		SqlType sqlType = SqlUtils.getSqlType(sql);
		Assert.assertEquals(SqlType.SELECT, sqlType);
		Assert.assertEquals(true, sqlType.isRead());
	}

	@Test
	public void testSelectFOrUpdate() throws SQLException {
		String sql = "select * from xx for update";
		SqlType sqlType = SqlUtils.getSqlType(sql);
		Assert.assertEquals(SqlType.SELECT_FOR_UPDATE, sqlType);
		Assert.assertEquals(false, sqlType.isRead());
	}

	@Test
	public void testUpdate() throws SQLException {
		String sql = "update table set xx=1";
		SqlType sqlType = SqlUtils.getSqlType(sql);
		Assert.assertEquals(SqlType.UPDATE, sqlType);
		Assert.assertEquals(false, sqlType.isRead());
	}

}
