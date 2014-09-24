package com.dianping.zebra.group.filter;

import com.dianping.zebra.group.filter.delegate.FilterAction;
import com.dianping.zebra.group.filter.delegate.FilterActionWithSQLExcption;
import com.dianping.zebra.group.filter.delegate.FilterFunction;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;

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

	@Override public <S, T> T findMasterFailOverDataSource(JdbcMetaData metaData, S source,
			FilterFunction<S, T> action) {
		return action.execute(source);
	}

	@Override public <S, T> T getGroupConnection(JdbcMetaData metaData, S source,
			FilterFunctionWithSQLException<S, T> action) throws SQLException {
		return action.execute(source);
	}

	@Override public int getOrder() {
		return this.DEFAULT_ORDER;
	}

	@Override public <S> void initGroupDataSource(JdbcMetaData metaData, S source, FilterAction<S> action) {
		action.execute(source);
	}

	@Override public <S, T> T initSingleDataSource(JdbcMetaData metaData, S source, FilterFunction<S, T> action) {
		return action.execute(source);
	}

	@Override public <S> void refreshGroupDataSource(JdbcMetaData metaData, String propertiesName, S source,
			FilterAction<S> action) {
		action.execute(source);
	}

	@Override public <S> void switchFailOverDataSource(JdbcMetaData metaData, S source, FilterAction<S> action) {
		action.execute(source);
	}
}
