package com.dianping.zebra.monitor.sql;

import static org.junit.Assert.assertSame;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.dianping.zebra.monitor.sql.MonitorableConnection;
import com.dianping.zebra.monitor.sql.MonitorableStatement;
import com.dianping.zebra.monitor.sql.SqlMonitor;


public class MonitorableStatementTest extends BaseMonitorableUnitTest {

	protected MonitorableStatement 			monitorableStatement;
	protected Statement 					innerStatement;
	protected MonitorableConnection 		monitorableConnection;

	@Before
	public void setUp() {
		innerStatement = (Statement) createTraceableObject(Statement.class);
		monitorableConnection = EasyMock.createMock(MonitorableConnection.class);
		monitorableStatement = new MonitorableStatement(innerStatement, monitorableConnection);
		SqlMonitor sqlMonitor = EasyMock.createMock(SqlMonitor.class);
		monitorableStatement.setSqlMonitor(sqlMonitor);
	}
	
	@Test
	public void testGetConnection() throws SQLException {
		Connection connection = monitorableStatement.getConnection();
		assertSame(monitorableConnection, connection);
	}
	
	@Test
	public void testDelegatedMethods() throws SQLException {
		Object result = monitorableStatement.executeQuery(SQL_ANY);
		assertInnerMethodInvokeAndRetValEquals("executeQuery", new Object[] {SQL_ANY}, result);
		result = monitorableStatement.executeUpdate(SQL_ANY);
		assertInnerMethodInvokeAndRetValEquals("executeUpdate", new Object[] {SQL_ANY}, result);
		result = monitorableStatement.executeUpdate(SQL_ANY, Statement.RETURN_GENERATED_KEYS);
		assertInnerMethodInvokeAndRetValEquals("executeUpdate", new Object[] {SQL_ANY, Statement.RETURN_GENERATED_KEYS}, result);
		result = monitorableStatement.executeUpdate(SQL_ANY, INT_ARRARY_ANY);
		assertInnerMethodInvokeAndRetValEquals("executeUpdate", new Object[] {SQL_ANY, INT_ARRARY_ANY}, result);
		result = monitorableStatement.executeUpdate(SQL_ANY, STR_ARRARY_ANY);
		assertInnerMethodInvokeAndRetValEquals("executeUpdate", new Object[] {SQL_ANY, STR_ARRARY_ANY}, result);
		result = monitorableStatement.execute(SQL_ANY);
		assertInnerMethodInvokeAndRetValEquals("execute", new Object[] {SQL_ANY}, result);
		result = monitorableStatement.execute(SQL_ANY, Statement.RETURN_GENERATED_KEYS);
		assertInnerMethodInvokeAndRetValEquals("execute", new Object[] {SQL_ANY, Statement.RETURN_GENERATED_KEYS}, result);
		result = monitorableStatement.execute(SQL_ANY, INT_ARRARY_ANY);
		assertInnerMethodInvokeAndRetValEquals("execute", new Object[] {SQL_ANY, INT_ARRARY_ANY}, result);
		result = monitorableStatement.execute(SQL_ANY, STR_ARRARY_ANY);
		assertInnerMethodInvokeAndRetValEquals("execute", new Object[] {SQL_ANY, STR_ARRARY_ANY}, result);
		monitorableStatement.addBatch(SQL_ANY);
		assertInnerMethodInvoked("addBatch", new Object[] {SQL_ANY});
		monitorableStatement.clearBatch();
		assertInnerMethodInvoked("clearBatch", new Object[] {});
		result = monitorableStatement.executeBatch();
		assertInnerMethodInvokeAndRetValEquals("executeBatch", new Object[] {}, result);
		result = monitorableStatement.getGeneratedKeys();
		assertInnerMethodInvokeAndRetValEquals("getGeneratedKeys", new Object[] {}, result);
		monitorableStatement.cancel();
		assertInnerMethodInvoked("cancel", new Object[] {});
		monitorableStatement.close();
		assertInnerMethodInvoked("close", new Object[] {});
		resetInnerMethodInvokeRecord();
		monitorableStatement.close();
		assertInnerMethodNotInvoked("close", new Object[] {});
		
		result = monitorableStatement.getMaxFieldSize();
		assertInnerMethodInvokeAndRetValEquals("getMaxFieldSize", new Object[] {}, result);
		monitorableStatement.setMaxFieldSize(INT_ANY);
		assertInnerMethodInvoked("setMaxFieldSize", new Object[] {INT_ANY});
		result = monitorableStatement.getMaxRows();
		assertInnerMethodInvokeAndRetValEquals("getMaxRows", new Object[] {}, result);
		monitorableStatement.setMaxRows(INT_ANY);
		assertInnerMethodInvoked("setMaxRows", new Object[] {INT_ANY});
		monitorableStatement.setEscapeProcessing(BOOL_ANY);
		assertInnerMethodInvoked("setEscapeProcessing", new Object[] {BOOL_ANY});
		monitorableStatement.setQueryTimeout(INT_ANY);
		assertInnerMethodInvoked("setQueryTimeout", new Object[] {INT_ANY});
		result = monitorableStatement.getQueryTimeout();
		assertInnerMethodInvokeAndRetValEquals("getQueryTimeout", new Object[] {}, result);
		
		result = monitorableStatement.getWarnings();
		assertInnerMethodInvokeAndRetValEquals("getWarnings", new Object[] {}, result);
		monitorableStatement.clearWarnings();
		assertInnerMethodInvoked("clearWarnings", new Object[] {});
		monitorableStatement.setCursorName(STR_ANY);
		assertInnerMethodInvoked("setCursorName", new Object[] {STR_ANY});
		result = monitorableStatement.getResultSet();
		assertInnerMethodInvokeAndRetValEquals("getResultSet", new Object[] {}, result);
		result = monitorableStatement.getUpdateCount();
		assertInnerMethodInvokeAndRetValEquals("getUpdateCount", new Object[] {}, result);
		result = monitorableStatement.getMoreResults(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getMoreResults", new Object[] {INT_ANY}, result);
		result = monitorableStatement.getMoreResults();
		assertInnerMethodInvokeAndRetValEquals("getMoreResults", new Object[] {}, result);
		
		monitorableStatement.setFetchDirection(INT_ANY);
		assertInnerMethodInvoked("setFetchDirection", new Object[] {INT_ANY});
		result = monitorableStatement.getFetchDirection();
		assertInnerMethodInvokeAndRetValEquals("getFetchDirection", new Object[] {}, result);
		
		monitorableStatement.setFetchSize(INT_ANY);
		assertInnerMethodInvoked("setFetchSize", new Object[] {INT_ANY});
		result = monitorableStatement.getFetchSize();
		assertInnerMethodInvokeAndRetValEquals("getFetchSize", new Object[] {}, result);
		
		result = monitorableStatement.getResultSetConcurrency();
		assertInnerMethodInvokeAndRetValEquals("getResultSetConcurrency", new Object[] {}, result);
		result = monitorableStatement.getResultSetType();
		assertInnerMethodInvokeAndRetValEquals("getResultSetType", new Object[] {}, result);
		result = monitorableStatement.getResultSetHoldability();
		assertInnerMethodInvokeAndRetValEquals("getResultSetHoldability", new Object[] {}, result);
		
		result = monitorableStatement.isClosed();
		assertInnerMethodInvokeAndRetValEquals("isClosed", new Object[] {}, result);
		
		monitorableStatement.setPoolable(BOOL_ANY);
		assertInnerMethodInvoked("setPoolable", new Object[] {BOOL_ANY});
		result = monitorableStatement.isPoolable();
		assertInnerMethodInvokeAndRetValEquals("isPoolable", new Object[] {}, result);
		
		result = monitorableStatement.unwrap(Integer.class);
		assertInnerMethodInvokeAndRetValEquals("unwrap", new Object[] {Integer.class}, result);
		result = monitorableStatement.isWrapperFor(Integer.class);
		assertInnerMethodInvokeAndRetValEquals("isWrapperFor", new Object[] {Integer.class}, result);
	}
	
	
}
