package com.dianping.zebra.biz.entity;

import java.util.List;

public class AlarmProjectContent {

	private String key;

	private List<OwnerContent> owners;

	private AlarmProjectConfigContent config;

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return this.key;
	}

	public void setOwners(List<OwnerContent> owners) {
		this.owners = owners;
	}

	public List<OwnerContent> getOwners() {
		return this.owners;
	}

	public void setConfig(AlarmProjectConfigContent config) {
		this.config = config;
	}

	public AlarmProjectConfigContent getConfig() {
		return this.config;
	}
}
