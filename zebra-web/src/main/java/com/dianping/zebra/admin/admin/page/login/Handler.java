package com.dianping.zebra.admin.admin.page.login;

import com.dianping.zebra.admin.admin.page.JsonHandler;
import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import javax.servlet.ServletException;
import java.io.IOException;

public class Handler extends JsonHandler<Context> {
	@Inject
	private JspViewer m_jspViewer;

	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "login")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		// display only, no action here
	}

	@Override
	@OutboundActionMeta(name = "login")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		try {
			Payload payload = ctx.getPayload();
			switch (payload.getAction()) {
			case VIEW:
				if ("zebra".equals(payload.getUsername()) && "zebra".equals(payload.getPassword())) {
					ctx.getHttpServletRequest().getSession().setAttribute("AuthorizationFilter", true);
					success(ctx, null);
				} else {
					error(ctx, null);
				}
			}
		} catch (Exception exp) {
			error(ctx, exp.getMessage());
		}
	}
}
