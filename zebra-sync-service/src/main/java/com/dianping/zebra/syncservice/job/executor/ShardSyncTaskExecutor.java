package com.dianping.zebra.syncservice.job.executor;

import com.dianping.cat.Cat;
import com.dianping.puma.api.PumaClient;
import com.dianping.puma.api.PumaClientConfig;
import com.dianping.puma.core.dto.BinlogMessage;
import com.dianping.puma.core.event.RowChangedEvent;
import com.dianping.puma.core.model.BinlogInfo;
import com.dianping.puma.core.util.sql.DMLType;
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
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class ShardSyncTaskExecutor implements TaskExecutor {
	private final PumaClientSyncTaskEntity task;

	private final static Logger eventSkipLogger = LoggerFactory.getLogger("EventSkip");

	public final AtomicLong hitTimes = new AtomicLong();

	public final static int MAX_TRY_TIMES = 10;

	public final static int NUMBER_OF_PROCESSORS = 5;

	public final static int MAX_QUEUE_SIZE = 10000;

	protected volatile GroovyRuleEngine engine;

	protected volatile SimpleDataSourceProvider dataSourceProvider;

	protected volatile PumaClient client;

	protected volatile Map<String, GroupDataSource> dataSources;

	protected volatile Map<String, JdbcTemplate> templateMap;

	protected volatile BlockingQueue<RowChangedEvent>[] eventQueues;

	protected volatile List<RowEventProcessor> rowEventProcessors;

	protected volatile List<Thread> rowEventProcesserThreads;

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
		ImmutableList.Builder<RowEventProcessor> processorBuilder = ImmutableList.builder();
		ImmutableList.Builder<Thread> threadBuilder = ImmutableList.builder();

		for (int k = 0; k < this.eventQueues.length; k++) {
			BlockingQueue<RowChangedEvent> queue = this.eventQueues[k];
			RowEventProcessor processor = new RowEventProcessor(queue);
			Thread thread = new Thread(processor);
			thread.setDaemon(true);
			thread.setName(String.format("RowEventProcessor-%d", k));

			processorBuilder.add(processor);
			threadBuilder.add(thread);
		}

		this.rowEventProcessors = processorBuilder.build();
		this.rowEventProcesserThreads = threadBuilder.build();
	}

	@SuppressWarnings("unchecked")
	protected void initEventQueues() {
		this.eventQueues = new BlockingQueue[NUMBER_OF_PROCESSORS];
		for (int k = 0; k < this.eventQueues.length; k++) {
			this.eventQueues[k] = new LinkedBlockingQueue<RowChangedEvent>(MAX_QUEUE_SIZE);
		}
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
		this.client = new PumaClientConfig().setClientName(task.getPumaClientName()).setDatabase(task.getPumaDatabase())
			.setTables(Lists.newArrayList(task.getPumaTables().split(","))).buildClusterPumaClient();
		this.clientThread = new Thread(new PumaClientRunner());
		this.clientThread.setDaemon(true);
		this.clientThread.setName("PumaSyncTask-" + task.getPumaClientName());
	}

	class RowEventProcessor implements Runnable {

		private final BlockingQueue<RowChangedEvent> queue;

		private final AtomicReference<BinlogInfo> binlogInfo = new AtomicReference<BinlogInfo>();

		public RowEventProcessor(BlockingQueue<RowChangedEvent> queue) {
			this.queue = queue;
		}

		public BinlogInfo getBinlogInfo() {
			return binlogInfo.getAndSet(null);
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
						this.binlogInfo.set(event.getBinlogInfo());
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
			ColumnInfoWrap column = new ColumnInfoWrap(rowEvent);
			RuleEngineEvalContext context = new RuleEngineEvalContext(column);
			Number index = (Number) engine.eval(context);
			if (index.intValue() < 0) {
				return;
			}
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
	}

	protected BinlogInfo getOldestBinlog(Iterable<BinlogInfo> binlogInfos) {
		BinlogInfo oldestBinlog = null;
		for (BinlogInfo binlog : binlogInfos) {
			if (oldestBinlog == null || binlog.getTimestamp() < oldestBinlog.getTimestamp()) {
				oldestBinlog = binlog;
			}
		}
		return oldestBinlog;
	}

	protected List<BinlogInfo> getBinlogInfos() {
		List<BinlogInfo> result = null;
		for (RowEventProcessor processor : rowEventProcessors) {
			BinlogInfo lastBinlogInfo = processor.getBinlogInfo();
			if (lastBinlogInfo == null) {
				continue;
			}

			if (result == null) {
				result = new ArrayList<BinlogInfo>();
			}

			result.add(lastBinlogInfo);
		}
		return result;
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

					List<BinlogInfo> binlogInfos = getBinlogInfos();
					if (binlogInfos != null) {
						BinlogInfo oldestBinlog = getOldestBinlog(binlogInfos);
						if (oldestBinlog != null) {
							client.ack(oldestBinlog);
						}
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

			RowChangedEvent.ColumnInfo columnInfo = rowEvent.getColumns().get(task.getPk());

			try {
				eventQueues[getIndex(columnInfo)].put(rowEvent);
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
