package com.dianping.zebra.group.datasources;

import com.dianping.cat.message.Transaction;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.WriteDsNotFoundException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FailoverDataSourceTest {
    Map<String, DataSourceConfig> configs;
    Statement state = mock(Statement.class);
    Statement readonlyState = mock(Statement.class);
    ResultSet result = mock(ResultSet.class);
    ResultSet readOnlyResult = mock(ResultSet.class);
    Connection coon = mock(Connection.class);
    Connection readOnlyCoon = mock(Connection.class);

    @Before
    public void init() throws SQLException {
        configs = new HashMap<String, DataSourceConfig>();
        DataSourceConfig write1 = new DataSourceConfig();
        write1.setId("db1");
        configs.put("db1", write1);
        DataSourceConfig write2 = new DataSourceConfig();
        write2.setId("db2");
        configs.put("db2", write2);

        when(result.next()).thenReturn(true);
        when(result.getInt(1)).thenReturn(0);
        when(readOnlyResult.next()).thenReturn(true);
        when(readOnlyResult.getInt(1)).thenReturn(1);

        when(state.executeQuery(anyString())).thenReturn(result);
        when(readonlyState.executeQuery(anyString())).thenReturn(readOnlyResult);

        when(coon.createStatement()).thenReturn(state);
        when(readOnlyCoon.createStatement()).thenReturn(readonlyState);
    }

    @Test
    public void test_use_auto_find_write_db() throws InterruptedException, SQLException {
        FailOverDataSourceForTestCat mockedDs = new FailOverDataSourceForTestCat(configs);

        mockedDs.setConnectionProvider(new ConnectionProvider() {
            @Override
            public Connection getConnection(DataSourceConfig config) {
                return coon;
            }
        });

        mockedDs.init();
        TimeUnit.MILLISECONDS.sleep(1200);
        Assert.assertEquals("db1", mockedDs.getCurrentDataSourceMBean().getId());

        mockedDs.setConnectionProvider(new ConnectionProvider() {
            @Override
            public Connection getConnection(DataSourceConfig config) {
                if (config.getId().equals("db1")) {
                    return readOnlyCoon;
                }
                return coon;
            }
        });
        TimeUnit.MILLISECONDS.sleep(1200);
        Assert.assertEquals("db2", mockedDs.getCurrentDataSourceMBean().getId());
    }


    @Test(expected = WriteDsNotFoundException.class)
    public void test_use_no_write_db() throws InterruptedException, SQLException {
        FailOverDataSourceForTestCat mockedDs = new FailOverDataSourceForTestCat(configs);
        mockedDs.setConnectionProvider(new ConnectionProvider() {
            @Override
            public Connection getConnection(DataSourceConfig config) {
                return readOnlyCoon;
            }
        });
        mockedDs.initFailFast();
    }


    @Test
    public void test_use_default_write_db() throws InterruptedException, SQLException {
        FailOverDataSourceForTestCat mockedDs = new FailOverDataSourceForTestCat(configs);
        mockedDs.setConnectionProvider(new ConnectionProvider() {
            @Override
            public Connection getConnection(DataSourceConfig config) {
                return coon;
            }
        });

        mockedDs.init();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals("db1", mockedDs.getCurrentDataSourceMBean().getId());
    }


    /**
     * just commit the transaction when write database changed
     */
    @Test
    @Ignore
    public void test_switch_write_db_on_cat_transaction() throws InterruptedException, SQLException {
        Transaction t = mock(Transaction.class);
        FailOverDataSourceForTestCat mockedDs = new FailOverDataSourceForTestCat(configs);
        mockedDs.setMockedTransaction(t);
        mockedDs.setConnectionProvider(new ConnectionProvider() {
            @Override
            public Connection getConnection(DataSourceConfig config) {
                return coon;
            }
        });

        mockedDs.init();
        TimeUnit.SECONDS.sleep(1);
        //changed because the writeBs is null
        TimeUnit.MILLISECONDS.sleep(1200);

        TimeUnit.SECONDS.sleep(1);
        //did change because the writeBs is the same
        TimeUnit.MILLISECONDS.sleep(1200);

        mockedDs.setConnectionProvider(new ConnectionProvider() {
            @Override
            public Connection getConnection(DataSourceConfig config) {
                if (config.getId().equals("db1")) {
                    return readOnlyCoon;
                }
                return coon;
            }
        });
        TimeUnit.MILLISECONDS.sleep(1200);
        //changed the writeBs and fire the transaction again
        verify(t, times(2)).complete();
    }

    interface ConnectionProvider {
        Connection getConnection(DataSourceConfig config);
    }

    class FailOverDataSourceForTestCat extends FailOverDataSource {
        private Transaction mockedTransaction;
        private ConnectionProvider connectionProvider;

        public FailOverDataSourceForTestCat(Map<String, DataSourceConfig> configs) {
            super(configs);
        }

        @Override
        protected Transaction getSwitchWriteDbTransaction() {
            if (mockedTransaction == null) {
                return super.getSwitchWriteDbTransaction();
            }
            return mockedTransaction;
        }

        public void setMockedTransaction(Transaction mockedTransaction) {
            this.mockedTransaction = mockedTransaction;
        }

        @Override
        public Connection getConnection(DataSourceConfig config) throws SQLException {
            return connectionProvider.getConnection(config);
        }

        public void setConnectionProvider(ConnectionProvider connectionProvider) {
            this.connectionProvider = connectionProvider;
        }
    }
}
