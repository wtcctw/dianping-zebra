package com.dianping.zebra.group.filter;

/**
 * Created by Dozer on 9/2/14.
 * Filters read metadata from this class
 */
public class JdbcMetaData implements Cloneable {
	private String jdbcUrl;

	private String jdbcRef;

	public String getJdbcRef() {
		return jdbcRef;
	}

	public void setJdbcRef(String jdbcRef) {
		this.jdbcRef = jdbcRef;
	}

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

	@Override public String toString() {
		return "JdbcMetaData{" +
				"jdbcUrl='" + jdbcUrl + '\'' +
				", jdbcRef='" + jdbcRef + '\'' +
				'}';
	}
}
