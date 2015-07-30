package com.dianping.zebra.admin.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dianping.lion.client.ConfigCache;
import com.dianping.zebra.admin.util.MHAAlarmContent;
import com.dianping.zebra.biz.service.AlarmService;
import com.dianping.zebra.biz.service.HttpService;
import com.dianping.zebra.util.StringUtils;

@Component
public class MHAAlarmManager {

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
		return "Zebra-MHA markDown告警:";
	}

	public String makeSmsContent(MHAAlarmContent alarmcontent) {
		return String.format("[%s] 主库:%s , %s ,请尽快处理!", getTitle(), alarmcontent.getHostname(),
		      alarmcontent.getContent());
	}

	public String makeWeiXinContent(MHAAlarmContent alarmcontent) {
		return String.format("[主库:%s , %s ,请尽快处理!]", alarmcontent.getHostname(), alarmcontent.getContent());
	}
	
	public String makeAlarmParam(MHAAlarmContent alarmcontent) {
		alarmcontent.setOp("insert");
		alarmcontent.setType("SQL");
		alarmcontent.setStatus("1");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		alarmcontent.setAlterationDate(df.format(new Date()));
		String domain = ((alarmcontent.getHostname()).split("-"))[0];
		if ("".equals(domain)) {
			domain = alarmcontent.getHostname();
		}
		alarmcontent.setDomain(domain);

		return "op=" + alarmcontent.getOp() + "&type=" + alarmcontent.getType() + "&title="
		      + alarmcontent.getTitle() + "&domain=" + alarmcontent.getDomain() + "&hostname="
		      + alarmcontent.getHostname() + "&ip=" + alarmcontent.getIp() + "&user=" + alarmcontent.getUser()
		      + "&status=" + alarmcontent.getStatus() + "&alterationDate=" + alarmcontent.getAlterationDate()
		      + "&content=" + alarmcontent.getContent();
	}

	public void sendCat(MHAAlarmContent alarmContent) {
		String param = makeAlarmParam(alarmContent);
		String url = catHost + param;

		httpService.sendGet(url);
	}
	
	public void alarm(MHAAlarmContent alarmContent) {

		for (String mobile : smsTargets) {
			alarmService.sendSms(mobile, makeSmsContent(alarmContent));
		}

		for (String email : weixinTargets) {
			alarmService.sendWeixin(email, alarmContent.getTitle(), makeWeiXinContent(alarmContent));
		}

		sendCat(alarmContent);
	}
	
}