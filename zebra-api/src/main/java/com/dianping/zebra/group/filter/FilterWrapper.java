package com.dianping.zebra.group.filter;

import com.dianping.zebra.group.filter.delegate.FilterAction;
import com.dianping.zebra.group.filter.delegate.FilterActionWithSQLExcption;
import com.dianping.zebra.group.filter.delegate.FilterFunction;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;

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
				@Override public int compare(JdbcFilter o1, JdbcFilter o2) {
					return Integer.compare(o1.getOrder(), o2.getOrder());
				}
			});
			this.filters = filters;
		}
	}

	@Override public <S> void closeGroupConnection(final JdbcMetaData metaData, final S source,
			final FilterActionWithSQLExcption<S> action) throws SQLException {
		FilterActionWithSQLExcption<S> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterActionWithSQLExcption<S> finalTodo = todo;
			todo = new FilterActionWithSQLExcption<S>() {
				@Override public void execute(S source) throws SQLException {
					filter.closeGroupConnection(metaData, source, finalTodo);
				}
			};
		}
		todo.execute(source);
	}

	@Override public <S> void closeSingleDataSource(final JdbcMetaData metaData, final S source,
			final FilterActionWithSQLExcption<S> action) throws SQLException {
		FilterActionWithSQLExcption<S> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterActionWithSQLExcption<S> finalTodo = todo;
			todo = new FilterActionWithSQLExcption<S>() {
				@Override public void execute(S source) throws SQLException {
					filter.closeSingleDataSource(metaData, source, finalTodo);
				}
			};
		}
		todo.execute(source);
	}

	@Override public <S, T> T execute(final JdbcMetaData metaData, final S source,
			final FilterFunctionWithSQLException<S, T> action)
			throws SQLException {

		FilterFunctionWithSQLException<S, T> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterFunctionWithSQLException<S, T> finalTodo = todo;
			todo = new FilterFunctionWithSQLException<S, T>() {
				@Override public T execute(S source) throws SQLException {
					return filter.execute(metaData, source, finalTodo);
				}
			};
		}
		return todo.execute(source);
	}

	@Override public <S, T> T findMasterFailOverDataSource(final JdbcMetaData metaData, S source,
			FilterFunction<S, T> action) {
		FilterFunction<S, T> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterFunction<S, T> finalTodo = todo;
			todo = new FilterFunction<S, T>() {
				@Override public T execute(S source) {
					return filter.findMasterFailOverDataSource(metaData, source, finalTodo);
				}
			};
		}
		return todo.execute(source);
	}

	@Override public <S, T> T getGroupConnection(final JdbcMetaData metaData, S source,
			FilterFunctionWithSQLException<S, T> action) throws SQLException {
		FilterFunctionWithSQLException<S, T> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterFunctionWithSQLException<S, T> finalTodo = todo;
			todo = new FilterFunctionWithSQLException<S, T>() {
				@Override public T execute(S source) throws SQLException {
					return filter.getGroupConnection(metaData, source, finalTodo);
				}
			};
		}
		return todo.execute(source);
	}

	@Override public int getOrder() {
		return DEFAULT_ORDER;
	}

	@Override public <S> void initGroupDataSource(final JdbcMetaData metaData, final S source,
			final FilterAction<S> action) {

		FilterAction<S> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterAction<S> finalTodo = todo;
			todo = new FilterAction<S>() {
				@Override public void execute(S source) {
					filter.initGroupDataSource(metaData, source, finalTodo);
				}
			};
		}
		todo.execute(source);
	}

	@Override public <S, T> T initSingleDataSource(final JdbcMetaData metaData, S source, FilterFunction<S, T> action) {
		FilterFunction<S, T> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterFunction<S, T> finalTodo = todo;
			todo = new FilterFunction<S, T>() {
				@Override public T execute(S source) {
					return filter.initSingleDataSource(metaData, source, finalTodo);
				}
			};
		}
		return todo.execute(source);
	}

	@Override public <S> void refreshGroupDataSource(final JdbcMetaData metaData, final String propertiesName, S source,
			FilterAction<S> action) {

		FilterAction<S> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterAction<S> finalTodo = todo;
			todo = new FilterAction<S>() {
				@Override public void execute(S source) {
					filter.refreshGroupDataSource(metaData, propertiesName, source, finalTodo);
				}
			};
		}
		todo.execute(source);
	}

	public int size() {
		return filters.size();
	}

	@Override public <S> void switchFailOverDataSource(final JdbcMetaData metaData, S source, FilterAction<S> action) {
		FilterAction<S> todo = action;
		for (final JdbcFilter filter : filters) {
			final FilterAction<S> finalTodo = todo;
			todo = new FilterAction<S>() {
				@Override public void execute(S source) {
					filter.switchFailOverDataSource(metaData, source, finalTodo);
				}
			};
		}
		todo.execute(source);
	}
}
