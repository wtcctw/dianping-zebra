package com.dianping.zebra.monitor.sql;

import java.io.ByteArrayInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.SQLException;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;


public class MonitorableCallableStatementTest extends BaseMonitorableUnitTest {

	private MonitorableCallableStatement 	monitorableCallableStatement;
	private CallableStatement 				innerCallableStatement;
	private MonitorableConnection 			monitorableConnection;

	@Before
	public void setUp() {
		monitorableConnection = EasyMock.createMock(MonitorableConnection.class);
		innerCallableStatement = (CallableStatement) createTraceableObject(CallableStatement.class);
		monitorableCallableStatement = new MonitorableCallableStatement(innerCallableStatement, SQL_ANY, monitorableConnection);
		SqlMonitor sqlMonitor = EasyMock.createMock(SqlMonitor.class);
		monitorableCallableStatement.setSqlMonitor(sqlMonitor);
	}
	
	@Test
	public void testDelegatedMethods() throws SQLException {
		monitorableCallableStatement.registerOutParameter(INT_ANY, INT_ANY);
		assertInnerMethodInvoked("registerOutParameter", new Object[] {INT_ANY, INT_ANY});
		monitorableCallableStatement.registerOutParameter(INT_ANY, INT_ANY, INT_ANY);
		assertInnerMethodInvoked("registerOutParameter", new Object[] {INT_ANY, INT_ANY, INT_ANY});
		monitorableCallableStatement.registerOutParameter(INT_ANY, INT_ANY, STR_ANY);
		assertInnerMethodInvoked("registerOutParameter", new Object[] {INT_ANY, INT_ANY, STR_ANY});
		monitorableCallableStatement.registerOutParameter(STR_ANY, INT_ANY);
		assertInnerMethodInvoked("registerOutParameter", new Object[] {STR_ANY, INT_ANY});
		monitorableCallableStatement.registerOutParameter(STR_ANY, INT_ANY, INT_ANY);
		assertInnerMethodInvoked("registerOutParameter", new Object[] {STR_ANY, INT_ANY, INT_ANY});
		monitorableCallableStatement.registerOutParameter(STR_ANY, INT_ANY, STR_ANY);
		assertInnerMethodInvoked("registerOutParameter", new Object[] {STR_ANY, INT_ANY, STR_ANY});
		Object result = monitorableCallableStatement.wasNull();
		assertInnerMethodInvokeAndRetValEquals("wasNull", new Object[] {}, result);
		result = monitorableCallableStatement.getString(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getString", new Object[] {INT_ANY}, result);
		result = monitorableCallableStatement.getBoolean(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getBoolean", new Object[] {INT_ANY}, result);
		result = monitorableCallableStatement.getByte(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getByte", new Object[] {INT_ANY}, result);
		
		result = monitorableCallableStatement.getShort(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getShort", new Object[] {INT_ANY}, result);
		result = monitorableCallableStatement.getInt(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getInt", new Object[] {INT_ANY}, result);
		result = monitorableCallableStatement.getLong(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getLong", new Object[] {INT_ANY}, result);
		result = monitorableCallableStatement.getFloat(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getFloat", new Object[] {INT_ANY}, result);
		
		result = monitorableCallableStatement.getDouble(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getDouble", new Object[] {INT_ANY}, result);
		result = monitorableCallableStatement.getBigDecimal(INT_ANY, INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getBigDecimal", new Object[] {INT_ANY, INT_ANY}, result);
		result = monitorableCallableStatement.getBytes(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getBytes", new Object[] {INT_ANY}, result);
		result = monitorableCallableStatement.getDate(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getDate", new Object[] {INT_ANY}, result);
		
		result = monitorableCallableStatement.getTime(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getTime", new Object[] {INT_ANY}, result);
		result = monitorableCallableStatement.getTimestamp(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getTimestamp", new Object[] {INT_ANY}, result);
		result = monitorableCallableStatement.getObject(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getObject", new Object[] {INT_ANY}, result);
		result = monitorableCallableStatement.getBigDecimal(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getBigDecimal", new Object[] {INT_ANY}, result);
		
		result = monitorableCallableStatement.getObject(INT_ANY, MAP_ANY);
		assertInnerMethodInvokeAndRetValEquals("getObject", new Object[] {INT_ANY, MAP_ANY}, result);
		result = monitorableCallableStatement.getRef(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getRef", new Object[] {INT_ANY}, result);
		result = monitorableCallableStatement.getBlob(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getBlob", new Object[] {INT_ANY}, result);
		result = monitorableCallableStatement.getClob(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getClob", new Object[] {INT_ANY}, result);
		
		result = monitorableCallableStatement.getArray(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getArray", new Object[] {INT_ANY}, result);
		result = monitorableCallableStatement.getDate(INT_ANY, CALENDAR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getDate", new Object[] {INT_ANY, CALENDAR_ANY}, result);
		result = monitorableCallableStatement.getTime(INT_ANY, CALENDAR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getTime", new Object[] {INT_ANY, CALENDAR_ANY}, result);
		result = monitorableCallableStatement.getTimestamp(INT_ANY, CALENDAR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getTimestamp", new Object[] {INT_ANY, CALENDAR_ANY}, result);
		
		result = monitorableCallableStatement.getURL(INT_ANY);
		assertInnerMethodInvokeAndRetValEquals("getURL", new Object[] {INT_ANY}, result);
		monitorableCallableStatement.setNull(STR_ANY, INT_ANY);
		assertInnerMethodInvoked("setNull", new Object[] {STR_ANY, INT_ANY});
		monitorableCallableStatement.setBoolean(STR_ANY, BOOL_ANY);
		assertInnerMethodInvoked("setBoolean", new Object[] {STR_ANY, BOOL_ANY});
		
		monitorableCallableStatement.setByte(STR_ANY, BYTE_ANY);
		assertInnerMethodInvoked("setByte", new Object[] {STR_ANY, BYTE_ANY});
		monitorableCallableStatement.setShort(STR_ANY, SHORT_ANY);
		assertInnerMethodInvoked("setShort", new Object[] {STR_ANY, SHORT_ANY});
		monitorableCallableStatement.setInt(STR_ANY, INT_ANY);
		assertInnerMethodInvoked("setInt", new Object[] {STR_ANY, INT_ANY});
		monitorableCallableStatement.setLong(STR_ANY, LONG_ANY);
		assertInnerMethodInvoked("setLong", new Object[] {STR_ANY, LONG_ANY});
		
		monitorableCallableStatement.setFloat(STR_ANY, FLOAT_ANY);
		assertInnerMethodInvoked("setFloat", new Object[] {STR_ANY, FLOAT_ANY});
		monitorableCallableStatement.setDouble(STR_ANY, DOUBLE_ANY);
		assertInnerMethodInvoked("setDouble", new Object[] {STR_ANY, DOUBLE_ANY});
		monitorableCallableStatement.setBigDecimal(STR_ANY, DECIMAL_ANY);
		assertInnerMethodInvoked("setBigDecimal", new Object[] {STR_ANY, DECIMAL_ANY});
		monitorableCallableStatement.setString(STR_ANY, STR_ANY);
		assertInnerMethodInvoked("setString", new Object[] {STR_ANY, STR_ANY});
		
		monitorableCallableStatement.setBytes(STR_ANY, BYTE_ARRARY_ANY);
		assertInnerMethodInvoked("setBytes", new Object[] {STR_ANY, BYTE_ARRARY_ANY});
		monitorableCallableStatement.setDate(STR_ANY, DATE_ANY);
		assertInnerMethodInvoked("setDate", new Object[] {STR_ANY, DATE_ANY});
		monitorableCallableStatement.setTime(STR_ANY, TIME_ANY);
		assertInnerMethodInvoked("setTime", new Object[] {STR_ANY, TIME_ANY});
		monitorableCallableStatement.setTimestamp(STR_ANY, TIMESTAMP_ANY);
		assertInnerMethodInvoked("setTimestamp", new Object[] {STR_ANY, TIMESTAMP_ANY});
		
		ByteArrayInputStream bais = new ByteArrayInputStream(new byte[0]);
		monitorableCallableStatement.setAsciiStream(STR_ANY, bais, INT_ANY);
		assertInnerMethodInvoked("setAsciiStream", new Object[] {STR_ANY, bais, INT_ANY});
		monitorableCallableStatement.setBinaryStream(STR_ANY, bais, INT_ANY);
		assertInnerMethodInvoked("setBinaryStream", new Object[] {STR_ANY, bais, INT_ANY});
		monitorableCallableStatement.setObject(STR_ANY, bais, INT_ANY, INT_ANY);
		assertInnerMethodInvoked("setObject", new Object[] {STR_ANY, bais, INT_ANY, INT_ANY});
		monitorableCallableStatement.setObject(STR_ANY, OBJECT_ANY, INT_ANY);
		assertInnerMethodInvoked("setObject", new Object[] {STR_ANY, OBJECT_ANY, INT_ANY});
		
		monitorableCallableStatement.setObject(STR_ANY, OBJECT_ANY);
		assertInnerMethodInvoked("setObject", new Object[] {STR_ANY, OBJECT_ANY});
		Reader reader = new StringReader(STR_ANY);
		monitorableCallableStatement.setCharacterStream(STR_ANY, reader, INT_ANY);
		assertInnerMethodInvoked("setCharacterStream", new Object[] {STR_ANY, reader, INT_ANY});
		monitorableCallableStatement.setDate(STR_ANY, DATE_ANY, CALENDAR_ANY);
		assertInnerMethodInvoked("setDate", new Object[] {STR_ANY, DATE_ANY, CALENDAR_ANY});
		monitorableCallableStatement.setTime(STR_ANY, TIME_ANY, CALENDAR_ANY);
		assertInnerMethodInvoked("setTime", new Object[] {STR_ANY, TIME_ANY, CALENDAR_ANY});
		
		monitorableCallableStatement.setTimestamp(STR_ANY, TIMESTAMP_ANY, CALENDAR_ANY);
		assertInnerMethodInvoked("setTimestamp", new Object[] {STR_ANY, TIMESTAMP_ANY, CALENDAR_ANY});
		monitorableCallableStatement.setNull(STR_ANY, INT_ANY, STR_ANY);
		assertInnerMethodInvoked("setNull", new Object[] {STR_ANY, INT_ANY, STR_ANY});
		
		result = monitorableCallableStatement.getString(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getString", new Object[] {STR_ANY}, result);
		result = monitorableCallableStatement.getBoolean(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getBoolean", new Object[] {STR_ANY}, result);
		result = monitorableCallableStatement.getByte(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getByte", new Object[] {STR_ANY}, result);
		result = monitorableCallableStatement.getShort(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getShort", new Object[] {STR_ANY}, result);
		result = monitorableCallableStatement.getInt(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getInt", new Object[] {STR_ANY}, result);
		
		result = monitorableCallableStatement.getLong(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getLong", new Object[] {STR_ANY}, result);
		result = monitorableCallableStatement.getFloat(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getFloat", new Object[] {STR_ANY}, result);
		result = monitorableCallableStatement.getDouble(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getDouble", new Object[] {STR_ANY}, result);
		result = monitorableCallableStatement.getBytes(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getBytes", new Object[] {STR_ANY}, result);
		result = monitorableCallableStatement.getDate(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getDate", new Object[] {STR_ANY}, result);
		result = monitorableCallableStatement.getTime(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getTime", new Object[] {STR_ANY}, result);
		result = monitorableCallableStatement.getTimestamp(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getTimestamp", new Object[] {STR_ANY}, result);
		
		result = monitorableCallableStatement.getObject(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getObject", new Object[] {STR_ANY}, result);
		result = monitorableCallableStatement.getBigDecimal(STR_ANY);
		assertInnerMethodInvokeAndRetValEquals("getBigDecimal", new Object[] {STR_ANY}, result);
	}
	
}
