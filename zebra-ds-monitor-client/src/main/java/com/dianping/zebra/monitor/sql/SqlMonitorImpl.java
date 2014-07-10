/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-10-28
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.monitor.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.StackObjectPool;
import org.springframework.util.StringUtils;

import com.dianping.avatar.tracker.ExecutionContext;
import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.avatar.tracker.TrackerContext;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.hawk.HawkExtend;
import com.dianping.hawk.protocol.Messages.SqlMsg;
import com.dianping.hawk.utils.LocalIP;
import com.dianping.zebra.monitor.util.LRUCache;
import com.dianping.zebra.monitor.util.LogUtil;
import com.dianping.zebra.monitor.util.StringUtil;

/**
 * @author danson.liu
 *
 */
public class SqlMonitorImpl implements SqlMonitor {
	

	private static final Log				logger						= LogFactory.getLog(SqlMonitorImpl.class);
	private static final String 			SQL_MONITOR_ENTRY 			= "sql_monitor_entry";
	private static final String 			SQL_MONITOR_ENTRY_VALUE 	= "1";
	private static final String				SQL_MONITOR_IGNORED			= "sql_monitor_ignored";
	private static final String				SQL_MONITOR_INTERRUPTED		= "sql_monitor_interrupted";
	private static final String 			SQL_MONITOR_MSG_STACK 		= "sql_monitor_msg_stack";
	public  static final String 			SQL_MONITOR_MSG 			= "sql_monitor_msg";
	private static final String 			SQL_STATEMENT_NAME 			= "sql_statement_name";
	public static final int 				PARAM_MAXLEN 				= 25;
	public static final int 				PARAM_MAXCOUNT 				= 15;
	
	//开始计时时间
	private long 							beginTime;
	//是否监控入口(最外层MonitorableDataSource)
	private boolean 						monitorEntry;
	private final Connection 				innerConnection;
	private List<Object> 					params;
	private ConnectionMetaAnalyzer			connectionMetaAnalyzer		= DefaultConnectionMetaAnalyzer.getInstance();
	private ExecutionContext 				executionContext;
	private boolean 						isLoggerDebugEnabled;
	
	private static LRUCache<String, String>	reformedSqlCache 			= new LRUCache<String, String>(2000);		
	private static Map<String, String>		reformedSqlReadonlyCache	= new HashMap<String, String>();	//热点SQL的Snapshot
	private static long						lastCacheSnapshotTime		= -1;
	private static final int 				SNAPSHOT_CACHE_INTERVAL 	= 30 * 60 * 1000;					//every 30 minutes to change cache snapshot
	
	private SqlMsgBuilder					sqlMsgBuilder;
	private static ObjectPool				sqlMsgBuilderStackPool;
	private Stack<SqlMsgBuilder>			sqlMsgBuilderStack;
	private MonitorOptions					monitorOptions				= MonitorOptions.getInstance();
	
	static {
		sqlMsgBuilderStackPool = new StackObjectPool(new SqlMsgBuilderStackPoolFactory(), 250, 200);
	}

	public SqlMonitorImpl(Connection innerConnection) {
		this.innerConnection = innerConnection;
		isLoggerDebugEnabled = logger.isDebugEnabled();
		if (lastCacheSnapshotTime == -1) {
			synchronized (SqlMonitorImpl.class) {
				if (lastCacheSnapshotTime == -1) {
					lastCacheSnapshotTime = System.currentTimeMillis();
				}
			}
		}
	}

	@Override
	public void beforeSqlExecute(String sql) {
		beforeSqlExecute(sql, null);
	}

