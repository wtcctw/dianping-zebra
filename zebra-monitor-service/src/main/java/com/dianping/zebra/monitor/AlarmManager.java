package com.dianping.zebra.monitor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dianping.lion.client.ConfigCache;
import com.dianping.zebra.biz.service.AlarmService;
import com.dianping.zebra.biz.service.HttpService;
import com.dianping.zebra.util.StringUtils;

@Component
public class AlarmManager {

	private static final String LION_KEY_SMS = "zebra.monitorservice.alarm.sms";

	private static final String LION_KEY_WEIXIN = "zebra.monitorservice.alarm.weixin";

	private static final String LION_ALARM_HOSTNAME = "zebra.monitorservice.alarm.catalarmhost";

	private static final String DELIM = ",";

	private Set<String> smsTargets = new HashSet<String>();

	private Set<String> weixinTargets = new HashSet<String>();

	private String catHost = null;

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private HttpService httpService;

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

		catHost = ConfigCache.getInstance().getProperty(LION_ALARM_HOSTNAME);
	}

	public String getTitle() {
		return "Zebra-MonitorService告警";
	}

	public String makeSmsContent(AlarmContent alarmContent) {
		if (alarmContent.getContent() != null) {
			if (alarmContent.getDelay() == -1) {
			return String.format("[%s] 读库:%s  复制线程没有运行,自动被%s", getTitle(), alarmContent.getHostname(),
			      alarmContent.getContent());
			} else if (alarmContent.getDelay() == 0) {
			return String.format("[%s] 读库:%s 数据库连接超时,自动被%s", getTitle(), alarmContent.getHostname(),
			      alarmContent.getContent());
			} else {
			return String.format("[%s] 读库:%s 已延迟了%d秒,自动被%s", getTitle(), alarmContent.getHostname(),
			      alarmContent.getDelay(), alarmContent.getContent());
			}
		} else {
			return String.format("[%s] 读库:%s 已延迟了%d秒,请尽快进行排查", getTitle(), alarmContent.getHostname(),
			      alarmContent.getDelay());
		}
	}

	public String makeWeiXinContent(AlarmContent alarmContent) {
		if (alarmContent.getContent() != null) {
			if (alarmContent.getDelay() == -1) {
			return String.format("[读库:%s  复制线程没有运行,自动被%s]", alarmContent.getHostname(), alarmContent.getContent());
			} else if (alarmContent.getDelay() == 0) {
			return String.format("[读库:%s 数据库连接超时,自动被%s]", alarmContent.getHostname(), alarmContent.getContent());
			} else {
			return String.format("[读库:%s 已延迟了%d秒,自动被%s]", alarmContent.getHostname(), alarmContent.getDelay(),
			      alarmContent.getContent());
			}
		} else {
			return String.format("[读库:%s 已延迟了%d秒,请尽快进行排查]", alarmContent.getHostname(), alarmContent.getDelay());
		}
	}

	public String makeAlarmParam(AlarmContent alarmContent) throws UnsupportedEncodingException {
		alarmContent.setOp("insert");
		alarmContent.setType("SQL");
		alarmContent.setStatus("1");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		alarmContent.setAlterationDate(df.format(new Date()));
		String domain = ((alarmContent.getHostname()).split("-"))[0];
		if ("".equals(domain)) {
			domain = alarmContent.getHostname();
		}
		alarmContent.setDomain(domain);

		return "op=" + alarmContent.getOp() + "&type=" + alarmContent.getType() + "&title=" + alarmContent.getTitle()
		      + "&domain=" + alarmContent.getDomain() + "&hostname=" + alarmContent.getHostname() + "&ip="
		      + alarmContent.getIp() + "&user=" + alarmContent.getUser() + "&status=" + alarmContent.getStatus()
		      + "&alterationDate=" + URLEncoder.encode(alarmContent.getAlterationDate(), "utf-8") + "&content="
		      + URLEncoder.encode(alarmContent.getContent(), "utf-8");
	}

	public void sendCat(AlarmContent alarmContent) {
		String param = null;
		try {
			param = makeAlarmParam(alarmContent);
		} catch (UnsupportedEncodingException e) {
		}

		String url = catHost + param;

		httpService.sendGet(url);
	}

	public void alarm(AlarmContent alarmContent) {
		for (String mobile : smsTargets) {
			alarmService.sendSms(mobile, makeSmsContent(alarmContent));
		}

		for (String email : weixinTargets) {
			alarmService.sendWeixin(email, alarmContent.getTitle(), makeWeiXinContent(alarmContent));
		}

		if (alarmContent.getContent() != null) {
			sendCat(alarmContent);
		}
	}

}
