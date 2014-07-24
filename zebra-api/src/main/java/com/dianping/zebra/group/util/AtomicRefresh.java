package com.dianping.zebra.group.util;

public class AtomicRefresh {
	private String newPassword;

	private String newUser;

	private String oldPassword;

	private String oldUser;

	public synchronized String getNewPassword() {
		return newPassword;
	}

	public synchronized String getNewUser() {
		return newUser;
	}

	public synchronized boolean needToRefresh() {
		// 帐号和密码都改了，就需要 refresh
		return (!StringUtils.equals(newUser, oldUser)) && (!StringUtils.equals(newPassword, oldPassword));
	}

	public synchronized void reset() {
		oldPassword = newPassword;
		oldUser = newUser;
	}

	public synchronized void setPassword(String password) {
		this.newPassword = password;
	}

	public synchronized void setUser(String user) {
		this.newUser = user;
	}
}
