package com.dianping.zebra.admin.job;

import com.dianping.cat.Cat;
import com.dianping.zebra.admin.dao.PumaClientStatusMapper;
import com.dianping.zebra.admin.dao.PumaClientSyncTaskMapper;
import com.dianping.zebra.admin.entity.PumaClientStatusEntity;
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

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Dozer @ 6/2/15 mail@dozer.cc http://www.dozer.cc
 */

@Component
public class ExecutorManager {
	@Autowired
	private ShardDumpService shardDumpService;

	@Autowired
	private PumaClientSyncTaskMapper pumaClientSyncTaskMapper;

	@Autowired
	private PumaClientStatusMapper pumaClientStatusMapper;

	private Map<String, PumaClientSyncTaskExecutor> pumaClientSyncTaskExecutorMap = new ConcurrentHashMap<String, PumaClientSyncTaskExecutor>();

	private Map<Integer, ShardDumpTaskExecutor> shardDumpTaskExecutorMap = new ConcurrentHashMap<Integer, ShardDumpTaskExecutor>();

	private String localAddress;

	public Map<String, String> getStatus() {
		Map<String, String> result = new HashMap<String, String>();
		for (PumaClientSyncTaskExecutor task : pumaClientSyncTaskExecutorMap.values()) {
			result.putAll(task.getStatus());
		}
		return result;
	}

	@PostConstruct
	public void init() throws UnknownHostException {
		this.localAddress = InetAddress.getLocalHost().getHostAddress();
	}

	@Scheduled(cron = "0/10 * * * * ?")
	public synchronized void startPumaSyncTask() {
		List<PumaClientSyncTaskEntity> tasks = pumaClientSyncTaskMapper.findEffectiveTaskByExecutor(this.localAddress);

		for (PumaClientSyncTaskEntity task : tasks) {
			if (pumaClientSyncTaskExecutorMap.containsKey(task.getPumaTaskName())) {
				continue;
			}

			PumaClientStatusEntity status = pumaClientStatusMapper.selectByTaskId(task.getId());
			if (status == null) {
				status = new PumaClientStatusEntity();
				status.setTaskId(task.getId());
				pumaClientStatusMapper.create(status);
			}

			PumaClientSyncTaskExecutor executor = null;
			try {
				executor = new PumaClientSyncTaskExecutor(task, status);
				executor.setStatusMapper(pumaClientStatusMapper);
				executor.init();
				executor.start();
				pumaClientSyncTaskExecutorMap.put(task.getPumaTaskName(), executor);
			} catch (Exception e) {
				if (executor != null) {
					executor.stop();
				}
			}
		}

		Set<String> idToRemove = new HashSet<String>();
		for (String pumaTaskName : pumaClientSyncTaskExecutorMap.keySet()) {
			final String finalPumaTaskName = pumaTaskName;
			if (Iterables.all(tasks, new Predicate<PumaClientSyncTaskEntity>() {
				@Override
				public boolean apply(PumaClientSyncTaskEntity entity) {
					return entity.getPumaTaskName() != finalPumaTaskName;
				}
			})) {
				idToRemove.add(finalPumaTaskName);
			}
		}

		for (String taskName : idToRemove) {
			PumaClientSyncTaskExecutor task = pumaClientSyncTaskExecutorMap.remove(taskName);
			if (task != null) {
				task.stop();
			}
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
