package com.dianping.zebra.admin.admin.service;

import org.unidal.lookup.annotation.Inject;

import com.dianping.cat.Cat;

public class DalConfigServiceImpl implements DalConfigService {

	private String project = "ds";

	private final String[] envs = new String[] { "dev", "alpha", "qa", "prelease", "product", "performance",
	      "product-hm" };

	@Inject
	private LionHttpService m_lionHttpService;

	@Override
	public boolean generateConfig(String name) {
		String url = String.format("%s.%s.jdbc.url", project, name);
		String user = String.format("%s.%s.jdbc.username", project, name);
		String password = String.format("%s.%s.jdbc.password", project, name);
		String driverClass = String.format("%s.%s.jdbc.driverClass", project, name);
		String properties = String.format("%s.%s.jdbc.properties", project, name);
		String active = String.format("%s.%s.jdbc.active", project, name);

		try {
			m_lionHttpService.createKey(project, url);
			m_lionHttpService.createKey(project, user);
			m_lionHttpService.createKey(project, password);
			m_lionHttpService.createKey(project, driverClass);
			m_lionHttpService.createKey(project, properties);
			m_lionHttpService.createKey(project, active);
		} catch (Throwable ignore) {
		}

		try {
			for (String env : envs) {
				String originUrl = m_lionHttpService.getConfig(env, url);
				if (originUrl == null || originUrl.length() == 0) {
					m_lionHttpService.setConfig(env, url, "jdbc:mysql://{ip}:{port}/{database}?characterEncoding=UTF8");
				} else {
					m_lionHttpService.setConfig(env, active, originUrl);
				}

				String originUser = m_lionHttpService.getConfig(env, user);
				if (originUser == null || originUser.length() == 0) {
					m_lionHttpService.setConfig(env, user, "");
				} else {
					m_lionHttpService.setConfig(env, active, originUser);
				}

				String originPassword = m_lionHttpService.getConfig(env, password);
				if (originPassword == null || originPassword.length() == 0) {
					m_lionHttpService.setConfig(env, password, "");
				} else {
					m_lionHttpService.setConfig(env, active, originPassword);
				}

				String originDriverClass = m_lionHttpService.getConfig(env, driverClass);
				if (originDriverClass == null || originDriverClass.length() == 0) {
					m_lionHttpService.setConfig(env, driverClass, "com.mysql.jdbc.Driver");
				}

				String originProperties = m_lionHttpService.getConfig(env, properties);
				if (originProperties == null || originProperties.length() == 0) {
					m_lionHttpService.setConfig(env, properties, "${ds.datasource.properties}");
				}

				String originActive = m_lionHttpService.getConfig(env, active);
				if (originActive == null || originActive.length() == 0) {
					m_lionHttpService.setConfig(env, active, "true");
				} else {
					m_lionHttpService.setConfig(env, active, originActive);
				}
			}
		} catch (Throwable t) {
			Cat.logError(t);
			return false;
		}

		return true;
	}

	@Override
	public void setProject(String project) {
		this.project = project;
	}
}
