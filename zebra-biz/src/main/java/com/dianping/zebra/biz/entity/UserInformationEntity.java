package com.dianping.zebra.biz.entity;

public class UserInformationEntity {
	
	private String name;
	
	private String wechat;
	
	private String tel;
	
	private int   systemPermission;
	
	private int   alarmPermission;
	
	public UserInformationEntity(String name, String wechat, String tel, int alarmPermission) {
		this.name = name;
		this.wechat = wechat;
		this.tel  = tel;
		this.systemPermission  = 0;
		this.alarmPermission = alarmPermission;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String mail) {
		this.wechat = mail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getSystemPermission() {
		return systemPermission;
	}

	public void setSystemPermission(int systemPermission) {
		this.systemPermission = systemPermission;
	}

	public int getAlarmPermission() {
		return alarmPermission;
	}

	public void setAlarmPermission(int alarmPermission) {
		this.alarmPermission = alarmPermission;
	}


	
}
