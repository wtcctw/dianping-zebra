package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.JdbcContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Dozer on 9/15/14.
 */
public final class StatContext {
	private final static Map<String, DataSourceStat> dataSource = new HashMap<String, DataSourceStat>();

	private final static Map<Integer, ExecuteStat> execute = new HashMap<Integer, ExecuteStat>();

	private final static Lock dataSourceLock = new ReentrantLock();

	private final static Lock executeLock = new ReentrantLock();

	private volatile static DataSourceStat dataSourceSummary = new DataSourceStat();

	private volatile static ExecuteStat executeSummary = new ExecuteStat();

	private StatContext() {
	}

	private static void checkDataSource(String key, JdbcContext context) {
		if (!dataSource.containsKey(key)) {
			dataSourceLock.lock();
			try {
				if (!dataSource.containsKey(key)) {
					dataSource.put(key, new DataSourceStat(context.getDataSourceId(), context.getDataSource().getClass()
						  .getSimpleName(), context.getProperties()));
				}
			} finally {
				dataSourceLock.unlock();
			}
		}
	}

	private static void checkExecute(Integer key, JdbcContext context) {
		if (!execute.containsKey(key)) {
			executeLock.lock();
			try {
				if (!execute.containsKey(key)) {
					execute.put(key, new ExecuteStat(context));
				}
			} finally {
				executeLock.unlock();
			}
		}
	}

	private static String generateDataSourceKey(JdbcContext context) {
		return context.getDataSourceId();
	}

	private static Integer generateExecuteKey(JdbcContext context) {
		int result = 0;

		if (context.getMergedSql() != null) {
			result = result * 31 + context.getMergedSql().hashCode();
		}

		result = result * 31 + new Boolean(context.isBatch()).hashCode();

		result = result * 31 + new Boolean(context.isPrepared()).hashCode();

		if (context.getMergedBatchedSql() != null) {
			result = result * 31 + Arrays.toString(context.getMergedBatchedSql().toArray()).hashCode();
		}

		if (context.getDataSourceId() != null) {
			result = result * 31 + context.getDataSourceId().hashCode();
		}

		if (context.getRealJdbcContext() != null && context.getRealJdbcContext().getDataSourceId() != null) {
			result = result * 31 + context.getRealJdbcContext().getDataSourceId().hashCode();
		}

		return result;
	}

	public static DataSourceStat getDataSource(JdbcContext context) {
		String key = generateDataSourceKey(context);
		checkDataSource(key, context);
		return dataSource.get(key);
	}

	public static Map<String, DataSourceStat> getDataSource() {
		return dataSource;
	}

	public static DataSourceStat getDataSourceSummary() {
		return dataSourceSummary;
	}

	public static ExecuteStat getExecute(JdbcContext context) {
		Integer key = generateExecuteKey(context);
		checkExecute(key, context);
		return execute.get(key);
	}

	public static Map<Integer, ExecuteStat> getExecute() {
		return execute;
	}

	public static ExecuteStat getExecuteSummary() {
		return executeSummary;
	}

	public static void reset() {
		dataSourceSummary = new DataSourceStat();
		executeSummary = new ExecuteStat();

		dataSourceLock.lock();
		try {
			dataSource.clear();
		} finally {
			dataSourceLock.unlock();
		}

		executeLock.lock();
		try {
			execute.clear();
		} finally {
			executeLock.unlock();
		}
	}
}
