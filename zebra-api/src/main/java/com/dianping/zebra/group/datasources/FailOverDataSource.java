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
import com.dianping.zebra.group.exception.WriteDsNotFoundException;
import com.dianping.zebra.group.jdbc.AbstractDataSource;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;

/**
 * features: 1. auto-detect write database by select @@read_only</br> 2. auto check the write database.</br> 3. if cannot find any
 * write database in the initial phase, fail fast.</br>
 */
public class FailOverDataSource extends AbstractDataSource {

	private static final String ERROR_MESSAGE = "Cannot find any write dataSource.";

	private volatile SingleDataSource writeDs;

	private Map<String, DataSourceConfig> configs;

	private Thread writeDataSourceMonitorThread;

	public FailOverDataSource(Map<String, DataSourceConfig> configs) {
		this.configs = configs;
	}

	@Override
	public void close() throws SQLException {
		if (this.writeDataSourceMonitorThread != null) {
			writeDataSourceMonitorThread.interrupt();
		}
		if (writeDs != null) {
			SingleDataSourceManagerFactory.getDataSourceManager().destoryDataSource(writeDs.getId(), this);
		}
		super.close();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getConnection(null, null);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		if (writeDs != null && writeDs.isAvailable()) {
			return writeDs.getConnection();
		} else {
			throw new SQLException("Write dataSource is currently in the maintaining stage.");
		}
	}

	public SingleDataSourceMBean getCurrentDataSourceMBean() {
		return writeDs;
	}

	private SingleDataSource getDataSource(DataSourceConfig config) {
		if (writeDs != null) {
			SingleDataSourceManagerFactory.getDataSourceManager().destoryDataSource(writeDs.getId(), this);
		}

		return SingleDataSourceManagerFactory.getDataSourceManager().createDataSource(this, config);
	}

	@Override
	public void init() {
		init(true);
	}

	public void init(boolean forceCheckWrite) {
		WriterDataSourceMonitor monitor = new WriterDataSourceMonitor();

		if (forceCheckWrite && !monitor.findWriteDataSource().isWriteDbExist()) {
			throw new WriteDsNotFoundException(ERROR_MESSAGE);
		}

		writeDataSourceMonitorThread = new Thread(monitor);
		writeDataSourceMonitorThread.setDaemon(true);
		writeDataSourceMonitorThread.setName("FailOverDataSource");
		writeDataSourceMonitorThread.start();

		super.init();
	}

	private boolean setWriteDb(DataSourceConfig config) {
		if (writeDs == null || !writeDs.getId().equals(config.getId())) {
			writeDs = getDataSource(config);
			return true;
		}
		return false;
	}

	enum CheckWriteDataSourceResult {
		WRITABLE(1), READ_ONLY(2), ERROR(3);

		private int value;

		private CheckWriteDataSourceResult(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	class FindWriteDataSourceResult {
		private boolean writeDbExist;

		private boolean changedWriteDb;

		public boolean isChangedWriteDb() {
			return changedWriteDb;
		}

		public boolean isWriteDbExist() {
			return writeDbExist;
		}

		public void setChangedWriteDb(boolean changedWriteDb) {
			this.changedWriteDb = changedWriteDb;
		}

		public void setWriteDbExist(boolean writeDbExist) {
			this.writeDbExist = writeDbExist;
		}
	}

	class WriterDataSourceMonitor implements Runnable {
		private AtomicInteger atomicSleepTimes = new AtomicInteger(0);

		private Map<String, Connection> cachedConnection = new HashMap<String, Connection>();

		Transaction transaction = null;

		private int transactionTryLimits = 0;

		private final int maxTransactionTryLimits = 60 * 10; // 10 minute

		private void increaseTransactionTryTimes() {
			transactionTryLimits++;
			if (transactionTryLimits >= maxTransactionTryLimits) {
				transactionTryLimits = 0;

				if (transaction != null) {
					Cat.logEvent("DAL", "FindWriteDbFailed");
					transaction.setStatus("Fail to find write dataSource");
					transaction.complete();
					transaction = null;
				}
			}
		}

		protected CheckWriteDataSourceResult checkWriteDataSource(DataSourceConfig config) {
			Statement stmt = null;
			ResultSet rs = null;

			try {
				stmt = getConnection(config).createStatement();
				rs = stmt.executeQuery(config.getTestReadOnlySql());

				if (isWritable(rs)) {
					return CheckWriteDataSourceResult.WRITABLE;
				}
			} catch (SQLException e) {
				Cat.logError(e);
				closeConnection(config.getId());

				return CheckWriteDataSourceResult.ERROR;
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

			return CheckWriteDataSourceResult.READ_ONLY;
		}

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

		private void createSwitchTransaction() {
			transaction = Cat.newTransaction("DAL", "SwitchWriteDb");
		}

		public FindWriteDataSourceResult findWriteDataSource() {
			FindWriteDataSourceResult result = new FindWriteDataSourceResult();

			for (DataSourceConfig config : configs.values()) {
				CheckWriteDataSourceResult checkResult = checkWriteDataSource(config);
				if (checkResult == CheckWriteDataSourceResult.WRITABLE) {
					result.setChangedWriteDb(setWriteDb(config));
					result.setWriteDbExist(true);
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

		private boolean isWritable(ResultSet rs) throws SQLException {
			if (rs.next()) {
				return rs.getInt(1) == 0;
			} else {
				return false;
			}
		}

		@Override
		public void run() {
			while (!Thread.interrupted()) {
				try {
					sleepForSeconds(1);

					FindWriteDataSourceResult findResult = findWriteDataSource();
					if (!findResult.isWriteDbExist()) {
						increaseTransactionTryTimes();
					} else {
						completeSwitchTransaction(findResult);

						closeConnections();
						while (!Thread.interrupted()) {
							sleepForSeconds(5);

							CheckWriteDataSourceResult checkWriteResult = checkWriteDataSource(configs.get(writeDs.getId()));
							if (checkWriteResult != CheckWriteDataSourceResult.WRITABLE) {
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

			Cat.logEvent("DAL", "ShutDown FailOverDataSource");
			closeConnections();
		}

		private void completeSwitchTransaction(FindWriteDataSourceResult findResult) {
			if (findResult.isChangedWriteDb() && transaction != null) {
				Cat.logEvent("DAL", "FindWriteDbSuccess");
				transaction.setStatus(Message.SUCCESS);
				transaction.complete();
				transaction = null;
			}
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
