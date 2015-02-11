/**
 * Project: ${zebra-client.aid}
 *
 * File Created at 2011-6-7 $Id$
 *
 * Copyright 2010 dianping.com. All rights reserved.
 *
 * This software is the confidential and proprietary information of Dianping
 * Company. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with dianping.com.
 */
package com.dianping.zebra.shard.jdbc;

import com.dianping.zebra.Constants;
import com.dianping.zebra.group.jdbc.AbstractDataSource;
import com.dianping.zebra.shard.router.DataSourceRouter;
import com.dianping.zebra.shard.router.DataSourceRouterFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Leo Liang
 * @author hao.zhu
 */
public class ShardDataSource extends AbstractDataSource {

    private Map<String, DataSource> dataSourcePool;

    private DataSourceRouterFactory routerFactory;

    private DataSourceRouter router;

    private volatile boolean switchOn;

    private DataSource originDataSource;

    private String ruleName;

    private String configType = Constants.CONFIG_MANAGER_TYPE_REMOTE;

    private String localConfigName;

    @Override
    public Connection getConnection() throws SQLException {
        return getConnection(null, null);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        if (switchOn) {
            ShardConnection connection = new ShardConnection(username, password);
            connection.setRouter(router);

            return connection;
        } else {
            if (originDataSource != null) {
                return originDataSource.getConnection();
            } else {
                throw new SQLException("cannot get connections from originDataSource because originDataSource is null.");
            }
        }
    }

    public void init() {
        // todo: init from config

        if (dataSourcePool == null || dataSourcePool.isEmpty()) {
            throw new IllegalArgumentException("dataSourcePool is required.");
        }
        if (routerFactory == null) {
            throw new IllegalArgumentException("routerRuleFile must be set.");
        }
        this.router = routerFactory.getRouter();
        this.router.setDataSourcePool(dataSourcePool);
        this.router.init();
    }

    public void setDataSourcePool(Map<String, DataSource> dataSourcePool) {
        this.dataSourcePool = dataSourcePool;
    }

    public void setRouterFactory(DataSourceRouterFactory routerFactory) {
        this.routerFactory = routerFactory;
    }

    public boolean isSwitchOn() {
        return switchOn;
    }

    public void setSwitchOn(boolean switchOn) {
        this.switchOn = switchOn;
    }

    public DataSource getOriginDataSource() {
        return originDataSource;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public void setOriginDataSource(DataSource originDataSource) {
        this.originDataSource = originDataSource;
    }

    public void setLocalConfigName(String localConfigName) {
        this.localConfigName = localConfigName;
    }
}
