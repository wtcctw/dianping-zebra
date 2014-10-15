package com.dianping.zebra.admin.admin.service;

import com.dianping.zebra.group.util.StringUtils;
import com.google.common.collect.Lists;
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

	@Inject
	private LionHttpService m_lionHttpService;

	@Inject
	private CmdbService m_cmdbService;

	public Map<String, List<String>> getAllBlackList(String env) throws IOException {
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		Map<String, String> lionResult = m_lionHttpService.getConfigByProject(env, PROJECT_NAME);
		for (Map.Entry<String, String> config : lionResult.entrySet()) {
			if (StringUtils.isBlank(config.getValue())) {
				continue;
			}
			result.put(config.getKey(), Lists.newArrayList(config.getValue().split(",")));
		}
		return result;
	}

	public void addItem(String env, String ip, String item) throws IOException {
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

		String config = m_lionHttpService.getConfig(env, key);
		if (StringUtils.isBlank(config)) {
			m_lionHttpService.createKey(PROJECT_NAME, key);
			config = "";
		}
		List<String> result = Lists.newArrayList(config.split(","));
		if (!result.contains(item)) {
			result.add(item);
		}

		m_lionHttpService.setConfig(env, key, StringUtils.joinCollectionToString(result, ","));
		if (!key.equals(globalKey)) {
			//修改了App配置后，触发glbal配置的事件。因为App配置不存在，不会被触发
			m_lionHttpService.setConfig(env, globalKey, m_lionHttpService.getConfig(env, globalKey));
		}
	}

	public void deleteItem(String env, String key, String item) throws IOException {
		if (StringUtils.isBlank(item)) {
			return;
		}
		String config = m_lionHttpService.getConfig(env, key);
		if (StringUtils.isBlank(config)) {
			return;
		}

		List<String> list = Lists.newArrayList(config.split(","));
		list.remove(item);

		m_lionHttpService.setConfig(env, key, StringUtils.joinCollectionToString(list, ","));
	}
}
