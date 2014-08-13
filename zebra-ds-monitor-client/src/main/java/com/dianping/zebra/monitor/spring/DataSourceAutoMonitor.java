/**
 * Project: ${zebra-ds-monitor-client.aid}
 *
 * File Created at 2011-11-9
 * $Id$
 *
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.monitor.spring;

import com.dianping.cat.Cat;
import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.lion.client.ConfigCache;
import com.dianping.lion.client.LionException;
import com.dianping.zebra.group.exception.IllegalConfigException;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.jdbc.SingleDataSource;
import com.dianping.zebra.group.util.StringUtils;
import com.dianping.zebra.monitor.sql.MonitorableDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.ClassUtils;
import org.unidal.helper.Files;
import org.unidal.helper.Urls;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自动为spring容器中的所有DataSource添加monitor功能，配置在spring配置文件中即可，如下 <bean class="com.dianping.zebra.monitor.spring.DataSourceAutoMonitor"/>
 *
 * @author danson.liu
 */
public class DataSourceAutoMonitor implements BeanFactoryPostProcessor, PriorityOrdered {

	private static final Log logger = LogFactory.getLog(DataSourceAutoMonitor.class);

	private static final String ZEBRA_DATA_SOURCE_NAME = "com.dianping.zebra.jdbc.DPDataSource";

	private static final String C3P0_CLASS_NAME = "com.mchange.v2.c3p0.ComboPooledDataSource";

	private static final String DPDL_CLASS_NAME = "com.dianping.dpdl.sql.DPDataSource";

	private static int nameId = 0;

