package com.dianping.zebra.group.filter;

import com.dianping.zebra.group.datasources.FailOverDataSource;
import com.dianping.zebra.group.datasources.SingleConnection;
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
public interface JdbcFilter {
	public static final int DEFAULT_ORDER = 0;

	public static final int MAX_ORDER = Integer.MAX_VALUE;

	public static final int MIN_ORDER = Integer.MIN_VALUE;

	<S> void closeGroupConnection(JdbcMetaData metaData, S source, FilterActionWithSQLExcption<S> action)
			throws SQLException;

	<S> void closeGroupDataSource(JdbcMetaData metaData, S source, FilterActionWithSQLExcption<S> action)
			throws SQLException;

	<S> void closeSingleConnection(JdbcMetaData metaData, S source, FilterActionWithSQLExcption<S> action)
			throws SQLException;

	<S> void closeSingleDataSource(JdbcMetaData metaData, S source, FilterActionWithSQLExcption<S> action)
			throws SQLException;

	<S, T> T execute(JdbcMetaData metaData, S source, FilterFunctionWithSQLException<S, T> action) throws SQLException;

	<S> FailOverDataSource.FindMasterDataSourceResult findMasterFailOverDataSource(JdbcMetaData metaData, S source,
			FilterFunction<S, FailOverDataSource.FindMasterDataSourceResult> action);

	<S> GroupConnection getGroupConnection(JdbcMetaData metaData, S source,
			FilterFunctionWithSQLException<S, GroupConnection> action)
			throws SQLException;

	/**
	 * filter_with_order_3_start
	 * filter_with_order_2_start
	 * filter_with_order_1_start
	 * targer_start
	 * filter_with_order_1_finish
	 * filter_with_order_2_finish
	 * filter_with_order_3_finish
	 *
	 * @return the order of execute
	 */
	int getOrder();

	<S> SingleConnection getSingleConnection(JdbcMetaData metaData, S source,
			FilterFunctionWithSQLException<S, SingleConnection> action)
			throws SQLException;

	<S> void initGroupDataSource(JdbcMetaData metaData, S source, FilterAction<S> action);

	<S> DataSource initSingleDataSource(JdbcMetaData metaData, S source,
			FilterFunction<S, DataSource> action);

	<S> void refreshGroupDataSource(JdbcMetaData metaData, String propertiesName, S source, FilterAction<S> action);

	<S> Boolean resultSetNext(JdbcMetaData metaData, S source, FilterFunctionWithSQLException<S, Boolean> action)
			throws SQLException;

	<S> String sql(JdbcMetaData metaData, S source, FilterFunction<S, String> action);

	<S> void switchFailOverDataSource(JdbcMetaData metaData, S source, FilterAction<S> action);
}
