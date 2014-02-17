package com.dianping.zebra.group.healthcheck;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.dianping.zebra.group.config.BaseGroupConfigChangeEvent;
import com.dianping.zebra.group.config.GroupConfigChangeListener;
import com.dianping.zebra.group.config.GroupConfigManager;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;


public class MysqlHealthCheckImpl implements GroupConfigChangeListener, HealthCheck {
	private int healthCheckInterval;
	private GroupConfigManager configManager;

	public MysqlHealthCheckImpl(GroupConfigManager configManager){
		this.configManager = configManager;
//		this.healthCheckInterval = configManager.getXX();
	}
	
	public void notifyException(String dsKey, SQLException e) {
		
	}

	@Override
	public void onChange(BaseGroupConfigChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

	private class heartbeatCheck implements Runnable {

		@Override
		public void run() {
			while(true){
				System.out.println("test");
				try {
	            Thread.sleep(3000);
            } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
            }
			}

		}

	}

	public int startHeartBeat(){
		return 0;
	}
	
	@Override
	public String getName() {
		return "health-checke-listener";
	}

	@Override
   public void onActiveDataSourceChange(Map<String, DataSourceConfig> configs) {
	   // TODO Auto-generated method stub
	   
   }
	
}
