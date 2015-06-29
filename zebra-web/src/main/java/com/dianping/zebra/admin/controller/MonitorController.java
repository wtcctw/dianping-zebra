package com.dianping.zebra.admin.controller;

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

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@ResponseBody
	public Object addJdbcRef(String ip, String jdbcRefs) throws Exception {
		if(StringUtils.isNotBlank(jdbcRefs)){
			String[] jdbcRefSplits = jdbcRefs.split(",");
			
			for(String jdbcRef : jdbcRefSplits){
				addJdbcRefToLion(ip, jdbcRef.trim());
			}
		}

		return null;
	}

	private void addJdbcRefToLion(String ip, String jdbcRef) {
		Map<String, Set<String>> ipWithJdbcRef = getIpWithJdbcRef();

		if (ipWithJdbcRef == null) {
			ipWithJdbcRef = new HashMap<String, Set<String>>();
		}

		// 如果已经存在，表明已经被监控，不做任何处理
		for (Entry<String, Set<String>> entry : ipWithJdbcRef.entrySet()) {
			for (String jdbcRef2 : entry.getValue()) {
				if (jdbcRef2.equalsIgnoreCase(jdbcRef)) {
					return;
				}
			}
		}

		Set<String> jdbcRefs = ipWithJdbcRef.get(ip);

		if (jdbcRefs == null) {
			jdbcRefs = new HashSet<String>();
			ipWithJdbcRef.put(ip, jdbcRefs);
		}

		if (!jdbcRefs.contains(jdbcRef)) {
			jdbcRefs.add(jdbcRef);

			String json = gson.toJson(ipWithJdbcRef);

			lionService.setConfig(lionService.getEnv(), LION_KEY, json);
		}
	}

	private void deleteJdbcRefToLion(String jdbcRef) {
		Map<String, Set<String>> ipWithJdbcRef = getIpWithJdbcRef();

		if (ipWithJdbcRef != null) {
			for (Entry<String, Set<String>> entry : ipWithJdbcRef.entrySet()) {
				for (String jdbcRef2 : entry.getValue()) {
					if (jdbcRef2.equalsIgnoreCase(jdbcRef)) {
						entry.getValue().remove(jdbcRef);

						String json = gson.toJson(ipWithJdbcRef);
						lionService.setConfig(lionService.getEnv(), LION_KEY, json);
						return;
					}
				}
			}
		}
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

	@RequestMapping(value = "/servers", method = RequestMethod.GET)
	@ResponseBody
	public Object getServers() throws Exception {
		return getIpWithJdbcRef().keySet();
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	@ResponseBody
	public Object removeJdbcRef(String jdbcRef) throws Exception {
		deleteJdbcRefToLion(jdbcRef);

		return null;
	}

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	@ResponseBody
	public Object showHistory() throws Exception {
		return monitorHistoryDao.findAllHistory();
	}
}
