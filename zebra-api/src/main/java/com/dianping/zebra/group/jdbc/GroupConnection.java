package com.dianping.zebra.group.jdbc;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.filter.JdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;
import com.dianping.zebra.group.router.CustomizedReadWriteStrategy;
import com.dianping.zebra.group.router.RouterType;
import com.dianping.zebra.group.util.JDBCExceptionUtils;
import com.dianping.zebra.group.util.SqlType;
import com.dianping.zebra.group.util.SqlUtils;
import com.dianping.zebra.group.util.StringUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;

public class GroupConnection implements Connection {

	private boolean autoCommit = true;

	private boolean closed = false;

	private CustomizedReadWriteStrategy customizedReadWriteStrategy;

	private JdbcFilter filter;

	private JdbcMetaData metaData;

	private Set<Statement> openedStatements = new HashSet<Statement>();

	private Connection rConnection;

	private DataSource readDataSource;

	private RouterType routerType;

	private int transactionIsolation = -1;

	private Connection wConnection;

	private DataSource writeDataSource;

	public GroupConnection(DataSource readDataSource, DataSource writeDataSource,
			CustomizedReadWriteStrategy customizedReadWriteStrategy, RouterType routerType,
			JdbcMetaData metaData, JdbcFilter filter) {
		super();
		this.readDataSource = readDataSource;
		this.writeDataSource = writeDataSource;
		this.customizedReadWriteStrategy = customizedReadWriteStrategy;
		this.filter = filter;
		this.metaData = metaData;
		this.routerType = routerType;
	}

