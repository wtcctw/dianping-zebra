package com.dianping.zebra.group.datasources;

public class GroupDataSourceManagerFactory {
	
	private static GroupDataSourceManager dataSourcePool;
	
	public static GroupDataSourceManager getDataSourcePool(){
		if(dataSourcePool == null){
			synchronized (GroupDataSourceManagerFactory.class) {
				if(dataSourcePool == null){
					dataSourcePool = new DefaultGroupDataSourceManager();
					dataSourcePool.init();
				}
         }
		}
		
		return dataSourcePool;
	}
}
