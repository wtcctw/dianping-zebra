package com.dianping.zebra.group.datasources;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.IllegalConfigException;
import com.dianping.zebra.group.exception.MasterDsNotFoundException;
import com.dianping.zebra.group.exception.WeakReferenceGCException;
import com.dianping.zebra.group.filter.DefaultJdbcFilterChain;
import com.dianping.zebra.group.filter.JdbcContext;
import com.dianping.zebra.group.filter.JdbcFilter;
import com.dianping.zebra.group.jdbc.AbstractDataSource;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;
import com.dianping.zebra.group.util.JdbcDriverClassHelper;
import com.dianping.zebra.group.util.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.ref.WeakReference;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * features: 1. auto-detect master database by select @@read_only</br> 2. auto check the master database.</br> 3. if cannot find any
 * master database in the initial phase, fail fast.</br>
 */
public class FailOverDataSource extends AbstractDataSource {
	private final static Logger logger = LogManager.getLogger(FailOverDataSource.class);

	private Map<String, DataSourceConfig> configs;

	private volatile SingleDataSource master;

	private Thread masterDataSourceMonitorThread;

	public FailOverDataSource(Map<String, DataSourceConfig> configs, JdbcContext context, List<JdbcFilter> filters) {
		this.configs = configs;
		this.filters = filters;
		this.context = context;
		this.context.setDataSource(this);
	}

