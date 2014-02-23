package com.dianping.zebra.group.config;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LocalConfigService implements ConfigService{
	
	private String resourceId;
	
	private List<PropertyChangeListener> listeners = new CopyOnWriteArrayList<PropertyChangeListener>();
	
	public LocalConfigService(String resourceId){
		this.resourceId = resourceId;
	}

	@Override
   public void addPropertyChangeListener(PropertyChangeListener listener) {
	   this.listeners.add(listener);
   }

	@Override
   public String getProperty(String key) {
	   // TODO Auto-generated method stub
	   return null;
   }

	@Override
   public void init() {
	   
   }

}
