/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 *
 * File Created at 2011-6-14
 * $Id$
 *
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.shard.config;

import java.util.List;

/**
 * @author danson.liu
 */
public class RouterRuleConfig {
    private List<TableShardRuleConfig> tableShardConfigs;

    private String ruleName;

    private String oldDataSource;

    private ReadWriteStrategy readStrategy;

    private ReadWriteStrategy writeStrategy;

    public List<TableShardRuleConfig> getTableShardConfigs() {
        return tableShardConfigs;
    }

    public void setTableShardConfigs(List<TableShardRuleConfig> tableShardConfig) {
        this.tableShardConfigs = tableShardConfig;
    }

    public ReadWriteStrategy getWriteStrategy() {
        return writeStrategy;
    }

    public void setWriteStrategy(ReadWriteStrategy writeStrategy) {
        this.writeStrategy = writeStrategy;
    }

    public ReadWriteStrategy getReadStrategy() {
        return readStrategy;
    }

    public void setReadStrategy(ReadWriteStrategy readStrategy) {
        this.readStrategy = readStrategy;
    }

    public String getOldDataSource() {
        return oldDataSource;
    }

    public void setOldDataSource(String oldDataSource) {
        this.oldDataSource = oldDataSource;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
