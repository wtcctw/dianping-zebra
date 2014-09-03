package com.dianping.zebra.group.datasources;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.jdbc.AbstractDataSource;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;
import com.dianping.zebra.group.router.DataSourceRouter;
import com.dianping.zebra.group.router.RouterTarget;
import com.dianping.zebra.group.router.RouterContext;
import com.dianping.zebra.group.router.WeightDataSourceRouter;
import com.dianping.zebra.group.util.JDBCExceptionUtils;

public class LoadBalancedDataSource extends AbstractDataSource {

	private int retryTimes;

	private Map<String, DataSourceConfig> loadBalancedConfigMap;

	private DataSourceRouter router;

	private Map<String, InnerSingleDataSource> dataSources;

	private SingleDataSourceManager dataSourceManager;

	public LoadBalancedDataSource(Map<String, DataSourceConfig> loadBalancedConfigMap, int retryTimes) {
		this.dataSources = new HashMap<String, InnerSingleDataSource>();
		this.loadBalancedConfigMap = loadBalancedConfigMap;
		this.retryTimes = retryTimes;
	}

	public void close() throws SQLException {
		for (InnerSingleDataSource ds : dataSources.values()) {
			dataSourceManager.destoryDataSource(ds);
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getConnection(null, null);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		RouterContext context = new RouterContext();

		for (InnerSingleDataSource dataSource : this.dataSources.values()) {
			if (dataSource.isDown() || dataSource.isClosed()) {
				context.addExcludeTarget(dataSource.getId());
			}
		}

		RouterTarget target = this.router.select(context);

		if (target != null) {
			int tmpRetryTimes = -1;
			Set<RouterTarget> excludeTargets = new HashSet<RouterTarget>();
			List<SQLException> exceptions = new ArrayList<SQLException>();

			while (tmpRetryTimes++ < this.retryTimes) {
				try {
					return this.dataSources.get(target.getId()).getConnection();
				} catch (SQLException e) {
					exceptions.add(e);
					excludeTargets.add(target);
					context = new RouterContext(excludeTargets);
					target = router.select(context);
					if (target == null) {
						break;
					}
				}
			}

			if (!exceptions.isEmpty()) {
				JDBCExceptionUtils.throwSQLExceptionIfNeeded(exceptions);
			}
		} else {
			throw new SQLException("No available dataSource");
		}

		throw new SQLException("Can not aquire connection");
	}

	public Map<String, SingleDataSourceMBean> getCurrentDataSourceMBean() {
		Map<String, SingleDataSourceMBean> beans = new HashMap<String, SingleDataSourceMBean>();
		beans.putAll(dataSources);

		return beans;
	}

	public void init() {
		this.dataSourceManager = SingleDataSourceManagerFactory.getDataSourceManager();

		for (DataSourceConfig config : loadBalancedConfigMap.values()) {
			InnerSingleDataSource dataSource = dataSourceManager.createDataSource(config);
			this.dataSources.put(config.getId(), dataSource);
		}

		this.router = new WeightDataSourceRouter(loadBalancedConfigMap);
	}
}
