package com.dianping.zebra.group.router;

public class GroupDataSourceRouterInfo {

	private String sql;

	public GroupDataSourceRouterInfo(String sql) {
		super();
		this.sql = sql;
	}

	public GroupDataSourceRouterInfo() {
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	@Override
	public String toString() {
		return String.format("GroupDataSourceRouterInfo [sql=%s]", sql);
	}

}
