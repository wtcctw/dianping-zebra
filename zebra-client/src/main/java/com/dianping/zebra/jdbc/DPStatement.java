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
package com.dianping.zebra.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import com.dianping.zebra.event.SBRSyncEvent;
import com.dianping.zebra.event.SyncEvent;
import com.dianping.zebra.event.SyncEventNotifier;
import com.dianping.zebra.jdbc.data.DataPool;
import com.dianping.zebra.jdbc.util.JDBCUtils;
import com.dianping.zebra.jdbc.util.LRUCache;
import com.dianping.zebra.jdbc.util.StringUtils;
import com.dianping.zebra.monitor.Monitor;
import com.dianping.zebra.router.DataSourceRouteException;
import com.dianping.zebra.router.DataSourceRouter;
import com.dianping.zebra.router.RouterTarget;
import com.dianping.zebra.router.TargetedSql;

/**
 * Zebra的Statement wrapper
 * 
 * @author Leo Liang
 * 
 */
public class DPStatement implements Statement {

	private static final Logger								log								= Logger.getLogger(DPStatement.class);

	private DataSourceRouter								router;
	private DPConnection									connectionWrapper;

	private boolean											closed;
	private boolean											readOnly;
	private boolean											autoCommit						= true;
	private int												resultSetType					= -1;
	private int												resultSetConcurrency			= -1;
	private int												resultSetHoldability			= -1;
	private static final String								SELECT_GENERATEDKEY_SQL_PATTERN	= "@@IDENTITY";

    private static final Map<String, JudgeSQLRetVal> judgeSqlRetValCache             = Collections
                                                                                             .synchronizedMap(new LRUCache<String, JudgeSQLRetVal>(
                                                                                                     1000));

	protected Set<ResultSet>								attachedResultSets				= new HashSet<ResultSet>();
	protected List<String>									batchedArgs;
	protected List<Statement>								actualStatements				= new ArrayList<Statement>();
	protected ResultSet										results;
	protected boolean										moreResults;
	protected int											updateCount;

	protected SyncEventNotifier								syncEventNotifier;

	private boolean											performanceMonitorSwitchOn		= false;

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

	/**
	 * @param syncEventNotifier
	 *            the syncEventNotifier to set
	 */
	public void setSyncEventNotifier(SyncEventNotifier syncEventNotifier) {
		this.syncEventNotifier = syncEventNotifier;
	}

	/**
	 * @return the attachedResultSets
	 */
	public Set<ResultSet> getAttachedResultSets() {
		return attachedResultSets;
	}

	/**
	 * @param attachedResultSets
	 *            the attachedResultSets to set
	 */
	public void setAttachedResultSets(Set<ResultSet> attachedResultSets) {
		this.attachedResultSets = attachedResultSets;
	}

	public void setResultSetType(int resultSetType) {
		this.resultSetType = resultSetType;
	}

	public void setResultSetConcurrency(int resultSetConcurrency) {
		this.resultSetConcurrency = resultSetConcurrency;
	}

	public void setResultSetHoldability(int resultSetHoldability) {
		this.resultSetHoldability = resultSetHoldability;
	}

	public DPConnection getConnectionWrapper() {
		return connectionWrapper;
	}

	public void setConnectionWrapper(DPConnection dpConnection) {
		this.connectionWrapper = dpConnection;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isAutoCommit() {
		return autoCommit;
	}

	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}

	public DataSourceRouter getRouter() {
		return router;
	}

	public void setRouter(DataSourceRouter router) {
		this.router = router;
	}

