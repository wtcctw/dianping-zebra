package com.dianping.zebra.admin.admin.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.unidal.dal.jdbc.DalException;
import org.unidal.helper.Threads.Task;
import org.unidal.lookup.annotation.Inject;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.zebra.web.dal.stat.Heartbeat;
import com.dianping.zebra.web.dal.stat.HeartbeatDao;
import com.dianping.zebra.web.dal.stat.HeartbeatEntity;

public class HeartbeatUpdateServiceImpl implements HeartbeatUpdateService, Task {

	@Inject
	private HeartbeatDao m_heartbeatDao;

	@Inject
	private CmdbService cmdbService;

	@Override
	public void update() {
		Transaction transaction = Cat.newTransaction("Heartbeat", "update");
		try {
			List<Heartbeat> heartbeats = m_heartbeatDao.findAll(HeartbeatEntity.READSET_FULL);

			Set<String> ips = new HashSet<String>();
			for (Heartbeat heartbeat : heartbeats) {
				ips.add(heartbeat.getIp());
			}

			List<String> ips_ = new ArrayList<String>();

			for (String ip : ips) {
				ips_.add(ip);
			}

			Map<String, String> multiAppName = cmdbService.getMultiAppName(ips_);

			for (Heartbeat heartbeat : heartbeats) {
				String ip = heartbeat.getIp();
				String name = heartbeat.getAppName();
				String cmdbName = multiAppName.get(ip);

				// if ip is no longer belongs to this app, then delete this heartbeat
				if (cmdbName != null && !name.equalsIgnoreCase(cmdbName)) {
					m_heartbeatDao.deleteByPK(heartbeat);
				}
				
				// if ip is no longer belongs to any app, then delete this heartbeat
				if(cmdbName == null){
					m_heartbeatDao.deleteByPK(heartbeat);
				}
			}

			transaction.setStatus(Message.SUCCESS);
		} catch (DalException e) {
			transaction.setStatus(e);
			Cat.logError(e);
		} finally {
			transaction.complete();
		}
	}

	@Override
	public void run() {
		while (true) {
			update();

			try {
				TimeUnit.MINUTES.sleep(30);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public String getName() {
		return HeartbeatUpdateServiceImpl.class.getSimpleName();
	}

	@Override
	public void shutdown() {
	}
}
