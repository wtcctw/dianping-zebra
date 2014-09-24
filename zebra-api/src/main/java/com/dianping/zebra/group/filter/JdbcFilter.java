package com.dianping.zebra.group.filter;

import com.dianping.zebra.group.filter.delegate.FilterAction;
import com.dianping.zebra.group.filter.delegate.FilterActionWithSQLExcption;
import com.dianping.zebra.group.filter.delegate.FilterFunction;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;

import java.sql.SQLException;

/**
 * Created by Dozer on 9/2/14.
 */
public interface JdbcFilter {
	public static final int DEFAULT_ORDER = 0;

	public static final int MAX_ORDER = Integer.MAX_VALUE;

	public static final int MIN_ORDER = Integer.MIN_VALUE;

	<S> void closeGroupConnection(JdbcMetaData metaData, S source, FilterActionWithSQLExcption<S> action)
			throws SQLException;

	<S> void closeSingleDataSource(JdbcMetaData metaData, S source, FilterActionWithSQLExcption<S> action)
			throws SQLException;

	<S, T> T execute(JdbcMetaData metaData, S source, FilterFunctionWithSQLException<S, T> action) throws SQLException;

	<S,T > T findMasterFailOverDataSource(JdbcMetaData metaData, S source, FilterFunction<S,T> action);

	<S, T> T getGroupConnection(JdbcMetaData metaData, S source, FilterFunctionWithSQLException<S, T> action)
			throws SQLException;

	int getOrder();

	<S> void initGroupDataSource(JdbcMetaData metaData, S source, FilterAction<S> action);

	<S, T> T initSingleDataSource(JdbcMetaData metaData, S source, FilterFunction<S, T> action);

	<S> void refreshGroupDataSource(JdbcMetaData metaData, String propertiesName, S source, FilterAction<S> action);

	<S> void switchFailOverDataSource(JdbcMetaData metaData, S source, FilterAction<S> action);
}
