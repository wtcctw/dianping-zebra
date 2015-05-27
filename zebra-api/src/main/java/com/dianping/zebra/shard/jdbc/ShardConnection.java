/**
 * Project: ${zebra-client.aid}
 * 
 * File Created at 2011-6-7 $Id$
 * 
 * Copyright 2010 dianping.com. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Dianping
 * Company. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with dianping.com.
 */
package com.dianping.zebra.shard.jdbc;

import com.dianping.zebra.util.JDBCUtils;
import com.dianping.zebra.shard.router.DataSourceRouter;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;

/**
 * @author Leo Liang
 * @author hao.zhu
 * 
 */
public class ShardConnection implements Connection {

	private DataSourceRouter router;

	private Map<String, Connection> actualConnections = new HashMap<String, Connection>();

	private Set<Statement> attachedStatements = new HashSet<Statement>();

	private boolean closed = false;

	private boolean readOnly;

	private boolean autoCommit = true;

	private int transactionIsolation = -1;

	protected ResultSet generatedKey;

	public ShardConnection() {
	}

	public ShardConnection(String username, String password) {
	}

	public void abort(Executor executor) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport abort");
	}

	private void checkClosed() throws SQLException {
		if (closed) {
			throw new SQLException("No operations allowed after connection closed.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#clearWarnings()
	 */
	@Override
	public void clearWarnings() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport clearWarnings");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#close()
	 */
	@Override
	public void close() throws SQLException {

		if (closed) {
			return;
		}

		List<SQLException> innerExceptions = new ArrayList<SQLException>();

		try {
			if (generatedKey != null) {
				try {
					generatedKey.close();
				} catch (SQLException e) {
					innerExceptions.add(e);
				}
			}

			for (Statement stmt : attachedStatements) {
				try {
					stmt.close();
				} catch (SQLException e) {
					innerExceptions.add(e);
				}
			}

			for (Map.Entry<String, Connection> entry : actualConnections.entrySet()) {
				try {
					entry.getValue().close();
				} catch (SQLException e) {
					innerExceptions.add(e);
				}
			}
		} finally {
			closed = true;
			generatedKey = null;
			attachedStatements.clear();
			actualConnections.clear();
		}

		JDBCUtils.throwSQLExceptionIfNeeded(innerExceptions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#commit()
	 */
	@Override
	public void commit() throws SQLException {
		checkClosed();

		if (autoCommit) {
			return;
		}

		List<SQLException> innerExceptions = new ArrayList<SQLException>();

		for (Map.Entry<String, Connection> entry : actualConnections.entrySet()) {
			try {
				entry.getValue().commit();
			} catch (SQLException e) {
				innerExceptions.add(e);
			}
		}

		JDBCUtils.throwSQLExceptionIfNeeded(innerExceptions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createArrayOf(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport createArrayOf");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createBlob()
	 */
	@Override
	public Blob createBlob() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport createBlob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createClob()
	 */
	@Override
	public Clob createClob() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport createClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createNClob()
	 */
	@Override
	public NClob createNClob() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport createNClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createSQLXML()
	 */
	@Override
	public SQLXML createSQLXML() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport createSQLXML");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createStatement()
	 */
	@Override
	public Statement createStatement() throws SQLException {
		checkClosed();

		ShardStatement stmt = new ShardStatement();
		stmt.setRouter(router);
		stmt.setAutoCommit(autoCommit);
		stmt.setReadOnly(readOnly);
		stmt.setConnection(this);

		attachedStatements.add(stmt);

		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createStatement(int, int)
	 */
	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		ShardStatement stmt = (ShardStatement) createStatement();

		stmt.setResultSetType(resultSetType);
		stmt.setResultSetConcurrency(resultSetConcurrency);

		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createStatement(int, int, int)
	 */
	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
	      throws SQLException {
		ShardStatement stmt = (ShardStatement) createStatement(resultSetType, resultSetConcurrency);

		stmt.setResultSetHoldability(resultSetHoldability);

		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createStruct(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport createStruct");
	}

	public Map<String, Connection> getActualConnections() {
		return actualConnections;
	}

	public Set<Statement> getAttachedStatements() {
		return attachedStatements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getAutoCommit()
	 */
	@Override
	public boolean getAutoCommit() throws SQLException {
		return autoCommit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getCatalog()
	 */
	@Override
	public String getCatalog() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getCatalog");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getClientInfo()
	 */
	@Override
	public Properties getClientInfo() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getClientInfo");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getClientInfo(java.lang.String)
	 */
	@Override
	public String getClientInfo(String name) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getClientInfo");
	}

	/**
	 * @return the generatedKey
	 */
	public ResultSet getGeneratedKey() {
		return generatedKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getHoldability()
	 */
	@Override
	public int getHoldability() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getHoldability");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getMetaData()
	 */
	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		checkClosed();
		return new ShardDatabaseMetaData();
	}

	public int getNetworkTimeout() throws SQLException {
		throw new UnsupportedOperationException("getNetworkTimeout");
	}

	public DataSourceRouter getRouter() {
		return router;
	}

	public String getSchema() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getSchema");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getTransactionIsolation()
	 */
	@Override
	public int getTransactionIsolation() throws SQLException {
		return transactionIsolation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getTypeMap()
	 */
	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getTypeMap");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getWarnings()
	 */
	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getWarnings");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#isClosed()
	 */
	@Override
	public boolean isClosed() throws SQLException {
		return closed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#isReadOnly()
	 */
	@Override
	public boolean isReadOnly() throws SQLException {
		return readOnly;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#isValid(int)
	 */
	@Override
	public boolean isValid(int timeout) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport isValid");
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
	 * @see java.sql.Connection#nativeSQL(java.lang.String)
	 */
	@Override
	public String nativeSQL(String sql) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport nativeSQL");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareCall(java.lang.String)
	 */
	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport prepareCall");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int)
	 */
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport prepareCall");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int, int)
	 */
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
	      int resultSetHoldability) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport prepareCall");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		checkClosed();

		ShardPreparedStatement stmt = new ShardPreparedStatement();
		stmt.setRouter(router);
		stmt.setAutoCommit(autoCommit);
		stmt.setReadOnly(readOnly);
		stmt.setConnection(this);
		stmt.setSql(sql);

		attachedStatements.add(stmt);

		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		ShardPreparedStatement stmt = (ShardPreparedStatement) prepareStatement(sql);
		stmt.setAutoGeneratedKeys(autoGeneratedKeys);
		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
	      throws SQLException {
		ShardPreparedStatement stmt = (ShardPreparedStatement) prepareStatement(sql);
		stmt.setResultSetType(resultSetType);
		stmt.setResultSetConcurrency(resultSetConcurrency);
		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int, int)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
	      int resultSetHoldability) throws SQLException {
		ShardPreparedStatement stmt = (ShardPreparedStatement) prepareStatement(sql, resultSetType, resultSetConcurrency);
		stmt.setResultSetHoldability(resultSetHoldability);
		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int[])
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		ShardPreparedStatement stmt = (ShardPreparedStatement) prepareStatement(sql);
		stmt.setColumnIndexes(columnIndexes);
		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String, java.lang.String[])
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		ShardPreparedStatement stmt = (ShardPreparedStatement) prepareStatement(sql);
		stmt.setColumnNames(columnNames);
		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#releaseSavepoint(java.sql.Savepoint)
	 */
	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport releaseSavePoint");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#rollback()
	 */
	@Override
	public void rollback() throws SQLException {

		checkClosed();

		if (autoCommit) {
			return;
		}

		List<SQLException> exceptions = new ArrayList<SQLException>();

		for (Map.Entry<String, Connection> entry : actualConnections.entrySet()) {
			try {
				entry.getValue().rollback();
			} catch (SQLException e) {
				exceptions.add(e);
			}
		}

		JDBCUtils.throwSQLExceptionIfNeeded(exceptions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#rollback(java.sql.Savepoint)
	 */
	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport rollback with savepoint");
	}

	public void setActualConnections(Map<String, Connection> actualConnections) {
		this.actualConnections = actualConnections;
	}

	public void setAttachedStatements(Set<Statement> attachedStatements) {
		this.attachedStatements = attachedStatements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setAutoCommit(boolean)
	 */
	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		checkClosed();
		this.autoCommit = autoCommit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setCatalog(java.lang.String)
	 */
	@Override
	public void setCatalog(String catalog) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setCatalog");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setClientInfo(java.util.Properties)
	 */
	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		throw new UnsupportedOperationException("Zebra unsupport setClientInfo");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setClientInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		throw new UnsupportedOperationException("Zebra unsupport setClientInfo");
	}

	/**
	 * @param generatedKey
	 *           the generatedKey to set
	 */
	public void setGeneratedKey(ResultSet generatedKey) {
		this.generatedKey = generatedKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setHoldability(int)
	 */
	@Override
	public void setHoldability(int holdability) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setHoldability");
	}

	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setNetworkTimeout");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setReadOnly(boolean)
	 */
	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		checkClosed();

		this.readOnly = readOnly;
	}

	public void setRouter(DataSourceRouter router) {
		this.router = router;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setSavepoint()
	 */
	@Override
	public Savepoint setSavepoint() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setSavepoint");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setSavepoint(java.lang.String)
	 */
	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setSavepoint");
	}

	public void setSchema(String schema) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setSchema");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setTransactionIsolation(int)
	 */
	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		checkClosed();
		this.transactionIsolation = level;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setTypeMap(java.util.Map)
	 */
	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setTypeMap");
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
}
