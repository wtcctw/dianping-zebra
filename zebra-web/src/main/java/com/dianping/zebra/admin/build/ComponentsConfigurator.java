package com.dianping.zebra.admin.build;

import java.util.ArrayList;
import java.util.List;

import org.unidal.lookup.configuration.AbstractResourceConfigurator;
import org.unidal.lookup.configuration.Component;

import com.dianping.zebra.admin.admin.service.DalConfigService;
import com.dianping.zebra.admin.admin.service.DalConfigServiceImpl;
import com.dianping.zebra.admin.admin.service.DalService;
import com.dianping.zebra.admin.admin.service.DalServiceImpl;
import com.dianping.zebra.admin.admin.service.LionHttpService;
import com.dianping.zebra.admin.admin.service.LionHttpServiceImpl;
import com.dianping.zebra.admin.admin.service.LocalLogService;
import com.dianping.zebra.admin.admin.service.LogService;

public class ComponentsConfigurator extends AbstractResourceConfigurator {
	@Override
	public List<Component> defineComponents() {
		List<Component> all = new ArrayList<Component>();

		all.add(C(LionHttpService.class, LionHttpServiceImpl.class));
		all.add(C(LogService.class, LocalLogService.class));
		all.add(C(DalConfigService.class, DalConfigServiceImpl.class).req(LionHttpService.class));
		all.add(C(DalService.class, DalServiceImpl.class).req(LionHttpService.class));

		// Please keep it as last
		all.addAll(new WebComponentConfigurator().defineComponents());

		return all;
	}

	public static void main(String[] args) {
		generatePlexusComponentsXmlFile(new ComponentsConfigurator());
	}
}
