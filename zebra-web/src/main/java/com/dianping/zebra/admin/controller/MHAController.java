package com.dianping.zebra.admin.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.admin.service.MHAService;

@Controller
@RequestMapping(value = "/mha")
public class MHAController {

	@Autowired
	private MHAService mhaService;

	// 给mha集群调用
	@RequestMapping(value = "/markdown", method = RequestMethod.GET)
	@ResponseBody
	public Object markdown(String ip, String port) {
		if (ip != null && port != null) {
			Set<String> dsIds = mhaService.findDsIds(ip, port);

			if (dsIds != null) {
				mhaService.markDownDsIds(dsIds);
			}
		}
		
		return "success";
	}

	// 给zebra-web界面调用
	@RequestMapping(value = "/markup", method = RequestMethod.GET)
	@ResponseBody
	public Object markup(String dsId) {
		if (dsId != null) {
			mhaService.markUpDsId(dsId);
		}
		
		return "success";
	}
}
