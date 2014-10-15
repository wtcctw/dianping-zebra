package com.dianping.zebra.admin.admin.service;

import java.util.Map;

public interface DatabaseRealtimeService {

	/**
	 * 
	 * @param database
	 * @return Map<IP,AppName>
	 */
	public Map<String, String> getConnectedIps(String database);

	/**
	 * 
	 * @return Map<database,Map<IP,AppName>>
	 */
	public Map<String, Map<String, String>> getAllConnectedIps();

}
