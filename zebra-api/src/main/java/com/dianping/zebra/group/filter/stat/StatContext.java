package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.JdbcMetaData;

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

	private final static Lock dataSourceLock = new ReentrantLock();

	private final static DataSourceStat dataSourceSummary = new DataSourceStat();

	private final static Map<Integer, ExecuteStat> execute = new HashMap<Integer, ExecuteStat>();

	private final static Lock executeLock = new ReentrantLock();

	private final static ExecuteStat executeSummary = new ExecuteStat();

	private StatContext() {
	}

	private static void checkDataSource(String key, JdbcMetaData metaData) {
		if (!dataSource.containsKey(key)) {
			try {
				dataSourceLock.lock();
				if (!dataSource.containsKey(key)) {
					dataSource.put(key, new DataSourceStat(metaData));
				}
			} finally {
				dataSourceLock.unlock();
			}
		}
	}

	private static void checkExecute(Integer key, JdbcMetaData metaData) {
		//todo: to many sql
		if (!execute.containsKey(key)) {
			try {
				executeLock.lock();
				if (!execute.containsKey(key)) {
					execute.put(key, new ExecuteStat(metaData));
				}
			} finally {
				executeLock.unlock();
			}
		}
	}

	private static String generateDataSourceKey(JdbcMetaData metaData) {
		return metaData.getDataSourceId();
	}

	private static Integer generateExecuteKey(JdbcMetaData metaData) {
		int result = 0;

		result = result * 31 + metaData.getSql().hashCode();

		result = result * 31 + new Boolean(metaData.isBatch()).hashCode();

		result = result * 31 + Arrays.toString(metaData.getBatchedSqls().toArray()).hashCode();

		if (metaData.getDataSourceId() != null) {
			result = result * 31 + metaData.getDataSourceId().hashCode();
		}

		if (metaData.getRealJdbcMetaData() != null) {
			if (metaData.getRealJdbcMetaData().getDataSourceId() != null) {
				result = result * 31 + metaData.getRealJdbcMetaData().getDataSourceId().hashCode();
			}
		}

		return result;
	}

	public static DataSourceStat getDataSource(JdbcMetaData metaData) {
		String key = generateDataSourceKey(metaData);
		checkDataSource(key, metaData);
		return dataSource.get(key);
	}

	public static Map<String, DataSourceStat> getDataSource() {
		return dataSource;
	}

	public static DataSourceStat getDataSourceSummary() {
		return dataSourceSummary;
	}

	public static ExecuteStat getExecute(JdbcMetaData metaData) {
		Integer key = generateExecuteKey(metaData);
		checkExecute(key, metaData);
		return execute.get(key);
	}

	public static Map<Integer, ExecuteStat> getExecute() {
		return execute;
	}

	public static ExecuteStat getExecuteSummary() {
		return executeSummary;
	}
}
