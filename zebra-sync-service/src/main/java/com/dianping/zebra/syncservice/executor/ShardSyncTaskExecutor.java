package com.dianping.zebra.syncservice.executor;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dianping.cat.Cat;
import com.dianping.puma.api.PumaClient;
import com.dianping.puma.api.PumaClientConfig;
import com.dianping.puma.core.dto.BinlogMessage;
import com.dianping.puma.core.event.Event;
import com.dianping.puma.core.event.RowChangedEvent;
import com.dianping.puma.core.model.BinlogInfo;
import com.dianping.puma.core.util.sql.DMLType;
import com.dianping.zebra.biz.entity.PumaClientSyncTaskBaseEntity;
import com.dianping.zebra.biz.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.biz.monitor.TaskExecutorMetric;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.router.RouterType;
import com.dianping.zebra.shard.router.rule.DataSourceBO;
import com.dianping.zebra.shard.router.rule.SimpleDataSourceProvider;
import com.dianping.zebra.shard.router.rule.engine.GroovyRuleEngine;
import com.dianping.zebra.shard.router.rule.engine.RuleEngineEvalContext;
import com.dianping.zebra.syncservice.exception.NoRowsAffectedException;
import com.dianping.zebra.syncservice.util.ColumnInfoWrap;
import com.dianping.zebra.syncservice.util.SqlBuilder;
import com.google.common.collect.Lists;

public class ShardSyncTaskExecutor implements TaskExecutor {
	private final static Logger eventLogger = LoggerFactory.getLogger("EventSkip");

	private final static Logger logger = LoggerFactory.getLogger(ShardSyncTaskExecutor.class);

	private final PumaClientSyncTaskEntity task;

	private final AtomicLong hitTimes = new AtomicLong();

	private final static int MAX_TRY_TIMES = 10;

	private final static int NUMBER_OF_PROCESSORS = 5;

	private final static int MAX_QUEUE_SIZE = 10000;

	protected volatile PumaClient client;

	protected Map<String, GroovyRuleEngine> engines = new ConcurrentHashMap<String, GroovyRuleEngine>();

	protected Map<String, SimpleDataSourceProvider> dataSourceProviders = new ConcurrentHashMap<String, SimpleDataSourceProvider>();

	protected Map<String, GroupDataSource> dataSources = new ConcurrentHashMap<String, GroupDataSource>();

	protected Map<String, JdbcTemplate> templateMap = new ConcurrentHashMap<String, JdbcTemplate>();

	protected volatile BlockingQueue<RowChangedEvent>[] eventQueues;

	protected volatile BinlogInfo[] lastSendBinlogInfo;

	protected volatile BinlogInfo lastAckBinlogInfo;

	protected volatile RowEventProcessor[] rowEventProcessors;

	protected volatile Thread[] rowEventProcesserThreads;

	protected volatile Thread clientThread;

	private TaskExecutorMetric metric = new TaskExecutorMetric();

	public ShardSyncTaskExecutor(PumaClientSyncTaskEntity task) {
		this.task = task;
	}

	public synchronized void init() {
		logger.info("initializing task executor...");

		initRouter();
		initDataSources();
		initJdbcTemplate();
		initPumaClient();
		initEventQueues();
		initProcessors();

		logger.info("task executor initialized.");
	}

	public synchronized void start() {
		if (clientThread == null || rowEventProcesserThreads == null) {
			throw new IllegalStateException("You should init before start!");
		}

		logger.info("start task executor...");

		clientThread.start();

		for (Thread thread : rowEventProcesserThreads) {
			thread.start();
		}
	}

	public synchronized void stop() {
		logger.info("stop task executor...");

		if (clientThread != null) {
			clientThread.interrupt();
		}

		if (dataSources != null) {
			for (GroupDataSource ds : dataSources.values()) {
				try {
					ds.close();
				} catch (SQLException ignore) {
				}
			}
			dataSources = null;
		}

		if (rowEventProcesserThreads != null) {
			for (Thread thread : rowEventProcesserThreads) {
				thread.interrupt();
			}
		}

		logger.info("task executor stopped.");
	}

	protected void initProcessors() {
		logger.info("initializing processors...");

		rowEventProcessors = new RowEventProcessor[NUMBER_OF_PROCESSORS];
		rowEventProcesserThreads = new Thread[NUMBER_OF_PROCESSORS];

		for (int k = 0; k < this.eventQueues.length; k++) {
			BlockingQueue<RowChangedEvent> queue = this.eventQueues[k];
			RowEventProcessor processor = new RowEventProcessor(queue);
			Thread thread = new Thread(processor);
			thread.setDaemon(true);
			thread.setName(String.format("RowEventProcessor-%d", k));

			rowEventProcessors[k] = processor;
			rowEventProcesserThreads[k] = thread;
		}
	}

