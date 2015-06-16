package com.dianping.zebra.admin.entity;

import java.util.Date;

public class PumaClientSyncTaskEntity {

	/**
	 * id
	 */
	private int id;

	/**
	 * 分库分表规则
	 */
	private String ruleName;

	/**
	 * 逻辑表名
	 */
	private String tableName;

	/**
	 * 主键字段
	 */
	private volatile String pk;

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
	 * 监听的数据库
	 */
	private String pumaDatabase;

	/**
	 * 监听的表，多个表用逗号分隔
	 */
	private String pumaTables;

	/**
	 * 当前执行任务的服务器
	 */
	private String executor;

	/**
	 * 备选执行任务的服务器
	 */
	private String executor1;

	/**
	 * 备选执行任务的服务器
	 */
	private String executor2;

	/**
	 * 状态： 1.创建 2.生效 3.暂停
	 */
	private int status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
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

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getPumaDatabase() {
		return pumaDatabase;
	}

	public void setPumaDatabase(String pumaDatabase) {
		this.pumaDatabase = pumaDatabase;
	}

	public String getPumaTables() {
		return pumaTables;
	}

	public void setPumaTables(String pumaTables) {
		this.pumaTables = pumaTables;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getExecutor1() {
		return executor1;
	}

	public void setExecutor1(String executor1) {
		this.executor1 = executor1;
	}

	public String getExecutor2() {
		return executor2;
	}

	public void setExecutor2(String executor2) {
		this.executor2 = executor2;
	}
}
