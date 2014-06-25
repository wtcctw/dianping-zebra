package com.dianping.zebra.group.datasources;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.IllegalConfigException;
import com.dianping.zebra.group.exception.SingleDataSourceException;
import com.dianping.zebra.group.jdbc.AbstractDataSource;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;
import com.mchange.v2.c3p0.DataSources;
import com.mchange.v2.c3p0.PoolBackedDataSource;

public class SingleDataSource extends AbstractDataSource implements MarkableDataSource, SingleDataSourceMBean {

	private static final Logger logger = LoggerFactory.getLogger(SingleDataSource.class);

	private volatile DataSourceState state = DataSourceState.INITIAL;

	private String dsId;

	private DataSourceConfig config;

	private DataSource dataSource;

	private Set<Object> references = new HashSet<Object>(); // 存储有多少业务方共享引用了这个dataSource

	private CountPunisher punisher;

	public SingleDataSource(DataSourceConfig config) {
		this.dsId = config.getId();
		this.config = config;
		this.punisher = new CountPunisher(this, config.getTimeWindow(), config.getPunishLimit());
		this.dataSource = initDataSources(config);
	}

	private void checkState() throws SQLException {
		if (state == DataSourceState.CLOSED || state == DataSourceState.DOWN) {
			throw new SQLException(String.format("dataSource is not avaiable, current state is [%s]", state));
		}
	}

	@Override
	public void close() throws SQLException {
		if (dataSource != null && (dataSource instanceof PoolBackedDataSource) && references.isEmpty()) {
			if (this.state == DataSourceState.UP) {
				PoolBackedDataSource poolBackedDataSource = (PoolBackedDataSource) dataSource;
				if (poolBackedDataSource.getNumBusyConnections() == 0) {
					logger.info("closing old datasource [" + this.dsId + "]");
					poolBackedDataSource.close();
					logger.info("old datasource [" + this.dsId + "] closed");
					this.state = DataSourceState.CLOSED;
				} else {
					throw new SingleDataSourceException(String.format(
					      "Cannot close dataSource[%s] since there are busy connections.", dsId));
				}
			} else {
				this.state = DataSourceState.CLOSED;
			}
		} else {
			// Normally not happen
			logger.warn("fail to close dataSource since dataSource is null or dataSource is not an instance of PoolBackedDataSource.");
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
		Connection conn = null;
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

	public synchronized Set<Object> getReferences() {
		return this.references;
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

	private DataSource initDataSources(DataSourceConfig value) {
		try {
			DataSource unPooledDataSource = DataSources.unpooledDataSource(value.getJdbcUrl(), value.getUser(),
			      value.getPassword());

			Map<String, Object> props = new HashMap<String, Object>();

			props.put("driverClass", value.getDriverClass());
			props.put("initialPoolSize", value.getInitialPoolSize());
			props.put("minPoolSize", value.getMinPoolSize());
			props.put("maxPoolSize", value.getMaxPoolSize());
			props.put("checkoutTimeout", value.getCheckoutTimeout());

			for (Any any : value.getProperties()) {
				props.put(any.getName(), any.getValue());
			}

			PoolBackedDataSource pooledDataSource = (PoolBackedDataSource) DataSources.pooledDataSource(
			      unPooledDataSource, props);

			logger.info(String.format("New dataSource [%s] created.", value.getId()));

			return pooledDataSource;
		} catch (Throwable e) {
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
