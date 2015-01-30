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

import com.dianping.zebra.shard.jdbc.util.JDBCUtils;
import com.dianping.zebra.shard.router.DataSourceRouter;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * Zebra的JDBC Connection Wrapper
 * 
 * @author Leo Liang
 * 
 */
public class DPConnection implements Connection {

	private static final Logger		log							= Logger.getLogger(DPConnection.class);

	private DataSourceRouter router;

	private Map<String, Connection>	actualConnections			= new HashMap<String, Connection>();
	private Set<Statement>			attachedStatements			= new HashSet<Statement>();
	private boolean					closed						= false;
	private boolean					readOnly;
	private boolean					autoCommit					= true;
	private int						transactionIsolation		= -1;

	private boolean					performanceMonitorSwitchOn	= false;

	protected ResultSet				generatedKey;

	/**
	 * @return the performanceMonitorSwitchOn
	 */
	public boolean isPerformanceMonitorSwitchOn() {
		return performanceMonitorSwitchOn;
	}

	/**
	 * @param performanceMonitorSwitchOn
	 *            the performanceMonitorSwitchOn to set
	 */
	public void setPerformanceMonitorSwitchOn(boolean performanceMonitorSwitchOn) {
		this.performanceMonitorSwitchOn = performanceMonitorSwitchOn;
	}

	public DPConnection() {

	}

	public DPConnection(String username, String password) {
	}

	/**
	 * @return the generatedKey
	 */
	public ResultSet getGeneratedKey() {
		return generatedKey;
	}

	/**
	 * @param generatedKey
	 *            the generatedKey to set
	 */
	public void setGeneratedKey(ResultSet generatedKey) {
		this.generatedKey = generatedKey;
	}

	public Set<Statement> getAttachedStatements() {
		return attachedStatements;
	}

	public void setAttachedStatements(Set<Statement> attachedStatements) {
		this.attachedStatements = attachedStatements;
	}

	public Map<String, Connection> getActualConnections() {
		return actualConnections;
	}

	public void setActualConnections(Map<String, Connection> actualConnections) {
		this.actualConnections = actualConnections;
	}

	public DataSourceRouter getRouter() {
		return router;
	}

	public void setRouter(DataSourceRouter router) {
		this.router = router;
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

		List<Throwable> innerExceptions = new ArrayList<Throwable>();

		try {
			if (generatedKey != null) {
				try {
					generatedKey.close();
				} catch (Exception e) {
					innerExceptions.add(e);
					log.error(e);
				}
			}

			for (Statement stmt : attachedStatements) {
				try {
					stmt.close();
				} catch (Exception e) {
					innerExceptions.add(e);
					log.error(e);
				}
			}

			String errorMsgPrefix = "DS : ";

			for (Map.Entry<String, Connection> entry : actualConnections.entrySet()) {
				try {
					entry.getValue().close();
				} catch (Exception e) {
					innerExceptions.add(e);
					log.error(errorMsgPrefix + entry.getKey(), e);
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

		List<Throwable> innerExceptions = new ArrayList<Throwable>();
		String errorMsgPrefix = "DS : ";

		for (Map.Entry<String, Connection> entry : actualConnections.entrySet()) {
			try {
				entry.getValue().commit();
			} catch (Exception e) {
				innerExceptions.add(e);
				log.error(errorMsgPrefix + entry.getKey(), e);
			}
		}

		JDBCUtils.throwSQLExceptionIfNeeded(innerExceptions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createArrayOf(java.lang.String,
	 * java.lang.Object[])
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

		DPStatement stmt = new DPStatement();
		stmt.setRouter(router);
		stmt.setAutoCommit(autoCommit);
		stmt.setReadOnly(readOnly);
		stmt.setConnectionWrapper(this);
		stmt.setPerformanceMonitorSwitchOn(performanceMonitorSwitchOn);

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
		DPStatement stmt = (DPStatement) createStatement();

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
		DPStatement stmt = (DPStatement) createStatement(resultSetType, resultSetConcurrency);

		stmt.setResultSetHoldability(resultSetHoldability);

		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createStruct(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport createStruct");
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
		return new DPDatabaseMetaData();
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

		DPPreparedStatement stmt = new DPPreparedStatement();
		stmt.setRouter(router);
		stmt.setAutoCommit(autoCommit);
		stmt.setReadOnly(readOnly);
		stmt.setConnectionWrapper(this);
		stmt.setSql(sql);
		stmt.setPerformanceMonitorSwitchOn(performanceMonitorSwitchOn);

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
		DPPreparedStatement stmt = (DPPreparedStatement) prepareStatement(sql);
		stmt.setAutoGeneratedKeys(autoGeneratedKeys);
		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int[])
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		DPPreparedStatement stmt = (DPPreparedStatement) prepareStatement(sql);
		stmt.setColumnIndexes(columnIndexes);
		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String,
	 * java.lang.String[])
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		DPPreparedStatement stmt = (DPPreparedStatement) prepareStatement(sql);
		stmt.setColumnNames(columnNames);
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
		DPPreparedStatement stmt = (DPPreparedStatement) prepareStatement(sql);
		stmt.setResultSetType(resultSetType);
		stmt.setResultSetConcurrency(resultSetConcurrency);
		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int,
	 * int)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		DPPreparedStatement stmt = (DPPreparedStatement) prepareStatement(sql, resultSetType, resultSetConcurrency);
		stmt.setResultSetHoldability(resultSetHoldability);
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

		List<Throwable> exceptions = new ArrayList<Throwable>();
		String errorMsgPrefix = "DS : ";

		for (Map.Entry<String, Connection> entry : actualConnections.entrySet()) {
			try {
				entry.getValue().rollback();
			} catch (Exception e) {

				exceptions.add(e);

				log.error(errorMsgPrefix + entry.getKey(), e);
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
	 * @see java.sql.Connection#setClientInfo(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		throw new UnsupportedOperationException("Zebra unsupport setClientInfo");
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
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport isWrapperFor");
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
