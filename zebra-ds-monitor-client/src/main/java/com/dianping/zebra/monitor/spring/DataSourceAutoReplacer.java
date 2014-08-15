package com.dianping.zebra.monitor.spring;

import com.dianping.cat.Cat;
import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.exception.DalException;
import com.dianping.zebra.group.exception.IllegalConfigException;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.jdbc.SingleDataSource;
import com.dianping.zebra.group.util.StringUtils;
import com.dianping.zebra.monitor.model.DataSourceInfo;
import com.dianping.zebra.monitor.util.LionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.config.ConstructorArgumentValues.ValueHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.ClassUtils;
import org.unidal.helper.Files;
import org.unidal.helper.Urls;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dozer on 8/13/14.
 */
public class DataSourceAutoReplacer implements BeanFactoryPostProcessor, PriorityOrdered {
	private static final String C3P0_CLASS_NAME = "com.mchange.v2.c3p0.ComboPooledDataSource";

	private static final String DPDL_CLASS_NAME = "com.dianping.dpdl.sql.DPDataSource";

	private static final String GROUP_CLASS_NAME = "com.dianping.zebra.group.jdbc.GroupDataSource";

	private static final Log logger = LogFactory.getLog(DataSourceAutoReplacer.class);

	private static boolean hasProcessd;

	private Set<String> c3p0Ds = new HashSet<String>();

	private Set<String> c3p0InDpdlDs = new HashSet<String>();

	private Set<String> dpdlDs = new HashSet<String>();

	private Set<String> groupds = new HashSet<String>();

	private DefaultListableBeanFactory listableBeanFactory = null;

	private Set<String> otherDs = new HashSet<String>();

	private Pattern pattern = Pattern.compile("^jdbc:([a-zA-Z0-9]+)://([^/]+)/([^?]+).*$");

	private String callHttp(String url) throws IOException {
		InputStream inputStream = Urls.forIO().connectTimeout(1000).readTimeout(5000).openStream(url);
		return Files.forIO().readFrom(inputStream, "utf-8");
	}

	private boolean canReplace(DataSourceInfo info) {
		String configString = LionUtil.getLionConfig("groupds.autoreplace.database");

		if (StringUtils.isBlank(info.getDatabase()) || StringUtils.isBlank(configString)) {
			return false;
		}
		if ("all".equals(configString)) {
			return true;
		}

		List<String> dataBases = Arrays.asList(configString.toLowerCase().split(","));
		return dataBases.contains(info.getDatabase());
	}

	private DataSourceInfo getDataSourceInfo(BeanDefinition bean, String beanName) {
		DataSourceInfo info = new DataSourceInfo();
		info.setDataSourceBeanName(beanName);
		info.setDataSourceBeanClass(bean.getBeanClassName());

		getJdbcUrlInfo(info, LionUtil.getLionValueFromBean(bean, "jdbcUrl"));
		info.setUsername(LionUtil.getLionValueFromBean(bean, "user"));
		info.setInitPoolSize(LionUtil.getLionValueFromBean(bean, "initialPoolSize"));
		info.setMaxPoolSize(LionUtil.getLionValueFromBean(bean, "maxPoolSize"));
		info.setMinPoolSize(LionUtil.getLionValueFromBean(bean, "minPoolSize"));

		return info;
	}

	private BeanDefinition getDpdlInnerDsBean(BeanDefinition dataSourceDefinition) {
		String writeDsName = ((TypedStringValue) dataSourceDefinition.getPropertyValues().getPropertyValue("writeDS")
				.getValue()).getValue();

		c3p0InDpdlDs.add(writeDsName);
		BeanDefinition writeDsBean = listableBeanFactory.getBeanDefinition(writeDsName);

		PropertyValue pv = dataSourceDefinition.getPropertyValues().getPropertyValue("readDS");
		if (pv != null && pv.getValue() != null) {
			ManagedMap map = (ManagedMap) pv.getValue();
			for (Object item : map.keySet()) {
				c3p0InDpdlDs.add(((TypedStringValue) item).getValue());
			}
		}

		return writeDsBean;
	}

