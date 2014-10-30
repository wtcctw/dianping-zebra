package com.dianping.zebra.group.filter.stat.server;

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
					Server server = new Server(8181);

					ResourceHandler resourceHandler = new ResourceHandler();
					resourceHandler.setWelcomeFiles(new String[] { "index.html" });

					String path = ServerHelper.class.getClassLoader()
						  .getResource("filter/stat/server").toExternalForm();
					resourceHandler.setResourceBase(path);
					server.setHandler(resourceHandler);

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
}
