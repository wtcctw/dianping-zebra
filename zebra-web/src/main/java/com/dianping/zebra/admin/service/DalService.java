package com.dianping.zebra.admin.service;

public interface DalService {

	public DalResult markDown(String env, String ip, String port, String database);

	public DalResult markUp(String env, String ip, String port, String database);
}