	public void abort(Executor executor) throws SQLException {
		throw new UnsupportedOperationException("abort");
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
		checkClosed();
		if (rConnection != null) {
			rConnection.clearWarnings();
		}
		if (wConnection != null) {
			wConnection.clearWarnings();
		}
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
		closed = true;

		JdbcMetaData tempMetaData = this.metaData.clone();

		List<SQLException> exceptions = new LinkedList<SQLException>();
		try {
			this.filter.closeGroupConnectionBefore(tempMetaData);

			for (Statement stmt : openedStatements) {
				try {
					stmt.close();
				} catch (SQLException e) {
					exceptions.add(e);
				}
			}

			try {
				if (rConnection != null && !rConnection.isClosed()) {
					rConnection.close();
				}
			} catch (SQLException e) {
				exceptions.add(e);
			}
			try {
				if (wConnection != null && !wConnection.isClosed()) {
					wConnection.close();
				}
			} catch (SQLException e) {
				exceptions.add(e);
			}
		} finally {
			openedStatements.clear();
			rConnection = null;
			wConnection = null;
		}

		try {
			JDBCExceptionUtils.throwSQLExceptionIfNeeded(exceptions);
			this.filter.closeGroupConnectionSuccess(tempMetaData);
		} catch (SQLException exp) {
			this.filter.closeGroupConnectionError(tempMetaData, exp);
		} finally {
			this.filter.closeGroupConnectionAfter(tempMetaData);
		}
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

		if (wConnection != null) {
			wConnection.commit();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createArrayOf(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		throw new UnsupportedOperationException("createArrayOf");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createBlob()
	 */
	@Override
	public Blob createBlob() throws SQLException {
		throw new UnsupportedOperationException("createBlob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createClob()
	 */
	@Override
	public Clob createClob() throws SQLException {
		throw new UnsupportedOperationException("createClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createNClob()
	 */
	@Override
	public NClob createNClob() throws SQLException {
		throw new UnsupportedOperationException("createNClob");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createSQLXML()
	 */
	@Override
	public SQLXML createSQLXML() throws SQLException {
		throw new UnsupportedOperationException("createSQLXML");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createStatement()
	 */
	@Override
	public Statement createStatement() throws SQLException {
		checkClosed();
		Statement stmt = new GroupStatement(this, this.metaData.clone(), this.filter);
		openedStatements.add(stmt);
		return stmt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createStatement(int, int)
	 */
	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		GroupStatement stmt = (GroupStatement) createStatement();
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
		GroupStatement stmt = (GroupStatement) createStatement(resultSetType, resultSetConcurrency);
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
		throw new UnsupportedOperationException("createStruct");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getAutoCommit()
	 */
	@Override
	public boolean getAutoCommit() throws SQLException {
		checkClosed();
		return this.autoCommit;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#setAutoCommit(boolean)
	 */
	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		checkClosed();
		if (this.autoCommit == autoCommit) {
			return;
		}
		this.autoCommit = autoCommit;
		if (this.wConnection != null) {
			this.wConnection.setAutoCommit(autoCommit);
		}

	}

	private CallableStatement getCallableStatement(Connection conn, String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		if (resultSetType == Integer.MIN_VALUE) {
			return conn.prepareCall(sql);
		} else if (resultSetHoldability == Integer.MIN_VALUE) {
			return conn.prepareCall(sql, resultSetType, resultSetConcurrency);
		} else {
			return conn.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#getCatalog()
	 */
	@Override
	public String getCatalog() throws SQLException {
		throw new UnsupportedOperationException("getCatalog");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#setCatalog(java.lang.String)
	 */
	@Override
	public void setCatalog(String catalog) throws SQLException {
		throw new UnsupportedOperationException("setCatalog");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#getClientInfo()
	 */
	@Override
	public Properties getClientInfo() throws SQLException {
		throw new UnsupportedOperationException("getClientInfo");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#setClientInfo(java.util.Properties)
	 */
	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		throw new UnsupportedOperationException("setClientInfo");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#getClientInfo(java.lang.String)
	 */
	@Override
	public String getClientInfo(String name) throws SQLException {
		throw new UnsupportedOperationException("getClientInfo");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#getHoldability()
	 */
	@Override
	public int getHoldability() throws SQLException {
		return ResultSet.CLOSE_CURSORS_AT_COMMIT;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#setHoldability(int)
	 */
	@Override
	public void setHoldability(int holdability) throws SQLException {
		throw new UnsupportedOperationException("setHoldability");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#getMetaData()
	 */
	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		checkClosed();
		if (rConnection != null) {
			return rConnection.getMetaData();
		} else {
			return this.getWriteConnection().getMetaData();
		}
	}

	public int getNetworkTimeout() throws SQLException {
		throw new UnsupportedOperationException("getNetworkTimeout");
	}

	private Connection getReadConnection() throws SQLException {
		if (rConnection == null) {
			rConnection = readDataSource.getConnection();
		}

		return rConnection;
	}

	Connection getRealConnection(String sql, boolean forceWriter) throws SQLException {
		if (forceWriter) {
			return getWriteConnection();
		} else if (!autoCommit || StringUtils.trimToEmpty(sql).startsWith(Constants.SQL_FORCE_WRITE_HINT)) {
			return getWriteConnection();
		} else if (customizedReadWriteStrategy != null && customizedReadWriteStrategy.forceReadFromMaster()) {
			return getWriteConnection();
		}

		if (this.routerType == RouterType.LOAD_BALANCE) {
			return getReadConnection();
		} else if (this.routerType == RouterType.FAIL_OVER) {
			return getWriteConnection();
		} else {
			SqlType sqlType = SqlUtils.getSqlType(sql);
			if (sqlType.isRead()) {
				return getReadConnection();
			} else {
				return getWriteConnection();
			}
		}
	}

	public String getSchema() throws SQLException {
		throw new UnsupportedOperationException("getSchema");
	}

	public void setSchema(String schema) throws SQLException {
		throw new UnsupportedOperationException("setSchema");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#getTransactionIsolation()
	 */
	@Override
	public int getTransactionIsolation() throws SQLException {
		return this.transactionIsolation;
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
	 * @see java.sql.Connection#getTypeMap()
	 */
	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		throw new UnsupportedOperationException("getTypeMap");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#setTypeMap(java.util.Map)
	 */
	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		throw new UnsupportedOperationException("setTypeMap");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#getWarnings()
	 */
	@Override
	public SQLWarning getWarnings() throws SQLException {
		checkClosed();
		if (rConnection != null) {
			return rConnection.getWarnings();
		} else if (wConnection != null) {
			return wConnection.getWarnings();
		} else {
			return null;
		}
	}

	private Connection getWriteConnection() throws SQLException {
		if (wConnection == null) {
			wConnection = writeDataSource.getConnection();

			if (wConnection.getAutoCommit() != autoCommit) {
				wConnection.setAutoCommit(autoCommit);
			}
		}

		return wConnection;
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
		throw new UnsupportedOperationException("isReadOnly");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#setReadOnly(boolean)
	 */
	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#isValid(int)
	 */
	@Override
	public boolean isValid(int timeout) throws SQLException {
		throw new UnsupportedOperationException("isValid");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.getClass().isAssignableFrom(iface);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#nativeSQL(java.lang.String)
	 */
	@Override
	public String nativeSQL(String sql) throws SQLException {
		throw new UnsupportedOperationException("nativeSQL");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#prepareCall(java.lang.String)
	 */
	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return prepareCall(sql, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int)
	 */
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return prepareCall(sql, resultSetType, resultSetConcurrency, Integer.MIN_VALUE);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int, int)
	 */
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		checkClosed();
		CallableStatement cstmt = null;
		// 存储过程强制走写库
		Connection conn = getRealConnection(sql, true);
		cstmt = getCallableStatement(conn, sql, resultSetType, resultSetConcurrency, resultSetHoldability);

		openedStatements.add(cstmt);
		return cstmt;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#prepareStatement(java.lang.String)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		checkClosed();
		PreparedStatement pstmt = new GroupPreparedStatement(this, sql, this.metaData.clone(), this.filter);
		openedStatements.add(pstmt);
		return pstmt;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		GroupPreparedStatement pstmt = (GroupPreparedStatement) prepareStatement(sql);
		pstmt.setAutoGeneratedKeys(autoGeneratedKeys);
		return pstmt;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
			throws SQLException {
		GroupPreparedStatement pstmt = (GroupPreparedStatement) prepareStatement(sql);
		pstmt.setResultSetType(resultSetType);
		pstmt.setResultSetConcurrency(resultSetConcurrency);
		return pstmt;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int, int)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		GroupPreparedStatement pstmt = (GroupPreparedStatement) prepareStatement(sql, resultSetType,
				resultSetConcurrency);
		pstmt.setResultSetHoldability(resultSetHoldability);
		return pstmt;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int[])
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		GroupPreparedStatement pstmt = (GroupPreparedStatement) prepareStatement(sql);
		pstmt.setColumnIndexes(columnIndexes);
		return pstmt;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#prepareStatement(java.lang.String, java.lang.String[])
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		GroupPreparedStatement pstmt = (GroupPreparedStatement) prepareStatement(sql);
		pstmt.setColumnNames(columnNames);
		return pstmt;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#releaseSavepoint(java.sql.Savepoint)
	 */
	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		throw new UnsupportedOperationException("releaseSavepoint");
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

		if (wConnection != null) {
			wConnection.rollback();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#rollback(java.sql.Savepoint)
	 */
	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		throw new UnsupportedOperationException("rollback with savepoint");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#setClientInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		throw new UnsupportedOperationException("setClientInfo");
	}

	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		throw new UnsupportedOperationException("setNetworkTimeout");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#setSavepoint()
	 */
	@Override
	public Savepoint setSavepoint() throws SQLException {
		throw new UnsupportedOperationException("setSavepoint");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.sql.Connection#setSavepoint(java.lang.String)
	 */
	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		throw new UnsupportedOperationException("setSavepoint");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		try {
			return (T) this;
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}
}
