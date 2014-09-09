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

	@Override public void closeSingleDataSourceAfter(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.closeSingleDataSourceAfter(metaData);
			}
		}.execute();
	}

	@Override public void closeSingleDataSourceBefore(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.closeSingleDataSourceBefore(metaData);
			}
		}.execute();
	}

	@Override public void closeSingleDataSourceError(final JdbcMetaData metaData, final Exception exp) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.closeSingleDataSourceError(metaData, exp);
			}
		}.execute();
	}

	@Override public void closeSingleDataSourceSuccess(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.closeSingleDataSourceSuccess(metaData);
			}
		}.execute();
	}

	@Override public void executeAfter(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.executeAfter(metaData);
			}
		}.execute();
	}

	@Override public void executeBefore(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.executeBefore(metaData);
			}
		}.execute();
	}

	@Override public void executeError(final JdbcMetaData metaData, final Exception exp) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.executeError(metaData, exp);
			}
		}.execute();
	}

	@Override public void executeSuccess(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.executeSuccess(metaData);
			}
		}.execute();
	}

	@Override public void findMasterFailOverDataSourceAfter(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.findMasterFailOverDataSourceAfter(metaData);
			}
		}.execute();
	}

	@Override public void findMasterFailOverDataSourceBefore(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.findMasterFailOverDataSourceBefore(metaData);
			}
		}.execute();
	}

	@Override public void findMasterFailOverDataSourceError(final JdbcMetaData metaData, final Exception exp) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.findMasterFailOverDataSourceError(metaData, exp);
			}
		}.execute();
	}

	@Override public void findMasterFailOverDataSourceSuccess(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.findMasterFailOverDataSourceSuccess(metaData);
			}
		}.execute();
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

	@Override public void initGroupDataSourceError(final JdbcMetaData metaData, final Exception exp) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.initGroupDataSourceError(metaData, exp);
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

	@Override public void initSingleDataSourceAfter(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.initSingleDataSourceAfter(metaData);
			}
		}.execute();
	}

	@Override public void initSingleDataSourceBefore(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.initSingleDataSourceBefore(metaData);
			}
		}.execute();
	}

	@Override public void initSingleDataSourceError(final JdbcMetaData metaData, final Exception exp) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.initSingleDataSourceError(metaData, exp);
			}
		}.execute();
	}

	@Override public void initSingleDataSourceSuccess(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.initSingleDataSourceSuccess(metaData);
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

	@Override public void switchFailOverDataSourceAfter(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.switchFailOverDataSourceAfter(metaData);
			}
		}.execute();
	}

	@Override public void switchFailOverDataSourceBefore(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.switchFailOverDataSourceBefore(metaData);
			}
		}.execute();
	}

	@Override public void switchFailOverDataSourceError(final JdbcMetaData metaData, final Exception exp) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.switchFailOverDataSourceError(metaData, exp);
			}
		}.execute();
	}

	@Override public void switchFailOverDataSourceSuccess(final JdbcMetaData metaData) {
		new JdbcFilterExecuter() {
			@Override protected void execute(JdbcFilter filter) {
				filter.switchFailOverDataSourceSuccess(metaData);
			}
		}.execute();
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
