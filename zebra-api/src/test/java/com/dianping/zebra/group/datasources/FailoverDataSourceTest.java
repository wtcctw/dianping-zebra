package com.dianping.zebra.group.datasources;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import javafx.scene.transform.Translate;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;

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


    class ConnectionAnswer implements Answer<Connection> {
        private Connection coon;

        @Override
        public Connection answer(InvocationOnMock invocation) throws Throwable {
            return coon;
        }

        public void setCoon(Connection coon) {
            this.coon = coon;
        }
    }

    @Test
    public void test_how_switch() throws SQLException, InterruptedException {
        FailOverDataSource ds = new FailOverDataSource(configs);
        FailOverDataSource.WriterDataSourceMonitor monitor = spy(ds.new WriterDataSourceMonitor());

        ConnectionAnswer connectionAnswer = new ConnectionAnswer();
        connectionAnswer.setCoon(coon);

        //use the db1
        doAnswer(connectionAnswer).when(monitor).getConnection(configs.get("db1"));
        doReturn(coon).when(monitor).getConnection(configs.get("db2"));

        new Thread(monitor).start();

        TimeUnit.MILLISECONDS.sleep(1900);
        Assert.assertEquals("db1", ds.getCurrentDataSourceMBean().getId());

        verify(coon, only()).createStatement();
        verify(readOnlyCoon, never()).createStatement();

        //fail over db1
        connectionAnswer.setCoon(readOnlyCoon);

        TimeUnit.MILLISECONDS.sleep(3500);
        Assert.assertEquals("db2", ds.getCurrentDataSourceMBean().getId());
        verify(coon, times(3)).createStatement();
        verify(readOnlyCoon, times(2)).createStatement();
    }

    @Test
    public void test_find_write_data_source1() throws Exception {
        FailOverDataSource ds = new FailOverDataSource(configs);
        FailOverDataSource.WriterDataSourceMonitor monitor = spy(ds.new WriterDataSourceMonitor());

        doReturn(coon).when(monitor).getConnection(any(DataSourceConfig.class));

        monitor.findWriteDataSource();

        Assert.assertEquals(ds.getCurrentDataSourceMBean().getId(), "db1");
        verify(coon, only()).createStatement();
    }

    @Test
    public void test_find_write_data_source2() throws Exception {
        FailOverDataSource ds = new FailOverDataSource(configs);
        FailOverDataSource.WriterDataSourceMonitor monitor = spy(ds.new WriterDataSourceMonitor());

        doReturn(readOnlyCoon).when(monitor).getConnection(configs.get("db1"));
        doReturn(coon).when(monitor).getConnection(configs.get("db2"));

        monitor.findWriteDataSource();

        Assert.assertEquals(ds.getCurrentDataSourceMBean().getId(), "db2");

        verify(coon, only()).createStatement();
        verify(readOnlyCoon, only()).createStatement();
    }

    @Test
    public void test_check_write_data_source_result_ok() throws Exception {
        FailOverDataSource ds = new FailOverDataSource(configs);
        FailOverDataSource.WriterDataSourceMonitor monitor = spy(ds.new WriterDataSourceMonitor());

        doReturn(coon).when(monitor).getConnection(any(DataSourceConfig.class));
        Assert.assertEquals(monitor.checkWriteDataSource(configs.get("db1")), FailOverDataSource.CheckWriteDataSourceResult.OK);
        verify(coon, only()).createStatement();
    }

    @Test
    public void test_check_write_data_source_result_readonly() throws Exception {
        FailOverDataSource ds = new FailOverDataSource(configs);
        FailOverDataSource.WriterDataSourceMonitor monitor = spy(ds.new WriterDataSourceMonitor());

        doReturn(readOnlyCoon).when(monitor).getConnection(any(DataSourceConfig.class));
        Assert.assertEquals(monitor.checkWriteDataSource(configs.get("db1")), FailOverDataSource.CheckWriteDataSourceResult.READ_ONLY);
        verify(readOnlyCoon, only()).createStatement();
    }

    @Test
    public void test_check_write_data_source_result_error() throws Exception {
        FailOverDataSource ds = new FailOverDataSource(configs);
        FailOverDataSource.WriterDataSourceMonitor monitor = spy(ds.new WriterDataSourceMonitor());

        Connection errorCoon = mock(Connection.class);
        when(errorCoon.createStatement()).thenThrow(new SQLException());

        doReturn(errorCoon).when(monitor).getConnection(any(DataSourceConfig.class));
        Assert.assertEquals(monitor.checkWriteDataSource(configs.get("db1")), FailOverDataSource.CheckWriteDataSourceResult.ERROR);
        verify(errorCoon, only()).createStatement();
    }

