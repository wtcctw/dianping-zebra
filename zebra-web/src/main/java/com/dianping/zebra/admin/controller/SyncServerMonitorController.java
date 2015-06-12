package com.dianping.zebra.admin.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dianping.zebra.admin.dao.PumaClientSyncTaskMapper;
import com.dianping.zebra.admin.dao.SyncServerMonitorMapper;
import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.admin.entity.SyncServerMonitorEntity;

@Component
public class SyncServerMonitorController {

	@Autowired
	private SyncServerMonitorMapper monitorDao;

	@Autowired
	private PumaClientSyncTaskMapper syncTaskDao;

	private static final long INTERVAL = 30 * 1000; // 30 seconds

	@Scheduled(cron = "0/5 * * * * ?")
	public void monitor() {
		List<SyncServerMonitorEntity> aliveSyncServices = monitorDao.getAllSyncServer();

		for (SyncServerMonitorEntity aliveSyncService : aliveSyncServices) {
			Date lastUpdateTime = aliveSyncService.getUpdateTime();

			long now = System.currentTimeMillis();

			if ((now - lastUpdateTime.getTime()) > INTERVAL) {
				String executor = aliveSyncService.getName();

				List<PumaClientSyncTaskEntity> tasks = syncTaskDao.findEffectiveTaskByExecutor(executor);

				for (PumaClientSyncTaskEntity task : tasks) {
					String executor1 = task.getExecutor1();
					String executor2 = task.getExecutor2();

					if (executor.equals(executor1)) {
						task.setExecutor(executor2);
						syncTaskDao.updateSyncTask(task);
						continue;
					}

					if (executor.equalsIgnoreCase(executor2)) {
						task.setExecutor(executor1);
						syncTaskDao.updateSyncTask(task);
						continue;
					}

				}
			}
		}
	}
}
