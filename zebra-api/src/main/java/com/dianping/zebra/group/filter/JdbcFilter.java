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
	int DEFAULT_ORDER = 0;

	int MAX_ORDER = Integer.MAX_VALUE;

	int MIN_ORDER = Integer.MIN_VALUE;

	void init();

	<S> void closeGroupConnection(JdbcContext context, S source, FilterActionWithSQLExcption<S> action)
		throws SQLException;

	<S> void closeGroupDataSource(JdbcContext context, S source, FilterActionWithSQLExcption<S> action)
		throws SQLException;

	<S> void closeSingleConnection(JdbcContext context, S source, FilterActionWithSQLExcption<S> action)
		throws SQLException;

	<S> void closeSingleDataSource(JdbcContext context, S source, FilterActionWithSQLExcption<S> action)
		throws SQLException;

	<S, T> T execute(JdbcContext context, S source, FilterFunctionWithSQLException<S, T> action) throws SQLException;

	<S> FailOverDataSource.FindMasterDataSourceResult findMasterFailOverDataSource(JdbcContext context, S source,
		FilterFunction<S, FailOverDataSource.FindMasterDataSourceResult> action);

	<S> GroupConnection getGroupConnection(JdbcContext context, S source,
		FilterFunctionWithSQLException<S, GroupConnection> action) throws SQLException;

	/**
	 * filter_with_order_3_start filter_with_order_2_start filter_with_order_1_start targer_start filter_with_order_1_finish
	 * filter_with_order_2_finish filter_with_order_3_finish
	 *
	 * @return the order of execute
	 */
	int getOrder();

	<S> SingleConnection getSingleConnection(JdbcContext context, S source,
		FilterFunctionWithSQLException<S, SingleConnection> action) throws SQLException;

	<S> void initGroupDataSource(JdbcContext context, S source, FilterAction<S> action);

	<S> DataSource initSingleDataSource(JdbcContext context, S source, FilterFunction<S, DataSource> action);

	<S> void refreshGroupDataSource(JdbcContext context, String propertiesName, S source, FilterAction<S> action);

	<S> Boolean resultSetNext(JdbcContext context, S source, FilterFunctionWithSQLException<S, Boolean> action)
		throws SQLException;

	<S> String sql(JdbcContext context, S source, FilterFunctionWithSQLException<S, String> action) throws SQLException;

	<S> void switchFailOverDataSource(JdbcContext context, S source, FilterAction<S> action);
}
