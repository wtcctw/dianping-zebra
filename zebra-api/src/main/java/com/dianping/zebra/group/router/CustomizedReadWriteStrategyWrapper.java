package com.dianping.zebra.group.router;

import java.util.ArrayList;
import java.util.List;

public class CustomizedReadWriteStrategyWrapper implements CustomizedReadWriteStrategy {
	private List<CustomizedReadWriteStrategy> items = new ArrayList<CustomizedReadWriteStrategy>();

	public void addStrategy(CustomizedReadWriteStrategy strategy) {
		items.add(strategy);
	}

	@Override
	public boolean forceReadFromMaster() {
		for (CustomizedReadWriteStrategy strategy : items) {
			if (strategy.forceReadFromMaster()) {
				return true;
			}
		}

		return false;
	}
}
