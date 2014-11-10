package com.dianping.zebra.group.datasources;

import com.dianping.zebra.group.filter.JdbcFilter;
import com.dianping.zebra.group.filter.JdbcContext;
import com.dianping.zebra.group.filter.delegate.FilterActionWithSQLExcption;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class SingleConnection implements Connection {

	private Connection conn;

	private SingleDataSource dataSource;

	private JdbcFilter filter;

	private JdbcContext context;

	public SingleConnection(SingleDataSource dataSource, Connection conn, JdbcContext context, JdbcFilter filter) {
		this.dataSource = dataSource;
		this.conn = conn;
		this.filter = filter;
		this.context = context;
		this.context.setConnection(this);
	}

	public void abort(Executor executor) throws SQLException {
		throw new UnsupportedOperationException("abort");
	}

	@Override
	public void clearWarnings() throws SQLException {
		conn.clearWarnings();
	}

	@Override
	public void close() throws SQLException {
		this.filter.closeSingleConnection(this.context.clone(), this,
				new FilterActionWithSQLExcption<SingleConnection>() {
					@Override public void execute(SingleConnection source) throws SQLException {
						conn.close();
					}
				});
	}

	@Override
	public void commit() throws SQLException {
		conn.commit();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		return conn.createArrayOf(typeName, elements);
	}

	@Override
	public Blob createBlob() throws SQLException {
		return conn.createBlob();
	}

	@Override
	public Clob createClob() throws SQLException {
		return conn.createClob();
	}

	@Override
	public NClob createNClob() throws SQLException {
		return conn.createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return conn.createSQLXML();
	}

	@Override
	public Statement createStatement() throws SQLException {
		return conn.createStatement();
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return conn.createStatement(resultSetType, resultSetConcurrency);
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return conn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		return conn.createStruct(typeName, attributes);
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return conn.getAutoCommit();
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		conn.setAutoCommit(autoCommit);
	}

	@Override
	public String getCatalog() throws SQLException {
		return conn.getCatalog();
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		conn.setCatalog(catalog);
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return conn.getClientInfo(name);
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return conn.getClientInfo();
	}

	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		conn.setClientInfo(properties);
	}

	public SingleDataSource getDataSource() {
		return dataSource;
	}

	@Override
	public int getHoldability() throws SQLException {
		return conn.getHoldability();
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		conn.setHoldability(holdability);
	}

	public JdbcContext getJdbcContext() {
		return this.context;
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return conn.getMetaData();
	}

	public int getNetworkTimeout() throws SQLException {
		throw new UnsupportedOperationException("getNetworkTimeout");
	}

	public String getSchema() throws SQLException {
		throw new UnsupportedOperationException("getSchema");
	}

	public void setSchema(String schema) throws SQLException {
		throw new UnsupportedOperationException("setSchema");
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return conn.getTransactionIsolation();
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		conn.setTransactionIsolation(level);
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return conn.getTypeMap();
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		conn.setTypeMap(map);
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return conn.getWarnings();
	}

	@Override
	public boolean isClosed() throws SQLException {
		return conn.isClosed();
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return conn.isReadOnly();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		conn.setReadOnly(readOnly);
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return conn.isValid(timeout);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return conn.isWrapperFor(iface);
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return conn.nativeSQL(sql);
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return conn.prepareCall(sql);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return conn.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		return conn.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return conn.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		return conn.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		return conn.prepareStatement(sql, autoGeneratedKeys);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		return conn.prepareStatement(sql, columnIndexes);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		return conn.prepareStatement(sql, columnNames);
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		conn.releaseSavepoint(savepoint);
	}

	@Override
	public void rollback() throws SQLException {
		conn.rollback();
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		conn.rollback(savepoint);
	}

	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		conn.setClientInfo(name, value);
	}

	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		throw new UnsupportedOperationException("setNetworkTimeout");
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return conn.setSavepoint();
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return conn.setSavepoint(name);
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return conn.unwrap(iface);
	}
}
