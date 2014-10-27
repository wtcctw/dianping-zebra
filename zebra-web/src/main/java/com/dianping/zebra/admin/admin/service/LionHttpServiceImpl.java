package com.dianping.zebra.admin.admin.service;

import com.dianping.cat.Cat;
import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.zebra.group.util.StringUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.unidal.lookup.annotation.Inject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class LionHttpServiceImpl implements LionHttpService {

	private static final Set<String> PRODUCT_ENV;

	private static final Set<String> DEV_ENV;

	private static final Set<String> ALL_ENV;

	private static final String ID = "2";

	private String createKeyUrl = "http://lionapi.dp:8080/config2/create?id=%s&project=%s&key=%s&desc=%s";

	private String getConfigUrl = "http://lionapi.dp:8080/config2/get?env=%s&key=%s&id=%s";

	private String getProjectConfigUrl = "http://lionapi.dp:8080/config2/get?env=%s&project=%s&id=%s";

	private String setConfigUrl = "http://lionapi.dp:8080/config2/set?env=%s&id=%s&key=%s&value=%s";

	@Inject
	private HttpService httpService;

	static {
		PRODUCT_ENV = new LinkedHashSet<String>();
		PRODUCT_ENV.add("prelease");
		PRODUCT_ENV.add("product");
		PRODUCT_ENV.add("product-hm");
		DEV_ENV = new LinkedHashSet<String>();
		DEV_ENV.add("dev");
		DEV_ENV.add("alpha");
		DEV_ENV.add("qa");
		DEV_ENV.add("performance");
		ALL_ENV = new LinkedHashSet<String>();
		ALL_ENV.addAll(DEV_ENV);
		ALL_ENV.addAll(PRODUCT_ENV);
	}

	@Override
	public Set<String> getAllEnv() {
		Set<String> result = new LinkedHashSet<String>();
		result.addAll(ALL_ENV);
		return result;
	}

	@Override public boolean isProduct() {
		return PRODUCT_ENV.contains(EnvZooKeeperConfig.getEnv());
	}

	@Override public boolean isDev() {
		return DEV_ENV.contains(EnvZooKeeperConfig.getEnv());
	}

	@Override
	public boolean createKey(String project, String key) throws IOException {
		String url = String.format(createKeyUrl, ID, project, key, key);
		String result = httpService.sendGet(url);

		try {
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(result).getAsJsonObject();
			if (obj.get("status").getAsString().equals("success")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception t) {
			throw new IOException("cannot create key into lion", t);
		}
	}

	@Override
	public String getConfig(String env, String key) throws IOException {
		String url = String.format(getConfigUrl, env, key, ID);
		String result = httpService.sendGet(url);

		try {
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(result).getAsJsonObject();
			return obj.get("result").getAsString();
		} catch (Exception t) {
			return null;
		}
	}

	@Override
	public HashMap<String, String> getConfigByProject(String env, String project) throws IOException {
		String url = String.format(getProjectConfigUrl, env, project, ID);
		String result = httpService.sendGet(url);

		try {
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(result).getAsJsonObject();
			HashMap<String, String> maps = new HashMap<String, String>();

			if (obj.get("status").getAsString().equals("success")) {
				JsonObject asJsonObject = obj.get("result").getAsJsonObject();

				for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
					maps.put(entry.getKey(), entry.getValue().getAsString());
				}

				return maps;
			}
		} catch (Exception t) {
			throw new IOException("cannot get config from lion", t);
		}

		throw new IOException("cannot get config from lion");
	}

	@Override
	public void removeUnset(String key) {
		try {
			for (String env : ALL_ENV) {
				String value = getConfig(env, key);
				if (StringUtils.isBlank(value)) {
					setConfig(env, key, "");
				}
			}
		} catch (IOException e) {
			Cat.logError(e);
		}
	}

	@Override
	public boolean setConfig(String env, String key, String value) {
		String result = httpService.sendGet(String.format(setConfigUrl, env, ID, key, value));

		if (result != null && result.length() > 0) {
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(result).getAsJsonObject();

			if (obj.get("status").getAsString().equals("success")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
