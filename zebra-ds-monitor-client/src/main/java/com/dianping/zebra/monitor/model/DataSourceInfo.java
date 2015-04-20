package com.dianping.zebra.monitor.model;

import org.unidal.net.Networks;

import com.dianping.zebra.Constants;
import com.dianping.zebra.util.AppPropertiesUtils;

/**
 * Created by Dozer on 8/13/14.
 */
public class DataSourceInfo {

	private String app;
	
	private String ip;
	
	private String dataSourceBeanClass;

	private String dataSourceBeanName;

	private String database;

	private String initPoolSize;

	private String maxPoolSize;

	private String minPoolSize;

	private boolean replaced;

	private String type;

	private String url;

	private String username;

	public DataSourceInfo() {
	}

	public DataSourceInfo(String beanName) {
		this.app = AppPropertiesUtils.getAppName();
		this.ip = Networks.forIp().getLocalHostAddress();
		this.dataSourceBeanName = beanName;
	}

	public String getApp() {
		return app;
	}

	public String getDataSourceBeanClass() {
		return dataSourceBeanClass;
	}

	public void setDataSourceBeanClass(String dataSourceBeanClass) {
		this.dataSourceBeanClass = dataSourceBeanClass;
	}

	public String getDataSourceBeanName() {
		return dataSourceBeanName;
	}

	public void setDataSourceBeanName(String dataSourceBeanName) {
		this.dataSourceBeanName = dataSourceBeanName;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getInitPoolSize() {
		return initPoolSize;
	}

	public void setInitPoolSize(String initPoolSize) {
		this.initPoolSize = initPoolSize;
	}

	public String getIp() {
		return ip;
	}

	public String getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(String maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public String getMinPoolSize() {
		return minPoolSize;
	}

	public void setMinPoolSize(String minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public boolean getReplaced() {
		return replaced;
	}

	public void setReplaced(boolean replaced) {
		this.replaced = replaced;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVersion() {
		return Constants.ZEBRA_VERSION;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
