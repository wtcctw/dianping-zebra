package com.dianping.zebra.admin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.zebra.admin.dto.ConfigDto;
import com.dianping.zebra.admin.dto.ConnectionStatusDto;
import com.dianping.zebra.admin.service.ConnectionService;
import com.dianping.zebra.admin.service.DalConfigService;
import com.dianping.zebra.admin.service.LionService;
import com.dianping.zebra.group.config.DataSourceConfigManager;
import com.dianping.zebra.group.config.DataSourceConfigManagerFactory;
import com.dianping.zebra.group.config.DefaultDataSourceConfigManager.ReadOrWriteRole;
import com.google.common.base.Strings;

/**
 * Dozer @ 2015-02 mail@dozer.cc http://www.dozer.cc
 */

@Controller
@RequestMapping(value = "/config")
public class ConfigController extends BasicController {
	private final static String currentEnv = EnvZooKeeperConfig.getEnv();

	@Autowired
	private LionService lionService;

	@Autowired
	private DalConfigService dalConfigService;

	@Autowired
	private ConnectionService connectionService;

	@RequestMapping(value = "/autoreplace", method = RequestMethod.POST)
	@ResponseBody
	public Object autoReplace(String jdbcRef, String env, boolean isNew) throws Exception {
		if (isNew) {
			dalConfigService.addItemIntoWhiteList(env, jdbcRef);
		} else {
			dalConfigService.deleteItemFromWhiteList(env, jdbcRef);
		}

		return null;
	}

	private String convertEnv(String env) {
		if (Strings.isNullOrEmpty(env)) {
			env = EnvZooKeeperConfig.getEnv();
		}
		return env;
	}

