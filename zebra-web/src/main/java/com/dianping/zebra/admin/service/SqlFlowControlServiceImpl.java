package com.dianping.zebra.admin.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.dianping.cat.Cat;
import com.dianping.zebra.Constants;
import com.dianping.zebra.admin.dao.FlowControlMapper;
import com.dianping.zebra.admin.dto.SqlFlowControlDto;
import com.dianping.zebra.admin.entity.FlowControlEntity;
import com.dianping.zebra.group.config.system.entity.SqlFlowControl;
import com.dianping.zebra.group.config.system.entity.SystemConfig;
import com.dianping.zebra.group.config.system.transform.DefaultSaxParser;
import com.dianping.zebra.group.util.StringUtils;

/**
 * Created by Dozer on 10/14/14.
 * 
 * @author hao.zhu
 */

@Service
public class SqlFlowControlServiceImpl implements SqlFlowControlService {

	private final static String LION_KEY = "zebra.system.flowControl";

	@Autowired
	private LionHttpService m_lionHttpService;

	@Autowired
	private CmdbService m_cmdbService;

	@Autowired
	private FlowControlMapper flowControlMapper;

	public Map<String, SqlFlowControlDto> getAllBlackList(String env) {
		Map<String, SqlFlowControlDto> result = new HashMap<String, SqlFlowControlDto>();
		SystemConfig systemConfig = fetchSystemConfig();

		List<FlowControlEntity> findAllActiveFlowControl = flowControlMapper.findAllActiveFlowControlByEnv(env);

		for (Entry<String, SqlFlowControl> entry : systemConfig.getSqlFlowControls().entrySet()) {
			SqlFlowControl sqlFlowControl = entry.getValue();
			String sqlId = sqlFlowControl.getSqlId();
			String sql = findSqlBySqlId(sqlId, findAllActiveFlowControl);

			SqlFlowControlDto sqlFlowControlDto = new SqlFlowControlDto(sqlFlowControl);
			sqlFlowControlDto.setSql(sql);;

			result.put(sqlId, sqlFlowControlDto);
		}

		return result;
	}

	private SystemConfig fetchSystemConfig() {
		String config = m_lionHttpService.getConfigFromZk(LION_KEY);
		SystemConfig systemConfig = new SystemConfig();
		try {
			systemConfig = DefaultSaxParser.parse(config);

			return systemConfig;
		} catch (IOException e) {
		} catch (SAXException e) {
		}

		return new SystemConfig();
	}

	private String findSqlBySqlId(String sqlId, List<FlowControlEntity> findAllActiveFlowControl) {
		for (FlowControlEntity entity : findAllActiveFlowControl) {
			if (entity.getSqlId().equalsIgnoreCase(sqlId)) {
				return entity.getSql();
			}
		}

		return null;
	}

	private String parseAppName(String ip) {
		String appName = "global";

		if (StringUtils.isNotBlank(ip)) {
			appName = m_cmdbService.getAppName(ip);

			if (Constants.PHOENIX_APP_NO_NAME.equals(appName)) {
				appName = "global";
			}
		}

		return appName;
	}

	public boolean addItem(String env, String ip, String sqlId, String sql, int percent) {
		SystemConfig systemConfig = fetchSystemConfig();
		String appName = parseAppName(ip);

		SqlFlowControl sqlFlow = new SqlFlowControl();
		sqlFlow.setSqlId(sqlId);
		sqlFlow.setAllowPercent(percent);
		sqlFlow.setApp(appName);

		systemConfig.getSqlFlowControls().put(sqlId, sqlFlow);
		boolean isOk = m_lionHttpService.setConfig(env, LION_KEY, systemConfig.toString());

		if (isOk) {
			FlowControlEntity entity = new FlowControlEntity();
			entity.setSqlId(sqlId);
			entity.setSql(sql);
			entity.setEnv(env);

			try {
				flowControlMapper.insertFlowControl(entity);

				return true;
			} catch (Throwable t) {
				Cat.logError(t);

				return false;
			}
		} else {
			return false;
		}
	}

	public boolean deleteItem(String env, String sqlId) {
		SystemConfig systemConfig = fetchSystemConfig();

		if (systemConfig.getSqlFlowControls().containsKey(sqlId)) {
			systemConfig.getSqlFlowControls().remove(sqlId);
			boolean isOk = m_lionHttpService.setConfig(env, LION_KEY, systemConfig.toString());

			if (isOk) {
				try {
					flowControlMapper.deleteFlowControl(sqlId, env);

					return true;
				} catch (Throwable t) {
					Cat.logError(t);

					return false;
				}
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
}
