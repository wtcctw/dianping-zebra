package com.dianping.zebra.group.datasources;

public class SingleDataSourceManagerFactory {
	
	private volatile static SingleDataSourceManager dataSourceManager;
	
	public static SingleDataSourceManager getDataSourceManager(){
		if(dataSourceManager == null){
			synchronized (SingleDataSourceManagerFactory.class) {
				if(dataSourceManager == null){
					dataSourceManager = new DefaultSingleDataSourceManager();
					dataSourceManager.init();
				}
         }
		}
		
		return dataSourceManager;
	}
}
