package com.dianping.zebra.syncservice.monitor;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dianping.cat.Cat;
import com.dianping.zebra.biz.service.SyncServerMonitorService;

@Component
public class ServerStatusMonitor {
	@Autowired
	private SyncServerMonitorService syncServerMonitorService;

	@PostConstruct
	public void init()  {
		Thread uploadStatusTask = new Thread(new UploadStatusTask());
		uploadStatusTask.setName("Sync-UploadStatus-Task");
		uploadStatusTask.setDaemon(true);

		uploadStatusTask.start();
	}
	
	class UploadStatusTask implements Runnable{

		@Override
      public void run() {
			while(!Thread.currentThread().isInterrupted()){
				try{
					uploadStatus();
				}catch(Exception e){
					Cat.logError(e);
				}
				
				try {
	            TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
            	break;
            }
			}
      }
		
	}
	
	public void uploadStatus() {
		syncServerMonitorService.uploadStatus();
	}
}
