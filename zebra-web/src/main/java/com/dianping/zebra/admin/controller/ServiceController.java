package com.dianping.zebra.admin.controller;

import com.dianping.zebra.admin.monitor.MHAService;
import com.dianping.zebra.admin.service.DalConfigService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/service")
public class ServiceController extends BasicController {
	@Autowired
	private DalConfigService m_dalConfigService;

	@Autowired
	private MHAService m_dalService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object test(String action, String name) throws Exception {
		if ("initDs".equals(action)) {

			if (name != null) {
				return m_dalConfigService.generateConfig(name.toLowerCase());
			}
		} else {
			throw new Exception("unkown operation");
		}

		return null;
	}
}
