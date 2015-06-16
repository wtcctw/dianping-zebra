package com.dianping.zebra.admin.job.executor;

import com.dianping.cat.Cat;
import com.dianping.puma.api.ConfigurationBuilder;
import com.dianping.puma.api.EventListener;
import com.dianping.puma.api.PumaClient;
import com.dianping.puma.core.event.ChangedEvent;
import com.dianping.puma.core.event.RowChangedEvent;
import com.dianping.puma.core.util.sql.DMLType;
import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.admin.exception.NoRowsAffectedException;
import com.dianping.zebra.admin.util.ColumnInfoWrap;
import com.dianping.zebra.admin.util.SqlBuilder;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.router.RouterType;
import com.dianping.zebra.shard.router.rule.DataSourceBO;
import com.dianping.zebra.shard.router.rule.SimpleDataSourceProvider;
import com.dianping.zebra.shard.router.rule.engine.GroovyRuleEngine;
import com.dianping.zebra.shard.router.rule.engine.RuleEngineEvalContext;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Dozer @ 6/9/15 mail@dozer.cc http://www.dozer.cc
 */
public class ShardSyncTaskExecutor implements TaskExecutor {
	private final PumaClientSyncTaskEntity task;

	public final AtomicLong hitTimes = new AtomicLong();

	public final static int MAX_TRY_TIMES = 10;

	public final static int THREADS_PER_DS = 5;

	public final static int MAX_QUEUE_SIZE = 10000;

	protected GroovyRuleEngine engine;

	protected SimpleDataSourceProvider dataSourceProvider;

	protected PumaClient client;

	protected Map<String, GroupDataSource> dataSources;

	protected Map<String, JdbcTemplate> templateMap;

	protected Map<String, BlockingQueue<RowChangedEvent>[]> eventQueues;

	protected List<RowEventProcessor> rowEventProcessers;

	protected List<Thread> rowEventProcesserThreads;

	public ShardSyncTaskExecutor(PumaClientSyncTaskEntity task) {
		this.task = task;
	}

	public synchronized void init() {
		initRouter();
		initDataSources();
		initJdbcTemplate();
		initPumaClient();
		initEventQueues();
		initProcessors();
	}

	public synchronized void start() {
		if (client != null || rowEventProcesserThreads != null) {
			throw new IllegalStateException("You should init before start!");
		}

		client.start();
		for (Thread thread : rowEventProcesserThreads) {
			thread.start();
		}
	}

	public synchronized void stop() {
		if (client != null) {
			client.stop();
		}

		if (dataSources != null) {
			for (GroupDataSource ds : dataSources.values()) {
				try {
					ds.close();
				} catch (SQLException ignore) {
				}
			}
			dataSources.clear();
		}

		if (rowEventProcesserThreads != null) {
			for (Thread thread : rowEventProcesserThreads) {
				thread.interrupt();
			}
		}
	}

	protected void initProcessors() {
		ImmutableList.Builder<RowEventProcessor> processorBuilder = ImmutableList.builder();
		ImmutableList.Builder<Thread> threadBuilder = ImmutableList.builder();

		for (Map.Entry<String, BlockingQueue<RowChangedEvent>[]> entry : eventQueues.entrySet()) {

			for (int k = 0; k < entry.getValue().length; k++) {
				BlockingQueue<RowChangedEvent> queue = entry.getValue()[k];
				RowEventProcessor processor = new RowEventProcessor(queue, templateMap.get(entry.getKey()));
				Thread thread = new Thread(processor);
				thread.setDaemon(true);
				thread.setName(String.format("RowEventProcessor-%s-%d", entry.getKey(), k));

				processorBuilder.add(processor);
				threadBuilder.add(thread);
			}
		}

		this.rowEventProcessers = processorBuilder.build();
		this.rowEventProcesserThreads = threadBuilder.build();
	}

	protected void initEventQueues() {
		ImmutableMap.Builder<String, BlockingQueue<RowChangedEvent>[]> queueBuilder = ImmutableMap.builder();

		for (String name : dataSources.keySet()) {
			BlockingQueue<RowChangedEvent>[] arrays = new BlockingQueue[THREADS_PER_DS];
			for (int k = 0; k < arrays.length; k++) {
				arrays[k] = new LinkedBlockingQueue<RowChangedEvent>();
			}
			queueBuilder.put(name, arrays);
		}

		this.eventQueues = queueBuilder.build();
	}

	protected void initRouter() {
		this.engine = new GroovyRuleEngine(task.getDbRule());
		this.dataSourceProvider = new SimpleDataSourceProvider(task.getTableName(), task.getDbIndexes(),
			task.getTbSuffix(), task.getTbRule());
	}

