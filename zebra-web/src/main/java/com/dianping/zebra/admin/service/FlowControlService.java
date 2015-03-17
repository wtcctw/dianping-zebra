package com.dianping.zebra.admin.service;

import java.util.Map;

import com.dianping.zebra.admin.dto.FlowControlDto;

/**
 * @author hao.zhu
 */
public interface FlowControlService {
	Map<String, FlowControlDto> getAllActiveFlowControl(String env);
	
	Map<String, FlowControlDto> getAllDeletedFlowControl(String env);
	
	boolean containFlowControl(String env,String sqlId);

	boolean deleteItem(String env, String key);

	boolean addItem(String env, String ip, String key, String sql, int percent,String database);
	
	boolean modifyItem(String env, String sqlId, int percent,String database);
}
