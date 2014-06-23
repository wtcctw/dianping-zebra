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

import com.dianping.cat.Cat;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.jdbc.AbstractDataSource;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;

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

	public SingleDataSourceMBean getCurrentDataSourceMBean() {
		return activeDs;
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

			logger.info("old failover dataSources switch monitor stopped.");
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

			boolean firstLoop = true;

			while (!Thread.interrupted()) {
				for (SingleDataSource ds : standbyDataSources.values()) {
					Connection conn = null;
					Statement stmt = null;
					ResultSet rs = null;
					try {
						conn = getConnection(ds.getConfig());
						stmt = conn.createStatement();
						rs = stmt.executeQuery(ds.getConfig().getTestReadOnlySql());

						boolean hasSwitched = false;
						while (rs.next()) {
							// switch database, 0 for write dataSource, 1 for read dataSource.
							if (rs.getInt(1) == 0) {
								activeDs = ds;
								hasSwitched = true;
								break;
							}
						}

						if (hasSwitched) {
							break;
						}
					} catch (SQLException e) {
						boolean shouldCat =
						// if could not find write database in the first for-loop,
						firstLoop ||
						// or communication link fail,then print the error message to cat
						      (activeDs != null && activeDs.getId().equals(ds.getId()));

						if (shouldCat) {
							Cat.logError(ds.getId(), e);
						}

						try {
							connections.remove(ds.getId());
							if (conn != null) {
								conn.close();
							}
						} catch (SQLException ignore) {
						}
					} finally {
						if (rs != null) {
							try {
								rs.close();
							} catch (SQLException ignore) {
							}
						}
						if (stmt != null) {
							try {
								stmt.close();
							} catch (SQLException ignore) {
							}
						}
					}
				}

				firstLoop = false;

				try {
					TimeUnit.SECONDS.sleep(1); // TODO: temperary
				} catch (InterruptedException e) {
					break;
				}
			}

			close();
		}
	}
}