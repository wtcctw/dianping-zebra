package com.dianping.zebra.group.router;

public class RounterTarget implements Comparable<RounterTarget> {

	private String id;

	private int weight;

	private int end;

	public RounterTarget(String dsId) {
		super();
		this.id = dsId;
	}

	public RounterTarget(String id, int weight, int end) {
		super();
		this.id = id;
		this.weight = weight;
		this.end = end;
	}

	public String getId() {
		return id;
	}

	public int getWeight() {
		return weight;
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RounterTarget other = (RounterTarget) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("GroupDataSourceTarget [id=%s, weight=%s, end=%s]", id, weight, end);
	}

	@Override
	public int compareTo(RounterTarget o) {
		return end - o.end;
	}
}
