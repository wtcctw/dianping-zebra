package com.dianping.zebra.admin.admin.page.update;

import com.dianping.zebra.admin.admin.service.ConnectionService;
import com.dianping.zebra.admin.admin.service.ReportService;
import com.google.gson.Gson;
import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;
import org.unidal.web.mvc.view.BaseJspViewer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Handler implements PageHandler<Context> {
    private Gson gson = new Gson();
    @Inject
    private ConnectionService m_connectionService;
    @Inject
    private ReportService m_heartbeatService;
    @Inject
    private BaseJspViewer viewer;

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
                responseObject = m_heartbeatService.getReport();
                break;
            case DATABASE:
                responseObject = m_heartbeatService.getDatabase(payload.getDatabase());
                break;
            case APP:
                responseObject = m_heartbeatService.getApp(payload.getApp());
                break;
//            case CONNECTION:
//                model.setAction(Action.CONNECTION);
//                String jdbcRef = payload.getDatabase().toLowerCase();
//                ConnectionStatus connectionstatus = new ConnectionStatus();
//                if (jdbcRef.toLowerCase().equals("dpreview")) {
//                    jdbcRef = "DPReview"; //hack
//                }
//                connectionstatus.setConnected(m_connectionService.canConnect(jdbcRef));
//                connectionstatus.setConfig(m_connectionService.getConfig(jdbcRef).toString());
//                model.setConnectionStatus(connectionstatus);
//                break;
        }

        HttpServletResponse response = ctx.getHttpServletResponse();
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(responseObject));
        response.getWriter().flush();
        response.getWriter().close();
        ctx.stopProcess();
    }
}
