package com.dianping.zebra.biz.service;

import java.util.Map;

import com.dianping.zebra.biz.entity.UserConfigEntity;

public interface UserService {

	public Map<String,UserConfigEntity> createUser(String id,UserConfigEntity userConfig);
	
	public Map<String,UserConfigEntity> changeUser(String id,UserConfigEntity userConfig);
	
	public Map<String,UserConfigEntity> deleteUser(String id);
	
	public Map<String,UserConfigEntity> getUserConfig(String id);
	
	public long getSystemPermission(String id);
	
	public long getMassagePermission(String id);
	
	public long changeSystemPermission(String id,long newSystemPermission);
	
	public long changeMassagePermission(String id,long newmassagePermission);
}
