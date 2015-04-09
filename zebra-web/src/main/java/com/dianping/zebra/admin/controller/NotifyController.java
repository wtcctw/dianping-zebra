package com.dianping.zebra.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.admin.dto.HeartbeatDto;
import com.dianping.zebra.admin.entity.HeartbeatEntity;
import com.dianping.zebra.admin.mapper.HeartbeatDto2HeartbeatEntityConvert;
import com.dianping.zebra.admin.service.ReportService;

@Controller
@RequestMapping(value = "/notify")
public class NotifyController {

	@Autowired
	private ReportService reportService;

	@Autowired
	private HeartbeatDto2HeartbeatEntityConvert convert;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object notify(HeartbeatDto model) {
		if (model.getApp() != null && model.getIp() != null && model.getDataSourceBeanName() != null) {

			if (!model.getIp().startsWith("10.128")) {
				HeartbeatEntity entity = convert.convert(model);
				reportService.createOrUpdate(entity);
			}
		}
		return null;
	}
}