	private void changeMetaData(DataSourceConfig config) {
		this.context.setDataSourceId(config.getId());
		this.context.setJdbcUrl(config.getJdbcUrl());
		this.context.setJdbcUsername(config.getUsername());
		this.context.setJdbcPassword(config.getPassword() == null ? null : StringUtils.repeat("*", config.getPassword()
			  .length()));
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

	private String getConfigSummary() {
		StringBuilder sb = new StringBuilder(100);

		for (Map.Entry<String, DataSourceConfig> config : configs.entrySet()) {
			sb.append(
				  String.format("[datasource=%s,url=%s,username=%s,password=%s,driverClass=%s,properties=%s]", config
							  .getValue().getId(), config.getValue().getJdbcUrl(), config.getValue().getUsername(),
						StringUtils
							  .repeat("*", config.getValue().getPassword() == null ?
									0 :
									config.getValue().getPassword().length()),
						config.getValue().getDriverClass(), config.getValue().getProperties()));
		}

		return sb.toString();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		if (master != null && master.isAvailable()) {
			return master.getConnection();
		} else {
			throw new SQLException("Master database is in the maintaining.");
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getConnection(null, null);
	}

	public SingleDataSourceMBean getCurrentDataSourceMBean() {
		return master;
	}

	private SingleDataSource getDataSource(DataSourceConfig config) {
		if (master != null) {
			SingleDataSourceManagerFactory.getDataSourceManager().destoryDataSource(master);
		}

		return SingleDataSourceManagerFactory.getDataSourceManager().createDataSource(config, this.context.clone(),
			  this.filters);
	}

	@Override
	public void init() {
		init(true);
	}

	public void init(boolean forceCheckMaster) {
		initFilter();

		MasterDataSourceMonitor monitor = new MasterDataSourceMonitor(this);

		try {
			FindMasterDataSourceResult result = monitor.findMasterDataSource();
			if (!result.isMasterExist()) {
				String error_message = String
					  .format("Cannot find any master dataSource. Configs=%s", getConfigSummary());

				if (forceCheckMaster) {
					MasterDsNotFoundException exp = new MasterDsNotFoundException(error_message, result.getException());
					logger.error(exp.getMessage(), exp);
					throw exp;
				} else {
					logger.warn(error_message, result.getException());
				}
			} else {
				logger.info("FailOverDataSource find master success!");
			}
		} catch (WeakReferenceGCException e) {
			logger.error("should never be here!", e);
		}

		masterDataSourceMonitorThread = new Thread(monitor);
		masterDataSourceMonitorThread.setDaemon(true);
		masterDataSourceMonitorThread.setName("Dal-MasterDataSourceChecker");
		masterDataSourceMonitorThread.start();
	}

	private void initFilter() {
		this.context.setDataSource(this);
	}

	private boolean setMasterDb(DataSourceConfig config) {
		if (master == null || !master.getId().equals(config.getId())) {
			master = getDataSource(config);
			changeMetaData(config);
			return true;
		}
		return false;
	}

	static enum CheckMasterDataSourceResult {
		READ_WRITE(1), READ_ONLY(2), ERROR(3);

		private int value;

		private Exception exception;

		private CheckMasterDataSourceResult(int value) {
			this.value = value;
		}

		public Exception getException() {
			return exception;
		}

		public void setException(Exception exception) {
			this.exception = exception;
		}

		public int getValue() {
			return value;
		}
	}

	public static class FindMasterDataSourceResult {
		private String dsId;

		private boolean changedMaster;

		private boolean masterExist;

		private Exception exception;

		public String getDsId() {
			return dsId;
		}

		public void setDsId(String dsId) {
			this.dsId = dsId;
		}

		public Exception getException() {
			return exception;
		}

		public void setException(Exception exception) {
			this.exception = exception;
		}

		public boolean isChangedMaster() {
			return changedMaster;
		}

		public void setChangedMaster(boolean changedMaster) {
			this.changedMaster = changedMaster;
		}

		public boolean isMasterExist() {
			return masterExist;
		}

		public void setMasterExist(boolean masterExist) {
			this.masterExist = masterExist;
		}
	}

	public static class MasterDataSourceMonitor implements Runnable {
		private final Logger logger = LogManager.getLogger(this.getClass());

		private final int maxTransactionTryLimits = 60 * 10; // 10 minute

		private AtomicInteger atomicSleepTimes = new AtomicInteger(0);

		private Map<String, Connection> cachedConnection = new HashMap<String, Connection>();

		private WeakReference<FailOverDataSource> ref;

		private int transactionTryLimits = 0;

		public MasterDataSourceMonitor(FailOverDataSource dsRef) {
			this.ref = new WeakReference<FailOverDataSource>(dsRef);
		}

		private void checkIfFailOverDataSourceHasGC() throws WeakReferenceGCException {
			getWeakFailOverDataSource();
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

		private FindMasterDataSourceResult findMasterDataSourceOrigin() throws WeakReferenceGCException {
			FindMasterDataSourceResult result = new FindMasterDataSourceResult();

			if (getWeakFailOverDataSource().configs.values().size() == 0) {
				Exception exp = new IllegalConfigException("zero writer data source in config!");
				logger.warn(exp.getMessage(), exp);
			}

			for (DataSourceConfig config : getWeakFailOverDataSource().configs.values()) {
				CheckMasterDataSourceResult checkResult = isMasterDataSource(config);
				if (checkResult == CheckMasterDataSourceResult.READ_WRITE) {
					result.setChangedMaster(getWeakFailOverDataSource().setMasterDb(config));
					result.setMasterExist(true);
					result.setDsId(config.getId());
					break;
				} else if (checkResult == CheckMasterDataSourceResult.ERROR) {
					result.setException(checkResult.getException());
				}
			}

			if (result.isMasterExist()) {
				// reset the exception if has any
				result.setException(null);
			}

			return result;
		}

		public FindMasterDataSourceResult findMasterDataSource() throws WeakReferenceGCException {
			List<JdbcFilter> filters = getWeakFailOverDataSource().filters;

			if (filters != null && filters.size() > 0) {
				JdbcFilter chain = new DefaultJdbcFilterChain(filters) {
					@Override
					public FindMasterDataSourceResult findMasterFailOverDataSource(MasterDataSourceMonitor source,
						  JdbcFilter chain) {
						if (index < filters.size()) {
							return filters.get(index++).findMasterFailOverDataSource(source, chain);
						} else {
							return source.findMasterDataSourceOrigin();
						}
					}
				};
				return chain.findMasterFailOverDataSource(this, chain);
			} else {
				return findMasterDataSourceOrigin();
			}
		}

		protected Connection getConnection(DataSourceConfig config) throws SQLException {
			if (!cachedConnection.containsKey(config.getId())) {
				JdbcDriverClassHelper.loadDriverClass(config.getDriverClass(), config.getJdbcUrl());
				cachedConnection.put(config.getId(),
					  DriverManager.getConnection(config.getJdbcUrl(), config.getUsername(), config.getPassword()));
			}

			return cachedConnection.get(config.getId());
		}

		public int getSleepTimes() {
			return atomicSleepTimes.get();
		}

		private FailOverDataSource getWeakFailOverDataSource() throws WeakReferenceGCException {
			FailOverDataSource weak = ref.get();
			if (weak == null) {
				throw new WeakReferenceGCException();
			}
			return weak;
		}

		private void increaseTransactionTryTimes() throws WeakReferenceGCException {
			transactionTryLimits++;
			if (transactionTryLimits >= maxTransactionTryLimits) {
				transactionTryLimits = 0;
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
				logger.error(e.getMessage(), e);
				closeConnection(config.getId());

				CheckMasterDataSourceResult result = CheckMasterDataSourceResult.ERROR;
				result.setException(e);

				return result;
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
					checkIfFailOverDataSourceHasGC();

					FindMasterDataSourceResult result = findMasterDataSource();
					if (!result.isMasterExist()) {
						increaseTransactionTryTimes();
					} else {
						closeConnections();
						while (!Thread.interrupted()) {
							sleepForSeconds(5);

							if (isMasterDataSource(
								  getWeakFailOverDataSource().configs.get(getWeakFailOverDataSource().master
										.getId())) != CheckMasterDataSourceResult.READ_WRITE) {
								closeConnections();
								break;
							}
						}
					}
				} catch (WeakReferenceGCException e) {
					logger.error("FailOverDataSource has be GC", e);
					break;
				} catch (InterruptedException ignore) {
					break;
				} catch (Exception e) {
					logger.error(e);
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