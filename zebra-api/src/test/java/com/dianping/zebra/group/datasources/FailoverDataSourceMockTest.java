package com.dianping.zebra.group.datasources;

import com.dianping.cat.message.Transaction;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FailoverDataSourceMockTest {

//    @Test(expected = WriteDsNotFoundException.class)
//    public void test_no_writer_exception_when_init() {
//        FailOverDataSource ds = new FailOverDataSource(null);
//    }

    /**
     * just commit the transaction when write database changed
     */
    @Test
    public void test_switch_write_db_on_cat_transaction() throws InterruptedException, SQLException {
        //mock config
        Map<String, DataSourceConfig> configs = new HashMap<String, DataSourceConfig>();
        DataSourceConfig write1 = new DataSourceConfig();
        write1.setId("db1");
        configs.put("db1", write1);
        DataSourceConfig write2 = new DataSourceConfig();
        write2.setId("db2");
        configs.put("db2", write2);

        FailOverDataSourceForTestCat mockedDs = new FailOverDataSourceForTestCat(configs);

        //mock
        Transaction t = mock(Transaction.class);
        Statement state = mock(Statement.class);
        ResultSet result = mock(ResultSet.class);
        Connection coon = mock(Connection.class);
        when(coon.createStatement()).thenReturn(state);
        when(state.executeQuery(anyString())).thenReturn(result);
        when(result.next()).thenReturn(true);
        when(result.getInt(1)).thenReturn(0);

        mockedDs.setMockedTransaction(t);
        mockedDs.setMockedConnection(coon);


        mockedDs.init();
        TimeUnit.SECONDS.sleep(1);
        //changed because the writeBs is null
        verify(t, times(1)).complete();

        TimeUnit.SECONDS.sleep(1);
        //did change because the writeBs is the same
        verify(t, times(1)).complete();
    }

    class FailOverDataSourceForTestCat extends FailOverDataSource {
        private Transaction mockedTransaction;
        private Connection mockedConnection;

        public FailOverDataSourceForTestCat(Map<String, DataSourceConfig> configs) {
            super(configs);
        }

        @Override
        protected Transaction getSwitchWriteDbTransaction() {
            return mockedTransaction;
        }

        public void setMockedTransaction(Transaction mockedTransaction) {
            this.mockedTransaction = mockedTransaction;
        }


        @Override
        public Connection getConnection(DataSourceConfig config) throws SQLException {
            return mockedConnection;
        }

        public void setMockedConnection(Connection mockedConnection) {
            this.mockedConnection = mockedConnection;
        }
    }
}
