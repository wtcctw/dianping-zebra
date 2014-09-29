package com.dianping.zebra.monitor.inspect;

import com.dianping.phoenix.status.AbstractComponentStatus;
import com.dianping.zebra.group.filter.stat.DataSourceStat;
import com.dianping.zebra.group.filter.stat.ExecuteStat;
import com.dianping.zebra.group.filter.stat.StatContext;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.Map;

public class DataSourceStatus extends AbstractComponentStatus {

	public static final String ID = "dal.datasource";

	public DataSourceStatus() {
		super(ID, "Data Source Status");
	}

	@Override
	protected void build(ServletContext ctx) throws Exception {
		buildDataSourceTable();
		buildExecuteSummaryTable();
		buildExecuteTable();
	}

	private void buildDataSourceTable() {
		for (Map.Entry<String, DataSourceStat> datasource : StatContext.getDataSource().entrySet()) {
			TableBuilder table = newTable();
			table.caption("DataSource " + datasource.getKey());
			table.header("Property", "Value");

			for (Map.Entry<String, Object> properties : datasource.getValue().toMap().entrySet()) {
				table.row(properties.getKey(), properties.getValue());
			}
			table.build();
		}
	}

	private void buildExecuteSummaryTable() {
		TableBuilder table = newTable();
		table.caption("SQL Summary");
		Object[] headers = StatContext.getExecuteSummary().toMap(true).keySet().toArray();
		table.header(Arrays.copyOf(headers, headers.length, String[].class));
		table.row(StatContext.getExecuteSummary().toMap(true).values().toArray());
		table.build();
	}

	private void buildExecuteTable() {
		TableBuilder table = newTable();
		table.caption("SQL");
		Object[] headers = StatContext.getExecuteSummary().toMap().keySet().toArray();
		table.header(Arrays.copyOf(headers, headers.length, String[].class));

		for (ExecuteStat stat : StatContext.getExecute().values()) {
			table.row(stat.toMap().values().toArray());
		}
		table.build();
	}

	@Override
	public int getOrder() {
		return 16;
	}
}
