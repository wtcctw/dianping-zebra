package com.dianping.zebra.admin.job.executor;

import com.dianping.cat.Cat;
import com.dianping.puma.api.ConfigurationBuilder;
import com.dianping.puma.api.EventListener;
import com.dianping.puma.api.PumaClient;
import com.dianping.puma.core.event.ChangedEvent;
import com.dianping.puma.core.event.RowChangedEvent;
import com.dianping.puma.core.util.sql.DMLType;
import com.dianping.zebra.admin.dao.PumaClientSyncTaskMapper;
import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.admin.exception.NoRowsAffectedException;
import com.dianping.zebra.admin.util.SqlGenerator;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.router.RouterType;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.config.TableShardRuleConfig;
import com.dianping.zebra.shard.router.*;
import com.dianping.zebra.shard.router.rule.DimensionRule;
import com.dianping.zebra.shard.router.rule.DimensionRuleImpl;
import com.dianping.zebra.shard.router.rule.RouterRule;
import com.dianping.zebra.shard.router.rule.TableShardRule;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Dozer @ 6/9/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class PumaClientSyncTaskExecutor implements TaskExecutor {
	private PumaClientSyncTaskMapper pumaClientSyncTaskMapper;

	private final PumaClientSyncTaskEntity task;

	protected ShardRouter shardRouter;

	protected RouterRule routerRule;

	protected PumaClient client;

	protected Thread taskSequenceUploader;

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
			initSequenceUploader();
		} catch (Exception exp) {
			Cat.logError(exp);
		}
	}

	public synchronized void start() {
		client.start();
		taskSequenceUploader.start();
	}

	public synchronized void pause() {
		client.stop();
		taskSequenceUploader.interrupt();
	}

	public synchronized void stop() {
		if (client != null) {
			client.stop();
		}
		if (taskSequenceUploader != null) {
			taskSequenceUploader.interrupt();
		}
		for (GroupDataSource ds : dataSources.values()) {
			try {
				ds.close();
			} catch (SQLException ignore) {
			}
		}
		dataSources.clear();
	}

	protected void initSequenceUploader() {
		taskSequenceUploader = new Thread(new TaskSequenceUploader());
		taskSequenceUploader.setName("TaskSequenceUploader");
		taskSequenceUploader.setDaemon(true);
	}

	protected void initRouter() {
		ShardRouterImpl router = new ShardRouterImpl();
		router.setRouterRule(this.routerRule);
		router.init();
		this.shardRouter = router;
	}

	protected void initRule() {
		TableShardDimensionConfig dimensionConfig = new TableShardDimensionConfig();
		dimensionConfig.setTableName(task.getTableName());
		dimensionConfig.setDbRule(task.getDbRule());
		dimensionConfig.setDbIndexes(task.getDbIndexes());
		dimensionConfig.setTbRule(task.getTbRule());
		dimensionConfig.setTbSuffix(task.getTbSuffix());
		dimensionConfig.setMaster(true);

		TableShardRuleConfig tableShardRuleConfig = new TableShardRuleConfig();
		tableShardRuleConfig.setTableName(task.getTableName());
		tableShardRuleConfig.setDimensionConfigs(Lists.newArrayList(dimensionConfig));

		RouterRuleConfig routerRuleConfig = new RouterRuleConfig();
		routerRuleConfig.setTableShardConfigs(Lists.newArrayList(tableShardRuleConfig));
		this.routerRule = AbstractDataSourceRouterFactory.build(routerRuleConfig);
	}

	protected void initDataSources() {
		TableShardRule tableShardRule = routerRule.getTableShardRules().get(task.getTableName());
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
		ds.setFilter("!cat");
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

	class TaskSequenceUploader implements Runnable {
		@Override
		public void run() {
			long lastSeq = 0;

			while (true) {
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {
					break;
				}

				if (lastSeq == task.getSequence()) {
					continue;
				}

				try {
					lastSeq = task.getSequence();
					pumaClientSyncTaskMapper.updateSequence(task);
				} catch (Exception e) {
					Cat.logError(e);
				}
			}
		}
	}

	class PumaEventListener implements EventListener {
		protected volatile long tryTimes = 0;

		@Override
		public void onEvent(ChangedEvent event) throws Exception {
			tryTimes++;
			onEventInternal(event);
			task.setSequence(event.getSeq());
			tryTimes = 0;
		}

		protected void onEventInternal(ChangedEvent event) {
			if (!(event instanceof RowChangedEvent)) {
				return;
			}
			RowChangedEvent rowEvent = (RowChangedEvent) event;
			if (rowEvent.isTransactionBegin() || rowEvent.isTransactionCommit()) {
				return;
			}

			String tempSql;
			Object[] args;
			rowEvent.setTable(task.getTableName());
			rowEvent.setDatabase("");

			tempSql = SqlGenerator.parseSql(rowEvent);
			args = SqlGenerator.parseArgs(rowEvent);

			if (Strings.isNullOrEmpty(tempSql)) {
				return;
			}
			
			RouterResult routerTarget = shardRouter.router(tempSql, Lists.newArrayList(args));

			for (RouterTarget targetedSql : routerTarget.getTargetedSqls()) {
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSources.get(targetedSql.getDataSourceName()));
				for (String sql : targetedSql.getSqls()) {
					int rows = jdbcTemplate.update(sql, args);
					if (rows == 0 && RowChangedEvent.UPDATE == rowEvent.getActionType()) {
						throw new NoRowsAffectedException();
					}
				}
			}
		}

		@Override
		public boolean onException(ChangedEvent event, Exception e) {
			Cat.logError(e);

			RowChangedEvent rowEvent = (RowChangedEvent) event;

			if (e instanceof DuplicateKeyException) {
				rowEvent.setDmlType(DMLType.UPDATE);
				return false;
			} else if (e instanceof NoRowsAffectedException) {
				rowEvent.setDmlType(DMLType.INSERT);
				return false;
			} else {
				//不断重试，随着重试次数增多，sleep 时间增加
				try {
					Thread.sleep(tryTimes);
				} catch (InterruptedException ignore) {
					return true;
				}
				return false;
			}
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

	public void setPumaClientSyncTaskMapper(PumaClientSyncTaskMapper pumaClientSyncTaskMapper) {
		this.pumaClientSyncTaskMapper = pumaClientSyncTaskMapper;
	}
}
