package com.dianping.zebra.group.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.jdbc.AbstractDataSource;

public class FailOverDataSource extends AbstractDataSource {

	private static final Logger logger = LoggerFactory.getLogger(FailOverDataSource.class);

	private volatile SingleDataSource activeDs;

	private Map<String, SingleDataSource> standbyDataSources = new LinkedHashMap<String, SingleDataSource>();

	private Map<String, DataSourceConfig> configs;

	private Thread switchMonitorThread;

	public FailOverDataSource(Map<String, DataSourceConfig> configs) {
		this.configs = configs;
	}

	@Override
	public void close() throws SQLException {
		if (this.switchMonitorThread != null) {
			switchMonitorThread.interrupt();
		}

		for (String dsId : standbyDataSources.keySet()) {
			SingleDataSourceManagerFactory.getDataSourceManager().destoryDataSource(dsId, this);
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
			throw new SQLException("Write dataSource is currently in the maintaining stage. ");
		}
	}

	@Override
	public void init() {
		for (Entry<String, DataSourceConfig> entry : configs.entrySet()) {
			DataSourceConfig config = entry.getValue();
			this.standbyDataSources.put(config.getId(), SingleDataSourceManagerFactory.getDataSourceManager()
			      .createDataSource(this, config));
		}

		switchMonitorThread = new Thread(new WriterSwitchMonitor());
		switchMonitorThread.setDaemon(true);
		switchMonitorThread.setName("Thread-" + WriterSwitchMonitor.class.getName());

		switchMonitorThread.start();

		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
		}
	}

	class WriterSwitchMonitor implements Runnable {
		private Map<String, Connection> connections = new HashMap<String, Connection>();

		private void close() {
			for (Connection conn : connections.values()) {
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
			}

			logger.info("failover dataSources switch monitor stopped.");
		}

		public Connection getConnection(DataSourceConfig config) throws SQLException {
			Connection conn = connections.get(config.getId());

			if (conn == null) {
				conn = DriverManager.getConnection(config.getJdbcUrl(), config.getUser(), config.getPassword());
				connections.put(config.getId(), conn);
			}

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
						} catch (SQLException ignore) {
						}
					}
				}

				try {
					TimeUnit.SECONDS.sleep(60);
				} catch (InterruptedException e) {
					break;
				}
			}

			close();
		}
	}
}
