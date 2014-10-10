package com.dianping.zebra.admin.admin.page.config;

import com.dianping.zebra.admin.admin.service.LionHttpService;
import com.google.gson.Gson;
import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Handler implements PageHandler<Context> {
    private Gson gson = new Gson();

    @Inject
    private LionHttpService m_lionHttpService;

    @Override
    @PayloadMeta(Payload.class)
    @InboundActionMeta(name = "config")
    public void handleInbound(Context ctx) throws ServletException, IOException {
        // display only, no action here
    }

    @Override
    @OutboundActionMeta(name = "config")
    public void handleOutbound(Context ctx) throws ServletException, IOException {
        Payload payload = ctx.getPayload();
        HttpServletResponse response = ctx.getHttpServletResponse();
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(m_lionHttpService.getConfigByProject(payload.getEnv(), "groupds")));
        response.getWriter().flush();
        response.getWriter().close();
        ctx.stopProcess();
    }
}
