package com.dianping.zebra.monitor.inspect;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dianping.phoenix.status.AbstractComponentStatus;
import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.datasources.DataSourceState;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.monitor.GroupDataSourceMBean;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;
import com.dianping.zebra.monitor.sql.MonitorableDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceStatus extends AbstractComponentStatus {

	public static final String ID = "dal.datasource";

	public DataSourceStatus() {
		super(ID, "Data Source Status");
	}

	@Override
	protected void build(ServletContext ctx) throws Exception {
		TableBuilder configTable = newTable();
		TableBuilder statusTable = newTable();

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(ctx);
		@SuppressWarnings("unchecked")
		Map<String, DataSource> beans = context.getBeansOfType(DataSource.class);

		configTable.caption("Group DataSource Config");
		configTable.header("Name", "Type", "Url", "Username", "InitialPoolSize", "MaxPoolSize", "MinPoolSize",
		      "CheckoutTimeout", "DalVersion");
		statusTable.caption("Group DataSource Status");
		statusTable.header("Name", "Url", "BusyConnection", "IdleConnection", "IsMaster", "Weight", "Status");

		for (Entry<String, DataSource> entry : beans.entrySet()) {
			String name = entry.getKey();
			DataSource ds = entry.getValue();

			if (ds instanceof MonitorableDataSource) {
				// ds = ((MonitorableDataSource) ds).getInnerDataSource();
				continue;
			}

			if (ds instanceof ComboPooledDataSource) {
				configTable.row(name, ComboPooledDataSource.class, ((ComboPooledDataSource) ds).getJdbcUrl(),
				      ((ComboPooledDataSource) ds).getUser(), ((ComboPooledDataSource) ds).getInitialPoolSize(),
				      ((ComboPooledDataSource) ds).getMaxPoolSize(), ((ComboPooledDataSource) ds).getMinPoolSize(),
				      ((ComboPooledDataSource) ds).getCheckoutTimeout(), null);
				statusTable.row(name, ((ComboPooledDataSource) ds).getJdbcUrl(),
				      ((ComboPooledDataSource) ds).getNumBusyConnections(),
				      ((ComboPooledDataSource) ds).getNumIdleConnections(), null, null);
			} else if (ds instanceof GroupDataSourceMBean) {
				try {
					SingleDataSourceMBean masterBean = ((GroupDataSourceMBean) ds).getWriteSingleDataSourceMBean();
					DataSourceConfig config = masterBean.getConfig();
					configTable.row(name, GroupDataSource.class, config.getJdbcUrl(), config.getUsername(),
					      findByKey(config.getProperties(), "InitialPoolSize"),
					      findByKey(config.getProperties(), "MaxPoolSize"),
					      findByKey(config.getProperties(), "MinPoolSize"),
					      findByKey(config.getProperties(), "CheckoutTimeout"), Constants.ZEBRA_VERSION);
					statusTable.row(name, config.getJdbcUrl(),
					      masterBean.getState() == DataSourceState.UP ? masterBean.getNumBusyConnection() : 0,
					      masterBean.getState() == DataSourceState.UP ? masterBean.getNumIdleConnection() : 0, "Yes", null,
					      masterBean.getState());

					for (SingleDataSourceMBean mbean : ((GroupDataSourceMBean) ds).getReaderSingleDataSourceMBean().values()) {
						config = mbean.getConfig();
						configTable.row(null, null, config.getJdbcUrl(), config.getUsername(),
						      findByKey(config.getProperties(), "InitialPoolSize"),
						      findByKey(config.getProperties(), "MaxPoolSize"),
						      findByKey(config.getProperties(), "MinPoolSize"),
						      findByKey(config.getProperties(), "CheckoutTimeout"), Constants.ZEBRA_VERSION);
						statusTable.row(null, config.getJdbcUrl(),
						      mbean.getState() == DataSourceState.UP ? mbean.getNumBusyConnection() : 0,
						      mbean.getState() == DataSourceState.UP ? mbean.getNumIdleConnection() : 0, "No", mbean
						            .getConfig().getWeight(), mbean.getState());
					}
				} catch (Exception e) {
					configTable.row(name, GroupDataSource.class, null, null, null, null, null, null, null, "Not-Initilized",
					      Constants.ZEBRA_VERSION);
					setState(State.ERROR);
				}
			} else {
				configTable.row(name, ds.getClass(), null, null, null, null, null, null, null, null, null);
			}
		}

		configTable.build();
		statusTable.build();
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
