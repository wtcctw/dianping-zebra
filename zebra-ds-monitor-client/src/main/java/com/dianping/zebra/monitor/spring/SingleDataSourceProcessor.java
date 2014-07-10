package com.dianping.zebra.monitor.spring;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.datasources.SingleDataSourceManagerFactory;
import com.mchange.v2.beans.BeansUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class SingleDataSourceProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (ComboPooledDataSource.class.isInstance(bean.getClass())) {
			ComboPooledDataSource c3p0Ds = (ComboPooledDataSource) bean;

			Map<String, String> properties = new HashMap<String, String>();
			try {
				BeansUtils.extractAccessiblePropertiesToMap(properties, c3p0Ds);
			} catch (IntrospectionException e) {
				throw new BeansException("get properties from C3P0 DataSource failed!", e) {
					private static final long serialVersionUID = -7778035191097930206L;
				};
			}

			DataSourceConfig config = new DataSourceConfig();

			config.setJdbcUrl(c3p0Ds.getJdbcUrl());
			properties.remove("jdbcUrl");
			config.setUser(c3p0Ds.getUser());
			properties.remove("user");
			config.setPassword(c3p0Ds.getPassword());
			properties.remove("password");

			for (Entry<String, String> entity : properties.entrySet()) {
				Any any = new Any();
				any.setName(entity.getKey());
				any.setValue(entity.getValue());
				config.getProperties().add(any);
			}

			return SingleDataSourceManagerFactory.getDataSourceManager().createDataSource(null, config);
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}