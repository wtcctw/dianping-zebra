package com.dianping.zebra.admin.mapper;

import com.dianping.zebra.admin.dto.HeartbeatDto;
import com.dianping.zebra.admin.entity.HeartbeatEntity;
import com.google.common.base.Strings;

import org.modelmapper.PropertyMap;

public class HeartbeatDto2HeartbeatEntityMap extends PropertyMap<HeartbeatDto, HeartbeatEntity> {

	private static final String NOT_FOUND = "N/A";

	@Override
	protected void configure() {
		map().setApp_name(source.getApp());
		map().setDatabase_name(source.getDatabase());
		map().setDatasource_bean_class(source.getDataSourceBeanClass());
		map().setDatasource_bean_name(source.getDataSourceBeanName());
		map().setInit_pool_size(source.getInitPoolSize());
		map().setIp(source.getIp());
		map().setMax_pool_size(source.getMaxPoolSize());
		map().setMin_pool_size(source.getMinPoolSize());
		map().setJdbc_url(source.getUrl());
		map().setUsername(source.getUsername());
		map().setVersion(source.getVersion());

		if (!Strings.isNullOrEmpty(map().getApp_name())) {
			map().setApp_name(source.getApp().toLowerCase());
		}

		if (Strings.isNullOrEmpty(map().getDatabase_name())) {
			map().setDatabase_name(NOT_FOUND);
		}
		if (Strings.isNullOrEmpty(map().getDatasource_bean_class())) {
			map().setDatasource_bean_class(NOT_FOUND);
		}
		if (Strings.isNullOrEmpty(map().getVersion())) {
			map().setVersion(NOT_FOUND);
		}
		if (Strings.isNullOrEmpty(map().getUsername())) {
			map().setUsername(NOT_FOUND);
		}

		if (!Strings.isNullOrEmpty(source.getUrl())) {
			String[] parts = source.getUrl().split(":");
			if (parts != null && parts.length > 2) {
				map().setDatabase_type(parts[1].toLowerCase());
			}
		} else {
			map().setJdbc_url(NOT_FOUND);
			map().setDatabase_type(NOT_FOUND);
		}
	}
}
