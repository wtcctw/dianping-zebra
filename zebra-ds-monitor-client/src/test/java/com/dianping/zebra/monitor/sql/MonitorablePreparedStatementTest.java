package com.dianping.zebra.monitor.sql;

import java.io.ByteArrayInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;


public class MonitorablePreparedStatementTest extends BaseMonitorableUnitTest {

	private MonitorablePreparedStatement 	monitorablePreparedStatement;
	private PreparedStatement 				innerPrepareStatement;
	private MonitorableConnection 			monitorableConnection;

	@Before
	public void setUp() {
		monitorableConnection = EasyMock.createMock(MonitorableConnection.class);
		innerPrepareStatement = (PreparedStatement) createTraceableObject(PreparedStatement.class);
		monitorablePreparedStatement = new MonitorablePreparedStatement(innerPrepareStatement, SQL_ANY, monitorableConnection);
		SqlMonitor sqlMonitor = EasyMock.createMock(SqlMonitor.class);
		monitorablePreparedStatement.setSqlMonitor(sqlMonitor);
	}
	
	@Test
	public void testDelegatedMethods() throws SQLException {
		Object result = monitorablePreparedStatement.executeQuery();
		assertInnerMethodInvokeAndRetValEquals("executeQuery", new Object[] {}, result);
		result = monitorablePreparedStatement.executeUpdate();
		assertInnerMethodInvokeAndRetValEquals("executeUpdate", new Object[] {}, result);
		result = monitorablePreparedStatement.execute();
		assertInnerMethodInvokeAndRetValEquals("execute", new Object[] {}, result);
		
		monitorablePreparedStatement.clearBatch();
		assertInnerMethodInvoked("clearBatch", new Object[] {});
		monitorablePreparedStatement.addBatch();
		assertInnerMethodInvoked("addBatch", new Object[] {});
		
		result = monitorablePreparedStatement.executeBatch();
		assertInnerMethodInvokeAndRetValEquals("executeBatch", new Object[] {}, result);
		
		monitorablePreparedStatement.setNull(UINT_ANY, INT_ANY);
		assertInnerMethodInvoked("setNull", new Object[] {UINT_ANY, INT_ANY});
		monitorablePreparedStatement.setBoolean(UINT_ANY, BOOL_ANY);
		assertInnerMethodInvoked("setBoolean", new Object[] {UINT_ANY, BOOL_ANY});
		monitorablePreparedStatement.setByte(UINT_ANY, BYTE_ANY);
		assertInnerMethodInvoked("setByte", new Object[] {UINT_ANY, BYTE_ANY});
		monitorablePreparedStatement.setShort(UINT_ANY, SHORT_ANY);
		assertInnerMethodInvoked("setShort", new Object[] {UINT_ANY, SHORT_ANY});
		monitorablePreparedStatement.setInt(UINT_ANY, INT_ANY);
		assertInnerMethodInvoked("setInt", new Object[] {UINT_ANY, INT_ANY});
		monitorablePreparedStatement.setLong(UINT_ANY, LONG_ANY);
		assertInnerMethodInvoked("setLong", new Object[] {UINT_ANY, LONG_ANY});
		monitorablePreparedStatement.setFloat(UINT_ANY, FLOAT_ANY);
		assertInnerMethodInvoked("setFloat", new Object[] {UINT_ANY, FLOAT_ANY});
		monitorablePreparedStatement.setDouble(UINT_ANY, DOUBLE_ANY);
		assertInnerMethodInvoked("setDouble", new Object[] {UINT_ANY, DOUBLE_ANY});
		monitorablePreparedStatement.setBigDecimal(UINT_ANY, DECIMAL_ANY);
		assertInnerMethodInvoked("setBigDecimal", new Object[] {UINT_ANY, DECIMAL_ANY});
		monitorablePreparedStatement.setString(UINT_ANY, STR_ANY);
		assertInnerMethodInvoked("setString", new Object[] {UINT_ANY, STR_ANY});
		monitorablePreparedStatement.setBytes(UINT_ANY, BYTE_ARRARY_ANY);
		assertInnerMethodInvoked("setBytes", new Object[] {UINT_ANY, BYTE_ARRARY_ANY});
		monitorablePreparedStatement.setDate(UINT_ANY, DATE_ANY);
		assertInnerMethodInvoked("setDate", new Object[] {UINT_ANY, DATE_ANY});
		monitorablePreparedStatement.setTime(UINT_ANY, TIME_ANY);
		assertInnerMethodInvoked("setTime", new Object[] {UINT_ANY, TIME_ANY});
		monitorablePreparedStatement.setTimestamp(UINT_ANY, TIMESTAMP_ANY);
		assertInnerMethodInvoked("setTimestamp", new Object[] {UINT_ANY, TIMESTAMP_ANY});
		monitorablePreparedStatement.setDate(UINT_ANY, DATE_ANY, CALENDAR_ANY);
		assertInnerMethodInvoked("setDate", new Object[] {UINT_ANY, DATE_ANY, CALENDAR_ANY});
		monitorablePreparedStatement.setTime(UINT_ANY, TIME_ANY, CALENDAR_ANY);
		assertInnerMethodInvoked("setTime", new Object[] {UINT_ANY, TIME_ANY, CALENDAR_ANY});
		monitorablePreparedStatement.setTimestamp(UINT_ANY, TIMESTAMP_ANY, CALENDAR_ANY);
		assertInnerMethodInvoked("setTimestamp", new Object[] {UINT_ANY, TIMESTAMP_ANY, CALENDAR_ANY});
		
		ByteArrayInputStream bais = new ByteArrayInputStream(new byte[0]);
		monitorablePreparedStatement.setAsciiStream(UINT_ANY, bais, UINT_ANY);
		assertInnerMethodInvoked("setAsciiStream", new Object[] {UINT_ANY, bais, UINT_ANY});
		monitorablePreparedStatement.setUnicodeStream(UINT_ANY, bais, UINT_ANY);
		assertInnerMethodInvoked("setUnicodeStream", new Object[] {UINT_ANY, bais, UINT_ANY});
		monitorablePreparedStatement.setBinaryStream(UINT_ANY, bais, UINT_ANY);
		assertInnerMethodInvoked("setBinaryStream", new Object[] {UINT_ANY, bais, UINT_ANY});
		
		Reader reader = new StringReader(STR_ANY);
		monitorablePreparedStatement.setCharacterStream(UINT_ANY, reader, UINT_ANY);
		assertInnerMethodInvoked("setCharacterStream", new Object[] {UINT_ANY, reader, UINT_ANY});
		
		monitorablePreparedStatement.setObject(UINT_ANY, OBJECT_ANY, Types.BLOB);
		assertInnerMethodInvoked("setObject", new Object[] {UINT_ANY, OBJECT_ANY, Types.BLOB});
		monitorablePreparedStatement.setObject(UINT_ANY, OBJECT_ANY);
		assertInnerMethodInvoked("setObject", new Object[] {UINT_ANY, OBJECT_ANY});
		
		result = monitorablePreparedStatement.getMetaData();
		assertInnerMethodInvokeAndRetValEquals("getMetaData", new Object[] {}, result);
		
		monitorablePreparedStatement.clearParameters();
		assertInnerMethodInvoked("clearParameters", new Object[] {});
		
		monitorablePreparedStatement.close();
		assertInnerMethodInvoked("close", new Object[] {});
	}
	
}
