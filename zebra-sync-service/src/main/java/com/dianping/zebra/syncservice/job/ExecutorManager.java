package com.dianping.zebra.syncservice.job;

import com.dianping.cat.Cat;
import com.dianping.zebra.admin.dao.PumaClientStatusMapper;
import com.dianping.zebra.admin.dao.PumaClientSyncTaskMapper;
import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;
import com.dianping.zebra.admin.service.ShardDumpService;
import com.dianping.zebra.syncservice.job.executor.ShardDumpTaskExecutor;
import com.dianping.zebra.syncservice.job.executor.ShardSyncTaskExecutor;
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

	private Map<String, ShardSyncTaskExecutor> pumaClientSyncTaskExecutorMap = new ConcurrentHashMap<String, ShardSyncTaskExecutor>();

	private Map<Integer, ShardDumpTaskExecutor> shardDumpTaskExecutorMap = new ConcurrentHashMap<Integer, ShardDumpTaskExecutor>();

	private String localAddress;

	public Map<String, String> getStatus() {
		Map<String, String> result = new HashMap<String, String>();
		for (ShardSyncTaskExecutor task : pumaClientSyncTaskExecutorMap.values()) {
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
			if (pumaClientSyncTaskExecutorMap.containsKey(task.getPumaClientName())) {
				continue;
			}

			ShardSyncTaskExecutor executor = null;
			try {
				executor = new ShardSyncTaskExecutor(task);
				executor.init();
				executor.start();
				pumaClientSyncTaskExecutorMap.put(task.getPumaClientName(), executor);
			} catch (Exception e) {
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
