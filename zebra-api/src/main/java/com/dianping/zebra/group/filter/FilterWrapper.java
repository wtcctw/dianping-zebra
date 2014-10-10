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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Dozer on 9/2/14.
 */
public class FilterWrapper implements JdbcFilter {
	private final List<JdbcFilter> filters;

	public FilterWrapper() {
		this.filters = new ArrayList<JdbcFilter>();
	}

	public FilterWrapper(List<JdbcFilter> filters) {
		if (filters == null) {
			this.filters = new ArrayList<JdbcFilter>();
		} else {
			Collections.sort(filters, new Comparator<JdbcFilter>() {
				@Override
				public int compare(JdbcFilter o1, JdbcFilter o2) {
					int x = o1.getOrder();
					int y = o2.getOrder();
					return (x < y) ? -1 : ((x == y) ? 0 : 1);
				}
			});
			
			this.filters = filters;
		}
	}

	@Override
	public <S> void closeGroupConnection(final JdbcContext context, final S source,
	      final FilterActionWithSQLExcption<S> action) throws SQLException {
		FilterActionWithSQLExcption<S> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterActionWithSQLExcption<S> finalTodo = todo;
			todo = new FilterActionWithSQLExcption<S>() {
				@Override
				public void execute(S source) throws SQLException {
					filter.closeGroupConnection(context, source, finalTodo);
				}
			};
		}
		todo.execute(source);
	}

	@Override
	public <S> void closeGroupDataSource(final JdbcContext context, final S source,
	      final FilterActionWithSQLExcption<S> action) throws SQLException {
		FilterActionWithSQLExcption<S> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterActionWithSQLExcption<S> finalTodo = todo;
			todo = new FilterActionWithSQLExcption<S>() {
				@Override
				public void execute(S source) throws SQLException {
					filter.closeGroupDataSource(context, source, finalTodo);
				}
			};
		}
		todo.execute(source);
	}

	@Override
	public <S> void closeSingleConnection(final JdbcContext context, final S source,
	      final FilterActionWithSQLExcption<S> action) throws SQLException {
		FilterActionWithSQLExcption<S> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterActionWithSQLExcption<S> finalTodo = todo;
			todo = new FilterActionWithSQLExcption<S>() {
				@Override
				public void execute(S source) throws SQLException {
					filter.closeSingleConnection(context, source, finalTodo);
				}
			};
		}
		todo.execute(source);
	}

	@Override
	public <S> void closeSingleDataSource(final JdbcContext context, final S source,
	      final FilterActionWithSQLExcption<S> action) throws SQLException {
		FilterActionWithSQLExcption<S> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterActionWithSQLExcption<S> finalTodo = todo;
			todo = new FilterActionWithSQLExcption<S>() {
				@Override
				public void execute(S source) throws SQLException {
					filter.closeSingleDataSource(context, source, finalTodo);
				}
			};
		}
		todo.execute(source);
	}

	@Override
	public <S, T> T execute(final JdbcContext context, final S source, final FilterFunctionWithSQLException<S, T> action)
	      throws SQLException {

		FilterFunctionWithSQLException<S, T> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterFunctionWithSQLException<S, T> finalTodo = todo;
			todo = new FilterFunctionWithSQLException<S, T>() {
				@Override
				public T execute(S source) throws SQLException {
					return filter.execute(context, source, finalTodo);
				}
			};
		}
		return todo.execute(source);
	}

	@Override
	public <S> FailOverDataSource.FindMasterDataSourceResult findMasterFailOverDataSource(final JdbcContext context,
	      S source, FilterFunction<S, FailOverDataSource.FindMasterDataSourceResult> action) {
		FilterFunction<S, FailOverDataSource.FindMasterDataSourceResult> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterFunction<S, FailOverDataSource.FindMasterDataSourceResult> finalTodo = todo;
			todo = new FilterFunction<S, FailOverDataSource.FindMasterDataSourceResult>() {
				@Override
				public FailOverDataSource.FindMasterDataSourceResult execute(S source) {
					return filter.findMasterFailOverDataSource(context, source, finalTodo);
				}
			};
		}
		return todo.execute(source);
	}

	@Override
	public <S> GroupConnection getGroupConnection(final JdbcContext context, S source,
	      FilterFunctionWithSQLException<S, GroupConnection> action) throws SQLException {
		FilterFunctionWithSQLException<S, GroupConnection> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterFunctionWithSQLException<S, GroupConnection> finalTodo = todo;
			todo = new FilterFunctionWithSQLException<S, GroupConnection>() {
				@Override
				public GroupConnection execute(S source) throws SQLException {
					return filter.getGroupConnection(context, source, finalTodo);
				}
			};
		}
		return todo.execute(source);
	}

	@Override
	public int getOrder() {
		return DEFAULT_ORDER;
	}

	@Override
	public <S> SingleConnection getSingleConnection(final JdbcContext context, S source,
	      FilterFunctionWithSQLException<S, SingleConnection> action) throws SQLException {
		FilterFunctionWithSQLException<S, SingleConnection> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterFunctionWithSQLException<S, SingleConnection> finalTodo = todo;
			todo = new FilterFunctionWithSQLException<S, SingleConnection>() {
				@Override
				public SingleConnection execute(S source) throws SQLException {
					return filter.getSingleConnection(context, source, finalTodo);
				}
			};
		}
		return todo.execute(source);
	}

	@Override
	public <S> void initGroupDataSource(final JdbcContext context, final S source, final FilterAction<S> action) {

		FilterAction<S> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterAction<S> finalTodo = todo;
			todo = new FilterAction<S>() {
				@Override
				public void execute(S source) {
					filter.initGroupDataSource(context, source, finalTodo);
				}
			};
		}
		todo.execute(source);
	}

	@Override
	public <S> DataSource initSingleDataSource(final JdbcContext context, S source, FilterFunction<S, DataSource> action) {
		FilterFunction<S, DataSource> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterFunction<S, DataSource> finalTodo = todo;
			todo = new FilterFunction<S, DataSource>() {
				@Override
				public DataSource execute(S source) {
					return filter.initSingleDataSource(context, source, finalTodo);
				}
			};
		}
		return todo.execute(source);
	}

	@Override
	public <S> void refreshGroupDataSource(final JdbcContext metaData, final String propertiesName, S source,
	      FilterAction<S> action) {

		FilterAction<S> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterAction<S> finalTodo = todo;
			todo = new FilterAction<S>() {
				@Override
				public void execute(S source) {
					filter.refreshGroupDataSource(metaData, propertiesName, source, finalTodo);
				}
			};
		}
		todo.execute(source);
	}

	@Override
	public <S> Boolean resultSetNext(final JdbcContext context, S source,
	      FilterFunctionWithSQLException<S, Boolean> action) throws SQLException {
		FilterFunctionWithSQLException<S, Boolean> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterFunctionWithSQLException<S, Boolean> finalTodo = todo;
			todo = new FilterFunctionWithSQLException<S, Boolean>() {
				@Override
				public Boolean execute(S source) throws SQLException {
					return filter.resultSetNext(context, source, finalTodo);
				}
			};
		}
		return todo.execute(source);
	}

	public int size() {
		return filters.size();
	}

	@Override
	public <S> String sql(final JdbcContext context, S source, FilterFunction<S, String> action) {
		FilterFunction<S, String> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterFunction<S, String> finalTodo = todo;
			todo = new FilterFunction<S, String>() {
				@Override
				public String execute(S source) {
					return filter.sql(context, source, finalTodo);
				}
			};
		}
		return todo.execute(source);
	}

	@Override
	public <S> void switchFailOverDataSource(final JdbcContext context, S source, FilterAction<S> action) {
		FilterAction<S> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterAction<S> finalTodo = todo;
			todo = new FilterAction<S>() {
				@Override
				public void execute(S source) {
					filter.switchFailOverDataSource(context, source, finalTodo);
				}
			};
		}
		todo.execute(source);
	}
}
