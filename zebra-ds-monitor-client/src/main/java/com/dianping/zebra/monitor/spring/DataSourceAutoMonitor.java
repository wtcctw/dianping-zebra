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
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.jdbc.SingleDataSource;
import com.dianping.zebra.group.util.StringUtils;
import com.dianping.zebra.monitor.sql.MonitorableDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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

		if (dataSourceDefinition.getBeanClassName().equals(C3P0_CLASS_NAME)) {
			JdbcUrlInfo info = getBeanJdbcInfo(dataSourceDefinition);
			if ("mysql".equals(info.getType())) {
				dataSourceDefinition.setBeanClassName(SingleDataSource.class.getName());
				dataSourceDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, beanName);
				dataSourceDefinition.getConstructorArgumentValues().addIndexedArgumentValue(1, false);

				if (dataSourceDefinition instanceof AbstractBeanDefinition) {
					((AbstractBeanDefinition) dataSourceDefinition).setInitMethodName("init");
				}
				Cat.logEvent("DAL.BeanFactory", String.format("Replace-%s(%s)", beanName, newBeanName));
			} else {
				Cat.logEvent("DAL.BeanFactory", String.format("NotMysql-%s(%s)", beanName, newBeanName));
			}
		} else if (dataSourceDefinition.getBeanClassName().equals(DPDL_CLASS_NAME)) {
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

				Cat.logEvent("DAL.BeanFactory", String.format("Replace-%s(%s)", beanName, newBeanName));
			} else {
				Cat.logEvent("DAL.BeanFactory",
						String.format("GroupConfigNotFound-%s(%s)", beanName, newBeanName));
			}
		} else {
			Cat.logEvent("DAL.BeanFactory",
					String.format("Ignore-%s(%s)-%s", beanName, newBeanName, dataSourceDefinition.getBeanClassName()));
		}

		listableBeanFactory.registerBeanDefinition(newBeanName, dataSourceDefinition);

		GenericBeanDefinition monitorableDataSourceDefinition = new GenericBeanDefinition();
		monitorableDataSourceDefinition.setBeanClass(MonitorableDataSource.class);
		monitorableDataSourceDefinition.getConstructorArgumentValues().addGenericArgumentValue(
				new RuntimeBeanReference(newBeanName));
		return monitorableDataSourceDefinition;
	}

	private Set<PropertyValue> getDpdlInnerDsPropertyValues(BeanDefinition writeDsBean) {
		Set<PropertyValue> properties = new HashSet<PropertyValue>();

		if (writeDsBean.getBeanClassName().equals(C3P0_CLASS_NAME) || writeDsBean.getBeanClassName()
				.equals(SingleDataSource.class.getName())) {

			JdbcUrlInfo info = getBeanJdbcInfo(writeDsBean);

			if (info.getType().equals("mysql")) {
				String groupConfig = getLionConfig(String.format("groupds.%s.mapping", info.getDataBase()));
				if (!StringUtils.isBlank(groupConfig)) {
					properties.add(new PropertyValue("jdbcRef", info.getDataBase()));

					Set<String> ignoreList = getGroupDataSourceIgnoreProperties();
					for (PropertyValue property : writeDsBean.getPropertyValues().getPropertyValues()) {
						if (ignoreList.contains(property.getName())) {
							continue;
						}
						properties.add(property);
					}

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

	private String getLionConfig(String key) {
		try {
			return ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).getProperty(key);
		} catch (LionException e) {
			return null;
		}
	}

	private JdbcUrlInfo getBeanJdbcInfo(BeanDefinition bean) {
		String url = ((TypedStringValue) bean.getPropertyValues().getPropertyValue("jdbcUrl").getValue())
				.getValue().trim();
		if (url.startsWith("${") && url.endsWith("}")) {
			url = url.substring(2, url.length() - 1);
			url = getLionConfig(url);
		}
		return getJdbcUrlInfo(url);
	}

	private Set<String> getGroupDataSourceIgnoreProperties() {
		Set<String> result = new HashSet<String>();
		result.add("jdbcUrl");
		result.add("password");
		result.add("user");
		result.add("driverClass");
		return result;
	}

	private JdbcUrlInfo getJdbcUrlInfo(String url) {
		JdbcUrlInfo info = new JdbcUrlInfo();
		Pattern p = Pattern.compile("^jdbc:([a-zA-Z0-9]+):\\/\\/([0-9\\.:]+)\\/([^?]+).*$");
		Matcher m = p.matcher(url);
		if (m.find()) {
			info.setType(m.group(1).toLowerCase());
			info.setIp(m.group(2).toLowerCase());
			info.setDataBase(m.group(3).toLowerCase());
		}
		return info;
	}

	static class JdbcUrlInfo {
		private String dataBase;

		private String type;

		private String ip;

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getDataBase() {
			return dataBase;
		}

		public void setDataBase(String dataBase) {
			this.dataBase = dataBase;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}
}
