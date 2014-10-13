package com.dianping.zebra.admin.admin.page.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import com.dianping.zebra.admin.admin.service.ReportService;
import com.google.gson.Gson;

public class Handler implements PageHandler<Context> {
	private Gson gson = new Gson();

	@Inject
	private ReportService m_reportService;

	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "update")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		// display only, no action here
	}

	@Override
	@OutboundActionMeta(name = "update")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		Payload payload = ctx.getPayload();
		Object responseObject = null;

		switch (payload.getAction()) {
		case VIEW:
			responseObject = m_reportService.getReport(false);
			break;
		case DATABASE:
			responseObject = m_reportService.getDatabase(payload.getDatabase(), false);
			break;
		case APP:
			responseObject = m_reportService.getApp(payload.getApp(), false);
			break;
		}

		HttpServletResponse response = ctx.getHttpServletResponse();
		response.setContentType("application/json");
		response.getWriter().write(gson.toJson(responseObject));
		response.getWriter().flush();
		response.getWriter().close();
		ctx.stopProcess();
	}
}
