package com.dianping.zebra.admin.dto;

import java.util.LinkedList;
import java.util.List;

public class DmlResultDto {

	private String ruleName;

	private String sql;

	private int targetSQLCount = 0;

	private boolean isCrossDb = false;

	private boolean isFullTableScan = false;

	private String pk;

	private List<RouterItem> routerItems = new LinkedList<RouterItem>();

	private List<String> dataHeaders = new LinkedList<String>();
	
	private List<DataRow> dataRows = new LinkedList<DataRow>();

	private boolean isSuccess = true;

	private String errorMsg;

	public void addDataHeader(String header){
		this.dataHeaders.add(header);
	}
	
	public List<String> getDataHeaders() {
		return dataHeaders;
	}

	public void addRouterItem(RouterItem item){
		this.routerItems.add(item);
	}

	public void addDataRow(DataRow dataRow){
		this.dataRows.add(dataRow);
	}
	
	public List<DataRow> getDataRows() {
		return dataRows;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getPk() {
		return pk;
	}

	public List<RouterItem> getRouterItems() {
		return routerItems;
	}

	public String getRuleName() {
		return ruleName;
	}

	public String getSql() {
		return sql;
	}

	public int getTargetSQLCount() {
		return targetSQLCount;
	}

	public boolean isCrossDb() {
		return isCrossDb;
	}

	public boolean isFullTableScan() {
		return isFullTableScan;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setCrossDb(boolean isCrossDb) {
		this.isCrossDb = isCrossDb;
	}

	public void setDataRows(List<DataRow> dataRows) {
		this.dataRows = dataRows;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setFullTableScan(boolean isFullTableScan) {
		this.isFullTableScan = isFullTableScan;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public void setRouterItems(List<RouterItem> routerItems) {
		this.routerItems = routerItems;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public void setTargetSQLCount(int targetSQLCount) {
		this.targetSQLCount = targetSQLCount;
	}

	public static class DataRow {
		private int effectRows = -1;

		private List<String> datas = new LinkedList<String>();

		public DataRow(){
		}

		public DataRow(int effectRows) {
			this.effectRows = effectRows;
		}
		
		public void addObject(String o){
			this.datas.add(o);
		}
		
		public List<String> getDatas() {
			return datas;
		}

		public int getEffectRows() {
			return effectRows;
		}

		public void setDatas(List<String> datas) {
			this.datas = datas;
		}

		public void setEffectRows(int effectRows) {
			this.effectRows = effectRows;
		}
	}

	public static class RouterItem {
		private String targetJdbcRef;

		private String targetTable;

		private String targetSql;
		
		public RouterItem(String targetJdbcRef, String targetTable, String targetSql) {
	      this.targetJdbcRef = targetJdbcRef;
	      this.targetTable = targetTable;
	      this.targetSql = targetSql;
      }

		public String getTargetJdbcRef() {
			return targetJdbcRef;
		}

		public String getTargetSql() {
			return targetSql;
		}

		public String getTargetTable() {
			return targetTable;
		}

		public void setTargetJdbcRef(String targetJdbcRef) {
			this.targetJdbcRef = targetJdbcRef;
		}

		public void setTargetSql(String targetSql) {
			this.targetSql = targetSql;
		}

		public void setTargetTable(String targetTable) {
			this.targetTable = targetTable;
		}
	}
}
