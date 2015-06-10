package com.dianping.zebra.admin.dto;

import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;

public class PumaClientSyncTaskDto extends PumaClientSyncTaskEntity{

	private String shardColumn;

	public String getShardColumn() {
		return shardColumn;
	}

	public void setShardColumn(String shardColumn) {
		this.shardColumn = shardColumn;
	}
}
