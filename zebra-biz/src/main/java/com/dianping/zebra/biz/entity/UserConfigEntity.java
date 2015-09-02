package com.dianping.zebra.biz.entity;

public class UserConfigEntity {
	
	private String name;
	
	private String mail;
	
	private String tel;
	
	private long   systemPermission;
	
	private long   massagePermission;
	
	public UserConfigEntity(String name, String mail, String tel, long systemPermission, long massagePermission) {
		this.name = name;
		this.mail = mail;
		this.tel  = tel;
		this.systemPermission  = systemPermission;
		this.massagePermission = massagePermission;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public long getSystemPermission() {
		return systemPermission;
	}

	public void setSystemPermission(long systemPermission) {
		this.systemPermission = systemPermission;
	}

	public long getMassagePermission() {
		return massagePermission;
	}

	public void setMassagePermission(long massagePermission) {
		this.massagePermission = massagePermission;
	}


	
}
