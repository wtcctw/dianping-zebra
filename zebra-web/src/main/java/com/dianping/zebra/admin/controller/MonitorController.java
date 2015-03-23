package com.dianping.zebra.admin.controller;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.dianping.cat.Cat;
import com.dianping.cat.configuration.NetworkInterfaceManager;
import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.lion.client.ConfigCache;
import com.dianping.lion.client.ConfigChange;
import com.dianping.lion.client.LionException;
import com.dianping.zebra.admin.dao.MonitorHistoryMapper;
import com.dianping.zebra.admin.monitor.InstanceStatus;
import com.dianping.zebra.admin.monitor.MySQLMonitorManager;
import com.dianping.zebra.admin.service.LionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/monitor")
public class MonitorController {

	private static final String LION_KEY = "zebra.server.monitor.config";

	@Autowired
	private MySQLMonitorManager monitorServer;

	@Autowired
	private LionService lionService;

	@Autowired
	private MonitorHistoryMapper monitorHistoryDao;

	private String localIpAddress;

	private Gson gson = new Gson();

	private Type type = new TypeToken<Map<String, Set<String>>>() {
	}.getType();

	private Type type1 = new TypeToken<Map<String, InstanceStatus>>() {
	}.getType();

	private Set<String> currentJdbcRefs = new HashSet<String>();

	private volatile boolean init = false;

	@PostConstruct
	public void init() {
		if (!init) {
			localIpAddress = NetworkInterfaceManager.INSTANCE.getLocalHostAddress();

			Map<String, Set<String>> ipToJdbcRefs = getIpWithJdbcRef();

			if (ipToJdbcRefs != null) {
				Set<String> jdbcRefs = ipToJdbcRefs.get(localIpAddress);

				if (jdbcRefs != null) {
					for (String jdbcRef : jdbcRefs) {
						monitorServer.addJdbcRef(jdbcRef);
					}

					synchronized (currentJdbcRefs) {
						currentJdbcRefs = jdbcRefs;
					}
				}
			}

			try {
				ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).addChange(new MyConfigChange());
			} catch (LionException e) {
			}

			init = true;
		}
	}

	private class MyConfigChange implements ConfigChange {

		@Override
		public void onChange(String key, String value) {
			if (key.equalsIgnoreCase(LION_KEY)) {
				Map<String, Set<String>> ipWithJdbcRef = getIpWithJdbcRef();

				if (ipWithJdbcRef != null) {
					Set<String> newJdbcRefs = ipWithJdbcRef.get(localIpAddress);

					synchronized (currentJdbcRefs) {
						if (newJdbcRefs != null) {
							for (String jdbcRef : newJdbcRefs) {
								if (!currentJdbcRefs.contains(jdbcRef)) {
									monitorServer.addJdbcRef(jdbcRef);
								}
							}

							for (String jdbcRef : currentJdbcRefs) {
								if (!newJdbcRefs.contains(jdbcRef)) {
									monitorServer.removeJdbcRef(jdbcRef);
								}
							}
							
							currentJdbcRefs = newJdbcRefs;
						}else{
							if(currentJdbcRefs != null){
								for(String jdbcRef : currentJdbcRefs){
									monitorServer.removeJdbcRef(jdbcRef);
								}
								
								currentJdbcRefs.clear();
							}
						}
						
					}
				}
			}
		}

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@ResponseBody
	public Object addJdbcRef(String jdbcRef) throws Exception {
		addJdbcRefToLion(jdbcRef);

		return null;
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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object listJdbcRef() throws Exception {
		Map<String, InstanceStatus> result = new HashMap<String, InstanceStatus>();

		result.putAll(monitorServer.listStatus());

		Map<String, Set<String>> ipWithJdbcRef = getIpWithJdbcRef();

		if (ipWithJdbcRef != null) {
			for (String ip : ipWithJdbcRef.keySet()) {
				if (!ip.equalsIgnoreCase(localIpAddress)) {
					try {
						String url = String.format("http://%s:8080/a/monitor/listown", ip);

						RestTemplate client = new RestTemplate();
						String jsonBody = client.exchange(url, HttpMethod.GET, null, String.class).getBody();

						if (jsonBody != null) {
							Map<String, InstanceStatus> fromJson = gson.fromJson(jsonBody, type1);

							result.putAll(fromJson);
						}
					} catch (Exception e) {
						Cat.logError(e);
					}
				}
			}
		}

		return result;
	}

	@RequestMapping(value = "/listown", method = RequestMethod.GET)
	@ResponseBody
	public Object listOwnJdbcRef() throws Exception {
		return monitorServer.listStatus();
	}

	private Map<String, Set<String>> getIpWithJdbcRef() {
		String config = lionService.getConfigFromZk(LION_KEY);
		if (config != null) {
			return gson.fromJson(config, type);
		} else {
			return null;
		}
	}

	private void addJdbcRefToLion(String jdbcRef) {
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

		Set<String> jdbcRefs = ipWithJdbcRef.get(localIpAddress);

		if (jdbcRefs == null) {
			jdbcRefs = new HashSet<String>();
			ipWithJdbcRef.put(localIpAddress, jdbcRefs);
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
			Set<String> jdbcRefs = ipWithJdbcRef.get(localIpAddress);

			if (jdbcRefs != null) {
				if (jdbcRefs.contains(jdbcRef)) {
					jdbcRefs.remove(jdbcRef);

					String json = gson.toJson(ipWithJdbcRef);

					lionService.setConfig(lionService.getEnv(), LION_KEY, json);
				}
			}
		}
	}
}
