package com.dianping.zebra.group.filter;

import com.dianping.zebra.group.datasources.FailOverDataSource;
import com.dianping.zebra.group.filter.delegate.FilterAction;
import com.dianping.zebra.group.filter.delegate.FilterActionWithSQLExcption;
import com.dianping.zebra.group.filter.delegate.FilterFunction;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;
import com.dianping.zebra.group.jdbc.GroupConnection;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Dozer on 9/2/14.
 */
public class DefaultJdbcFilter implements JdbcFilter {
	@Override public <S> void closeGroupConnection(JdbcMetaData metaData, S source,
			FilterActionWithSQLExcption<S> action)
			throws SQLException {
		action.execute(source);
	}

	@Override public <S> void closeSingleDataSource(JdbcMetaData metaData, S source,
			FilterActionWithSQLExcption<S> action)
			throws SQLException {
		action.execute(source);
	}

	@Override public <S, T> T execute(JdbcMetaData metaData, S source, FilterFunctionWithSQLException<S, T> action)
			throws SQLException {
		return action.execute(source);
	}

	@Override public <S> FailOverDataSource.FindMasterDataSourceResult findMasterFailOverDataSource(
			JdbcMetaData metaData, S source,
			FilterFunction<S, FailOverDataSource.FindMasterDataSourceResult> action) {
		return action.execute(source);
	}

	@Override public <S> GroupConnection getGroupConnection(JdbcMetaData metaData, S source,
			FilterFunctionWithSQLException<S, GroupConnection> action) throws SQLException {
		return action.execute(source);
	}

	@Override public int getOrder() {
		return this.DEFAULT_ORDER;
	}

	@Override public <S> void initGroupDataSource(JdbcMetaData metaData, S source, FilterAction<S> action) {
		action.execute(source);
	}

	@Override public <S> DataSource initSingleDataSource(JdbcMetaData metaData, S source,
			FilterFunction<S, DataSource> action) {
		return action.execute(source);
	}

	@Override public <S> void refreshGroupDataSource(JdbcMetaData metaData, String propertiesName, S source,
			FilterAction<S> action) {
		action.execute(source);
	}

	@Override public <S> String sql(JdbcMetaData metaData, S source, FilterFunction<S, String> action) {
		return action.execute(source);
	}

	@Override public <S> void switchFailOverDataSource(JdbcMetaData metaData, S source, FilterAction<S> action) {
		action.execute(source);
	}
}
