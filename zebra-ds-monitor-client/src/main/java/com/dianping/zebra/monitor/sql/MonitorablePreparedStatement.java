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

import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.MessageProducer;
import com.dianping.cat.message.Transaction;
import com.site.helper.Stringizers;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author danson.liu
 */
public class MonitorablePreparedStatement extends MonitorableStatement implements PreparedStatement {
	private static final String SQL_STATEMENT_NAME = "sql_statement_name";

	private static final String CAT_LOGED = "cat_log";

	private final PreparedStatement prepareStatement;

	protected String sql;

	private Object[] params;

	private int maxParamIndex;

	private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	private int notPlainBatchSize;

	private static final Map<Integer, String> SQL_LENGTH_RANGE = new LinkedHashMap<Integer, String>();

	static {
		SQL_LENGTH_RANGE.put(1024, "<= 1K");
		SQL_LENGTH_RANGE.put(10240, "<= 10K");
		SQL_LENGTH_RANGE.put(102400, "<= 100K");
		SQL_LENGTH_RANGE.put(1024 * 1024, "<= 1M");
		SQL_LENGTH_RANGE.put(10240 * 1024, "<= 10M");
		SQL_LENGTH_RANGE.put(102400 * 1024, "<= 100M");
		SQL_LENGTH_RANGE.put(Integer.MAX_VALUE, "> 100M");
	}

	private void logSqlLengthEvent(String sql) {
		int length = sql == null ? 0 : sql.length();

		for (Map.Entry<Integer, String> item : SQL_LENGTH_RANGE.entrySet()) {
			if (length <= item.getKey()) {
				Cat.logEvent("SQL.Length", item.getValue());
				return;
			}
		}
	}

	public MonitorablePreparedStatement(PreparedStatement prepareStatement, String sql,
			MonitorableConnection monitorableConnection) {
		super(prepareStatement, monitorableConnection);
		this.prepareStatement = prepareStatement;
		this.sql = sql;
		initParams();
	}

	private String buildSqlType(String sql) {
		try {
			char c = sql.trim().charAt(0);
			if (c == 's' || c == 'S') {
				return "Select";
			} else if (c == 'u' || c == 'U') {
				return "Update";
			} else if (c == 'i' || c == 'I') {
				return "Insert";
			} else if (c == 'd' || c == 'D') {
				return "Delete";
			} else if (c == 'c' || c == 'C') {
				return "Call";
			}
		} catch (Exception e) {
		}
		return "Execute";
	}

	private <T> T executeWithCat(JDBCOperationCallback<T> callback, String type) throws SQLException {
		String isLoged = ExecutionContextHolder.getContext().get(CAT_LOGED);
		if (isLoged == null) {
			String stateName = ExecutionContextHolder.getContext().get(SQL_STATEMENT_NAME);
			MessageProducer cat = Cat.getProducer();
			Transaction t = cat.newTransaction("SQL", stateName);
			t.addData(sql);
			try {
				cat.logEvent("SQL.Database", monitorableConnection.getMetaData().getURL());
				cat.logEvent("SQL.Method", buildSqlType(sql),
						Transaction.SUCCESS, Stringizers.forJson().compact()
								.from(getParamList(), CatConstants.MAX_LENGTH, CatConstants.MAX_ITEM_LENGTH));
				logSqlLengthEvent(sql);
				t.setStatus(Transaction.SUCCESS);
				ExecutionContextHolder.getContext().add(CAT_LOGED, "Loged");
				return callback.doAction();
			} catch (SQLException e) {
				cat.logError(e);
				t.setStatus(e);
				throw e;
			} finally {
				t.complete();
				ExecutionContextHolder.getContext().clear(CAT_LOGED);
			}
		} else {
			return callback.doAction();
		}
	}

	@Override
	public ResultSet executeQuery() throws SQLException {
		return executeWithCat(new JDBCOperationCallback<ResultSet>() {
			@Override
			public ResultSet doAction() throws SQLException {
				checkClosed();
				ResultSet resultSet = prepareStatement.executeQuery();
				return resultSet;
			}
		}, "Query");
	}

	@Override
	public int executeUpdate() throws SQLException {
		return executeWithCat(new JDBCOperationCallback<Integer>() {
			@Override
			public Integer doAction() throws SQLException {
				checkClosed();
				int updatedCount = prepareStatement.executeUpdate();
				return updatedCount;
			}
		}, "Update");
	}

