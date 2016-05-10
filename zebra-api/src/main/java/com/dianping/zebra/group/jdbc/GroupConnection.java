package com.dianping.zebra.group.jdbc;

import com.dianping.zebra.Constants;
import com.dianping.zebra.filter.DefaultJdbcFilterChain;
import com.dianping.zebra.filter.JdbcFilter;
import com.dianping.zebra.group.router.ReadWriteStrategy;
import com.dianping.zebra.group.router.RouterType;
import com.dianping.zebra.util.JDBCUtils;
import com.dianping.zebra.util.SqlType;
import com.dianping.zebra.util.SqlUtils;
import com.dianping.zebra.util.StringUtils;

import javax.sql.DataSource;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;

public class GroupConnection implements Connection {

	private DataSource readDataSource;

	private DataSource writeDataSource;

	private ReadWriteStrategy readWriteStrategy;

	private RouterType routerType;

	private List<JdbcFilter> filters;

	private volatile Connection rConnection;

	private volatile Connection wConnection;

	private List<Statement> openedStatements = new ArrayList<Statement>();
	
	private int transactionIsolation = -1;

	private boolean autoCommit = true;
	
	private boolean closed = false;
	
	public GroupConnection(DataSource readDataSource, DataSource writeDataSource, ReadWriteStrategy readWriteStrategy,
			RouterType routerType, List<JdbcFilter> filters) {
		super();
		this.readDataSource = readDataSource;
		this.writeDataSource = writeDataSource;
		this.readWriteStrategy = readWriteStrategy;
		this.filters = filters;
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

	private void closeOrigin() throws SQLException {
		if (closed) {
			return;
		}
		closed = true;

		final List<SQLException> exceptions = new LinkedList<SQLException>();

		try {
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

		JDBCUtils.throwSQLExceptionIfNeeded(exceptions);
	}

	@Override
	public void close() throws SQLException {
		if (filters != null && filters.size() > 0) {
			JdbcFilter chain = new DefaultJdbcFilterChain(filters) {
				@Override
				public void closeGroupConnection(GroupConnection source, JdbcFilter chain) throws SQLException {
					if (index < filters.size()) {
						filters.get(index++).closeGroupConnection(source, chain);
					} else {
						source.closeOrigin();
					}
				}
			};
			chain.closeGroupConnection(this, chain);
		} else {
			closeOrigin();
		}
	}

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

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		throw new UnsupportedOperationException("createArrayOf");
	}

	@Override
	public Blob createBlob() throws SQLException {
		throw new UnsupportedOperationException("createBlob");
	}

	@Override
	public Clob createClob() throws SQLException {
		throw new UnsupportedOperationException("createClob");
	}

	@Override
	public NClob createNClob() throws SQLException {
		throw new UnsupportedOperationException("createNClob");
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		throw new UnsupportedOperationException("createSQLXML");
	}

	@Override
	public Statement createStatement() throws SQLException {
		checkClosed();
		Statement stmt = new GroupStatement(this, this.filters);
		openedStatements.add(stmt);
		return stmt;
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		GroupStatement stmt = (GroupStatement) createStatement();
		stmt.setResultSetType(resultSetType);
		stmt.setResultSetConcurrency(resultSetConcurrency);
		return stmt;
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		GroupStatement stmt = (GroupStatement) createStatement(resultSetType, resultSetConcurrency);
		stmt.setResultSetHoldability(resultSetHoldability);
		return stmt;
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		throw new UnsupportedOperationException("createStruct");
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		checkClosed();
		return this.autoCommit;
	}

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

	@Override
	public String getCatalog() throws SQLException {
		throw new UnsupportedOperationException("getCatalog");
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		throw new UnsupportedOperationException("setCatalog");
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		throw new UnsupportedOperationException("getClientInfo");
	}

	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		throw new UnsupportedOperationException("setClientInfo");
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		throw new UnsupportedOperationException("getClientInfo");
	}

	@Override
	public int getHoldability() throws SQLException {
		return ResultSet.CLOSE_CURSORS_AT_COMMIT;
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		throw new UnsupportedOperationException("setHoldability");
	}

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
			synchronized (this) {
				if(rConnection == null){
					rConnection = readDataSource.getConnection();
				}
			}
		}

		return rConnection;
	}

	Connection getRealConnection(String sql, boolean forceWriter) throws SQLException {
		if (forceWriter) {
			return getWriteConnection();
		} else if (!autoCommit || StringUtils.trimToEmpty(sql).contains(Constants.SQL_FORCE_WRITE_HINT)) {
			return getWriteConnection();
		} else if (readWriteStrategy != null && readWriteStrategy.shouldReadFromMaster()) {
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

	@Override
	public int getTransactionIsolation() throws SQLException {
		return this.transactionIsolation;
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		checkClosed();
		this.transactionIsolation = level;
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		throw new UnsupportedOperationException("getTypeMap");
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		throw new UnsupportedOperationException("setTypeMap");
	}

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
			synchronized (this) {
				if(wConnection == null){
					wConnection = writeDataSource.getConnection();
					
					if (wConnection.getAutoCommit() != autoCommit) {
						wConnection.setAutoCommit(autoCommit);
					}
				}
			}
		}

		return wConnection;
	}

	@Override
	public boolean isClosed() throws SQLException {
		return closed;
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		if (routerType != null && routerType == RouterType.LOAD_BALANCE) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		// do nothing
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		throw new UnsupportedOperationException("isValid");
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.getClass().isAssignableFrom(iface);
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		throw new UnsupportedOperationException("nativeSQL");
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return prepareCall(sql, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return prepareCall(sql, resultSetType, resultSetConcurrency, Integer.MIN_VALUE);
	}

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

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		checkClosed();
		PreparedStatement pstmt = new GroupPreparedStatement(this, sql, this.filters);
		openedStatements.add(pstmt);
		return pstmt;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		GroupPreparedStatement pstmt = (GroupPreparedStatement) prepareStatement(sql);
		pstmt.setAutoGeneratedKeys(autoGeneratedKeys);
		return pstmt;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
			throws SQLException {
		GroupPreparedStatement pstmt = (GroupPreparedStatement) prepareStatement(sql);
		pstmt.setResultSetType(resultSetType);
		pstmt.setResultSetConcurrency(resultSetConcurrency);
		return pstmt;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		GroupPreparedStatement pstmt = (GroupPreparedStatement) prepareStatement(sql, resultSetType,
				resultSetConcurrency);
		pstmt.setResultSetHoldability(resultSetHoldability);
		return pstmt;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		GroupPreparedStatement pstmt = (GroupPreparedStatement) prepareStatement(sql);
		pstmt.setColumnIndexes(columnIndexes);
		return pstmt;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		GroupPreparedStatement pstmt = (GroupPreparedStatement) prepareStatement(sql);
		pstmt.setColumnNames(columnNames);
		return pstmt;
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		throw new UnsupportedOperationException("releaseSavepoint");
	}

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

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		throw new UnsupportedOperationException("rollback with savepoint");
	}

	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		throw new UnsupportedOperationException("setClientInfo");
	}

	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		throw new UnsupportedOperationException("setNetworkTimeout");
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		throw new UnsupportedOperationException("setSavepoint");
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		throw new UnsupportedOperationException("setSavepoint");
	}

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
