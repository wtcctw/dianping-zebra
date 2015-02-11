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

import com.dianping.zebra.group.jdbc.AbstractDataSource;
import com.dianping.zebra.shard.router.DataSourceRouter;
import com.dianping.zebra.shard.router.DataSourceRouterFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;

/**
 * @author Leo Liang
 * @author hao.zhu
 */
public class ShardDataSource extends AbstractDataSource {

    private Map<String, DataSource> dataSourcePool;

    private DataSourceRouterFactory routerFactory;

    private DataSourceRouter router;

    private boolean switchOn;

    private DataSource originDataSource;

    private String ruleName;

    @Override
    public Connection getConnection() {
        return getConnection(null, null);
    }

    @Override
    public Connection getConnection(String username, String password) {
        ShardConnection connection = new ShardConnection(username, password);
        connection.setRouter(router);

        return connection;
    }

    public void init() {
        //todo: init from config

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

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
