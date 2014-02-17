package com.dianping.zebra.group.healthcheck;

public interface HealthCheck {

	public void healthProblemNotify(String dsKey,Throwable e);
	
}