	protected void checkClosed() throws SQLException {
		if (closed) {
			throw new SQLException("No operations allowed after statement closed.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#addBatch(java.lang.String)
	 */
	@Override
	public void addBatch(String sql) throws SQLException {
		checkClosed();

		if (batchedArgs == null) {
			batchedArgs = new ArrayList<String>();
		}
		if (sql != null) {
			batchedArgs.add(sql);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#cancel()
	 */
	@Override
	public void cancel() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport cancel");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#clearBatch()
	 */
	@Override
	public void clearBatch() throws SQLException {
		checkClosed();

		if (batchedArgs != null) {
			batchedArgs.clear();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#clearWarnings()
	 */
	@Override
	public void clearWarnings() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport clearWarnings");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#close()
	 */
	@Override
	public void close() throws SQLException {

		if (closed) {
			return;
		}

		List<Throwable> exceptions = new ArrayList<Throwable>();

		try {
			for (ResultSet resultSet : attachedResultSets) {
				try {
					resultSet.close();
				} catch (Exception e) {
					exceptions.add(e);
					log.error(e);
				}
			}

			for (Statement stmt : actualStatements) {
				try {
					stmt.close();
				} catch (Exception e) {
					exceptions.add(e);
					log.error(e);
				}
			}
		} finally {
			closed = true;
			attachedResultSets.clear();
			actualStatements.clear();
			results = null;
		}

		JDBCUtils.throwSQLExceptionIfNeeded(exceptions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#execute(java.lang.String)
	 */
	@Override
	public boolean execute(String sql) throws SQLException {
		return _execute(sql, -1, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#execute(java.lang.String, int)
	 */
	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		return _execute(sql, autoGeneratedKeys, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#execute(java.lang.String, int[])
	 */
	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		return _execute(sql, -1, columnIndexes, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#execute(java.lang.String, java.lang.String[])
	 */
	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		return _execute(sql, -1, null, columnNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#executeBatch()
	 */
	@Override
	public int[] executeBatch() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport executeBatch");
	}

	protected ResultSet beforeQuery(String sql) throws SQLException {
		// 特殊处理 SELECT @@IDENTITY AS A
		// 这种SQL，因为这种SQL需要从同一个DPConnection会话中获得上次Insert语句的返回值
		if (getConnectionWrapper().getGeneratedKey() != null && sql != null
				&& sql.indexOf(SELECT_GENERATEDKEY_SQL_PATTERN) >= 0) {

			DPResultSet rs = new DPResultSet();
			rs.setStatementWrapper(this);
			DataPool dataPool = new DataPool();
			List<ResultSet> rsList = new ArrayList<ResultSet>();
			getConnectionWrapper().getGeneratedKey().beforeFirst();
			rsList.add(getConnectionWrapper().getGeneratedKey());
			dataPool.setResultSets(rsList);
			dataPool.setInMemory(false);
			rs.setDataPool(dataPool);

			attachedResultSets.add(rs);

			this.results = rs;
			this.moreResults = false;
			this.updateCount = -1;

			return this.results;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#executeQuery(java.lang.String)
	 */
	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		checkClosed();
		StopWatch watch = new StopWatch();
		watch.start();

		ResultSet specRS = beforeQuery(sql);
		if (specRS != null) {
			this.results = specRS;
			this.moreResults = false;
			this.updateCount = -1;
			attachedResultSets.add(specRS);
			return this.results;
		}

		RouterTarget routerTarget = routingAndCheck(sql, null);

		DPResultSet rs = new DPResultSet();
		rs.setStatementWrapper(this);
		rs.setRouterTarget(routerTarget);

		attachedResultSets.add(rs);

		List<Throwable> exceptions = new ArrayList<Throwable>();

		for (TargetedSql targetedSql : routerTarget.getTargetedSqls()) {
			for (String executableSql : targetedSql.getSqls()) {
				try {
					Connection conn = getConnectionWrapper().getActualConnections()
							.get(targetedSql.getDataSourceName());
					if (conn == null) {
						conn = targetedSql.getDataSource().getConnection();
						getConnectionWrapper().getActualConnections().put(targetedSql.getDataSourceName(), conn);
					}
					Statement stmt = createStatementInternal(conn);
					actualStatements.add(stmt);
					rs.getActualResultSets().add(stmt.executeQuery(executableSql));
				} catch (Exception e) {
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

		watch.stop();

		performanceWatch(watch, this.getClass(), "executeQuery", sql, null);

		return this.results;

	}

	protected void performanceWatch(StopWatch watch, Class<?> cl, String method, String sql,
			List<? extends Serializable> params) {
		if (performanceMonitorSwitchOn) {
			Map<String, Object> context = new HashMap<String, Object>(4);
			context.put("class", cl.getName());
			context.put("method", method);
			context.put("sql", sql);
			if (params != null && params.size() > 0) {
				context.put("params", params);
			}
			Monitor.notifyPerformanceEvent(watch.getTime(), context);
		}
	}

	/**
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	protected RouterTarget routingAndCheck(String sql, List<Object> params) throws SQLException {

		RouterTarget routerTarget = null;
		try {
            JudgeSQLRetVal judgeSQLRetVal = judgeSQLType(sql);

            routerTarget = router.getTarget(judgeSQLRetVal.getSqlWithoutComment(), params);

			executableCheck(routerTarget);

		} catch (DataSourceRouteException e) {
			throw new SQLException(e);
		}
		return routerTarget;
	}

	private Statement createStatementInternal(Connection connection) throws SQLException {
		Statement stmt;
		if (this.resultSetType != -1 && this.resultSetConcurrency != -1 && this.resultSetHoldability != -1) {
			stmt = connection.createStatement(this.resultSetType, this.resultSetConcurrency, this.resultSetHoldability);
		} else if (this.resultSetType != -1 && this.resultSetConcurrency != -1) {
			stmt = connection.createStatement(this.resultSetType, this.resultSetConcurrency);
		} else {
			stmt = connection.createStatement();
		}

		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#executeUpdate(java.lang.String)
	 */
	@Override
	public int executeUpdate(String sql) throws SQLException {
		return _executeUpdate(sql, -1, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#executeUpdate(java.lang.String, int)
	 */
	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		return _executeUpdate(sql, autoGeneratedKeys, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#executeUpdate(java.lang.String, int[])
	 */
	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		return _executeUpdate(sql, -1, columnIndexes, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#executeUpdate(java.lang.String,
	 * java.lang.String[])
	 */
	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		return _executeUpdate(sql, -1, null, columnNames);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getConnection()
	 */
	@Override
	public Connection getConnection() throws SQLException {
		return connectionWrapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getFetchDirection()
	 */
	@Override
	public int getFetchDirection() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getFetchDirection");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getFetchSize()
	 */
	@Override
	public int getFetchSize() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getFetchSize");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getGeneratedKeys()
	 */
	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getGeneratedKeys");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getMaxFieldSize()
	 */
	@Override
	public int getMaxFieldSize() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getMaxFieldSize");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getMaxRows()
	 */
	@Override
	public int getMaxRows() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getMaxRows");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getMoreResults()
	 */
	@Override
	public boolean getMoreResults() throws SQLException {
		return moreResults;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getMoreResults(int)
	 */
	@Override
	public boolean getMoreResults(int current) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getMoreResults");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getQueryTimeout()
	 */
	@Override
	public int getQueryTimeout() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getQueryTimeout");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getResultSet()
	 */
	@Override
	public ResultSet getResultSet() throws SQLException {
		return results;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getResultSetConcurrency()
	 */
	@Override
	public int getResultSetConcurrency() throws SQLException {
		return resultSetConcurrency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getResultSetHoldability()
	 */
	@Override
	public int getResultSetHoldability() throws SQLException {
		return resultSetHoldability;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getResultSetType()
	 */
	@Override
	public int getResultSetType() throws SQLException {
		return resultSetType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getUpdateCount()
	 */
	@Override
	public int getUpdateCount() throws SQLException {
		return updateCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#getWarnings()
	 */
	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport getWarnings");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#isClosed()
	 */
	@Override
	public boolean isClosed() throws SQLException {
		return closed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#isPoolable()
	 */
	@Override
	public boolean isPoolable() throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport isPoolable");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setCursorName(java.lang.String)
	 */
	@Override
	public void setCursorName(String name) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setCursorName");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setEscapeProcessing(boolean)
	 */
	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setEscapeProcessing");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setFetchDirection(int)
	 */
	@Override
	public void setFetchDirection(int direction) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setFetchDirection");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setFetchSize(int)
	 */
	@Override
	public void setFetchSize(int rows) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setFetchSize");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setMaxFieldSize(int)
	 */
	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setMaxFieldSize");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setMaxRows(int)
	 */
	@Override
	public void setMaxRows(int max) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setMaxRows");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setPoolable(boolean)
	 */
	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setPoolable");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Statement#setQueryTimeout(int)
	 */
	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		throw new UnsupportedOperationException("Zebra unsupport setQueryTimeout");
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

	private boolean _execute(String sql, int autoGeneratedKeys, int[] columnIndexes, String[] columnNames)
			throws SQLException {
		JudgeSQLRetVal judgeSQLRetVal = judgeSQLType(sql);
		if (judgeSQLRetVal.getSqlType() == SQLType.SELECT) {
			executeQuery(judgeSQLRetVal.getSqlWithoutComment());
			return true;
		} else if (judgeSQLRetVal.getSqlType() == SQLType.INSERT || judgeSQLRetVal.getSqlType() == SQLType.UPDATE
				|| judgeSQLRetVal.getSqlType() == SQLType.DELETE) {
			if (autoGeneratedKeys == -1 && columnIndexes == null && columnNames == null) {
				executeUpdate(judgeSQLRetVal.getSqlWithoutComment());
			} else if (autoGeneratedKeys != -1) {
				executeUpdate(judgeSQLRetVal.getSqlWithoutComment(), autoGeneratedKeys);
			} else if (columnIndexes != null) {
				executeUpdate(judgeSQLRetVal.getSqlWithoutComment(), columnIndexes);
			} else if (columnNames != null) {
				executeUpdate(judgeSQLRetVal.getSqlWithoutComment(), columnNames);
			} else {
				executeUpdate(judgeSQLRetVal.getSqlWithoutComment());
			}

			return false;
		} else {
			throw new SQLException("only select, insert, update, delete sql is supported");
		}
	}

	private int _executeUpdate(String sql, int autoGeneratedKeys, int[] columnIndexes, String[] columnNames)
			throws SQLException {
		checkClosed();
		StopWatch watch = new StopWatch();
		watch.start();

		RouterTarget routerTarget = routingAndCheck(sql, null);

		int affectedRows = 0;
		List<Throwable> exceptions = new ArrayList<Throwable>();

		boolean rewritedInsert = false;

		for (TargetedSql targetedSql : routerTarget.getTargetedSqls()) {
			for (String executableSql : targetedSql.getSqls()) {
				try {
					Connection conn = getConnectionWrapper().getActualConnections()
							.get(targetedSql.getDataSourceName());
					if (conn == null) {
						conn = targetedSql.getDataSource().getConnection();
						getConnectionWrapper().getActualConnections().put(targetedSql.getDataSourceName(), conn);
					}
					Statement stmt = createStatementInternal(conn);
					actualStatements.add(stmt);

					if (autoGeneratedKeys == -1 && columnIndexes == null && columnNames == null) {
						affectedRows += stmt.executeUpdate(executableSql, Statement.RETURN_GENERATED_KEYS);
					} else if (autoGeneratedKeys != -1) {
						affectedRows += stmt.executeUpdate(executableSql, autoGeneratedKeys);
					} else if (columnIndexes != null) {
						affectedRows += stmt.executeUpdate(executableSql, columnIndexes);
					} else if (columnNames != null) {
						affectedRows += stmt.executeUpdate(executableSql, columnNames);
					} else {
						affectedRows += stmt.executeUpdate(executableSql, Statement.RETURN_GENERATED_KEYS);
					}

					// 把insert语句返回的生成key保存在当前会话中
					if (executableSql.trim().charAt(0) == 'i' || executableSql.trim().charAt(0) == 'I') {
						getAndSetGeneratedKeys(stmt);
					}

					// 因为insert一定可以解析到某一个库上的某一个表上，所以只需要做一次，而且如果有自增键需要返回，必定能在这一次里获得
					if (!rewritedInsert
							&& (executableSql.trim().charAt(0) == 'i' || executableSql.trim().charAt(0) == 'I')) {
						rewriteInsertIfNeed(routerTarget);
						rewritedInsert = true;
					}

				} catch (Exception e) {
					exceptions.add(e);

					log.error(e);
				}
			}
		}

		this.results = null;
		this.moreResults = false;
		this.updateCount = affectedRows;

		JDBCUtils.throwSQLExceptionIfNeeded(exceptions);

		notifySlave(routerTarget);

		watch.stop();
		performanceWatch(watch, this.getClass(), "executeUpdate", sql, null);

		return affectedRows;
	}

	protected void getAndSetGeneratedKeys(Statement stmt) throws Exception {
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			getConnectionWrapper().setGeneratedKey(rs);
		} else {
			getConnectionWrapper().setGeneratedKey(null);
		}
	}

	protected void rewriteInsertIfNeed(RouterTarget routerTarget) throws SQLException {
		if (syncEventNotifier == null) {
			return;
		}

		if (routerTarget.getSubTargetedSqls() != null && routerTarget.getSubTargetedSqls().size() != 0) {
			String pkName = routerTarget.getGeneratedPK();
			Long pkValue = null;
			if (pkName != null && pkName.trim().length() != 0) {
				if (getConnectionWrapper().getGeneratedKey() != null) {
					pkValue = (Long) getConnectionWrapper().getGeneratedKey().getObject(1);
					getConnectionWrapper().getGeneratedKey().beforeFirst();

					for (TargetedSql subTargetedSql : routerTarget.getSubTargetedSqls()) {
						List<String> newSqlList = new ArrayList<String>(subTargetedSql.getSqls().size());
						for (String sql : subTargetedSql.getSqls()) {
							// Insert语句需要判断是否需要增加自增键
							if (sql != null && (sql.trim().charAt(0) == 'I' || sql.trim().charAt(0) == 'i')
									&& pkValue != null) {
								String newSql = rewriteInsertSql(pkName, pkValue, sql);
								newSqlList.add(newSql);
							} else {
								newSqlList.add(sql);
							}
						}
						subTargetedSql.setSqls(newSqlList);

					}
				}
			}
		}
	}

	/**
	 * @param routerTarget
	 */
	protected void notifySlave(RouterTarget routerTarget) throws SQLException {
		if (syncEventNotifier == null) {
			return;
		}

		if (routerTarget.getSubTargetedSqls() != null && routerTarget.getSubTargetedSqls().size() != 0) {

			SBRSyncEvent sbrSyncEvent = new SBRSyncEvent();
			for (TargetedSql subTargetedSql : routerTarget.getSubTargetedSqls()) {
				sbrSyncEvent.addIdSqlsPair(subTargetedSql.getDataSourceName(), subTargetedSql.getSqls());
			}

			beforeNotifySlave(routerTarget, sbrSyncEvent);

			try {
				syncEventNotifier.notifyEvent(sbrSyncEvent);
			} catch (Exception e) {
				Monitor.notifyErrorEvent("Notify modify event failed.", e, null);
			}
		}
	}

	protected void beforeNotifySlave(RouterTarget routerTarget, SyncEvent event) {

	}

	/**
	 * @param pkName
	 * @param pkValue
	 * @param sql
	 * @return
	 */
	private String rewriteInsertSql(String pkName, Long pkValue, String sql) {
		StringBuilder newSql = new StringBuilder();
		boolean isValue = false;
		boolean finished = false;
		for (char ch : sql.toCharArray()) {
			if (ch != '(' || finished) {
				newSql.append(ch);
			} else {
				if (!isValue) {
					newSql.append(ch).append(pkName).append(",");
					isValue = true;
				} else {
					newSql.append(ch).append(pkValue).append(",");
					finished = true;
				}
			}
		}
		return newSql.toString();
	}

	protected JudgeSQLRetVal judgeSQLType(String sql) throws SQLException {
		JudgeSQLRetVal judgeSQLRetVal = judgeSqlRetValCache.get(sql);
		SQLType sqlType = null;
		String sqlWithoutComment = null;

		if (judgeSQLRetVal == null) {
            sqlWithoutComment = StringUtils.stripComments(sql, "'\"", "'\"", true, false, true, true).trim();
			sqlWithoutComment = sql.trim();

			if (StringUtils.startsWithIgnoreCaseAndWs(sqlWithoutComment, "select")) {
				if (StringUtils.endsWithIgnoreCase(sqlWithoutComment, "update")) {
					throw new SQLException("select for update is unsupported");
				}

				sqlType = SQLType.SELECT;
			} else if (StringUtils.startsWithIgnoreCaseAndWs(sqlWithoutComment, "insert")) {
				sqlType = SQLType.INSERT;
			} else if (StringUtils.startsWithIgnoreCaseAndWs(sqlWithoutComment, "update")) {
				sqlType = SQLType.UPDATE;
			} else if (StringUtils.startsWithIgnoreCaseAndWs(sqlWithoutComment, "delete")) {
				sqlType = SQLType.DELETE;
			} else {
				throw new SQLException("Only select, insert, update, delete sql is supported.");
			}

			judgeSQLRetVal = new JudgeSQLRetVal(sqlType, sqlWithoutComment);
			judgeSqlRetValCache.put(sql, judgeSQLRetVal);
		}

		return judgeSQLRetVal;
	}

	protected void executableCheck(RouterTarget routerTarget) throws SQLException {
		if (routerTarget == null) {
			throw new SQLException("No router return value.");
		}
		// TODO 可以增加更多限制
	}

	protected static class JudgeSQLRetVal implements Serializable {
		private static final long	serialVersionUID	= 5876868684084751922L;
		private SQLType				sqlType;
		private String				sqlWithoutComment;

		public JudgeSQLRetVal(SQLType sqlType, String sqlWithoutComment) {
			this.sqlType = sqlType;
			this.sqlWithoutComment = sqlWithoutComment;
		}

		public SQLType getSqlType() {
			return sqlType;
		}

		public String getSqlWithoutComment() {
			return sqlWithoutComment;
		}

	}

	protected enum SQLType {
		SELECT, UPDATE, INSERT, DELETE;
	}
}
