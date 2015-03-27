package com.dianping.zebra.admin.monitor;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianping.zebra.admin.monitor.handler.HaHandler;
import com.dianping.zebra.admin.monitor.handler.HaHandler.Operator;
import com.dianping.zebra.admin.service.LionService;

@Service
public class MHAServiceImpl implements MHAService {

	private final String PORJECT = "ds";

	private final String MHA_LION_KEY = "zebra.server.monitor.mha.markdown";

	@Autowired
	private LionService m_lionHttpService;

	@Autowired
	private HaHandler haHandler;

	private Map<String, String> mhaMarkedDownDs = new ConcurrentHashMap<String, String>();

	private Set<String> findDsIds1(String ip, String port, HashMap<String, String> keyValues) {
		String content = ip + ":" + port;

		Set<String> dsIds = new HashSet<String>();

		for (Entry<String, String> entry : keyValues.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			if (key != null && key.contains(".jdbc.url") && value != null && value.contains(content.trim())) {
				int begin = "ds.".length();
				int end = key.indexOf(".jdbc.url");

				dsIds.add(key.substring(begin, end));
			}
		}

		return dsIds;
	}

	@Override
	public Set<String> findDsIds(String ip, String port) {
		try {
			HashMap<String, String> dsKV = m_lionHttpService.getConfigByProject(m_lionHttpService.getEnv(), PORJECT);
			Set<String> dsIds = findDsIds1(ip, port, dsKV);

			HashMap<String, String> groupdsKV = m_lionHttpService
			      .getConfigByProject(m_lionHttpService.getEnv(), "groupds");

			Iterator<String> iterator = dsIds.iterator();
			while (iterator.hasNext()) {
				String dsId = iterator.next();

				// 第一步过滤写账号
				if (dsId.contains("write")) {
					iterator.remove();

					continue;
				}

				// 第二步过滤没有被groupds使用的dsId
				boolean hasRef = false;
				for (String value : groupdsKV.values()) {
					if (value.contains(dsId)) {
						hasRef = true;
						break;
					}
				}

				if (!hasRef) {
					iterator.remove();
				}
			}

			return dsIds;
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public boolean isMarkdownByMHA(String dsId) {
		return mhaMarkedDownDs.containsKey(dsId);
	}

	@PostConstruct
	public void init() {
		String config = m_lionHttpService.getConfigFromZk(MHA_LION_KEY);

		if (config != null) {
			String[] dsIds = config.split(",");
			Map<String, String> mhaMarkedDownDs = new ConcurrentHashMap<String, String>();

			for (String dsId : dsIds) {
				if (dsId != null && dsId.length() > 0) {
					mhaMarkedDownDs.put(dsId, dsId);
				}
			}

			this.mhaMarkedDownDs = mhaMarkedDownDs;
		}
	}

	@Override
	public void markDownDsIds(Set<String> dsIds) {
		for (String dsId : dsIds) {
			mhaMarkedDownDs.put(dsId, dsId);
		}

		flushToLion();

		for (String dsId : dsIds) {
			haHandler.markdown(dsId, Operator.MHA);
		}
	}

	private void flushToLion() {
		StringBuilder value = new StringBuilder(1024);
		boolean isFirst = true;
		for (String dsId : mhaMarkedDownDs.keySet()) {
			if (isFirst) {
				value.append(dsId);
				isFirst = false;
			} else {
				value.append(",");
				value.append(dsId);
			}
		}

		m_lionHttpService.setConfig(m_lionHttpService.getEnv(), MHA_LION_KEY, value.toString());
	}

	@Override
	public void markUpDsId(String dsId) {
		String dsIdValue = mhaMarkedDownDs.remove(dsId);

		if (dsIdValue != null) {
			flushToLion();

			haHandler.markup(dsId, Operator.PEOPLE);
		}
	}
}
