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
 * features:
 * 1. auto-detect write database by select @@read_only</br>
 * 2. auto check the write database.</br>
 * 3. if cannot find any write database in the initial phase, fail fast.</br>
 * <p/>
 * todo:
 * 1. write database is alive,but has network error or timeout exception.</br>
 * 2. the dba is changing the write database, it will be too many exceptions.<br/>
 */
public class FailOverDataSource extends AbstractDataSource {

    private static final String ERROR_MESSAGE = "[DAL]Cannot find any write dataSource.";

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
        WriterDataSourceMonitor runner = new WriterDataSourceMonitor();

        if (forceCheckWrite && !runner.findWriteDataSource().isWriteDbExist()) {
            throw new WriteDsNotFoundException(ERROR_MESSAGE);
        }

        writeDataSourceMonitorThread = new Thread(runner);
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

    class FindWriteDataSourceResult {
        private boolean writeDbExist;
        private boolean changedWrteDb;

        public boolean isWriteDbExist() {
            return writeDbExist;
        }

        public void setWriteDbExist(boolean writeDbExist) {
            this.writeDbExist = writeDbExist;
        }

        public boolean isChangedWrteDb() {
            return changedWrteDb;
        }

        public void setChangedWrteDb(boolean changedWrteDb) {
            this.changedWrteDb = changedWrteDb;
        }
    }

    enum CheckWriteDataSourceResult {
        OK(1), READ_ONLY(2), ERROR(3);

        private int status;

        private CheckWriteDataSourceResult(int status) {
            this.status = status;
        }
    }

    class WriterDataSourceMonitor implements Runnable {
        private AtomicInteger atomicSleepTimes = new AtomicInteger(0);
        private Map<String, Connection> cachedConnection = new HashMap<String, Connection>();

        public int getSleepTimes() {
            return atomicSleepTimes.get();
        }

        protected CheckWriteDataSourceResult checkWriteDataSource(DataSourceConfig config) {
            Statement stmt = null;
            ResultSet rs = null;

            try {
                Connection conn = getConnection(config);
                stmt = conn.createStatement();
                rs = stmt.executeQuery(config.getTestReadOnlySql());

                if (rs.next()) {
                    // switch database, 0 for write dataSource, 1 for read dataSource.
                    if (rs.getInt(1) == 0) {
                        return CheckWriteDataSourceResult.OK;
                    }
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

        public FindWriteDataSourceResult findWriteDataSource() {
            FindWriteDataSourceResult result = new FindWriteDataSourceResult();

            for (DataSourceConfig config : configs.values()) {
                CheckWriteDataSourceResult checkResult = checkWriteDataSource(config);
                if (checkResult == CheckWriteDataSourceResult.OK) {
                    result.setChangedWrteDb(setWriteDb(config));
                    result.setWriteDbExist(true);
                    break;
                } else if (checkResult == CheckWriteDataSourceResult.ERROR) {

                }
            }
            return result;
        }

        protected Connection getConnection(DataSourceConfig config) throws SQLException {
            if (!cachedConnection.containsKey(config.getId())) {
                cachedConnection.put(config.getId(), DriverManager.getConnection(config.getJdbcUrl(), config.getUser(), config.getPassword()));
            }
            return cachedConnection.get(config.getId());
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

        @Override
        public void run() {
            Transaction switchTransaction = null;

            while (!Thread.interrupted()) {
                try {
                    sleep(1);

                    FindWriteDataSourceResult findResult = findWriteDataSource();
                    if (!findResult.isWriteDbExist()) {
                        Cat.logEvent("DAL", "FindWriteDbFailed");
                        continue;
                    }

                    Cat.logEvent("DAL", "FindWriteDbSuccess");
                    if (findResult.isChangedWrteDb() && switchTransaction != null) {
                        switchTransaction.setStatus(Message.SUCCESS);
                        switchTransaction.complete();
                        switchTransaction = null;
                    }

                    closeConnections();
                    while (!Thread.interrupted()) {
                        sleep(5);

                        CheckWriteDataSourceResult checkWriteResult = checkWriteDataSource(configs.get(writeDs.getId()));

                        if (checkWriteResult == CheckWriteDataSourceResult.OK) {
                            continue;
                        } else {
                            switchTransaction = Cat.newTransaction("DAL", "SwitchWriteDb");
                        }

                        closeConnections();
                        break;
                    }
                } catch (InterruptedException ignore) {
                    break;
                } catch (Throwable e) {
                    Cat.logError(e);
                }
            }

            Cat.logEvent("DAL", "WriterDataSourceMonitorInterrupted");
            if (switchTransaction != null && !switchTransaction.isCompleted()) {
                switchTransaction.setStatus("Thread interrupted");
                switchTransaction.complete();
            }
            closeConnections();
        }

        private void sleep(long seconds) throws InterruptedException {
            atomicSleepTimes.incrementAndGet();
            if (atomicSleepTimes.get() > 100) {
                atomicSleepTimes.set(0);
            }

            TimeUnit.SECONDS.sleep(seconds);
        }
    }
}
