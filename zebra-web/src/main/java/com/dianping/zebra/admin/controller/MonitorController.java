package com.dianping.zebra.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.admin.monitor.MySQLMonitorServer;

@Controller
@RequestMapping(value = "/monitor")
public class MonitorController {
	
	private MySQLMonitorServer monitorServer;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@ResponseBody
	public Object addJdbcRef(String jdbcRef) throws Exception {

		return null;
	}
}
