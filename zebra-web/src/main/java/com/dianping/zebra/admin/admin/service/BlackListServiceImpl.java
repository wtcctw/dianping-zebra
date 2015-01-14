package com.dianping.zebra.admin.admin.service;

import com.dianping.zebra.group.util.StringUtils;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.unidal.lookup.annotation.Inject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dozer on 10/14/14.
 */
public class BlackListServiceImpl implements BlackListService {

	private final static String PROJECT_NAME = "zebra-sql-blacklist";

	private final static Gson gson = new Gson();

	private final static String EXTEND_KEY = ".extend";

	@Inject
	private LionHttpService m_lionHttpService;

	@Inject
	private CmdbService m_cmdbService;

	public Map<String, Map<String, BlackExtend>> getAllBlackList(String env) throws IOException {
		Map<String, Map<String, BlackExtend>> result = new HashMap<String, Map<String, BlackExtend>>();
		Map<String, String> lionResult = m_lionHttpService.getConfigByProject(env, PROJECT_NAME);
		for (Map.Entry<String, String> config : lionResult.entrySet()) {
			if (!config.getKey().endsWith(EXTEND_KEY) || StringUtils.isBlank(config.getValue())) {
				continue;
			}
			result.put(config.getKey(),
				gson.<Map<String, BlackExtend>>fromJson(config.getValue(), new TypeToken<Map<String, BlackExtend>>() {
				}.getType()));
		}
		return result;
	}

	public void addItem(String env, String ip, String item, String comment) throws IOException {
		String key;
		String globalKey = String.format("%s.global.id", PROJECT_NAME);

		if (StringUtils.isBlank(ip)) {
			key = globalKey;
		} else {
			String appName = m_cmdbService.getAppName(ip);
			if (StringUtils.isBlank(appName) || StringUtils.isBlank(item)) {
				return;
			}
			key = String.format("%s.app.%s.id", PROJECT_NAME, appName);
		}
		String extendKey = key + EXTEND_KEY;

		List<String> result = getIds(env, key);
		Map<String, BlackExtend> extendJson = getStringBlackExtendMap(env, item, extendKey);

		if (!result.contains(item)) {
			result.add(item);
		}
		extendJson.get(item).setComment(comment);

		m_lionHttpService.setConfig(env, key, StringUtils.joinCollectionToString(result, ","));
		m_lionHttpService.setConfig(env, extendKey, gson.toJson(extendJson));

		if (!key.equals(globalKey)) {
			//修改了App配置后，触发glbal配置的事件。因为App配置不存在，不会被触发
			m_lionHttpService.setConfig(env, globalKey, m_lionHttpService.getConfig(env, globalKey));
		}
	}

	private List<String> getIds(String env, String key) throws IOException {
		String config = m_lionHttpService.getConfig(env, key);
		if (StringUtils.isBlank(config)) {
			m_lionHttpService.createKey(PROJECT_NAME, key);
			config = "";
		}
		List<String> result = Lists.newArrayList(config.split(","));
		return result;
	}

	private Map<String, BlackExtend> getStringBlackExtendMap(String env, String item, String extendKey)
		throws IOException {
		String extend = m_lionHttpService.getConfig(env, extendKey);
		if (StringUtils.isBlank(extend) || StringUtils.isBlank(extend.trim())) {
			m_lionHttpService.createKey(PROJECT_NAME, extendKey);
			extend = "{}";
		}
		Map<String, BlackExtend> extendJson = gson.fromJson(extend, new TypeToken<Map<String, BlackExtend>>() {
		}.getType());
		if (extendJson.get(item) == null) {
			extendJson.put(item, new BlackExtend());
		}
		return extendJson;
	}

	public void deleteItem(String env, String extendKey, String item) throws IOException {
		String key = extendKey.replace(EXTEND_KEY, "");
		if (StringUtils.isBlank(item)) {
			return;
		}

		List<String> list = getIds(env, key);
		list.remove(item);

		Map<String, BlackExtend> extend = getStringBlackExtendMap(env, item, extendKey);
		extend.remove(item);

		m_lionHttpService.setConfig(env, key, StringUtils.joinCollectionToString(list, ","));
		m_lionHttpService.setConfig(env, extendKey, gson.toJson(extend));

	}
}
