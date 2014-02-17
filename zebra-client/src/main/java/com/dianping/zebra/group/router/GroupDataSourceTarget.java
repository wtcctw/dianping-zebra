package com.dianping.zebra.group.router;

public class GroupDataSourceTarget {

	private String id;

	private boolean readOnly;

	public GroupDataSourceTarget() {
		super();
	}

	public GroupDataSourceTarget(String id, boolean readOnly) {
		super();
		this.id = id;
		this.readOnly = readOnly;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	@Override
	public String toString() {
		return String.format("GroupDataSourceTarget [id=%s, readOnly=%s]", id, readOnly);
	}

}
