package com.dianping.zebra.biz.service.impl;

import com.dianping.zebra.biz.dao.SyncServerMonitorMapper;
import com.dianping.zebra.biz.entity.SyncServerMonitorEntity;
import com.dianping.zebra.biz.service.SyncServerMonitorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Dozer @ 6/4/15 mail@dozer.cc http://www.dozer.cc
 */
@Service
public class SyncServerMonitorServiceImpl implements SyncServerMonitorService {

	@Autowired
	private SyncServerMonitorMapper syncServerMonitorMapper;

	private String localAddress;

	@PostConstruct
	public void init() throws UnknownHostException {
		this.localAddress = InetAddress.getLocalHost().getHostName();
	}

	@Override
	public SyncServerMonitorEntity chooseOne() {
		List<SyncServerMonitorEntity> servers = syncServerMonitorMapper.getAllAlive();

		SyncServerMonitorEntity target = null;
		for (SyncServerMonitorEntity server : servers) {
			if (target == null || target.getLoad() > server.getLoad()) {
				target = server;
			}
		}
		return target;
	}

	@Override
	public List<SyncServerMonitorEntity> getAllAlive() {
		return syncServerMonitorMapper.getAllAlive();
	}

	@Override
	public void uploadStatus() {
		SyncServerMonitorEntity item = new SyncServerMonitorEntity();
		item.setName(this.localAddress);
		OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
		item.setLoad(os.getSystemLoadAverage() / os.getAvailableProcessors());

		int row = syncServerMonitorMapper.update(item);
		if (row == 0) {
			syncServerMonitorMapper.create(item);
		}
	}
}
