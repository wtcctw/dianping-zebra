package com.dianping.zebra.biz.service.impl;
//package com.dianping.zebra.admin.service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.springframework.stereotype.Service;
//import org.unidal.dal.jdbc.DalException;
//import org.unidal.lookup.annotation.Inject;
//
//import com.dianping.cat.Cat;
//import com.dianping.cat.message.Message;
//import com.dianping.cat.message.Transaction;
//import com.dianping.zebra.admin.datasource.TransactionManagerWrapper;
//import com.dianping.zebra.web.dal.lion.Config;
//import com.dianping.zebra.web.dal.lion.ConfigDao;
//import com.dianping.zebra.web.dal.lion.ConfigEntity;
//import com.dianping.zebra.web.dal.lion.ConfigInstance;
//import com.dianping.zebra.web.dal.lion.ConfigInstanceDao;
//import com.dianping.zebra.web.dal.lion.ConfigInstanceEntity;
//
//@Service
//public class MergeConfigServiceImpl implements MergeConfigService {
//
//	@Inject
//	private ConfigInstanceDao configInstanceDao;
//
//	@Inject
//	private ConfigDao configDao;
//
//	@Inject
//	private TransactionManagerWrapper transactionManager;
//
//	@Inject
//	private LionHttpService lionHttpService;
//
//	private final int PROJECT_DS = 100;
//
//	private final int PROJECT_GROUPDS = 10864;
//
//	private void deleteConfigInstance(List<String> from, String to, Env env) throws DalException {
//		List<Integer> ids = new ArrayList<Integer>();
//		for (String ds : from) {
//			if (!ds.equals(to)) {
//				List<Config> configs = configDao.findByProjectAndDs(PROJECT_DS, "%" + ds + "%", ConfigEntity.READSET_FULL);
//
//				for (Config config : configs) {
//					if (isDalKey(ds, config.getKey())) {
//						ids.add(config.getId());
//					}
//				}
//			}
//		}
//
//		int[] ids_ = new int[ids.size()];
//		for (int i = 0; i < ids.size(); i++) {
//			ids_[i] = ids.get(i).intValue();
//		}
//
//		ConfigInstance cins = configInstanceDao.createLocal();
//		cins.setConfigIds(ids_);
//		cins.setEnvId(env.getValue());
//
//		configInstanceDao.deleteByConfigIdsAndEnvId(cins);
//	}
//
//	private boolean isDalKey(String ds, String key) {
//		if (key.equals(String.format("ds.%s.jdbc.url", ds)) || key.equals(String.format("ds.%s.jdbc.username", ds))
//		      || key.equals(String.format("ds.%s.jdbc.password", ds))
//		      || key.equals(String.format("ds.%s.jdbc.active", ds))
//		      || key.equals(String.format("ds.%s.jdbc.driverClass", ds))
//		      || key.equals(String.format("ds.%s.jdbc.properties", ds))) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public boolean merge(List<String> from, String to, Env env) {
//		Transaction tran = Cat.newTransaction("LionMerge", formatName(from, to, env));
//
//		try {
//			transactionManager.startTransaction(ConfigInstanceEntity.UPDATE_BY_PKS, configInstanceDao.createLocal());
//			// merge reference
//			mergeInternal(from, to, "url", env);
//			mergeInternal(from, to, "username", env);
//			mergeInternal(from, to, "password", env);
//
//			// delete unused config instance references
//			deleteConfigInstance(from, to, env);
//
//			// update groupds key
//			List<ConfigEntry> updatedConfigEntries = updateGroupDs(from, to, env);
//
//			transactionManager.commitTransaction(ConfigInstanceEntity.UPDATE_BY_PKS, configInstanceDao.createLocal());
//
//			updateZookeeper(to, env, updatedConfigEntries);
//
//			tran.setStatus(Message.SUCCESS);
//		} catch (DalException e) {
//			try {
//				transactionManager.rollbackTransaction(ConfigInstanceEntity.UPDATE_BY_PKS, configInstanceDao.createLocal());
//			} catch (DalException e1) {
//			}
//
//			tran.setStatus(e);
//			return false;
//		} finally {
//			tran.complete();
//		}
//
//		return true;
//	}
//
//	private String formatName(List<String> from, String to, Env env) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("env=");
//		sb.append(env.getStringEnv());
//		sb.append("&from=");
//		boolean isFirst = true;
//		for (String item : from) {
//			if (isFirst) {
//				sb.append(item);
//				isFirst = false;
//			} else {
//				sb.append(",");
//				sb.append(item);
//			}
//		}
//		sb.append("&to=");
//		sb.append(to);
//		return sb.toString();
//	}
//
//	private String formatName2(List<ConfigEntry> from, String to, Env env) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("env=");
//		sb.append(env.getStringEnv());
//		sb.append("&ds=");
//		sb.append(to);
//		sb.append("&groupds=");
//		boolean isFirst = true;
//		for (ConfigEntry item : from) {
//			if (isFirst) {
//				sb.append(item.getKey());
//				isFirst = false;
//			} else {
//				sb.append(",");
//				sb.append(item.getKey());
//			}
//		}
//
//		return sb.toString();
//	}
//
//	private void updateZookeeper(String to, Env env, List<ConfigEntry> updatedConfigEntries) {
//		Transaction tran = Cat.newTransaction("FlushZookeeper", formatName2(updatedConfigEntries, to, env));
//		try {
//			updateKey(String.format("ds.%s.jdbc.url", to), env);
//			updateKey(String.format("ds.%s.jdbc.username", to), env);
//			updateKey(String.format("ds.%s.jdbc.password", to), env);
//
//			for (ConfigEntry entry : updatedConfigEntries) {
//				lionHttpService.setConfig(env.getStringEnv(), entry.getKey(), entry.getNewValue());
//			}
//		} finally {
//			tran.setStatus(Message.SUCCESS);
//			tran.complete();
//		}
//	}
//
//	private void updateKey(String key, Env env) {
//		try {
//			ConfigInstance ci = configInstanceDao.findByConfigKeyAndEnvId(key, env.getValue(),
//			      ConfigInstanceEntity.READSET_FULL);
//			lionHttpService.setConfig(env.getStringEnv(), key, ci.getValue());
//		} catch (DalException e) {
//		}
//	}
//
//	private void mergeInternal(List<String> from, String to, String key, Env env) throws DalException {
//		List<ConfigInstance> targets = new ArrayList<ConfigInstance>();
//		// search lion key which refer to from
//		for (String fromItem : from) {
//			if (!fromItem.equals(to)) {
//				String dsKey = "%${ds." + fromItem + ".jdbc." + key + "}%";
//				targets = configInstanceDao.findByDs(dsKey, env.getValue(), ConfigInstanceEntity.READSET_FULL);
//			}
//		}
//
//		// change them refer to new to
//		if (targets != null && targets.size() > 0) {
//			int[] configInstanceIds = new int[targets.size()];
//			int i = 0;
//			for (ConfigInstance entry : targets) {
//				configInstanceIds[i++] = entry.getId();
//			}
//
//			ConfigInstance ins = configInstanceDao.createLocal();
//			ins.setConfigIds(configInstanceIds);
//			ins.setValue("${ds." + to + ".jdbc." + key + "}");
//
//			configInstanceDao.updateByPKs(ins, ConfigInstanceEntity.UPDATESET_REFERENCE);
//		}
//	}
//
//	private List<ConfigEntry> updateGroupDs(List<String> from, String to, Env env) throws DalException {
//		Set<ConfigInstance> targets = new HashSet<ConfigInstance>();
//		List<ConfigEntry> results = new ArrayList<ConfigEntry>();
//		List<Config> configs = configDao.findByProjectId(PROJECT_GROUPDS, ConfigEntity.READSET_FULL);
//		Map<Integer, String> keyMap = new HashMap<Integer, String>();
//		int[] ids = new int[configs.size()];
//		for (int i = 0; i < configs.size(); i++) {
//			int id = configs.get(i).getId();
//			ids[i] = id;
//			keyMap.put(id, configs.get(i).getKey());
//		}
//
//		List<ConfigInstance> configIns = configInstanceDao.findByConfigIdAndEnvId(ids, env.getValue(),
//		      ConfigInstanceEntity.READSET_FULL);
//
//		for (ConfigInstance ins : configIns) {
//			for (String ds : from) {
//				String groupds = ins.getValue();
//				if (groupds.contains(ds) && !ds.equals(to)) {
//					ins.setValue(groupds.replace(ds, to));
//					targets.add(ins);
//				}
//			}
//		}
//
//		for (ConfigInstance ins : targets) {
//			configInstanceDao.updateByPK(ins, ConfigInstanceEntity.UPDATESET_REFERENCE);
//			results.add(new ConfigEntry(keyMap.get(ins.getConfigId()), ins.getValue()));
//		}
//
//		return results;
//	}
//
//	public static class ConfigEntry {
//		private String key;
//
//		private String newValue;
//
//		public ConfigEntry(String key, String newValue) {
//			this.key = key;
//			this.newValue = newValue;
//		}
//
//		public String getKey() {
//			return key;
//		}
//
//		public String getNewValue() {
//			return newValue;
//		}
//	}
//}
