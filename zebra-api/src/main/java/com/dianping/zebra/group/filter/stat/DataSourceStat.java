package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.JdbcMetaData;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Dozer on 9/15/14.
 */
public class DataSourceStat {
	private final AtomicLong closeGroupConnectionErrorCount = new AtomicLong();

	private final AtomicLong closeGroupConnectionSuccessCount = new AtomicLong();

	private final String dataSourceId;

	private final AtomicLong getGroupConnectionErrorCount = new AtomicLong();

	private final AtomicLong getGroupConnectionSuccessCount = new AtomicLong();

	public DataSourceStat() {
		dataSourceId = null;
	}

	public DataSourceStat(JdbcMetaData metaData) {
		dataSourceId = metaData.getDataSourceId();
	}

	public AtomicLong getCloseGroupConnectionErrorCount() {
		return closeGroupConnectionErrorCount;
	}

	public AtomicLong getCloseGroupConnectionSuccessCount() {
		return closeGroupConnectionSuccessCount;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public AtomicLong getGetGroupConnectionErrorCount() {
		return getGroupConnectionErrorCount;
	}

	public AtomicLong getGetGroupConnectionSuccessCount() {
		return getGroupConnectionSuccessCount;
	}
}
