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
	@Inject
	private LionHttpService m_lionHttpService;

	@Inject
	private CmdbService m_cmdbService;

	public Map<String, List<String>> getAllBlackList(String env) throws IOException {
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		Map<String, String> lionResult = m_lionHttpService.getConfigByProject(env, "zebra-sql-blacklist");
		for (Map.Entry<String, String> config : lionResult.entrySet()) {
			result.put(config.getKey(), Lists.newArrayList(config.getValue().split(",")));
		}
		return result;
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
