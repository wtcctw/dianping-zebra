package com.dianping.zebra.admin.admin.page;

import com.dianping.zebra.admin.admin.page.config.Context;
import com.google.gson.Gson;
import org.unidal.web.mvc.PageHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dozer on 10/14/14.
 */
public abstract class JsonHandler<T> implements PageHandler<Context> {
	protected Gson gson = new Gson();

	protected void success(Context ctx, Object result) throws IOException {
		HttpServletResponse response = ctx.getHttpServletResponse();
		response.setContentType("application/json");
		response.getWriter().write(gson.toJson(result));
		response.getWriter().flush();
		response.getWriter().close();
		ctx.stopProcess();
	}

	protected void error(Context ctx, Object result) throws IOException {
		HttpServletResponse response = ctx.getHttpServletResponse();
		response.setContentType("application/json");
		response.setStatus(500);
		response.getWriter().write(gson.toJson(result));
		response.getWriter().flush();
		response.getWriter().close();
		ctx.stopProcess();
	}
}
