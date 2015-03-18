package com.dianping.zebra.admin.dto;

import java.util.Date;

import com.dianping.zebra.group.config.system.entity.SqlFlowControl;

public class FlowControlDto extends SqlFlowControl {
	
	private int id;
	
	private String sql;
	
	private String ip;
	
	private int index;
	
	private Date createTime;
	
	private Date updateTime;
	
	public FlowControlDto(){
	}
	
	public FlowControlDto(SqlFlowControl sqlFlowContorl) {
		this.setSqlId(sqlFlowContorl.getSqlId());
		this.setAllowPercent(sqlFlowContorl.getAllowPercent());
		this.setApp(sqlFlowContorl.getApp());
		this.setIndex(this.getAllowPercent() / 10);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}