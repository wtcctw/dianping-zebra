package com.dianping.zebra.syncservice.monitor;

import com.dianping.cat.status.StatusExtension;
import com.dianping.cat.status.StatusExtensionRegister;
import com.dianping.zebra.syncservice.job.ExecutorManager;

import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.Map;

/**
 * Dozer @ 6/12/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */

@Component
public class HeartbeatExtenstion implements StatusExtension, Initializable {

	@Autowired
	ExecutorManager manager;

	@Override
	@PostConstruct
	public void initialize() throws InitializationException {
		StatusExtensionRegister.getInstance().register(this);
	}

	@Override
	public String getId() {
		return "PumaSyncService";
	}

	@Override
	public String getDescription() {
		return "PumaSyncService";
	}

	@Override
	public Map<String, String> getProperties() {
		return manager.getStatus();
	}
}