	private String convertKey(String key) {
		if (!Strings.isNullOrEmpty(key) && key.equals("dpreview")) {
			key = "DPReview";
		}
		return key;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(String project, String key) throws Exception {
		if (project.equals("groupds")) {
			if (Strings.isNullOrEmpty(key)) {
				throw new NullPointerException("key");
			}
			String dskey = String.format("groupds.%s.mapping", key.toLowerCase());

			lionService.createKey("groupds", dskey);
			lionService.removeUnset(dskey);
		} else if (project.equals("ds")) {
		}
		return null;
	}

	@RequestMapping(value = "/ds", method = RequestMethod.GET)
	@ResponseBody
	public Object ds(String env, String key, String otherkey) throws Exception {
		return dalConfigService.getDsConfig(env, key, otherkey);
	}

	@RequestMapping(value = "/env", method = RequestMethod.GET)
	@ResponseBody
	public Object env() throws Exception {
		Object responseObject;

		if (lionService.isDev()) {
			responseObject = lionService.getDevEnv();
		} else if ("prelease".equals(currentEnv)) {
			responseObject = new String[] { "prelease" };
		} else {
			responseObject = lionService.getAllEnv();
		}

		return responseObject;
	}

	@RequestMapping(value = "/getConfig", method = RequestMethod.GET)
	@ResponseBody
	public ConnectionStatusDto getConfig(String env, String key) throws Exception {
		env = convertEnv(env);
		key = convertKey(key.toLowerCase());

		if (env.equalsIgnoreCase(currentEnv)) {
			DataSourceConfigManager configManager = DataSourceConfigManagerFactory.getConfigManager("remote", key);

			ConnectionStatusDto dto = new ConnectionStatusDto();
			dto.setConfig(configManager.getGroupDataSourceConfig().toString());
			dto.setConnected(true);

			configManager.close();

			return dto;
		} else {
			String host = getHost(env);
			if (Strings.isNullOrEmpty(host)) {
				throw new NullPointerException("host");
			}
			String url = getUrl(env, key, host);

			RestTemplate client = new RestTemplate();
			return client.exchange(url, HttpMethod.GET, null, ConnectionStatusDto.class).getBody();
		}
	}

	private String getHost(String env) throws Exception {
		String host = "";
		if ("alpha".equals(env)) {
			host = "http://192.168.214.228:8080";
		} else if ("qa".equals(env)) {
			host = "http://192.168.217.69:8080";
		} else if ("prelease".equals(env)) {
			host = "http://10.2.8.65:8080";
		} else if ("product".equals(env)) {
			host = "http://zebra.dp";
		} else if("performance".equals(env)){
			host = "http://192.168.104.88:8080";
		} else {
			throw new Exception("Error: unrecognized lion env!");
		}
		
		return host;
	}

	private String getUrl(String env, String key, String host) {
		return host + "/a/config/test?key=" + key + "&env=" + env;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public Object index(String env) throws Exception {
		HashMap<String, ConfigDto> configs = new HashMap<String, ConfigDto>();

		HashMap<String, String> jdbcRefs = lionService.getConfigByProject(env, "groupds");
		Set<String> whiteList = dalConfigService.getWhiteList(env);

		for (Entry<String, String> entry : jdbcRefs.entrySet()) {
			ConfigDto dto = new ConfigDto();
			String jdbcRefKey = entry.getKey();

			if (jdbcRefKey != null && jdbcRefKey.endsWith("mapping")) {
				int first = jdbcRefKey.indexOf(".");
				int last = jdbcRefKey.lastIndexOf(".");
				String jdbcRef = jdbcRefKey.substring(first + 1, last);

				String value = entry.getValue();

				dto.setJdbcRef(jdbcRef);
				dto.setValue(value);
				dto.setAutoReplaced(whiteList.contains(jdbcRef));
				dto.setShouldAlert(shouldAlert(value));

				configs.put(jdbcRef, dto);
			}
		}

		return configs;
	}

	private boolean shouldAlert(String value) {
		Map<String, ReadOrWriteRole> parseConfig = ReadOrWriteRole.parseConfig(value);

		int readCount = 0;

		for (ReadOrWriteRole role : parseConfig.values()) {
			if (role.isRead()) {
				readCount++;
			}

			if (role.isRead() && role.isWrite()) {
				return true;
			}
		}

		if (readCount < 2) {
			return true;
		}

		return false;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public ConnectionStatusDto test(String env, String key) throws Exception {
		env = convertEnv(env);
		key = convertKey(key.toLowerCase());

		if (env.equalsIgnoreCase(currentEnv)) {
			return connectionService.getConnectionResult(lionService.isProduct(), key, null);
		} else {
			String host = getHost(env);
			if (Strings.isNullOrEmpty(host)) {
				throw new NullPointerException("host");
			}
			String url = getUrl(env, key, host);

			RestTemplate client = new RestTemplate();
			return client.exchange(url, HttpMethod.GET, null, ConnectionStatusDto.class).getBody();
		}
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public Object testWithConfig(String env, String key, @RequestBody DalConfigService.GroupConfigModel dsConfig)
	      throws Exception {
		env = convertEnv(env);
		key = convertKey(key.toLowerCase());

		if (env.equalsIgnoreCase(currentEnv)) {
			return connectionService.getConnectionResult(lionService.isProduct(), key, dsConfig);
		} else {
			String host = getHost(env);
			if (Strings.isNullOrEmpty(host)) {
				throw new NullPointerException("host");
			}
			String url = getUrl(env, key, host);

			RestTemplate client = new RestTemplate();
			return client.exchange(url, HttpMethod.POST, new HttpEntity<DalConfigService.GroupConfigModel>(dsConfig),
			      ConnectionStatusDto.class).getBody();
		}
	}

	@RequestMapping(value = "/updateds", method = RequestMethod.POST)
	@ResponseBody
	public Object updateds(boolean force, @RequestBody DalConfigService.GroupConfigModel dsConfig) throws Exception {
		dalConfigService.updateDsConfig(dsConfig, force);
		return null;
	}
}
