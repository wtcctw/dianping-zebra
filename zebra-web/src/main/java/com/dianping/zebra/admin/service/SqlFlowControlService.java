package com.dianping.zebra.admin.service;

import java.util.Map;

import com.dianping.zebra.admin.dto.SqlFlowControlDto;

/**
 * Created by Dozer on 10/14/14.
 */
public interface SqlFlowControlService {
	Map<String, SqlFlowControlDto> getAllBlackList(String env);

	boolean deleteItem(String env, String key);

	boolean addItem(String env, String ip, String key, String sql, int percent);
}
