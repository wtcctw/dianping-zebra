package com.dianping.zebra.admin.build;

import java.util.ArrayList;
import java.util.List;

import com.dianping.zebra.admin.admin.AdminModule;

import org.unidal.lookup.configuration.Component;
import org.unidal.web.configuration.AbstractWebComponentsConfigurator;

class WebComponentConfigurator extends AbstractWebComponentsConfigurator {
	@SuppressWarnings("unchecked")
	@Override
	public List<Component> defineComponents() {
		List<Component> all = new ArrayList<Component>();

		defineModuleRegistry(all, AdminModule.class, AdminModule.class);

		return all;
	}
}
