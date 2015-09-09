package com.dianping.zebra.biz.service;

import java.util.Map;

import com.dianping.zebra.biz.entity.UserInformationEntity;

public interface UserService {

	public Map<String,UserInformationEntity> createUser(String id,UserInformationEntity userInformation);
	
	public Map<String,UserInformationEntity> editUser(String id,UserInformationEntity userInformation);
	
	public Map<String,UserInformationEntity> deleteUser(String id);
	
	public Map<String,UserInformationEntity> getUserInformation(String id);
	
	public int getSystemPermission(String id);
	
	public int getAlarmPermission(String id);
	
	public int setSystemPermission(String id,int newSystemPermission);
	
	public int setAlarmPermission(String id,int newAlarmPermission);
}
