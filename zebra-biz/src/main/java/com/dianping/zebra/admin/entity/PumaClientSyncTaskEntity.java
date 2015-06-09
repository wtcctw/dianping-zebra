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
	 * 监听的数据库
	 */
	private String pumaDatabase;

	/**
	 * 监听的表，多个表用逗号分隔
	 */
	private String pumaTables;

	/**
	 * 执行任务的服务器
	 */
	private String executor;

	/**
	 * 状态： 1.创建 2.生效 3.暂停
	 */
	private String status;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

    /**
     * 消费位置
     */
    private long sequence;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }
}
