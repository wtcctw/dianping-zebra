package com.dianping.zebra.biz.dto;

import com.dianping.zebra.biz.entity.PumaClientSyncTaskEntity;

public class PumaClientSyncTaskDto extends PumaClientSyncTaskEntity{

	private String shardColumn;

	public String getShardColumn() {
		return shardColumn;
	}

	public void setShardColumn(String shardColumn) {
		this.shardColumn = shardColumn;
	}
}
