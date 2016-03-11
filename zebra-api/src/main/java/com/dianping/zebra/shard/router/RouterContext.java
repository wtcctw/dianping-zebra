package com.dianping.zebra.shard.router;

import java.util.HashSet;
import java.util.Set;

public class RouterContext {
	
	private Set<String> tableSets = new HashSet<String>();
	
	private String hintComment;

	public Set<String> getTableSets() {
		return tableSets;
	}

	public void setTableSets(Set<String> tableSets) {
		this.tableSets = tableSets;
	}

	public String getHintComment() {
		return hintComment;
	}

	public void setHintComment(String hintComment) {
		this.hintComment = hintComment;
	}
}
