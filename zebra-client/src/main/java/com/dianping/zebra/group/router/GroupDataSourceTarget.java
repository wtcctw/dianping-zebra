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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupDataSourceTarget other = (GroupDataSourceTarget) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("GroupDataSourceTarget [id=%s, readOnly=%s]", id, readOnly);
	}

}
