/**
 * Project: ${zebra-client.aid}
 *
 * File Created at 2011-6-10 $Id$
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.dianping.zebra.group.jdbc.param.ArrayParamContext;
import com.dianping.zebra.group.jdbc.param.AsciiParamContext;
import com.dianping.zebra.group.jdbc.param.BigDecimalParamContext;
import com.dianping.zebra.group.jdbc.param.BinaryStreamParamContext;
import com.dianping.zebra.group.jdbc.param.BlobParamContext;
import com.dianping.zebra.group.jdbc.param.BooleanParamContext;
import com.dianping.zebra.group.jdbc.param.ByteArrayParamContext;
import com.dianping.zebra.group.jdbc.param.ByteParamContext;
import com.dianping.zebra.group.jdbc.param.CharacterStreamParamContext;
import com.dianping.zebra.group.jdbc.param.ClobParamContext;
import com.dianping.zebra.group.jdbc.param.DateParamContext;
import com.dianping.zebra.group.jdbc.param.DoubleParamContext;
import com.dianping.zebra.group.jdbc.param.FloatParamContext;
import com.dianping.zebra.group.jdbc.param.IntParamContext;
import com.dianping.zebra.group.jdbc.param.LongParamContext;
import com.dianping.zebra.group.jdbc.param.NCharacterStreamParamContext;
import com.dianping.zebra.group.jdbc.param.NClobParamContext;
import com.dianping.zebra.group.jdbc.param.NStringParamContext;
import com.dianping.zebra.group.jdbc.param.NullParamContext;
import com.dianping.zebra.group.jdbc.param.ObjectParamContext;
import com.dianping.zebra.group.jdbc.param.ParamContext;
import com.dianping.zebra.group.jdbc.param.RefParamContext;
import com.dianping.zebra.group.jdbc.param.RowIdParamContext;
import com.dianping.zebra.group.jdbc.param.SQLXMLParamContext;
import com.dianping.zebra.group.jdbc.param.ShortParamContext;
import com.dianping.zebra.group.jdbc.param.StringParamContext;
import com.dianping.zebra.group.jdbc.param.TimeParamContext;
import com.dianping.zebra.group.jdbc.param.TimestampParamContext;
import com.dianping.zebra.group.jdbc.param.URLParamContext;
import com.dianping.zebra.group.jdbc.param.UnicodeStreamParamContext;
import com.dianping.zebra.util.JDBCUtils;
import com.dianping.zebra.shard.router.RouterTarget;
import com.dianping.zebra.shard.router.TargetedSql;

/**
 * Zebra的PreparedStatement wrapper
 *
 * @author Leo Liang
 */
public class ShardPreparedStatement extends ShardStatement implements PreparedStatement {

	private static final Logger log = Logger.getLogger(ShardPreparedStatement.class);

	private String sql;

	private int autoGeneratedKeys = -1;

	private int[] columnIndexes;

	private String[] columnNames;

