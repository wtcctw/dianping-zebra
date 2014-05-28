package com.dianping.zebra.group.datasources;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dianping.zebra.group.config.SystemConfigManager;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.jdbc.AbstractDataSource;
import com.dianping.zebra.group.router.DataSourceRouter;
import com.dianping.zebra.group.router.RounterTarget;
import com.dianping.zebra.group.router.RouterContext;
import com.dianping.zebra.group.router.WeightDataSourceRouter;
import com.dianping.zebra.group.util.JDBCExceptionUtils;

public class LoadBalancedDataSource extends AbstractDataSource {

	private DataSourceRouter router;

	private SystemConfigManager systemConfigManager;

	private Map<String, SingleDataSource> dataSources;

	private SingleDataSourceManager singleDataSourceManager;

	public LoadBalancedDataSource(String name, Map<String, DataSourceConfig> configs,
	      SystemConfigManager systemConfigManager, SingleDataSourceManager manager) {
		this.name = name;
		this.systemConfigManager = systemConfigManager;
		this.singleDataSourceManager = manager;
		this.dataSources = new HashMap<String, SingleDataSource>();
		for (DataSourceConfig config : configs.values()) {
			this.dataSources.put(config.getId(), singleDataSourceManager.createDataSource(name, config));
		}
		this.router = new WeightDataSourceRouter(configs);
		this.singleDataSourceManager = manager;
	}

	@Override
	public void close() throws SQLException {
//		for (SingleDataSource dataSource : dataSources.values()) {
//			singleDataSourceManager.destoryDataSource(dataSource.getId(), name);
//		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getConnection(null, null);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		RouterContext context = new RouterContext();

		for (SingleDataSource dataSource : this.dataSources.values()) {
			if (dataSource.isDown() || dataSource.isClosed()) {
				context.addExcludeTarget(dataSource.getId());
			}
		}

		RounterTarget target = this.router.select(context);

		if (target != null) {
			Connection conn = null;

			int retryTimes = -1;
			Set<RounterTarget> excludeTargets = new HashSet<RounterTarget>();
			List<SQLException> exceptions = new ArrayList<SQLException>();
			int systemRetryTimes = systemConfigManager.getSystemConfig().getRetryTimes();

			while (retryTimes++ < systemRetryTimes) {
				try {
					conn = this.dataSources.get(target.getId()).getConnection();
					return conn;
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
}
