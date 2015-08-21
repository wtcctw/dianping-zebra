package com.dianping.zebra.syncservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.syncservice.executor.ExecutorManager;
import com.dianping.zebra.syncservice.monitor.TaskExecutorMetric;

@Controller
@RequestMapping(value = "/status")
public class StatusController {

	@Autowired
	private ExecutorManager executorManager;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Map<String, TaskExecutorMetric> getStatus() {
		return executorManager.getStatus();
	}
}