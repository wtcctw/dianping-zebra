package com.dianping.zebra.group.monitor;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dianping.phoenix.status.AbstractComponentStatus;
import com.dianping.phoenix.status.ComponentStatus;

public class SingleDataSourceComponentStatus extends AbstractComponentStatus implements ComponentStatus {
	public static final String ID = "dal.groupdatasource";

	public SingleDataSourceComponentStatus() {
		super(ID, "DAL GroupDataSoure");
	}

	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new LinkedHashMap<String, Object>();
		properties.put("Cpu cores", "asdadas");
		properties.put("Load average", "asdas");
		return properties;
	}

	@Override
	public State getState() {
		return State.INITIALIZED;
	}

}
