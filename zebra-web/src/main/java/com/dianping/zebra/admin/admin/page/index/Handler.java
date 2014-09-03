package com.dianping.zebra.admin.admin.page.index;

import java.io.IOException;

import javax.servlet.ServletException;

import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import com.dianping.zebra.admin.admin.AdminPage;
import com.dianping.zebra.admin.admin.service.ConnectionService;
import com.dianping.zebra.admin.admin.service.ConnectionServiceImpl.ConnectionStatus;
import com.dianping.zebra.admin.admin.service.ReportService;

public class Handler implements PageHandler<Context> {
	@Inject
	private JspViewer m_jspViewer;

	@Inject
	private ReportService m_heartbeatService;

	@Inject
	private ConnectionService m_connectionService;

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
		case CONNECTION:
			model.setAction(Action.CONNECTION);
			String jdbcRef = payload.getDatabase().toLowerCase();
			ConnectionStatus connectionstatus = new ConnectionStatus();
			if(jdbcRef.toLowerCase().equals("dpreview")){
				jdbcRef = "DPReview"; //hack
			}
			connectionstatus.setConnected(m_connectionService.canConnect(jdbcRef));
			connectionstatus.setConfig(m_connectionService.getConfig(jdbcRef).toString());
			model.setConnectionStatus(connectionstatus);
			break;
		}

		model.setPage(AdminPage.INDEX);

		if (!ctx.isProcessStopped()) {
			m_jspViewer.view(ctx, model);
		}
	}
}
