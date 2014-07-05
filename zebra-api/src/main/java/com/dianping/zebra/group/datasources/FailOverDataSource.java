package com.dianping.zebra.group.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 *
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

        public int getSleepTimes() {
            return atomicSleepTimes.get();
        }

        protected CheckWriteDataSourceResult checkWriteDataSource(DataSourceConfig config) {
            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = null;

            try {
                conn = getConnection(config);
                stmt = conn.createStatement();
                rs = stmt.executeQuery(config.getTestReadOnlySql());

                if (rs.next()) {
                    // switch database, 0 for write dataSource, 1 for read dataSource.
                    if (rs.getInt(1) == 0) {
                        return CheckWriteDataSourceResult.OK;
                    }
                }
            } catch (SQLException e) {
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
                if(conn != null){
                    try {
                        conn.close();
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
            return DriverManager.getConnection(config.getJdbcUrl(), config.getUser(), config.getPassword());
        }

        @Override
        public void run() {
            Transaction switchTransaction = null;

            while (!Thread.interrupted()) {
                try {
                    sleep(1);
                    if (Thread.interrupted()) {
                        break;
                    }

                    FindWriteDataSourceResult findResult = findWriteDataSource();
                    if (!findResult.isWriteDbExist()) {
                        continue;
                    } else if (findResult.isChangedWrteDb() && switchTransaction != null) {
                        switchTransaction.setStatus(Message.SUCCESS);
                        switchTransaction.complete();
                        switchTransaction = null;
                    }

                    while (!Thread.interrupted()) {
                        sleep(5);
                        if (Thread.interrupted()) {
                            break;
                        }

                        CheckWriteDataSourceResult checkWriteResult = checkWriteDataSource(configs.get(writeDs.getId()));

                        if (checkWriteResult == CheckWriteDataSourceResult.OK) {
                            continue;
                        } else {
                            switchTransaction = Cat.newTransaction("SQL.Coon", "SwitchWriteDb");
                        }
                        break;
                    }
                } catch (Throwable e) {
                    Cat.logError(e);
                }
            }

            if (switchTransaction != null && !switchTransaction.isCompleted()) {
                switchTransaction.setStatus("Thread interrupted");
                switchTransaction.complete();
            }
        }

        private void sleep(long seconds) {
            try {
                atomicSleepTimes.incrementAndGet();
                if (atomicSleepTimes.get() > 100) {
                    atomicSleepTimes.set(0);
                }

                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
            }
        }
    }
}
