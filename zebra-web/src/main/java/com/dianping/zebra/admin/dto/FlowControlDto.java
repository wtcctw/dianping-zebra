package com.dianping.zebra.admin.dto;

import com.dianping.zebra.group.config.system.entity.SqlFlowControl;

public class FlowControlDto extends SqlFlowControl {
	private String sql;
	
	private String ip;
	
	private int index;
	
	public FlowControlDto(SqlFlowControl sqlFlowContorl) {
		this.setSqlId(sqlFlowContorl.getSqlId());
		this.setAllowPercent(sqlFlowContorl.getAllowPercent());
		this.setApp(sqlFlowContorl.getApp());
		this.setIndex(this.getAllowPercent() / 10);
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}