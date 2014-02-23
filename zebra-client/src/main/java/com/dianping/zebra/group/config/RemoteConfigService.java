package com.dianping.zebra.group.config;

import java.beans.PropertyChangeListener;

public class RemoteConfigService implements ConfigService{
	
	private String resourceId;
	
	public RemoteConfigService(String resourceId) {
		this.resourceId = resourceId;
	}

	@Override
   public String getProperty(String key) {
	   // TODO Auto-generated method stub
	   return null;
   }

	@Override
   public void addPropertyChangeListener(PropertyChangeListener listener) {
	   // TODO Auto-generated method stub
	   
   }

	@Override
   public void init() {
	   // TODO Auto-generated method stub
	   
   }

}
