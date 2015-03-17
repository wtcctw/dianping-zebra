package com.dianping.zebra.admin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.admin.dto.FlowControlDto;
import com.dianping.zebra.admin.service.BlackListService;
import com.dianping.zebra.admin.service.FlowControlService;

/**
 * Dozer @ 2015-02 mail@dozer.cc http://www.dozer.cc
 */

@Controller
@RequestMapping(value = "/flowcontrol")
public class FlowControlController {
	@Autowired
	private FlowControlService flowControlService;

	// TODO: to remove this when all applications are upgrating new dal version.
	@Autowired
	private BlackListService blackListService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object index(String env) throws IOException {
		return flowControlService.getAllActiveFlowControl(env);
	}

	@RequestMapping(value = "history", method = RequestMethod.GET)
	@ResponseBody
	public Object history(String env) throws IOException {
		return flowControlService.getAllDeletedFlowControl(env);
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(String env, @RequestBody FlowControlDto dto) throws IOException {
		if (dto.getSqlId() != null && dto.getSqlId().length() > 0) {
			boolean contain = flowControlService.containFlowControl(env, dto.getSqlId());

			if (contain) {
				// modify
				flowControlService.modifyItem(env, dto.getSqlId(), dto.getAllowPercent(), dto.getDatabase());
			} else {
				// create
				boolean ok = flowControlService.addItem(env, dto.getIp(), dto.getSqlId(), dto.getSql(),
				      dto.getAllowPercent(), dto.getDatabase());

				if (ok) {
					blackListService.addItem(env, null, dto.getSqlId(), null);
				}
			}
		}

		return null;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(String env, String key) throws IOException {
		boolean contain = flowControlService.containFlowControl(env, key);

		if (contain) {
			boolean ok = flowControlService.deleteItem(env, key);

			if (ok) {
				blackListService.deleteItem(env, "zebra-sql-blacklist.global.id", key);
			}
		}
		return null;
	}
}
