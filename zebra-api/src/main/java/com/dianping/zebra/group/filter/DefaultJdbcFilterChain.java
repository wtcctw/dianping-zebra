package com.dianping.zebra.group.filter;

import com.dianping.zebra.group.datasources.FailOverDataSource;
import com.dianping.zebra.group.datasources.SingleConnection;
import com.dianping.zebra.group.datasources.SingleDataSource;
import com.dianping.zebra.group.jdbc.GroupConnection;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.jdbc.GroupResultSet;
import com.dianping.zebra.group.jdbc.GroupStatement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dozer on 11/11/14.
 */
public class DefaultJdbcFilterChain implements JdbcFilter {
	protected final List<JdbcFilter> filters;

	protected int index = 0;

	public DefaultJdbcFilterChain(List<JdbcFilter> filters) {
		this.filters = filters;
	}

	@Override
	public void closeGroupConnection(GroupConnection source, JdbcFilter chain) throws SQLException {
	}

	@Override
	public void closeGroupDataSource(GroupDataSource source, JdbcFilter chain) throws SQLException {
	}

	@Override
	public void closeSingleConnection(SingleConnection source, JdbcFilter chain) throws SQLException {
	}

	@Override
	public void closeSingleDataSource(SingleDataSource source, JdbcFilter chain) throws SQLException {
	}

	@Override
	public <T> T execute(GroupStatement source, Connection conn, String sql, List<String> batchedSql, boolean isBatched,
	      boolean autoCommit, Object params, JdbcFilter chain) throws SQLException {
		return null;
	}

	@Override
	public FailOverDataSource.FindMasterDataSourceResult findMasterFailOverDataSource(
	      FailOverDataSource.MasterDataSourceMonitor source, JdbcFilter chain) {
		return null;
	}

	@Override
	public GroupConnection getGroupConnection(GroupDataSource source, JdbcFilter chain) throws SQLException {
		return null;
	}

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public SingleConnection getSingleConnection(SingleDataSource source, JdbcFilter chain) throws SQLException {
		return null;
	}

	@Override
	public void init() {
	}

	@Override
	public void initGroupDataSource(GroupDataSource source, JdbcFilter chain) {
	}

	@Override
	public DataSource initSingleDataSource(SingleDataSource source, JdbcFilter chain) {
		return null;
	}

	@Override
	public void refreshGroupDataSource(GroupDataSource source, String propertiesName, JdbcFilter chain) {
	}

	@Override
	public boolean resultSetNext(GroupResultSet source, JdbcFilter chain) throws SQLException {
		return false;
	}

	@Override
	public String sql(SingleConnection conn, String sql, boolean isPreparedStmt, JdbcFilter chain) throws SQLException {
		return null;
	}

	@Override
	public void switchFailOverDataSource(FailOverDataSource source, JdbcFilter chain) {
	}
}
