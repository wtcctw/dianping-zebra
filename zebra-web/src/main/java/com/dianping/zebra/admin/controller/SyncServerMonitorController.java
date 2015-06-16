package com.dianping.zebra.admin.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dianping.cat.Cat;
import com.dianping.zebra.admin.dao.PumaClientSyncTaskMapper;
import com.dianping.zebra.admin.dao.SyncServerMonitorMapper;
import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.admin.entity.SyncServerMonitorEntity;
import com.dianping.zebra.admin.manager.AlarmManager;
import com.dianping.zebra.admin.manager.AlarmManager.AlarmContent;

@Component
public class SyncServerMonitorController {

	@Autowired
	private SyncServerMonitorMapper monitorDao;

	@Autowired
	private PumaClientSyncTaskMapper syncTaskDao;
	
	@Autowired
	private AlarmManager alarmManager;

	private static final long INTERVAL = 30 * 1000; // 30 seconds

	@Scheduled(cron = "0/5 * * * * ?")
	public void monitor() {
		List<SyncServerMonitorEntity> aliveSyncServers = monitorDao.getAllAlive();
		Set<String> aliveSyncServersSet = new HashSet<String>();

		for (SyncServerMonitorEntity entity : aliveSyncServers) {
			aliveSyncServersSet.add(entity.getName());
		}

		List<SyncServerMonitorEntity> allSyncServers = monitorDao.getAllSyncServer();

		for (SyncServerMonitorEntity aliveSyncService : allSyncServers) {
			Date lastUpdateTime = aliveSyncService.getUpdateTime();

			long now = System.currentTimeMillis();

			if ((now - lastUpdateTime.getTime()) > INTERVAL) {
				String executor = aliveSyncService.getName();

				List<PumaClientSyncTaskEntity> tasks = syncTaskDao.findEffectiveTaskByExecutor(executor);

				for (PumaClientSyncTaskEntity task : tasks) {
					String executor1 = task.getExecutor1();
					String executor2 = task.getExecutor2();

					AlarmContent content = null;
					if (!executor.equals(executor1) && aliveSyncServersSet.contains(executor1)) {
						task.setExecutor(executor1);
						syncTaskDao.updateSyncTask(task);
						
						content = new AlarmContent(task.getPumaTaskName(), executor, executor1);
					} else if (!executor.equalsIgnoreCase(executor2) && aliveSyncServersSet.contains(executor2)) {
						task.setExecutor(executor2);
						syncTaskDao.updateSyncTask(task);

						content = new AlarmContent(task.getPumaTaskName(), executor, executor2);
					} else {
						content = new AlarmContent(task.getPumaTaskName(), executor, null);
						
						Cat.logError(new NoAliveSyncServerException(task.getPumaTaskName()
						      + " has no available alive syncservers."));
					}

					alarmManager.alarm(content);
				}
			}
		}
	}

	public static class NoAliveSyncServerException extends Exception {

		private static final long serialVersionUID = -1437599668873911951L;

		public NoAliveSyncServerException(String message) {
			super(message);
		}
	}
}
