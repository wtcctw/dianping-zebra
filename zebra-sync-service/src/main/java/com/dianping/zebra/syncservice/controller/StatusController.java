package com.dianping.zebra.syncservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.biz.monitor.TaskExecutorMetric;
import com.dianping.zebra.syncservice.executor.TaskManager;

@Controller
@RequestMapping(value = "/status")
public class StatusController {

	@Autowired
	private TaskManager executorManager;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Map<String, TaskExecutorMetric> getStatus() {
		return executorManager.getStatus();
	}
}