	@SuppressWarnings("unchecked")
	protected void initEventQueues() {
		logger.info("initializing event queues...");

		this.eventQueues = new BlockingQueue[NUMBER_OF_PROCESSORS];
		this.lastSendBinlogInfo = new BinlogInfo[NUMBER_OF_PROCESSORS];
		for (int k = 0; k < this.eventQueues.length; k++) {
			this.eventQueues[k] = new LinkedBlockingQueue<RowChangedEvent>(MAX_QUEUE_SIZE);
		}
	}

	protected void initRouter() {
		logger.info("initializing router...");

		for (Entry<String, PumaClientSyncTaskBaseEntity> entry : task.getPumaBaseEntities().entrySet()) {
			String tableName = entry.getKey();
			PumaClientSyncTaskBaseEntity entity = entry.getValue();

			this.engines.put(tableName, new GroovyRuleEngine(entity.getDbRule()));
			this.dataSourceProviders.put(tableName,
			      new SimpleDataSourceProvider(tableName, entity.getDbIndex(), entity.getTbSuffix(), entity.getTbRule()));
		}
	}

	protected void initDataSources() {
		logger.info("initializing dataSources...");

		for (SimpleDataSourceProvider dataSourceProvider : this.dataSourceProviders.values()) {
			for (Map.Entry<String, Set<String>> entity : dataSourceProvider.getAllDBAndTables().entrySet()) {
				String jdbcRef = entity.getKey();
				if (!this.dataSources.containsKey(jdbcRef)) {
					GroupDataSource ds = initGroupDataSource(jdbcRef);
					this.dataSources.put(jdbcRef, ds);
				}
			}
		}
	}

