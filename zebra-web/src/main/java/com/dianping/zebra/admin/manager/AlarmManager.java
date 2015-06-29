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
public class AlarmManager {

	private static final String LION_KEY_SMS = "zebra.syncservice.alarm.sms";

	private static final String LION_KEY_WEIXIN = "zebra.syncservice.alarm.weixin";

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
		private String syncTaskName;

		private String fromSyncIp;

		private String toSyncIp;

		public AlarmContent(String syncTaskName, String fromSyncIp, String toSyncIp) {
			this.syncTaskName = syncTaskName;
			this.fromSyncIp = fromSyncIp;
			this.toSyncIp = toSyncIp;
		}

		public String getSmsContent() {
			if (StringUtils.isNotBlank(toSyncIp)) {
				return String.format("[%s] 同步任务:%s 在同步服务器1:%s已挂,灾备到同步服务器2:%s", getTitle(), syncTaskName, fromSyncIp,
				      toSyncIp);
			} else {
				return String.format("[%s] 同步任务:%s 在同步服务器1:%s已挂,无法找到其灾备服务器!", getTitle(), syncTaskName, fromSyncIp);
			}
		}

		public String getTitle() {
			return "Zebra-SyncService告警";
		}

		public String getWeiXinContent() {
			if (StringUtils.isNotBlank(toSyncIp)) {
				return String.format("[同步任务:%s 在同步服务器1:%s已挂,灾备到同步服务器2:%s]", syncTaskName, fromSyncIp, toSyncIp);
			} else {
				return String.format("[同步任务:%s 在同步服务器1:%s已挂,无法找到其灾备服务器!!]", syncTaskName, fromSyncIp);
			}
		}
	}
}
