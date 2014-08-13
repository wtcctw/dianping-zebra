package com.dianping.zebra.admin;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.servlet.GzipFilter;
import org.unidal.test.jetty.JettyServer;

@RunWith(JUnit4.class)
public class TestServer extends JettyServer {
   public static void main(String[] args) throws Exception {
      TestServer server = new TestServer();

      server.startServer();
      server.startWebapp();
      server.stopServer();
   }

   @Before
   public void before() throws Exception {
      System.setProperty("devMode", "true");
      super.startServer();
   }

   @Override
   protected String getContextPath() {
      return "/admin";
   }

   @Override
   protected int getServerPort() {
      return 2364;
   }

   @Override
   protected void postConfigure(WebAppContext context) {
      context.addFilter(GzipFilter.class, "/a/*", Handler.ALL);
   }

   @Test
   public void startWebapp() throws Exception {
      // open the page in the default browser
      display("/admin/a");
      waitForAnyKey();
   }
}