	protected void initDataSources() {
		Map<String, GroupDataSource> temp = new HashMap<String, GroupDataSource>();
		for (Map.Entry<String, Set<String>> entity : dataSourceProvider.getAllDBAndTables().entrySet()) {
			String jdbcRef = entity.getKey();
			if (!temp.containsKey(jdbcRef)) {
				GroupDataSource ds = initGroupDataSource(jdbcRef);
				temp.put(jdbcRef, ds);
			}
		}
		this.dataSources = ImmutableMap.copyOf(temp);
	}

	protected void initJdbcTemplate() {
		ImmutableMap.Builder<String, JdbcTemplate> builder = ImmutableMap.builder();

		for (Map.Entry<String, GroupDataSource> entry : dataSources.entrySet()) {
			builder.put(entry.getKey(), new JdbcTemplate(entry.getValue()));
		}

		this.templateMap = builder.build();
	}

	protected GroupDataSource initGroupDataSource(String jdbcRef) {
		GroupDataSource ds = new GroupDataSource(jdbcRef);

		ds.setRouterType(RouterType.FAIL_OVER.getRouterType());
		ds.setFilter("!cat");
		ds.init();

		return ds;
	}

	protected void initPumaClient() {
		String fullName = String.format("%s-%d", "PumaClientSyncTask", task.getId());

		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.dml(true).ddl(false).transaction(false).target(task.getPumaTaskName()).name(fullName)
			.tables(task.getPumaDatabase(), task.getPumaTables().split(","));

		this.client = new PumaClient(configBuilder.build());

		this.client.register(new PumaEventListener());
	}

	class RowEventProcessor implements Runnable {

		private final BlockingQueue<RowChangedEvent> queue;

		private final JdbcTemplate template;

		public RowEventProcessor(BlockingQueue<RowChangedEvent> queue, JdbcTemplate template) {
			this.queue = queue;
			this.template = template;
		}

		public long getLastSuccessSequence() {
			return 0;
		}

		@Override
		public void run() {

		}
	}

	class PumaEventListener implements EventListener {
		protected volatile long tryTimes = 0;

		@Override
		public void onEvent(ChangedEvent event) throws Exception {
			hitTimes.incrementAndGet();
			tryTimes++;
			onEventInternal(event);
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

			processPK(rowEvent);

			ColumnInfoWrap column = new ColumnInfoWrap(rowEvent);
			RuleEngineEvalContext context = new RuleEngineEvalContext(column);
			Number index = (Number) engine.eval(context);
			DataSourceBO bo = dataSourceProvider.getDataSource(index.intValue());
			String table = bo.evalTable(context);

			rowEvent.setTable(table);
			rowEvent.setDatabase("");
			String sql = SqlBuilder.buildSql(rowEvent);
			Object[] args = SqlBuilder.buildArgs(rowEvent);

			int rows = templateMap.get(bo.getDbIndex()).update(sql, args);
			if (rows == 0 && RowChangedEvent.UPDATE == rowEvent.getActionType()) {
				throw new NoRowsAffectedException();
			}
		}

		protected void processPK(RowChangedEvent rowEvent) {
			Set<String> needToRemoveKeys = new HashSet<String>();
			for (Map.Entry<String, RowChangedEvent.ColumnInfo> info : rowEvent.getColumns().entrySet()) {
				if (info.getValue().isKey() && !task.getPk().equals(info.getKey())) {
					needToRemoveKeys.add(info.getKey());
				}
			}

			for (String key : needToRemoveKeys) {
				rowEvent.getColumns().remove(key);
			}

			rowEvent.getColumns().get(task.getPk()).setKey(true);
		}

		@Override
		public boolean onException(ChangedEvent event, Exception e) {
			Cat.logError(e);

			if (hitTimes.get() > MAX_TRY_TIMES) {
				return true;
			}

			RowChangedEvent rowEvent = (RowChangedEvent) event;

			if (e instanceof DuplicateKeyException) {
				rowEvent.setDmlType(DMLType.UPDATE);

				return false;
			} else if (e instanceof NoRowsAffectedException) {
				rowEvent.setDmlType(DMLType.INSERT);

				return false;
			} else {
				// 不断重试，随着重试次数增多，sleep 时间增加
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

	public Map<String, String> getStatus() {
		Map<String, String> result = new HashMap<String, String>();
		result.put(String.format("PumaTask-%d", task.getId()), String.valueOf(hitTimes.getAndSet(0)));
		return result;
	}
}
