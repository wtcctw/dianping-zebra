package com.dianping.zebra.group.filter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Dozer on 9/2/14.
 * Filters read metadata from this class
 */
public class JdbcMetaData implements Cloneable {
	private DataSource dataSource;

	private String dataSourceClass;

	private String dataSourceId;

	private String jdbcPassword;

	private String jdbcRef;

	private String jdbcUrl;

	private String jdbcUsername;

	private Properties properties;

	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public JdbcMetaData clone() {
		try {
			return (JdbcMetaData) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getDataSourceClass() {
		return dataSourceClass;
	}

	public void setDataSourceClass(String dataSourceClass) {
		this.dataSourceClass = dataSourceClass;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}

	public String getJdbcRef() {
		return jdbcRef;
	}

	public void setJdbcRef(String jdbcRef) {
		this.jdbcRef = jdbcRef;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
	}

	public Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
		}
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override public String toString() {
		return "JdbcMetaData{" +
				"dataSourceClass='" + dataSourceClass + '\'' +
				", dataSourceId='" + dataSourceId + '\'' +
				", jdbcPassword='" + jdbcPassword + '\'' +
				", jdbcRef='" + jdbcRef + '\'' +
				", jdbcUrl='" + jdbcUrl + '\'' +
				", jdbcUsername='" + jdbcUsername + '\'' +
				'}';
	}
}
