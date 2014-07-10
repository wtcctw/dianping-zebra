package com.dianping.zebra.monitor.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.dianping.unknown.monitor.sql.MockUnknownConnection;
import com.dianping.zebra.monitor.sql.c3p0.MockC3p0Connection;
import com.dianping.zebra.monitor.sql.dpdl.MockDpdlConnection;
import com.dianping.zebra.monitor.sql.zebra.MockZebraConnection;

public class DefaultConnectionMetaAnalyzerTest {
	
	private ConnectionMetaAnalyzer connectionMetaAnalyzer;
	private Connection connection;
	
	@Before
	public void setUp() {
		connection = EasyMock.createMock(Connection.class);
		connectionMetaAnalyzer = DefaultConnectionMetaAnalyzer.getInstance();
	}

	@Test
	public void testGetMysqlDbAndSchema() throws SQLException {
		DatabaseMetaData metaData = EasyMock.createMock(DatabaseMetaData.class);
		EasyMock.expect(metaData.getURL()).andReturn("jdbc:mysql://192.168.8.44:3306/DianPing?characterEncoding=UTF8");
		EasyMock.expect(connection.getMetaData()).andReturn(metaData);
		EasyMock.replay(metaData, connection);
		String[] dbAndSchema = connectionMetaAnalyzer.getDbAndSchema(connection);
		assertTrue(dbAndSchema != null && dbAndSchema.length == 2);
		assertEquals("192.168.8.44:3306", dbAndSchema[0]);
		assertEquals("DianPing", dbAndSchema[1]);
		EasyMock.verify(metaData, connection);
	}
	
	@Test
	public void testGetMssqlDbAndSchema() throws SQLException {
		DatabaseMetaData metaData = EasyMock.createMock(DatabaseMetaData.class);
		EasyMock.expect(metaData.getURL()).andReturn("jdbc:sqlserver://192.168.8.30:1433;DatabaseName=zSurvey_NET_20090710");
		EasyMock.expect(connection.getMetaData()).andReturn(metaData);
		EasyMock.replay(metaData, connection);
		String[] dbAndSchema = connectionMetaAnalyzer.getDbAndSchema(connection);
		assertTrue(dbAndSchema != null && dbAndSchema.length == 2);
		assertEquals("192.168.8.30:1433", dbAndSchema[0]);
		assertEquals("zSurvey_NET_20090710", dbAndSchema[1]);
		EasyMock.verify(metaData, connection);
	}
	
	@Test
	public void testGetH2DbAndSchema() throws SQLException {
		DatabaseMetaData metaData = EasyMock.createMock(DatabaseMetaData.class);
		EasyMock.expect(metaData.getURL()).andReturn("jdbc:h2:tcp://localhost/~/sql-monitor-db");
		EasyMock.expect(connection.getMetaData()).andReturn(metaData);
		EasyMock.replay(metaData, connection);
		String[] dbAndSchema = connectionMetaAnalyzer.getDbAndSchema(connection);
		assertTrue(dbAndSchema != null && dbAndSchema.length == 2);
		assertEquals("localhost", dbAndSchema[0]);
		assertEquals("sql-monitor-db", dbAndSchema[1]);
		EasyMock.verify(metaData, connection);
	}

	@Test
	public void testGetDSIdentity() {
		int dsIdentity = connectionMetaAnalyzer.getDSIdentity(new MockZebraConnection());
		assertEquals(ConnectionMetaAnalyzer.DS_ZEBRA, dsIdentity);
		dsIdentity = connectionMetaAnalyzer.getDSIdentity(new MockDpdlConnection());
		assertEquals(ConnectionMetaAnalyzer.DS_DPDL, dsIdentity);
		dsIdentity = connectionMetaAnalyzer.getDSIdentity(new MockC3p0Connection());
		assertEquals(ConnectionMetaAnalyzer.DS_C3P0, dsIdentity);
		dsIdentity = connectionMetaAnalyzer.getDSIdentity(new MockUnknownConnection());
		assertEquals(ConnectionMetaAnalyzer.DS_UNKNOWN, dsIdentity);
	}

}
