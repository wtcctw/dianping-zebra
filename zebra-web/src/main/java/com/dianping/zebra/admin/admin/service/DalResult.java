package com.dianping.zebra.admin.admin.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DalResult {

	private String m_ip;

	private String m_port;

	private String m_database;

	private String m_env;

	private String m_user;

	private Date m_time;

	private String m_action;

	private Set<String> m_planOperated = new HashSet<String>();

	private Set<String> m_actualOperated = new HashSet<String>();

	private String m_message;

	private int m_status = 0; // 0 for success, 1 for failure, 2 for success but no database markdown

	public DalResult(String ip, String port, String database, String env, String action) {
		super();
		m_ip = ip;
		m_port = port;
		m_database = database;
		m_env = env;
		m_action = action;
	}

	public String getAction() {
		return m_action;
	}

	public Set<String> getActualOperated() {
		return m_actualOperated;
	}

	public String getDatabase() {
		return m_database;
	}

	public String getEnv() {
		return m_env;
	}

	public String getIp() {
		return m_ip;
	}

	public String getMessage() {
		return m_message;
	}

	public Set<String> getPlanOperated() {
		return m_planOperated;
	}

	public String getPort() {
		return m_port;
	}

	public int getStatus() {
		return m_status;
	}

	public Date getTime() {
		return m_time;
	}

	public String getUser() {
		return m_user;
	}

	public void onFail(Set<String> planOperated, Set<String> actualOperated, String message) {
		m_status = 1;
		if (planOperated != null) {
			m_planOperated = planOperated;
		}
		if (actualOperated != null) {
			m_actualOperated = actualOperated;
		}
		m_message = message;
	}

	public void onFail(String message) {
		m_status = 1;
		m_message = message;
	}

	public void onSuccess(int status, Set<String> operated) {
		m_status = status;
		m_planOperated = operated;
		m_actualOperated = operated;
	}

	public void setAction(String action) {
		m_action = action;
	}

	public void setActualOperated(Set<String> actualOperated) {
		m_actualOperated = actualOperated;
	}

	public void setDatabase(String database) {
		m_database = database;
	}

	public void setEnv(String env) {
		m_env = env;
	}

	public void setIp(String ip) {
		m_ip = ip;
	}

	public void setMessage(String message) {
		m_message = message;
	}

	public void setPlanOperated(Set<String> planOperated) {
		m_planOperated = planOperated;
	}

	public void setPort(String port) {
		m_port = port;
	}

	public void setStatus(int status) {
		m_status = status;
	}

	public void setTime(Date time) {
		m_time = time;
	}

	public void setUser(String user) {
		m_user = user;
	}

	@Override
	public String toString() {
		return "DalResult [ip=" + m_ip + ", port=" + m_port + ", database=" + m_database + ", env=" + m_env + ", user="
		      + m_user + ", time=" + m_time + ", action=" + m_action + ", planOperated=" + m_planOperated
		      + ", actualOperated=" + m_actualOperated + ", message=" + m_message + ", status=" + m_status + "]";
	}
}
