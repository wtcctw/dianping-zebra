package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.JdbcMetaData;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Dozer on 9/15/14.
 */
public class ExecuteStat {

	private final String dataSourceId;

	private final AtomicLong deleteErrorCount = new AtomicLong();

	private final AtomicLong deleteSuccessCount = new AtomicLong();

	private final RangeCounter errorTimeRange = RangeCounter.getDefaultTimeRange();

	private final String groupDataSourceId;

	private final AtomicLong insertErrorCount = new AtomicLong();

	private final AtomicLong insertSuccessCount = new AtomicLong();

	private final AtomicLong selectErrorCount = new AtomicLong();

	private final AtomicLong selectSuccessCount = new AtomicLong();

	private final String sql;

	private final RangeCounter successTimeRange = RangeCounter.getDefaultTimeRange();

	private final AtomicLong transactionCount = new AtomicLong();

	private final AtomicLong updateErrorCount = new AtomicLong();

	private final AtomicLong updateSuccessCount = new AtomicLong();

	public ExecuteStat() {
		this.sql = null;
		this.dataSourceId = null;
		this.groupDataSourceId = null;
	}

	public ExecuteStat(JdbcMetaData metaData) {
		this.sql = metaData.getSql();
		this.groupDataSourceId = metaData.getDataSourceId();
		this.dataSourceId =
				metaData.getRealJdbcMetaData() == null ? null : metaData.getRealJdbcMetaData().getDataSourceId();

	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public AtomicLong getDeleteErrorCount() {
		return deleteErrorCount;
	}

	public AtomicLong getDeleteSuccessCount() {
		return deleteSuccessCount;
	}

	public RangeCounter getErrorTimeRange() {
		return errorTimeRange;
	}

	public String getGroupDataSourceId() {
		return groupDataSourceId;
	}

	public AtomicLong getInsertErrorCount() {
		return insertErrorCount;
	}

	public AtomicLong getInsertSuccessCount() {
		return insertSuccessCount;
	}

	public AtomicLong getSelectErrorCount() {
		return selectErrorCount;
	}

	public AtomicLong getSelectSuccessCount() {
		return selectSuccessCount;
	}

	public String getSql() {
		return sql;
	}

	public RangeCounter getSuccessTimeRange() {
		return successTimeRange;
	}

	public AtomicLong getTransactionCount() {
		return transactionCount;
	}

	public AtomicLong getUpdateErrorCount() {
		return updateErrorCount;
	}

	public AtomicLong getUpdateSuccessCount() {
		return updateSuccessCount;
	}
}
