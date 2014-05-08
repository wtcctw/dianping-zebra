package com.dianping.zebra.admin.admin.page.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import com.dianping.zebra.admin.admin.AdminPage;
import com.dianping.zebra.admin.admin.service.DalException;
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
		List<String> markedDataSource = new ArrayList<String>();

		try {
			switch (payload.getAction()) {
			case MARKDOWN:
				markedDataSource = m_dalService.markDown(env, ip, port);
				ctx.sendJsonResponse("0", String.format("successs markdown %s", markedDataSource), null);
				break;
			case MARKUP:
				markedDataSource = m_dalService.markUp(env, ip, port);
				ctx.sendJsonResponse("0", String.format("successs markup %s", markedDataSource), null);
				break;
			case REMOVE:
				// 1. delete all db key 2. update all value refer to this key
				break;
			case GETCONFIG:
				// TODO
				break;
			default:
				break;
			}
		} catch (DalException dalException) {
			markedDataSource = dalException.getMarkedDataSources();
			ctx.sendJsonResponse("0", String.format("successs to mark %s", markedDataSource), null);
		} catch (Throwable throwable) {
			ctx.sendJsonResponse("1", "fail to mark any database", throwable);
		} finally {
			m_logService.log(ip, port, user, new Date(), payload.getAction().getName(), markedDataSource);
		}

		model.setAction(Action.VIEW);
		model.setPage(AdminPage.SERVICE);

		if (!ctx.isProcessStopped()) {
			m_jspViewer.view(ctx, model);
		}
	}
}
