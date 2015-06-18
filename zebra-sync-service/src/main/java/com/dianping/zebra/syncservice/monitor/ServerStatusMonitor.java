package com.dianping.zebra.syncservice.monitor;

import com.dianping.zebra.admin.service.SyncServerMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Dozer @ 6/4/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
@Component
public class ServerStatusMonitor implements Runnable {
	@Autowired
	private SyncServerMonitorService syncServerMonitorService;

	protected void uploadStatus() {
		syncServerMonitorService.uploadStatus();
	}

	@PostConstruct
	public void init() {
		Thread t = new Thread(this);
		t.setName(this.getClass().getName());
		t.setDaemon(true);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				break;
			}

			this.uploadStatus();
		}
	}
}
