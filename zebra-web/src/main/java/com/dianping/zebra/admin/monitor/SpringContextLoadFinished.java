package com.dianping.zebra.admin.monitor;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringContextLoadFinished implements ApplicationListener<ContextRefreshedEvent> {
	
	private volatile boolean isLoaded = false;

	@Override
   public void onApplicationEvent(ContextRefreshedEvent event) {
		isLoaded = true;
   }
	
	public boolean isLoaded(){
		return isLoaded;
	}

}
