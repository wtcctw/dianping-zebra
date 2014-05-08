package com.dianping.zebra.admin.admin.service;

import java.util.List;

public interface DalService {

	public List<String> markDown(String env, String ip, String port) throws Exception;

	public List<String> markUp(String env, String ip, String port) throws Exception;

}
