package com.dianping.zebra.admin.admin.page.blacklist;

import java.io.IOException;

import javax.servlet.ServletException;

import com.dianping.zebra.admin.admin.AdminPage;
import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.PageHandler;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

public class Handler implements PageHandler<Context> {

	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "blacklist")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		// display only, no action here
	}

	@Override
	@OutboundActionMeta(name = "blacklist")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
	}
}
