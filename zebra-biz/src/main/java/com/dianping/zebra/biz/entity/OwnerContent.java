package com.dianping.zebra.biz.entity;

public class OwnerContent {

	private String name;

	private String tel;

	private String wechat;

	private int permission;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTel() {
		return this.tel;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getWechat() {
		return this.wechat;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public int getPermission() {
		return this.permission;
	}

}
