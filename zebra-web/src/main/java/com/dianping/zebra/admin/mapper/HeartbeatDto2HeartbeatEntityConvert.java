package com.dianping.zebra.admin.mapper;

import com.dianping.zebra.admin.dto.HeartbeatDto;
import com.dianping.zebra.admin.entity.HeartbeatEntity;
import com.dianping.zebra.monitor.model.DataSourceInfo;
import com.google.common.base.Strings;

public class HeartbeatDto2HeartbeatEntityConvert {

	private static final String NOT_FOUND = "N/A";

	public HeartbeatEntity convert(HeartbeatDto source) {
		HeartbeatEntity entity = new HeartbeatEntity();
		
		entity.setApp_name(source.getApp());
		entity.setDatabase_name(source.getDatabase());
		entity.setDatasource_bean_class(source.getDataSourceBeanClass());
		entity.setDatasource_bean_name(source.getDataSourceBeanName());
		entity.setInit_pool_size(source.getInitPoolSize());
		entity.setIp(source.getIp());
		entity.setMax_pool_size(source.getMaxPoolSize());
		entity.setMin_pool_size(source.getMinPoolSize());
		entity.setReplaced(source.isReplaced());
		entity.setJdbc_url(source.getUrl());
		entity.setUsername(source.getUsername());
		entity.setVersion(source.getVersion());

		if (!Strings.isNullOrEmpty(entity.getApp_name())) {
			entity.setApp_name(source.getApp().toLowerCase());
		}

		if (Strings.isNullOrEmpty(entity.getDatabase_name())) {
			entity.setDatabase_name(NOT_FOUND);
		}
		if (Strings.isNullOrEmpty(entity.getDatasource_bean_class())) {
			entity.setDatasource_bean_class(NOT_FOUND);
		}
		if (Strings.isNullOrEmpty(entity.getVersion())) {
			entity.setVersion(NOT_FOUND);
		}
		if (Strings.isNullOrEmpty(entity.getUsername())) {
			entity.setUsername(NOT_FOUND);
		}

		if (!Strings.isNullOrEmpty(source.getUrl())) {
			String[] parts = source.getUrl().split(":");
			if (parts != null && parts.length > 2) {
				entity.setDatabase_type(parts[1].toLowerCase());
			}
		} else {
			entity.setJdbc_url(NOT_FOUND);
			entity.setDatabase_type(NOT_FOUND);
		}
		
		return entity;
	}
	
	public HeartbeatEntity convert(DataSourceInfo source) {
		HeartbeatEntity entity = new HeartbeatEntity();
		
		entity.setApp_name(source.getApp());
		entity.setDatabase_name(source.getDatabase());
		entity.setDatasource_bean_class(source.getDataSourceBeanClass());
		entity.setDatasource_bean_name(source.getDataSourceBeanName());
		try{
			entity.setInit_pool_size(Integer.parseInt(source.getInitPoolSize()));
		}catch(Exception e){
			entity.setInit_pool_size(0);
		}
		
		entity.setIp(source.getIp());
		try{
			entity.setMax_pool_size(Integer.parseInt(source.getMaxPoolSize()));
		}catch(Exception e){
			entity.setMax_pool_size(0);
		}
		try{
			entity.setMin_pool_size(Integer.parseInt(source.getMinPoolSize()));
		}catch(Exception e){
			entity.setMin_pool_size(0);
		}
		entity.setReplaced(source.getReplaced());
		entity.setJdbc_url(source.getUrl());
		entity.setUsername(source.getUsername());
		entity.setVersion(source.getVersion());

		if (!Strings.isNullOrEmpty(entity.getApp_name())) {
			entity.setApp_name(source.getApp().toLowerCase());
		}

		if (Strings.isNullOrEmpty(entity.getDatabase_name())) {
			entity.setDatabase_name(NOT_FOUND);
		}
		if (Strings.isNullOrEmpty(entity.getDatasource_bean_class())) {
			entity.setDatasource_bean_class(NOT_FOUND);
		}
		if (Strings.isNullOrEmpty(entity.getVersion())) {
			entity.setVersion(NOT_FOUND);
		}
		if (Strings.isNullOrEmpty(entity.getUsername())) {
			entity.setUsername(NOT_FOUND);
		}

		if (!Strings.isNullOrEmpty(source.getUrl())) {
			String[] parts = source.getUrl().split(":");
			if (parts != null && parts.length > 2) {
				entity.setDatabase_type(parts[1].toLowerCase());
			}
		} else {
			entity.setJdbc_url(NOT_FOUND);
			entity.setDatabase_type(NOT_FOUND);
		}
		
		return entity;
	}
}
