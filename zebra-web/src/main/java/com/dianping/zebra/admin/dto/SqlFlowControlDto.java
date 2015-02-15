package com.dianping.zebra.admin.dto;

import com.dianping.zebra.group.config.system.entity.SqlFlowControl;

public class SqlFlowControlDto extends SqlFlowControl {
	private String sql;
	
	private String ip;

	public SqlFlowControlDto(SqlFlowControl sqlFlowContorl) {
		this.setSqlId(sqlFlowContorl.getSqlId());
		this.setAllowPercent(sqlFlowContorl.getAllowPercent());
		this.setApp(sqlFlowContorl.getApp());
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
}