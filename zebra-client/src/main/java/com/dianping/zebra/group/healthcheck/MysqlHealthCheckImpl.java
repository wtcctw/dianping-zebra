package com.dianping.zebra.group.healthcheck;

import java.sql.SQLException;
import java.util.List;

import com.dianping.zebra.group.config.ConfigChangeEvent;
import com.dianping.zebra.group.config.ConfigChangeListener;
import com.dianping.zebra.group.config.ConfigManager;

public class MysqlHealthCheckImpl implements ConfigChangeListener {
	private int healthCheckInterval;
	private ConfigManager configManager;

	public MysqlHealthCheckImpl(ConfigManager configManager){
		this.configManager = configManager;
//		this.healthCheckInterval = configManager.getXX();
	}
	
	public void notifyException(String dsKey, SQLException e) {
		
	}

	@Override
	public void onChange(ConfigChangeEvent event) {
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
	
}
