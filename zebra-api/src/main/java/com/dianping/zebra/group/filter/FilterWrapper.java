package com.dianping.zebra.group.filter;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Dozer on 9/2/14.
 */
public class FilterWrapper implements JdbcFilter {
	private final List<JdbcFilter> filters;

	public FilterWrapper() {
		filters = null;
	}

	public FilterWrapper(List<JdbcFilter> filters) {
		this.filters = filters;
	}

	@Override public void getConnectionAfter(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.getConnectionAfter(metaData);
			}
		}.execute();
	}

	@Override public void getConnectionBefore(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.getConnectionBefore(metaData);
			}
		}.execute();
	}

	@Override public void getConnectionError(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.getConnectionError(metaData);
			}
		}.execute();
	}

	@Override public void getConnectionSuccess(final JdbcMetaData metaData, final Connection connection) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.getConnectionSuccess(metaData, connection);
			}
		}.execute();
	}

	public int size() {
		return filters == null ? 0 : filters.size();
	}

	abstract class JdbcFilterExecuter {
		protected abstract void execute(JdbcFilter filter);

		public void execute() {
			if (filters == null) {
				return;
			}

			for (JdbcFilter filter : filters) {
				execute(filter);
			}
		}
	}
}
