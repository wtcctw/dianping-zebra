package com.dianping.zebra.admin.service;

import com.dianping.zebra.admin.dto.ConnectionStatusDto;

public interface ConnectionService {
	public ConnectionStatusDto getConnectionResult(boolean isProduct, String jdbcRef, DalConfigService.GroupConfigModel modal);
}
