package com.dianping.zebra.syncservice.executor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dianping.cat.Cat;
import com.dianping.zebra.biz.dao.PumaClientSyncTaskMapper;
import com.dianping.zebra.biz.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.biz.entity.ShardDumpTaskEntity;
import com.dianping.zebra.biz.monitor.TaskExecutorMetric;
import com.dianping.zebra.biz.service.ShardDumpService;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

@Component
public class TaskManager {

	private final static Logger logger = LoggerFactory.getLogger(TaskManager.class);

	@Autowired
	private ShardDumpService shardDumpService;

	@Autowired
	private PumaClientSyncTaskMapper pumaClientSyncTaskMapper;

	private Map<String, ShardSyncTaskExecutor> pumaClientSyncTaskExecutorMap = new ConcurrentHashMap<String, ShardSyncTaskExecutor>();

	private Map<Integer, ShardDumpTaskExecutor> shardDumpTaskExecutorMap = new ConcurrentHashMap<Integer, ShardDumpTaskExecutor>();

	private String localAddress;

	public synchronized Map<String, TaskExecutorMetric> getStatus() {
		Map<String, TaskExecutorMetric> result = new HashMap<String, TaskExecutorMetric>();
		for (ShardSyncTaskExecutor task : pumaClientSyncTaskExecutorMap.values()) {
			result.put(task.getName(), task.getMetric());
		}

		return result;
	}

	@PostConstruct
	public void init() throws UnknownHostException {
		this.localAddress = InetAddress.getLocalHost().getHostName();

		Thread syncWithDbTask = new Thread(new SyncWithDatabaseTask());
		syncWithDbTask.setName("Sync-Database-Task");
		syncWithDbTask.setDaemon(true);

		syncWithDbTask.start();
	}

	class SyncWithDatabaseTask implements Runnable {

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				startPumaSyncTask();

				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}

	public void startPumaSyncTask() {
		List<PumaClientSyncTaskEntity> tasks = pumaClientSyncTaskMapper.findEffectiveTaskByExecutor(this.localAddress);

		for (PumaClientSyncTaskEntity task : tasks) {
			if (pumaClientSyncTaskExecutorMap.containsKey(task.getPumaClientName())) {
				continue;
			}

			ShardSyncTaskExecutor executor = null;
			try {
				logger.info("starting a new task {" + task.getPumaClientName() + "} ...");

				executor = new ShardSyncTaskExecutor(task);
				executor.init();
				executor.start();
				pumaClientSyncTaskExecutorMap.put(task.getPumaClientName(), executor);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				Cat.logError(e);
				if (executor != null) {
					executor.stop();
				}
			}
		}

		Set<String> pksToRemove = new HashSet<String>();
		for (String pk : pumaClientSyncTaskExecutorMap.keySet()) {
			final String finalPK = pk;
			if (Iterables.all(tasks, new Predicate<PumaClientSyncTaskEntity>() {
				@Override
				public boolean apply(PumaClientSyncTaskEntity entity) {
					return !finalPK.equals(entity.getPumaClientName());
				}
			})) {
				pksToRemove.add(finalPK);
			}
		}

		for (String pk : pksToRemove) {
			ShardSyncTaskExecutor task = pumaClientSyncTaskExecutorMap.remove(pk);
			if (task != null) {
				logger.info("stop an old task {" + task.getName() + "} ...");

				task.stop();
			}
		}
	}

	public synchronized void startShardDumpTask() {
		try {
			List<ShardDumpTaskEntity> tasks = shardDumpService.getTaskByIp(InetAddress.getLocalHost().getHostAddress());

			for (ShardDumpTaskEntity task : tasks) {
				if (shardDumpTaskExecutorMap.containsKey(task.getId())) {
					continue;
				}

				ShardDumpTaskExecutor executor = new ShardDumpTaskExecutor(task);
				executor.setShardDumpService(shardDumpService);
				executor.init();
				executor.start();

				shardDumpTaskExecutorMap.put(task.getId(), executor);
			}

			Set<Integer> idToRemove = new HashSet<Integer>();
			for (int id : shardDumpTaskExecutorMap.keySet()) {
				final int finalId = id;
				if (Iterables.all(tasks, new Predicate<ShardDumpTaskEntity>() {
					@Override
					public boolean apply(ShardDumpTaskEntity entity) {
						return entity.getId() != finalId;
					}
				})) {
					idToRemove.add(finalId);
				}
			}

			for (int id : idToRemove) {
				ShardDumpTaskExecutor task = shardDumpTaskExecutorMap.remove(id);
				if (task != null) {
					task.stop();
				}
			}

		} catch (UnknownHostException e) {
			Cat.logError(e);
		}
	}
}
