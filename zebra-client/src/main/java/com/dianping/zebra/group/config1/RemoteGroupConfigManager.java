package com.dianping.zebra.group.config1;

import java.util.Map;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

public class RemoteGroupConfigManager implements GroupConfigManager {

	private final String resourceId;

	public RemoteGroupConfigManager(String resourceId) {
		this.resourceId = resourceId;
	   // TODO Auto-generated constructor stub
   }

	@Override
   public void init() {
	   // TODO Auto-generated method stub
	   
   }

	@Override
   public Map<String, DataSourceConfig> getDataSourceConfigs() {
	   // TODO Auto-generated method stub
	   return null;
   }

	@Override
   public GroupDataSourceConfig getGroupDataSourceConfig() {
	   // TODO Auto-generated method stub
	   return null;
   }

	@Override
   public void addListerner(GroupConfigChangeListener listener) {
	   // TODO Auto-generated method stub
	   
   }

	@Override
   public void markDown(String id) {
	   // TODO Auto-generated method stub
	   
   }

	@Override
   public void markUp(String id) {
	   // TODO Auto-generated method stub
	   
   }

	@Override
   public Map<String, DataSourceConfig> getAvailableDataSources() {
	   // TODO Auto-generated method stub
	   return null;
   }

	@Override
   public Map<String, DataSourceConfig> getUnAvailableDataSources() {
	   // TODO Auto-generated method stub
	   return null;
   }

}
