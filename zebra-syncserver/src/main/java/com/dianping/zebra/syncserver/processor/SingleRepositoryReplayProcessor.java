/**
 * Project: ${zebra-syncserver.aid}
 * 
 * File Created at 2011-7-26
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
package com.dianping.zebra.syncserver.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.dianping.zebra.jdbc.param.ParamContext;
import com.dianping.zebra.monitor.Monitor;
import com.dianping.zebra.syncserver.bo.RelayEvent;
import com.dianping.zebra.syncserver.lifecycle.LifeCycleException;
import com.dianping.zebra.syncserver.relay.RelayEventRepository;

/**
 * <p>
 * 基于单存储的同步事件回放Processor
 * </p>
 * 
 * <p>
 * 从<tt>RelayEventRepository</tt>的<tt>default</tt>存储引擎中获取回放事件并执行回放
 * </p>
 * 
 * @author Leo Liang
 * 
 */
public class SingleRepositoryReplayProcessor implements ReplayProcessor, ApplicationContextAware {

	private static final Logger		log						= Logger.getLogger(SingleRepositoryReplayProcessor.class);

	private RelayEventRepository	relayEventRepository;
	private Thread					replayTask				= null;
	private ApplicationContext		applicationContext;

	private static final int		MAX_RETRYTIMES			= 3;
	private static final int		RETRY_INTERVAL_FACTOR	= 500;
	private AtomicBoolean			stopped					= new AtomicBoolean(false);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dianping.zebra.syncserver.lifecycle.LifeCycle#init()
	 */
	@Override
	public void init() throws LifeCycleException {
		if (relayEventRepository == null) {
			throw new LifeCycleException("relayEventRepository not set.");
		}
		log.info("SingleRepositoryReplayProcessor inited.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dianping.zebra.syncserver.lifecycle.LifeCycle#start()
	 */
	@Override
	public void start() throws LifeCycleException {
		replayTask = new Thread(new Runnable() {

			@Override
			public void run() {
				RelayEvent relayEvent = null;
				boolean replaySuc = true;
				int retryTimes = 0;

				while (!stopped.get()) {
					if (Thread.interrupted()) {
						Monitor.notifyCommonEvent("Replay task thread is interrupted.", null);
						return;
					}
					try {

						if (retryTimes > MAX_RETRYTIMES) {
							Map<String, Object> logParams = new HashMap<String, Object>(1);
							logParams.put("Event", relayEvent);
							Monitor.notifyErrorEvent("[Skip] Skip one sync event, since reached max retry times.",
									null, logParams);
							relayEvent = null;
						}

						if (relayEvent == null || retryTimes > MAX_RETRYTIMES) {
							retryTimes = 0;
							replaySuc = true;
							relayEvent = relayEventRepository.get("default");
						}

						if (relayEvent == null) {
							continue;
						}

						replay(relayEvent);
						RelayEvent tmpEvent = relayEvent;
						relayEvent = null;
						if (!replaySuc) {
							Map<String, Object> logParams = new HashMap<String, Object>(1);
							logParams.put("Event", tmpEvent);
							Monitor.notifyCommonEvent("[Retry]Replay sync event success.", logParams);
							replaySuc = true;
							retryTimes = 0;
						}
					} catch (Exception e) {
						if (e instanceof InterruptedException) {
							Thread.currentThread().interrupt();
							continue;
						}

						Map<String, Object> logParams = new HashMap<String, Object>(2);
						logParams.put("RetryTimes", retryTimes);
						logParams.put("Event", relayEvent);
						Monitor.notifyErrorEvent("Replay sync event failed.", e, logParams);
						try {
							Thread.sleep(RETRY_INTERVAL_FACTOR * (retryTimes + 1));
						} catch (InterruptedException e1) {
							Monitor.notifyErrorEvent("Interrupted while retrying sleep.", null, null);
							Thread.currentThread().interrupt();
						}
						replaySuc = false;
						retryTimes++;
					}

				}
			}

		}, "SingleRepositoryReplayProcessor-Thread");
		replayTask.start();
		log.info("SingleRepositoryReplayProcessor started.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dianping.zebra.syncserver.lifecycle.LifeCycle#stop()
	 */
	@Override
	public void stop() throws LifeCycleException {
		try {
			stopped.set(true);
			if (replayTask != null) {
				replayTask.interrupt();
			}
			log.info("SingleRepositoryReplayProcessor stopped.");
		} catch (Exception e) {
			throw new LifeCycleException(e);
		}
	}

	private void setParams(PreparedStatement stmt, List<ParamContext> params) throws SQLException {
		for (ParamContext paramContext : params) {
			paramContext.setParam(stmt);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dianping.zebra.syncserver.processor.ReplayProcessor#replay(com.dianping
	 * .zebra.syncserver.bo.RelayEvent)
	 */
	@Override
	public void replay(RelayEvent relayEvent) throws Exception {
		DataSource ds = (DataSource) applicationContext.getBean(relayEvent.getIdentity());
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {
			conn = ds.getConnection();
			List<ParamContext> params = relayEvent.getParams();
			if (params == null) {
				stmt = conn.createStatement();
				stmt.execute(relayEvent.getSql());
			} else {
				pstmt = conn.prepareStatement(relayEvent.getSql());
				setParams(pstmt, relayEvent.getParams());
				pstmt.execute();
			}
			log.info("Replay one event successfully!!");
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dianping.zebra.syncserver.processor.ReplayProcessor#
	 * setRelayEventRepository
	 * (com.dianping.zebra.syncserver.relay.RelayEventRepository)
	 */
	@Override
	public void setRelayEventRepository(RelayEventRepository relayEventRepository) {
		this.relayEventRepository = relayEventRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
