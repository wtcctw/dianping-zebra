package com.dianping.zebra.admin.admin.page.service;

import com.dianping.zebra.admin.admin.service.DalConfigService;
import com.dianping.zebra.admin.admin.service.DalResult;
import com.dianping.zebra.admin.admin.service.DalService;
import com.dianping.zebra.admin.admin.service.LogService;
import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Date;

public class Handler implements PageHandler<Context> {

    @Inject
    private DalConfigService m_dalConfigService;
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
        Payload payload = ctx.getPayload();
        String ip = payload.getIp();
        String port = payload.getPort();
        String user = payload.getUser();
        String env = payload.getEnv();
        String database = payload.getDatabase();
        String name = payload.getName();
        String[] ips = payload.getIps();
        DalResult result = null;

        switch (payload.getAction()) {
            case MARKDOWN:
                if (ip != null && port != null && env != null) {
                    result = m_dalService.markDown(env, ip, port, database);
                } else {
                    ctx.sendJsonResponse("1", "ip or port or env cannot be null", null);
                }
                break;
            case MARKUP:
                if (ip != null && port != null && env != null) {
                    result = m_dalService.markUp(env, ip, port, database);
                } else {
                    ctx.sendJsonResponse("1", "ip or port or env cannot be null", null);
                }
                break;
            case NOTIFY_IP:
                if (ips != null) {
                    m_logService.logNotify(ips);
                }
                break;
            case INIT_DS:
                if (name != null) {
                    boolean success = m_dalConfigService.generateConfig(name.toLowerCase());
                    ctx.sendJsonResponse(success == true ? "0" : "1", success == true ? "success" : "fail", null);
                }
                break;
            default:
                ctx.sendJsonResponse("1", "unkown operation", null);
                break;
        }

        if (result != null) {
            result.setUser(user);
            result.setTime(new Date());
            ctx.sendJsonResponse(String.valueOf(result.getStatus()),
                    String.format("%s %s", result.getAction(), result.getActualOperated()), result.getMessage());
            m_logService.log(result);
        }
    }
}