	@Override
	public boolean execute() throws SQLException {
		return executeWithCat(new JDBCOperationCallback<Boolean>() {
			@Override
			public Boolean doAction() throws SQLException {
				checkClosed();
				boolean hasResultSet = prepareStatement.execute();
				return hasResultSet;
			}
		}, "Execute");
	}

	@Override
	public void clearBatch() throws SQLException {
		prepareStatement.clearBatch();
		notPlainBatchSize = 0;
	}

	@Override
	public void addBatch() throws SQLException {
		prepareStatement.addBatch();
		notPlainBatchSize++;
	}

	@Override
	public int[] executeBatch() throws SQLException {
		return executeWithCat(new JDBCOperationCallback<int[]>() {
			@Override
			public int[] doAction() throws SQLException {
				checkClosed();
				boolean batchHasNotplainStatements = notPlainBatchSize > 0;
				if (batchHasNotplainStatements) {
					try {
						int[] batchResult = prepareStatement.executeBatch();
						return batchResult;
					} finally {
						notPlainBatchSize = 0;
					}
				} else {
					return prepareStatement.executeBatch();
				}
			}
		}, "Batch");

	}

	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		setParameter(parameterIndex, null);
		prepareStatement.setNull(parameterIndex, sqlType);
	}

	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		setParameter(parameterIndex, x);
		prepareStatement.setBoolean(parameterIndex, x);
	}

	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		setParameter(parameterIndex, x);
		prepareStatement.setByte(parameterIndex, x);
	}

	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		setParameter(parameterIndex, x);
		prepareStatement.setShort(parameterIndex, x);
	}

	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		setParameter(parameterIndex, x);
		prepareStatement.setInt(parameterIndex, x);
	}

	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		setParameter(parameterIndex, x);
		prepareStatement.setLong(parameterIndex, x);
	}

	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		setParameter(parameterIndex, x);
		prepareStatement.setFloat(parameterIndex, x);
	}

	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
		setParameter(parameterIndex, x);
		prepareStatement.setDouble(parameterIndex, x);
	}

	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		setParameter(parameterIndex, x.scale() > 4 ? x.setScale(4, BigDecimal.ROUND_HALF_UP) : x);
		prepareStatement.setBigDecimal(parameterIndex, x);
	}

	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		setParameter(parameterIndex, x);
		prepareStatement.setString(parameterIndex, x);
	}

	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		setParameter(parameterIndex, "byte[]");
		prepareStatement.setBytes(parameterIndex, x);
	}

	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {
		setParameter(parameterIndex, dateFormat.format(x));
		prepareStatement.setDate(parameterIndex, x);
	}

	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		setParameter(parameterIndex, timeFormat.format(x));
		prepareStatement.setTime(parameterIndex, x);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		setParameter(parameterIndex, dateTimeFormat.format(x));
		prepareStatement.setTimestamp(parameterIndex, x);
	}

	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
		setParameter(parameterIndex, dateFormat.format(x));
		prepareStatement.setDate(parameterIndex, x, cal);
	}

	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		setParameter(parameterIndex, timeFormat.format(x));
		prepareStatement.setTime(parameterIndex, x, cal);
	}

	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		setParameter(parameterIndex, dateTimeFormat.format(x));
		prepareStatement.setTimestamp(parameterIndex, x, cal);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		setParameter(parameterIndex, "[ascii-stream]");
		prepareStatement.setAsciiStream(parameterIndex, x, length);
	}

	@Override
	@Deprecated
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		setParameter(parameterIndex, "[unicode-stream]");
		prepareStatement.setUnicodeStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		setParameter(parameterIndex, "[binary-stream]");
		prepareStatement.setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		setParameter(parameterIndex, "[object]");
		prepareStatement.setObject(parameterIndex, x, targetSqlType);
	}

	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		setParameter(parameterIndex, "[object]");
		prepareStatement.setObject(parameterIndex, x);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		setParameter(parameterIndex, "[char-stream]");
		prepareStatement.setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		setParameter(parameterIndex, "[ref]");
		prepareStatement.setRef(parameterIndex, x);
	}

	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		setParameter(parameterIndex, "[blob]");
		prepareStatement.setBlob(parameterIndex, x);
	}

	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		setParameter(parameterIndex, "[clob]");
		prepareStatement.setClob(parameterIndex, x);
	}

	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		setParameter(parameterIndex, "[array]");
		prepareStatement.setArray(parameterIndex, x);
	}

	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
		setParameter(parameterIndex, null);
		prepareStatement.setNull(parameterIndex, sqlType, typeName);
	}

	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		setParameter(parameterIndex, x);
		prepareStatement.setURL(parameterIndex, x);
	}

	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		setParameter(parameterIndex, "[rowid]");
		prepareStatement.setRowId(parameterIndex, x);
	}

	@Override
	public void setNString(int parameterIndex, String value) throws SQLException {
		setParameter(parameterIndex, value);
		prepareStatement.setNString(parameterIndex, value);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		setParameter(parameterIndex, "[nchar-stream]");
		prepareStatement.setNCharacterStream(parameterIndex, value, length);
	}

	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		setParameter(parameterIndex, "[nclob]");
		prepareStatement.setNClob(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		setParameter(parameterIndex, "[clob]");
		prepareStatement.setClob(parameterIndex, reader, length);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		setParameter(parameterIndex, "[blob]");
		prepareStatement.setBlob(parameterIndex, inputStream, length);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		setParameter(parameterIndex, "[nclob]");
		prepareStatement.setNClob(parameterIndex, reader, length);
	}

	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		setParameter(parameterIndex, "[xml]");
		prepareStatement.setSQLXML(parameterIndex, xmlObject);
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
		setParameter(parameterIndex, "[object]");
		prepareStatement.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		setParameter(parameterIndex, "[ascii-stream]");
		prepareStatement.setAsciiStream(parameterIndex, x, length);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		setParameter(parameterIndex, "[binary-stream]");
		prepareStatement.setBinaryStream(parameterIndex, x, length);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		setParameter(parameterIndex, "[char-stream]");
		prepareStatement.setCharacterStream(parameterIndex, reader, length);
	}

	@Override
	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		setParameter(parameterIndex, "[ascii-stream]");
		prepareStatement.setAsciiStream(parameterIndex, x);
	}

	@Override
	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		setParameter(parameterIndex, "[binary-stream]");
		prepareStatement.setBinaryStream(parameterIndex, x);
	}

	@Override
	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		setParameter(parameterIndex, "[char-stream]");
		prepareStatement.setCharacterStream(parameterIndex, reader);
	}

	@Override
	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		setParameter(parameterIndex, "[nchar-stream]");
		prepareStatement.setNCharacterStream(parameterIndex, value);
	}

	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		setParameter(parameterIndex, "[clob]");
		prepareStatement.setClob(parameterIndex, reader);
	}

	@Override
	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		setParameter(parameterIndex, "[blob]");
		prepareStatement.setBlob(parameterIndex, inputStream);
	}

	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		setParameter(parameterIndex, "[nclob]");
		prepareStatement.setNClob(parameterIndex, reader);
	}

	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		return prepareStatement.getParameterMetaData();
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		return prepareStatement.getMetaData();
	}

	@Override
	public void clearParameters() throws SQLException {
		initParams();
		prepareStatement.clearParameters();
	}

	@Override
	public void close() throws SQLException {
		super.close();
		params = null;
		sql = null;
		maxParamIndex = 0;
	}

	private void setParameter(int parameterIndex, Object parameterValue) {
		try {
			int paramIndex = parameterIndex - 1;
			int paramLen = params.length;
			if (paramIndex > paramLen - 1) {
				int newParamLen = (int) (paramLen * 1.5);
				Object[] newParams = new Object[newParamLen > parameterIndex ? newParamLen : parameterIndex];
				System.arraycopy(params, 0, newParams, 0, paramLen);
				params = newParams;
			}
			params[paramIndex] = parameterValue;
			if (parameterIndex > maxParamIndex) {
				maxParamIndex = parameterIndex;
			}
		} catch (Throwable throwable) {
		}
	}

	private void initParams() {
		try {
			String lowerSql = sql.toLowerCase().trim();
			params = null;
			if (lowerSql.startsWith("insert")) {
				params = new Object[25];
			} else {
				params = new Object[10];
			}
		} catch (Throwable throwable) {
		}
	}

	private List<Object> getParamList() {
		try {
			if (maxParamIndex <= 0) {
				return null;
			}
			List<Object> paramList = new ArrayList<Object>();
			for (int i = 0; i < maxParamIndex; i++) {
				paramList.add(params[i]);
			}
			return paramList;
		} catch (Throwable throwable) {
			return null;
		}
	}

	interface JDBCOperationCallback<T> {
		T doAction() throws SQLException;
	}
}
