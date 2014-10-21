package com.dianping.zebra.admin.build;

import com.dianping.zebra.admin.admin.service.*;
import com.dianping.zebra.web.dal.stat.HeartbeatDao;
import org.unidal.dal.jdbc.datasource.JdbcDataSourceConfigurationManager;
import org.unidal.lookup.configuration.AbstractResourceConfigurator;
import org.unidal.lookup.configuration.Component;

import java.util.ArrayList;
import java.util.List;

public class ComponentsConfigurator extends AbstractResourceConfigurator {
	public static void main(String[] args) {
		generatePlexusComponentsXmlFile(new ComponentsConfigurator());
	}

	@Override
	public List<Component> defineComponents() {
		List<Component> all = new ArrayList<Component>();

		all.add(C(LionHttpService.class, LionHttpServiceImpl.class).req(HttpService.class));
		all.add(C(LogService.class, LocalLogService.class));
		all.add(C(CmdbService.class, CmdbServiceImpl.class).req(HttpService.class));
		all.add(C(HttpService.class, HttpServiceImpl.class));
		all.add(C(DatabaseRealtimeService.class, DatabaseRealtimeServiceImpl.class).req(CmdbService.class,
		      HttpService.class));
		all.add(C(DalConfigService.class, DalConfigServiceImpl.class).req(LionHttpService.class));
		all.add(C(DalService.class, DalServiceImpl.class).req(LionHttpService.class));
		all.add(C(ReportService.class, ReportServiceImpl.class).req(HeartbeatDao.class, CmdbService.class,
		      DatabaseRealtimeService.class));
		all.add(C(ConnectionService.class, ConnectionServiceImpl.class));
		all.add(C(BlackListService.class, BlackListServiceImpl.class).req(LionHttpService.class, CmdbService.class));

		// move following line to top-level project if necessary
		all.add(C(JdbcDataSourceConfigurationManager.class));
		all.addAll(new ZebraDatabaseConfigurator().defineComponents());
		all.addAll(new LionDatabaseConfigurator().defineComponents());

		// Please keep it as last
		all.addAll(new WebComponentConfigurator().defineComponents());

		return all;
	}
}
