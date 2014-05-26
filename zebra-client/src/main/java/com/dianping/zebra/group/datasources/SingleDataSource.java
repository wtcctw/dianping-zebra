package com.dianping.zebra.group.datasources;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.IllegalConfigException;
import com.dianping.zebra.group.jdbc.AbstractDataSource;
import com.dianping.zebra.group.jdbc.CountPunisher;
import com.mchange.v2.c3p0.DataSources;
import com.mchange.v2.c3p0.PoolBackedDataSource;

public class SingleDataSource extends AbstractDataSource implements MarkableDataSource {

	private static final Logger logger = LoggerFactory.getLogger(SingleDataSource.class);

	private volatile DataSourceState state = DataSourceState.INITIAL;

	private String dsId;

	private DataSourceConfig config;

	private DataSource dataSource;

	private Set<String> users = new HashSet<String>(); // 存储有多少业务方共享了这个dataSource

	private CountPunisher punisher;

	public SingleDataSource(DataSourceConfig config) {
		this.dsId = config.getId();
		this.config = config;
		this.punisher = new CountPunisher(this, 1000L, 1000L);
		this.dataSource = initDataSources(config);
	}

	public CountPunisher getPunisher(){
		return this.punisher;
	}
	
	public synchronized Set<String> getUsers() {
		return this.users;
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
			pooledDataSource.setIdentityToken(value.getId());

			logger.info(String.format("dataSource [%s] created. Properties are [%s]", value.getId(),
			      ReflectionToStringBuilder.toString(pooledDataSource)));

			return pooledDataSource;
		} catch (Throwable e) {
			throw new IllegalConfigException(e);
		}
	}

	private void checkState() throws SQLException {
		if (state == DataSourceState.CLOSED || state == DataSourceState.DOWN) {
			throw new SQLException(String.format("dataSource is not avaiable, current state is [%s]", state));
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		checkState();
		return getConnection(null, null);
	}

	public boolean isAvailable() {
		return this.state == DataSourceState.INITIAL || this.state == DataSourceState.UP;
	}

	public boolean isDown() {
		return this.state == DataSourceState.DOWN;
	}

	public boolean isClosed() {
		return this.state == DataSourceState.CLOSED;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		checkState();
		Connection conn = null;
		try {
			conn = this.dataSource.getConnection();
		} catch (SQLException e) {
			punisher.punish(e);

			throw e;
		}

		if (state == DataSourceState.INITIAL) {
			state = DataSourceState.UP;
		}

		return new SingleConnection(this.dsId, conn);
	}

	public String getId() {
		return this.dsId;
	}

	@Override
	public DataSourceState getState() {
		return this.state;
	}

	public synchronized DataSourceConfig getConfig() {
		return this.config;
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

	@Override
	public void close() throws SQLException {
		if (dataSource != null && (dataSource instanceof PoolBackedDataSource)) {
			PoolBackedDataSource poolBackedDataSource = (PoolBackedDataSource) dataSource;
			String dsId = poolBackedDataSource.getIdentityToken();

			if (this.users.isEmpty() && poolBackedDataSource.getNumBusyConnections() == 0) {
				logger.info("closing the datasource : " + dsId);
				poolBackedDataSource.close();
				logger.info("datasource : " + dsId + " closed");
				this.state = DataSourceState.CLOSED;
			} else {
				throw new SQLException("Cannot close dataSource since there are busy connections.");
			}
		} else {
			// Normally not happen
			logger.warn("fail to close dataSource since dataSource is null or dataSource is not an instance of PoolBackedDataSource.");
		}
	}
}
