package com.dianping.zebra.admin.admin.page.notify;

import com.dianping.zebra.admin.admin.service.ReportService;
import com.dianping.zebra.web.dal.stat.Heartbeat;
import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import javax.servlet.ServletException;
import java.io.IOException;

public class Handler implements PageHandler<Context> {
    private String NOT_FOUND = "N/A";
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
            hb.setDatabaseName(payload.getDatabase() == null ? NOT_FOUND : payload.getDatabase().toLowerCase());
            hb.setDatasourceBeanClass(payload.getDataSourceBeanClass() == null ? NOT_FOUND : payload
                    .getDataSourceBeanClass());
            hb.setInitPoolSize(payload.getInitPoolSize());
            hb.setMaxPoolSize(payload.getMaxPoolSize());
            hb.setMinPoolSize(payload.getMinPoolSize());
            hb.setReplaced(payload.isReplaced());
            hb.setVersion(payload.getVersion() == null ? NOT_FOUND : payload.getVersion());
            hb.setUsername(payload.getUsername() == null ? NOT_FOUND : payload.getUsername());
            String jdbcUrl = payload.getUrl();
            if (jdbcUrl != null) {
                hb.setJdbcUrl(jdbcUrl);
                String[] parts = jdbcUrl.split(":");
                if (parts != null && parts.length > 2) {
                    hb.setDatabaseType(parts[1].toLowerCase());
                }
            } else {
                hb.setJdbcUrl(NOT_FOUND);
                hb.setDatabaseType(NOT_FOUND);
            }

            m_reportService.createOrUpdate(hb);
        }
    }

    @Override
    @OutboundActionMeta(name = "notify")
    public void handleOutbound(Context ctx) throws ServletException, IOException {
    }
}
