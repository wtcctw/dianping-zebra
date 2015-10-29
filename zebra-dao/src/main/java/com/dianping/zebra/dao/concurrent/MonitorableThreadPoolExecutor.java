package com.dianping.zebra.dao.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.dianping.cat.status.StatusExtension;
import com.dianping.cat.status.StatusExtensionRegister;

public class MonitorableThreadPoolExecutor extends ThreadPoolExecutor {

	private volatile CatStatusExtension catExt = null;

	public MonitorableThreadPoolExecutor(String name, int corePoolSize, int maximumPoolSize, long keepAliveTime,
			TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);

		catExt = new CatStatusExtension(name, this);
		StatusExtensionRegister.getInstance().register(catExt);
	}

	public static class CatStatusExtension implements StatusExtension {
		private String name;

		private ThreadPoolExecutor executor;

		public CatStatusExtension(String name, ThreadPoolExecutor executor) {
			this.name = name;
			this.executor = executor;
		}

		@Override
		public String getId() {
			return name;
		}

		@Override
		public String getDescription() {
			return null;
		}

		@Override
		public Map<String, String> getProperties() {
			Map<String, String> properties = new HashMap<String, String>();

			properties.put("ActiveCount", String.valueOf(executor.getActiveCount()));
			properties.put("PoolSize", String.valueOf(executor.getPoolSize()));
			properties.put("QueueSize", String.valueOf(executor.getQueue().size()));

			return properties;
		}
	}
}
