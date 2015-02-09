package com.dianping.zebra.admin.service;


public interface LogService {

	public void log(DalResult result);
	
	public void logNotify(String[] ips);
	
}
