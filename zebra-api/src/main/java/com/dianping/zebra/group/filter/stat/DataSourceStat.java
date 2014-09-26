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

	private final AtomicLong closeGroupDataSourceErrorCount = new AtomicLong();

	private final AtomicLong closeGroupDataSourceSuccessCount = new AtomicLong();

	private final AtomicLong closeSingleConnectionErrorCount = new AtomicLong();

	private final AtomicLong closeSingleConnectionSuccessCount = new AtomicLong();

	private final AtomicLong closeSingleDataSourceErrorCount = new AtomicLong();

	private final AtomicLong closeSingleDataSourceSuccessCount = new AtomicLong();

	private final String dataSourceId;

	private final AtomicLong getGroupConnectionErrorCount = new AtomicLong();

	private final AtomicLong getGroupConnectionSuccessCount = new AtomicLong();

	private final AtomicLong getSingleConnectionErrorCount = new AtomicLong();

	private final AtomicLong getSingleConnectionSuccessCount = new AtomicLong();

	private final AtomicLong initGroupDataSourceErrorCount = new AtomicLong();

	private final AtomicLong initGroupDataSourceSuccessCount = new AtomicLong();

	private final AtomicLong initSingleDataSourceErrorCount = new AtomicLong();

	private final AtomicLong initSingleDataSourceSuccessCount = new AtomicLong();

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

	public AtomicLong getCloseGroupDataSourceErrorCount() {
		return closeGroupDataSourceErrorCount;
	}

	public AtomicLong getCloseGroupDataSourceSuccessCount() {
		return closeGroupDataSourceSuccessCount;
	}

	public AtomicLong getCloseSingleConnectionErrorCount() {
		return closeSingleConnectionErrorCount;
	}

	public AtomicLong getCloseSingleConnectionSuccessCount() {
		return closeSingleConnectionSuccessCount;
	}

	public AtomicLong getCloseSingleDataSourceErrorCount() {
		return closeSingleDataSourceErrorCount;
	}

	public AtomicLong getCloseSingleDataSourceSuccessCount() {
		return closeSingleDataSourceSuccessCount;
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

	public AtomicLong getGetSingleConnectionErrorCount() {
		return getSingleConnectionErrorCount;
	}

	public AtomicLong getGetSingleConnectionSuccessCount() {
		return getSingleConnectionSuccessCount;
	}

	public AtomicLong getInitGroupDataSourceErrorCount() {
		return initGroupDataSourceErrorCount;
	}

	public AtomicLong getInitGroupDataSourceSuccessCount() {
		return initGroupDataSourceSuccessCount;
	}

	public AtomicLong getInitSingleDataSourceErrorCount() {
		return initSingleDataSourceErrorCount;
	}

	public AtomicLong getInitSingleDataSourceSuccessCount() {
		return initSingleDataSourceSuccessCount;
	}

	public Map<String, Object> toMap() {
		return toMap(false);
	}

	public Map<String, Object> toMap(boolean isSummary) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		if (!isSummary) {
			resultMap.put("Id", this.dataSourceId);
		}
		resultMap.put("GetGroupConnection(S/E)", String.format("%d/%d", this.getGroupConnectionSuccessCount.get(),
				this.getGroupConnectionErrorCount.get()));
		resultMap.put("CloseGroupConnection(S/E)", String.format("%d/%d", this.closeGroupConnectionSuccessCount.get(),
				this.closeGroupConnectionErrorCount.get()));
		resultMap.put("GetSingleConnection(S/E)", String.format("%d/%d", this.getSingleConnectionSuccessCount.get(),
				this.getSingleConnectionErrorCount.get()));
		resultMap.put("CloseSingleConnection(S/E)", String.format("%d/%d", this.closeSingleConnectionSuccessCount.get(),
				this.closeSingleConnectionErrorCount.get()));
		resultMap.put("InitGroupDataSource(S/E)", String.format("%d/%d", this.initGroupDataSourceSuccessCount.get(),
				this.initGroupDataSourceErrorCount.get()));
		resultMap.put("CloseGroupDataSource(S/E)", String.format("%d/%d", this.closeGroupDataSourceSuccessCount.get(),
				this.closeGroupDataSourceErrorCount.get()));
		resultMap.put("InitSingleDataSource(S/E)", String.format("%d/%d", this.initSingleDataSourceSuccessCount.get(),
				this.initSingleDataSourceErrorCount.get()));
		resultMap.put("CloseSingleDataSource(S/E)", String.format("%d/%d", this.closeSingleDataSourceSuccessCount.get(),
				this.closeSingleDataSourceErrorCount.get()));
		return resultMap;
	}
}
