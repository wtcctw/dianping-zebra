package com.dianping.zebra.group.config;

import java.util.HashMap;
import java.util.Map;

public class DatasourceConfig {

	private String id;

	private String driverClass;

	private String poolClass;

	private String connectionUrl;

	private String userName;

	private String password;

	private String initMethod;

	private String destroyMethod;
	
	private boolean readOnly;
	
	private int weight;

	private Map<String, String> poolProperties = new HashMap<String, String>();

	// for future use
	private Map<String, String> properties = new HashMap<String, String>();

	private String version;

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public String getDestroyMethod() {
		return destroyMethod;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public String getId() {
		return id;
	}

	public String getInitMethod() {
		return initMethod;
	}

	public String getPassword() {
		return password;
	}

	public String getPoolClass() {
		return poolClass;
	}

	public Map<String, String> getPoolProperties() {
		return poolProperties;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public String getUserName() {
		return userName;
	}

	public String getVersion() {
		return version;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public void setDestroyMethod(String destroyMethod) {
		this.destroyMethod = destroyMethod;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setInitMethod(String initMethod) {
		this.initMethod = initMethod;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPoolClass(String poolClass) {
		this.poolClass = poolClass;
	}

	public void setPoolProperties(Map<String, String> poolProperties) {
		this.poolProperties = poolProperties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
