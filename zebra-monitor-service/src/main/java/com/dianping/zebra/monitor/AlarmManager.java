package com.dianping.zebra.monitor;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dianping.lion.client.ConfigCache;
import com.dianping.zebra.biz.service.AlarmService;
import com.dianping.zebra.util.StringUtils;

@Component
public class AlarmManager {

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
		private String ds;

		private String op;

		public AlarmContent(String ds, String op) {
			this.ds = ds;
			this.op = op;
		}

		public String getSmsContent() {
			return String.format("[%s] 读库:%s 已自动被%s", getTitle(), ds, op);
		}

		public String getTitle() {
			return "Zebra-MonitorService告警";
		}

		public String getWeiXinContent() {
			return String.format("[读库:%s 已自动被%s]", ds, op);
		}
	}
}
