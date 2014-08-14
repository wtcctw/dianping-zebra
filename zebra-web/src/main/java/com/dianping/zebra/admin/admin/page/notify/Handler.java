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

		if (payload.getApp() != null && payload.getIp() != null && payload.getDataSourceBeanName() != null) {
			Heartbeat hb = new Heartbeat();

			hb.setAppName(payload.getApp().toLowerCase());
			hb.setIp(payload.getIp());
			hb.setDatasourceBeanName(payload.getDataSourceBeanName());
			hb.setDatabaseName(payload.getDatabase() == null ? "N/A" : payload.getDatabase().toLowerCase());
			hb.setDatasourceBeanClass(payload.getDataSourceBeanClass() == null ? "N/A" :payload.getDataSourceBeanClass());
			hb.setInitPoolSize(payload.getInitPoolSize());
			hb.setMaxPoolSize(payload.getMaxPoolSize());
			hb.setMinPoolSize(payload.getMinPoolSize());
			hb.setReplaced(payload.isReplaced());
			hb.setVersion(payload.getVersion() == null ? "N/A" : payload.getVersion());
			hb.setUsername(payload.getUsername() == null ? "N/A" : payload.getUsername());
			String jdbcUrl = payload.getUrl();
			if(jdbcUrl != null){
				hb.setJdbcUrl(jdbcUrl);
				String[] parts = jdbcUrl.split(":");
				if (parts != null && parts.length > 2) {
					hb.setDatabaseType(parts[1].toLowerCase());
				}
			}else{
				hb.setDatabaseType("N/A");
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
