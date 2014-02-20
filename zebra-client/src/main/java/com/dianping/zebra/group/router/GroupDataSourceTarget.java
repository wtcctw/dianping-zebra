package com.dianping.zebra.group.router;

public class GroupDataSourceTarget {

	private String id;

	private boolean readOnly;

	private int weight;

	public GroupDataSourceTarget() {
		super();
	}

	public GroupDataSourceTarget(String id, int weight, boolean readOnly) {
		super();
		this.id = id;
		this.weight = weight;
		this.readOnly = readOnly;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
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
