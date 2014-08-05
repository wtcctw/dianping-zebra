package com.dianping.zebra.group.datasources;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.dianping.zebra.group.util.JdbcDriverClassHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.cat.Cat;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.DalException;
import com.dianping.zebra.group.exception.IllegalConfigException;
import com.dianping.zebra.group.jdbc.AbstractDataSource;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;
import com.mchange.v2.c3p0.DataSources;
import com.mchange.v2.c3p0.PoolBackedDataSource;

public class InnerSingleDataSource extends AbstractDataSource implements MarkableDataSource, SingleDataSourceMBean {

	private static final Logger logger = LoggerFactory.getLogger(InnerSingleDataSource.class);

	private volatile DataSourceState state = DataSourceState.INITIAL;

	private String dsId;

	private DataSourceConfig config;

	private DataSource dataSource;

	private CountPunisher punisher;

	public InnerSingleDataSource(DataSourceConfig config) {
		this.dsId = config.getId();
		this.config = config;
		this.punisher = new CountPunisher(this, config.getTimeWindow(), config.getPunishLimit());
		this.dataSource = initDataSource(config);
	}

	private void checkState() throws SQLException {
		if (state == DataSourceState.CLOSED || state == DataSourceState.DOWN) {
			throw new SQLException(String.format("dataSource is not avaiable, current state is [%s]", state));
		}
	}

	@Override
	public void close() throws SQLException {
		if (dataSource != null && (dataSource instanceof PoolBackedDataSource)) {
			if (this.state == DataSourceState.UP) {
				PoolBackedDataSource poolBackedDataSource = (PoolBackedDataSource) dataSource;
				if (poolBackedDataSource.getNumBusyConnections() == 0) {
					logger.info("closing old datasource [" + this.dsId + "]");

					poolBackedDataSource.close();

					Cat.logEvent("DataSource.Destoryed", this.dsId);
					logger.info("old datasource [" + this.dsId + "] closed");
					this.state = DataSourceState.CLOSED;
				} else {
					throw new DalException(String.format("Cannot close dataSource[%s] since there are busy connections.",
							dsId));
				}
			} else {
				this.state = DataSourceState.CLOSED;
			}
		} else {
			// Normally not happen
			logger.warn(
					"fail to close dataSource since dataSource is null or dataSource is not an instance of PoolBackedDataSource.");
		}
	}

	public synchronized DataSourceConfig getConfig() {
		return this.config;
	}

	@Override
	public Connection getConnection() throws SQLException {
		checkState();
		return getConnection(null, null);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		checkState();
		Connection conn;
		try {
			conn = this.dataSource.getConnection();
		} catch (SQLException e) {
			punisher.countAndPunish(e);

			throw e;
		}

		if (state == DataSourceState.INITIAL) {
			state = DataSourceState.UP;
		}

		return new SingleConnection(this, conn);
	}

	@Override
	public String getCurrentState() {
		return state.toString();
	}

	public String getId() {
		return this.dsId;
	}

	@Override
	public int getNumBusyConnection() {
		if (dataSource != null && (dataSource instanceof PoolBackedDataSource)) {
			try {
				return ((PoolBackedDataSource) dataSource).getNumBusyConnections();
			} catch (SQLException e) {
			}
		}
		return 0;
	}

	@Override
	public int getNumConnections() {
		if (dataSource != null && (dataSource instanceof PoolBackedDataSource)) {
			try {
				return ((PoolBackedDataSource) dataSource).getNumConnections();
			} catch (SQLException e) {
			}
		}
		return 0;
	}

	@Override
	public long getNumFailedCheckins() {
		if (dataSource != null && (dataSource instanceof PoolBackedDataSource)) {
			try {
				return ((PoolBackedDataSource) dataSource).getNumFailedCheckinsDefaultUser();
			} catch (SQLException e) {
			}
		}
		return 0;
	}

	@Override
	public long getNumFailedCheckouts() {
		if (dataSource != null && (dataSource instanceof PoolBackedDataSource)) {
			try {
				return ((PoolBackedDataSource) dataSource).getNumFailedCheckoutsDefaultUser();
			} catch (SQLException e) {
			}
		}
		return 0;
	}

	@Override
	public int getNumIdleConnection() {
		if (dataSource != null && (dataSource instanceof PoolBackedDataSource)) {
			try {
				return ((PoolBackedDataSource) dataSource).getNumIdleConnections();
			} catch (SQLException e) {
			}
		}
		return 0;
	}

	@Override
	public int getNumUnClosedOrphanedConnections() {
		if (dataSource != null && (dataSource instanceof PoolBackedDataSource)) {
			try {
				return ((PoolBackedDataSource) dataSource).getNumUnclosedOrphanedConnections();
			} catch (SQLException e) {
			}
		}
		return 0;
	}

	public CountPunisher getPunisher() {
		return this.punisher;
	}

	@Override
	public DataSourceState getState() {
		return this.state;
	}

	@Override
	public int getThreadPoolNumActiveThreads() {
		if (dataSource != null && (dataSource instanceof PoolBackedDataSource)) {
			try {
				return ((PoolBackedDataSource) dataSource).getThreadPoolNumActiveThreads();
			} catch (SQLException e) {
			}
		}
		return 0;
	}

	@Override
	public int getThreadPoolNumIdleThreads() {
		if (dataSource != null && (dataSource instanceof PoolBackedDataSource)) {
			try {
				return ((PoolBackedDataSource) dataSource).getThreadPoolNumIdleThreads();
			} catch (SQLException e) {
			}
		}
		return 0;
	}

	@Override
	public int getThreadPoolNumTasksPending() {
		if (dataSource != null && (dataSource instanceof PoolBackedDataSource)) {
			try {
				return ((PoolBackedDataSource) dataSource).getThreadPoolNumTasksPending();
			} catch (SQLException e) {
			}
		}
		return 0;
	}

	@Override
	public int getThreadPoolSize() {
		if (dataSource != null && (dataSource instanceof PoolBackedDataSource)) {
			try {
				return ((PoolBackedDataSource) dataSource).getThreadPoolSize();
			} catch (SQLException e) {
			}
		}
		return 0;
	}

	private DataSource initDataSource(DataSourceConfig value) {
		JdbcDriverClassHelper.loadDriverClass(config.getDriverClass(), config.getJdbcUrl());

		try {
			DataSource unPooledDataSource = DataSources.unpooledDataSource(value.getJdbcUrl(), value.getUsername(),
					value.getPassword());

			Map<String, Object> props = new HashMap<String, Object>();

			props.put("driverClass", value.getDriverClass());

			for (Any any : value.getProperties()) {
				props.put(any.getName(), any.getValue());
			}

			PoolBackedDataSource pooledDataSource = (PoolBackedDataSource) DataSources.pooledDataSource(
					unPooledDataSource, props);

			Cat.logEvent("DataSource.Created", value.getId());
			logger.info(String.format("New dataSource [%s] created.", value.getId()));

			return pooledDataSource;
		} catch (Exception e) {
			throw new IllegalConfigException(e);
		}
	}

	public boolean isAvailable() {
		return this.state == DataSourceState.INITIAL || this.state == DataSourceState.UP;
	}

	public boolean isClosed() {
		return this.state == DataSourceState.CLOSED;
	}

	public boolean isDown() {
		return this.state == DataSourceState.DOWN;
	}

	@Override
	public void markClosed() {
		this.state = DataSourceState.CLOSED;
	}

	@Override
	public void markDown() {
		this.state = DataSourceState.DOWN;
	}

	@Override
	public void markUp() {
		this.state = DataSourceState.INITIAL;
	}
}
