package com.dianping.zebra.group.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.MasterDsNotFoundException;
import com.dianping.zebra.group.jdbc.AbstractDataSource;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;

/**
 * features: 1. auto-detect master database by select @@read_only</br> 2. auto check the master database.</br> 3. if cannot find any
 * master database in the initial phase, fail fast.</br>
 */
public class FailOverDataSource extends AbstractDataSource {

	private static final String ERROR_MESSAGE = "Cannot find any master dataSource.";

	private volatile InnerSingleDataSource master;

	private Map<String, DataSourceConfig> configs;

	private Thread masterDataSourceMonitorThread;

	public FailOverDataSource(Map<String, DataSourceConfig> configs) {
		this.configs = configs;
	}

	@Override
	public void close() throws SQLException {
		if (this.masterDataSourceMonitorThread != null) {
			masterDataSourceMonitorThread.interrupt();
		}
		if (master != null) {
			SingleDataSourceManagerFactory.getDataSourceManager().destoryDataSource(master);
		}
		super.close();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getConnection(null, null);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		if (master != null && master.isAvailable()) {
			return master.getConnection();
		} else {
			throw new SQLException("Master database is in the maintaining.");
		}
	}

	public SingleDataSourceMBean getCurrentDataSourceMBean() {
		return master;
	}

	private InnerSingleDataSource getDataSource(DataSourceConfig config) {
		if (master != null) {
			SingleDataSourceManagerFactory.getDataSourceManager().destoryDataSource(master);
		}

		return SingleDataSourceManagerFactory.getDataSourceManager().createDataSource(config);
	}

	@Override
	public void init() {
		init(true);
	}

	public void init(boolean forceCheckMaster) {
		MasterDataSourceMonitor monitor = new MasterDataSourceMonitor();

		if (forceCheckMaster && !monitor.findMasterDataSource().isMasterExist()) {
			throw new MasterDsNotFoundException(ERROR_MESSAGE);
		}

		masterDataSourceMonitorThread = new Thread(monitor);
		masterDataSourceMonitorThread.setDaemon(true);
		masterDataSourceMonitorThread.setName("Dal-" + FailOverDataSource.class.getSimpleName());
		masterDataSourceMonitorThread.start();
	}

	private boolean setMasterDb(DataSourceConfig config) {
		if (master == null || !master.getId().equals(config.getId())) {
			master = getDataSource(config);
			return true;
		}
		return false;
	}

	enum CheckMasterDataSourceResult {
		READ_WRITE(1), READ_ONLY(2), ERROR(3);

		private int value;

		private CheckMasterDataSourceResult(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	class FindMasterDataSourceResult {
		private boolean masterExist;

		private boolean changedMaster;

		public boolean isChangedMaster() {
			return changedMaster;
		}

		public boolean isMasterExist() {
			return masterExist;
		}

		public void setChangedMaster(boolean changedMaster) {
			this.changedMaster = changedMaster;
		}

		public void setMasterExist(boolean masterExist) {
			this.masterExist = masterExist;
		}
	}

	class MasterDataSourceMonitor implements Runnable {
		private AtomicInteger atomicSleepTimes = new AtomicInteger(0);

		private Map<String, Connection> cachedConnection = new HashMap<String, Connection>();

		private Transaction transaction = null;

		private int transactionTryLimits = 0;

		private final int maxTransactionTryLimits = 60 * 10; // 10 minute

		protected void closeConnection(String id) {
			if (!cachedConnection.containsKey(id)) {
				return;
			}

			try {
				cachedConnection.get(id).close();
			} catch (SQLException ignore) {
			} finally {
				cachedConnection.remove(id);
			}
		}

		protected void closeConnections() {
			for (Map.Entry<String, Connection> entity : cachedConnection.entrySet()) {
				try {
					entity.getValue().close();
				} catch (SQLException ignore) {
				}
			}

			cachedConnection.clear();
		}

		private void completeSwitchTransaction() {
			if (transaction != null) {
				Cat.logEvent("DAL.FailOver", "Success");
				transaction.setStatus(Message.SUCCESS);
				transaction.complete();
				transaction = null;
			}
		}

		private void createSwitchTransaction() {
			transaction = Cat.newTransaction("DAL", "FailOver");
		}

		public FindMasterDataSourceResult findMasterDataSource() {
			FindMasterDataSourceResult result = new FindMasterDataSourceResult();

			for (DataSourceConfig config : configs.values()) {
				CheckMasterDataSourceResult checkResult = isMasterDataSource(config);
				if (checkResult == CheckMasterDataSourceResult.READ_WRITE) {
					Cat.logEvent("DAL.Master", "Found-" + config.getId());

					result.setChangedMaster(setMasterDb(config));
					result.setMasterExist(true);
					break;
				}
			}

			return result;
		}

		protected Connection getConnection(DataSourceConfig config) throws SQLException {
			if (!cachedConnection.containsKey(config.getId())) {
				cachedConnection.put(config.getId(),
				      DriverManager.getConnection(config.getJdbcUrl(), config.getUser(), config.getPassword()));
			}

			return cachedConnection.get(config.getId());
		}

		public int getSleepTimes() {
			return atomicSleepTimes.get();
		}

		private void increaseTransactionTryTimes() {
			transactionTryLimits++;
			if (transactionTryLimits >= maxTransactionTryLimits) {
				transactionTryLimits = 0;

				if (transaction != null) {
					Cat.logEvent("DAL.FailOver", "Failed");
					transaction.setStatus("Fail to find any master database");
					transaction.complete();
					transaction = null;
				}
			}
		}

		private boolean isMaster(ResultSet rs) throws SQLException {
			if (rs.next()) {
				return rs.getInt(1) == 0;
			} else {
				return false;
			}
		}

		protected CheckMasterDataSourceResult isMasterDataSource(DataSourceConfig config) {
			Statement stmt = null;
			ResultSet rs = null;

			try {
				stmt = getConnection(config).createStatement();
				rs = stmt.executeQuery(config.getTestReadOnlySql());

				if (isMaster(rs)) {
					return CheckMasterDataSourceResult.READ_WRITE;
				} else {
					return CheckMasterDataSourceResult.READ_ONLY;
				}
			} catch (SQLException e) {
				Cat.logError(e);
				closeConnection(config.getId());

				return CheckMasterDataSourceResult.ERROR;
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

		@Override
		public void run() {
			while (!Thread.interrupted()) {
				try {
					sleepForSeconds(1);

					FindMasterDataSourceResult result = findMasterDataSource();
					if (!result.isMasterExist()) {
						increaseTransactionTryTimes();
					} else {
						completeSwitchTransaction();

						closeConnections();
						while (!Thread.interrupted()) {
							sleepForSeconds(5);

							if (isMasterDataSource(configs.get(master.getId())) != CheckMasterDataSourceResult.READ_WRITE) {
								createSwitchTransaction();
								closeConnections();
								break;
							}
						}
					}
				} catch (InterruptedException ignore) {
					break;
				} catch (Throwable e) {
					Cat.logError(e);
				}
			}

			closeConnections();
		}

		private void sleepForSeconds(long seconds) throws InterruptedException {
			atomicSleepTimes.incrementAndGet();
			if (atomicSleepTimes.get() > 100) {
				atomicSleepTimes.set(0);
			}

			TimeUnit.SECONDS.sleep(seconds);
		}
	}
}