package com.dianping.zebra.dao;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncMapperExecutor {

	private static final int MAX_QUEUE_SIZE = 1000;

	private static int INIT_POOL_SIZE = 10;

	private static int MAX_POOL_SIZE = 20;

	private static ExecutorService executorService = null;

	public static void init() {
		executorService = new ThreadPoolExecutor(INIT_POOL_SIZE, MAX_POOL_SIZE, 0L, TimeUnit.SECONDS,
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

	public static void init(int initPoolSize, int maxPoolSize) {
		INIT_POOL_SIZE = initPoolSize;
		MAX_POOL_SIZE = maxPoolSize;

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
}
