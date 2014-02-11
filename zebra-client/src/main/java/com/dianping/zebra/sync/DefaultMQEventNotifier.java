/**
 * Project: ${zebra-client.aid}
 * 
 * File Created at 2011-7-27
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
package com.dianping.zebra.sync;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

import com.dianping.swallow.Destination;
import com.dianping.swallow.MQService;
import com.dianping.swallow.MessageProducer;
import com.dianping.swallow.impl.MongoMQService;
import com.dianping.zebra.event.SyncEvent;
import com.dianping.zebra.event.SyncEventNotifier;
import com.dianping.zebra.jdbc.util.StringUtils;

/**
 * 基于MQ的事件发布器
 * 
 * @author Leo Liang
 * 
 */
public class DefaultMQEventNotifier implements SyncEventNotifier {

	private String			url;
	private String			queueName;
	private MessageProducer	messageProducer;

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param queueName
	 *            the queueName to set
	 */
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public void init() {
		if (StringUtils.isEmpty(url) || StringUtils.isEmpty(queueName)) {
			throw new RuntimeException("url and queueName can not be empty.");
		}

		MQService mqService = new MongoMQService(url);

		Destination dest = Destination.queue(queueName);

		Map<MQService.ProducerOptionKey, Object> options = new HashMap<MQService.ProducerOptionKey, Object>();
		options.put(MQService.ProducerOptionKey.MsgSendRetryCount, 3);

		messageProducer = mqService.createProducer(dest, options);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dianping.zebra.event.EventNotifier#notifyEvent(com.dianping.zebra
	 * .event.SyncEvent)
	 */
	@Override
	public void notifyEvent(SyncEvent event) {
		try {
			messageProducer.send(messageProducer.createBinaryMessage(SerializationUtils.serialize(event)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
