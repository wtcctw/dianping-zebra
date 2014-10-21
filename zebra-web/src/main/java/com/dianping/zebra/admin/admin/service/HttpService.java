package com.dianping.zebra.admin.admin.service;

public interface HttpService {

	String sendGet(String url);
	
	String sendPost(String url, String params);
}
