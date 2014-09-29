package com.dianping.zebra.group.filter.stat;

import com.dianping.zebra.group.filter.JdbcMetaData;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Dozer on 9/15/14.
 */
public class DataSourceStat {
	private final AtomicLong closeConnectionErrorCount = new AtomicLong();

	private final AtomicLong closeConnectionSuccessCount = new AtomicLong();

	private final AtomicLong closeDataSourceErrorCount = new AtomicLong();

	private final AtomicLong closeDataSourceSuccessCount = new AtomicLong();

	private final String dataSourceId;

	private final AtomicLong getConnectionErrorCount = new AtomicLong();

	private final AtomicLong getConnectionSuccessCount = new AtomicLong();

	private final AtomicLong initDataSourceErrorCount = new AtomicLong();

	private final AtomicLong initDataSourceSuccessCount = new AtomicLong();

	private final Map<String, Object> properties;

	private final AtomicLong refreshDataSourceErrorCount = new AtomicLong();

	private final AtomicLong refreshDataSourceSuccessCount = new AtomicLong();

	private final String type;

	public DataSourceStat() {
		this.dataSourceId = null;
		this.type = null;
		this.properties = null;
	}

	public DataSourceStat(JdbcMetaData metaData) {
		this.dataSourceId = metaData.getDataSourceId();
		this.properties = metaData.getProperties();
		this.type = metaData.getDataSource().getClass().getSimpleName();
	}

	public AtomicLong getCloseConnectionErrorCount() {
		return closeConnectionErrorCount;
	}

	public AtomicLong getCloseConnectionSuccessCount() {
		return closeConnectionSuccessCount;
	}

	public AtomicLong getCloseDataSourceErrorCount() {
		return closeDataSourceErrorCount;
	}

	public AtomicLong getCloseDataSourceSuccessCount() {
		return closeDataSourceSuccessCount;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public AtomicLong getGetConnectionErrorCount() {
		return getConnectionErrorCount;
	}

	public AtomicLong getGetConnectionSuccessCount() {
		return getConnectionSuccessCount;
	}

	public AtomicLong getInitDataSourceErrorCount() {
		return initDataSourceErrorCount;
	}

	public AtomicLong getInitDataSourceSuccessCount() {
		return initDataSourceSuccessCount;
	}

	public AtomicLong getRefreshDataSourceErrorCount() {
		return refreshDataSourceErrorCount;
	}

	public AtomicLong getRefreshDataSourceSuccessCount() {
		return refreshDataSourceSuccessCount;
	}

	public String getType() {
		return type;
	}

	public Map<String, Object> toMap() {
		return toMap(false);
	}

	public Map<String, Object> toMap(boolean isSummary) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		if (!isSummary) {
			resultMap.put("Id", this.dataSourceId);
			resultMap.put("Type", this.type);
		}
		resultMap.put("GetConnection(S/E)", String.format("%d/%d", this.getConnectionSuccessCount.get(),
				this.getConnectionErrorCount.get()));
		resultMap.put("CloseConnection(S/E)", String.format("%d/%d", this.closeConnectionSuccessCount.get(),
				this.closeConnectionErrorCount.get()));
		resultMap.put("RefreshDataSource(S/E)", String.format("%d/%d", this.refreshDataSourceSuccessCount.get(),
				this.refreshDataSourceErrorCount.get()));
		resultMap.put("InitDataSource(S/E)", String.format("%d/%d", this.initDataSourceSuccessCount.get(),
				this.initDataSourceErrorCount.get()));
		resultMap.put("CloseDataSource(S/E)", String.format("%d/%d", this.closeDataSourceSuccessCount.get(),
				this.closeDataSourceErrorCount.get()));

		for (Map.Entry<String, Object> entity : this.properties.entrySet()) {
			resultMap.put(entity.getKey(), entity.getValue());
		}

		return resultMap;
	}
}
