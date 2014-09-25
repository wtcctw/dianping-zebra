package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.JdbcMetaData;

import java.util.LinkedHashMap;
import java.util.Map;
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

	public Map<String, Object> toMap() {
		return toMap(false);
	}

	public Map<String, Object> toMap(boolean isSummary) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		if (!isSummary) {
			resultMap.put("DataSourceId", this.dataSourceId);
		}
		resultMap.put("GetGroupConnectionSuccess", this.getGroupConnectionSuccessCount.get());
		resultMap.put("GetGroupConnectionError", this.getGroupConnectionErrorCount.get());
		resultMap.put("CloseGroupConnectionSuccess", this.closeGroupConnectionSuccessCount.get());
		resultMap.put("CloseGroupConnectionError", this.closeGroupConnectionErrorCount.get());
		return resultMap;
	}
}
