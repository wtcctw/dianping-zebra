package com.dianping.zebra.admin.admin.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
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

			if (key != null && key.contains(".jdbcUrl") && value != null && value.contains(ip + ":" + port)) {
				int begin = "zebra.v2.ds.".length();
				int end = key.indexOf(".jdbcUrl");

				dataSources.add(key.substring(begin, end));
			}
		}

		return dataSources;
	}

	private void markDBInternal(DalResult result, Set<String> dataSources, boolean isActive) {
		Set<String> unMarkedDataSources = new HashSet<String>();
		Set<String> markedDataSource = new HashSet<String>();
		String value = "false";
		if (isActive) {
			value = "true";
		}

		for (String db : dataSources) {
			boolean isSuccess = m_lionHttpService.setConfig(result.getEnv(), "zebra", "v2.ds." + db + ".active", value);

			if (isSuccess) {
				markedDataSource.add(db);
			} else {
				unMarkedDataSources.add(db);
			}
		}

		if (unMarkedDataSources.size() > 0) {
			result.onFail(dataSources, markedDataSource, String.format(
			      "fail to mark down %s on mysql instance %s:%s because of lion problem.", unMarkedDataSources,
			      result.getIp(), result.getPort(), markedDataSource));
		} else {
			result.onSuccess(dataSources);
		}
	}

	@Override
	public DalResult markDown(String env, String ip, String port) {
		DalResult result = new DalResult(ip, port, env, "markdown");
		HashMap<String, String> keyValues = null;
		try {
			keyValues = m_lionHttpService.getKeyValuesByPrefix(env, PREFIX);
		} catch (IOException e) {
			result.onFail(e.getMessage());

			return result;
		}

		// 找到所有符合ip:port的db
		Set<String> dataSources = findDataSources(ip, port, keyValues);
		Set<String> dataBasesWithoutReadDB = validateDataSources(keyValues, dataSources);

		if (dataBasesWithoutReadDB.size() > 0) {
			result.onFail(String.format("fail to mark down %s:%s because %s will have no read databases.", ip, port,
			      dataBasesWithoutReadDB));

			return result;
		}

		markDBInternal(result, dataSources, false);

		return result;
	}

	@Override
	public DalResult markUp(String env, String ip, String port) {
		DalResult result = new DalResult(ip, port, env, "markup");

		HashMap<String, String> keyValues = null;
		try {
			keyValues = m_lionHttpService.getKeyValuesByPrefix(env, PREFIX);
		} catch (IOException e) {
			result.onFail(e.getMessage());

			return result;
		}

		markDBInternal(result, findDataSources(ip, port, keyValues), true);

		return result;
	}

	private Set<String> validateDataSources(HashMap<String, String> keyValues, Set<String> dataSources) {
		// 找到使用这些db的读库组
		Set<String> dataBases = new HashSet<String>();
		for (String db : dataSources) {
			for (Entry<String, String> entry : keyValues.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();

				// 假设所有的读库组的key都是以zebra.v2.group.开头的
				if (value.contains(db) && key.contains("zebra.v2.group.") && value.contains(":r")) {
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
				String databaseName = database.substring(database.lastIndexOf('.') + 1);
				for (Entry<String, String> entry : keyValues.entrySet()) {
					// 如果有业务用到这个读库组，则记录下来
					if (entry.getValue().contains(databaseName)) {
						dataBasesWithoutReadDB.add(databaseName);
					}
				}
			}
		}

		return dataBasesWithoutReadDB;
	}

	@Override
   public DalResult notify(String env, String ip, String port) {
	   // TODO Auto-generated method stub
	   return null;
   }
}
