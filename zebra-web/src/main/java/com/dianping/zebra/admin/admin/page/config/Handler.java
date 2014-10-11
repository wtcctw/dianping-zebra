package com.dianping.zebra.admin.admin.page.config;

import com.dianping.zebra.admin.admin.service.DalConfigService;
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

	@Inject
	private DalConfigService m_dalConfigService;

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
		Object responseObject = null;

		switch (payload.getAction()) {
		case VIEWDS: {
			responseObject = m_dalConfigService.getDsConfig(payload.getEnv(), payload.getKey());
			break;
		}
		case CREATE: {
			String project = payload.getProject();
			if (project.equals("groupds")) {
				String key = String.format("groupds.%s.mapping", payload.getKey());
				m_lionHttpService.createKey("groupds", key);
				m_lionHttpService.removeUnset(key);
			} else if (project.equals("ds")) {

			}
			break;
		}
		default: {
			responseObject = m_lionHttpService.getConfigByProject(payload.getEnv(), "groupds");
		}
		}

		HttpServletResponse response = ctx.getHttpServletResponse();
		response.setContentType("application/json");
		response.getWriter().write(gson.toJson(responseObject));
		response.getWriter().flush();
		response.getWriter().close();
		ctx.stopProcess();
	}
}
