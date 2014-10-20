package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.JdbcContext;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Dozer on 9/15/14.
 */
public class ExecuteStat {

	private final String dataSourceId;

	private final AtomicLong deleteErrorCount = new AtomicLong();

	private final AtomicLong deleteRow = new AtomicLong();

	private final AtomicLong deleteSuccessCount = new AtomicLong();

	private final AtomicLong errorCount = new AtomicLong();

	private final AtomicLong errorTime = new AtomicLong();

	private final RangeCounter errorTimeRange = RangeCounter.getDefaultTimeRange();

	private final String groupDataSourceId;

	private final AtomicLong insertErrorCount = new AtomicLong();

	private final AtomicLong insertRow = new AtomicLong();

	private final AtomicLong insertSuccessCount = new AtomicLong();

	private final boolean isBatch;

	private final boolean isPrepared;

	private final AtomicLong selectErrorCount = new AtomicLong();

	private final AtomicLong selectRow = new AtomicLong();

	private final AtomicLong selectSuccessCount = new AtomicLong();

	private final String sql;

	private final AtomicLong successCount = new AtomicLong();

	private final AtomicLong successTime = new AtomicLong();

	private final RangeCounter successTimeRange = RangeCounter.getDefaultTimeRange();

	private final AtomicLong transactionCount = new AtomicLong();

	private final AtomicLong updateErrorCount = new AtomicLong();

	private final AtomicLong updateRow = new AtomicLong();

	private final AtomicLong updateSuccessCount = new AtomicLong();

	public ExecuteStat() {
		this.sql = null;
		this.dataSourceId = null;
		this.groupDataSourceId = null;
		this.isBatch = false;
		this.isPrepared = false;
	}

	public ExecuteStat(JdbcContext context) {
		this.isBatch = context.isBatch();
		if (context.isBatch() && !context.isPrepared()) {
			this.sql = Arrays.toString(context.getMergedBatchedSqls().toArray());
		} else {
			this.sql = context.getMergedSql();
		}
		this.isPrepared = context.isPrepared();
		this.groupDataSourceId = context.getDataSourceId();
		this.dataSourceId = context.getRealJdbcContext() == null ? null : context.getRealJdbcContext()
		      .getDataSourceId();
	}

	private void convertTimeRange(Map<String, Object> resultMap, String titlePrefix, Map<Long, AtomicLong> resultResult) {
		StringBuffer successKey = new StringBuffer();
		StringBuffer successValue = new StringBuffer();
		for (Map.Entry<Long, AtomicLong> entry : resultResult.entrySet()) {
			if (entry.getKey().equals(Long.MAX_VALUE)) {
				continue;
			}
			if (successKey.length() > 0) {
				successKey.append(",");
			}
			if (successKey.length() > 0) {
				successValue.append(",");
			}
			successKey.append(entry.getKey());
			successValue.append(entry.getValue());
		}
		resultMap.put(String.format("%s[%s]", titlePrefix, successKey), successValue);
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public AtomicLong getDeleteErrorCount() {
		return deleteErrorCount;
	}

	public AtomicLong getDeleteRow() {
		return deleteRow;
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

	public AtomicLong getInsertRow() {
		return insertRow;
	}

	public AtomicLong getInsertSuccessCount() {
		return insertSuccessCount;
	}

	public AtomicLong getSelectErrorCount() {
		return selectErrorCount;
	}

	public AtomicLong getSelectRow() {
		return selectRow;
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

	public AtomicLong getUpdateRow() {
		return updateRow;
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
			resultMap.put("GroupId", this.groupDataSourceId);
			resultMap.put("Id", this.dataSourceId);
			resultMap.put("SQL", this.sql);
			resultMap.put("IsBatch", this.isBatch);
			resultMap.put("IsPrepared", this.isPrepared);
		}
		resultMap.put(
		      "AvgTime(S/E)",
		      String.format("%d/%d", this.getSuccessCount().get() == 0 ? 0 : this.getSuccessTime().get()
		            / this.getSuccessCount().get(), this.getErrorCount().get() == 0 ? 0 : this.getErrorTime().get()
		            / this.getErrorCount().get()));
		resultMap.put("Execute(S/E)", String.format("%d/%d", this.successCount.get(), this.errorCount.get()));
		resultMap.put("Select(S/E)", String.format("%d/%d", this.selectSuccessCount.get(), this.selectErrorCount.get()));
		resultMap.put("SelectRows", this.selectRow.get());
		resultMap.put("Update(S/E)", String.format("%d/%d", this.updateSuccessCount.get(), this.updateErrorCount.get()));
		resultMap.put("UpdateRows", this.updateRow.get());
		resultMap.put("Insert(S/E)", String.format("%d/%d", this.insertSuccessCount.get(), this.insertErrorCount.get()));
		resultMap.put("InsertRows", this.insertRow.get());
		resultMap.put("Delete(S/E)", String.format("%d/%d", this.deleteSuccessCount.get(), this.deleteErrorCount.get()));
		resultMap.put("DeleteRows", this.deleteRow.get());
		resultMap.put("InTransaction", this.transactionCount.get());
		convertTimeRange(resultMap, "S", this.successTimeRange.getResult());
		convertTimeRange(resultMap, "E", this.errorTimeRange.getResult());
		return resultMap;
	}
}
