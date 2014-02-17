package com.dianping.zebra.group.healthcheck;

import java.util.List;

import com.dianping.zebra.group.config.GroupConfigChangeEvent;
import com.dianping.zebra.group.config.GroupConfigChangeListener;

public class HealthCheckImpl implements GroupConfigChangeListener {
	private int heartbeatTime;

	private List<String> activeDsKeys;

	private List<String> unactiveDsKeys;

	public void healthProblemNotify(String dsKey, Throwable e) {

	}

	@Override
	public void onChange(GroupConfigChangeEvent event) {
		// TODO Auto-generated method stub

	}

	private class heartbeatCheck implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// check unactive ds.

		}

	}

	@Override
	public String getName() {
		return "health-checke-listener";
	}
}
