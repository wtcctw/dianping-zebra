package com.dianping.zebra.admin.admin.page.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.unidal.dal.jdbc.DalException;
import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import com.dianping.zebra.admin.admin.service.LionHttpService;
import com.dianping.zebra.admin.admin.service.ReportService;
import com.dianping.zebra.web.dal.stat.Heartbeat;
import com.dianping.zebra.web.dal.stat.HeartbeatDao;
import com.google.gson.Gson;

public class Handler implements PageHandler<Context> {
	private Gson gson = new Gson();

	@Inject
	private ReportService m_reportService;

	@Inject
	private LionHttpService m_lionHttpService;
	
	@Inject
	private HeartbeatDao heartbeatDao;

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
		boolean isProduct = m_lionHttpService.isProduct();

		switch (payload.getAction()) {
		case VIEW:
			responseObject = m_reportService.getReport(isProduct);
			break;
		case DATABASE:
			responseObject = m_reportService.getDatabase(payload.getDatabase(), isProduct);
			break;
		case APP:
			responseObject = m_reportService.getApp(payload.getApp(), isProduct);
			break;
		case DELETE_MACHINE:
			Heartbeat ht = heartbeatDao.createLocal();
			ht.setAppName(payload.getApp());
			ht.setIp(payload.getIp());
			ht.setDatasourceBeanName(payload.getBeanName());
			try {
	         heartbeatDao.deleteByInfo(ht);
	         responseObject = true;
         } catch (DalException e) {
         	responseObject = false;
         }
		}

		HttpServletResponse response = ctx.getHttpServletResponse();
		response.setContentType("application/json");
		response.getWriter().write(gson.toJson(responseObject));
		response.getWriter().flush();
		response.getWriter().close();
		ctx.stopProcess();
	}
}
