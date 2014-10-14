package com.dianping.zebra.admin.admin.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Dozer on 10/14/14.
 */
public interface BlackListService {
	Map<String, List<String>> getAllBlackList(String env) throws IOException;

	void deleteItem(String env, String key, String item) throws IOException;

	void addItem(String env, String ip, String item) throws IOException;
}
