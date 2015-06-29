package com.dianping.zebra.biz.service;

public interface HttpService {

	String sendGet(String url);
	
	String sendPost(String url, String params);
}
