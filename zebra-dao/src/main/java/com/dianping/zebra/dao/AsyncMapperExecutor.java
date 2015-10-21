package com.dianping.zebra.dao;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.dianping.cat.status.StatusExtension;
import com.dianping.cat.status.StatusExtensionRegister;

public class AsyncMapperExecutor {

	private static int MAX_QUEUE_SIZE;

	private static int CORE_POOL_SIZE;

	private static int MAX_POOL_SIZE;

	private static volatile ThreadPoolExecutor executorService = null;

	private static volatile CatStatusExtension catExt = null;

	public static void init() {
		if (executorService == null) {
			executorService = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 60L, TimeUnit.SECONDS,
			      new LinkedBlockingQueue<Runnable>(MAX_QUEUE_SIZE), new ThreadFactory() {

				      private AtomicInteger counter = new AtomicInteger(1);

				      @Override
				      public Thread newThread(Runnable r) {
					      Thread t = new Thread(r);
					      t.setName("Zebra-Dao-Executor-" + counter.getAndIncrement());
					      t.setDaemon(true);

					      return t;
				      }
			      });
		}

		if (catExt == null) {
			catExt = new CatStatusExtension();
			StatusExtensionRegister.getInstance().register(catExt);
		}
	}

	public static void init(int corePoolSize, int maxPoolSize, int queueSize) {
		CORE_POOL_SIZE = corePoolSize;
		MAX_POOL_SIZE = maxPoolSize;
		MAX_QUEUE_SIZE = queueSize;

		init();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void executeRunnable(Object mapper, Method method, Object[] args, AsyncDaoCallback callback) {
		checkNull();

		AsyncDaoRunnableExecutor executor = new AsyncDaoRunnableExecutor(mapper, method, args, callback);

		executorService.execute(executor);
	}

	public static Future<?> submitCallback(Object mapper, Method method, Object[] args) {
		checkNull();

		AsyncDaoCallableExecutor executor = new AsyncDaoCallableExecutor(mapper, method, args);

		return executorService.submit(executor);
	}

	private static void checkNull() {
		if (executorService == null) {
			throw new AsyncDaoException("AsyncMapperExecutor has not been init yet.");
		}
	}

	public static void setCorePoolSize(int corePoolSize) {
		if (executorService != null && CORE_POOL_SIZE != corePoolSize) {
			executorService.setCorePoolSize(corePoolSize);
		}

		CORE_POOL_SIZE = corePoolSize;
	}

	public static void setMaximumPoolSize(int maximumPoolSize) {
		if (executorService != null && MAX_POOL_SIZE != maximumPoolSize) {
			executorService.setMaximumPoolSize(maximumPoolSize);
		}

		MAX_POOL_SIZE = maximumPoolSize;
	}

	static class CatStatusExtension implements StatusExtension {

		@Override
		public String getId() {
			return "Zebra-Dao-Pool";
		}

		@Override
		public String getDescription() {
			return null;
		}

		@Override
		public Map<String, String> getProperties() {
			Map<String, String> properties = new HashMap<String, String>();

			properties.put("ActiveCount", String.valueOf(executorService.getActiveCount()));
			properties.put("PoolSize", String.valueOf(executorService.getPoolSize()));
			properties.put("QueueSize", String.valueOf(executorService.getQueue().size()));

			return properties;
		}
	}
}
