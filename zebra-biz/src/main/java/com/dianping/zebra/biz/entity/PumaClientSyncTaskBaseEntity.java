package com.dianping.zebra.biz.entity;

public class PumaClientSyncTaskBaseEntity {
	
	private String tableName;
	
	private String pk;
	
	private String dbRule;
	
	private String dbIndex;
	
	private String tbRule;
	
	private String tbSuffix;

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

	public String getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(String dbIndex) {
		this.dbIndex = dbIndex;
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

	@Override
   public String toString() {
	   return "PumaClientSyncTaskBaseEntity [tableName=" + tableName + ", pk=" + pk + ", dbRule=" + dbRule
	         + ", dbIndex=" + dbIndex + ", tbRule=" + tbRule + ", tbSuffix=" + tbSuffix + "]";
   }
}
