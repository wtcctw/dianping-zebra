package com.dianping.zebra.monitor;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dianping.zebra.biz.entity.AlarmProjectConfigContent;
import com.dianping.zebra.biz.entity.AlarmProjectContent;
import com.dianping.zebra.biz.service.LionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jodd.util.StringUtil;

@Component("projectConfigHandler")
public class ProjectConfigHandler {
	@Autowired
	private LionService lionService;

	private static final String LION_KEY_PROJECTCONFIG = "zebra-web.monitor.projectconfig";

	private final static String DEFAULT_PROJECTCONFIG = "zebra-default";

	private Gson gson = new Gson();

	private Type type = new TypeToken<List<AlarmProjectContent>>() {
	}.getType();

	public AlarmProjectConfigContent getProjectConfigByKey(String key) {
		AlarmProjectConfigContent result = null;
		
		if(StringUtil.isNotBlank(key)) {
			List<AlarmProjectContent> projects = getProjectConfigs();

			for(AlarmProjectContent projectConfig : projects) {
				if(key.equals(projectConfig.getKey())) {
					result = projectConfig.getConfig();
				}
			}
			
			if(result == null) {
				for(AlarmProjectContent projectConfig : projects) {
					if(DEFAULT_PROJECTCONFIG.equals(projectConfig.getKey())) {
						result = projectConfig.getConfig();
					}
				}
			}
		}
		
		return result;
	}

	private List<AlarmProjectContent> getProjectConfigs() {

		return gson.fromJson(lionService.getConfigFromZk(LION_KEY_PROJECTCONFIG), type);
	}
}
