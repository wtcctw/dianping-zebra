package com.dianping.zebra.admin.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.biz.entity.AlarmProjectContent;
import com.dianping.zebra.biz.entity.OwnerContent;
import com.dianping.zebra.biz.service.LionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jodd.util.StringUtil;

@Controller
@RequestMapping(value = "/alarm")
public class MonitorAlarmController {
	
	@Autowired
	private LionService lionService;
	
	private final static String LION_KEY_PROJECTCONFIG = "zebra-web.monitor.projectconfig";
	
	private final static String DEFAULT_PROJECTCONFIG = "zebra-default";

	private Gson gson = new Gson();
	
	private Type type = new TypeToken<List<AlarmProjectContent>>() {
	}.getType();

	@RequestMapping(value = "/getProjectConfig", method = RequestMethod.POST)
	@ResponseBody
	public Object getProjectConfig() {
		
		return lionService.getConfigFromZk(LION_KEY_PROJECTCONFIG);
	}


	@RequestMapping(value = "/saveConfig", method = RequestMethod.POST)
	@ResponseBody
	public void sendChangeMassage(@RequestBody Object projectConfig) {
		if (projectConfig == null) {
			return;
		}
		
		lionService.setConfig(lionService.getEnv(), LION_KEY_PROJECTCONFIG, gson.toJson(projectConfig));
	}
	
	private List<AlarmProjectContent> getProjectConfigs() {
		Object projectConfig = lionService.getConfigFromZk(LION_KEY_PROJECTCONFIG);
		
		return gson.fromJson(gson.toJson(projectConfig),type);
	}
	
	@RequestMapping(value = "/addDefaultOwner", method = RequestMethod.GET)
	@ResponseBody
	public boolean addDefaultOwner(String name, String tel, String wechat, int permission) {
		if(StringUtil.isNotBlank(name) && StringUtil.isNotBlank(tel) && StringUtil.isNotBlank(wechat)) {
			OwnerContent newOwner = new OwnerContent();
			newOwner.setName(name);
			newOwner.setTel(tel);
			newOwner.setWechat(wechat);
			newOwner.setPermission(permission);
			
			List<AlarmProjectContent> projects = getProjectConfigs();
			for(AlarmProjectContent project : projects) {
				if(DEFAULT_PROJECTCONFIG.equals(project.getKey())) {
					project.getOwners().add(newOwner);
				}
			}

			lionService.setConfig(lionService.getEnv(), LION_KEY_PROJECTCONFIG, gson.toJson(projects));
			
			return true;
		}
		
		return false;
	}
	
	@RequestMapping(value = "/removeDefaultOwner", method = RequestMethod.GET)
	@ResponseBody
	public boolean removeDefaultOwner(String tel) {
		if(StringUtil.isNotBlank(tel)) {
			List<AlarmProjectContent> projects = getProjectConfigs();
			
			for(AlarmProjectContent project : projects) {
				if(DEFAULT_PROJECTCONFIG.equals(project.getKey())) {
					int index = 0;
					for(OwnerContent owner : project.getOwners()) {
						if(tel.equals(owner.getTel())) {
							project.getOwners().remove(index);
						}
						index++;
					}
				}
			}

			lionService.setConfig(lionService.getEnv(), LION_KEY_PROJECTCONFIG, gson.toJson(projects));
			
			return true;
		}
		
		return false;
	}
}
