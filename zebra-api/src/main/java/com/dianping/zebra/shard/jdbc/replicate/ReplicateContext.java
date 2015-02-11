package com.dianping.zebra.shard.jdbc.replicate;

import java.util.List;

import javax.sql.DataSource;

public class ReplicateContext {
	private boolean isReplicated = false;

	private DataSource originalDs;

	private List<DataSource> replicatedDs;

	public boolean isReplicated() {
		return isReplicated;
	}

	public void setReplicated(boolean isReplicated) {
		this.isReplicated = isReplicated;
	}

	public DataSource getOriginalDs() {
		return originalDs;
	}

	public void setOriginalDs(DataSource originalDs) {
		this.originalDs = originalDs;
	}

	public List<DataSource> getReplicatedDs() {
		return replicatedDs;
	}

	public void setReplicatedDs(List<DataSource> replicatedDs) {
		this.replicatedDs = replicatedDs;
	}
}
