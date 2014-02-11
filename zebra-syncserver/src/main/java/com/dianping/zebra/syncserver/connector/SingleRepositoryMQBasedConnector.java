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
package com.dianping.zebra.syncserver.connector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;

import com.dianping.swallow.Destination;
import com.dianping.swallow.MQService;
import com.dianping.swallow.Message;
import com.dianping.swallow.MessageListener;
import com.dianping.swallow.MQService.ConsumerOptionKey;
import com.dianping.swallow.impl.MongoMQService;
import com.dianping.zebra.event.SBRSyncEvent;
import com.dianping.zebra.monitor.Monitor;
import com.dianping.zebra.syncserver.bo.RelayEvent;
import com.dianping.zebra.syncserver.config.Config;
import com.dianping.zebra.syncserver.lifecycle.LifeCycleException;
import com.dianping.zebra.syncserver.relay.RelayEventRepository;

/**
 * <p>
 * 从MQ获取同步通知的单存储Connector
 * </p>
 * <p>
 * 从MQ获取同步通知后写入<tt>RelayEventRepository</tt>的<tt>default</tt>存储引擎中
 * </p>
 * 
 * @author Leo Liang
 * 
 */
public class SingleRepositoryMQBasedConnector implements Connector {

	private static final Logger		log	= Logger.getLogger(SingleRepositoryMQBasedConnector.class);
	private RelayEventRepository	relayEventRepository;
	private MQService				mqService;

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
		log.info("SingleRepositoryMQBasedConnector inited.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dianping.zebra.syncserver.lifecycle.LifeCycle#start()
	 */
	@Override
	public void start() throws LifeCycleException {
		try {
			mqService = new MongoMQService(Config.getInstance().getConnectorParams().getConnectorUrl());
			Destination dest = Destination.queue(Config.getInstance().getConnectorParams().getQueueName());

            Map<ConsumerOptionKey, Object> options = new HashMap<ConsumerOptionKey, Object>();
            options.put(ConsumerOptionKey.IsSlaveConsumer, Config.getInstance().isSlave());
            mqService.createConsumer(dest, options).setMessageListener(new SyncMessageListerner(relayEventRepository));

			log.info("SingleRepositoryMQBasedConnector started.");
		} catch (Exception e) {
			throw new LifeCycleException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dianping.zebra.syncserver.lifecycle.LifeCycle#stop()
	 */
	@Override
	public void stop() throws LifeCycleException {
		try {
			mqService.close();
			relayEventRepository.stop();
			log.info("SingleRepositoryMQBasedConnector stopped.");
		} catch (Exception e) {
			throw new LifeCycleException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dianping.zebra.syncserver.connector.Connector#setRelayEventRepository
	 * ()
	 */
	@Override
	public void setRelayEventRepository(RelayEventRepository relayEventRepository) {
		this.relayEventRepository = relayEventRepository;
	}

	private static final class SyncMessageListerner implements MessageListener {

		private RelayEventRepository	relayEventRepository;

		public SyncMessageListerner(RelayEventRepository relayEventRepository) {
			this.relayEventRepository = relayEventRepository;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.dianping.swallow.MessageListener#onMessage(com.dianping.swallow
		 * .Message)
		 */
		@Override
		public void onMessage(Message msg) {
			byte[] content = (byte[]) msg.getContent();
			Object obj = SerializationUtils.deserialize(content);
			if (obj != null) {
				if (obj instanceof SBRSyncEvent) {
					SBRSyncEvent event = (SBRSyncEvent) obj;
					// 一个消息可能包含多个sql处理，为了防止消息丢失时重做全部sql，所以需要按sql进行分拆写入RelayEventRepository中
					for (Map.Entry<String, List<String>> entry : event.getIdentitySqlsMapping().entrySet()) {
						for (String sql : entry.getValue()) {
							RelayEvent relayEvent = new RelayEvent(entry.getKey(), sql, event.getParams());
							try {
								relayEventRepository.add("default", relayEvent);
							} catch (Exception e) {
								Map<String, Object> params = new HashMap<String, Object>(1);
								params.put("Event", obj);
								Monitor.notifyErrorEvent("SingleRepositoryMQBasedConnector#MessageListerner error.", e,
										params);
							}
						}
					}
				}
			}
		}
	}

}
