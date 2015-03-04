package com.dianping.zebra.migrate;

import com.dianping.zebra.shard.jdbc.ShardDataSource;
import org.junit.After;
import org.junit.Before;

public class MigrateWorkerTest {
    MigrateWorker migrateWorker;

    @Before
    public void init() {
        ShardDataSource shardDataSource = new ShardDataSource();


        migrateWorker = new MigrateWorker();
        migrateWorker.setShardDataSource(null);
        migrateWorker.init();
    }

    @After
    public void clean() {
        migrateWorker.close();
    }
}