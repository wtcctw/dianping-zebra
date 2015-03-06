package com.dianping.zebra.migrate;

import com.dianping.zebra.config.ConfigService;
import com.dianping.zebra.config.LionKey;
import com.dianping.zebra.group.config.DefaultDataSourceConfigManager;
import com.dianping.zebra.group.config.SystemConfigManagerFactory;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.migrate.task.TaskConfig;
import com.dianping.zebra.shard.jdbc.ShardDataSource;
import com.dianping.zebra.util.ConfigServiceMock;
import com.dianping.zebra.util.SqlExecuteHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MigrateWorkerTest {
    MigrateWorker migrateWorker;
    ShardDataSource shardDataSource;
    ConfigServiceMock configService;
    GroupDataSourceExtend dsOrigin;
    GroupDataSourceExtend ds0;
    GroupDataSourceExtend ds1;

    private final String rule = "test_shard";

    @Before
    public void init() throws SQLException {
        initConfigs();

        shardDataSource = new ShardDataSource();
        shardDataSource.setRuleName(rule);
        shardDataSource.setConfigService(configService);

        initDataSourcePool();

        shardDataSource.init();

        createTableData();
        initData();


        migrateWorker = new MigrateWorker();
        migrateWorker.setShardDataSource(shardDataSource);
    }


    @After
    public void clean() {
        if (migrateWorker != null) {
            migrateWorker.close();
        }
    }

    @Test
    public void test_init() {
        MigrateWorker.MigrateWorkerRunner runner = migrateWorker.new MigrateWorkerRunner();
        runner.processMigrate(new TaskConfig()
                        .setTaskId(1)
                        .setTableName("test_shard")
                        .setKeyStart(1)
                        .setKeyEnd(100)
                        .setPageSize(10)
                        .setKeyName("id")
        );
    }

    public void createTableData() throws SQLException {
        String createOrigin = "CREATE TABLE test_shard(" +
                "id INT," +
                "name VARCHAR(255)" +
                ")";

        String create0 = "CREATE TABLE test_shard0(" +
                "id INT," +
                "name VARCHAR(255)" +
                ")";
        String create1 = "CREATE TABLE test_shard1(" +
                "id INT," +
                "name VARCHAR(255)" +
                ")";

        SqlExecuteHelper.executeUpdate(dsOrigin.getConnection(), createOrigin);
        SqlExecuteHelper.executeUpdate(ds0.getConnection(), create0);
        SqlExecuteHelper.executeUpdate(ds0.getConnection(), create1);
        SqlExecuteHelper.executeUpdate(ds1.getConnection(), create0);
        SqlExecuteHelper.executeUpdate(ds1.getConnection(), create1);
    }

    public void initData() throws SQLException {
        for (int k = 1; k <= 100; k++) {
            SqlExecuteHelper.executeUpdate(
                    dsOrigin.getConnection(),
                    String.format("INSERT INTO test_shard (id,name) values (%d,'%d')", k, k));
        }
    }

    public void initDataSourcePool() {
        dsOrigin = new GroupDataSourceExtend();
        dsOrigin.setJdbcRef("test_shard");
        dsOrigin.setDataSourceConfigManager(configService);
        dsOrigin.init();

        ds0 = new GroupDataSourceExtend();
        ds0.setJdbcRef("test_shard0");
        ds0.setDataSourceConfigManager(configService);
        ds0.init();

        ds1 = new GroupDataSourceExtend();
        ds1.setJdbcRef("test_shard1");
        ds1.setDataSourceConfigManager(configService);
        ds1.init();

        shardDataSource.setOriginDataSource(dsOrigin);
        Map<String, DataSource> dsPool = new HashMap<String, DataSource>();
        dsPool.put("test_shard0", ds0);
        dsPool.put("test_shard1", ds1);
        shardDataSource.setDataSourcePool(dsPool);
    }

    public void initConfigs() {
        configService = new ConfigServiceMock();
        configService
                .addProperty(LionKey.getShardConfigKey(rule), "{\"tableShardConfigs\":[{\"tableName\":\"test_shard\",\"dimensionConfigs\":[{\"dbRule\":\"(#id# % 2)\",\"dbIndexes\":\"test_shard0,test_shard1\",\"tbRule\":\"((#id# / 2).toLong() % 2)\",\"tbSuffix\":\"everydb:[0,1]\",\"isMaster\":true,\"exceptions\":[]}],\"generatedPK\":\"pk\"}]}")
                .addProperty(LionKey.getShardOriginDatasourceKey(rule), "test")
                .addProperty(LionKey.getGroupDsConfigKey("test_shard"), "(test_shard-1),(test_shard-1:1)") // origin
                .addProperty(LionKey.getGroupDsConfigKey("test_shard0"), "(test_shard0-1),(test_shard0-1:1)") // shard1
                .addProperty(LionKey.getGroupDsConfigKey("test_shard1"), "(test_shard1-1),(test_shard1-1:1)") // shard2

                .addProperty(LionKey.getDsUsernameConfigKey("test_shard-1"), "sa") // origin test-1
                .addProperty(LionKey.getDsPasswordConfigKey("test_shard-1"), "") // origin test-1
                .addProperty(LionKey.getDsJdbcUrlConfigKey("test_shard-1"), "jdbc:h2:mem:test;MVCC=TRUE") // origin test-1
                .addProperty(LionKey.getDsDriverClassConfigKey("test_shard-1"), "org.h2.Driver") // origin test-1
                .addProperty(LionKey.getDsActiveConfigKey("test_shard-1"), "true") // origin test-1
                .addProperty("ds.test_shard-1.jdbc.testReadOnlySql", "call readonly()") // origin test-1

                .addProperty(LionKey.getDsUsernameConfigKey("test_shard0-1"), "sa") // origin test-1
                .addProperty(LionKey.getDsPasswordConfigKey("test_shard0-1"), "") // origin test-1
                .addProperty(LionKey.getDsJdbcUrlConfigKey("test_shard0-1"), "jdbc:h2:mem:test0;MVCC=TRUE") // origin test-1
                .addProperty(LionKey.getDsDriverClassConfigKey("test_shard0-1"), "org.h2.Driver") // origin test-1
                .addProperty(LionKey.getDsActiveConfigKey("test_shard0-1"), "true") // origin test-1
                .addProperty("ds.test_shard0-1.jdbc.testReadOnlySql", "call readonly()") // origin test-1

                .addProperty(LionKey.getDsUsernameConfigKey("test_shard1-1"), "sa") // origin test-1
                .addProperty(LionKey.getDsPasswordConfigKey("test_shard1-1"), "") // origin test-1
                .addProperty(LionKey.getDsJdbcUrlConfigKey("test_shard1-1"), "jdbc:h2:mem:test1;MVCC=TRUE") // origin test-1
                .addProperty(LionKey.getDsDriverClassConfigKey("test_shard1-1"), "org.h2.Driver") // origin test-1
                .addProperty(LionKey.getDsActiveConfigKey("test_shard1-1"), "true") // origin test-1
                .addProperty("ds.test_shard1-1.jdbc.testReadOnlySql", "call readonly()") // origin test-1
        ;
    }

    public class GroupDataSourceExtend extends GroupDataSource {
        @Override
        protected void initConfig() {
            this.groupConfig = buildGroupConfig();
            this.systemConfigManager = SystemConfigManagerFactory.getConfigManger(configManagerType);
        }

        public void setDataSourceConfigManager(ConfigService configService) {
            this.dataSourceConfigManager = new DefaultDataSourceConfigManager(jdbcRef, configService);
            this.dataSourceConfigManager.init();
        }
    }
}