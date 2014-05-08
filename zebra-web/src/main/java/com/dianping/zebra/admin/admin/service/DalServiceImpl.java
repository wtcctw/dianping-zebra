package com.dianping.zebra.admin.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.unidal.lookup.annotation.Inject;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.util.Splitters;

public class DalServiceImpl implements DalService {

	private final String PREFIX = Constants.DEFAULT_ZEBRA_PRFIX + ".";

	@Inject
	private LionHttpService m_lionHttpService;

	private Set<String> findDataSources(String ip, String port, HashMap<String, String> keyValues) {
		Set<String> dataSources = new HashSet<String>();

		for (Entry<String, String> entry : keyValues.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			if (key != null && key.toLowerCase().contains("jdbcUrl") && value != null && value.contains(ip + ":" + port)) {
				int begin = "zebra.v2.ds.".length();
				int end = key.indexOf(".jdbcUrl");

				dataSources.add(key.substring(begin, end));
			}
		}

		return dataSources;
	}

	private List<String> markDBInternal(String env, Set<String> dataSources, String ip, String port, boolean isActive)
	      throws Exception {
		Set<String> unMarkedDataSources = new HashSet<String>();
		List<String> markedDataSource = new ArrayList<String>();
		String value = "false";
		if (isActive) {
			value = "true";
		}

		for (String db : dataSources) {
			boolean isSuccess = m_lionHttpService.setConfig(env, "zebra", "v2.ds." + db + ".active", value);

			if (isSuccess) {
				markedDataSource.add(db);
			} else {
				unMarkedDataSources.add(db);
			}
		}

		if (unMarkedDataSources.size() > 0) {
			throw new DalException(String.format("fail to mark down %s on mysql instance %s:%s because of lion problem.",
			      unMarkedDataSources, ip, port), markedDataSource);
		}

		return markedDataSource;
	}

	@Override
	public List<String> markDown(String env, String ip, String port) throws Exception {
		HashMap<String, String> keyValues = m_lionHttpService.getKeyValuesByPrefix(env, PREFIX);

		// 找到所有符合ip:port的db
		Set<String> dataSources = findDataSources(ip, port, keyValues);
		Set<String> dataBasesWithoutReadDB = validateDataSources(keyValues, dataSources);

		if (dataBasesWithoutReadDB.size() > 0) {
			throw new Exception(String.format("fail to mark down %s:%s because these %s will have no read database.", ip,
			      port, dataBasesWithoutReadDB));
		}

		return markDBInternal(env, dataSources, ip, port, false);
	}

	@Override
	public List<String> markUp(String env, String ip, String port) throws Exception {
		HashMap<String, String> keyValues = m_lionHttpService.getKeyValuesByPrefix(env, PREFIX);

		return markDBInternal(env, findDataSources(ip, port, keyValues), ip, port, true);
	}

	private Set<String> validateDataSources(HashMap<String, String> keyValues, Set<String> dataSources) {
		// 找到使用这些db的读库组
		Set<String> dataBases = new HashSet<String>();
		for (String db : dataSources) {
			for (Entry<String, String> entry : keyValues.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();

				// 假设所有的读库组的key都是以zebra.v2.ds.zebra-r开头的
				if (value.contains(db) && key.contains("zebra.v2.ds.zebra-r")) {
					dataBases.add(key);
				}
			}
		}

		// 过滤所有的读库组
		Set<String> dataBasesWithoutReadDB = new HashSet<String>();
		for (String database : dataBases) {
			String dbs = keyValues.get(database);
			Map<String, String> splits = Splitters.by(',', ':').trim().split(dbs);

			for (String db : dataSources) {
				splits.remove(db);
			}

			// 如果删除读库后，该读库组已经没有任何读库
			if (splits.size() == 0) {
				for (Entry<String, String> entry : keyValues.entrySet()) {
					// 如果有业务用到这个读库组，则记录下来
					if (entry.getValue().contains("#" + database)) {
						dataBasesWithoutReadDB.add(database);
					}
				}
			}
		}

		return dataBasesWithoutReadDB;
	}
}
