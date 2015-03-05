package com.dianping.zebra.admin.service;

import org.codehaus.plexus.logging.LogEnabled;
import org.codehaus.plexus.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class LocalLogService implements LogService, LogEnabled {
	private Logger m_logger;

	@Override
	public void enableLogging(Logger logger) {
		m_logger = logger;
	}

	@Override
	public void log(DalResult result) {
		m_logger.info(result.toString());
	}

	public void logNotify(String[] ips) {
		StringBuilder sb = new StringBuilder();

		for (String ip : ips) {
			sb.append(ip);
			sb.append(",");
		}

		m_logger.info("following client " + sb + " still connect to the mysql");
	}
}
