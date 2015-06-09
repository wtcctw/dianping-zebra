package com.dianping.zebra.admin.job.executor;

import com.dianping.cat.Cat;
import com.dianping.puma.api.ConfigurationBuilder;
import com.dianping.puma.api.EventListener;
import com.dianping.puma.api.PumaClient;
import com.dianping.puma.core.event.ChangedEvent;
import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.router.RouterType;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.config.TableShardRuleConfig;
import com.dianping.zebra.shard.router.AbstractDataSourceRouterFactory;
import com.dianping.zebra.shard.router.ShardRouter;
import com.dianping.zebra.shard.router.ShardRouterImpl;
import com.dianping.zebra.shard.router.rule.DimensionRule;
import com.dianping.zebra.shard.router.rule.DimensionRuleImpl;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.TableShardRule;
import com.google.common.collect.Lists;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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

    protected static Map<String, GroupDataSource> dataSources = new ConcurrentHashMap<String, GroupDataSource>();

    public PumaClientSyncTaskExecutor(PumaClientSyncTaskEntity task) {
        this.task = task;
    }

    public synchronized void init() {
        try {
            initRule();
            initRouter();
            initDataSources();
            initPumaClient();
        } catch (Exception exp) {
            Cat.logError(exp);
        }
    }

    public synchronized void start() {
        client.start();
    }

    public synchronized void pause() {
        client.stop();
    }

    public synchronized void close() {
        client.stop();
        for (GroupDataSource ds : dataSources.values()) {
            try {
                ds.close();
            } catch (SQLException ignore) {
            }
        }
        dataSources.clear();
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
            if (!dataSources.containsKey(jdbcRef)) {
                GroupDataSource ds = initGroupDataSource(jdbcRef);
                dataSources.put(jdbcRef, ds);
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

        this.client = new PumaClient(configBuilder.build());
        this.client.register(new PumaEventListener());
        client.getSeqFileHolder().saveSeq(task.getSequence());
    }

    class PumaEventListener implements EventListener {

        @Override
        public void onEvent(ChangedEvent event) throws Exception {
            System.out.println(event.getSeq());
        }

        @Override
        public boolean onException(ChangedEvent event, Exception e) {
            return false;
        }

        @Override
        public void onConnectException(Exception e) {

        }

        @Override
        public void onConnected() {

        }

        @Override
        public void onSkipEvent(ChangedEvent event) {

        }
    }
}
