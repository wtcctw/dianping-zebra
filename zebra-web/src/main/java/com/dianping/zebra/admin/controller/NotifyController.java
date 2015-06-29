package com.dianping.zebra.admin.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.Constants;
import com.dianping.zebra.admin.mapper.HeartbeatDto2HeartbeatEntityConvert;
import com.dianping.zebra.admin.service.CmdbService;
import com.dianping.zebra.admin.service.ReportService;
import com.dianping.zebra.biz.dao.HeartbeatMapper;
import com.dianping.zebra.biz.dto.HeartbeatDto;
import com.dianping.zebra.biz.entity.HeartbeatEntity;
import com.dianping.zebra.monitor.model.DataSourceInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/notify")
public class NotifyController extends BasicController {

	@Autowired
	private ReportService reportService;

	@Autowired
	private HeartbeatDto2HeartbeatEntityConvert convert;

	@Autowired
	private HeartbeatMapper heartbeatDao;

	@Autowired
	private CmdbService cmdbService;
	
	private Type listType = new TypeToken<ArrayList<DataSourceInfo>>() {
   }.getType();

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object notify(HeartbeatDto model) {
		if (model.getApp() != null && model.getIp() != null && model.getDataSourceBeanName() != null) {
			if (!model.getApp().contains("job")) {
				HeartbeatEntity entity = convert.convert(model);
				reportService.createOrUpdate(entity);
			}
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Object batchNotify(String infos) {
		Gson gson = new Gson();
		List<DataSourceInfo> dsInfos = gson.fromJson(infos, listType);
		
		if (dsInfos != null && dsInfos.size() > 0) {
			DataSourceInfo dsInfo = dsInfos.get(0);

			if (dsInfo.getApp() != null && dsInfo.getIp() != null) {
				heartbeatDao.deleteHeartbeatByAppAndIp(dsInfo.getApp(), dsInfo.getIp());
			}

			for (DataSourceInfo info : dsInfos) {
				HeartbeatEntity entity = convert.convert(info);

				if (entity.getApp_name().equalsIgnoreCase(Constants.PHOENIX_APP_NO_NAME) && entity.getIp() != null) {
					String name = cmdbService.getAppName(entity.getIp());

					if (!name.equalsIgnoreCase(Constants.PHOENIX_APP_NO_NAME)) {
						entity.setApp_name(name);
					}
				}
				
				heartbeatDao.insertHeartbeat(entity);
			}
		}

		return null;
	}

}
