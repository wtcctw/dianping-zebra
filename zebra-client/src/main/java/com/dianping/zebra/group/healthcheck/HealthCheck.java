package com.dianping.zebra.group.healthcheck;

import java.util.List;

import com.dianping.zebra.group.config.ConfigChangeEvent;
import com.dianping.zebra.group.config.ConfigChangeListener;

public class HealthCheck implements ConfigChangeListener{
	private int heartbeatTime;
	
	private List<String> activeDsKeys;
	
	private List<String> unactiveDsKeys;
	
	
	public void healthProblemNotify(String dsKey,Throwable e){
	
	}


	@Override
   public void onChange(ConfigChangeEvent event) {
	   // TODO Auto-generated method stub
	   
   }
	
	
	private class heartbeatCheck implements Runnable{

		@Override
      public void run() {
	      // TODO Auto-generated method stub
	      // check unactive ds.
	
			
			
      }
		
	}

}
