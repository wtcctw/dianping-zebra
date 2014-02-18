package com.dianping.zebra.group.config;

import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

public class ActiveDataSourceChangeEvent extends BaseGroupConfigChangeEvent {

	public ActiveDataSourceChangeEvent(GroupDataSourceConfig groupDatasourceConfig) {
	   super(groupDatasourceConfig);
   }
}
