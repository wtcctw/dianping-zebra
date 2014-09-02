package com.dianping.zebra.group.filter;

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

	@Override public void get_connection(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.get_connection(metaData);
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
