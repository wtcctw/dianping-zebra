package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.JdbcMetaData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Dozer on 9/15/14.
 */
public class ExecuteStat {

	private final String dataSourceId;

	private final AtomicLong deleteErrorCount = new AtomicLong();

	private final AtomicLong deleteSuccessCount = new AtomicLong();

	private final AtomicLong errorCount = new AtomicLong();

	private final AtomicLong errorTime = new AtomicLong();

	private final RangeCounter errorTimeRange = RangeCounter.getDefaultTimeRange();

	private final String groupDataSourceId;

	private final AtomicLong insertErrorCount = new AtomicLong();

	private final AtomicLong insertSuccessCount = new AtomicLong();

	private final AtomicLong selectErrorCount = new AtomicLong();

	private final AtomicLong selectSuccessCount = new AtomicLong();

	private final String sql;

	private final AtomicLong successCount = new AtomicLong();

	private final AtomicLong successTime = new AtomicLong();

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

	private void convertTimeRange(Map<String, Object> resultMap, String titlePrefix,
			Map<Long, AtomicLong> resultResult) {
		List<String> successKey = new ArrayList<String>();
		List<String> successValue = new ArrayList<String>();
		for (Map.Entry<Long, AtomicLong> entry : resultResult.entrySet()) {
			if (entry.getKey().equals(Long.MAX_VALUE)) {
				continue;
			}
			successKey.add(String.valueOf(entry.getKey()));
			successValue.add(String.valueOf(entry.getValue()));
		}
		resultMap.put(
				String.format("%s[%s]", titlePrefix, String.join(",", successKey)),
				String.join(",", successValue));
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

	public AtomicLong getErrorCount() {
		return errorCount;
	}

	public AtomicLong getErrorTime() {
		return errorTime;
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

	public AtomicLong getSuccessCount() {
		return successCount;
	}

	public AtomicLong getSuccessTime() {
		return successTime;
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

	public Map<String, Object> toMap() {
		return toMap(false);
	}

	public Map<String, Object> toMap(boolean isSummary) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		if (!isSummary) {
			resultMap.put("GroupDataSourceId", this.groupDataSourceId);
			resultMap.put("DataSourceId", this.dataSourceId);
			resultMap.put("Sql", this.sql);
		}
		resultMap.put("Success", this.successCount.get());
		resultMap.put("Error", this.errorCount.get());
		resultMap.put("Transaction", this.transactionCount.get());
		resultMap.put("SelectSuccess", this.selectSuccessCount.get());
		resultMap.put("SelectError", this.selectErrorCount.get());
		resultMap.put("InsertSuccess", this.insertSuccessCount.get());
		resultMap.put("InsertError", this.insertErrorCount.get());
		resultMap.put("UpdateSuccess", this.updateSuccessCount.get());
		resultMap.put("UpdateError", this.updateErrorCount.get());
		resultMap.put("DeleteSuccess", this.deleteSuccessCount.get());
		resultMap.put("DeleteError", this.deleteErrorCount.get());
		convertTimeRange(resultMap, "S", this.successTimeRange.getResult());
		convertTimeRange(resultMap, "E", this.errorTimeRange.getResult());
		return resultMap;
	}
}
