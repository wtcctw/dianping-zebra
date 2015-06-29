package com.dianping.zebra.admin.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dianping.zebra.admin.service.CmdbService;
import com.dianping.zebra.admin.service.FlowControlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.xml.sax.SAXException;

import com.dianping.cat.Cat;
import com.dianping.zebra.Constants;
import com.dianping.zebra.biz.dao.FlowControlMapper;
import com.dianping.zebra.biz.dto.FlowControlDto;
import com.dianping.zebra.biz.entity.FlowControlEntity;
import com.dianping.zebra.biz.service.LionService;
import com.dianping.zebra.group.config.system.entity.SqlFlowControl;
import com.dianping.zebra.group.config.system.entity.SystemConfig;
import com.dianping.zebra.group.config.system.transform.DefaultSaxParser;
import com.dianping.zebra.util.StringUtils;

/**
 * @author hao.zhu
 */

@Service
public class FlowControlServiceImpl implements FlowControlService {

	private final static String LION_KEY = "zebra.system.flowControl";

	private final static String GLOBAL_APP_NAME = "_global_";

	@Autowired
	private LionService m_lionHttpService;

	@Autowired
	private CmdbService m_cmdbService;

	@Autowired
	private FlowControlMapper flowControlMapper;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	private DefaultTransactionDefinition def = new DefaultTransactionDefinition(
	      TransactionDefinition.PROPAGATION_REQUIRED);

	@Override
	public boolean addItem(String env, String ip, String sqlId, String sql, int percent) {
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			String appName = parseAppName(ip);

			FlowControlEntity entity = new FlowControlEntity();
			entity.setSqlId(sqlId);
			entity.setSql(sql);
			entity.setEnv(env);
			entity.setApp(appName);

			flowControlMapper.insertFlowControl(entity);

			SystemConfig systemConfig = fetchSystemConfig(env);

			SqlFlowControl sqlFlow = new SqlFlowControl();
			sqlFlow.setSqlId(sqlId);
			sqlFlow.setAllowPercent(percent);
			sqlFlow.setApp(appName);

			systemConfig.getSqlFlowControls().put(sqlId, sqlFlow);

			if (m_lionHttpService.setConfig(env, LION_KEY, systemConfig.toString())) {
				transactionManager.commit(status);

				return true;
			} else {
				transactionManager.rollback(status);

				return false;
			}
		} catch (Exception e) {
			Cat.logError(e);

			transactionManager.rollback(status);
			return false;
		}
	}

	@Override
	public boolean deleteItem(String env, String sqlId) {
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			flowControlMapper.deleteFlowControl(sqlId, env);

			SystemConfig systemConfig = fetchSystemConfig(env);
			systemConfig.getSqlFlowControls().remove(sqlId);

			if (m_lionHttpService.setConfig(env, LION_KEY, systemConfig.toString())) {
				transactionManager.commit(status);

				return true;
			} else {
				transactionManager.rollback(status);

				return false;
			}
		} catch (Exception e) {
			Cat.logError(e);

			transactionManager.rollback(status);
			return false;
		}
	}

	private SystemConfig fetchSystemConfig(String env) throws SAXException, IOException {
		return DefaultSaxParser.parse(m_lionHttpService.getConfigByHttp(env, LION_KEY));
	}

	private String findSqlBySqlId(String sqlId, List<FlowControlEntity> findAllActiveFlowControl) {
		for (FlowControlEntity entity : findAllActiveFlowControl) {
			if (entity.getSqlId().equalsIgnoreCase(sqlId)) {
				return entity.getSql();
			}
		}

		return null;
	}

	@Override
	public Map<String, FlowControlDto> getAllActiveFlowControl(String env) {
		Map<String, FlowControlDto> result = new HashMap<String, FlowControlDto>();
		try {
			SystemConfig systemConfig = fetchSystemConfig(env);
			List<FlowControlEntity> findAllActiveFlowControl = flowControlMapper.findAllActiveFlowControlByEnv(env);

			for (Entry<String, SqlFlowControl> entry : systemConfig.getSqlFlowControls().entrySet()) {
				SqlFlowControl sqlFlowControl = entry.getValue();
				String sqlId = sqlFlowControl.getSqlId();
				String sql = findSqlBySqlId(sqlId, findAllActiveFlowControl);

				FlowControlDto sqlFlowControlDto = new FlowControlDto(sqlFlowControl);
				sqlFlowControlDto.setSql(sql);

				result.put(sqlId, sqlFlowControlDto);
			}
		} catch (Exception e) {
			Cat.logError(e);
		}

		return result;
	}

	private String parseAppName(String ip) {
		if (StringUtils.isNotBlank(ip)) {
			String appName = m_cmdbService.getAppName(ip);

			if (Constants.PHOENIX_APP_NO_NAME.equals(appName)) {
				appName = GLOBAL_APP_NAME;
			}

			return appName;
		} else {
			return GLOBAL_APP_NAME;
		}
	}

	@Override
	public boolean modifyItem(String env, String sqlId, int percent) {
		try {
			SystemConfig systemConfig = fetchSystemConfig(env);
			SqlFlowControl flowControl = systemConfig.getSqlFlowControls().get(sqlId);

			if (flowControl != null) {
				flowControl.setAllowPercent(percent);

				return m_lionHttpService.setConfig(env, LION_KEY, systemConfig.toString());
			} else {
				return true;
			}
		} catch (Exception e) {
			Cat.logError(e);
			return false;
		}
	}

	@Override
	public boolean containFlowControl(String env, String sqlId) {
		try {
			SystemConfig systemConfig = fetchSystemConfig(env);
			SqlFlowControl flowControl = systemConfig.getSqlFlowControls().get(sqlId);

			if (flowControl != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			Cat.logError(e);
			return false;
		}
	}

	@Override
   public Map<String, FlowControlDto> getAllDeletedFlowControl(String env) {
		Map<String, FlowControlDto> result = new HashMap<String, FlowControlDto>();
		try {
			List<FlowControlEntity> findAllActiveFlowControl = flowControlMapper.findAllDeletedFlowControlByEnv(env);

			for(FlowControlEntity entity : findAllActiveFlowControl){
				FlowControlDto flowControlDto = new FlowControlDto();
				
				flowControlDto.setId(entity.getId());
				flowControlDto.setSqlId(entity.getSqlId());
				flowControlDto.setSql(entity.getSql());
				flowControlDto.setApp(entity.getApp());
				flowControlDto.setCreateTime(entity.getCreateTime());
				flowControlDto.setUpdateTime(entity.getUpdateTime());
			
				result.put(entity.getSqlId(), flowControlDto);
			}
		} catch (Exception e) {
			Cat.logError(e);
		}

		return result;
	}
}
