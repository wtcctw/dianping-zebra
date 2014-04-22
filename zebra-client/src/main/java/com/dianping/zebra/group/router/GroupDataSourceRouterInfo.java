package com.dianping.zebra.group.router;

public class GroupDataSourceRouterInfo {

	private String sql;
	
	private boolean forceWrite;

	public GroupDataSourceRouterInfo(String sql) {
		super();
		this.sql = sql;
	}
	
	public GroupDataSourceRouterInfo(String sql,boolean forceWrite) {
		super();
		this.sql = sql;
		this.forceWrite = forceWrite;
	}

	public String getSql() {
		return sql;
	}

	public boolean isForceWrite(){
		return forceWrite;
	}
}
