package com.dianping.zebra.admin.build;

import java.util.ArrayList;
import java.util.List;

import org.unidal.dal.jdbc.datasource.JdbcDataSourceConfigurationManager;
import org.unidal.dal.jdbc.entity.EntityInfoManager;
import org.unidal.dal.jdbc.transaction.TransactionManager;
import org.unidal.initialization.DefaultModuleManager;
import org.unidal.initialization.Module;
import org.unidal.initialization.ModuleManager;
import org.unidal.lookup.configuration.AbstractResourceConfigurator;
import org.unidal.lookup.configuration.Component;

import com.dianping.zebra.admin.ZebraHomeModule;
import com.dianping.zebra.admin.admin.service.BlackListService;
import com.dianping.zebra.admin.admin.service.BlackListServiceImpl;
import com.dianping.zebra.admin.admin.service.CmdbService;
import com.dianping.zebra.admin.admin.service.CmdbServiceImpl;
import com.dianping.zebra.admin.admin.service.ConnectionService;
import com.dianping.zebra.admin.admin.service.ConnectionServiceImpl;
import com.dianping.zebra.admin.admin.service.DalConfigService;
import com.dianping.zebra.admin.admin.service.DalConfigServiceImpl;
import com.dianping.zebra.admin.admin.service.DalService;
import com.dianping.zebra.admin.admin.service.DalServiceImpl;
import com.dianping.zebra.admin.admin.service.DatabaseRealtimeService;
import com.dianping.zebra.admin.admin.service.DatabaseRealtimeServiceImpl;
import com.dianping.zebra.admin.admin.service.HeartbeatUpdateService;
import com.dianping.zebra.admin.admin.service.HeartbeatUpdateServiceImpl;
import com.dianping.zebra.admin.admin.service.HttpService;
import com.dianping.zebra.admin.admin.service.HttpServiceImpl;
import com.dianping.zebra.admin.admin.service.LionHttpService;
import com.dianping.zebra.admin.admin.service.LionHttpServiceImpl;
import com.dianping.zebra.admin.admin.service.LocalLogService;
import com.dianping.zebra.admin.admin.service.LogService;
import com.dianping.zebra.admin.admin.service.MergeConfigService;
import com.dianping.zebra.admin.admin.service.MergeConfigServiceImpl;
import com.dianping.zebra.admin.admin.service.ReportService;
import com.dianping.zebra.admin.admin.service.ReportServiceImpl;
import com.dianping.zebra.admin.datasource.TransactionManagerWrapper;
import com.dianping.zebra.web.dal.lion.ConfigDao;
import com.dianping.zebra.web.dal.lion.ConfigInstanceDao;
import com.dianping.zebra.web.dal.stat.HeartbeatDao;

public class ComponentsConfigurator extends AbstractResourceConfigurator {
	public static void main(String[] args) {
		generatePlexusComponentsXmlFile(new ComponentsConfigurator());
	}

	@Override
	public List<Component> defineComponents() {
		List<Component> all = new ArrayList<Component>();

		all.add(C(Module.class, ZebraHomeModule.ID, ZebraHomeModule.class));

		all.add(C(ModuleManager.class, DefaultModuleManager.class) //
		      .config(E("topLevelModules").value(ZebraHomeModule.ID)));

		all.add(C(TransactionManagerWrapper.class).req(TransactionManager.class, EntityInfoManager.class));
		all.add(C(MergeConfigService.class, MergeConfigServiceImpl.class).req(ConfigDao.class, ConfigInstanceDao.class,
		      TransactionManagerWrapper.class, LionHttpService.class));
		all.add(C(LionHttpService.class, LionHttpServiceImpl.class).req(HttpService.class));
		all.add(C(LogService.class, LocalLogService.class));
		all.add(C(CmdbService.class, CmdbServiceImpl.class).req(HttpService.class));
		all.add(C(HeartbeatUpdateService.class, HeartbeatUpdateServiceImpl.class).req(CmdbService.class,
		      HeartbeatDao.class));
		all.add(C(HttpService.class, HttpServiceImpl.class));
		all.add(C(DatabaseRealtimeService.class, DatabaseRealtimeServiceImpl.class).req(CmdbService.class,
		      HttpService.class,LionHttpService.class));
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
