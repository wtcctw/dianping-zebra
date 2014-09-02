package com.dianping.zebra.group.filter;

/**
 * Created by Dozer on 9/2/14.
 * Filters read metadata from this class
 */
public class JdbcMetaData implements Cloneable {
	private String jdbcUrl;

	public JdbcMetaData clone() {
		try {
			return (JdbcMetaData) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
}
