package com.dianping.zebra.group.datasources;

public class GroupDataSourceManagerFactory {
	
	private static GroupDataSourceManager dataSourceManager;
	
	public static GroupDataSourceManager getDataSourceManager(){
		if(dataSourceManager == null){
			synchronized (GroupDataSourceManagerFactory.class) {
				if(dataSourceManager == null){
					dataSourceManager = new DefaultGroupDataSourceManager();
					dataSourceManager.init();
				}
         }
		}
		
		return dataSourceManager;
	}
}
