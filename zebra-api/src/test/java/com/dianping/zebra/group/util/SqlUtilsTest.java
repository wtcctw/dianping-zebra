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
	
	@Test
	public void testInsert() throws SQLException {
		String sql = "INSERT into table select from table2";
		SqlType sqlType = SqlUtils.getSqlType(sql);
		Assert.assertEquals(SqlType.INSERT, sqlType);
		Assert.assertEquals(false, sqlType.isRead());
	}

	@Test
	public void testDelete() throws SQLException {
		String sql = "delete from table where id = 1";
		SqlType sqlType = SqlUtils.getSqlType(sql);
		Assert.assertEquals(SqlType.DELETE, sqlType);
		Assert.assertEquals(false, sqlType.isRead());
	}
	
	@Test
	public void testExecute() throws SQLException {
		String sql = "{call sp_proc}";
		SqlType sqlType = SqlUtils.getSqlType(sql);
		Assert.assertEquals(SqlType.EXECUTE, sqlType);
		Assert.assertEquals(false, sqlType.isRead());
	}
	
	@Test
	public void testSelectIdentity() throws SQLException {
		String sql = "select @@identity";
		SqlType sqlType = SqlUtils.getSqlType(sql);
		Assert.assertEquals(SqlType.SELECT_FOR_IDENTITY, sqlType);
		Assert.assertEquals(false, sqlType.isRead());
		
		String sql2 = "select last_insert_id()";
		SqlType sqlType2 = SqlUtils.getSqlType(sql2);
		Assert.assertEquals(SqlType.SELECT_FOR_IDENTITY, sqlType2);
		Assert.assertEquals(false, sqlType.isRead());
	}

}
