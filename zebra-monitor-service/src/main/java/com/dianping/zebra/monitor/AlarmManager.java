package com.dianping.zebra.monitor;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dianping.zebra.biz.entity.AlarmProjectContent;
import com.dianping.zebra.biz.entity.AlarmResource;
import com.dianping.zebra.biz.entity.OwnerContent;
import com.dianping.zebra.biz.service.AlarmService;
import com.dianping.zebra.biz.service.HttpService;
import com.dianping.zebra.biz.service.LionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jodd.util.StringUtil;

@Component("alarmManager")
public class AlarmManager {
	@Autowired
	private LionService lionService;

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private HttpService httpService;

	private static final String LION_KEY_PROJECTCONFIG = "zebra-web.monitor.projectconfig";

	private static final String LION_KEY_CATHOST = "zebra.monitorservice.alarm.catalarmhost";

	private final static String DEFAULT_PROJECTCONFIG = "zebra-default";

	private Gson gson = new Gson();

	private Type type = new TypeToken<List<AlarmProjectContent>>() {
	}.getType();

	/* 根据告警类型，生成不同的告警内容并向 微信，短信,cat数据库大盘发送告警 */
	public void alarm(int job, AlarmContent alarmContent) {
		String jdbcRef = alarmContent.getHostname().substring(0, alarmContent.getHostname().indexOf("-"));
		List<OwnerContent> owners = getOwnersByKey(jdbcRef);

		if (owners != null && owners.size() > 0) {
			for (OwnerContent owner : owners) {
				switch(job) {
				case AlarmResource.MARKDOWN :
					if((owner.getPermission() & (1<<AlarmResource.MARKDOWN)) > 0) {
						alarmService.sendSms(owner.getTel(), makeAlarmMessage(job, alarmContent));
						alarmService.sendWeixin(owner.getWechat().substring(0,owner.getWechat().indexOf("@")),makeAlarmMessage(job, alarmContent));
					}
					break;
				case AlarmResource.DELAY:
					if((owner.getPermission() & (1<<AlarmResource.DELAY)) > 0) {
						alarmService.sendSms(owner.getTel(), makeAlarmMessage(job, alarmContent));
						alarmService.sendWeixin(owner.getWechat().substring(0,owner.getWechat().indexOf("@")),makeAlarmMessage(job, alarmContent));
					}
					break;
				case AlarmResource.CHANGE:
					if((owner.getPermission() & (1<<AlarmResource.CHANGE)) > 0) {
						alarmService.sendSms(owner.getTel(), makeAlarmMessage(job, alarmContent));
						alarmService.sendWeixin(owner.getWechat().substring(0,owner.getWechat().indexOf("@")),makeAlarmMessage(job, alarmContent));
					}
					break;
				default: break;
				}
			}
		}

		if(alarmContent.getContent() != null) {
			sendCat(alarmContent);
		}
	}

	/* 获取该key所对应的负责人列表 如没有找见该key 则使用默认负责人 */
	public List<OwnerContent> getOwnersByKey(String key) {
		List<OwnerContent> result = null;

		if (StringUtil.isNotBlank(key)) {
			List<AlarmProjectContent> projects = getProjectConfigs();

			for (AlarmProjectContent projectInfo : projects) {
				if (key.equals(projectInfo.getKey())) {
					result = projectInfo.getOwners();
					break;
				}
			}

			if (result == null) {
				for (AlarmProjectContent projectInfo : projects) {
					if (projectInfo.getKey().equals(DEFAULT_PROJECTCONFIG)) {
						result = projectInfo.getOwners();
						break;
					}
				}
			}
		}

		return result;
	}

	private List<AlarmProjectContent> getProjectConfigs() {

		return gson.fromJson(lionService.getConfigFromZk(LION_KEY_PROJECTCONFIG), type);
	}

	/* 发送cat的数据库大盘提示 */
	public void sendCat(AlarmContent alarmContent) {
		try {
			String param = makeCatAlarmParam(alarmContent);
			String catHost = lionService.getConfigFromZk(LION_KEY_CATHOST);
			String url = catHost + param;

			httpService.sendGet(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private String makeCatAlarmParam(AlarmContent alarmcontent) throws UnsupportedEncodingException {
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

		return "op=" + alarmcontent.getOp() + "&type=" + alarmcontent.getType() + "&title=" + alarmcontent.getTitle()
				+ "&domain=" + alarmcontent.getDomain() + "&hostname=" + alarmcontent.getHostname() + "&ip="
				+ alarmcontent.getIp() + "&user=" + alarmcontent.getUser() + "&status=" + alarmcontent.getStatus()
				+ "&alterationDate=" + URLEncoder.encode(alarmcontent.getAlterationDate(), "UTF-8") + "&content="
				+ URLEncoder.encode(alarmcontent.getContent(), "UTF-8");
	}

	/* 生成告警信息内容 */
	public String makeAlarmMessage(int job, AlarmContent alarmContent) {
		String result = null;

		switch (job) {
		case AlarmResource.MARKDOWN:
			if (alarmContent.getDelay() == -1) {
				result = String.format("[%s]读库:%s复制线程没有运行,自动被%s", getTitle(), alarmContent.getHostname(),
						alarmContent.getContent());
			} else if (alarmContent.getDelay() == 0) {
				result = String.format("[%s]读库:%s数据库连接超时,自动被%s", getTitle(), alarmContent.getHostname(),
						alarmContent.getContent());
			} else {
				result = String.format("[%s]读库:%s已延迟了%d秒,自动被%s", getTitle(), alarmContent.getHostname(),
						alarmContent.getDelay(), alarmContent.getContent());
			}
			break;
		case AlarmResource.DELAY:
			result = String.format("[%s]读库:%s已延迟了%d秒,请尽快进行排查!", getTitle(), alarmContent.getHostname(),
					alarmContent.getDelay());
			break;
		case AlarmResource.CHANGE:
			result = String.format("[%s]读库:%s,状态发生变更", getTitle(), alarmContent.getHostname(),
					alarmContent.getContent());
		default:
			break;
		}

		return result;
	}

	public String getTitle() {
		return "Zebra-MonitorService告警";
	}

}