	@Override
	public void beforeSqlExecute(String sql, List<Object> params) {
		beforeSqlExecute(sql, params, 1);
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public void beforeSqlExecute(String sql, List<Object> params, int count) {
		try {
			executionContext = ExecutionContextHolder.getContext();
			if (!isMonitorInterrupted()) {
				beginTime = System.currentTimeMillis();
				monitorEntry = executionContext.get(SQL_MONITOR_ENTRY) == null;
				if (monitorEntry) {
					executionContext.clear(SQL_MONITOR_MSG);
					executionContext.add(SQL_MONITOR_ENTRY, SQL_MONITOR_ENTRY_VALUE);
					sqlMsgBuilderStack = (Stack<SqlMsgBuilder>) sqlMsgBuilderStackPool.borrowObject();
					if (!sqlMsgBuilderStack.isEmpty()) {
						throw new IllegalStateException("Borrowed SqlMsgBuilder stack is not empty.");
					}
					executionContext.add(SQL_MONITOR_MSG_STACK, sqlMsgBuilderStack);
				} else {
					sqlMsgBuilderStack = executionContext.get(SQL_MONITOR_MSG_STACK);
				}
				if (isLoggerDebugEnabled) {
					logger.debug("1: " + (System.currentTimeMillis() - beginTime));
				}
				collectBasicInfo(sql, count);
				if (isLoggerDebugEnabled) {
					logger.debug("2: " + (System.currentTimeMillis() - beginTime));
				}
				this.params = params;
				if (isLoggerDebugEnabled) {
					logger.debug("3: " + (System.currentTimeMillis() - beginTime));
				}
				sqlMsgBuilderStack.push(sqlMsgBuilder);
				if (isLoggerDebugEnabled) {
					logger.debug("4: " + (System.currentTimeMillis() - beginTime));
				}
			}
		} catch (Throwable throwable) {
			failedToMonitor(throwable);
		}
	}

	private void collectBasicInfo(String sql, int count) {
		sqlMsgBuilder = SqlMsgBuilder.newBuilder();
		if (isLoggerDebugEnabled) {
			logger.debug("1.1: " + (System.currentTimeMillis() - beginTime));
		}
		sqlMsgBuilder.setToken(getToken());
		sqlMsgBuilder.setSql(reformSql(sql));
		if (isLoggerDebugEnabled) {
			logger.debug("1.2: " + (System.currentTimeMillis() - beginTime));
		}
		sqlMsgBuilder.setCount(count);
		sqlMsgBuilder.setWhen(beginTime);
		sqlMsgBuilder.setServer(getServer());
		if (isLoggerDebugEnabled) {
			logger.debug("1.3: " + (System.currentTimeMillis() - beginTime));
		}
	}

	@Override
	public void afterSqlExecute() {
		if (isLoggerDebugEnabled) {
			logger.debug("4.9: " + (System.currentTimeMillis() - beginTime));
		}
		if (!isMonitorInterrupted()) {
			try {
				int timeCost = (int) (System.currentTimeMillis() - beginTime);
				sqlMsgBuilder.setDspath(getDSIdentity()).setTimeCost(timeCost);
				if (isLoggerDebugEnabled) {
					logger.debug("5: " + (System.currentTimeMillis() - beginTime));
				}
				collectSqlParams(params, timeCost);
				//判断subsql情形，以便相应处理(此时该sqlMsgBuilder的子sql已收集)
				judgeActionWithSubSqls();
				collectSqlInfoComplete();
				if (isLoggerDebugEnabled) {
					logger.debug("6: " + (System.currentTimeMillis() - beginTime));
				}
			} catch (Throwable throwable) {
				failedToMonitor(throwable);
			}
		}
	}

	private void collectSqlParams(List<Object> params, int timeCost) {
		if (monitorOptions.isMonitorParamEnabled()) {
			int monitorParamTimeCostThreshold = monitorOptions.getMonitorParamThreshold();
			if (monitorParamTimeCostThreshold == -1 || timeCost >= monitorParamTimeCostThreshold) {
				if (params != null) {
					for (int i = 0; i < params.size(); i++) {
						//为了SqlMsg对象不至于过大，仅记录15个参数值
						if (i < PARAM_MAXCOUNT) {
							sqlMsgBuilder.addParams(processParam(params.get(i)));
						} else {
							break;
						}
					}
				}
			}
		}
		if (isLoggerDebugEnabled) {
			logger.debug("2.1: " + (System.currentTimeMillis() - beginTime));
		}
	}

	private void judgeActionWithSubSqls() {
		List<SqlMsg> subSqlsList = sqlMsgBuilder.getSubSqlsList();
		if (subSqlsList != null && !subSqlsList.isEmpty()) {
			if (subSqlsList.size() == 1) {
				SqlMsg sqlMsg = subSqlsList.get(0);
				if (sqlMsg.getSql().equals(sqlMsgBuilder.getSql())) {
					//只有一条sql，且一样时，params不可能被改变，直接将subSql这一层和谐掉,但子孙subSql需要保留
					sqlMsgBuilder.setDb(sqlMsg.getDb()).setSchema(sqlMsg.getSchema())
						.setDspath(sqlMsgBuilder.getDspath() | sqlMsg.getDspath())
						.clearSubSqls().addAllSubSqls(sqlMsg.getSubSqlsList());
				}
				if (isLoggerDebugEnabled) {
					logger.debug("5.1: " + (System.currentTimeMillis() - beginTime));
				}
			}
		} else {
			if ((sqlMsgBuilder.getDb() == null || "".equals(sqlMsgBuilder.getDb())) && (sqlMsgBuilder.getSchema() == null
					|| "".equals(sqlMsgBuilder.getSchema()))) {
				//the innermost monitorable datasource
				String[] dbAndSchema = connectionMetaAnalyzer.getDbAndSchema(innerConnection);
				try {
                    DatabaseMetaData metaData = innerConnection.getMetaData();
                    Cat.getProducer().logEvent("SQL.Database", metaData.getURL(),Event.SUCCESS,"");
                } catch (Exception e) {
                }
				if (dbAndSchema == null) {
					if (StringUtils.startsWithIgnoreCase(sqlMsgBuilder.getSql(), "SELECT @@IDENTITY")) {
						executionContext.add(SQL_MONITOR_IGNORED, true);
						return;
					}
					throw new RuntimeException("Sql's db destination cannot be retrieved, maybe inner datasource not wrapped.");
				}
				if (isLoggerDebugEnabled) {
					logger.debug("5.2: " + (System.currentTimeMillis() - beginTime));
				}
				sqlMsgBuilder.setDb(dbAndSchema[0]).setSchema(dbAndSchema[1]);
			}
		}
	}

	private void collectSqlInfoComplete() {
		if (monitorEntry) {
			//监控sql执行完成
			completeSqlMsgMonitor();
			if (isLoggerDebugEnabled) {
				logger.debug("5.3: " + (System.currentTimeMillis() - beginTime));
			}
		} else {
			//将中间层收集的信息加入到上一层信息中
			addCurrentSqlMsg2ParentsSubSqls();
			if (isLoggerDebugEnabled) {
				logger.debug("5.4: " + (System.currentTimeMillis() - beginTime));
			}
		}
	}

	private void completeSqlMsgMonitor() {
		enrichOtherInfos();
		SqlMsg sqlMsg = sqlMsgBuilder.build();
		if (sqlMsg != null) {
			if (!isIgnoredToLog()) {
				HawkExtend.logSql(sqlMsg);
			}
			executionContext.add(SQL_MONITOR_MSG, sqlMsg);
		} else {
			LogUtil.logMonitorWarn("Complete with a null sqlmsg, no hawk it.", null);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(sqlMsg);
		}
	}

	private void addCurrentSqlMsg2ParentsSubSqls() {
		sqlMsgBuilderStack.pop();
		if (isLoggerDebugEnabled) {
			logger.debug("5.3.1: " + (System.currentTimeMillis() - beginTime));
		}
		SqlMsgBuilder parentBuilder = sqlMsgBuilderStack.peek();
		if (isLoggerDebugEnabled) {
			logger.debug("5.3.2: " + (System.currentTimeMillis() - beginTime));
		}
		parentBuilder.addSubSqls(sqlMsgBuilder.build());
	}

	private void enrichOtherInfos() {
		sqlMsgBuilder.setCategory((String) executionContext.get(SQL_STATEMENT_NAME));
	}

	private String processParam(Object param) {
		String paramStr = param != null ? param.toString() : "null";
		return paramStr.length() > PARAM_MAXLEN ? paramStr.substring(0, PARAM_MAXLEN) + "(l:" + paramStr.length() + ")" 
				: paramStr;
	}

	private String getServer() {
		//只需在SqlMsg最外层记录serverIP即可，内层值与此相同
		return monitorEntry ? LocalIP.getAddress() : null;
	}
	
	private String reformSql(String sql) {
		String reformedSql = reformedSqlReadonlyCache.get(sql);
		if (reformedSql != null) {
			return reformedSql;
		}
		synchronized (reformedSqlCache) {
			reformedSql = reformedSqlCache.get(sql);
		}
		if (reformedSql != null) {
			return reformedSql;
		}
//		reformedSql = StringUtil.stripComments(sql, "'\"", "'\"", true, false, true, true);
//		reformedSql = StringUtil.formatSql(reformedSql);
		reformedSql = StringUtil.formatSql(sql);
		synchronized (reformedSqlCache) {
			reformedSqlCache.put(sql, reformedSql);
			if (System.currentTimeMillis() - lastCacheSnapshotTime >= SNAPSHOT_CACHE_INTERVAL) {
				Map<String, String>	tempSnapshotCache = new HashMap<String, String>(reformedSqlCache);
				reformedSqlReadonlyCache = tempSnapshotCache;
				tempSnapshotCache = null;
				lastCacheSnapshotTime = System.currentTimeMillis();
			}
		}
		return reformedSql;
	}

	private int getDSIdentity() {
		return connectionMetaAnalyzer.getDSIdentity(innerConnection);
	}

	private String getToken() {
		TrackerContext trackerContext = executionContext.getTrackerContext();
		return monitorEntry && trackerContext != null ? trackerContext.getToken() : null;
	}

	private void failedToMonitor(Throwable throwable) {
		LogUtil.logMonitorError(throwable);
		executionContext.add(SQL_MONITOR_INTERRUPTED, true);
	}
	
	private Boolean isMonitorInterrupted() {
		return executionContext.get(SQL_MONITOR_INTERRUPTED, false);
	}

	private Boolean isIgnoredToLog() {
		return executionContext.get(SQL_MONITOR_IGNORED, false);
	}

	@Override
	public void finalizeSqlExecute() {
		if (monitorEntry) {
			if (sqlMsgBuilderStack != null) {
				try {
					sqlMsgBuilderStackPool.returnObject(sqlMsgBuilderStack);
				} catch (Exception e) {
					//ignore
				}
			}
			executionContext.clear(SQL_MONITOR_ENTRY);
			executionContext.clear(SQL_MONITOR_INTERRUPTED);
			executionContext.clear(SQL_MONITOR_IGNORED);
			executionContext.clear(SQL_MONITOR_MSG_STACK);
			if (isLoggerDebugEnabled) {
				logger.debug("7: " + (System.currentTimeMillis() - beginTime));
			}
		}
	}

}
