package com.dianping.zebra.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.cat.Cat;
import com.dianping.zebra.biz.dto.MHAResultDto;
import com.dianping.zebra.biz.entity.AlarmResource;
import com.dianping.zebra.biz.service.HaHandler;
import com.dianping.zebra.biz.service.HaHandler.Operator;
import com.dianping.zebra.biz.service.LionService;
import com.dianping.zebra.admin.util.AlarmContent;
import com.dianping.zebra.admin.manager.MHAAlarmManager;

/**
 * 给外部系统——MHA集群调用的接口，请勿轻易改变
 * 
 * @author damonzhu
 *
 */
@Controller
@RequestMapping(value = "/mha")
public class MHAController extends BasicController {

	private final String PORJECT = "ds";
	
	private MHAAlarmManager mhaAlarmManager;
	
	@Autowired
	private HaHandler haHandler;

	@Autowired
	private LionService lionService;

	// 给mha集群调用
	@RequestMapping(value = "/markdown", method = RequestMethod.GET)
	@ResponseBody
	public Object markdown(String ip, String port) {
		MHAResultDto result = new MHAResultDto();

		if (ip != null && port != null) {
			Set<String> dsIds = findDsIds(ip, port);

			if (dsIds != null) {
				for (String dsId : dsIds) {
					haHandler.markdown(dsId, Operator.MHA);
					mhaAlarmManager.alarm(AlarmResource.MARKDOWN,new AlarmContent(dsId,"MHA","MHA",ip,"MarkDown by MHA"));
				}
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
			String active;
			try {
				active = lionService.getConfigByHttp(lionService.getEnv(), "ds." + dsId + ".active");

				if (active == null || active.equalsIgnoreCase("false")) {
					haHandler.markup(dsId, Operator.PEOPLE);
				}

				result.addDsId(dsId);
			} catch (IOException e) {
				Cat.logError(e);
			}
		}

		result.setStatus("success");

		return result;
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	@ResponseBody
	public Object find(String ip, String port) {
		return findDsIds(ip, port);
	}

	private Set<String> findDsIds1(String ip, String port, HashMap<String, String> keyValues) {
		String content = ip + ":" + port;

		Set<String> dsIds = new HashSet<String>();

		for (Entry<String, String> entry : keyValues.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			if (key != null && key.contains(".jdbc.url") && value != null && value.contains(content.trim())) {
				int begin = "ds.".length();
				int end = key.indexOf(".jdbc.url");

				dsIds.add(key.substring(begin, end));
			}
		}

		return dsIds;
	}

	private Set<String> findDsIds(String ip, String port) {
		try {
			HashMap<String, String> dsKV = lionService.getConfigByProject(lionService.getEnv(), PORJECT);
			Set<String> dsIds = findDsIds1(ip, port, dsKV);

			HashMap<String, String> groupdsKV = lionService.getConfigByProject(lionService.getEnv(), "groupds");

			Iterator<String> iterator = dsIds.iterator();
			while (iterator.hasNext()) {
				String dsId = iterator.next();

				// 第一步过滤写账号
				if (dsId.contains("write")) {
					iterator.remove();

					continue;
				}

				// 第二步过滤没有被groupds使用的dsId
				boolean hasRef = false;
				for (String value : groupdsKV.values()) {
					if (value.contains(dsId)) {
						hasRef = true;
						break;
					}
				}

				if (!hasRef) {
					iterator.remove();
				}
			}

			return dsIds;
		} catch (IOException e) {
			return null;
		}
	}
}
