package com.dianping.zebra.biz.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianping.zebra.biz.entity.UserInformationEntity;
import com.dianping.zebra.biz.service.HttpService;

@Service
public class UserServiceImpl {

	private static final String LION_KEY = "zebra-web.system.userconfig";
	
	@Autowired
	HttpService httpservice;
	
	private List<Map<String,UserInformationEntity>> getAllUsers() {
		return null;
	}
	
	public Map<String,UserInformationEntity> createUser(String id,UserInformationEntity userInformation) {
		return null;
	}
	
	Map<String,UserInformationEntity> editUser(String id,UserInformationEntity userInformation) {
		return null;
	}
	
	public Map<String,UserInformationEntity> deleteUser(String id) {
		return null;
	}
	
	public Map<String,UserInformationEntity> getUserInformation(String id) {
		return null;
	}
	
	public int getSystemPermission(String id) {
		return 0;

	}
	
	public int getAlarmPermission(String id) {
		return 0;

	}
	
	public int setSystemPermission(String id,int newSystemPermission) {
		return 0;

	}
	
	public int setAlarmPermission(String id,int newAlarmPermission) {
		return 0;

	}
	
	
}
