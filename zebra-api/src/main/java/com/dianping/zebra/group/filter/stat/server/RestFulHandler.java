package com.dianping.zebra.group.filter.stat.server;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dozer on 10/30/14.
 */
public class RestFulHandler extends AbstractHandler {
	@Override public void handle(String s, Request request, HttpServletRequest httpServletRequest,
		  HttpServletResponse httpServletResponse) throws IOException, ServletException {
		httpServletResponse.getWriter().write("sdadada");
		httpServletResponse.getWriter().flush();
	}
}
