package com.dianping.zebra.biz.entity;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PumaClientSyncTaskEntity {
	
	private static Type type = new TypeToken<Map<String, PumaClientSyncTaskBaseEntity>>() {  
   }.getType();

	/**
	 * id
	 */
	private int id;

	/**
	 * 分库分表规则
	 */
	private String ruleName;

	/**
	 * puma client名
	 */
	private String pumaClientName;

	/**
	 * 监听的数据库
	 */
	private String pumaDatabase;

	/**
	 * 监听的表，多个表用逗号分隔
	 */
	private String pumaTables;

	/**
	 * 需要分库分表配置
	 */
	private Map<String, PumaClientSyncTaskBaseEntity> pumaBaseEntities = new HashMap<String, PumaClientSyncTaskBaseEntity>();

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

	public String getPumaClientName() {
		return pumaClientName;
	}

	public void setPumaClientName(String pumaClientName) {
		this.pumaClientName = pumaClientName;
	}

	public Map<String, PumaClientSyncTaskBaseEntity> getPumaBaseEntities() {
		return pumaBaseEntities;
	}

	public void setPumaBaseEntities(Map<String, PumaClientSyncTaskBaseEntity> pumaBaseEntities) {
		this.pumaBaseEntities = pumaBaseEntities;
	}

	public void addPumaTable(String table) {
		if (this.pumaTables == null || this.pumaTables.length() == 0) {
			this.pumaTables = table;
		} else {
			this.pumaTables += "," + table;
		}
	}
	
	public String getPumaBaseEntitiesJson() {
		Gson gson = new Gson();
		return gson.toJson(this.pumaBaseEntities);
	}

	public void setPumaBaseEntitiesJson(String pumaBaseEntitiesJson) {
		Gson gson = new Gson();
		this.pumaBaseEntities = gson.fromJson(pumaBaseEntitiesJson, type);
	}

	@Override
   public String toString() {
	   return "PumaClientSyncTaskEntity [id=" + id + ", ruleName=" + ruleName + ", pumaClientName=" + pumaClientName
	         + ", pumaDatabase=" + pumaDatabase + ", pumaTables=" + pumaTables + ", pumaBaseEntities="
	         + pumaBaseEntities + ", executor=" + executor + ", executor1=" + executor1 + ", executor2=" + executor2
	         + ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
   }
}
