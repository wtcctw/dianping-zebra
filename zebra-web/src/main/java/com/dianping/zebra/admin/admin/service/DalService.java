package com.dianping.zebra.admin.admin.service;

public interface DalService {

	public DalResult markDown(String env, String ip, String port);

	public DalResult markUp(String env, String ip, String port);
	
	public DalResult notify(String env, String ip, String port);

}
