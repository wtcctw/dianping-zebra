package com.dianping.zebra.group.router;

import java.util.HashSet;
import java.util.Set;

public class RouterContext {

	private String sql;

	private Set<RounterTarget> excludeTargets = new HashSet<RounterTarget>();

	public RouterContext() {
	}

	public RouterContext(String sql) {
		super();
		this.sql = sql;
	}

	public RouterContext(String sql, Set<RounterTarget> excludeTargets) {
		super();
		this.sql = sql;
		this.excludeTargets = excludeTargets;
	}
	
	public RouterContext(Set<RounterTarget> excludeTargets) {
		super();
		this.excludeTargets = excludeTargets;
	}

	public String getSql() {
		return sql;
	}

	public Set<RounterTarget> getExcludeTargets() {
		return excludeTargets;
	}
	
	public void addExcludeTarget(String dsId){
		this.excludeTargets.add(new RounterTarget(dsId));
	}
}
