/**
 * Project: zebra-sql-monitor-client
 *
 * File Created at 2011-10-28
 * $Id$
 *
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.monitor.sql;


import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

/**
 * @author danson.liu
 */
public class MonitorableCallableStatement extends MonitorablePreparedStatement implements CallableStatement {

	private final CallableStatement prepareCall;

	public MonitorableCallableStatement(CallableStatement prepareCall, String sql,
			MonitorableConnection monitorableConnection) {
		super(prepareCall, sql, monitorableConnection);
		this.prepareCall = prepareCall;
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
		prepareCall.registerOutParameter(parameterIndex, sqlType);
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
		prepareCall.registerOutParameter(parameterIndex, sqlType, scale);
	}

	@Override
	public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
		prepareCall.registerOutParameter(parameterIndex, sqlType, typeName);
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
		prepareCall.registerOutParameter(parameterName, sqlType);
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
		prepareCall.registerOutParameter(parameterName, sqlType, scale);
	}

	@Override
	public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
		prepareCall.registerOutParameter(parameterName, sqlType, typeName);
	}

	@Override
	public boolean wasNull() throws SQLException {
		return prepareCall.wasNull();
	}

	@Override
	public String getString(int parameterIndex) throws SQLException {
		return prepareCall.getString(parameterIndex);
	}

	@Override
	public boolean getBoolean(int parameterIndex) throws SQLException {
		return prepareCall.getBoolean(parameterIndex);
	}

	@Override
	public byte getByte(int parameterIndex) throws SQLException {
		return prepareCall.getByte(parameterIndex);
	}

	@Override
	public short getShort(int parameterIndex) throws SQLException {
		return prepareCall.getShort(parameterIndex);
	}

	@Override
	public int getInt(int parameterIndex) throws SQLException {
		return prepareCall.getInt(parameterIndex);
	}

	@Override
	public long getLong(int parameterIndex) throws SQLException {
		return prepareCall.getLong(parameterIndex);
	}

	@Override
	public float getFloat(int parameterIndex) throws SQLException {
		return prepareCall.getFloat(parameterIndex);
	}

	@Override
	public double getDouble(int parameterIndex) throws SQLException {
		return prepareCall.getDouble(parameterIndex);
	}

	@Override
	@Deprecated
	public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
		return prepareCall.getBigDecimal(parameterIndex, scale);
	}

	@Override
	public byte[] getBytes(int parameterIndex) throws SQLException {
		return prepareCall.getBytes(parameterIndex);
	}

	@Override
	public Date getDate(int parameterIndex) throws SQLException {
		return prepareCall.getDate(parameterIndex);
	}

	@Override
	public Time getTime(int parameterIndex) throws SQLException {
		return prepareCall.getTime(parameterIndex);
	}

	@Override
	public Timestamp getTimestamp(int parameterIndex) throws SQLException {
		return prepareCall.getTimestamp(parameterIndex);
	}

	@Override
	public Object getObject(int parameterIndex) throws SQLException {
		return prepareCall.getObject(parameterIndex);
	}

	@Override
	public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
		return prepareCall.getBigDecimal(parameterIndex);
	}

	@Override
	public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
		return prepareCall.getObject(parameterIndex, map);
	}

	@Override
	public Ref getRef(int parameterIndex) throws SQLException {
		return prepareCall.getRef(parameterIndex);
	}

	@Override
	public Blob getBlob(int parameterIndex) throws SQLException {
		return prepareCall.getBlob(parameterIndex);
	}

	@Override
	public Clob getClob(int parameterIndex) throws SQLException {
		return prepareCall.getClob(parameterIndex);
	}

	@Override
	public Array getArray(int parameterIndex) throws SQLException {
		return prepareCall.getArray(parameterIndex);
	}

	@Override
	public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
		return prepareCall.getDate(parameterIndex, cal);
	}

	@Override
	public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
		return prepareCall.getTime(parameterIndex, cal);
	}

	@Override
	public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
		return prepareCall.getTimestamp(parameterIndex, cal);
	}

	@Override
	public URL getURL(int parameterIndex) throws SQLException {
		return prepareCall.getURL(parameterIndex);
	}

	@Override
	public void setURL(String parameterName, URL val) throws SQLException {
		prepareCall.setURL(parameterName, val);
	}

	@Override
	public void setNull(String parameterName, int sqlType) throws SQLException {
		prepareCall.setNull(parameterName, sqlType);
	}

	@Override
	public void setBoolean(String parameterName, boolean x) throws SQLException {
		prepareCall.setBoolean(parameterName, x);
	}

	@Override
	public void setByte(String parameterName, byte x) throws SQLException {
		prepareCall.setByte(parameterName, x);
	}

	@Override
	public void setShort(String parameterName, short x) throws SQLException {
		prepareCall.setShort(parameterName, x);
	}

	@Override
	public void setInt(String parameterName, int x) throws SQLException {
		prepareCall.setInt(parameterName, x);
	}

	@Override
	public void setLong(String parameterName, long x) throws SQLException {
		prepareCall.setLong(parameterName, x);
	}

	@Override
	public void setFloat(String parameterName, float x) throws SQLException {
		prepareCall.setFloat(parameterName, x);
	}

	@Override
	public void setDouble(String parameterName, double x) throws SQLException {
		prepareCall.setDouble(parameterName, x);
	}

	@Override
	public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
		prepareCall.setBigDecimal(parameterName, x);
	}

	@Override
	public void setString(String parameterName, String x) throws SQLException {
		prepareCall.setString(parameterName, x);
	}

	@Override
	public void setBytes(String parameterName, byte[] x) throws SQLException {
		prepareCall.setBytes(parameterName, x);
	}

	@Override
	public void setDate(String parameterName, Date x) throws SQLException {
		prepareCall.setDate(parameterName, x);
	}

	@Override
	public void setTime(String parameterName, Time x) throws SQLException {
		prepareCall.setTime(parameterName, x);
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
		prepareCall.setTimestamp(parameterName, x);
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
		prepareCall.setAsciiStream(parameterName, x, length);
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
		prepareCall.setBinaryStream(parameterName, x, length);
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
		prepareCall.setObject(parameterName, x, targetSqlType, scale);
	}

	@Override
	public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
		prepareCall.setObject(parameterName, x, targetSqlType);
	}

	@Override
	public void setObject(String parameterName, Object x) throws SQLException {
		prepareCall.setObject(parameterName, x);
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
		prepareCall.setCharacterStream(parameterName, reader, length);
	}

	@Override
	public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
		prepareCall.setDate(parameterName, x, cal);
	}

	@Override
	public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
		prepareCall.setTime(parameterName, x, cal);
	}

	@Override
	public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
		prepareCall.setTimestamp(parameterName, x, cal);
	}

	@Override
	public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
		prepareCall.setNull(parameterName, sqlType, typeName);
	}

	@Override
	public String getString(String parameterName) throws SQLException {
		return prepareCall.getString(parameterName);
	}

	@Override
	public boolean getBoolean(String parameterName) throws SQLException {
		return prepareCall.getBoolean(parameterName);
	}

	@Override
	public byte getByte(String parameterName) throws SQLException {
		return prepareCall.getByte(parameterName);
	}

	@Override
	public short getShort(String parameterName) throws SQLException {
		return prepareCall.getShort(parameterName);
	}

	@Override
	public int getInt(String parameterName) throws SQLException {
		return prepareCall.getInt(parameterName);
	}

	@Override
	public long getLong(String parameterName) throws SQLException {
		return prepareCall.getLong(parameterName);
	}

	@Override
	public float getFloat(String parameterName) throws SQLException {
		return prepareCall.getFloat(parameterName);
	}

	@Override
	public double getDouble(String parameterName) throws SQLException {
		return prepareCall.getDouble(parameterName);
	}

	@Override
	public byte[] getBytes(String parameterName) throws SQLException {
		return prepareCall.getBytes(parameterName);
	}

	@Override
	public Date getDate(String parameterName) throws SQLException {
		return prepareCall.getDate(parameterName);
	}

	@Override
	public Time getTime(String parameterName) throws SQLException {
		return prepareCall.getTime(parameterName);
	}

	@Override
	public Timestamp getTimestamp(String parameterName) throws SQLException {
		return prepareCall.getTimestamp(parameterName);
	}

	@Override
	public Object getObject(String parameterName) throws SQLException {
		return prepareCall.getObject(parameterName);
	}

	@Override
	public BigDecimal getBigDecimal(String parameterName) throws SQLException {
		return prepareCall.getBigDecimal(parameterName);
	}

	@Override
	public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
		return prepareCall.getObject(parameterName, map);
	}

	@Override
	public Ref getRef(String parameterName) throws SQLException {
		return prepareCall.getRef(parameterName);
	}

	@Override
	public Blob getBlob(String parameterName) throws SQLException {
		return prepareCall.getBlob(parameterName);
	}

	@Override
	public Clob getClob(String parameterName) throws SQLException {
		return prepareCall.getClob(parameterName);
	}

	@Override
	public Array getArray(String parameterName) throws SQLException {
		return prepareCall.getArray(parameterName);
	}

	@Override
	public Date getDate(String parameterName, Calendar cal) throws SQLException {
		return prepareCall.getDate(parameterName, cal);
	}

	@Override
	public Time getTime(String parameterName, Calendar cal) throws SQLException {
		return prepareCall.getTime(parameterName, cal);
	}

	@Override
	public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
		return prepareCall.getTimestamp(parameterName, cal);
	}

	@Override
	public URL getURL(String parameterName) throws SQLException {
		return prepareCall.getURL(parameterName);
	}

	@Override
	public RowId getRowId(int parameterIndex) throws SQLException {
		return prepareCall.getRowId(parameterIndex);
	}

	@Override
	public RowId getRowId(String parameterName) throws SQLException {
		return prepareCall.getRowId(parameterName);
	}

	@Override
	public void setRowId(String parameterName, RowId x) throws SQLException {
		prepareCall.setRowId(parameterName, x);
	}

	@Override
	public void setNString(String parameterName, String value) throws SQLException {
		prepareCall.setNString(parameterName, value);
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
		prepareCall.setNCharacterStream(parameterName, value, length);
	}

	@Override
	public void setNClob(String parameterName, NClob value) throws SQLException {
		prepareCall.setNClob(parameterName, value);
	}

	@Override
	public void setClob(String parameterName, Reader reader, long length) throws SQLException {
		prepareCall.setClob(parameterName, reader, length);
	}

	@Override
	public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
		prepareCall.setBlob(parameterName, inputStream, length);
	}

	@Override
	public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
		prepareCall.setNClob(parameterName, reader, length);
	}

	@Override
	public NClob getNClob(int parameterIndex) throws SQLException {
		return prepareCall.getNClob(parameterIndex);
	}

	@Override
	public NClob getNClob(String parameterName) throws SQLException {
		return prepareCall.getNClob(parameterName);
	}

	@Override
	public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
		prepareCall.setSQLXML(parameterName, xmlObject);
	}

	@Override
	public SQLXML getSQLXML(int parameterIndex) throws SQLException {
		return prepareCall.getSQLXML(parameterIndex);
	}

	@Override
	public SQLXML getSQLXML(String parameterName) throws SQLException {
		return prepareCall.getSQLXML(parameterName);
	}

	@Override
	public String getNString(int parameterIndex) throws SQLException {
		return prepareCall.getNString(parameterIndex);
	}

	@Override
	public String getNString(String parameterName) throws SQLException {
		return prepareCall.getNString(parameterName);
	}

	@Override
	public Reader getNCharacterStream(int parameterIndex) throws SQLException {
		return prepareCall.getNCharacterStream(parameterIndex);
	}

	@Override
	public Reader getNCharacterStream(String parameterName) throws SQLException {
		return prepareCall.getNCharacterStream(parameterName);
	}

	@Override
	public Reader getCharacterStream(int parameterIndex) throws SQLException {
		return prepareCall.getCharacterStream(parameterIndex);
	}

	@Override
	public Reader getCharacterStream(String parameterName) throws SQLException {
		return prepareCall.getCharacterStream(parameterName);
	}

	@Override
	public void setBlob(String parameterName, Blob x) throws SQLException {
		prepareCall.setBlob(parameterName, x);
	}

	@Override
	public void setClob(String parameterName, Clob x) throws SQLException {
		prepareCall.setClob(parameterName, x);
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
		prepareCall.setAsciiStream(parameterName, x, length);
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
		prepareCall.setBinaryStream(parameterName, x, length);
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
		prepareCall.setCharacterStream(parameterName, reader, length);
	}

	@Override
	public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
		prepareCall.setAsciiStream(parameterName, x);
	}

	@Override
	public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
		prepareCall.setBinaryStream(parameterName, x);
	}

	@Override
	public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
		prepareCall.setCharacterStream(parameterName, reader);
	}

	@Override
	public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
		prepareCall.setNCharacterStream(parameterName, value);
	}

	@Override
	public void setClob(String parameterName, Reader reader) throws SQLException {
		prepareCall.setClob(parameterName, reader);
	}

	@Override
	public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
		prepareCall.setBlob(parameterName, inputStream);
	}

	@Override
	public void setNClob(String parameterName, Reader reader) throws SQLException {
		prepareCall.setNClob(parameterName, reader);
	}

	public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
		throw new UnsupportedOperationException();
	}

	public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
		throw new UnsupportedOperationException();
	}
}
