package com.dianping.zebra.admin.admin.page.blacklist;

import com.dianping.zebra.admin.admin.page.JsonHandler;
import com.dianping.zebra.admin.admin.service.BlackListService;
import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import javax.servlet.ServletException;
import java.io.IOException;

public class Handler extends JsonHandler<Context> {

	@Inject
	private BlackListService m_blackListService;

	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "blacklist")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		// display only, no action here
	}

	@Override
	@OutboundActionMeta(name = "blacklist")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		try {
			Payload payload = ctx.getPayload();
			Object responseObject = null;

			switch (payload.getAction()) {
			case VIEW:
				responseObject = m_blackListService.getAllBlackList(payload.getEnv());
				break;
			case ADD:
				m_blackListService.addItem(payload.getEnv(), payload.getIp(), payload.getId());
				break;
			case DELETE:
				m_blackListService.deleteItem(payload.getEnv(), payload.getKey(), payload.getId());
				break;
			}

			success(ctx, responseObject);
		} catch (Exception exp) {
			error(ctx, exp.getMessage());
		}
	}
}
