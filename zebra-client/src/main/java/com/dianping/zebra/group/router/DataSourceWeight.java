package com.dianping.zebra.group.router;

public class DataSourceWeight {

	private String dataSourceKey;

	private int weight;

	public DataSourceWeight() {
		super();
	}

	public DataSourceWeight(String dataSourceKey, int weight) {
		super();
		this.dataSourceKey = dataSourceKey;
		this.weight = weight;
	}

	public String getDataSourceKey() {
		return dataSourceKey;
	}

	public void setDataSourceKey(String dataSourceKey) {
		this.dataSourceKey = dataSourceKey;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return String.format("DataSourceWeight [dataSourceKey=%s, weight=%s]", dataSourceKey, weight);
	}

}