//    @Test
//    public void test_use_auto_find_write_db() throws InterruptedException, SQLException {
//        FailOverDataSourceForTestCat mockedDs = new FailOverDataSourceForTestCat(configs);
//
//        mockedDs.setConnectionProvider(new ConnectionProvider() {
//            @Override
//            public Connection getConnection(DataSourceConfig config) {
//                return coon;
//            }
//        });
//
//        mockedDs.init();
//        TimeUnit.MILLISECONDS.sleep(1200);
//        Assert.assertEquals("db1", mockedDs.getCurrentDataSourceMBean().getId());
//
//        mockedDs.setConnectionProvider(new ConnectionProvider() {
//            @Override
//            public Connection getConnection(DataSourceConfig config) {
//                if (config.getId().equals("db1")) {
//                    return readOnlyCoon;
//                }
//                return coon;
//            }
//        });
//        TimeUnit.MILLISECONDS.sleep(1200);
//        Assert.assertEquals("db2", mockedDs.getCurrentDataSourceMBean().getId());
//    }
//
//
//    @Test(expected = WriteDsNotFoundException.class)
//    public void test_use_no_write_db() throws InterruptedException, SQLException {
//        FailOverDataSourceForTestCat mockedDs = new FailOverDataSourceForTestCat(configs);
//        mockedDs.setConnectionProvider(new ConnectionProvider() {
//            @Override
//            public Connection getConnection(DataSourceConfig config) {
//                return readOnlyCoon;
//            }
//        });
//        mockedDs.initFailFast();
//    }
//
//
//    @Test
//    public void test_use_default_write_db() throws InterruptedException, SQLException {
//        FailOverDataSourceForTestCat mockedDs = new FailOverDataSourceForTestCat(configs);
//        mockedDs.setConnectionProvider(new ConnectionProvider() {
//            @Override
//            public Connection getConnection(DataSourceConfig config) {
//                return coon;
//            }
//        });
//
//        mockedDs.init();
//        TimeUnit.SECONDS.sleep(1);
//        Assert.assertEquals("db1", mockedDs.getCurrentDataSourceMBean().getId());
//    }
//
//
//    /**
//     * just commit the transaction when write database changed
//     */
//    @Test
//    @Ignore
//    public void test_switch_write_db_on_cat_transaction() throws InterruptedException, SQLException {
//        Transaction t = mock(Transaction.class);
//        FailOverDataSourceForTestCat mockedDs = new FailOverDataSourceForTestCat(configs);
//        mockedDs.setMockedTransaction(t);
//        mockedDs.setConnectionProvider(new ConnectionProvider() {
//            @Override
//            public Connection getConnection(DataSourceConfig config) {
//                return coon;
//            }
//        });
//
//        mockedDs.init();
//        TimeUnit.SECONDS.sleep(1);
//        //changed because the writeBs is null
//        TimeUnit.MILLISECONDS.sleep(1200);
//
//        TimeUnit.SECONDS.sleep(1);
//        //did change because the writeBs is the same
//        TimeUnit.MILLISECONDS.sleep(1200);
//
//        mockedDs.setConnectionProvider(new ConnectionProvider() {
//            @Override
//            public Connection getConnection(DataSourceConfig config) {
//                if (config.getId().equals("db1")) {
//                    return readOnlyCoon;
//                }
//                return coon;
//            }
//        });
//        TimeUnit.MILLISECONDS.sleep(1200);
//        //changed the writeBs and fire the transaction again
//        verify(t, times(2)).complete();
//    }
//
//    interface ConnectionProvider {
//        Connection getConnection(DataSourceConfig config);
//    }
//
//    class FailOverDataSourceForTestCat extends FailOverDataSource {
//        private Transaction mockedTransaction;
//        private ConnectionProvider connectionProvider;
//
//        public FailOverDataSourceForTestCat(Map<String, DataSourceConfig> configs) {
//            super(configs);
//        }
//
}