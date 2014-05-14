package com.dianping.zebra.admin.admin.page.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import com.dianping.zebra.admin.admin.AdminPage;
import com.dianping.zebra.admin.admin.service.DalResult;
import com.dianping.zebra.admin.admin.service.DalService;
import com.dianping.zebra.admin.admin.service.LogService;

public class Handler implements PageHandler<Context> {

	@Inject
	private JspViewer m_jspViewer;

	@Inject
	private DalService m_dalService;

	@Inject
	private LogService m_logService;

	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "service")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		// display only, no action here

	}

	@Override
	@OutboundActionMeta(name = "service")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		Model model = new Model(ctx);
		Payload payload = ctx.getPayload();
		String ip = payload.getIp();
		String port = payload.getPort();
		String user = payload.getUser();
		String env = payload.getEnv();
		DalResult result = null;

		if (ip != null && port != null && env != null) {
			switch (payload.getAction()) {
			case MARKDOWN:
				result = m_dalService.markDown(env, ip, port);
				break;
			case MARKUP:
				result = m_dalService.markUp(env, ip, port);
				break;
			case REMOVE:
				// 1. delete all db key 2. update all value refer to this key
				break;
			case GETCONFIG:
				// TODO
				break;
			default:
				ctx.sendJsonResponse("1", "unkown operation", null);
				break;
			}
		}else{
			ctx.sendJsonResponse("1", "ip or port or env cannot be null", null);
		}

		if (result != null) {
			result.setUser(user);
			result.setTime(new Date());
			ctx.sendJsonResponse(String.valueOf(result.getStatus()),
			      String.format("%s %s", result.getAction(), result.getActualOperated()), result.getMessage());
			m_logService.log(result);
		}

		model.setAction(Action.VIEW);
		model.setPage(AdminPage.SERVICE);

		if (!ctx.isProcessStopped()) {
			m_jspViewer.view(ctx, model);
		}
	}
}
