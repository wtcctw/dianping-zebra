package com.dianping.zebra.admin.service;

public interface HttpService {

	String sendGet(String url);
	
	String sendPost(String url, String params);
}
