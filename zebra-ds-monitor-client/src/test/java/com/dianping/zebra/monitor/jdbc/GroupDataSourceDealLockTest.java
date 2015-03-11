package com.dianping.zebra.monitor.jdbc;

import com.dianping.zebra.Constants;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import groovy.sql.Sql;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class GroupDataSourceDealLockTest {

	private static GroupDataSource ds;

	protected static final Range<Integer> idRange = Range.closed(1, 10000);

	protected static final List<Exception> exps = new CopyOnWriteArrayList();

	@BeforeClass
	public static void init() throws SQLException {
		ds = new GroupDataSource();
		ds.setJdbcRef("deadlock.ds.v2");
		ds.setConfigManagerType(Constants.CONFIG_MANAGER_TYPE_LOCAL);
		ds.init();

		Connection conn = ds.getConnection();

		new Sql(conn).execute("DROP TABLE IF EXISTS PERSON");

		new Sql(conn).execute("create table PERSON (\n" + "  ID int identity primary key,\n" + "  NAME varchar,\n"
			+ "  LAST_NAME varchar,\n" + "  AGE  smallint\n" + ")");
		conn.close();
	}

	@AfterClass
	public static void cleanup() throws SQLException {
		ds.close();
	}

	@Test
	@Ignore
	public void test_multi_thread_long_test() throws Exception {
		while (true) {
			test_multi_thread();

			Connection conn = ds.getConnection();
			new Sql(conn).execute("Delete from PERSON");
			conn.close();

			System.out.println("I'm running! " + new Date().toString());
		}
	}

	@Test(timeout = 60 * 1000)
	public void test_multi_thread() throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					addItem();
				} catch (Exception e) {
					exps.add(e);
					e.printStackTrace();
				}
			}
		});

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					updateItem();
				} catch (Exception e) {
					exps.add(e);
					e.printStackTrace();
				}
			}
		});

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					selectItem();
				} catch (Exception e) {
					exps.add(e);
					e.printStackTrace();
				}
			}
		});

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					deleteItem();
				} catch (Exception e) {
					exps.add(e);
					e.printStackTrace();
				}
			}
		});

		executorService.shutdown();
		boolean isFinish = executorService.awaitTermination(60, TimeUnit.SECONDS);

		if (!isFinish) {
			throw new Exception("Timeout!");
		}

		for (Exception e : exps) {
			throw e;
		}
	}

	public void execute(String sqlTemplate) throws SQLException {
		for (Integer id : ContiguousSet.create(idRange, DiscreteDomain.integers())) {
			Connection conn = ds.getConnection();
			new Sql(conn).execute(String.format(sqlTemplate, id));
			conn.close();
		}
	}

	private void addItem() throws SQLException {
		execute("INSERT INTO PERSON (ID,NAME,LAST_NAME) values ( %d ,'xx','xx')");
	}

	private void updateItem() throws SQLException {
		execute("update PERSON set name = 'abc' where id = %d");
	}

	private void selectItem() throws SQLException {
		execute("select * from PERSON where id = %d");
		execute("select * from PERSON where id = %d");
		execute("select * from PERSON where id = %d");
	}

	private void deleteItem() throws SQLException {
		execute("delete from PERSON where id = %d");
	}
}
