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
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		resultMap.put("DataSourceId", this.dataSourceId);
		resultMap.put("GetGroupConnectionSuccessCount", this.getGroupConnectionSuccessCount.get());
		resultMap.put("GetGroupConnectionErrorCount", this.getGroupConnectionErrorCount.get());
		resultMap.put("CloseGroupConnectionSuccessCount", this.closeGroupConnectionSuccessCount.get());
		resultMap.put("CloseGroupConnectionErrorCount", this.closeGroupConnectionErrorCount.get());
		return resultMap;
	}
}
