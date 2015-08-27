//package com.dianping.zebra.admin.controller;
//
//import java.lang.reflect.Type;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.client.RestTemplate;
//
//import com.dianping.zebra.biz.entity.SyncServerMonitorEntity;
//import com.dianping.zebra.biz.monitor.TaskExecutorMetric;
//import com.dianping.zebra.biz.service.SyncServerMonitorService;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//@Controller
//@RequestMapping(value = "/sync-server")
//public class SyncServerController extends BasicController {
//
//	@Autowired
//	private SyncServerMonitorService syncServerMonitorService;
//
//	@Autowired
//	private RestTemplate restClient;
//
//	private Gson gson = new Gson();
//
//	private Type type = new TypeToken<Map<String, TaskExecutorMetric>>() {
//	}.getType();
//
//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseBody
//	public List<SyncServerMonitorEntity> listServers() {
//		return syncServerMonitorService.getAllAlive();
//	}
//
//	@RequestMapping(value = "/status",method = RequestMethod.GET)
//	@ResponseBody
//	public Object getStatus() {
//		return null;
//	}
//}
