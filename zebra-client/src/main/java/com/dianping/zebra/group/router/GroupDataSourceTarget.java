package com.dianping.zebra.group.router;

public class GroupDataSourceTarget implements Comparable<GroupDataSourceTarget> {

	private String id;

	private boolean readOnly;

	private int weight;

	private int end;

	public GroupDataSourceTarget() {
		super();
	}

	public GroupDataSourceTarget(String id, int weight, int end, boolean readOnly) {
		super();
		this.id = id;
		this.weight = weight;
		this.readOnly = readOnly;
		this.end = end;
	}

	public String getId() {
		return id;
	}

	public int getWeight() {
		return weight;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public int getEnd() {
		return end;
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
		return String.format("GroupDataSourceTarget [id=%s, readOnly=%s, weight=%s, end=%s]", id, readOnly, weight, end);
	}

	@Override
	public int compareTo(GroupDataSourceTarget o) {
		return end - o.end;
	}

}
