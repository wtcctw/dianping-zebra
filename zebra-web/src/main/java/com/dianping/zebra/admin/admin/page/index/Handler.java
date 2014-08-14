package com.dianping.zebra.admin.admin.page.index;

import java.io.IOException;

import javax.servlet.ServletException;

import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import com.dianping.zebra.admin.admin.AdminPage;
import com.dianping.zebra.admin.admin.service.ReportService;

public class Handler implements PageHandler<Context> {
	@Inject
	private JspViewer m_jspViewer;

	@Inject
	private ReportService m_heartbeatService;

	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "index")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		// display only, no action here
	}

	@Override
	@OutboundActionMeta(name = "index")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		Model model = new Model(ctx);
		Payload payload = ctx.getPayload();
		
		switch (payload.getAction()) {
		case VIEW:
			model.setAction(Action.VIEW);
			model.setReport(m_heartbeatService.getReport());
			break;
		case DATABASE:
			model.setAction(Action.DATABASE);
			model.setDatabase(m_heartbeatService.getDatabase(payload.getDatabase()));
			break;
		case APP:
			model.setAction(Action.APP);
			model.setApp(m_heartbeatService.getApp(payload.getApp()));
			break;
		}

		model.setPage(AdminPage.INDEX);

		if (!ctx.isProcessStopped()) {
			m_jspViewer.view(ctx, model);
		}
	}
}
