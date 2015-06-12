package com.dianping.zebra.admin.job.executor;

import com.dianping.cat.Cat;
import com.dianping.puma.api.ConfigurationBuilder;
import com.dianping.puma.api.EventListener;
import com.dianping.puma.api.PumaClient;
import com.dianping.puma.core.constant.SubscribeConstant;
import com.dianping.puma.core.event.ChangedEvent;
import com.dianping.puma.core.event.RowChangedEvent;
import com.dianping.puma.core.util.sql.DMLType;
import com.dianping.zebra.admin.dao.PumaClientStatusMapper;
import com.dianping.zebra.admin.entity.PumaClientStatusEntity;
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
import com.google.common.collect.ImmutableMap;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Dozer @ 6/9/15 mail@dozer.cc http://www.dozer.cc
 */
public class PumaClientSyncTaskExecutor implements TaskExecutor {
	private PumaClientStatusMapper statusMapper;

	private final PumaClientSyncTaskEntity task;

	private final PumaClientStatusEntity status;

	public final AtomicLong hitTimes = new AtomicLong();

	protected GroovyRuleEngine engine;

	protected SimpleDataSourceProvider dataSourceProvider;

	protected PumaClient client;

	protected Thread taskSequenceUploader;

	protected Map<String, GroupDataSource> dataSources;

	protected Map<String, JdbcTemplate> templateMap;

	public PumaClientSyncTaskExecutor(PumaClientSyncTaskEntity task, PumaClientStatusEntity status) {
		this.task = task;
		this.status = status;
	}

	public synchronized void init() {
		initRouter();
		initDataSources();
		initJdbcTemplate();
		initPumaClient();
		initSequenceUploader();
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
		client.getSeqFileHolder()
			.saveSeq(status.getSequence() == 0 ? SubscribeConstant.SEQ_FROM_LATEST : status.getSequence());
	}

	class TaskSequenceUploader implements Runnable {
		@Override
		public void run() {
			Long lastSeq = null;

			while (!Thread.currentThread().isInterrupted()) {
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {
					break;
				}

				if (lastSeq == null || lastSeq.longValue() != status.getSequence()) {
					try {
						lastSeq = status.getSequence();
						statusMapper.updateSequence(status);
					} catch (Exception e) {
						Cat.logError(e);
					}
				}
			}
		}
	}

	class PumaEventListener implements EventListener {
		protected volatile long tryTimes = 0;

		@Override
		public void onEvent(ChangedEvent event) throws Exception {
			hitTimes.incrementAndGet();
			tryTimes++;
			onEventInternal(event);
			status.setSequence(event.getSeq());
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
			Number index = (Number) engine.eval(new RuleEngineEvalContext(column));
			DataSourceBO bo = dataSourceProvider.getDataSource(index.intValue());
			String table = bo.evalTable(column);

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

	public void setStatusMapper(PumaClientStatusMapper statusMapper) {
		this.statusMapper = statusMapper;
	}
}
