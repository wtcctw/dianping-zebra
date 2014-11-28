package com.dianping.zebra.monitor.inspect;

import com.dianping.phoenix.status.AbstractComponentStatus;
import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.monitor.GroupDataSourceMBean;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;
import com.dianping.zebra.group.util.DataSourceState;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DataSourceStatus extends AbstractComponentStatus {

	public static final String ID = "dal.datasource";

	public DataSourceStatus() {
		super(ID, "Data Source Status");
	}

	@Override
	protected void build(ServletContext ctx) throws Exception {
		TableBuilder configTable = newTable();
		TableBuilder statusTable = newTable();

		configTable.caption("DataSource Config");
		configTable.header("Name", "Type", "Url", "Username", "InitialPoolSize", "MaxPoolSize", "MinPoolSize",
				"CheckoutTimeout", "DalVersion");
		statusTable.caption("DataSource Connections");
		statusTable.header("Name", "Url", "BusyConnection", "IdleConnection", "IsMaster", "Weight", "Status");

		// in case of spring framework has listener in web.xml
		Object object = ctx.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");

		if (object != null) {
			org.springframework.web.context.WebApplicationContext context = (org.springframework.web.context.WebApplicationContext) object;
			@SuppressWarnings("unchecked")
			Map<String, DataSource> beans = context.getBeansOfType(DataSource.class);

			for (Entry<String, DataSource> entry : beans.entrySet()) {
				String name = narmalized(entry.getKey());
				DataSource ds = entry.getValue();

				// if (ds instanceof ComboPooledDataSource) {
				// configTable.row(name, ComboPooledDataSource.class.getName(), ((ComboPooledDataSource) ds).getJdbcUrl(),
				// ((ComboPooledDataSource) ds).getUser(), ((ComboPooledDataSource) ds).getInitialPoolSize(),
				// ((ComboPooledDataSource) ds).getMaxPoolSize(), ((ComboPooledDataSource) ds).getMinPoolSize(),
				// ((ComboPooledDataSource) ds).getCheckoutTimeout(), null);
				// statusTable.row(name, ((ComboPooledDataSource) ds).getJdbcUrl(),
				// ((ComboPooledDataSource) ds).getNumBusyConnections(),
				// ((ComboPooledDataSource) ds).getNumIdleConnections(), null, null, null);
				// } else

				if (ds instanceof GroupDataSourceMBean) {
					try {
						SingleDataSourceMBean masterBean = ((GroupDataSourceMBean) ds).getWriteSingleDataSourceMBean();
						DataSourceConfig config = masterBean.getConfig();
						configTable
								.row(name, GroupDataSource.class.getName(), config.getJdbcUrl(), config.getUsername(),
										findByKey(config.getProperties(), "InitialPoolSize"),
										findByKey(config.getProperties(), "MaxPoolSize"),
										findByKey(config.getProperties(), "MinPoolSize"),
										findByKey(config.getProperties(), "CheckoutTimeout"), Constants.ZEBRA_VERSION);
						statusTable.row(name, config.getJdbcUrl(),
								masterBean.getState() == DataSourceState.UP ? masterBean.getNumBusyConnection() : 0,
								masterBean.getState() == DataSourceState.UP ? masterBean.getNumIdleConnection() : 0,
								"Yes",
								null, masterBean.getState());

						for (SingleDataSourceMBean mbean : ((GroupDataSourceMBean) ds).getReaderSingleDataSourceMBean()
								.values()) {
							config = mbean.getConfig();
							configTable.row(null, null, config.getJdbcUrl(), config.getUsername(),
									findByKey(config.getProperties(), "InitialPoolSize"),
									findByKey(config.getProperties(), "MaxPoolSize"),
									findByKey(config.getProperties(), "MinPoolSize"),
									findByKey(config.getProperties(), "CheckoutTimeout"), Constants.ZEBRA_VERSION);
							statusTable.row(null, config.getJdbcUrl(),
									mbean.getState() == DataSourceState.UP ? mbean.getNumBusyConnection() : 0,
									mbean.getState() == DataSourceState.UP ? mbean.getNumIdleConnection() : 0, "No",
									mbean
											.getConfig().getWeight(), mbean.getState());
						}
					} catch (Exception e) {
						configTable.row(name, GroupDataSource.class.getName(), null, null, null, null, null, null, null,
								"Not-Initilized", Constants.ZEBRA_VERSION);
						setState(State.ERROR);
					}
				}

				// else {
				// configTable.row(name, ds.getClass(), null, null, null, null, null, null, null);
				// }
			}
		}

		configTable.build();
		statusTable.build();
	}

	private String narmalized(String name) {
		String[] parts = name.split("-");
		if (parts.length > 0) {
			int pos = parts[parts.length - 1].indexOf('z');
			if (pos >= 0) {
				String noramlName = "";
				for (int i = 0; i < parts.length - 1; i++) {
					noramlName += parts[i];
				}

				return noramlName;
			}
		}

		return name;
	}

	private String findByKey(List<Any> anys, String key) {
		for (Any any : anys) {
			if (any.getName().equalsIgnoreCase(key.toLowerCase())) {
				return any.getValue();
			}
		}

		return null;
	}

	@Override
	public int getOrder() {
		return 16;
	}
}