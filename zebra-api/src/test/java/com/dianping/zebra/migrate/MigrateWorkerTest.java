package com.dianping.zebra.migrate;

import com.dianping.zebra.config.ConfigService;
import com.dianping.zebra.config.LionKey;
import com.dianping.zebra.group.config.DefaultDataSourceConfigManager;
import com.dianping.zebra.group.config.SystemConfigManagerFactory;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.shard.jdbc.ShardDataSource;
import com.dianping.zebra.util.ConfigServiceMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class MigrateWorkerTest {
    MigrateWorker migrateWorker;
    ShardDataSource shardDataSource;
    ConfigServiceMock configService;

    private final String rule = "test";

    @Before
    public void init() {
        initConfigs();

        shardDataSource = new ShardDataSource();
        shardDataSource.setRuleName(rule);
        shardDataSource.setConfigService(configService);

        initDataSourcePool();

        shardDataSource.init();

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

    }

    public void initDataSourcePool() {
        GroupDataSourceExtend dsOrigin = new GroupDataSourceExtend();
        dsOrigin.setJdbcRef("test");
        dsOrigin.setDataSourceConfigManager(configService);
        dsOrigin.init();

        GroupDataSourceExtend ds0 = new GroupDataSourceExtend();
        ds0.setJdbcRef("test0");
        ds0.setDataSourceConfigManager(configService);
        ds0.init();

        GroupDataSourceExtend ds1 = new GroupDataSourceExtend();
        ds1.setJdbcRef("test1");
        ds1.setDataSourceConfigManager(configService);
        ds1.init();

        shardDataSource.setOriginDataSource(dsOrigin);
        Map<String, DataSource> dsPool = new HashMap<String, DataSource>();
        dsPool.put("test0", ds0);
        dsPool.put("test1", ds1);
        shardDataSource.setDataSourcePool(dsPool);
    }

    public void initConfigs() {
        configService = new ConfigServiceMock();
        configService
                .addProperty(LionKey.getShardConfigKey(rule), "{\"tableShardConfigs\":[{\"tableName\":\"test\",\"dimensionConfigs\":[{\"dbRule\":\"(#id# % 2)\",\"dbIndexes\":\"test0,test1\",\"tbRule\":\"((#id# / 2).toLong() % 2)\",\"tbSuffix\":\"everydb:[_0,_1]\",\"isMaster\":true,\"exceptions\":[]}],\"generatedPK\":\"pk\"}]}")
                .addProperty(LionKey.getShardOriginDatasourceKey(rule), "test")
                .addProperty(LionKey.getGroupDsConfigKey("test"), "(test-1),(test-1:1)") // origin
                .addProperty(LionKey.getGroupDsConfigKey("test0"), "(test0-1),(test0-1:1)") // shard1
                .addProperty(LionKey.getGroupDsConfigKey("test1"), "(test1-1),(test1-1:1)") // shard2

                .addProperty(LionKey.getDsUsernameConfigKey("test-1"), "sa") // origin test-1
                .addProperty(LionKey.getDsPasswordConfigKey("test-1"), "") // origin test-1
                .addProperty(LionKey.getDsJdbcUrlConfigKey("test-1"), "jdbc:h2:mem:test;MVCC=TRUE") // origin test-1
                .addProperty(LionKey.getDsDriverClassConfigKey("test-1"), "org.h2.Driver") // origin test-1
                .addProperty(LionKey.getDsActiveConfigKey("test-1"), "true") // origin test-1
                .addProperty("ds.test-1.jdbc.testReadOnlySql", "call readonly()") // origin test-1

                .addProperty(LionKey.getDsUsernameConfigKey("test0-1"), "sa") // origin test-1
                .addProperty(LionKey.getDsPasswordConfigKey("test0-1"), "") // origin test-1
                .addProperty(LionKey.getDsJdbcUrlConfigKey("test0-1"), "jdbc:h2:mem:test0;MVCC=TRUE") // origin test-1
                .addProperty(LionKey.getDsDriverClassConfigKey("test0-1"), "org.h2.Driver") // origin test-1
                .addProperty(LionKey.getDsActiveConfigKey("test0-1"), "true") // origin test-1
                .addProperty("ds.test0-1.jdbc.testReadOnlySql", "call readonly()") // origin test-1

                .addProperty(LionKey.getDsUsernameConfigKey("test1-1"), "sa") // origin test-1
                .addProperty(LionKey.getDsPasswordConfigKey("test1-1"), "") // origin test-1
                .addProperty(LionKey.getDsJdbcUrlConfigKey("test1-1"), "jdbc:h2:mem:test1;MVCC=TRUE") // origin test-1
                .addProperty(LionKey.getDsDriverClassConfigKey("test1-1"), "org.h2.Driver") // origin test-1
                .addProperty(LionKey.getDsActiveConfigKey("test1-1"), "true") // origin test-1
                .addProperty("ds.test1-1.jdbc.testReadOnlySql", "call readonly()") // origin test-1
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