package com.dianping.zebra.group.datasources;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.mchange.v2.c3p0.DataSources;

public class SingleDataSourceC3P0Adapter implements DataSource {
	private DataSourceConfig config = new DataSourceConfig();

	private DataSource innerDs;

	public SingleDataSourceC3P0Adapter() {
		this("db1");
	}

	public SingleDataSourceC3P0Adapter(String id) {
		config.setId(id);
	}

	private void addProperty(String key, String value) {
		Any any = new Any();
		any.setName(key);
		any.setValue(value);
		config.getProperties().add(any);
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getInnerDs().getConnection();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return getInnerDs().getConnection(username, password);
	}

	private DataSource getInnerDs() throws SQLException {
		if (innerDs == null) {
			if (shouldReplace()) {
				innerDs = SingleDataSourceManagerFactory.getDataSourceManager().createDataSource(null, config);
			} else {
				DataSource unPooledDataSource;
				unPooledDataSource = DataSources.unpooledDataSource(config.getJdbcUrl(), config.getUser(),
				      config.getPassword());

				Map<String, Object> props = new HashMap<String, Object>();

				props.put("driverClass", config.getDriverClass());
				props.put("initialPoolSize", config.getInitialPoolSize());
				props.put("minPoolSize", config.getMinPoolSize());
				props.put("maxPoolSize", config.getMaxPoolSize());
				props.put("checkoutTimeout", config.getCheckoutTimeout());

				for (Any any : config.getProperties()) {
					props.put(any.getName(), any.getValue());
				}

				innerDs = DataSources.pooledDataSource(unPooledDataSource, props);
			}
		}

		return innerDs;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return getInnerDs().getLoginTimeout();
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return getInnerDs().getLogWriter();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		try {
			return getInnerDs().getParentLogger();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		return getInnerDs().isWrapperFor(arg0);
	}

	public void setAcquireIncrement(int acquireIncrement) {
		addProperty("acquireIncrement", String.valueOf(acquireIncrement));
	}

	public void setAcquireRetryAttempts(int acquireRetryAttempts) {
		addProperty("acquireRetryAttempts", String.valueOf(acquireRetryAttempts));
	}

	public void setAcquireRetryDelay(int acquireRetryDelay) {
		addProperty("acquireRetryDelay", String.valueOf(acquireRetryDelay));
	}

	public void setAutoCommitOnClose(boolean autoCommitOnClose) {
		addProperty("autoCommitOnClose", String.valueOf(autoCommitOnClose));
	}

	public void setAutomaticTestTable(String automaticTestTable) {
		addProperty("automaticTestTable", automaticTestTable);
	}

	public void setBreakAfterAcquireFailure(boolean breakAfterAcquireFailure) {
		addProperty("breakAfterAcquireFailure", String.valueOf(breakAfterAcquireFailure));
	}

	public void setCheckoutTimeout(int checkoutTimeout) {
		config.setCheckoutTimeout(checkoutTimeout);
	}

	public void setConnectionCustomizerClassName(String connectionCustomizerClassName) {
		addProperty("connectionCustomizerClassName", connectionCustomizerClassName);
	}

	public void setConnectionTesterClassName(String connectionTesterClassName) {
		addProperty("connectionTesterClassName", connectionTesterClassName);
	}

	public void setDebugUnreturnedConnectionStackTraces(boolean debugUnreturnedConnectionStackTraces) {
		addProperty("debugUnreturnedConnectionStackTraces", String.valueOf(debugUnreturnedConnectionStackTraces));
	}

	public void setDescription(String description) {
		addProperty("description", description);
	}

	public void setDriverClass(String driverClass) {
		config.setDriverClass(driverClass);
	}

	public void setFactoryClassLocation(String factoryClassLocation) {
		addProperty("factoryClassLocation", factoryClassLocation);
	}

	public void setForceIgnoreUnresolvedTransactions(boolean forceIgnoreUnresolvedTransactions) {
		addProperty("forceIgnoreUnresolvedTransactions", String.valueOf(forceIgnoreUnresolvedTransactions));
	}

	public void setIdleConnectionTestPeriod(int idleConnectionTestPeriod) {
		addProperty("idleConnectionTestPeriod", String.valueOf(idleConnectionTestPeriod));
	}

	public void setInitialPoolSize(int initialPoolSize) {
		config.setInitialPoolSize(initialPoolSize);
	}

	public void setJdbcUrl(String jdbcUrl) {
		config.setJdbcUrl(jdbcUrl);
	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		getInnerDs().setLoginTimeout(arg0);
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		getInnerDs().setLogWriter(arg0);
	}

	public void setMaxAdministrativeTaskTime(int maxAdministrativeTaskTime) {
		addProperty("maxAdministrativeTaskTime", String.valueOf(maxAdministrativeTaskTime));
	}

	public void setMaxConnectionAge(int maxConnectionAge) {
		addProperty("maxConnectionAge", String.valueOf(maxConnectionAge));
	}

	public void setMaxIdleTime(int maxIdleTime) {
		addProperty("maxIdleTime", String.valueOf(maxIdleTime));
	}

	public void setMaxIdleTimeExcessConnections(int maxIdleTimeExcessConnections) {
		addProperty("maxIdleTimeExcessConnections", String.valueOf(maxIdleTimeExcessConnections));
	}

	public void setMaxPoolSize(int maxPoolSize) {
		config.setMaxPoolSize(maxPoolSize);
	}

	public void setMaxStatements(int maxStatements) {
		addProperty("maxStatements", String.valueOf(maxStatements));
	}

	public void setMaxStatementsPerConnection(int maxStatementsPerConnection) {
		addProperty("maxStatementsPerConnection", String.valueOf(maxStatementsPerConnection));
	}

	public void setMinPoolSize(int minPoolSize) {
		config.setMinPoolSize(minPoolSize);
	}

	public void setOverrideDefaultPassword(String overrideDefaultPassword) {
		addProperty("overrideDefaultPassword", overrideDefaultPassword);
	}

	public void setOverrideDefaultUser(String overrideDefaultUser) {
		addProperty("overrideDefaultUser", overrideDefaultUser);
	}

	public void setPassword(String password) {
		config.setPassword(password);
	}

	public void setPreferredTestQuery(String preferredTestQuery) {
		addProperty("preferredTestQuery", preferredTestQuery);
	}

	public void setProperties(Properties properties) {
		for (Entry<Object, Object> item : properties.entrySet()) {
			addProperty(String.valueOf(item.getKey().toString()), String.valueOf(item.getValue()));
		}
	}

	public void setPropertyCycle(int propertyCycle) {
		addProperty("propertyCycle", String.valueOf(propertyCycle));
	}

	public void setTestConnectionOnCheckin(boolean testConnectionOnCheckin) {
		addProperty("testConnectionOnCheckin", String.valueOf(testConnectionOnCheckin));
	}

	public void setTestConnectionOnCheckout(boolean testConnectionOnCheckout) {
		addProperty("testConnectionOnCheckout", String.valueOf(testConnectionOnCheckout));
	}

	public void setUnreturnedConnectionTimeout(int unreturnedConnectionTimeout) {
		addProperty("unreturnedConnectionTimeout", String.valueOf(unreturnedConnectionTimeout));
	}

	public void setUser(String user) {
		config.setUser(user);
	}

	public void setUserOverridesAsString(String userOverridesAsString) {
		addProperty("userOverridesAsString", userOverridesAsString);
	}

	public void setUsesTraditionalReflectiveProxies(boolean usesTraditionalReflectiveProxies) {
		addProperty("usesTraditionalReflectiveProxies", String.valueOf(usesTraditionalReflectiveProxies));
	}

	private boolean shouldReplace() {
		return config.getJdbcUrl().toLowerCase().startsWith("jdbc:mysql://");
	}

	public void setNumHelperThreads(int numHelperThreads) {
		addProperty("numHelperThreads", String.valueOf(numHelperThreads));
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		return getInnerDs().unwrap(arg0);
	}

}
