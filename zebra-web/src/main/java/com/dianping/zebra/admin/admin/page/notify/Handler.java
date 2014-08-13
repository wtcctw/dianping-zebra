package com.dianping.zebra.admin.admin.page.notify;

import java.io.IOException;

import javax.servlet.ServletException;

import com.dianping.zebra.admin.admin.AdminPage;
import com.dianping.zebra.admin.admin.service.ReportService;
import com.dianping.zebra.web.dal.stat.Heartbeat;

import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

public class Handler implements PageHandler<Context> {
	@Inject
	private JspViewer m_jspViewer;

	@Inject
	private ReportService m_reportService;

	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "notify")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		// display only, no action here
		Payload payload = ctx.getPayload();

		if (payload.getApp() != null && payload.getDatabase() != null && payload.getDataSourceBeanClass() != null
		      && payload.getDataSourceBeanName() != null && payload.getIp() != null && payload.getVersion() != null
		      && payload.getUrl() != null && payload.getUsername() != null) {
			Heartbeat hb = new Heartbeat();

			hb.setAppName(payload.getApp().toLowerCase());
			hb.setDatabase(payload.getDatabase().toLowerCase());
			hb.setDatasourceBeanClass(payload.getDataSourceBeanClass());
			hb.setDatasourceBeanName(payload.getDataSourceBeanName());
			hb.setInitPoolSize(payload.getInitPoolSize());
			hb.setIp(payload.getIp());
			hb.setMaxPoolSize(payload.getMaxPoolSize());
			hb.setMinPoolSize(payload.getMinPoolSize());
			hb.setReplaced(payload.isReplaced());
			hb.setVersion(payload.getVersion());
			hb.setUsername(payload.getUsername());
			String jdbcUrl = payload.getUrl();
			hb.setJdbcUrl(jdbcUrl);
			String[] parts = jdbcUrl.split(":");
			if (parts != null && parts.length > 2) {
				hb.setDatabaseType(parts[1].toLowerCase());
			}

			m_reportService.createOrUpdate(hb);
		}
	}

	@Override
	@OutboundActionMeta(name = "notify")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		Model model = new Model(ctx);

		model.setAction(Action.VIEW);
		model.setPage(AdminPage.NOTIFY);
		m_jspViewer.view(ctx, model);
	}
}
