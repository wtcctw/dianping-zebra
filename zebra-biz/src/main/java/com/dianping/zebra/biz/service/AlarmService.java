package com.dianping.zebra.biz.service;

public interface AlarmService {
	
	public boolean sendSms(String mobile,String body);
	
	public boolean sendWeixin(String email, String title, String content);

}
