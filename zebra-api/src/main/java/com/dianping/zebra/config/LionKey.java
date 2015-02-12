package com.dianping.zebra.config;

import com.dianping.zebra.Constants;
import com.dianping.zebra.util.StringUtils;

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

    public static boolean isShardConfigKey(String key){
        return StringUtils.isNotBlank(key) && key.endsWith("shard");
    }

    public static String getRuleNameFromShardKey(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        String[] array = key.split(".");
        if (array.length != 3) {
            return null;
        }
        return array[1];
    }
}