	private List<ParamContext> params = new ArrayList<ParamContext>();

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		if (columnNames != null && columnNames.length != 0) {
			this.columnNames = new String[columnNames.length];
			for (int i = 0; i < columnNames.length; i++) {
				this.columnNames[i] = columnNames[i];
			}
		}
	}

	public int[] getColumnIndexes() {
		return columnIndexes;
	}

	public void setColumnIndexes(int[] columnIndexes) {
		if (columnIndexes != null && columnIndexes.length != 0) {
			this.columnIndexes = new int[columnIndexes.length];
			for (int i = 0; i < columnIndexes.length; i++) {
				this.columnIndexes[i] = columnIndexes[i];
			}
		}
	}

	public int getAutoGeneratedKeys() {
		return autoGeneratedKeys;
	}

	public void setAutoGeneratedKeys(int autoGeneratedKeys) {
		this.autoGeneratedKeys = autoGeneratedKeys;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	private PreparedStatement _prepareStatement(Connection connection, String targetSql) throws SQLException {
		PreparedStatement stmt = null;
		if (getResultSetType() != -1 && getResultSetConcurrency() != -1 && getResultSetHoldability() != -1) {
			stmt = connection.prepareStatement(targetSql, getResultSetType(), getResultSetConcurrency(),
				getResultSetHoldability());
		} else if (getResultSetType() != -1 && getResultSetConcurrency() != -1) {
			stmt = connection.prepareStatement(targetSql, getResultSetType(), getResultSetConcurrency());
		} else if (autoGeneratedKeys != -1) {
			stmt = connection.prepareStatement(targetSql, autoGeneratedKeys);
		} else if (columnIndexes != null) {
			stmt = connection.prepareStatement(targetSql, columnIndexes);
		} else if (columnNames != null) {
			stmt = connection.prepareStatement(targetSql, columnNames);
		} else {
			stmt = connection.prepareStatement(targetSql, Statement.RETURN_GENERATED_KEYS);
		}

		return stmt;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#addBatch()
	 */
	@Override
	public void addBatch() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport addBatch");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#clearParameters()
	 */
	@Override
	public void clearParameters() throws SQLException {
		params.clear();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#execute()
	 */
	@Override
	public boolean execute() throws SQLException {

		JudgeSQLRetVal judgeSQLRetVal = judgeSQLType(sql);
		if (judgeSQLRetVal.getSqlType() == SQLType.SELECT) {
			executeQuery();
			return true;
		} else if (judgeSQLRetVal.getSqlType() == SQLType.INSERT || judgeSQLRetVal.getSqlType() == SQLType.UPDATE
			|| judgeSQLRetVal.getSqlType() == SQLType.DELETE) {
			executeUpdate();
			return false;
		} else {
			throw new SQLException("only select, insert, update, delete sql is supported");
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected List<Object> getParams() {
		Collections.sort(params, new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				ParamContext context1 = (ParamContext) o1;
				ParamContext context2 = (ParamContext) o2;

				return context1.getIndex() < context2.getIndex() ? -1 : (context1.getIndex() > context2.getIndex() ? 1
					: 0);
			}

		});

		List<Object> parameters = new ArrayList<Object>();
		for (ParamContext context : params) {
			parameters.add(context.getValues()[0]);
		}

		return parameters;
	}

	protected void setParams(PreparedStatement stmt) throws SQLException {
		for (ParamContext paramContext : params) {
			paramContext.setParam(stmt);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#executeQuery()
	 */
	@Override
	public ResultSet executeQuery() throws SQLException {

		checkClosed();

		ResultSet specRS = beforeQuery(sql);
		if (specRS != null) {
			this.results = specRS;
			this.moreResults = false;
			this.updateCount = -1;
			attachedResultSets.add(specRS);
			return this.results;
		}

		RouterTarget routerTarget = routingAndCheck(sql, getParams());

		rewriteAndMergeParms(routerTarget.getNewParams());

		ShardResultSet rs = new ShardResultSet();
		rs.setStatementWrapper(this);
		rs.setRouterTarget(routerTarget);

		attachedResultSets.add(rs);

		List<SQLException> exceptions = new ArrayList<SQLException>();

		for (TargetedSql targetedSql : routerTarget.getTargetedSqls()) {
			for (String executableSql : targetedSql.getSqls()) {
				try {
					Connection conn = getConnectionWrapper().getActualConnections()
						.get(targetedSql.getDataSourceName());
					if (conn == null) {
						conn = targetedSql.getDataSource().getConnection();
						getConnectionWrapper().getActualConnections().put(targetedSql.getDataSourceName(), conn);
					}
					PreparedStatement stmt = _prepareStatement(conn, executableSql);
					actualStatements.add(stmt);
					setParams(stmt);
					rs.getActualResultSets().add(stmt.executeQuery());
				} catch (SQLException e) {
					exceptions.add(e);
					log.error(e);
				}
			}
		}

		this.results = rs;
		this.moreResults = false;
		this.updateCount = -1;
		rs.init();

		JDBCUtils.throwSQLExceptionIfNeeded(exceptions);

		return this.results;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#executeUpdate()
	 */
	@Override
	public int executeUpdate() throws SQLException {

		checkClosed();

		RouterTarget routerTarget = routingAndCheck(sql, getParams());

		rewriteAndMergeParms(routerTarget.getNewParams());

		int affectedRows = 0;
		List<SQLException> exceptions = new ArrayList<SQLException>();

		for (TargetedSql targetedSql : routerTarget.getTargetedSqls()) {
			for (String executableSql : targetedSql.getSqls()) {
				try {
					Connection conn = getConnectionWrapper().getActualConnections()
						.get(targetedSql.getDataSourceName());
					if (conn == null) {
						conn = targetedSql.getDataSource().getConnection();
						getConnectionWrapper().getActualConnections().put(targetedSql.getDataSourceName(), conn);
					}
					PreparedStatement stmt = _prepareStatement(conn, executableSql);
					actualStatements.add(stmt);
					setParams(stmt);

					affectedRows += stmt.executeUpdate();

					// 把insert语句返回的生成key保存在当前会话中
					if (executableSql.trim().charAt(0) == 'i' || executableSql.trim().charAt(0) == 'I') {
						getAndSetGeneratedKeys(stmt);
					}
				} catch (SQLException e) {
					exceptions.add(e);
					log.error(e);
				}
			}
		}

		this.results = null;
		this.moreResults = false;
		this.updateCount = affectedRows;

		JDBCUtils.throwSQLExceptionIfNeeded(exceptions);

		return affectedRows;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#getMetaData()
	 */
	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getMetaData");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#getParameterMetaData()
	 */
	@Override
	public ParameterMetaData getParameterMetaData() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getParameterMetaData");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setArray(int, java.sql.Array)
	 */
	@Override
	public void setArray(int parameterIndex, Array x) throws SQLException {
		params.add(new ArrayParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setAsciiStream(int, java.io.InputStream)
	 */
	@Override
	public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
		params.add(new AsciiParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setAsciiStream(int, java.io.InputStream,
	 * int)
	 */
	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
		params.add(new AsciiParamContext(parameterIndex, new Object[] { x, length }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setAsciiStream(int, java.io.InputStream,
	 * long)
	 */
	@Override
	public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
		params.add(new AsciiParamContext(parameterIndex, new Object[] { x, length }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setBigDecimal(int, java.math.BigDecimal)
	 */
	@Override
	public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
		params.add(new BigDecimalParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setBinaryStream(int, java.io.InputStream)
	 */
	@Override
	public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
		params.add(new BinaryStreamParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setBinaryStream(int, java.io.InputStream,
	 * int)
	 */
	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
		params.add(new BinaryStreamParamContext(parameterIndex, new Object[] { x, length }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setBinaryStream(int, java.io.InputStream,
	 * long)
	 */
	@Override
	public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
		params.add(new BinaryStreamParamContext(parameterIndex, new Object[] { x, length }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setBlob(int, java.sql.Blob)
	 */
	@Override
	public void setBlob(int parameterIndex, Blob x) throws SQLException {
		params.add(new BlobParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setBlob(int, java.io.InputStream)
	 */
	@Override
	public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
		params.add(new BlobParamContext(parameterIndex, new Object[] { inputStream }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setBlob(int, java.io.InputStream, long)
	 */
	@Override
	public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
		params.add(new BlobParamContext(parameterIndex, new Object[] { inputStream, length }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setBoolean(int, boolean)
	 */
	@Override
	public void setBoolean(int parameterIndex, boolean x) throws SQLException {
		params.add(new BooleanParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setByte(int, byte)
	 */
	@Override
	public void setByte(int parameterIndex, byte x) throws SQLException {
		params.add(new ByteParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setBytes(int, byte[])
	 */
	@Override
	public void setBytes(int parameterIndex, byte[] x) throws SQLException {
		params.add(new ByteArrayParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setCharacterStream(int, java.io.Reader)
	 */
	@Override
	public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
		params.add(new CharacterStreamParamContext(parameterIndex, new Object[] { reader }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setCharacterStream(int, java.io.Reader,
	 * int)
	 */
	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
		params.add(new CharacterStreamParamContext(parameterIndex, new Object[] { reader, length }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setCharacterStream(int, java.io.Reader,
	 * long)
	 */
	@Override
	public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
		params.add(new CharacterStreamParamContext(parameterIndex, new Object[] { reader, length }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setClob(int, java.sql.Clob)
	 */
	@Override
	public void setClob(int parameterIndex, Clob x) throws SQLException {
		params.add(new ClobParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setClob(int, java.io.Reader)
	 */
	@Override
	public void setClob(int parameterIndex, Reader reader) throws SQLException {
		params.add(new ClobParamContext(parameterIndex, new Object[] { reader }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setClob(int, java.io.Reader, long)
	 */
	@Override
	public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
		params.add(new ClobParamContext(parameterIndex, new Object[] { reader, length }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setDate(int, java.sql.Date)
	 */
	@Override
	public void setDate(int parameterIndex, Date x) throws SQLException {
		params.add(new DateParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setDate(int, java.sql.Date,
	 * java.util.Calendar)
	 */
	@Override
	public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
		params.add(new DateParamContext(parameterIndex, new Object[] { x, cal }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setDouble(int, double)
	 */
	@Override
	public void setDouble(int parameterIndex, double x) throws SQLException {
		params.add(new DoubleParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setFloat(int, float)
	 */
	@Override
	public void setFloat(int parameterIndex, float x) throws SQLException {
		params.add(new FloatParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setInt(int, int)
	 */
	@Override
	public void setInt(int parameterIndex, int x) throws SQLException {
		params.add(new IntParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setLong(int, long)
	 */
	@Override
	public void setLong(int parameterIndex, long x) throws SQLException {
		params.add(new LongParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setNCharacterStream(int, java.io.Reader)
	 */
	@Override
	public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
		params.add(new NCharacterStreamParamContext(parameterIndex, new Object[] { value }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setNCharacterStream(int, java.io.Reader,
	 * long)
	 */
	@Override
	public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
		params.add(new NCharacterStreamParamContext(parameterIndex, new Object[] { value, length }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setNClob(int, java.sql.NClob)
	 */
	@Override
	public void setNClob(int parameterIndex, NClob value) throws SQLException {
		params.add(new NClobParamContext(parameterIndex, new Object[] { value }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setNClob(int, java.io.Reader)
	 */
	@Override
	public void setNClob(int parameterIndex, Reader reader) throws SQLException {
		params.add(new NClobParamContext(parameterIndex, new Object[] { reader }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setNClob(int, java.io.Reader, long)
	 */
	@Override
	public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
		params.add(new NClobParamContext(parameterIndex, new Object[] { reader, length }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setNString(int, java.lang.String)
	 */
	@Override
	public void setNString(int parameterIndex, String value) throws SQLException {
		params.add(new NStringParamContext(parameterIndex, new Object[] { value }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setNull(int, int)
	 */
	@Override
	public void setNull(int parameterIndex, int sqlType) throws SQLException {
		params.add(new NullParamContext(parameterIndex, new Object[] { sqlType }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setNull(int, int, java.lang.String)
	 */
	@Override
	public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
		params.add(new NullParamContext(parameterIndex, new Object[] { sqlType, typeName }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setObject(int, java.lang.Object)
	 */
	@Override
	public void setObject(int parameterIndex, Object x) throws SQLException {
		params.add(new ObjectParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setObject(int, java.lang.Object, int)
	 */
	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		params.add(new ObjectParamContext(parameterIndex, new Object[] { x, targetSqlType }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setObject(int, java.lang.Object, int,
	 * int)
	 */
	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
		params.add(new ObjectParamContext(parameterIndex, new Object[] { x, targetSqlType, scaleOrLength }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setRef(int, java.sql.Ref)
	 */
	@Override
	public void setRef(int parameterIndex, Ref x) throws SQLException {
		params.add(new RefParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setRowId(int, java.sql.RowId)
	 */
	@Override
	public void setRowId(int parameterIndex, RowId x) throws SQLException {
		params.add(new RowIdParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setSQLXML(int, java.sql.SQLXML)
	 */
	@Override
	public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
		params.add(new SQLXMLParamContext(parameterIndex, new Object[] { xmlObject }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setShort(int, short)
	 */
	@Override
	public void setShort(int parameterIndex, short x) throws SQLException {
		params.add(new ShortParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setString(int, java.lang.String)
	 */
	@Override
	public void setString(int parameterIndex, String x) throws SQLException {
		params.add(new StringParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setTime(int, java.sql.Time)
	 */
	@Override
	public void setTime(int parameterIndex, Time x) throws SQLException {
		params.add(new TimeParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setTime(int, java.sql.Time,
	 * java.util.Calendar)
	 */
	@Override
	public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
		params.add(new TimeParamContext(parameterIndex, new Object[] { x, cal }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setTimestamp(int, java.sql.Timestamp)
	 */
	@Override
	public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
		params.add(new TimestampParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setTimestamp(int, java.sql.Timestamp,
	 * java.util.Calendar)
	 */
	@Override
	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
		params.add(new TimestampParamContext(parameterIndex, new Object[] { x, cal }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setURL(int, java.net.URL)
	 */
	@Override
	public void setURL(int parameterIndex, URL x) throws SQLException {
		params.add(new URLParamContext(parameterIndex, new Object[] { x }));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.PreparedStatement#setUnicodeStream(int,
	 * java.io.InputStream, int)
	 */
	@Override
	public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
		params.add(new UnicodeStreamParamContext(parameterIndex, new Object[] { x, length }));
	}

	private void rewriteAndMergeParms(List<Object> newParams) {
		if (newParams == null) {
			return;
		}
		int index = 0;
		for (Object newParam : newParams) {
			ParamContext context = params.get(index++);
			context.getValues()[0] = newParam;
		}
	}

}