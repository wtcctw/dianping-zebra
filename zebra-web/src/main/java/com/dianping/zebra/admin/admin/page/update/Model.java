package com.dianping.zebra.admin.admin.page.update;

import com.dianping.zebra.admin.admin.AdminPage;
import org.unidal.web.mvc.ViewModel;

public class Model extends ViewModel<AdminPage, Action, Context> {
	public Model(Context ctx) {
		super(ctx);
	}

	@Override
	public Action getDefaultAction() {
		return Action.VIEW;
	}
}
