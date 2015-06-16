package com.dianping.zebra.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianping.zebra.admin.service.AlarmService;
import com.dianping.zebra.admin.service.HttpService;
import com.dianping.zebra.util.StringUtils;

@Service
public class AlarmServiceImpl implements AlarmService {

	private static final String SMS_URL = "http://paas-sevice09.nh:8080/sms/send";

	private static final String WEXIN_URL = "http://dpoa.api.dianping.com/app/monitor/cat/push";

	@Autowired
	private HttpService httpService;

	@Override
	public boolean sendSms(String mobile, String body) {
		if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(body)) {
			String params = String.format("mobile=%s&body=%s", mobile, body);

			String response = httpService.sendPost(SMS_URL, params);

			return response.contains("success");
		}

		return false;
	}

	@Override
	public boolean sendWeixin(String email, String title, String content) {
		if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(title) && StringUtils.isNotBlank(content)) {
			String params = String.format("email=%s&title=%s&content=%s", email, title, content);

			String response = httpService.sendPost(WEXIN_URL, params);

			return response.contains("200");
		}

		return false;
	}

}
