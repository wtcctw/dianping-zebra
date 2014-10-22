package com.dianping.zebra.admin.admin.page.merge;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.unidal.helper.Splitters;
import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.annotation.InboundActionMeta;
import org.unidal.web.mvc.annotation.OutboundActionMeta;
import org.unidal.web.mvc.annotation.PayloadMeta;

import com.dianping.zebra.admin.admin.page.JsonHandler;
import com.dianping.zebra.admin.admin.service.MergeConfigService;
import com.dianping.zebra.admin.admin.service.MergeConfigService.Env;

public class Handler extends JsonHandler<Context> {

	@Inject
	private MergeConfigService mergeConfigService;

	@Override
	@PayloadMeta(Payload.class)
	@InboundActionMeta(name = "merge")
	public void handleInbound(Context ctx) throws ServletException, IOException {
		// display only, no action here
	}

	@Override
	@OutboundActionMeta(name = "merge")
	public void handleOutbound(Context ctx) throws ServletException, IOException {
		Payload payload = ctx.getPayload();

		switch (payload.getAction()) {
		case VIEW:
			break;
		case MERGE:
			String from = payload.getFrom();
			String to = payload.getTo();

			if (from != null && from.length() > 0 && to != null && to.length() > 0) {
				List<String> splits = Splitters.by(',').noEmptyItem().trim().split(from);
				Env env = Env.getEnv(payload.getEnv());
				if (env != null) {
					boolean isOk = mergeConfigService.merge(splits, to, env);
					
					success(ctx, new MergeResult(isOk));
				}
			}

			break;
		}
	}
	
	public static class MergeResult{
		public boolean status;

		public MergeResult(boolean status) {
	      super();
	      this.status = status;
      }
	}
}