	protected void initJdbcTemplate() {
		logger.info("initializing jdbcTemplate...");

		for (Map.Entry<String, GroupDataSource> entry : dataSources.entrySet()) {
			this.templateMap.put(entry.getKey(), new JdbcTemplate(entry.getValue()));
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
		logger.info("initializing puma client...");

		this.client = new PumaClientConfig().setClientName(task.getPumaClientName()).setDatabase(task.getPumaDatabase())
		      .setTables(Lists.newArrayList(task.getPumaTables().split(","))).setTransaction(false)
		      .buildClusterPumaClient();
		this.clientThread = new Thread(new PumaClientRunner());
		this.clientThread.setDaemon(true);
		this.clientThread.setName("PumaSyncTask-" + task.getPumaClientName());
	}

	class RowEventProcessor implements Runnable {
		private final BlockingQueue<RowChangedEvent> queue;

		private volatile BinlogInfo binlogInfo;

		public RowEventProcessor(BlockingQueue<RowChangedEvent> queue) {
			this.queue = queue;
		}

		public BinlogInfo getBinlogInfo() {
			return binlogInfo;
		}

		@Override
		public void run() {
			while (true) {
				RowChangedEvent event;
				try {
					event = queue.take();
				} catch (InterruptedException e) {
					break;
				}

				processPK(event);

				int tryTimes = 0;
				while (true) {
					tryTimes++;
					if (Thread.interrupted()) {
						break;
					}

					try {
						process(event);

						metric.getTotalSyncBinlogNumber().incrementAndGet();
						AtomicLong atomicLong = metric.getEveryTableSyncBinlogNumber().get(event.getTable());

						if (atomicLong == null) {
							atomicLong = new AtomicLong(1);
							metric.getEveryTableSyncBinlogNumber().put(event.getTable(), atomicLong);
						} else {
							atomicLong.incrementAndGet();
						}

						this.binlogInfo = event.getBinlogInfo();
						break;
					} catch (DuplicateKeyException e) {
						metric.getTotalDuplicateKey().incrementAndGet();
						event.setDmlType(DMLType.UPDATE);
					} catch (NoRowsAffectedException e) {
						metric.getTotalNoRowsAffected().incrementAndGet();
						event.setDmlType(DMLType.INSERT);
					} catch (RuntimeException e) {
						if (tryTimes > MAX_TRY_TIMES) {
							Cat.logError(e);
							eventLogger.warn(event.toString());
							metric.getTotalSkipBinlogNumber().incrementAndGet();
							break;
						}
					}
				}

				hitTimes.incrementAndGet();
			}
		}

		protected void process(RowChangedEvent rowEvent) {
			String tableName = task.getTableNamesMapping().get(rowEvent.getTable());
			ColumnInfoWrap column = new ColumnInfoWrap(rowEvent);
			RuleEngineEvalContext context = new RuleEngineEvalContext(column);
			Number index = (Number) engines.get(tableName).eval(context);
			if (index.intValue() < 0) {
				return;
			}
			DataSourceBO bo = dataSourceProviders.get(tableName).getDataSource(index.intValue());
			String table = bo.evalTable(context);

			String sql = SqlBuilder.buildSql(rowEvent, "", table);
			Object[] args = SqlBuilder.buildArgs(rowEvent);

			int rows = templateMap.get(bo.getDbIndex()).update(sql, args);
			if (rows == 0 && RowChangedEvent.UPDATE == rowEvent.getActionType()) {
				throw new NoRowsAffectedException();
			}
		}

		protected void processPK(RowChangedEvent rowEvent) {
			Set<String> needToRemoveKeys = new HashSet<String>();
			String tableName = rowEvent.getTable();
			String originTableName = task.getTableNamesMapping().get(tableName);
			PumaClientSyncTaskBaseEntity baseEntity = task.getPumaBaseEntities().get(originTableName);

			for (Map.Entry<String, RowChangedEvent.ColumnInfo> info : rowEvent.getColumns().entrySet()) {
				if (info.getValue().isKey() && !baseEntity.getPk().equals(info.getKey())) {
					needToRemoveKeys.add(info.getKey());
				}
			}

			for (String key : needToRemoveKeys) {
				rowEvent.getColumns().remove(key);
			}

			String[] splits = baseEntity.getPk().split("\\+");

			for (String pk : splits) {
				rowEvent.getColumns().get(pk).setKey(true);
			}
		}
	}

	protected BinlogInfo getAckBinlogInfo() {
		BinlogInfo oldestBinlogInRunningProcessor = null;
		BinlogInfo newestBinlogInRunningProcessor = null;

		for (int k = 0; k < rowEventProcessors.length; k++) {
			BinlogInfo lastBinlogInfo = rowEventProcessors[k].getBinlogInfo();
			BinlogInfo sendBinlogInfo = lastSendBinlogInfo[k];

			// 任务刚启动，有process一个都没处理完
			if (lastBinlogInfo == null && sendBinlogInfo != null) {
				return null;
			}

			if (lastBinlogInfo == sendBinlogInfo) {
				// 任务空闲中，统计最新完成的 BinlogInfo
				if (newestBinlogInRunningProcessor == null
				      || (lastBinlogInfo != null && lastBinlogInfo.getTimestamp() > newestBinlogInRunningProcessor
				            .getTimestamp())) {
					newestBinlogInRunningProcessor = lastBinlogInfo;
				}
				continue;
			} else {
				// 任务运行中，统计最早完成的 BinlogInfo
				if (oldestBinlogInRunningProcessor == null
				      || (lastBinlogInfo != null && lastBinlogInfo.getTimestamp() < oldestBinlogInRunningProcessor
				            .getTimestamp())) {
					oldestBinlogInRunningProcessor = lastBinlogInfo;
				}
			}
		}

		return oldestBinlogInRunningProcessor != null ? oldestBinlogInRunningProcessor : newestBinlogInRunningProcessor;
	}

	class PumaClientRunner implements Runnable {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					BinlogMessage data = client.get(1000, 1, TimeUnit.SECONDS);
					List<Event> binlogEvents = data.getBinlogEvents();
					for (com.dianping.puma.core.event.Event event : binlogEvents) {
						onEvent(event);
					}

					if (rowEventProcessors == null) {
						continue;
					}

					BinlogInfo binlogInfo = getAckBinlogInfo();
					if (binlogInfo != null && binlogInfo != lastAckBinlogInfo) {
						client.ack(binlogInfo);
						lastAckBinlogInfo = binlogInfo;
					}
				} catch (Exception e) {
					Cat.logError(e);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						break;
					}
				}
			}
		}

		public void onEvent(com.dianping.puma.core.event.Event event) {
			if (!(event instanceof RowChangedEvent)) {
				return;
			}
			
			RowChangedEvent rowEvent = (RowChangedEvent) event;
			if (rowEvent.isTransactionBegin() || rowEvent.isTransactionCommit()) {
				return;
			}


			String tableName = rowEvent.getTable();
			String originTableName = task.getTableNamesMapping().get(tableName);
			PumaClientSyncTaskBaseEntity pumaClientSyncTaskBaseEntity = task.getPumaBaseEntities().get(originTableName);
			
			String pk = pumaClientSyncTaskBaseEntity.getPk();
			String[] pks = pk.split("\\+");
			RowChangedEvent.ColumnInfo columnInfo = rowEvent.getColumns().get(pks[0]);

			try {
				int index = getIndex(columnInfo);
				lastSendBinlogInfo[index] = rowEvent.getBinlogInfo();
				eventQueues[index].put(rowEvent);
				
				metric.getTotalBinlogNumber().incrementAndGet();
			} catch (InterruptedException e) {
			}
		}

		private int getIndex(RowChangedEvent.ColumnInfo columnInfo) {
			return Math.abs((columnInfo.getNewValue() != null ? columnInfo.getNewValue() : columnInfo.getOldValue())
			      .hashCode()) % NUMBER_OF_PROCESSORS;
		}
	}

	public TaskExecutorMetric getMetric() {
		return metric;
	}

	public String getName() {
		return task.getPumaClientName();
	}
}
