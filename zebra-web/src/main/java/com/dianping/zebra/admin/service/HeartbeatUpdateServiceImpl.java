package com.dianping.zebra.admin.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.zebra.admin.dao.HeartbeatMapper;
import com.dianping.zebra.admin.entity.HeartbeatEntity;

@Service
public class HeartbeatUpdateServiceImpl implements HeartbeatUpdateService, Runnable {

	@Autowired
	private HeartbeatMapper heartbeatMapper;

	@Autowired
	private CmdbService cmdbService;

	@PostConstruct
	public void init() {
		Thread th = new Thread(this);
		th.setName("Dal-" + HeartbeatUpdateServiceImpl.class.getSimpleName());
		th.setDaemon(true);

		th.start();
	}

	@Override
	public void update() throws Exception {
		Transaction transaction = Cat.newTransaction("Heartbeat", "update");
		try {
			List<HeartbeatEntity> heartbeats = heartbeatMapper.getAll();

			Set<String> ips = new HashSet<String>();
			for (HeartbeatEntity heartbeat : heartbeats) {
				ips.add(heartbeat.getIp());
			}

			List<String> ips_ = new ArrayList<String>();

			for (String ip : ips) {
				ips_.add(ip);
			}

			Map<String, String> multiAppName = cmdbService.getMultiAppName(ips_);

			for (HeartbeatEntity heartbeat : heartbeats) {
				String ip = heartbeat.getIp();
				String name = heartbeat.getApp_name();
				String cmdbName = multiAppName.get(ip);

				// if ip is no longer belongs to this app, then delete this heartbeat
				if (cmdbName != null && !name.equalsIgnoreCase(cmdbName)) {
					heartbeatMapper.deleteHeartbeatById((heartbeat.getId()));
				}

				// if ip is no longer belongs to any app, then delete this heartbeat
				if (cmdbName == null) {
					heartbeatMapper.deleteHeartbeatById((heartbeat.getId()));
				}

				// if name is equals noname
				if (name.equalsIgnoreCase("noname") || name.contains("job")) {
					heartbeatMapper.deleteHeartbeatById((heartbeat.getId()));
				}
			}

			transaction.setStatus(Message.SUCCESS);
		} catch (Exception e) {
			transaction.setStatus(e);
			Cat.logError(e);
			throw e;
		} finally {
			transaction.complete();
		}
	}

	@Override
	public void run() {
		// todo: run!
		while (true) {
			try {
				update();

				TimeUnit.MINUTES.sleep(30);
			} catch (Throwable e) {
				Cat.logError(e);
			}
		}
	}
}
