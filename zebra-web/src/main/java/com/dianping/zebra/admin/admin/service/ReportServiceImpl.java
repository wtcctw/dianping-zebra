package com.dianping.zebra.admin.admin.service;

import java.util.List;

import org.unidal.dal.jdbc.DalException;
import org.unidal.lookup.annotation.Inject;

import com.dianping.cat.Cat;
import com.dianping.zebra.admin.report.entity.App;
import com.dianping.zebra.admin.report.entity.Database;
import com.dianping.zebra.admin.report.entity.Datasource;
import com.dianping.zebra.admin.report.entity.Machine;
import com.dianping.zebra.admin.report.entity.Report;
import com.dianping.zebra.admin.report.transform.BaseVisitor;
import com.dianping.zebra.web.dal.stat.Heartbeat;
import com.dianping.zebra.web.dal.stat.HeartbeatDao;
import com.dianping.zebra.web.dal.stat.HeartbeatEntity;

public class ReportServiceImpl implements ReportService {

	@Inject
	private HeartbeatDao m_heartbeatDao;

	private void buildApp(App app, Heartbeat hb) {
		Machine machine = app.findOrCreateMachine(hb.getIp());
		machine.setIp(hb.getIp());
		machine.setVersion(hb.getVersion());

		Datasource ds = machine.findOrCreateDatasource(hb.getDatasourceBeanName());

		ds.setName(hb.getDatabaseName());
		ds.setUsername(hb.getUsername());
		ds.setBeanName(hb.getDatasourceBeanName());
		ds.setType(hb.getDatasourceBeanClass());
		ds.setInitPoolSize(hb.getInitPoolSize());
		ds.setJdbcUrl(hb.getJdbcUrl());
		ds.setMaxPoolSize(hb.getMaxPoolSize());
		ds.setMinPoolSize(hb.getMinPoolSize());
		ds.setReplaced(hb.isReplaced());
	}

	@Override
	public void createOrUpdate(Heartbeat heartbeat) {
		try {
			Heartbeat h = m_heartbeatDao.exists(heartbeat.getAppName(), heartbeat.getIp(), heartbeat.getDatasourceBeanName(),
			      HeartbeatEntity.READSET_FULL);

			heartbeat.setId(h.getId());

			m_heartbeatDao.updateByPK(heartbeat, HeartbeatEntity.UPDATESET_FULL);
		} catch (DalException e) {
			Cat.logError(e);
			try {
				m_heartbeatDao.insert(heartbeat);
			} catch (DalException e1) {
				Cat.logError(e1);
			}
		}
	}

	@Override
	public App getApp(String appName) {
		App app = new App(appName);

		try {
			List<Heartbeat> all = m_heartbeatDao.findByAppName(appName, HeartbeatEntity.READSET_FULL);

			for (Heartbeat hb : all) {
				buildApp(app, hb);
			}

			new StatisticsVisitor().visitApp(app);
		} catch (DalException e) {
			Cat.logError(e);
		}

		return app;
	}

	@Override
	public Database getDatabase(String database) {
		return getReport().getDatabases().get(database);
	}

	@Override
	public Report getReport() {
		Report report = new Report();
		try {
			List<Heartbeat> all = m_heartbeatDao.findAll(HeartbeatEntity.READSET_FULL);

			for (Heartbeat hb : all) {
				Database database = report.findOrCreateDatabase(hb.getDatabaseName());
				database.setName(hb.getDatabaseName());

				App app = database.findOrCreateApp(hb.getAppName());
				app.setName(hb.getAppName());

				buildApp(app, hb);
			}

			new StatisticsVisitor().visitReport(report);
		} catch (DalException e) {
			Cat.logError(e);
		}

		return report;
	}

	class StatisticsVisitor extends BaseVisitor {

		private App m_app;

		@Override
		public void visitApp(App app) {
			m_app = app;
			for (Machine machine : app.getMachines().values()) {
				visitMachine(machine);
			}
		}

		@Override
		public void visitDatabase(Database database) {
			for (App app : database.getApps().values()) {
				visitApp(app);

				database.incGroupDataSource(app.getGroupDataSource());
				database.incDpdlDataSource(app.getDpdlDataSource());
				database.incSingleDataSource(app.getSingleDataSource());
				database.incC3p0DataSource(app.getC3p0DataSource());
				database.incOtherDataSource(app.getOtherDataSource());
				database.incReplacedDataSource(app.getReplacedDataSource());
				database.incTotalDataSource(app.getTotalDataSource());
			}
		}

		@Override
		public void visitDatasource(Datasource datasource) {
			String type = datasource.getType();
			if (type.equals("com.dianping.zebra.group.jdbc.GroupDataSource")) {
				m_app.incGroupDataSource();
			} else if (type.equals("com.dianping.dpdl.sql.DPDataSource")) {
				m_app.incDpdlDataSource();
			} else if (type.equals("com.dianping.zebra.group.jdbc.SingleDataSource")) {
				m_app.incSingleDataSource();
			} else if (type.equals("com.mchange.v2.c3p0.ComboPooledDataSource")) {
				m_app.incC3p0DataSource();
			} else {
				m_app.incOtherDataSource();
			}

			if (datasource.isReplaced()) {
				m_app.incReplacedDataSource();
			}

			m_app.incTotalDataSource();
		}
	}
}
