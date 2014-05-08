package com.dianping.zebra.admin.admin.service;

import java.util.Date;
import java.util.List;

public interface LogService {

	public void log(String ip, String port, String user, Date date, String action, List<String> targetDbs);
	
}
