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

	@Override public void getGroupConnectionAfter(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.getGroupConnectionAfter(metaData);
			}
		}.execute();
	}

	@Override public void getGroupConnectionBefore(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.getGroupConnectionBefore(metaData);
			}
		}.execute();
	}

	@Override public void getGroupConnectionError(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.getGroupConnectionError(metaData);
			}
		}.execute();
	}

	@Override public void getGroupConnectionSuccess(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.getGroupConnectionSuccess(metaData);
			}
		}.execute();
	}

	@Override public void initGroupDataSourceAfter(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.initGroupDataSourceAfter(metaData);
			}
		}.execute();
	}

	@Override public void initGroupDataSourceBefore(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.initGroupDataSourceBefore(metaData);
			}
		}.execute();
	}

	@Override public void initGroupDataSourceError(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.initGroupDataSourceError(metaData);
			}
		}.execute();
	}

	@Override public void initGroupDataSourceSuccess(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.initGroupDataSourceSuccess(metaData);
			}
		}.execute();
	}

	@Override public void refreshGroupDataSourceAfter(final JdbcMetaData metaData, final String propertiesName) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.refreshGroupDataSourceAfter(metaData, propertiesName);
			}
		}.execute();
	}

	@Override public void refreshGroupDataSourceBefore(final JdbcMetaData metaData, final String propertiesName) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.refreshGroupDataSourceBefore(metaData, propertiesName);
			}
		}.execute();
	}

	@Override public void refreshGroupDataSourceError(
			final JdbcMetaData metaData, final String propertiesName, final Exception exp) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.refreshGroupDataSourceError(metaData, propertiesName, exp);
			}
		}.execute();
	}

	@Override public void refreshGroupDataSourceSuccess(final JdbcMetaData metaData, final String propertiesName) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.refreshGroupDataSourceSuccess(metaData, propertiesName);
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
