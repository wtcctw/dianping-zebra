package com.dianping.zebra.admin.job.executor;

import com.dianping.cat.Cat;
import com.dianping.zebra.admin.entity.PumaClientSyncTaskConfig;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.config.TableShardRuleConfig;
import com.dianping.zebra.shard.router.AbstractDataSourceRouterFactory;
import com.dianping.zebra.shard.router.ShardRouter;
import com.dianping.zebra.shard.router.ShardRouterImpl;
import com.google.common.collect.Lists;

/**
 * Dozer @ 6/9/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class PumaClientSyncTaskExecutor {

    final PumaClientSyncTaskConfig task;

    public PumaClientSyncTaskExecutor(PumaClientSyncTaskConfig task) {
        this.task = task;
    }

    public synchronized void init() {
        try {
            //            initAndConvertConfig();
            //            initRouterRule();
            //            initDataSources();
            //            initRouter();
            //            initPumaClient();
        } catch (Exception exp) {
            Cat.logError(exp);
        }
    }

    protected ShardRouter initRouter() {
        TableShardDimensionConfig dimensionConfig = new TableShardDimensionConfig();
        dimensionConfig.setTableName(task.getTable());
        dimensionConfig.setDbRule(task.getDbRule());
        dimensionConfig.setDbIndexes(task.getDbIndexes());
        dimensionConfig.setTbRule(task.getTbRule());
        dimensionConfig.setTbSuffix(task.getTbSuffix());
        dimensionConfig.setMaster(true);

        TableShardRuleConfig tableShardRuleConfig = new TableShardRuleConfig();
        tableShardRuleConfig.setTableName(task.getTable());
        tableShardRuleConfig.setDimensionConfigs(Lists.newArrayList(dimensionConfig));

        RouterRuleConfig routerRuleConfig = new RouterRuleConfig();
        routerRuleConfig.setTableShardConfigs(Lists.newArrayList(tableShardRuleConfig));

        ShardRouterImpl router = new ShardRouterImpl();
        router.setRouterRule(AbstractDataSourceRouterFactory.build(routerRuleConfig));
        router.init();
        return router;
    }
}
