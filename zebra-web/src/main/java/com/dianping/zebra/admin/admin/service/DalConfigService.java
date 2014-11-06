package com.dianping.zebra.admin.admin.service;

import com.dianping.zebra.group.config.DefaultDataSourceConfigManager;

import java.util.ArrayList;
import java.util.List;

public interface DalConfigService {
	public boolean generateConfig(String name);

	GroupConfigModel getDsConfig(String env, final String groupId);

	void updateDsConfig(GroupConfigModel modal);

	// for test purpose
	public void setProject(String project);

	public static class GroupConfigModel {
		private String env;

		private String id;

		private String config;

		private List<DsConfigModel> configs;

		public String getEnv() {
			return env;
		}

		public void setEnv(String env) {
			this.env = env;
		}

		public String getConfig() {
			return config;
		}

		public void setConfig(String config) {
			this.config = config;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public List<DsConfigModel> getConfigs() {
			return configs;
		}

		public void setConfigs(List<DsConfigModel> configs) {
			this.configs = configs;
		}
	}

	public static class DsConfigModel {
		private String id;

		private boolean selected = false;

		private boolean isMerged = false;

		private boolean isDelete = false;

		private DefaultDataSourceConfigManager.ReadOrWriteRole role;

		private List<ConfigProperty> properties = new ArrayList<ConfigProperty>();

		public DefaultDataSourceConfigManager.ReadOrWriteRole getRole() {
			return role;
		}

		public void setRole(DefaultDataSourceConfigManager.ReadOrWriteRole role) {
			this.role = role;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public boolean getSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}

		public boolean getIsMerged() {
			return isMerged;
		}

		public void setIsMerged(boolean isMerged) {
			this.isMerged = isMerged;
		}

		public boolean getIsDelete() {
			return isDelete;
		}

		public void setIsDelete(boolean isDelete) {
			this.isDelete = isDelete;
		}

		public List<ConfigProperty> getProperties() {
			return properties;
		}

		public void setProperties(List<ConfigProperty> properties) {
			this.properties = properties;
		}
	}

	public static class ConfigProperty {
		private String key;

		private String value;

		private boolean isCreate = false;

		private String newValue;

		private boolean isDelete;

		public boolean isCreate() {
			return isCreate;
		}

		public void setCreate(boolean isCreate) {
			this.isCreate = isCreate;
		}

		public boolean isDelete() {
			return isDelete;
		}

		public void setDelete(boolean isDelete) {
			this.isDelete = isDelete;
		}

		public String getNewValue() {
			return newValue;
		}

		public void setNewValue(String newValue) {
			this.newValue = newValue;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
