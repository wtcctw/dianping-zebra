//package com.dianping.zebra.admin.service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;
//
//import org.unidal.dal.jdbc.DalException;
//import org.unidal.lookup.annotation.Inject;
//
//import com.dianping.cat.Cat;
//import com.dianping.cat.message.Message;
//import com.dianping.cat.message.Transaction;
//import com.dianping.zebra.Constants;
//import com.dianping.zebra.admin.report.entity.App;
//import com.dianping.zebra.admin.report.entity.Database;
//import com.dianping.zebra.admin.report.entity.Datasource;
//import com.dianping.zebra.admin.report.entity.Machine;
//import com.dianping.zebra.admin.report.entity.Report;
//import com.dianping.zebra.admin.report.transform.BaseVisitor;
//import com.dianping.zebra.web.dal.stat.Heartbeat;
//import com.dianping.zebra.web.dal.stat.HeartbeatDao;
//import com.dianping.zebra.web.dal.stat.HeartbeatEntity;
//
///**
//* updateStatus:
//* <p>
//* -1 = 没有升级任何
//* </p>
//* <p>
//* 0 = 升级成功
//* </p>
//* <p>
//* 1 = 部分升级
//* </p>
//* <p>
//* 2 = 没有接入dal，所以没有被dal监控到
//* </p>
//*
//* @author damonzhu
//*/
//public class ReportServiceImpl implements ReportService {
//
//	@Inject
//	private HeartbeatDao m_heartbeatDao;
//
//	@Inject
//	private CmdbService m_cmdbService;
//
//	@Inject
//	private DatabaseRealtimeService m_databaseRealtimeService;
//
//	private void buildApp(App app, Heartbeat hb) {
//		Machine machine = app.findOrCreateMachine(hb.getIp());
//		machine.setIp(hb.getIp());
//		machine.setVersion(hb.getVersion());
//
//		Datasource ds = machine.findOrCreateDatasource(hb.getDatasourceBeanName());
//
//		ds.setName(hb.getDatabaseName());
//		ds.setUsername(hb.getUsername());
//		ds.setBeanName(hb.getDatasourceBeanName());
//		ds.setType(hb.getDatasourceBeanClass());
//		ds.setInitPoolSize(hb.getInitPoolSize());
//		ds.setJdbcUrl(hb.getJdbcUrl());
//		ds.setMaxPoolSize(hb.getMaxPoolSize());
//		ds.setMinPoolSize(hb.getMinPoolSize());
//		ds.setReplaced(hb.isReplaced());
//	}
//
//	@Override
//	public void createOrUpdate(Heartbeat heartbeat) {
//		try {
//			if (heartbeat.getAppName().equalsIgnoreCase(Constants.PHOENIX_APP_NO_NAME) && heartbeat.getIp() != null) {
//				String name = m_cmdbService.getAppName(heartbeat.getIp());
//
//				if (!name.equalsIgnoreCase(Constants.PHOENIX_APP_NO_NAME)) {
//					heartbeat.setAppName(name);
//				}
//			}
//
//			Heartbeat h = m_heartbeatDao.exists(heartbeat.getAppName(), heartbeat.getIp(),
//			      heartbeat.getDatasourceBeanName(), HeartbeatEntity.READSET_FULL);
//			heartbeat.setId(h.getId());
//			m_heartbeatDao.updateByPK(heartbeat, HeartbeatEntity.UPDATESET_FULL);
//		} catch (DalException e) {
//			try {
//				m_heartbeatDao.insert(heartbeat);
//			} catch (DalException ignore) {
//			}
//		}
//	}
//
//	@Override
//	public App getApp(String appName, boolean isProduct) {
//		App app = new App(appName);
//
//		Transaction transaction = Cat.newTransaction("Report", "App");
//
//		try {
//			List<Heartbeat> all = m_heartbeatDao.findByAppName(appName, HeartbeatEntity.READSET_FULL);
//
//			for (Heartbeat hb : all) {
//				if (hb.getAppName().equalsIgnoreCase(Constants.PHOENIX_APP_NO_NAME) && hb.getIp() != null) {
//					String name = m_cmdbService.getAppName(hb.getIp());
//
//					if (!name.equalsIgnoreCase(Constants.PHOENIX_APP_NO_NAME)) {
//						hb.setAppName(name);
//
//						m_heartbeatDao.updateByPK(hb, HeartbeatEntity.UPDATESET_FULL);
//					}
//				}
//
//				buildApp(app, hb);
//			}
//		} catch (DalException e) {
//			Cat.logError(e);
//		}
//
//		if (isProduct) {
//			new ConnectionInfoVisitor2().visitApp(app);
//		}
//		new StatisticsVisitor().visitApp(app);
//
//		transaction.setStatus(Message.SUCCESS);
//		transaction.complete();
//		return app;
//	}
//
//	@Override
//	public Database getDatabase(String database, boolean isProduct) {
//		Transaction transaction = Cat.newTransaction("Report", "Database");
//
//		Database database2 = getReportInternal().getDatabases().get(database);
//
//		if (isProduct) {
//			new ConnectionInfoVisitor().visitDatabase(database2);
//		}
//
//		new StatisticsVisitor().visitDatabase(database2);
//
//		transaction.setStatus(Message.SUCCESS);
//		transaction.complete();
//		return database2;
//	}
//
//	@Override
//	public Report getReport(boolean isProduct) {
//		Transaction transaction = Cat.newTransaction("Report", "All");
//
//		Report report = getReportInternal();
//
//		if (isProduct) {
//			new ConnectionInfoVisitor().visitReport(report);
//		}
//
//		new StatisticsVisitor().visitReport(report);
//
//		transaction.setStatus(Message.SUCCESS);
//		transaction.complete();
//		return report;
//	}
//
//	public Report getReportInternal() {
//		Report report = new Report();
//		try {
//			List<Heartbeat> all = m_heartbeatDao.findAll(HeartbeatEntity.READSET_FULL);
//
//			for (Heartbeat hb : all) {
//				if (!hb.getDatabaseName().equals("N/A")) {
//					Database database = report.findOrCreateDatabase(hb.getDatabaseName());
//					database.setName(hb.getDatabaseName());
//
//					App app = database.findOrCreateApp(hb.getAppName());
//					app.setName(hb.getAppName());
//
//					buildApp(app, hb);
//				}
//			}
//		} catch (DalException e) {
//			Cat.logError(e);
//		}
//
//		return report;
//	}
//
//	public class ConnectionInfoVisitor extends BaseVisitor {
//
//		private Map<String, String> connectedIps;
//
//		private Map<String, Map<String, String>> connectedAllIps;
//
//		@Override
//		public void visitReport(Report report) {
//			connectedAllIps = m_databaseRealtimeService.getAllConnectedIps();
//
//			for (Database database : report.getDatabases().values()) {
//				visitDatabase(database);
//			}
//		}
//
//		@Override
//		public void visitApp(App app) {
//			if (connectedIps != null) {
//				for (Entry<String, String> entry : connectedIps.entrySet()) {
//					String ip = entry.getKey();
//					String appName = entry.getValue();
//
//					if (appName.equalsIgnoreCase(app.getName())) {
//						Machine machine = app.findMachine(ip);
//
//						if (machine == null) {
//							machine = app.findOrCreateMachine(ip);
//							machine.setIntergrateWithDal(false);
//						}
//					}
//				}
//			}
//		}
//
//		@Override
//		public void visitDatabase(Database database) {
//			if (connectedAllIps == null) {
//				connectedAllIps = m_databaseRealtimeService.getAllConnectedIps();
//			}
//
//			connectedIps = connectedAllIps.get(database.getName());
//
//			for (App app : database.getApps().values()) {
//				visitApp(app);
//			}
//
//			if(connectedIps != null){
//				Set<String> connectedApps = new HashSet<String>();
//				for(String app : connectedIps.values()){
//					connectedApps.add(app);
//				}
//
//				for(String name : connectedApps){
//					App app = database.findApp(name);
//
//					if(app == null){
//						app = database.findOrCreateApp(name);
//						visitApp(app);
//					}
//				}
//			}
//		}
//	}
//
//	public class ConnectionInfoVisitor2 extends BaseVisitor {
//		@Override
//		public void visitApp(App app) {
//			Map<String, Map<String, String>> connectedIps = m_databaseRealtimeService.getAllConnectedIps();
//			if (connectedIps != null) {
//				for (Entry<String, Map<String, String>> entry : connectedIps.entrySet()) {
//					Map<String, String> connections = entry.getValue();
//
//					for (Entry<String, String> entry2 : connections.entrySet()) {
//						if (entry2.getValue().equalsIgnoreCase(app.getName())) {
//							String ip = entry2.getKey();
//							Machine machine = app.findMachine(ip);
//
//							if (machine == null) {
//								machine = app.findOrCreateMachine(ip);
//								machine.setIntergrateWithDal(false);
//							}
//						}
//					}
//				}
//			}
//		}
//	}
//
//	public class StatisticsVisitor extends BaseVisitor {
//
//		private App m_app;
//
//		@Override
//		public void visitApp(App app) {
//			m_app = app;
//			boolean hasNotIntegratedWithDal = false;
//
//			for (Machine machine : app.getMachines().values()) {
//				if (machine.getIntergrateWithDal()) {
//					visitMachine(machine);
//				} else {
//					hasNotIntegratedWithDal = true;
//				}
//			}
//
//			if (hasNotIntegratedWithDal) {
//				app.setUpdateStatus(2);
//			} else {
//				if (app.getGroupDataSource() == 0 && app.getReplacedSingleDataSource() == 0
//				      && app.getReplacedDpdlDataSource() == 0
//				      && (app.getC3p0DataSource() != 0 || app.getDpdlDataSource() != 0)) {
//					app.setUpdateStatus(-1);
//				} else if ((app.getReplacedSingleDataSource() + app.getReplacedDpdlDataSource() + app.getGroupDataSource()) == app
//				      .getTotalDataSource()) {
//					app.setUpdateStatus(0);
//				} else {
//					app.setUpdateStatus(1);
//				}
//			}
//		}
//
//		@Override
//		public void visitDatabase(Database database) {
//			boolean hasNotIntegratedWithDal = false;
//
//			for (App app : database.getApps().values()) {
//				visitApp(app);
//
//				database.incGroupDataSource(app.getGroupDataSource());
//				database.incDpdlDataSource(app.getDpdlDataSource());
//				database.incC3p0DataSource(app.getC3p0DataSource());
//				database.incSingleDataSource(app.getSingleDataSource());
//				database.incOtherDataSource(app.getOtherDataSource());
//				database.incReplacedSingleDataSource(app.getReplacedSingleDataSource());
//				database.incReplacedDpdlDataSource(app.getReplacedDpdlDataSource());
//				database.incTotalDataSource(app.getTotalDataSource());
//
//				if (app.getUpdateStatus() == 2) {
//					hasNotIntegratedWithDal = true;
//				}
//			}
//
//			if (hasNotIntegratedWithDal) {
//				database.setUpdateStatus(2);
//			} else {
//				if (database.getGroupDataSource() == 0 && database.getReplacedSingleDataSource() == 0
//				      && database.getReplacedDpdlDataSource() == 0
//				      && (database.getC3p0DataSource() != 0 || database.getDpdlDataSource() != 0)) {
//					database.setUpdateStatus(-1);
//				} else if ((database.getReplacedSingleDataSource() + database.getReplacedDpdlDataSource() + database
//				      .getGroupDataSource()) == database.getTotalDataSource()) {
//					database.setUpdateStatus(0);
//				} else {
//					database.setUpdateStatus(1);
//				}
//			}
//		}
//
//		@Override
//		public void visitDatasource(Datasource datasource) {
//			String type = datasource.getType();
//			if (type.equals("com.dianping.zebra.group.jdbc.GroupDataSource")) {
//				m_app.incGroupDataSource();
//			} else if (type.equals("com.dianping.dpdl.sql.DPDataSource")) {
//				m_app.incDpdlDataSource();
//				if (datasource.isReplaced()) {
//					m_app.incReplacedDpdlDataSource();
//				}
//			} else if (type.equals("com.dianping.zebra.group.jdbc.SingleDataSource")) {
//				m_app.incSingleDataSource();
//			} else if (type.equals("com.mchange.v2.c3p0.ComboPooledDataSource")) {
//				m_app.incC3p0DataSource();
//				if (datasource.isReplaced()) {
//					m_app.incReplacedSingleDataSource();
//				}
//			} else {
//				m_app.incOtherDataSource();
//			}
//
//			m_app.incTotalDataSource();
//		}
//
//		@Override
//		public void visitReport(Report report) {
//			for (Database database : report.getDatabases().values()) {
//				visitDatabase(database);
//
//				report.incGroupDataSource(database.getGroupDataSource());
//				report.incDpdlDataSource(database.getDpdlDataSource());
//				report.incC3p0DataSource(database.getC3p0DataSource());
//				report.incSingleDataSource(database.getSingleDataSource());
//				report.incOtherDataSource(database.getOtherDataSource());
//				report.incReplacedSingleDataSource(database.getReplacedSingleDataSource());
//				report.incReplacedDpdlDataSource(database.getReplacedDpdlDataSource());
//				report.incTotalDataSource(database.getTotalDataSource());
//
//				switch (database.getUpdateStatus()) {
//				case -1:
//					report.incFailureDatabase();
//					break;
//				case 0:
//					report.incSuccessDatabase();
//					break;
//				default:
//					report.incHalfDoneDatabase();
//				}
//			}
//		}
//	}
//}
