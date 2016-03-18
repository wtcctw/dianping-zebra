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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.dianping.zebra.shard.jdbc.unsupport.UnsupportedShardResultSet;
import com.dianping.zebra.shard.merge.DataMerger;
import com.dianping.zebra.shard.merge.DataPool;
import com.dianping.zebra.shard.merge.DefaultDataMerger;
import com.dianping.zebra.shard.router.RouterResult;
import com.dianping.zebra.util.JDBCUtils;

/**
 * 
 * @author Leo Liang
 * 
 */
public class ShardResultSet extends UnsupportedShardResultSet implements ResultSet {

	private ShardStatement statement;

	private DataPool dataPool = new DataPool();

	private DataMerger dataMerger = new DefaultDataMerger();

	private boolean dataInited = false;

	private RouterResult routerTarget;

	private List<ResultSet> actualResultSets = new ArrayList<ResultSet>();

	private int fetchDirection = FETCH_FORWARD;

	private int fetchSize;

	private boolean closed = false;

	private void checkClosed() throws SQLException {
		if (closed) {
			throw new SQLException("No operations allowed after result set closed.");
		}
	}

	@Override
	public void close() throws SQLException {

		if (closed) {
			return;
		}

		List<SQLException> exceptions = new ArrayList<SQLException>();

		try {
			for (int i = 0; i < actualResultSets.size(); ++i) {
				try {
					actualResultSets.get(i).close();
				} catch (SQLException e) {
					exceptions.add(e);
				}
			}
		} finally {
			closed = true;
			dataPool.clear();
		}

		JDBCUtils.throwSQLExceptionIfNeeded(exceptions);
	}

	@Override
	public int findColumn(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.findColumn(columnLabel);
	}

	public List<ResultSet> getActualResultSets() {
		return actualResultSets;
	}

	@Override
	public Array getArray(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getArray(columnIndex);
	}

	@Override
	public Array getArray(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getArray(columnLabel);
	}

	@Override
	public InputStream getAsciiStream(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getAsciiStream(columnIndex);
	}

	@Override
	public InputStream getAsciiStream(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getAsciiStream(columnLabel);
	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getBigDecimal(columnIndex);
	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
		checkClosed();
		return dataPool.getBigDecimal(columnIndex, scale);
	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getBigDecimal(columnLabel);
	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
		checkClosed();
		return dataPool.getBigDecimal(columnLabel, scale);
	}

	@Override
	public InputStream getBinaryStream(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getBinaryStream(columnIndex);
	}

	@Override
	public InputStream getBinaryStream(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getBinaryStream(columnLabel);
	}

	@Override
	public Blob getBlob(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getBlob(columnIndex);
	}

	@Override
	public Blob getBlob(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getBlob(columnLabel);
	}

	@Override
	public boolean getBoolean(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getBoolean(columnIndex);
	}

	@Override
	public boolean getBoolean(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getBoolean(columnLabel);
	}

	@Override
	public byte getByte(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getByte(columnIndex);
	}

	@Override
	public byte getByte(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getByte(columnLabel);
	}

	@Override
	public byte[] getBytes(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getBytes(columnIndex);
	}

	@Override
	public byte[] getBytes(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getBytes(columnLabel);
	}

	@Override
	public Reader getCharacterStream(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getCharacterStream(columnIndex);
	}

	@Override
	public Reader getCharacterStream(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getCharacterStream(columnLabel);
	}

	@Override
	public Clob getClob(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getClob(columnIndex);
	}

	@Override
	public Clob getClob(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getClob(columnLabel);
	}

	@Override
	public int getConcurrency() throws SQLException {
		checkClosed();
		return dataPool.getConcurrency();
	}

	@Override
	public String getCursorName() throws SQLException {
		checkClosed();
		return dataPool.getCursorName();
	}

	@Override
	public Date getDate(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getDate(columnIndex);
	}

	@Override
	public Date getDate(int columnIndex, Calendar cal) throws SQLException {
		checkClosed();
		return dataPool.getDate(columnIndex, cal);
	}

	@Override
	public Date getDate(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getDate(columnLabel);
	}

	@Override
	public Date getDate(String columnLabel, Calendar cal) throws SQLException {
		checkClosed();
		return dataPool.getDate(columnLabel, cal);
	}

	@Override
	public double getDouble(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getDouble(columnIndex);
	}

	@Override
	public double getDouble(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getDouble(columnLabel);
	}

	@Override
	public int getFetchDirection() throws SQLException {
		checkClosed();
		return fetchDirection;
	}

	@Override
	public int getFetchSize() throws SQLException {
		checkClosed();
		return fetchSize;
	}

	@Override
	public float getFloat(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getFloat(columnIndex);
	}

	@Override
	public float getFloat(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getFloat(columnLabel);
	}

