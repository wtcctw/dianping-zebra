package com.dianping.zebra.shard.router;

import java.util.HashSet;
import java.util.Set;

public class RouterContext {
	
	private Set<String> tableSet = new HashSet<String>();
	
	private String hintComment;

	public Set<String> getTableSet() {
		return tableSet;
	}

	public void setTableSets(Set<String> tableSet) {
		this.tableSet = tableSet;
	}

	public String getHintComment() {
		return hintComment;
	}

	public void setHintComment(String hintComment) {
		this.hintComment = hintComment;
	}
}
