package com.dianping.zebra.monitor.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import com.dianping.zebra.monitor.sql.MonitorableConnection;
import com.dianping.zebra.monitor.sql.MonitorableDataSource;

public class MonitorableDataSourceTest {
	
	private static final int 			LOGIN_TIMEOUT 			= 100;

	private MonitorableDataSource 		monitorableDataSource;
	
	private DataSource 					innerDataSource;
	
	private static Object 				unwrappedObject 		= new Object();
	
	@Before
	public void setUp() {
		innerDataSource = new MockDataSource();
		monitorableDataSource = new MonitorableDataSource(innerDataSource);
	}
	
	@Test
	public void testGetConnection() throws SQLException {
		Connection connection = monitorableDataSource.getConnection();
		assertNotNull(connection);
		assertTrue(connection instanceof MonitorableConnection);
	}
	
	@Test
	public void testGetConnection2() throws SQLException {
		Connection connection = monitorableDataSource.getConnection("dper", "passwd");
		assertNotNull(connection);
		assertTrue(connection instanceof MonitorableConnection);
	}

	@Test
	public void testGetLogWriter() throws SQLException {
		PrintWriter printWriter = new PrintWriter(new ByteArrayOutputStream());
		innerDataSource.setLogWriter(printWriter);
		assertSame(printWriter, monitorableDataSource.getLogWriter());
	}

	@Test
	public void testSetLogWriter() throws SQLException {
		PrintWriter printWriter = new PrintWriter(new ByteArrayOutputStream());
		monitorableDataSource.setLogWriter(printWriter);
		assertSame(printWriter, innerDataSource.getLogWriter());
	}

	@Test
	public void testSetLoginTimeout() throws SQLException {
		monitorableDataSource.setLoginTimeout(LOGIN_TIMEOUT);
		assertEquals(LOGIN_TIMEOUT, innerDataSource.getLoginTimeout());
	}

	@Test
	public void testGetLoginTimeout() throws SQLException {
		innerDataSource.setLoginTimeout(LOGIN_TIMEOUT);
		assertEquals(LOGIN_TIMEOUT, monitorableDataSource.getLoginTimeout());
	}

	@Test
	public void testUnwrap() throws SQLException {
		assertSame(unwrappedObject, monitorableDataSource.unwrap(Object.class));
	}

	@Test
	public void testIsWrapperFor() throws SQLException {
		assertTrue(monitorableDataSource.isWrapperFor(String.class));
		assertFalse(monitorableDataSource.isWrapperFor(Integer.class));
	}

	static class MockDataSource implements DataSource {
		private PrintWriter out;
		private int loginTimeOut;
		public PrintWriter getLogWriter() throws SQLException {
			return out;
		}
		public void setLogWriter(PrintWriter out) throws SQLException {
			this.out = out;
		}
		public void setLoginTimeout(int seconds) throws SQLException {
			this.loginTimeOut = seconds;
		}
		public int getLoginTimeout() throws SQLException {
			return loginTimeOut;
		}
		@SuppressWarnings("unchecked")
		public <T> T unwrap(Class<T> iface) throws SQLException {
			return (T) unwrappedObject;
		}
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			return String.class == iface;
		}
		public Connection getConnection() throws SQLException {
			return null;
		}
		public Connection getConnection(String username, String password) throws SQLException {
			return null;
		}
	}

}