	@Override
	public int getHoldability() throws SQLException {
		checkClosed();
		return dataPool.getHoldability();
	}

	@Override
	public int getInt(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getInt(columnIndex);
	}

	@Override
	public int getInt(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getInt(columnLabel);
	}

	@Override
	public long getLong(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getLong(columnIndex);
	}

	@Override
	public long getLong(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getLong(columnLabel);
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		checkClosed();
		return dataPool.getMetaData();
	}

	@Override
	public Reader getNCharacterStream(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getNCharacterStream(columnIndex);
	}

	@Override
	public Reader getNCharacterStream(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getNCharacterStream(columnLabel);
	}

	@Override
	public NClob getNClob(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getNClob(columnIndex);
	}

	@Override
	public NClob getNClob(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getNClob(columnLabel);
	}

	@Override
	public String getNString(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getNString(columnIndex);
	}

	@Override
	public String getNString(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getNString(columnLabel);
	}

	@Override
	public Object getObject(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getObject(columnIndex);
	}

	@Override
	public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
		checkClosed();
		return dataPool.getObject(columnIndex, map);
	}

	@Override
	public Object getObject(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getObject(columnLabel);
	}

	@Override
	public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
		checkClosed();
		return dataPool.getObject(columnLabel, map);
	}

	@Override
	public Ref getRef(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getRef(columnIndex);
	}

	@Override
	public Ref getRef(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getRef(columnLabel);
	}

	@Override
	public int getRow() throws SQLException {
		checkClosed();
		return dataPool.getCurrentRowNo();
	}

	@Override
	public RowId getRowId(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getRowId(columnIndex);
	}

	@Override
	public RowId getRowId(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getRowId(columnLabel);
	}

	@Override
	public short getShort(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getShort(columnIndex);
	}

	@Override
	public short getShort(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getShort(columnLabel);
	}

	@Override
	public SQLXML getSQLXML(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getSQLXML(columnIndex);
	}

	@Override
	public SQLXML getSQLXML(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getSQLXML(columnLabel);
	}

	@Override
	public Statement getStatement() throws SQLException {
		return statement;
	}

	@Override
	public String getString(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getString(columnIndex);
	}

	@Override
	public String getString(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getString(columnLabel);
	}

	@Override
	public Time getTime(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getTime(columnIndex);
	}

	@Override
	public Time getTime(int columnIndex, Calendar cal) throws SQLException {
		checkClosed();
		return dataPool.getTime(columnIndex, cal);
	}

	@Override
	public Time getTime(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getTime(columnLabel);
	}

	@Override
	public Time getTime(String columnLabel, Calendar cal) throws SQLException {
		checkClosed();
		return dataPool.getTime(columnLabel, cal);
	}

	@Override
	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getTimestamp(columnIndex);
	}

	@Override
	public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
		checkClosed();
		return dataPool.getTimestamp(columnIndex, cal);
	}

	@Override
	public Timestamp getTimestamp(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getTimestamp(columnLabel);
	}

	@Override
	public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
		checkClosed();
		return dataPool.getTimestamp(columnLabel, cal);
	}

	@Override
	public int getType() throws SQLException {
		checkClosed();
		return dataPool.getType();
	}

	@Override
	public InputStream getUnicodeStream(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getUnicodeStream(columnIndex);
	}

	@Override
	public InputStream getUnicodeStream(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getUnicodeStream(columnLabel);
	}

	@Override
	public URL getURL(int columnIndex) throws SQLException {
		checkClosed();
		return dataPool.getURL(columnIndex);
	}

	@Override
	public URL getURL(String columnLabel) throws SQLException {
		checkClosed();
		return dataPool.getURL(columnLabel);
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

	@Override
	public boolean isClosed() throws SQLException {
		return closed;
	}

	@Override
	public boolean next() throws SQLException {
		checkClosed();
		if (!dataInited) {
			init();
		}
		return dataPool.next();
	}

	public void setActualResultSets(List<ResultSet> actualResultSets) {
		this.actualResultSets = actualResultSets;
	}

	public void setDataPool(DataPool dataPool) {
		this.dataPool = dataPool;
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		checkClosed();

		if (direction != FETCH_FORWARD) {
			throw new SQLException("only support fetch direction FETCH_FORWARD");
		}

		this.fetchDirection = direction;
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		checkClosed();

		if (rows < 0) {
			throw new SQLException("fetch size must greater than or equal 0");
		}

		this.fetchSize = rows;
	}

	public void setRouterTarget(RouterResult routerTarget) {
		this.routerTarget = routerTarget;
	}

	public void setStatement(ShardStatement statementWrapper) {
		this.statement = statementWrapper;
	}

	@Override
	public boolean wasNull() throws SQLException {
		checkClosed();

		return dataPool.wasNull();
	}

}
