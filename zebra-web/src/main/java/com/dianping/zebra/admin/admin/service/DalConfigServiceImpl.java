package com.dianping.zebra.admin.admin.service;

import org.unidal.lookup.annotation.Inject;

public class DalConfigServiceImpl implements DalConfigService {

	private String project = "ds";

	private final String[] envs = new String[] { "dev", "alpha", "qa", "prelease", "product", "performance" };

	@Inject
	private LionHttpService m_lionHttpService;

	@Override
	public boolean generateConfig(String name) {
		String url = String.format("%s.%s.jdbc.url", project, name);
		String user = String.format("%s.%s.jdbc.user", project, name);
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

		for (String env : envs) {
			m_lionHttpService.setConfig(env, url, "");
			m_lionHttpService.setConfig(env, user, "");
			m_lionHttpService.setConfig(env, password, "");
			m_lionHttpService.setConfig(env, driverClass, "com.mysql.jdbc.Driver");
			m_lionHttpService.setConfig(env, properties, "${ds.datasource.properties}");
			m_lionHttpService.setConfig(env, active, "true");
		}

		return true;
	}

	@Override
	public void setProject(String project) {
		this.project = project;
	}
}
