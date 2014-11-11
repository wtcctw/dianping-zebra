package com.dianping.zebra.group.filter.wall;

import com.dianping.zebra.group.datasources.FailOverDataSource;
import com.dianping.zebra.group.datasources.SingleConnection;
import com.dianping.zebra.group.filter.JdbcContext;
import com.dianping.zebra.group.filter.JdbcFilter;
import com.dianping.zebra.group.filter.delegate.FilterAction;
import com.dianping.zebra.group.filter.delegate.FilterActionWithSQLExcption;
import com.dianping.zebra.group.filter.delegate.FilterFunction;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;
import com.dianping.zebra.group.jdbc.GroupConnection;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
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

	@Override public void init() {
		throw new NotImplementedException();
	}

	@Override public <S> void closeGroupConnection(S source, JdbcFilter chain) throws SQLException {
		throw new NotImplementedException();
	}

	@Override public <S> void closeGroupConnection(JdbcContext context, S source, FilterActionWithSQLExcption<S> action)
		  throws SQLException {
		throw new NotImplementedException();

	}

	@Override public void closeGroupDataSource(GroupDataSource source, JdbcFilter chain) throws SQLException {
		throw new NotImplementedException();
	}

	@Override public <S> void closeSingleConnection(JdbcContext context, S source,
		  FilterActionWithSQLExcption<S> action) throws SQLException {
		throw new NotImplementedException();

	}

	@Override public <S> void closeSingleDataSource(JdbcContext context, S source,
		  FilterActionWithSQLExcption<S> action) throws SQLException {
		throw new NotImplementedException();

	}

	@Override public <S, T> T execute(JdbcContext context, S source, FilterFunctionWithSQLException<S, T> action)
		  throws SQLException {
		throw new NotImplementedException();

	}

	@Override public <S> FailOverDataSource.FindMasterDataSourceResult findMasterFailOverDataSource(JdbcContext context,
		  S source, FilterFunction<S, FailOverDataSource.FindMasterDataSourceResult> action) {
		throw new NotImplementedException();

	}

	@Override public <S> GroupConnection getGroupConnection(JdbcContext context, S source,
		  FilterFunctionWithSQLException<S, GroupConnection> action) throws SQLException {
		throw new NotImplementedException();

	}

	@Override public int getOrder() {
		throw new NotImplementedException();

	}

	@Override public <S> SingleConnection getSingleConnection(JdbcContext context, S source,
		  FilterFunctionWithSQLException<S, SingleConnection> action) throws SQLException {
		throw new NotImplementedException();

	}

	@Override public void initGroupDataSource(GroupDataSource source, JdbcFilter chain) {
		throw new NotImplementedException();
	}

	@Override public <S> DataSource initSingleDataSource(JdbcContext context, S source,
		  FilterFunction<S, DataSource> action) {
		throw new NotImplementedException();

	}

	@Override public <S> void refreshGroupDataSource(JdbcContext context, String propertiesName, S source,
		  FilterAction<S> action) {
		throw new NotImplementedException();

	}

	@Override public <S> Boolean resultSetNext(JdbcContext context, S source,
		  FilterFunctionWithSQLException<S, Boolean> action) throws SQLException {
		throw new NotImplementedException();
	}

	@Override public <S> String sql(JdbcContext context, S source, FilterFunctionWithSQLException<S, String> action)
		  throws SQLException {
		throw new NotImplementedException();
	}

	@Override public <S> void switchFailOverDataSource(JdbcContext context, S source, FilterAction<S> action) {
		throw new NotImplementedException();
	}
}
