package com.dianping.zebra.monitor.inspect;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.unidal.test.jetty.JettyServer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
		execute("insert into `app` (`name`) values ('test')");
		execute("update `app` set `name` = 'test2'");
		execute("delete from `app`");
		execute("select 1");
		execute("select 1");
		execute("select 1");
		execute("select 1");
		execute("select 1");
		execute("select 1");

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
		try {
			conn = ds.getConnection();
			stat = conn.createStatement();
			stat.execute(sql);
		} finally {
			if (stat != null) {
				stat.close();
			}
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
