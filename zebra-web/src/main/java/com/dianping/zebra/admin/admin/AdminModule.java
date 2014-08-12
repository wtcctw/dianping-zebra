package com.dianping.zebra.admin.admin;

import org.unidal.web.mvc.AbstractModule;
import org.unidal.web.mvc.annotation.ModuleMeta;
import org.unidal.web.mvc.annotation.ModulePagesMeta;

@ModuleMeta(name = "a", defaultInboundAction = "index", defaultTransition = "default", defaultErrorAction = "default")
@ModulePagesMeta({

com.dianping.zebra.admin.admin.page.index.Handler.class,

com.dianping.zebra.admin.admin.page.service.Handler.class,

com.dianping.zebra.admin.admin.page.notify.Handler.class
})
public class AdminModule extends AbstractModule {

}
