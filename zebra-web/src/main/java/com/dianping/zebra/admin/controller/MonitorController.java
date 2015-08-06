package com.dianping.zebra.admin.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.dianping.cat.Cat;
import com.dianping.zebra.biz.dao.MonitorHistoryMapper;
import com.dianping.zebra.biz.dto.InstanceStatusDto;
import com.dianping.zebra.biz.service.LionService;
import com.dianping.zebra.util.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import groovy.util.MapEntry;

@Controller
@RequestMapping(value = "/monitor")
public class MonitorController extends BasicController {

	private static final String LION_KEY = "zebra.monitorservice.jdbcreflist";

	@Autowired
	private LionService lionService;

	@Autowired
	private MonitorHistoryMapper monitorHistoryDao;

	@Autowired
	private RestTemplate restClient;

	private Gson gson = new Gson();

	private Type type = new TypeToken<Map<String, Set<String>>>() {
	}.getType();

	private Type type1 = new TypeToken<Map<String, InstanceStatusDto>>() {
	}.getType();

	@RequestMapping(value = "/getTickedList", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> getTickedList(String ip) throws Exception {
		if (StringUtils.isBlank(ip)) {
			return null;
		}

		Map<String, Set<String>> ipWithJdbcRef = getIpWithJdbcRef();

		Set<String> jdbcRefSet = ipWithJdbcRef.get(ip);

		return jdbcRefSet;
	}

	@RequestMapping(value = "/submit", method = RequestMethod.GET)
	@ResponseBody
	public Object submitJdbcRef(String ip, String jdbcRefs) throws Exception {
		Map<String, Set<String>> ipWithJdbcRef = getIpWithJdbcRef();

		if (ipWithJdbcRef == null) {
			ipWithJdbcRef = new HashMap<String, Set<String>>();
		}

		if (StringUtils.isNotBlank(jdbcRefs)) {
			Set<String> newJdbcRefs = new HashSet<String>();

			if (!jdbcRefs.equalsIgnoreCase("null")) {
			String[] jdbcRefSplits = jdbcRefs.trim().split(",");

			//在其他IP中已经监控的jdbcRef不会再一次被监控
			for (String jdbcRef : jdbcRefSplits) {
				boolean isMonitored = false;

				for (Entry<String, Set<String>> entry : ipWithJdbcRef.entrySet()) {
					Set<String> monitoredJdbcRef = entry.getValue();

					if (!entry.getKey().equals(ip) && monitoredJdbcRef.contains(jdbcRef)) {
						isMonitored = true;
						break;
					}
				}
				if(!isMonitored) {
					newJdbcRefs.add(jdbcRef);
				}
			}
			}

			ipWithJdbcRef.put(ip, newJdbcRefs);

			String json = gson.toJson(ipWithJdbcRef);

			lionService.setConfig(lionService.getEnv(), LION_KEY, json);
		}

		return null;
	}

	public boolean isRightJdbcRef(String jdbcRef) {
		if (StringUtils.isBlank(jdbcRef)) {
			return false;
		}

		Set<String> jdbcRefSet = getJdbcRefSet();

		return jdbcRefSet.contains(jdbcRef);
	}

	private Map<String, Set<String>> getIpWithJdbcRef() {
		String config = lionService.getConfigFromZk(LION_KEY);
		if (config != null) {
			return gson.fromJson(config, type);
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/getStatus", method = RequestMethod.GET)
	@ResponseBody
	public Object getStatus(String ip) throws Exception {
		Map<String, InstanceStatusDto> result = new HashMap<String, InstanceStatusDto>();

		try {
			String url = String.format("http://%s:8080/a/status", ip);
			String jsonBody = restClient.exchange(url, HttpMethod.GET, null, String.class).getBody();

			if (jsonBody != null) {
			Map<String, InstanceStatusDto> fromJson = gson.fromJson(jsonBody, type1);

			result.putAll(fromJson);
			}
		} catch (Exception e) {
			Cat.logError(e);
		}

		return result;
	}

	private Set<String> getJdbcRefSet() {
		Set<String> jdbcRefSet = new HashSet<String>();
		try {
			HashMap<String, String> dsKV = lionService.getConfigByProject(lionService.getEnv(), "groupds");

			synchronized (jdbcRefSet) {
			for (Entry<String, String> entry : dsKV.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();

				if (key != null && value != null) {
					int begin = "groupds.".length();
					int end = key.indexOf(".mapping");
					if (end == -1 || begin == -1) {
						continue;
					}

					jdbcRefSet.add(key.substring(begin, end).toLowerCase());
				}
			}
			}
		} catch (IOException e) {
		}
		return jdbcRefSet;
	}

	@RequestMapping(value = "/getJdbcRefList", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> getJdbcRefList() {
		return getJdbcRefSet();
	}

	@RequestMapping(value = "/servers", method = RequestMethod.GET)
	@ResponseBody
	public Object getServers() throws Exception {
		return getIpWithJdbcRef().keySet();
	}

	/*
	 * @RequestMapping(value = "/remove", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public Object removeJdbcRef(String jdbcRefs) throws Exception { if (StringUtils.isNotBlank(jdbcRefs)) {
	 * String[] jdbcRefSplits = jdbcRefs.split(",");
	 * 
	 * for (String jdbcRef : jdbcRefSplits) { deleteJdbcRefToLion(jdbcRef.trim()); } }
	 * 
	 * return null; }
	 */
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	@ResponseBody
	public Object showHistory() throws Exception {
		return monitorHistoryDao.findAllHistory();
	}
}
