package com.dianping.zebra.admin;

import org.unidal.helper.Threads;
import org.unidal.helper.Threads.Task;
import org.unidal.initialization.AbstractModule;
import org.unidal.initialization.Module;
import org.unidal.initialization.ModuleContext;

import com.dianping.zebra.admin.admin.service.DatabaseRealtimeService;
import com.dianping.zebra.admin.admin.service.HeartbeatUpdateService;

public class ZebraHomeModule extends AbstractModule{
	
	public static String ID = "zebra";

	@Override
   public Module[] getDependencies(ModuleContext ctx) {
	   return null;
   }

	@Override
   protected void execute(ModuleContext ctx) throws Exception {
		DatabaseRealtimeService databaseRealtimeService = ctx.lookup(DatabaseRealtimeService.class);
		HeartbeatUpdateService heartbeatUpdateService = ctx.lookup(HeartbeatUpdateService.class);
		
		Threads.forGroup("Zebra").start((Task)databaseRealtimeService);
		Threads.forGroup("Zebra").start((Task)heartbeatUpdateService);
   }
}
