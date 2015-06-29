package com.dianping.zebra.admin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.biz.dto.MHAResultDto;
import com.dianping.zebra.biz.service.LionService;
import com.dianping.zebra.biz.service.MHAService;

@Controller
@RequestMapping(value = "/mha")
public class MHAController extends BasicController {

	private static final String lionKey = "zebra.server.monitor.mha.markdown";

	@Autowired
	private MHAService mhaService;

	@Autowired
	private LionService lionService;

	@RequestMapping(value = "/allMarkedDown", method = RequestMethod.GET)
	@ResponseBody
	public Object listMarkDownByMHA() throws Exception {
		String configFromZk = lionService.getConfigFromZk(lionKey);
		
		Map<String,String> results = new HashMap<String,String>();
		
		if(configFromZk != null && configFromZk.length() > 0){
			
			String[] dsIds = configFromZk.split(",");
			
			for(String dsId : dsIds){
				results.put(dsId, dsId);
			}
		}
		
		return results;
	}
	
	// 给mha集群调用
	@RequestMapping(value = "/markdown", method = RequestMethod.GET)
	@ResponseBody
	public Object markdown(String ip, String port) {
		MHAResultDto result = new MHAResultDto();

		if (ip != null && port != null) {
			Set<String> dsIds = mhaService.findDsIds(ip, port);

			if (dsIds != null) {
				mhaService.markDownDsIds(dsIds);
			}

			result.setDsIds(dsIds);
		}

		result.setStatus("success");

		return result;
	}

	// 给zebra-web界面调用
	@RequestMapping(value = "/markup", method = RequestMethod.GET)
	@ResponseBody
	public Object markup(String dsId) {
		MHAResultDto result = new MHAResultDto();

		if (dsId != null) {
			mhaService.markUpDsId(dsId);

			result.addDsId(dsId);
		}

		result.setStatus("success");

		return result;
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseBody
	public Object find(String ip, String port) {
		return mhaService.findDsIds(ip, port);
	}
}
