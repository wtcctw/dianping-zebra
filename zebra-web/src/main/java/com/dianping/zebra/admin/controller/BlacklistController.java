package com.dianping.zebra.admin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.admin.dto.SqlFlowControlDto;
import com.dianping.zebra.admin.service.SqlFlowControlService;

/**
 * Dozer @ 2015-02 mail@dozer.cc http://www.dozer.cc
 */

@Controller
@RequestMapping(value = "/blacklist")
public class BlacklistController {
	@Autowired
	private SqlFlowControlService blackListService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object index(String env) throws IOException {
		return blackListService.getAllBlackList(env);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(String env, @RequestBody SqlFlowControlDto dto) throws IOException {
		if(dto.getSqlId() != null && dto.getSqlId().length() > 0){
			blackListService.addItem(env, dto.getIp(), dto.getSqlId(), dto.getSql(), dto.getAllowPercent());
		}
		
		return null;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(String env, String key) throws IOException {
		blackListService.deleteItem(env, key);
		return null;
	}
}
