package com.dianping.zebra.biz.service;

public interface HttpService {

	public String sendGet(String url);
	
	public String sendPost(String url, String params);
}
