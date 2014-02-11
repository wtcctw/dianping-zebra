/**
 * Project: ${zebra-syncserver.aid}
 * 
 * File Created at 2011-7-25
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
package com.dianping.zebra.syncserver.bootstrap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dianping.zebra.syncserver.config.Config;
import com.dianping.zebra.syncserver.connector.Connector;
import com.dianping.zebra.syncserver.lifecycle.LifeCycle;
import com.dianping.zebra.syncserver.lifecycle.LifeCycleException;
import com.dianping.zebra.syncserver.processor.ReplayProcessor;

/**
 * <p>
 * 同步Server的启动类
 * </p>
 * 
 * <p>
 * 同步Server流程：
 * <ol>
 * <li>一个Connector线程负责接受同步事件通知，并纪录Relay Log</li>
 * <li>一个ReplayProcessor线程负责从Relay Log存储中逐行回放同步事件</li>
 * </ol>
 * </p>
 * <p>
 * 另外，启动类会额外启动一个Socket监听服务，负责接受控制请求（如关闭Server命令就是通过这个监听服务实现的）。
 * </p>
 * 
 * @author Leo Liang
 * 
 */
public class BootStrap {
	private static final Logger		log						= Logger.getLogger(BootStrap.class);

	private static final String		SPRING_CONFIG_LOCATION	= "classpath*:ctx-*.xml";

	private static final String		CONNECTOR_CONTEXT_ID	= "connector";
	private static final String		PROCESSOR_CONTEXT_ID	= "processor";

	private static final String		MONITOR_PORT_KEY		= "monitorPort";
	private static final int		DEFAULT_MONITOR_PORT	= 7874;

	private static AtomicBoolean	stopped					= new AtomicBoolean(false);
	private static AtomicBoolean	stopJobExecuted			= new AtomicBoolean(false);

	public static void main(String[] args) throws Exception {
		try {
            if (args != null && args.length == 1 && StringUtils.equals("slave", args[0])) {
                log.info("Start as slave node.");
                Config.getInstance().setSlave(true);
            }

			ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_CONFIG_LOCATION);
			final Connector connector = (Connector) context.getBean(CONNECTOR_CONTEXT_ID);
			final ReplayProcessor replayProcessor = (ReplayProcessor) context.getBean(PROCESSOR_CONTEXT_ID);

			((LifeCycle) connector).init();
			((LifeCycle) replayProcessor).init();

			((LifeCycle) replayProcessor).start();
			((LifeCycle) connector).start();

			startMonitorTaskThread();

			startShutdownCheckTaskThread(connector, replayProcessor);

			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				
				@Override
				public void run() {
					shutdown(connector, replayProcessor);
				}
			}));

			log.info("BootStrap started.");

		} catch (Exception e) {
			log.error("Uncatched exception occurred.", e);
		}
	}

	private static void startMonitorTaskThread() {
		MonitorTask monitorTask = new MonitorTask();
		monitorTask.init();
		Thread monitorThread = new Thread(monitorTask, "monitor-thread");
		monitorThread.setDaemon(true);
		monitorThread.start();
	}

	private static void startShutdownCheckTaskThread(Connector connector, ReplayProcessor replayProcessor) {
		ShutdownCheckTask shutdownCheckTask = new ShutdownCheckTask(connector, replayProcessor);
		Thread shutdownCheckThread = new Thread(shutdownCheckTask, "shutdownCheck-thread");
		shutdownCheckThread.setDaemon(true);
		shutdownCheckThread.start();
	}
	
	private static void shutdown(Connector connector, ReplayProcessor replayProcessor){
		if (stopJobExecuted.compareAndSet(false, true)) {
			try {
				((LifeCycle) connector).stop();
			} catch (LifeCycleException e) {
				log.error("Stop connector failed.", e);
			}
			try {
				((LifeCycle) replayProcessor).stop();
			} catch (LifeCycleException e) {
				log.error("Stop replayProcessor failed.", e);
			}

		}
	}

	private static class ShutdownCheckTask implements Runnable {
		private Connector		connector;
		private ReplayProcessor	replayProcessor;

		public ShutdownCheckTask(Connector connector, ReplayProcessor replayProcessor) {
			this.connector = connector;
			this.replayProcessor = replayProcessor;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			log.info("ShutdownCheckTask started.");

			try {
				synchronized (stopped) {
					while (!stopped.get()) {
						stopped.wait();
					}
				}
			} catch (InterruptedException e) {
				log.info("ShutdwonCheck Task interrupted.");
				Thread.currentThread().interrupt();
			}

			shutdown(connector, replayProcessor);
		}
	}

	private static class MonitorTask implements Runnable {
		private int		port				= DEFAULT_MONITOR_PORT;
		private String	COMMAND_SHUTDOWN	= "shutdown";

		public void init() {
			String portStr = System.getProperty(MONITOR_PORT_KEY);
			if (portStr != null && StringUtils.isNumeric(portStr)) {
				port = Integer.parseInt(portStr);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {

			try {
				ServerSocket ss = new ServerSocket(port);

				log.info("MonitorTask started at port: " + port);

				Socket socket = null;

				while (true) {
					try {
						socket = ss.accept();
						log.info("Accepted one connection : " + socket.getRemoteSocketAddress());
						BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String command = br.readLine();
						log.info("Command : " + command);
						if (COMMAND_SHUTDOWN.equals(command)) {
							synchronized (stopped) {
								stopped.set(true);
								stopped.notifyAll();
							}
							log.info("Shutdown command received.");
							return;
						}
					} catch (Exception e) {
						// ignore
					} finally {
						if (socket != null) {
							socket.close();
						}
					}
				}

			} catch (Exception e) {
				log.error("MonitorTask start failed.", e);
			}

		}
	}

}
