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
package com.dianping.zebra.single.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
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

import com.dianping.zebra.filter.JdbcFilter;
import com.dianping.zebra.group.jdbc.JDBCOperationCallback;
import com.dianping.zebra.group.jdbc.param.ParamContext;
import com.dianping.zebra.util.SqlType;
import com.dianping.zebra.util.SqlUtils;

/**
 * @author hao.zhu
 */
public class SinglePreparedStatement extends SingleStatement implements PreparedStatement {

	private String sql;

	private List<ParamContext> params = new ArrayList<ParamContext>();

	private List<List<ParamContext>> pstBatchedArgs;

	public SinglePreparedStatement(Connection innerConnection, List<JdbcFilter> filters, Statement stmt, String sql)
			throws SQLException {
		super(innerConnection, filters, stmt);
		this.sql = sql;
	}

	private PreparedStatement getPreparedStatement() throws SQLException {
		checkClosed();
		return (PreparedStatement) openedStatement;
	}

	@Override
	public boolean execute() throws SQLException {
		SqlType sqlType = SqlUtils.getSqlType(sql);
		if (sqlType.isQuery()) {
			executeQuery();
			return true;
		} else {
			executeUpdate();
			return false;
		}
	}

	@Override
	public ResultSet executeQuery() throws SQLException {
		return executeWithFilter(new JDBCOperationCallback<ResultSet>() {
			@Override
			public ResultSet doAction(Connection conn) throws SQLException {
				return getPreparedStatement().executeQuery();
			}
		}, sql, params, false);
	}

	@Override
	public int executeUpdate() throws SQLException {
		return executeWithFilter(new JDBCOperationCallback<Integer>() {
			@Override
			public Integer doAction(Connection conn) throws SQLException {
				return getPreparedStatement().executeUpdate();
			}
		}, sql, params, false);
	}

	@Override
	public int[] executeBatch() throws SQLException {
		try {
			if (pstBatchedArgs == null || pstBatchedArgs.isEmpty()) {
				return new int[0];
			}

			return executeWithFilter(new JDBCOperationCallback<int[]>() {
				@Override
				public int[] doAction(Connection conn) throws SQLException {
					return getPreparedStatement().executeBatch();
				}
			}, sql, pstBatchedArgs, true);
		} finally {
			if (pstBatchedArgs != null) {
				pstBatchedArgs.clear();
			}
		}
	}

	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		getPreparedStatement().setNull(parameterIndex, sqlType);
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		getPreparedStatement().setBoolean(parameterIndex, x);
	}

	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		getPreparedStatement().setByte(parameterIndex, x);
	}

	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		getPreparedStatement().setShort(parameterIndex, x);
	}

	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		getPreparedStatement().setInt(parameterIndex, x);
	}

	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		getPreparedStatement().setLong(parameterIndex, x);
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		getPreparedStatement().setFloat(parameterIndex, x);
	}

	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
		getPreparedStatement().setDouble(parameterIndex, x);
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		getPreparedStatement().setBigDecimal(parameterIndex, x);
	}

	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		getPreparedStatement().setString(parameterIndex, x);
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		getPreparedStatement().setBytes(parameterIndex, x);
	}

	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {
		getPreparedStatement().setDate(parameterIndex, x);
	}

	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		getPreparedStatement().setTime(parameterIndex, x);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		getPreparedStatement().setTimestamp(parameterIndex, x);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		getPreparedStatement().setAsciiStream(parameterIndex, x, length);
	}

	@Override
	@Deprecated
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		getPreparedStatement().setUnicodeStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		getPreparedStatement().setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void clearParameters() throws SQLException {
		getPreparedStatement().clearParameters();
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		getPreparedStatement().setObject(parameterIndex, x, targetSqlType);
	}

	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		getPreparedStatement().setObject(parameterIndex, x);
	}

	@Override
	public void addBatch() throws SQLException {
		if (pstBatchedArgs == null) {
			pstBatchedArgs = new ArrayList<List<ParamContext>>();
		}
		List<ParamContext> newArgs = new ArrayList<ParamContext>(params.size());
		newArgs.addAll(params);

		params.clear();

		pstBatchedArgs.add(newArgs);
		getPreparedStatement().addBatch();
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		getPreparedStatement().setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		getPreparedStatement().setRef(parameterIndex, x);
	}

	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		getPreparedStatement().setBlob(parameterIndex, x);
	}

	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		getPreparedStatement().setClob(parameterIndex, x);
	}

	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		getPreparedStatement().setArray(parameterIndex, x);
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		return getPreparedStatement().getMetaData();
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
		getPreparedStatement().setDate(parameterIndex, x, cal);
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		getPreparedStatement().setTime(parameterIndex, x, cal);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		getPreparedStatement().setTimestamp(parameterIndex, x, cal);
	}

	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
		getPreparedStatement().setNull(parameterIndex, sqlType, typeName);
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		getPreparedStatement().setURL(parameterIndex, x);
	}

	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		return getPreparedStatement().getParameterMetaData();
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		getPreparedStatement().setRowId(parameterIndex, x);
	}

	@Override
	public void setNString(int parameterIndex, String value) throws SQLException {
		getPreparedStatement().setNString(parameterIndex, value);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		getPreparedStatement().setNCharacterStream(parameterIndex, value, length);
	}

	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		getPreparedStatement().setNClob(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		getPreparedStatement().setClob(parameterIndex, reader, length);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		getPreparedStatement().setBlob(parameterIndex, inputStream, length);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		getPreparedStatement().setNClob(parameterIndex, reader, length);
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		getPreparedStatement().setSQLXML(parameterIndex, xmlObject);
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
		getPreparedStatement().setObject(parameterIndex, x, targetSqlType, scaleOrLength);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		getPreparedStatement().setAsciiStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		getPreparedStatement().setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		getPreparedStatement().setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		getPreparedStatement().setAsciiStream(parameterIndex, x);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		getPreparedStatement().setBinaryStream(parameterIndex, x);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		getPreparedStatement().setCharacterStream(parameterIndex, reader);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		getPreparedStatement().setNCharacterStream(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		getPreparedStatement().setClob(parameterIndex, reader);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		getPreparedStatement().setBlob(parameterIndex, inputStream);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		getPreparedStatement().setNCharacterStream(parameterIndex, reader);
	}
}