	private DefaultListableBeanFactory listableBeanFactory = null;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		listableBeanFactory = (DefaultListableBeanFactory) beanFactory;
		String[] beanDefinitionNames = listableBeanFactory.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) listableBeanFactory
					.getBeanDefinition(beanDefinitionName);
			try {
				Class<?> beanClazz = beanDefinition.resolveBeanClass(ClassUtils.getDefaultClassLoader());
				if (beanClazz != null && DataSource.class.isAssignableFrom(beanClazz)) {
					autoReplaceWithMonitorableDataSource(beanDefinitionName, beanDefinition, beanClazz);
				}
			} catch (ClassNotFoundException e) {
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info("All dataSource is auto monitored by zebra ds monitor.");
		}
	}

	private void autoReplaceWithMonitorableDataSource(String beanName, BeanDefinition dataSourceDefinition,
			Class<?> dataSourceClazz) {

		if (needToReplace(beanName, dataSourceDefinition)) {
			listableBeanFactory.registerBeanDefinition(beanName,
					createMonitorableBeanDefinition(beanName, dataSourceDefinition));
		}

		// zebra需做特殊处理，一些inner datasource可能作为nested bean方式定义，也需要wrapper
		if (isZebraDataSource(dataSourceClazz)) {
			replaceInnerDataSourceInZebra(beanName, dataSourceDefinition);
		}
	}

	@SuppressWarnings("unchecked")
	private void replaceInnerDataSourceInZebra(String beanName, BeanDefinition zebraDataSourceDefinition) {
		MutablePropertyValues propertyValues = zebraDataSourceDefinition.getPropertyValues();
		PropertyValue dataSourcePoolVal = propertyValues.getPropertyValue("dataSourcePool");

		if (dataSourcePoolVal == null) {
			if (logger.isWarnEnabled()) {
				logger.warn("Zebra dataSource's dataSourcePool property not found, maybe its name is modified, "
						+ "change the automonitor's implementation, otherwise some inner dataSource cannot be monitored.");
			}
			return;
		}

		Map<TypedStringValue, Object> innerDSDefinitionMap = (Map<TypedStringValue, Object>) dataSourcePoolVal.getValue();
		for (Entry<TypedStringValue, Object> innerDSDefEntry : innerDSDefinitionMap.entrySet()) {
			Object innerDSDefVal = innerDSDefEntry.getValue();
			if (innerDSDefVal instanceof BeanDefinitionHolder) {
				BeanDefinitionHolder innerDSDefHolder = (BeanDefinitionHolder) innerDSDefVal;
				if (needToReplace(innerDSDefHolder.getBeanName(), innerDSDefHolder.getBeanDefinition())) {
					BeanDefinition innerDSDefinition = innerDSDefHolder.getBeanDefinition();
					innerDSDefEntry.setValue(new BeanDefinitionHolder(createMonitorableBeanDefinition(beanName,
							innerDSDefinition), innerDSDefHolder.getBeanName(), innerDSDefHolder
							.getAliases()));
				}
			}
		}
	}

	private GenericBeanDefinition createMonitorableBeanDefinition(String beanName, BeanDefinition dataSourceDefinition) {
		String newBeanName = String.format("%s-z%d", beanName, nameId++);

		DataSourceInfo info = getBeanJdbcInfo(dataSourceDefinition, beanName);

		if (C3P0_CLASS_NAME.equals(info.getDataSourceBeanClass()) && canReplace(info)) {
			if ("mysql".equals(info.getType())) {
				dataSourceDefinition.setBeanClassName(SingleDataSource.class.getName());
				dataSourceDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, beanName);
				dataSourceDefinition.getConstructorArgumentValues().addIndexedArgumentValue(1, false);

				if (dataSourceDefinition instanceof AbstractBeanDefinition) {
					((AbstractBeanDefinition) dataSourceDefinition).setInitMethodName("init");
				}

				info.setReplaced(true);
				Cat.logEvent("DAL.BeanFactory", String.format("Replace-%s(%s)", beanName, newBeanName));
			} else {
				Cat.logEvent("DAL.BeanFactory", String.format("NotMysql-%s(%s)", beanName, newBeanName));
			}
		} else if (DPDL_CLASS_NAME.equals(info.getDataSourceBeanClass())) {
			BeanDefinition dpdlInnerDsBean = getDpdlInnerDsBean(dataSourceDefinition);
			Set<PropertyValue> properties = getDpdlInnerDsPropertyValues(dpdlInnerDsBean);

			if (properties.size() > 0) {
				dataSourceDefinition.setBeanClassName(GroupDataSource.class.getName());
				for (PropertyValue p : dataSourceDefinition.getPropertyValues().getPropertyValues()) {
					dataSourceDefinition.getPropertyValues().removePropertyValue(p);
				}
				for (PropertyValue entity : properties) {
					dataSourceDefinition.getPropertyValues().addPropertyValue(entity.getName(), entity.getValue());
				}
				if (dataSourceDefinition instanceof AbstractBeanDefinition) {
					((AbstractBeanDefinition) dataSourceDefinition).setInitMethodName("init");
				}

				info.setReplaced(true);
				Cat.logEvent("DAL.BeanFactory", String.format("Replace-%s(%s)", beanName, newBeanName));
			} else {
				Cat.logEvent("DAL.BeanFactory",
						String.format("IgnoreDpdl-%s(%s)", beanName, newBeanName));
			}
		} else {
			Cat.logEvent("DAL.BeanFactory",
					String.format("Ignore-%s(%s)-%s", beanName, newBeanName, dataSourceDefinition.getBeanClassName()));
		}

		uploadDataSourceInfo(info);

		listableBeanFactory.registerBeanDefinition(newBeanName, dataSourceDefinition);

		GenericBeanDefinition monitorableDataSourceDefinition = new GenericBeanDefinition();
		monitorableDataSourceDefinition.setBeanClass(MonitorableDataSource.class);
		monitorableDataSourceDefinition.getConstructorArgumentValues().addGenericArgumentValue(
				new RuntimeBeanReference(newBeanName));
		return monitorableDataSourceDefinition;
	}

	private void uploadDataSourceInfo(DataSourceInfo info) {
		String url = getLionConfig("zebra.server.heartbeat.url");
		if (StringUtils.isBlank(url)) {
			logger.error("zebra.v2.datasource.upload.url not exists!");
			Cat.logError(new IllegalConfigException("zebra.v2.datasource.upload.url not exists!"));
		}

		try {
			callHttp(String.format("%s?%s", url, info.toString()));
		} catch (IOException e) {
			String msg = String.format("Call Zebra Web failed! [%s]", url);
			logger.error(msg, e);
			Cat.logError("msg", e);
		}
	}

	private Set<PropertyValue> getDpdlInnerDsPropertyValues(BeanDefinition writeDsBean) {
		Set<PropertyValue> properties = new HashSet<PropertyValue>();

		if (!writeDsBean.getBeanClassName().equals(C3P0_CLASS_NAME) &&
				!writeDsBean.getBeanClassName().equals(SingleDataSource.class.getName())) {
			return properties;
		}

		DataSourceInfo info = getBeanJdbcInfo(writeDsBean, null);

		if ("mysql".equals(info.getType()) && canReplace(info)) {
			String groupConfig = getLionConfig(String.format("groupds.%s.mapping", info.getDatabase()));
			if (!StringUtils.isBlank(groupConfig)) {
				properties.add(new PropertyValue("jdbcRef", info.getDatabase()));

				Set<String> ignoreList = getGroupDataSourceIgnoreProperties();
				for (PropertyValue property : writeDsBean.getPropertyValues().getPropertyValues()) {
					if (ignoreList.contains(property.getName())) {
						continue;
					}
					properties.add(property);
				}

			}
		}
		return properties;
	}

	private BeanDefinition getDpdlInnerDsBean(BeanDefinition dataSourceDefinition) {
		String writeDsName = ((TypedStringValue) dataSourceDefinition.getPropertyValues().getPropertyValue("writeDS")
				.getValue()).getValue();
		BeanDefinition writeDsBean = listableBeanFactory.getBeanDefinition(writeDsName);

		if (writeDsBean.getBeanClassName().equals(MonitorableDataSource.class.getName())) {
			String innerName = ((RuntimeBeanReference) ((ConstructorArgumentValues.ValueHolder) writeDsBean
					.getConstructorArgumentValues().getGenericArgumentValues().get(0)).getValue())
					.getBeanName();
			writeDsBean = listableBeanFactory.getBeanDefinition(innerName);
		}
		return writeDsBean;
	}

	private boolean isZebraDataSource(Class<?> dataSourceClazz) {
		return ZEBRA_DATA_SOURCE_NAME.equals(dataSourceClazz.getName());
	}

	private boolean needToReplace(String beanName, BeanDefinition dataSourceDefinition) {
		if (dataSourceDefinition.getBeanClassName().equals(MonitorableDataSource.class.getName())) {
			return false;
		}
		if (dataSourceDefinition.getBeanClassName().equals(SingleDataSource.class.getName())) {
			return false;
		}
		if (dataSourceDefinition.getBeanClassName().equals(GroupDataSource.class.getName())) {
			return false;
		}

		if (beanName != null && beanName.matches("^.*z\\d+$")) {
			return false;
		}
		return true;
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE - 1;
	}

	private boolean isLionKey(String key) {
		return key.startsWith("${") && key.endsWith("}");
	}

	private String trimLionKey(String key) {
		if (isLionKey(key)) {
			key = key.substring(2, key.length() - 1);
		}
		return key;
	}

	private String getLionConfig(String key) {
		try {
			return ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).getProperty(key);
		} catch (LionException e) {
			return null;
		}
	}

	private DataSourceInfo getBeanJdbcInfo(BeanDefinition bean, String beanName) {
		DataSourceInfo info = new DataSourceInfo();
		info.setDataSourceBeanName(beanName);
		info.setDataSourceBeanClass(bean.getBeanClassName());

		getJdbcUrlInfo(info, getLionValueFromBean(bean, "jdbcUrl"));
		info.setUsername(getLionValueFromBean(bean, "user"));
		info.setInitPoolSize(getLionValueFromBean(bean, "initPoolSize"));
		info.setMaxPoolSize(getLionValueFromBean(bean, "maxPoolSize"));
		info.setMinPoolSize(getLionValueFromBean(bean, "minPoolSize"));

		return info;
	}

	private String getLionValueFromBean(BeanDefinition bean, String key) {
		try {
			String value = ((TypedStringValue) bean.getPropertyValues().getPropertyValue(key).getValue())
					.getValue().trim();

			return isLionKey(value) ? getLionConfig(trimLionKey(value)) : value;
		} catch (Exception e) {
			return null;
		}
	}

	private Set<String> getGroupDataSourceIgnoreProperties() {
		Set<String> result = new HashSet<String>();
		result.add("jdbcUrl");
		result.add("password");
		result.add("user");
		result.add("driverClass");
		return result;
	}

	private void getJdbcUrlInfo(DataSourceInfo info, String url) {
		info.setUrl(url);

		if (url == null) {
			return;
		}

		Pattern p = Pattern.compile("^jdbc:([a-zA-Z0-9]+):\\/\\/([^\\/]+)\\/([^?]+).*$");
		Matcher m = p.matcher(url);
		if (m.find()) {
			info.setType(m.group(1).toLowerCase());
			info.setDatabase(m.group(3).toLowerCase());
		}
	}

	private String callHttp(String url) throws IOException {
		InputStream inputStream = Urls.forIO().connectTimeout(1000).readTimeout(5000).openStream(url);
		return Files.forIO().readFrom(inputStream, "utf-8");
	}

	private boolean canReplace(DataSourceInfo info) {
		String configString = getLionConfig("groupds.autoreplace.database");

		if (StringUtils.isBlank(info.getDatabase()) || StringUtils.isBlank(configString)) {
			return false;
		}
		if ("all".equals(configString)) {
			return true;
		}

		List<String> dataBases = Arrays.asList(configString.toLowerCase().split(","));
		return dataBases.contains(info.getDatabase());
	}

	static class DataSourceInfo {
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
}
