package com.dianping.zebra.admin.job;

import com.dianping.cat.Cat;
import com.dianping.zebra.admin.dao.PumaClientSyncTaskMapper;
import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;
import com.dianping.zebra.admin.job.executor.PumaClientSyncTaskExecutor;
import com.dianping.zebra.admin.job.executor.ShardDumpTaskExecutor;
import com.dianping.zebra.admin.service.ShardDumpService;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Dozer @ 6/2/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */

@Component
public class ExecutorManager {
	@Autowired
	private ShardDumpService shardDumpService;

	@Autowired
	private PumaClientSyncTaskMapper pumaClientSyncTaskMapper;

	private Map<Integer, PumaClientSyncTaskExecutor> pumaClientSyncTaskExecutorMap = new ConcurrentHashMap<Integer, PumaClientSyncTaskExecutor>();

	private Map<Integer, ShardDumpTaskExecutor> shardDumpTaskExecutorMap = new ConcurrentHashMap<Integer, ShardDumpTaskExecutor>();

	@Scheduled(cron = "0/10 * * * * ?")
	public synchronized void startPumaSyncTask() {
		try {
			List<PumaClientSyncTaskEntity> tasks = pumaClientSyncTaskMapper
				.findEffectiveTaskByExecutor(InetAddress.getLocalHost().getHostAddress());

			for (PumaClientSyncTaskEntity task : tasks) {
				if (pumaClientSyncTaskExecutorMap.containsKey(task.getId())) {
					continue;
				}

				PumaClientSyncTaskExecutor executor = new PumaClientSyncTaskExecutor(task);
				executor.setPumaClientSyncTaskMapper(pumaClientSyncTaskMapper);
				executor.init();
				executor.start();

				pumaClientSyncTaskExecutorMap.put(task.getId(), executor);
			}

			Set<Integer> idToRemove = new HashSet<Integer>();
			for (int id : pumaClientSyncTaskExecutorMap.keySet()) {
				final int finalId = id;
				if (Iterables.all(tasks, new Predicate<PumaClientSyncTaskEntity>() {
					@Override
					public boolean apply(PumaClientSyncTaskEntity entity) {
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

	@Scheduled(cron = "0/10 * * * * ?")
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
