package com.dianping.zebra.group.filter;

import com.dianping.zebra.group.datasources.FailOverDataSource;
import com.dianping.zebra.group.datasources.SingleConnection;
import com.dianping.zebra.group.filter.delegate.FilterAction;
import com.dianping.zebra.group.filter.delegate.FilterActionWithSQLExcption;
import com.dianping.zebra.group.filter.delegate.FilterFunction;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;
import com.dianping.zebra.group.jdbc.GroupConnection;
import com.dianping.zebra.group.jdbc.GroupDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Dozer on 9/2/14.
 */
public class DefaultJdbcFilter implements JdbcFilter {
	@Override
	public <S> void closeGroupConnection(JdbcContext metaData, S source, FilterActionWithSQLExcption<S> action)
		  throws SQLException {
		action.execute(source);
	}

	@Override public void closeGroupDataSource(GroupDataSource source, JdbcFilter chain) throws SQLException {
		chain.closeGroupDataSource(source, chain);
	}

	@Override
	public <S> void closeSingleConnection(JdbcContext metaData, S source, FilterActionWithSQLExcption<S> action)
		  throws SQLException {
		action.execute(source);
	}

	@Override
	public <S> void closeSingleDataSource(JdbcContext metaData, S source, FilterActionWithSQLExcption<S> action)
		  throws SQLException {
		action.execute(source);
	}

	@Override
	public <S, T> T execute(JdbcContext metaData, S source, FilterFunctionWithSQLException<S, T> action)
		  throws SQLException {
		return action.execute(source);
	}

	@Override
	public <S> FailOverDataSource.FindMasterDataSourceResult findMasterFailOverDataSource(JdbcContext metaData,
		  S source, FilterFunction<S, FailOverDataSource.FindMasterDataSourceResult> action) {
		return action.execute(source);
	}

	@Override
	public <S> GroupConnection getGroupConnection(JdbcContext metaData, S source,
		  FilterFunctionWithSQLException<S, GroupConnection> action) throws SQLException {
		return action.execute(source);
	}

	@Override
	public int getOrder() {
		return DEFAULT_ORDER;
	}

	@Override
	public <S> SingleConnection getSingleConnection(JdbcContext metaData, S source,
		  FilterFunctionWithSQLException<S, SingleConnection> action) throws SQLException {
		return action.execute(source);
	}

	@Override
	public void init() {

	}

	@Override
	public <S> void closeGroupConnection(S source, JdbcFilter chain) throws SQLException {
		chain.closeGroupConnection(source, chain);
	}

	@Override
	public void initGroupDataSource(GroupDataSource source, JdbcFilter chain) {
		chain.initGroupDataSource(source, chain);
	}

	@Override
	public <S> DataSource initSingleDataSource(JdbcContext metaData, S source, FilterFunction<S, DataSource> action) {
		return action.execute(source);
	}

	@Override
	public <S> void refreshGroupDataSource(JdbcContext metaData, String propertiesName, S source,
		  FilterAction<S> action) {
		action.execute(source);
	}

	@Override
	public <S> Boolean resultSetNext(JdbcContext metaData, S source, FilterFunctionWithSQLException<S, Boolean> action)
		  throws SQLException {
		return action.execute(source);
	}

	@Override
	public <S> String sql(JdbcContext metaData, S source, FilterFunctionWithSQLException<S, String> action)
		  throws SQLException {
		return action.execute(source);
	}

	@Override
	public <S> void switchFailOverDataSource(JdbcContext metaData, S source, FilterAction<S> action) {
		action.execute(source);
	}
}
