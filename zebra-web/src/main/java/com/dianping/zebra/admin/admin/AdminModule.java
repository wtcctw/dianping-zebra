package com.dianping.zebra.admin.admin;

import org.unidal.web.mvc.AbstractModule;
import org.unidal.web.mvc.annotation.ModuleMeta;
import org.unidal.web.mvc.annotation.ModulePagesMeta;

@ModuleMeta(name = "a", defaultInboundAction = "update", defaultTransition = "default", defaultErrorAction = "default")
@ModulePagesMeta({

com.dianping.zebra.admin.admin.page.update.Handler.class,

com.dianping.zebra.admin.admin.page.service.Handler.class,

com.dianping.zebra.admin.admin.page.notify.Handler.class,

com.dianping.zebra.admin.admin.page.blacklist.Handler.class,

com.dianping.zebra.admin.admin.page.merge.Handler.class,

com.dianping.zebra.admin.admin.page.config.Handler.class
})
public class AdminModule extends AbstractModule {

}
