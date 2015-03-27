package com.dianping.zebra.admin.controller;

import java.util.HashSet;
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

	public class MHAResultDto {
		private String status;

		private Set<String> dsIds;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Set<String> getDsIds() {
			return dsIds;
		}

		public void setDsIds(Set<String> dsIds) {
			this.dsIds = dsIds;
		}

		public void addDsId(String dsId) {
			if (dsIds == null) {
				dsIds = new HashSet<String>();
			}

			dsIds.add(dsId);
		}
	}
}
