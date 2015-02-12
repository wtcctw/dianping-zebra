package com.dianping.zebra.config;

import com.dianping.zebra.Constants;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public final class LionKey {
    private LionKey() {
    }

    public static String getShardConfigKey(String ruleName) {
        return String.format("%s.%s.%s", Constants.DEFAULT_SHARDING_PRFIX, ruleName, "shard");
    }

    public static String getShardSiwtchOnKey(String ruleName) {
        return String.format("%s.%s.%s", Constants.DEFAULT_SHARDING_PRFIX, ruleName, "switch");
    }

    public static String getShardOriginDatasourceKey(String ruleName) {
        return String.format("%s.%s.%s", Constants.DEFAULT_SHARDING_PRFIX, ruleName, "origin");
    }
}
