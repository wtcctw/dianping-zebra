package com.dianping.zebra.admin.job.executor;

import com.dianping.cat.Cat;
import com.dianping.puma.api.ConfigurationBuilder;
import com.dianping.puma.api.PumaClient;
import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.router.RouterType;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.config.TableShardRuleConfig;
import com.dianping.zebra.shard.router.AbstractDataSourceRouterFactory;
import com.dianping.zebra.shard.router.DataSourceRepository;
import com.dianping.zebra.shard.router.ShardRouter;
import com.dianping.zebra.shard.router.ShardRouterImpl;
import com.dianping.zebra.shard.router.rule.DimensionRule;
import com.dianping.zebra.shard.router.rule.DimensionRuleImpl;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.TableShardRule;
import com.google.common.collect.Lists;

import java.util.Map;
import java.util.Set;

/**
 * Dozer @ 6/9/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class PumaClientSyncTaskExecutor {

    private final PumaClientSyncTaskEntity task;

    protected ShardRouter shardRouter;

    protected RouterRule routerRule;

    protected PumaClient client;

    public PumaClientSyncTaskExecutor(PumaClientSyncTaskEntity task) {
        this.task = task;
    }

    public synchronized void init() {
        try {
            initRule();
            initRouter();
            initDataSources();
        } catch (Exception exp) {
            Cat.logError(exp);
        }
    }

    protected void initRouter() {
        ShardRouterImpl router = new ShardRouterImpl();
        router.setRouterRule(this.routerRule);
        router.init();
        this.shardRouter = router;
    }

    protected void initRule() {
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
        this.routerRule = AbstractDataSourceRouterFactory.build(routerRuleConfig);
    }

    protected void initDataSources() {
        TableShardRule tableShardRule = routerRule.getTableShardRules().get(task.getTable());
        for (DimensionRule dimensionRule : tableShardRule.getDimensionRules()) {
            DimensionRuleImpl dimensionRuleImpl = (DimensionRuleImpl) dimensionRule;
            initDataSources(dimensionRuleImpl.getDataSourceProvider().getAllDBAndTables());
            for (DimensionRule rule : dimensionRuleImpl.getWhiteListRules()) {
                initDataSources(rule.getAllDBAndTables());
            }
        }
    }

    protected void initDataSources(Map<String, Set<String>> all) {
        for (Map.Entry<String, Set<String>> entity : all.entrySet()) {
            String jdbcRef = entity.getKey();
            if (!DataSourceRepository.contains(jdbcRef)) {
                GroupDataSource ds = initGroupDataSource(jdbcRef);
                DataSourceRepository.put(jdbcRef, ds);
            }
        }
    }

    protected GroupDataSource initGroupDataSource(String jdbcRef) {
        GroupDataSource ds = new GroupDataSource(jdbcRef);
        ds.setRouterType(RouterType.FAIL_OVER.getRouterType());
        ds.init();
        return ds;
    }

    protected void initPumaClient() {
        ConfigurationBuilder configBuilder = new ConfigurationBuilder();

        configBuilder.dml(true).ddl(false).transaction(false).target(task.getPumaTaskName());

        String fullName = String.format("%s-%d", "PumaClientSyncTask", task.getId());
        configBuilder.name(fullName);

        configBuilder.tables(task.getPumaDatabase(), task.getPumaTables().split(","));

        //        //todo:seq ??
        //        if (task.getType() == ShardSyncTaskEntity.MIGRATE_TASK) {
        //            configBuilder.binlog(task.getBinlogName());
        //            configBuilder.binlogPos(task.getBinlogPos());
        //        } else if (task.getSeqTimestamp() != null && task.getSeqTimestamp().longValue() > 0) {
        //            configBuilder.timeStamp(task.getSeqTimestamp().longValue());
        //        }
        //
        //        PumaClient client = new PumaClient(configBuilder.build());
        //
        //        if (task.getType() == ShardSyncTaskEntity.MIGRATE_TASK) {
        //            client.getSeqFileHolder().saveSeq(SubscribeConstant.SEQ_FROM_BINLOGINFO);
        //            client.register(new Processor(fullName, Lists.newArrayList(routerForMigrate)));
        //        } else {
        //            if (client.getSeqFileHolder().getSeq() == SubscribeConstant.SEQ_FROM_OLDEST) {
        //                if (task.getSeqTimestamp() != 0) {
        //                    client.getSeqFileHolder().saveSeq(task.getSeqTimestamp());
        //                } else {
        //                    client.getSeqFileHolder().saveSeq(SubscribeConstant.SEQ_FROM_LATEST);
        //                }
        //            }
        //            client.register(new Processor(fullName, routerList));
        //        }
        //
        //        pumaClientList.put(jdbcRef, client);
        //        return client;
    }
}
