package com.dianping.zebra.monitor.inspect;

import com.dianping.zebra.Constants;
import com.dianping.zebra.group.jdbc.GroupDataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.unidal.test.jetty.JettyServer;

import java.sql.*;

@RunWith(JUnit4.class)
@Ignore
public class TestServer extends JettyServer {
	GroupDataSource ds;

	public static void main(String[] args) throws Exception {
		TestServer server = new TestServer();

		server.startServer();
		server.startWebapp();
		server.stopServer();
	}

	@Before
	public void before() throws Exception {
		System.setProperty("devMode", "true");
		super.startServer();
	}

	private void callDataSource() throws SQLException {
		ds = new GroupDataSource();
		ds.setConfigManagerType(Constants.CONFIG_MANAGER_TYPE_LOCAL);
		ds.setJdbcRef("sample.ds.v2");
		ds.setFilter("mock,stat,wall,cat");
		ds.init();

		createTable();
		executeWithTransaction("update `app` set `name` = 'ttt'", "update `app` set `name` = 'ttt2'");
		executeWithBatch("update `app` set `name` = 'ttt'", "update `app` set `name` = 'ttt2'");
		executeWithPreparedBatch("update `app` set `name` = ?", "aa", "bb", "cc");
		execute("insert into `app` (`name`) values ('test')");
		execute("update `app` set `name` = (select 1)");//todo: 需要支持子查询
		execute("update `app` set `name` = 'xxx'");
		execute("update `app` set `name1` = 'test2'");
		execute("delete from `app`");
		execute("select 1");
		execute("select 1");
		execute("select 1");
		execute("select 1");
		execute("select 1");
		execute("select * from `not_exist`");
		execute("select 1");
		execute("select from user"); //error sql
		ds.setMaxPoolSize(100);
		ds.setMaxPoolSize(101);

		execute("select 1");
	}

	private void createTable() throws SQLException {
		execute("CREATE TABLE IF NOT EXISTS app ( name varchar(100) )");
	}

	void execute(String sql) throws SQLException {
		Connection conn = null;
		Statement stat = null;
		ResultSet result;
		try {
			conn = ds.getConnection();
			stat = conn.createStatement();
			stat.execute(sql);
			result = stat.getResultSet();

			if (result != null) {
				while (result.next()) {
				}
			}
		} catch (SQLException ignore) {

		} finally {
			if (stat != null) {
				stat.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	void executeWithBatch(String... sqls) throws SQLException {
		Connection conn = ds.getConnection();

		Statement stmt = conn.createStatement();

		for (String sql : sqls) {
			stmt.addBatch(sql);
		}

		stmt.executeBatch();
		stmt.close();
		conn.close();
	}

	void executeWithPreparedBatch(String sql, String... args) throws SQLException {
		Connection conn = ds.getConnection();

		PreparedStatement stmt = conn.prepareStatement(sql);

		for (int k = 0; k < args.length; k++) {
			stmt.setString(1, args[k]);
			stmt.addBatch();
		}

		stmt.executeBatch();
		stmt.close();
		conn.close();
	}

	void executeWithTransaction(String... sqls) throws SQLException {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			for (String sql : sqls) {
				Statement stat = conn.createStatement();
				stat.execute(sql);
				stat.close();
			}
			conn.commit();
		} catch (SQLException ignore) {

		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	@Override
	protected String getContextPath() {
		return "/";
	}

	@Override
	protected int getServerPort() {
		return 3473;
	}

	@Test
	public void startWebapp() throws Exception {
		// open the page in the default browser
		callDataSource();

		display("/inspect/status/html");

		waitForAnyKey();
	}
}
