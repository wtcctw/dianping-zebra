package com.dianping.zebra.migrate;

import com.dianping.zebra.shard.jdbc.ShardDataSource;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class MigrateWorker {
    private ShardDataSource shardDataSource;

    public void init() {
        if (shardDataSource == null) {
            throw new NullPointerException("ShardDataSource");
        }
        if (shardDataSource.getOriginDataSource() == null) {
            throw new NullPointerException("ShardDataSource.OriginDataSource");
        }
    }

    public void setShardDataSource(ShardDataSource shardDataSource) {
        this.shardDataSource = shardDataSource;
    }
}