	private Set<PropertyValue> getDpdlInnerDsPropertyValues(BeanDefinition writeDsBean, DataSourceInfo dpdlInfo) {
		Set<PropertyValue> properties = new HashSet<PropertyValue>();

		if (!writeDsBean.getBeanClassName().equals(C3P0_CLASS_NAME)
				&& !writeDsBean.getBeanClassName().equals(SingleDataSource.class.getName())) {
			return properties;
		}

		DataSourceInfo info = getDataSourceInfo(writeDsBean, null);
		dpdlInfo.setDatabase(info.getDatabase());
		dpdlInfo.setUrl(info.getUrl());
		dpdlInfo.setUsername(info.getUsername());
		dpdlInfo.setType(info.getType());

		if ("mysql".equals(info.getType()) && canReplace(info)) {
			String groupConfig = LionUtil.getLionConfig(String.format("groupds.%s.mapping", info.getDatabase()));
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

		Matcher m = pattern.matcher(url);
		if (m.find()) {
			info.setType(m.group(1).toLowerCase());
			info.setDatabase(m.group(3).toLowerCase());
		}
	}

	@Override
	public int getOrder() {
		// 必须在DataSourceAutoMonitor之前执行
		return Ordered.LOWEST_PRECEDENCE - 2;
	}

	@Override
	public synchronized void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		if (hasProcessd) {
			return;
		}
		hasProcessd = true;

		listableBeanFactory = (DefaultListableBeanFactory) beanFactory;
		String[] beanDefinitionNames = listableBeanFactory.getBeanDefinitionNames();

		for (String beanDefinitionName : beanDefinitionNames) {
			try {
				AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) listableBeanFactory
						.getBeanDefinition(beanDefinitionName);
				Class<?> beanClazz = beanDefinition.resolveBeanClass(ClassUtils.getDefaultClassLoader());
				if (beanClazz != null && DataSource.class.isAssignableFrom(beanClazz)) {
					if (beanClazz.getName().equals(C3P0_CLASS_NAME)) {
						c3p0Ds.add(beanDefinitionName);
					} else if (beanClazz.getName().equals(DPDL_CLASS_NAME)) {
						dpdlDs.add(beanDefinitionName);
					} else if (beanClazz.getName().equals(GROUP_CLASS_NAME)) {
						groupds.add(beanDefinitionName);
					} else {
						otherDs.add(beanDefinitionName);
					}
				}
			} catch (ClassNotFoundException e) {
			}
		}
		replace();
	}

	private void processC3P0() {
		// remove c3p0 bean which has already caculated in dpdl
		for (String bean : c3p0InDpdlDs) {
			c3p0Ds.remove(bean);
		}

		new DataSourceProcesser().process(c3p0Ds, new DataSourceProcesserTemplate() {
			@Override
			public void process(BeanDefinition dataSourceDefinition, String beanName, DataSourceInfo info) {
				if (c3p0InDpdlDs.contains(beanName)) {
					return;
				}

				if ("mysql".equals(info.getType()) && canReplace(info)) {
					dataSourceDefinition.setBeanClassName(SingleDataSource.class.getName());
					dataSourceDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, beanName);
					dataSourceDefinition.getConstructorArgumentValues().addIndexedArgumentValue(1, false);

					if (dataSourceDefinition instanceof AbstractBeanDefinition) {
						((AbstractBeanDefinition) dataSourceDefinition).setInitMethodName("init");
					}

					info.setReplaced(true);
					Cat.logEvent("DAL.BeanFactory", String.format("Replace-%s", beanName));
				} else {
					Cat.logEvent("DAL.BeanFactory",
							String.format("IgnoreC3P0-%s-%s-%s", beanName, info.getType(), info.getDatabase()));
				}
			}
		});
	}

	@SuppressWarnings("unused")
	private void processC3P0InDpdl() {
		new DataSourceProcesser().process(c3p0InDpdlDs, new DataSourceProcesserTemplate() {
			@Override
			public void process(BeanDefinition dataSourceDefinition, String beanName, DataSourceInfo info) {
				Cat.logEvent("DAL.BeanFactory", String.format("IgnoreC3P0InDpdl-%s", beanName));
			}
		});
	}

	private void processDpdl() {
		new DataSourceProcesser().process(dpdlDs, new DataSourceProcesserTemplate() {
			@Override
			public void process(BeanDefinition dataSourceDefinition, String beanName, DataSourceInfo info) {
				BeanDefinition dpdlInnerDsBean = getDpdlInnerDsBean(dataSourceDefinition);
				Set<PropertyValue> properties = getDpdlInnerDsPropertyValues(dpdlInnerDsBean, info);

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
						((AbstractBeanDefinition) dataSourceDefinition).setDestroyMethodName("close");
					}

					info.setReplaced(true);
					Cat.logEvent("DAL.BeanFactory", String.format("Replace-%s", beanName));
				} else {
					Cat.logEvent("DAL.BeanFactory", String.format("IgnoreDpdl-%s", beanName));
				}
			}
		});
	}

	private void processGroupDs() {
		new DataSourceProcesser().process(groupds, new DataSourceProcesserTemplate() {
			@Override
			public void process(BeanDefinition dataSourceDefinition, String beanName, DataSourceInfo info) {
				String jdbcRef = null;
				PropertyValue propertyValue = dataSourceDefinition.getPropertyValues().getPropertyValue("jdbcRef");

				if (propertyValue == null) {
					ConstructorArgumentValues.ValueHolder valueHolder = (ValueHolder) dataSourceDefinition
							.getConstructorArgumentValues().getGenericArgumentValues().get(0);
					jdbcRef = ((TypedStringValue) valueHolder.getValue()).getValue();
				} else {
					jdbcRef = ((TypedStringValue) propertyValue.getValue()).getValue();
				}

				if (!StringUtils.isBlank(jdbcRef)) {
					DataSourceConfigManager manager = DataSourceConfigManagerFactory.getConfigManager(
							Constants.CONFIG_MANAGER_TYPE_REMOTE, jdbcRef, false, false);
					GroupDataSourceConfig config = manager.getGroupDataSourceConfig();
					if (config.getDataSourceConfigs().size() > 0) {
						DataSourceConfig dsConfig = config.getDataSourceConfigs().values().iterator().next();
						getJdbcUrlInfo(info, dsConfig.getJdbcUrl());
						info.setUsername(dsConfig.getUsername());
					}
				}

				Cat.logEvent("DAL.BeanFactory",
						String.format("Ignore-%s-%s", beanName, dataSourceDefinition.getBeanClassName()));
			}
		});
	}

	private void processOther() {
		new DataSourceProcesser().process(otherDs, new DataSourceProcesserTemplate() {
			@Override
			public void process(BeanDefinition dataSourceDefinition, String beanName, DataSourceInfo info) {
				// TODO
			}
		});
	}

	private void replace() {
		processDpdl();
		processC3P0();
		// processC3P0InDpdl();
		processGroupDs();
		processOther();
	}

	private void uploadDataSourceInfo(DataSourceInfo info) {
		String url = LionUtil.getLionConfig("zebra.server.heartbeat.url");
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

	interface DataSourceProcesserTemplate {
		void process(BeanDefinition bean, String beanName, DataSourceInfo info);
	}

	class DataSourceProcesser {
		public void process(Set<String> ds, DataSourceProcesserTemplate template) {
			for (String beanName : ds) {
				BeanDefinition dataSourceDefinition = listableBeanFactory.getBeanDefinition(beanName);
				DataSourceInfo info = getDataSourceInfo(dataSourceDefinition, beanName);

				try {
					template.process(dataSourceDefinition, beanName, info);
				} catch (Exception e) {
					String msg = String.format("DataSourceProcesser Error! bean:%s  class:%s", info.getDataSourceBeanName(),
							info.getDataSourceBeanClass());
					Exception exp = new DalException(e);
					logger.error(msg, exp);
					Cat.logError(msg, exp);
				}

				uploadDataSourceInfo(info);
			}
		}
	}
}
