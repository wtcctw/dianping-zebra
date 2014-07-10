package com.dianping.zebra.monitor.sql;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.dianping.zebra.monitor.sql.MonitorableConnection;
import com.dianping.zebra.monitor.sql.MonitorablePreparedStatement;
import com.dianping.zebra.monitor.sql.MonitorableStatement;


public class MonitorableConnectionTest extends BaseMonitorableUnitTest {
	
	private static final Savepoint 		SP_ANY 					= new MockSavepoint();
	
	private MonitorableConnection 		monitorableConnection;
	private Connection 					innerConnection;
	
	@Before
	public void setUp() {
		innerConnection = (Connection) createTraceableObject(Connection.class);
		monitorableConnection = new MonitorableConnection(innerConnection);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDelegatedMethods() throws SQLException {
		monitorableConnection.unwrap(String.class);
		assertInnerMethodInvoked("unwrap", new Object[] {String.class});
		Object retVal = monitorableConnection.isWrapperFor(Boolean.class);
		assertInnerMethodInvokeAndRetValEquals("isWrapperFor", new Object[] {Boolean.class}, retVal);
		retVal = monitorableConnection.nativeSQL(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("nativeSQL", new Object[] {STR_ANY}, retVal);
		monitorableConnection.setAutoCommit(BOOL_ANY);
		assertInnerMethodInvoked("setAutoCommit", new Object[] {BOOL_ANY});
		retVal = monitorableConnection.getAutoCommit();
		assertInnerMethodInvokeAndRetValEquals("getAutoCommit", new Object[] {}, retVal);
		monitorableConnection.commit();
		assertInnerMethodInvoked("commit", new Object[] {});
		monitorableConnection.rollback();
		assertInnerMethodInvoked("rollback", new Object[] {});
		monitorableConnection.close();
		assertInnerMethodInvoked("close", new Object[] {});
		retVal = monitorableConnection.isClosed();
		assertInnerMethodInvokeAndRetValEquals("isClosed", new Object[] {}, retVal);
		monitorableConnection.getMetaData();
		assertInnerMethodInvoked("getMetaData", new Object[] {});
		monitorableConnection.setReadOnly(BOOL_ANY);
		assertInnerMethodInvoked("setReadOnly", new Object[] {BOOL_ANY});
		retVal = monitorableConnection.isReadOnly();
		assertInnerMethodInvokeAndRetValEquals("isReadOnly", new Object[] {}, retVal);
		monitorableConnection.setCatalog(STR_ANY);
		assertInnerMethodInvoked("setCatalog", new Object[] {STR_ANY});
		retVal = monitorableConnection.getCatalog();
		assertInnerMethodInvokeAndRetValEquals("getCatalog", new Object[] {}, retVal);
		
		monitorableConnection.setTransactionIsolation(INT_ANY);
		assertInnerMethodInvoked("setTransactionIsolation", new Object[] {INT_ANY});
		retVal = monitorableConnection.getTransactionIsolation();
		assertInnerMethodInvokeAndRetValEquals("getTransactionIsolation", new Object[] {}, retVal);
		
		retVal = monitorableConnection.getWarnings();
		assertInnerMethodInvokeAndRetValEquals("getWarnings", new Object[] {}, retVal);
		monitorableConnection.clearWarnings();
		assertInnerMethodInvoked("clearWarnings", new Object[] {});
		
		retVal = monitorableConnection.getTypeMap();
		assertInnerMethodInvokeAndRetValEquals("getTypeMap", new Object[] {}, retVal);
		
		monitorableConnection.setTypeMap(MAP_ANY);
		assertInnerMethodInvoked("setTypeMap", new Object[] {MAP_ANY});
		
		retVal = monitorableConnection.getHoldability();
		assertInnerMethodInvokeAndRetValEquals("getHoldability", new Object[] {}, retVal);
		monitorableConnection.setHoldability(INT_ANY);
		assertInnerMethodInvoked("setHoldability", new Object[] {INT_ANY});
		
		retVal = monitorableConnection.getHoldability();
		assertInnerMethodInvokeAndRetValEquals("getHoldability", new Object[] {}, retVal);
		
		retVal = monitorableConnection.setSavepoint();
		assertInnerMethodInvokeAndRetValEquals("setSavepoint", new Object[] {}, retVal);
		retVal = monitorableConnection.setSavepoint(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("setSavepoint", new Object[] {STR_ANY}, retVal);
		
		monitorableConnection.rollback(SP_ANY);
		assertInnerMethodInvoked("rollback", new Object[] {SP_ANY});
		monitorableConnection.releaseSavepoint(SP_ANY);
		assertInnerMethodInvoked("releaseSavepoint", new Object[] {SP_ANY});
		
		monitorableConnection.createClob();
		assertInnerMethodInvoked("createClob", new Object[] {});
		monitorableConnection.createBlob();
		assertInnerMethodInvoked("createBlob", new Object[] {});
		monitorableConnection.createNClob();
		assertInnerMethodInvoked("createNClob", new Object[] {});
		monitorableConnection.createSQLXML();
		assertInnerMethodInvoked("createSQLXML", new Object[] {});
		
		retVal = monitorableConnection.isValid(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("isValid", new Object[] {INT_ANY}, retVal);
		
		monitorableConnection.setClientInfo(STR_ANY, STR_ANY);
		assertInnerMethodInvoked("setClientInfo", new Object[] {STR_ANY, STR_ANY});
		
		Properties properties = new Properties();
		monitorableConnection.setClientInfo(properties);
		assertInnerMethodInvoked("setClientInfo", new Object[] {properties});
		
		retVal = monitorableConnection.getClientInfo(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getClientInfo", new Object[] {STR_ANY}, retVal);
		retVal = monitorableConnection.getClientInfo();
		assertInnerMethodInvokeAndRetValEquals("getClientInfo", new Object[] {}, retVal);
		
		Object[] array = new Object[] {};
		retVal = monitorableConnection.createArrayOf(STR_ANY, array);
		assertInnerMethodInvokeAndRetValEquals("createArrayOf", new Object[] {STR_ANY, array}, retVal);
		
		retVal = monitorableConnection.createStruct(STR_ANY, array);
		assertInnerMethodInvokeAndRetValEquals("createStruct", new Object[] {STR_ANY, array}, retVal);
	}
	
	@Test
	public void testGetInnerConnection() {
		Connection innerConnection = monitorableConnection.getInnerConnection();
		assertSame(this.innerConnection, innerConnection);
	}
	
	@Test
	public void testCreateStatement1() throws SQLException {
		Statement statement = monitorableConnection.createStatement();
		assertNotNull(statement);
		assertTrue(statement instanceof MonitorableStatement);
	}
	
	@Test
	public void testCreateStatement2() throws SQLException {
		Statement statement = monitorableConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		assertNotNull(statement);
		assertTrue(statement instanceof MonitorableStatement);
	}
	
	@Test
	public void testCreateStatement3() throws SQLException {
		Statement statement = monitorableConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,
				ResultSet.HOLD_CURSORS_OVER_COMMIT);
		assertNotNull(statement);
		assertTrue(statement instanceof MonitorableStatement);
	}
	
	@Test
	public void testPrepareStatement1() throws SQLException {
		PreparedStatement prepareStatement = monitorableConnection.prepareStatement(SQL_ANY);
		assertNotNull(prepareStatement);
		assertTrue(prepareStatement instanceof MonitorablePreparedStatement);
	}
	
	@Test
	public void testPrepareStatement2() throws SQLException {
		PreparedStatement prepareStatement = monitorableConnection.prepareStatement(SQL_ANY, Statement.RETURN_GENERATED_KEYS);
		assertNotNull(prepareStatement);
		assertTrue(prepareStatement instanceof MonitorablePreparedStatement);
	}
	
	@Test
	public void testPrepareStatement3() throws SQLException {
		PreparedStatement prepareStatement = monitorableConnection.prepareStatement(SQL_ANY, new int[] {3});
		assertNotNull(prepareStatement);
		assertTrue(prepareStatement instanceof MonitorablePreparedStatement);
	}
	
	@Test
	public void testPrepareStatement4() throws SQLException {
		PreparedStatement prepareStatement = monitorableConnection.prepareStatement(SQL_ANY, new String[] {"Name"});
		assertNotNull(prepareStatement);
		assertTrue(prepareStatement instanceof MonitorablePreparedStatement);
	}
	
	@Test
	public void testPrepareStatement5() throws SQLException {
		PreparedStatement prepareStatement = monitorableConnection.prepareStatement(SQL_ANY, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		assertNotNull(prepareStatement);
		assertTrue(prepareStatement instanceof MonitorablePreparedStatement);
	}
	
	@Test
	public void testPrepareStatement6() throws SQLException {
		PreparedStatement prepareStatement = monitorableConnection.prepareStatement(SQL_ANY, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
		assertNotNull(prepareStatement);
		assertTrue(prepareStatement instanceof MonitorablePreparedStatement);
	}
	
	@Test
	public void testPrepareCall1() throws SQLException {
		CallableStatement callStatement = monitorableConnection.prepareCall(SQL_ANY);
		assertNotNull(callStatement);
		assertTrue(callStatement instanceof CallableStatement);
	}
	
	@Test
	public void testPrepareCall2() throws SQLException {
		CallableStatement callStatement = monitorableConnection.prepareCall(SQL_ANY, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY);
		assertNotNull(callStatement);
		assertTrue(callStatement instanceof CallableStatement);
	}
	
	@Test
	public void testPrepareCall3() throws SQLException {
		CallableStatement callStatement = monitorableConnection.prepareCall(SQL_ANY, ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
		assertNotNull(callStatement);
		assertTrue(callStatement instanceof CallableStatement);
	}
	
	static class MockSavepoint implements Savepoint {
		public int getSavepointId() throws SQLException {
			return 0;
		}
		public String getSavepointName() throws SQLException {
			return null;
		}
	}
 
}
