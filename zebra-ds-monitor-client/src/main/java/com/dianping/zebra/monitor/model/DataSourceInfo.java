package com.dianping.zebra.monitor.model;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.net.URLEncoder;

/**
 * Created by Dozer on 8/13/14.
 */
public class DataSourceInfo {
	private static String app;

	private static String ip;

	private static String version;

	private String dataSourceBeanName;

	private String dataSourceBeanClass;

	private String database;

	private String url;

	private String username;

	private boolean replaced;

	private String initPoolSize;

	private String maxPoolSize;

	private String minPoolSize;

	private String type;

	static {
		//todo: read from phoenix env
	}

	public static String getApp() {
		return app;
	}

	public static void setApp(String app) {
		DataSourceInfo.app = app;
	}

	public static String getIp() {
		return ip;
	}

	public static void setIp(String ip) {
		DataSourceInfo.ip = ip;
	}

	public String getDataSourceBeanName() {
		return dataSourceBeanName;
	}

	public void setDataSourceBeanName(String dataSourceBeanName) {
		this.dataSourceBeanName = dataSourceBeanName;
	}

	public String getDataSourceBeanClass() {
		return dataSourceBeanClass;
	}

	public void setDataSourceBeanClass(String dataSourceBeanClass) {
		this.dataSourceBeanClass = dataSourceBeanClass;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
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

	public boolean getReplaced() {
		return replaced;
	}

	public void setReplaced(boolean replaced) {
		this.replaced = replaced;
	}

	public String getInitPoolSize() {
		return initPoolSize;
	}

	public void setInitPoolSize(String initPoolSize) {
		this.initPoolSize = initPoolSize;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override public String toString() {
		final StringBuffer sb = new StringBuffer();

		PropertyDescriptor[] props = BeanUtils.getPropertyDescriptors(this.getClass());
		for (PropertyDescriptor prop : props) {
			if (prop.getName().equals("class")) {
				continue;
			}
			try {
				Object value = prop.getReadMethod().invoke(this);

				if (value == null) {
					continue;
				}

				if (sb.length() > 0) {
					sb.append("&");
				}
				sb.append(prop.getName()).append("=")
						.append(URLEncoder.encode(String.valueOf(value), "utf8"));
			} catch (Exception e) {
			}
		}
		return sb.toString();
	}
}
