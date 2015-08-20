package com.dianping.zebra.syncservice.job.executor;

import com.dianping.cat.Cat;
import com.dianping.puma.api.PumaClient;
import com.dianping.puma.api.PumaClientConfig;
import com.dianping.puma.core.dto.BinlogMessage;
import com.dianping.puma.core.event.RowChangedEvent;
import com.dianping.puma.core.model.BinlogInfo;
import com.dianping.puma.core.util.sql.DMLType;
import com.dianping.zebra.biz.entity.PumaClientSyncTaskBaseEntity;
import com.dianping.zebra.biz.entity.PumaClientSyncTaskEntity;
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
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ShardSyncTaskExecutor implements TaskExecutor {
	private final static Logger eventSkipLogger = LoggerFactory.getLogger("EventSkip");

	private final PumaClientSyncTaskEntity task;

	public final AtomicLong hitTimes = new AtomicLong();

	public final static int MAX_TRY_TIMES = 10;

	public final static int NUMBER_OF_PROCESSORS = 5;

	public final static int MAX_QUEUE_SIZE = 10000;

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
		if (clientThread == null || rowEventProcesserThreads == null) {
			throw new IllegalStateException("You should init before start!");
		}

		clientThread.start();

		for (Thread thread : rowEventProcesserThreads) {
			thread.start();
		}
	}

	public synchronized void stop() {
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
	}

	protected void initProcessors() {
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
		this.eventQueues = new BlockingQueue[NUMBER_OF_PROCESSORS];
		this.lastSendBinlogInfo = new BinlogInfo[NUMBER_OF_PROCESSORS];
		for (int k = 0; k < this.eventQueues.length; k++) {
			this.eventQueues[k] = new LinkedBlockingQueue<RowChangedEvent>(MAX_QUEUE_SIZE);
		}
	}

	protected void initRouter() {
		for (Entry<String, PumaClientSyncTaskBaseEntity> entry : task.getPumaBaseEntities().entrySet()) {
			String tableName = entry.getKey();
			PumaClientSyncTaskBaseEntity entity = entry.getValue();

			this.engines.put(tableName, new GroovyRuleEngine(entity.getDbRule()));
			this.dataSourceProviders.put(tableName,
				new SimpleDataSourceProvider(tableName, entity.getDbIndex(), entity.getTbSuffix(), entity.getTbRule()));
		}
	}

	protected void initDataSources() {
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
						this.binlogInfo = event.getBinlogInfo();
						break;
					} catch (DuplicateKeyException e) {
						Cat.logError(e);
						event.setDmlType(DMLType.UPDATE);
					} catch (NoRowsAffectedException e) {
						Cat.logError(e);
						event.setDmlType(DMLType.INSERT);
					} catch (RuntimeException e) {
						Cat.logError(e);
						if (tryTimes > MAX_TRY_TIMES) {
							eventSkipLogger.warn(event.toString());
							break;
						}
					}
				}

				hitTimes.incrementAndGet();
			}
		}

		protected void process(RowChangedEvent rowEvent) {
			String tableName = rowEvent.getTable();
			ColumnInfoWrap column = new ColumnInfoWrap(rowEvent);
			RuleEngineEvalContext context = new RuleEngineEvalContext(column);
			Number index = (Number) engines.get(tableName).eval(context);
			if (index.intValue() < 0) {
				return;
			}
			DataSourceBO bo = dataSourceProviders.get(tableName).getDataSource(index.intValue());
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
			String tableName = rowEvent.getTable();
			PumaClientSyncTaskBaseEntity baseEntity = task.getPumaBaseEntities().get(tableName);

			for (Map.Entry<String, RowChangedEvent.ColumnInfo> info : rowEvent.getColumns().entrySet()) {
				if (info.getValue().isKey() && !baseEntity.getPk().equals(info.getKey())) {
					needToRemoveKeys.add(info.getKey());
				}
			}

			for (String key : needToRemoveKeys) {
				rowEvent.getColumns().remove(key);
			}

			rowEvent.getColumns().get(baseEntity.getPk()).setKey(true);
		}
	}

	protected BinlogInfo getAckBinlogInfo() {
		BinlogInfo oldestBinlogInRunningProcessor = null;
		BinlogInfo newestBinlogInRunningProcessor = null;

		for (int k = 0; k < rowEventProcessors.length; k++) {
			BinlogInfo lastBinlogInfo = rowEventProcessors[k].getBinlogInfo();
			BinlogInfo sendBinlogInfo = lastSendBinlogInfo[k];

			//任务刚启动，有process一个都没处理完
			if (lastBinlogInfo == null && sendBinlogInfo != null) {
				return null;
			}

			if (lastBinlogInfo == sendBinlogInfo) {
				//任务空闲中，统计最新完成的 BinlogInfo
				if (newestBinlogInRunningProcessor == null || (lastBinlogInfo != null
					&& lastBinlogInfo.getTimestamp() > newestBinlogInRunningProcessor.getTimestamp())) {
					newestBinlogInRunningProcessor = lastBinlogInfo;
				}
				continue;
			} else {
				//任务运行中，统计最早完成的 BinlogInfo
				if (oldestBinlogInRunningProcessor == null || (lastBinlogInfo != null
					&& lastBinlogInfo.getTimestamp() < oldestBinlogInRunningProcessor.getTimestamp())) {
					oldestBinlogInRunningProcessor = lastBinlogInfo;
				}
			}
		}

		return oldestBinlogInRunningProcessor != null ? oldestBinlogInRunningProcessor : newestBinlogInRunningProcessor;
	}

	class PumaClientRunner implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					BinlogMessage data = client.get(1000, 1, TimeUnit.SECONDS);
					for (com.dianping.puma.core.event.Event event : data.getBinlogEvents()) {
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
					Cat.logError(e.getMessage(), e);
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
			RowChangedEvent.ColumnInfo columnInfo = rowEvent.getColumns().get(pumaClientSyncTaskBaseEntity.getPk());

			try {
				int index = getIndex(columnInfo);
				eventQueues[index].put(rowEvent);
				lastSendBinlogInfo[index] = rowEvent.getBinlogInfo();
			} catch (InterruptedException e) {
			}
		}

		private int getIndex(RowChangedEvent.ColumnInfo columnInfo) {
			return Math.abs(
				(columnInfo.getNewValue() != null ? columnInfo.getNewValue() : columnInfo.getOldValue()).hashCode())
				% NUMBER_OF_PROCESSORS;
		}
	}

	public Map<String, String> getStatus() {
		Map<String, String> result = new HashMap<String, String>();
		result.put(String.format("PumaTask-%d", task.getId()), String.valueOf(hitTimes.getAndSet(0)));

		return result;
	}
}
