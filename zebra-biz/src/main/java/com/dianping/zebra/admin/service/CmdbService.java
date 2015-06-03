package com.dianping.zebra.admin.service;

import java.util.List;
import java.util.Map;

public interface CmdbService {
	
	public String getAppName(String ip);
	
	/**
	 * 
	 * @param ips
	 * @return Map<IP,AppName>
	 */
	public Map<String,String> getMultiAppName(List<String> ips);
}
