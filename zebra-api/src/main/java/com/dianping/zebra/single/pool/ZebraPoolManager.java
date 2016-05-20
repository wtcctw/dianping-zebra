package com.dianping.zebra.single.pool;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.dianping.zebra.Constants;
import com.dianping.zebra.exception.ZebraConfigException;
import com.dianping.zebra.exception.ZebraException;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.util.DataSourceState;
import com.dianping.zebra.single.jdbc.SingleDataSource;
import com.dianping.zebra.util.JdbcDriverClassHelper;
import com.mchange.v2.c3p0.DataSources;
import com.mchange.v2.c3p0.PoolBackedDataSource;

public class ZebraPoolManager {

	private static final Logger logger = LogManager.getLogger(ZebraPoolManager.class);

	private static final int MAX_CLOSE_ATTEMPT = 600;

	public static DataSource buildDataSource(DataSourceConfig value) {
		try {
			JdbcDriverClassHelper.loadDriverClass(value.getDriverClass(), value.getJdbcUrl());
			if (value.getType().equalsIgnoreCase(Constants.CONNECTION_POOL_TYPE_C3P0)) {
				DataSource unPooledDataSource = DataSources.unpooledDataSource(value.getJdbcUrl(), value.getUsername(),
						value.getPassword());

				Map<String, Object> props = new HashMap<String, Object>();

				props.put("driverClass", value.getDriverClass());

				for (Any any : value.getProperties()) {
					props.put(any.getName(), any.getValue());
				}

				PoolBackedDataSource pooledDataSource = (PoolBackedDataSource) DataSources
						.pooledDataSource(unPooledDataSource, props);

				logger.info(String.format("New dataSource [%s] created.", value.getId()));

				return pooledDataSource;
			} else if (value.getType().equalsIgnoreCase(Constants.CONNECTION_POOL_TYPE_TOMCAT_JDBC)) {
				PoolProperties p = new PoolProperties();
				p.setUrl(value.getJdbcUrl());
				p.setDriverClassName(value.getDriverClass());
				p.setUsername(value.getUsername());
				p.setPassword(value.getPassword());

				p.setInitialSize(getIntProperty(value, "initialPoolSize", 5));
				p.setMaxActive(getIntProperty(value, "maxPoolSize", 20));
				p.setMinIdle(getIntProperty(value, "minPoolSize", 5));
				p.setMaxIdle(getIntProperty(value, "maxPoolSize", 20));
				p.setMaxWait(getIntProperty(value, "checkoutTimeout", 1000));

				p.setRemoveAbandoned(true);
				p.setRemoveAbandonedTimeout(60);
				p.setTestOnBorrow(true);
				p.setTestOnReturn(false);
				p.setTestWhileIdle(true);
				p.setValidationQuery(getStringProperty(value, "preferredTestQuery", "SELECT 1"));
				p.setValidationInterval(30000); // 5 min
				p.setTimeBetweenEvictionRunsMillis(300000); // 30 min
				p.setMinEvictableIdleTimeMillis(1800000);

				org.apache.tomcat.jdbc.pool.DataSource datasource = new org.apache.tomcat.jdbc.pool.DataSource();
				datasource.setPoolProperties(p);

				logger.info(String.format("New dataSource [%s] created.", value.getId()));

				return datasource;
			} else if(value.getType().equalsIgnoreCase(Constants.CONNECTION_POOL_TYPE_DRUID)) {
				Properties props = new Properties();
				
				props.put("driverClass", value.getDriverClass());

				for (Any any : value.getProperties()) {
					props.put(any.getName(), any.getValue());
				}
				
				DruidDataSource druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(props);
				
				logger.info(String.format("New dataSource [%s] created.", value.getId()));

				return druidDataSource;
			} else {
				throw new ZebraConfigException("illegal datasource pool type : " + value.getType());
			}
		} catch (ZebraConfigException e) {
			throw e;
		} catch (Exception e) {
			throw new ZebraConfigException(e);
		}
	}

	public static void close(SingleDataSource singleDataSource) throws SQLException {
		DataSource dataSource = singleDataSource.getInnerDataSource();
		if (dataSource != null) {
			String dsId = singleDataSource.getId();
			if (dataSource instanceof PoolBackedDataSource) {
				PoolBackedDataSource poolBackedDataSource = (PoolBackedDataSource) dataSource;

				logger.info(
						singleDataSource.getAndIncrementCloseAttempt() + " attempt to close datasource [" + dsId + "]");

				if (poolBackedDataSource.getNumBusyConnections() == 0
						|| singleDataSource.getCloseAttempt() >= MAX_CLOSE_ATTEMPT) {
					DataSources.destroy(poolBackedDataSource);

					logger.info("datasource [" + dsId + "] closed");
					singleDataSource.setState(DataSourceState.CLOSED);
				} else {
					throw new ZebraException(
							String.format("Cannot close dataSource[%s] since there are busy connections.", dsId));
				}
			} else if (dataSource instanceof org.apache.tomcat.jdbc.pool.DataSource) {
				org.apache.tomcat.jdbc.pool.DataSource ds = (org.apache.tomcat.jdbc.pool.DataSource) dataSource;

				logger.info(
						singleDataSource.getAndIncrementCloseAttempt() + " attempt to close datasource [" + dsId + "]");

				if (ds.getActive() == 0 || singleDataSource.getCloseAttempt() >= MAX_CLOSE_ATTEMPT) {
					logger.info("closing old datasource [" + dsId + "]");

					ds.close();

					logger.info("old datasource [" + dsId + "] closed");
					singleDataSource.setState(DataSourceState.CLOSED);
				} else {
					throw new ZebraException(
							String.format("Cannot close dataSource[%s] since there are busy connections.", dsId));
				}
			} else if(dataSource instanceof DruidDataSource) {
				DruidDataSource druidDataSource = (DruidDataSource) dataSource;
				
				logger.info(
						singleDataSource.getAndIncrementCloseAttempt() + " attempt to close datasource [" + dsId + "]");

				if (druidDataSource.getActiveCount() == 0 || singleDataSource.getCloseAttempt() >= MAX_CLOSE_ATTEMPT) {
					logger.info("closing old datasource [" + dsId + "]");

					druidDataSource.close();

					logger.info("old datasource [" + dsId + "] closed");
					singleDataSource.setState(DataSourceState.CLOSED);
				} else {
					throw new ZebraException(
							String.format("Cannot close dataSource[%s] since there are busy connections.", dsId));
				}
			} else {
				Exception exp = new ZebraException(
						"fail to close dataSource since dataSource is not an instance of C3P0 or Tomcat-Jdbc.");
				logger.warn(exp.getMessage(), exp);
			}
		} else {
			Exception exp = new ZebraException("fail to close dataSource since dataSource is null.");
			logger.warn(exp.getMessage(), exp);
		}
	}

	private static int getIntProperty(DataSourceConfig config, String name, int defaultValue) {
		for (Any any : config.getProperties()) {
			if (any.getName().equalsIgnoreCase(name)) {
				return Integer.parseInt(any.getValue());
			}
		}

		return defaultValue;
	}

	public static String getStringProperty(DataSourceConfig config, String name, String defaultValue) {
		for (Any any : config.getProperties()) {
			if (any.getName().equalsIgnoreCase(name)) {
				return any.getValue();
			}
		}

		return defaultValue;
	}
}
