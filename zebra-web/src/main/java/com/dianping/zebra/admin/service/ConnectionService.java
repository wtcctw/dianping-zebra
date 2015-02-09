package com.dianping.zebra.admin.service;

import com.dianping.zebra.admin.dto.ConnectionStatusDto;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

public interface ConnectionService {
	public ConnectionStatusDto getConnectionResult(String jdbcRef, DalConfigService.GroupConfigModel modal);
}
