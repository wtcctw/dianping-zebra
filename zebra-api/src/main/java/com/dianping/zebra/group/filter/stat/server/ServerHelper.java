package com.dianping.zebra.group.filter.stat.server;

import com.dianping.zebra.group.filter.stat.StatFilterConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 * Created by Dozer on 10/30/14.
 */
public class ServerHelper {
	private static Thread serverThread;

	public synchronized static void start() {
		if (serverThread != null) {
			return;
		}
		serverThread = new Thread(new Runnable() {
			@Override public void run() {
				try {
					Server server = new Server(StatFilterConfig.getServerPort());
					server.setHandler(getResourceHandler());
					server.start();
					server.join();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		serverThread.setDaemon(true);
		serverThread.setName("zebra-statfilter-server");
		serverThread.start();
	}

	private static ResourceHandler getResourceHandler() {
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setWelcomeFiles(new String[] { "index.html" });
		String path = ServerHelper.class.getClassLoader()
			  .getResource("filter/stat/server").toExternalForm();
		resourceHandler.setResourceBase(path);
		return resourceHandler;
	}
}
