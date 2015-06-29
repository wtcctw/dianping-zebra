package com.dianping.zebra.monitor;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.cat.configuration.NetworkInterfaceManager;
import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.lion.client.ConfigCache;
import com.dianping.lion.client.ConfigChange;
import com.dianping.lion.client.LionException;
import com.dianping.zebra.biz.service.LionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/status")
public class MySQLMonitorController {

	@Autowired
	private LionService lionService;

	@Autowired
	private MySQLMonitorManager monitorManager;

	private static final String LION_KEY = "zebra.monitorservice.jdbcreflist";

	private String localIpAddress;

	private Set<String> currentJdbcRefs = new HashSet<String>();

	private Gson gson = new Gson();

	private Type type = new TypeToken<Map<String, Set<String>>>() {
	}.getType();

	@PostConstruct
	public void init() {
		localIpAddress = NetworkInterfaceManager.INSTANCE.getLocalHostAddress();

		Map<String, Set<String>> ipToJdbcRefs = getIpWithJdbcRef();
		Set<String> jdbcRefs = ipToJdbcRefs.get(localIpAddress);

		if (jdbcRefs != null) {
			for (String jdbcRef : jdbcRefs) {
				monitorManager.addJdbcRef(jdbcRef);
			}

			synchronized (currentJdbcRefs) {
				currentJdbcRefs = jdbcRefs;
			}
		}

		// register
		if (!ipToJdbcRefs.containsKey(localIpAddress)) {
			HashSet<String> empty = new HashSet<String>();
			ipToJdbcRefs.put(localIpAddress, empty);

			String json = gson.toJson(ipToJdbcRefs);

			lionService.setConfig(lionService.getEnv(), LION_KEY, json);
		}

		try {
			ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).addChange(new MyConfigChange());
		} catch (LionException e) {
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object listOwnJdbcRef() throws Exception {
		return monitorManager.listStatus();
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
									monitorManager.addJdbcRef(jdbcRef);
								}
							}

							for (String jdbcRef : currentJdbcRefs) {
								if (!newJdbcRefs.contains(jdbcRef)) {
									monitorManager.removeJdbcRef(jdbcRef);
								}
							}

							currentJdbcRefs = newJdbcRefs;
						} else {
							if (currentJdbcRefs != null) {
								for (String jdbcRef : currentJdbcRefs) {
									monitorManager.removeJdbcRef(jdbcRef);
								}

								currentJdbcRefs.clear();
							}
						}
					}
				} else {
					synchronized (currentJdbcRefs) {
						for (String jdbcRef : currentJdbcRefs) {
							monitorManager.removeJdbcRef(jdbcRef);
						}

						currentJdbcRefs.clear();
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
			return new HashMap<String, Set<String>>();
		}
	}
}
