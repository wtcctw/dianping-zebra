/**
 * Project: ${zebra-client.aid}
 * 
 * File Created at 2011-6-14 $Id$
 * 
 * Copyright 2010 dianping.com. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Dianping
 * Company. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with dianping.com.
 */
package com.dianping.zebra.shard.jdbc;

import com.dianping.zebra.shard.jdbc.data.DataMerger;
import com.dianping.zebra.shard.jdbc.data.DataPool;
import com.dianping.zebra.shard.jdbc.data.DefaultDataMerger;
import com.dianping.zebra.shard.jdbc.util.JDBCUtils;
import com.dianping.zebra.shard.router.RouterTarget;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Zebra的ResultSet wrapper
 * 
 * @author Leo Liang
 * 
 */
public class ShardResultSet implements ResultSet {

	private static Logger log = Logger.getLogger(ShardResultSet.class);

	private ShardStatement statementWrapper;

	private DataPool dataPool = new DataPool();

	private DataMerger dataMerger = new DefaultDataMerger();

	private boolean dataInited = false;

	private RouterTarget routerTarget;

	private List<ResultSet> actualResultSets = new ArrayList<ResultSet>();

	private int fetchDirection = FETCH_FORWARD;

	private int fetchSize;

	private boolean closed = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#absolute(int)
	 */
	@Override
	public boolean absolute(int row) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport absolute");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#afterLast()
	 */
	@Override
	public void afterLast() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport afterLast");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#beforeFirst()
	 */
	@Override
	public void beforeFirst() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport beforeFirst");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#cancelRowUpdates()
	 */
	@Override
	public void cancelRowUpdates() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport cancelRowUpdates");
	}

	private void checkClosed() throws SQLException {
		if (closed) {
			throw new SQLException("No operations allowed after result set closed.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#clearWarnings()
	 */
	@Override
	public void clearWarnings() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport clearWarnings");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#close()
	 */
	@Override
	public void close() throws SQLException {

		if (closed) {
			return;
		}

		List<Throwable> exceptions = new ArrayList<Throwable>();

		try {
			for (int i = 0; i < actualResultSets.size(); ++i) {
				try {
					actualResultSets.get(i).close();
				} catch (Exception e) {
					exceptions.add(e);
					log.error(e);
				}
			}
		} finally {
			closed = true;
			dataPool.clear();
		}

		JDBCUtils.throwSQLExceptionIfNeeded(exceptions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#deleteRow()
	 */
	@Override
	public void deleteRow() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport deleteRow");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#findColumn(java.lang.String)
	 */
	@Override
	public int findColumn(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.findColumn(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#first()
	 */
	@Override
	public boolean first() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport first");
	}

	public List<ResultSet> getActualResultSets() {
		return actualResultSets;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getArray(int)
	 */
	@Override
	public Array getArray(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getArray(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getArray(java.lang.String)
	 */
	@Override
	public Array getArray(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getArray(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getAsciiStream(int)
	 */
	@Override
	public InputStream getAsciiStream(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getAsciiStream(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getAsciiStream(java.lang.String)
	 */
	@Override
	public InputStream getAsciiStream(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getAsciiStream(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getBigDecimal(int)
	 */
	@Override
	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getBigDecimal(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getBigDecimal(int, int)
	 */
	@Override
	public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
		checkClosed();
		return dataPool.getBigDecimal(columnIndex, scale);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getBigDecimal(java.lang.String)
	 */
	@Override
	public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getBigDecimal(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getBigDecimal(java.lang.String, int)
	 */
	@Override
	public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
		checkClosed();
		return dataPool.getBigDecimal(columnLabel, scale);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getBinaryStream(int)
	 */
	@Override
	public InputStream getBinaryStream(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getBinaryStream(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getBinaryStream(java.lang.String)
	 */
	@Override
	public InputStream getBinaryStream(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getBinaryStream(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getBlob(int)
	 */
	@Override
	public Blob getBlob(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getBlob(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getBlob(java.lang.String)
	 */
	@Override
	public Blob getBlob(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getBlob(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getBoolean(int)
	 */
	@Override
	public boolean getBoolean(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getBoolean(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getBoolean(java.lang.String)
	 */
	@Override
	public boolean getBoolean(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getBoolean(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getByte(int)
	 */
	@Override
	public byte getByte(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getByte(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getByte(java.lang.String)
	 */
	@Override
	public byte getByte(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getByte(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getBytes(int)
	 */
	@Override
	public byte[] getBytes(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getBytes(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getBytes(java.lang.String)
	 */
	@Override
	public byte[] getBytes(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getBytes(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getCharacterStream(int)
	 */
	@Override
	public Reader getCharacterStream(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getCharacterStream(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getCharacterStream(java.lang.String)
	 */
	@Override
	public Reader getCharacterStream(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getCharacterStream(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getClob(int)
	 */
	@Override
	public Clob getClob(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getClob(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getClob(java.lang.String)
	 */
	@Override
	public Clob getClob(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getClob(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getConcurrency()
	 */
	@Override
	public int getConcurrency() throws SQLException {
		checkClosed();
		return dataPool.getConcurrency();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getCursorName()
	 */
	@Override
	public String getCursorName() throws SQLException {
		checkClosed();
		return dataPool.getCursorName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getDate(int)
	 */
	@Override
	public Date getDate(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getDate(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getDate(int, java.util.Calendar)
	 */
	@Override
	public Date getDate(int columnIndex, Calendar cal) throws SQLException {
		checkClosed();
		return dataPool.getDate(columnIndex, cal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getDate(java.lang.String)
	 */
	@Override
	public Date getDate(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getDate(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getDate(java.lang.String, java.util.Calendar)
	 */
	@Override
	public Date getDate(String columnLabel, Calendar cal) throws SQLException {
		checkClosed();
		return dataPool.getDate(columnLabel, cal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getDouble(int)
	 */
	@Override
	public double getDouble(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getDouble(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getDouble(java.lang.String)
	 */
	@Override
	public double getDouble(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getDouble(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getFetchDirection()
	 */
	@Override
	public int getFetchDirection() throws SQLException {
		checkClosed();
		return fetchDirection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getFetchSize()
	 */
	@Override
	public int getFetchSize() throws SQLException {
		checkClosed();
		return fetchSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getFloat(int)
	 */
	@Override
	public float getFloat(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getFloat(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getFloat(java.lang.String)
	 */
	@Override
	public float getFloat(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getFloat(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getHoldability()
	 */
	@Override
	public int getHoldability() throws SQLException {
		checkClosed();
		return dataPool.getHoldability();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getInt(int)
	 */
	@Override
	public int getInt(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getInt(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getInt(java.lang.String)
	 */
	@Override
	public int getInt(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getInt(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getLong(int)
	 */
	@Override
	public long getLong(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getLong(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getLong(java.lang.String)
	 */
	@Override
	public long getLong(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getLong(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getMetaData()
	 */
	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		checkClosed();
		return dataPool.getMetaData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getNCharacterStream(int)
	 */
	@Override
	public Reader getNCharacterStream(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getNCharacterStream(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getNCharacterStream(java.lang.String)
	 */
	@Override
	public Reader getNCharacterStream(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getNCharacterStream(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getNClob(int)
	 */
	@Override
	public NClob getNClob(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getNClob(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getNClob(java.lang.String)
	 */
	@Override
	public NClob getNClob(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getNClob(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getNString(int)
	 */
	@Override
	public String getNString(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getNString(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getNString(java.lang.String)
	 */
	@Override
	public String getNString(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getNString(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getObject(int)
	 */
	@Override
	public Object getObject(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getObject(columnIndex);
	}

	public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
		throw new UnsupportedOperationException("getObject");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getObject(int, java.util.Map)
	 */
	@Override
	public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
		checkClosed();
		return dataPool.getObject(columnIndex, map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getObject(java.lang.String)
	 */
	@Override
	public Object getObject(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getObject(columnLabel);
	}

	public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
		throw new UnsupportedOperationException("getObject");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getObject(java.lang.String, java.util.Map)
	 */
	@Override
	public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
		checkClosed();
		return dataPool.getObject(columnLabel, map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getRef(int)
	 */
	@Override
	public Ref getRef(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getRef(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getRef(java.lang.String)
	 */
	@Override
	public Ref getRef(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getRef(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getRow()
	 */
	@Override
	public int getRow() throws SQLException {
		checkClosed();
		return dataPool.getCurrentRowNo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getRowId(int)
	 */
	@Override
	public RowId getRowId(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getRowId(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getRowId(java.lang.String)
	 */
	@Override
	public RowId getRowId(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getRowId(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getShort(int)
	 */
	@Override
	public short getShort(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getShort(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getShort(java.lang.String)
	 */
	@Override
	public short getShort(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getShort(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getSQLXML(int)
	 */
	@Override
	public SQLXML getSQLXML(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getSQLXML(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getSQLXML(java.lang.String)
	 */
	@Override
	public SQLXML getSQLXML(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getSQLXML(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getStatement()
	 */
	@Override
	public Statement getStatement() throws SQLException {
		return statementWrapper;
	}

	public ShardStatement getStatementWrapper() {
		return statementWrapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getString(int)
	 */
	@Override
	public String getString(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getString(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getString(java.lang.String)
	 */
	@Override
	public String getString(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getString(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getTime(int)
	 */
	@Override
	public Time getTime(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getTime(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getTime(int, java.util.Calendar)
	 */
	@Override
	public Time getTime(int columnIndex, Calendar cal) throws SQLException {
		checkClosed();
		return dataPool.getTime(columnIndex, cal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getTime(java.lang.String)
	 */
	@Override
	public Time getTime(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getTime(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getTime(java.lang.String, java.util.Calendar)
	 */
	@Override
	public Time getTime(String columnLabel, Calendar cal) throws SQLException {
		checkClosed();
		return dataPool.getTime(columnLabel, cal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getTimestamp(int)
	 */
	@Override
	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getTimestamp(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getTimestamp(int, java.util.Calendar)
	 */
	@Override
	public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
		checkClosed();
		return dataPool.getTimestamp(columnIndex, cal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getTimestamp(java.lang.String)
	 */
	@Override
	public Timestamp getTimestamp(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getTimestamp(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getTimestamp(java.lang.String, java.util.Calendar)
	 */
	@Override
	public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
		checkClosed();
		return dataPool.getTimestamp(columnLabel, cal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getType()
	 */
	@Override
	public int getType() throws SQLException {
		checkClosed();
		return dataPool.getType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getUnicodeStream(int)
	 */
	@Override
	public InputStream getUnicodeStream(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getUnicodeStream(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getUnicodeStream(java.lang.String)
	 */
	@Override
	public InputStream getUnicodeStream(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getUnicodeStream(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getURL(int)
	 */
	@Override
	public URL getURL(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getURL(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getURL(java.lang.String)
	 */
	@Override
	public URL getURL(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getURL(columnLabel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#getWarnings()
	 */
	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getWarnings");
	}

	public void init() throws SQLException {
		checkClosed();

		if (!dataInited) {
			if (routerTarget != null) {
				dataMerger.merge(dataPool, routerTarget, actualResultSets);
			}
			dataInited = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#insertRow()
	 */
	@Override
	public void insertRow() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport insertRow");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#isAfterLast()
	 */
	@Override
	public boolean isAfterLast() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport isAfterLast");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#isBeforeFirst()
	 */
	@Override
	public boolean isBeforeFirst() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport isBeforeFirst");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#isClosed()
	 */
	@Override
	public boolean isClosed() throws SQLException {
		return closed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#isFirst()
	 */
	@Override
	public boolean isFirst() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport isFirst");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#isLast()
	 */
	@Override
	public boolean isLast() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport isLast");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport isWrapperFor");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#last()
	 */
	@Override
	public boolean last() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport last");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#moveToCurrentRow()
	 */
	@Override
	public void moveToCurrentRow() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport moveToCurrentRow");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#moveToInsertRow()
	 */
	@Override
	public void moveToInsertRow() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport moveToInsertRow");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#next()
	 */
	@Override
	public boolean next() throws SQLException {
		checkClosed();
		if (!dataInited) {
			init();
		}
		return dataPool.next();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#previous()
	 */
	@Override
	public boolean previous() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport previous");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#refreshRow()
	 */
	@Override
	public void refreshRow() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport refreshRow");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#relative(int)
	 */
	@Override
	public boolean relative(int rows) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport relative");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#rowDeleted()
	 */
	@Override
	public boolean rowDeleted() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport rowDeleted");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#rowInserted()
	 */
	@Override
	public boolean rowInserted() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport rowInserted");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#rowUpdated()
	 */
	@Override
	public boolean rowUpdated() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport rowUpdated");
	}

	public void setActualResultSets(List<ResultSet> actualResultSets) {
		this.actualResultSets = actualResultSets;
	}

	/**
	 * @param dataPool
	 *           the dataPool to set
	 */
	public void setDataPool(DataPool dataPool) {
		this.dataPool = dataPool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#setFetchDirection(int)
	 */
	@Override
	public void setFetchDirection(int direction) throws SQLException {
		checkClosed();

		if (direction != FETCH_FORWARD) {
			throw new SQLException("only support fetch direction FETCH_FORWARD");
		}

		this.fetchDirection = direction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#setFetchSize(int)
	 */
	@Override
	public void setFetchSize(int rows) throws SQLException {
		checkClosed();

		if (rows < 0) {
			throw new SQLException("fetch size must greater than or equal 0");
		}

		this.fetchSize = rows;
	}

	/**
	 * @param routerTarget
	 *           the routerTarget to set
	 */
	public void setRouterTarget(RouterTarget routerTarget) {
		this.routerTarget = routerTarget;
	}

	public void setStatementWrapper(ShardStatement statementWrapper) {
		this.statementWrapper = statementWrapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport unwrap");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateArray(int, java.sql.Array)
	 */
	@Override
	public void updateArray(int columnIndex, Array x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateArray");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateArray(java.lang.String, java.sql.Array)
	 */
	@Override
	public void updateArray(String columnLabel, Array x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateArray");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateAsciiStream(int, java.io.InputStream)
	 */
	@Override
	public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateAsciiStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateAsciiStream(int, java.io.InputStream, int)
	 */
	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateAsciiStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateAsciiStream(int, java.io.InputStream, long)
	 */
	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateAsciiStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateAsciiStream(java.lang.String, java.io.InputStream)
	 */
	@Override
	public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateAsciiStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateAsciiStream(java.lang.String, java.io.InputStream, int)
	 */
	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateAsciiStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateAsciiStream(java.lang.String, java.io.InputStream, long)
	 */
	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateAsciiStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBigDecimal(int, java.math.BigDecimal)
	 */
	@Override
	public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBigDecimal");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBigDecimal(java.lang.String, java.math.BigDecimal)
	 */
	@Override
	public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBigDecimal");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBinaryStream(int, java.io.InputStream)
	 */
	@Override
	public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBinaryStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBinaryStream(int, java.io.InputStream, int)
	 */
	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBinaryStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBinaryStream(int, java.io.InputStream, long)
	 */
	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBinaryStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBinaryStream(java.lang.String, java.io.InputStream)
	 */
	@Override
	public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBinaryStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBinaryStream(java.lang.String, java.io.InputStream, int)
	 */
	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBinaryStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBinaryStream(java.lang.String, java.io.InputStream, long)
	 */
	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBinaryStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBlob(int, java.sql.Blob)
	 */
	@Override
	public void updateBlob(int columnIndex, Blob x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBlob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBlob(int, java.io.InputStream)
	 */
	@Override
	public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBlob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBlob(int, java.io.InputStream, long)
	 */
	@Override
	public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBlob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBlob(java.lang.String, java.sql.Blob)
	 */
	@Override
	public void updateBlob(String columnLabel, Blob x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBlob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBlob(java.lang.String, java.io.InputStream)
	 */
	@Override
	public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBlob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBlob(java.lang.String, java.io.InputStream, long)
	 */
	@Override
	public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBlob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBoolean(int, boolean)
	 */
	@Override
	public void updateBoolean(int columnIndex, boolean x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBoolean");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBoolean(java.lang.String, boolean)
	 */
	@Override
	public void updateBoolean(String columnLabel, boolean x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBoolean");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateByte(int, byte)
	 */
	@Override
	public void updateByte(int columnIndex, byte x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateByte");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateByte(java.lang.String, byte)
	 */
	@Override
	public void updateByte(String columnLabel, byte x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateByte");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBytes(int, byte[])
	 */
	@Override
	public void updateBytes(int columnIndex, byte[] x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBytes");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateBytes(java.lang.String, byte[])
	 */
	@Override
	public void updateBytes(String columnLabel, byte[] x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateBytes");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateCharacterStream(int, java.io.Reader)
	 */
	@Override
	public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateCharacterStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateCharacterStream(int, java.io.Reader, int)
	 */
	@Override
	public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateCharacterStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateCharacterStream(int, java.io.Reader, long)
	 */
	@Override
	public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateCharacterStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateCharacterStream(java.lang.String, java.io.Reader)
	 */
	@Override
	public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateCharacterStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateCharacterStream(java.lang.String, java.io.Reader, int)
	 */
	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateCharacterStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateCharacterStream(java.lang.String, java.io.Reader, long)
	 */
	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateCharacterStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateClob(int, java.sql.Clob)
	 */
	@Override
	public void updateClob(int columnIndex, Clob x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateClob(int, java.io.Reader)
	 */
	@Override
	public void updateClob(int columnIndex, Reader reader) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateClob(int, java.io.Reader, long)
	 */
	@Override
	public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateClob(java.lang.String, java.sql.Clob)
	 */
	@Override
	public void updateClob(String columnLabel, Clob x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateClob(java.lang.String, java.io.Reader)
	 */
	@Override
	public void updateClob(String columnLabel, Reader reader) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateClob(java.lang.String, java.io.Reader, long)
	 */
	@Override
	public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateDate(int, java.sql.Date)
	 */
	@Override
	public void updateDate(int columnIndex, Date x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateDate");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateDate(java.lang.String, java.sql.Date)
	 */
	@Override
	public void updateDate(String columnLabel, Date x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateDate");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateDouble(int, double)
	 */
	@Override
	public void updateDouble(int columnIndex, double x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateDouble");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateDouble(java.lang.String, double)
	 */
	@Override
	public void updateDouble(String columnLabel, double x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateDouble");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateFloat(int, float)
	 */
	@Override
	public void updateFloat(int columnIndex, float x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateFloat");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateFloat(java.lang.String, float)
	 */
	@Override
	public void updateFloat(String columnLabel, float x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateFloat");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateInt(int, int)
	 */
	@Override
	public void updateInt(int columnIndex, int x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateInt");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateInt(java.lang.String, int)
	 */
	@Override
	public void updateInt(String columnLabel, int x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateInt");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateLong(int, long)
	 */
	@Override
	public void updateLong(int columnIndex, long x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateLong");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateLong(java.lang.String, long)
	 */
	@Override
	public void updateLong(String columnLabel, long x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateLong");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNCharacterStream(int, java.io.Reader)
	 */
	@Override
	public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNCharacterStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNCharacterStream(int, java.io.Reader, long)
	 */
	@Override
	public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNCharacterStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNCharacterStream(java.lang.String, java.io.Reader)
	 */
	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNCharacterStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNCharacterStream(java.lang.String, java.io.Reader, long)
	 */
	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNCharacterStream");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNClob(int, java.sql.NClob)
	 */
	@Override
	public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNClob(int, java.io.Reader)
	 */
	@Override
	public void updateNClob(int columnIndex, Reader reader) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNClob(int, java.io.Reader, long)
	 */
	@Override
	public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNClob(java.lang.String, java.sql.NClob)
	 */
	@Override
	public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNClob(java.lang.String, java.io.Reader)
	 */
	@Override
	public void updateNClob(String columnLabel, Reader reader) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNClob(java.lang.String, java.io.Reader, long)
	 */
	@Override
	public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNString(int, java.lang.String)
	 */
	@Override
	public void updateNString(int columnIndex, String nString) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNString");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNString(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateNString(String columnLabel, String nString) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNString");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNull(int)
	 */
	@Override
	public void updateNull(int columnIndex) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNull");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateNull(java.lang.String)
	 */
	@Override
	public void updateNull(String columnLabel) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateNull");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateObject(int, java.lang.Object)
	 */
	@Override
	public void updateObject(int columnIndex, Object x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateObject");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateObject(int, java.lang.Object, int)
	 */
	@Override
	public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateObject");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateObject(java.lang.String, java.lang.Object)
	 */
	@Override
	public void updateObject(String columnLabel, Object x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateObject");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateObject(java.lang.String, java.lang.Object, int)
	 */
	@Override
	public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateObject");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateRef(int, java.sql.Ref)
	 */
	@Override
	public void updateRef(int columnIndex, Ref x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateRef");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateRef(java.lang.String, java.sql.Ref)
	 */
	@Override
	public void updateRef(String columnLabel, Ref x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateRef");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateRow()
	 */
	@Override
	public void updateRow() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateRow");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateRowId(int, java.sql.RowId)
	 */
	@Override
	public void updateRowId(int columnIndex, RowId x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateRowId");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateRowId(java.lang.String, java.sql.RowId)
	 */
	@Override
	public void updateRowId(String columnLabel, RowId x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateRowId");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateShort(int, short)
	 */
	@Override
	public void updateShort(int columnIndex, short x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateShort");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateShort(java.lang.String, short)
	 */
	@Override
	public void updateShort(String columnLabel, short x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateShort");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateSQLXML(int, java.sql.SQLXML)
	 */
	@Override
	public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateSQLXML");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateSQLXML(java.lang.String, java.sql.SQLXML)
	 */
	@Override
	public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateSQLXML");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateString(int, java.lang.String)
	 */
	@Override
	public void updateString(int columnIndex, String x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateString");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateString(java.lang.String, java.lang.String)
	 */
	@Override
	public void updateString(String columnLabel, String x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateString");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateTime(int, java.sql.Time)
	 */
	@Override
	public void updateTime(int columnIndex, Time x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateTime");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateTime(java.lang.String, java.sql.Time)
	 */
	@Override
	public void updateTime(String columnLabel, Time x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateTime");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateTimestamp(int, java.sql.Timestamp)
	 */
	@Override
	public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateTimestamp");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#updateTimestamp(java.lang.String, java.sql.Timestamp)
	 */
	@Override
	public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport updateTimestamp");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.ResultSet#wasNull()
	 */
	@Override
	public boolean wasNull() throws SQLException {
		checkClosed();

		return dataPool.wasNull();
	}

}
