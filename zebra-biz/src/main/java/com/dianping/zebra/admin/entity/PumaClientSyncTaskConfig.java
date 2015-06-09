package com.dianping.zebra.admin.entity;

public class PumaClientSyncTaskConfig {

	/**
	 * id
	 */
	private String id;
	
	/**
	 * 分库分表规则
	 */
	private String ruleName;
	
	/**
	 * 表名
	 */
	private String table;

	/**
	 * 数据库规则分配策略
	 */
	private String dbRule;

	/**
	 * 数据库列表
	 */
	private String dbIndexes;

	/**
	 * 表分配策略
	 */
	private String tbRule;

	/**
	 * 表后缀名
	 */
	private String tbSuffix;

	/**
	 * puma任务名
	 */
	private String pumaTaskName;

	/**
	 * 同步服务器IP地址
	 */
	private String syncServerIp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getDbRule() {
		return dbRule;
	}

	public void setDbRule(String dbRule) {
		this.dbRule = dbRule;
	}

	public String getDbIndexes() {
		return dbIndexes;
	}

	public void setDbIndexes(String dbIndexes) {
		this.dbIndexes = dbIndexes;
	}

	public String getTbRule() {
		return tbRule;
	}

	public void setTbRule(String tbRule) {
		this.tbRule = tbRule;
	}

	public String getTbSuffix() {
		return tbSuffix;
	}

	public void setTbSuffix(String tbSuffix) {
		this.tbSuffix = tbSuffix;
	}

	public String getPumaTaskName() {
		return pumaTaskName;
	}

	public void setPumaTaskName(String pumaTaskName) {
		this.pumaTaskName = pumaTaskName;
	}

	public String getSyncServerIp() {
		return syncServerIp;
	}

	public void setSyncServerIp(String syncServerIp) {
		this.syncServerIp = syncServerIp;
	}
}
