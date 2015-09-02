package com.dianping.zebra.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianping.zebra.biz.service.HttpService;

@Service
public class UserServiceImpl {

	private static final String LION_KEY = "zebra-web.system.userconfig";
	
	@Autowired
	HttpService httpservice;
	
	
	
	
}
