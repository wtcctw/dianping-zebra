package com.dianping.zebra.group.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.IllegalConfigException;
import com.dianping.zebra.group.jdbc.AbstractDataSource;

public class FailOverDataSource extends AbstractDataSource {

	private static final Logger logger = LoggerFactory.getLogger(FailOverDataSource.class);

	private volatile SingleDataSource activeDs;

	private Map<String, SingleDataSource> standbyDataSources = new LinkedHashMap<String, SingleDataSource>();

	private SingleDataSourceManager singleDataSourceManager;

	private Thread switchMonitorThread;

	public FailOverDataSource(String name, Map<String, DataSourceConfig> dataSourceConfigs,
	      SingleDataSourceManager manager) {
		this.name = name;
		this.singleDataSourceManager = manager;

		for (DataSourceConfig config : dataSourceConfigs.values()) {
			this.standbyDataSources.put(config.getId(), singleDataSourceManager.createDataSource(name, config));
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getConnection(null, null);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		if (activeDs != null && activeDs.isAvailable()) {
			return activeDs.getConnection();
		} else {
			throw new SQLException("No dataSource avaiable.");
		}
	}

	@Override
	public void init() {
		switchMonitorThread = new Thread(new WriterSwitchMonitor());
		switchMonitorThread.setDaemon(true);
		switchMonitorThread.setName("Thread-" + WriterSwitchMonitor.class.getName());

		switchMonitorThread.start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
		}

		if (activeDs == null) {
			throw new IllegalConfigException("Cannot connect to any write datasources.");
		}
	}

	@Override
	public void close() throws SQLException {
		// for (SingleDataSource dataSource : standbyDataSources.values()) {
		// singleDataSourceManager.destoryDataSource(dataSource.getId(), name);
		// }

		if (this.switchMonitorThread != null) {
			switchMonitorThread.interrupt();
		}
	}

	class WriterSwitchMonitor implements Runnable {
		public Connection getConnection(DataSourceConfig config) throws SQLException {
			Connection conn = DriverManager.getConnection(config.getJdbcUrl(), config.getUser(), config.getPassword());

			return conn;
		}

		@Override
		public void run() {
			logger.info("failover dataSources switch monitor start...");

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				throw new RuntimeException(ex);
			}

			while (!Thread.interrupted()) {
				for (SingleDataSource ds : standbyDataSources.values()) {
					Connection conn = null;
					Statement stmt = null;
					try {
						conn = getConnection(ds.getConfig());
						stmt = conn.createStatement();
						ResultSet resultSet = stmt.executeQuery(ds.getConfig().getTestReadOnlySql());

						boolean hasSwitched = false;
						while (resultSet.next()) {
							// switch database
							if (resultSet.getInt(1) == 0) {
								activeDs = ds;
								hasSwitched = true;
								break;
							}
						}

						if (hasSwitched) {
							break;
						}
					} catch (SQLException ignore) {
						// do nothing
					} finally {
						try {
							if (stmt != null) {
								stmt.close();
							}

							if (conn != null) {
								conn.close();
							}
						} catch (SQLException ignore) {
						}
					}
				}

				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					break;
				}
			}

			logger.info("failover dataSources switch monitor stopped.");
		}
	}
}
