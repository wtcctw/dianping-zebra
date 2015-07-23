package com.dianping.zebra.admin.manager;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dianping.lion.client.ConfigCache;
import com.dianping.zebra.biz.service.AlarmService;
import com.dianping.zebra.util.StringUtils;

@Component
public class MHAAlarmManager {

	private static final String LION_KEY_SMS = "zebra.monitorservice.alarm.sms";

	private static final String LION_KEY_WEIXIN = "zebra.monitorservice.alarm.weixin";

	private static final String DELIM = ",";

	private Set<String> smsTargets = new HashSet<String>();

	private Set<String> weixinTargets = new HashSet<String>();

	@Autowired
	private AlarmService alarmService;

	@PostConstruct
	public void init() {
		String smsValue = ConfigCache.getInstance().getProperty(LION_KEY_SMS);

		if (StringUtils.isNotBlank(smsValue)) {
			String[] splits = smsValue.split(DELIM);

			for (String split : splits) {
				smsTargets.add(split);
			}
		}

		String weixinValue = ConfigCache.getInstance().getProperty(LION_KEY_WEIXIN);

		if (StringUtils.isNotBlank(weixinValue)) {
			String[] splits = weixinValue.split(DELIM);

			for (String split : splits) {
				weixinTargets.add(split);
			}
		}
	}

	public void alarm(AlarmContent content) {
		for (String mobile : smsTargets) {
			alarmService.sendSms(mobile, content.getSmsContent());
		}

		for (String email : weixinTargets) {
			alarmService.sendWeixin(email, content.getTitle(), content.getWeiXinContent());
		}
	}

	public static class AlarmContent {
		private String dsId;

		private String op;

		public AlarmContent(String dsId,String op) {
			this.dsId = dsId;
			this.op = op;
		}
		
		public String getTitle() {
			return "Zebra-MHA markDown告警:";
		}
		
		public String getSmsContent() {
			return String.format("[%s] 主库:%s , %s ,请尽快处理!", getTitle(), dsId, op);
		}


		public String getWeiXinContent() {
				return String.format("[主库:s% , %s ,请尽快处理!]", dsId, op);
		}
	}
}
