package com.dianping.zebra.admin.job;

import com.dianping.cat.Cat;
import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;
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

    private Map<Integer, ShardDumpTaskExecutor> shardDumpTaskExecutorMap = new ConcurrentHashMap<Integer, ShardDumpTaskExecutor>();

    @Scheduled(cron = "0/15 * * * * ?")
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
