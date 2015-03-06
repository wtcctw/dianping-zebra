package com.dianping.zebra.group.router;

import java.util.Map;

import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;

public class RetryConnectDataSourceRouter extends WeightDataSourceRouter{

	private Map<String, DataSourceConfig> dataSourceConfigs;
	
	public RetryConnectDataSourceRouter(Map<String, DataSourceConfig> dataSourceConfigs) {
	   super(dataSourceConfigs);
	   this.dataSourceConfigs = dataSourceConfigs;
   }
	
	public String getName(){
		return "retry-connect";
	}

	public RouterTarget select(RouterContext routerContext){
		RouterTarget target = super.select(routerContext);	
		
		if(target == null){
			for(DataSourceConfig config : dataSourceConfigs.values()){
				if(config.getActive() && config.getCanRead() && config.getWeight() == 0){
					target = new RouterTarget(config.getId());
					
					break;
				}
			}
		}
		
		return target;
	}

}
