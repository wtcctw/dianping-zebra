package com.dianping.zebra.admin.job;

import com.dianping.cat.Cat;
import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;
import com.dianping.zebra.admin.job.executor.ShardDumpTaskExecutor;
import com.dianping.zebra.admin.service.ShardDumpService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Dozer @ 6/2/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ExecutorManager implements Runnable {

    @Autowired
    private ShardDumpService shardDumpService;

    private Map<Integer, ShardDumpTaskExecutor> shardDumpTaskExecutorMap = new ConcurrentHashMap<Integer, ShardDumpTaskExecutor>();

    private Thread runner;

    public void init() {
        runner = new Thread(this);
        runner.setName("ExecutorManager");
        runner.setDaemon(true);
        runner.start();
    }

    protected synchronized void startShardDumpTask() {
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

        } catch (UnknownHostException e) {
            Cat.logError(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            startShardDumpTask();

            try {
                Thread.sleep(30 * 1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
