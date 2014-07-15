package com.dianping.zebra.group.datasources;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Properties;

import javax.sql.DataSource;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.DalException;
import com.dianping.zebra.group.util.SmoothReload;
import com.dianping.zebra.group.util.StringUtils;

/**
 * @author Dozer <br/>
 *         1.实现了自动 reload 机制。 <br/>
 *         2.实现了帐号密码原子修改，只改帐号或者密码不会触发重新加载流程，只有两个同时修改后，才会一次性触发重新加载。
 */
public class SingleDataSourceC3P0Adapter implements DataSource {
	private AtomicRefresh atomicRefresh = new AtomicRefresh();

	private DataSourceConfig config = new DataSourceConfig();

	private volatile DataSource innerDs;

	public SingleDataSourceC3P0Adapter(String id) {
		// todo: resolve from lion
		config.setId(id);
		config.setActive(true);
		config.setCanRead(true);
		config.setCanWrite(true);
	}

	public void close() {
		destoryInnerDs();
	}

	private void destoryInnerDs() {
		Transaction t = Cat.newTransaction("DAL.Adapter", "Destory");
		SingleDataSourceManagerFactory.getDataSourceManager().destoryDataSource(config.getId(), null);
		t.setStatus(Message.SUCCESS);
		t.complete();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getInnerDs().getConnection();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return getInnerDs().getConnection(username, password);
	}

	private DataSource getInnerDs() {
		if (innerDs == null) {
			innerDs = initInnerDs();
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

	private DataSource initInnerDs() {
		Transaction t = Cat.newTransaction("DAL.Adapter", "Init");
		try {
			try {
				Class.forName(config.getDriverClass());
			} catch (ClassNotFoundException ex) {
				throw new DalException("Cannot find mysql driver class[com.mysql.jdbc.Driver]", ex);
			}

			DataSource ds = SingleDataSourceManagerFactory.getDataSourceManager().createDataSource(null, config);
			t.setStatus(Message.SUCCESS);
			return ds;
		} catch (Exception e) {
			Cat.logError(e);
			t.setStatus(e);
			return null;
		} finally {
			t.complete();
		}
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		return getInnerDs().isWrapperFor(arg0);
	}

	private void refresh() {
		if (innerDs == null) {
			return;
		}

		Transaction t = Cat.newTransaction("DAL.Adapter", "Refresh");

		innerDs = initInnerDs();
		destoryInnerDs();

		t.setStatus(Message.SUCCESS);
		t.complete();
	}

	private void refreshUserAndPassword() {
		Transaction t = Cat.newTransaction("DAL.Adapter", "RefreshUser");
		config.setUser(atomicRefresh.getNewUser());
		config.setPassword(atomicRefresh.getNewPassword());
		atomicRefresh.reset();

		// todo:平滑切换
		SmoothReload sr = new SmoothReload();
		sr.waitForReload();

		refresh();
		t.setStatus(Message.SUCCESS);
		t.complete();
	}

	public void setAcquireIncrement(int acquireIncrement) {
		setProperty("acquireIncrement", String.valueOf(acquireIncrement));
	}

	public void setAcquireRetryAttempts(int acquireRetryAttempts) {
		setProperty("acquireRetryAttempts", String.valueOf(acquireRetryAttempts));
	}

	public void setAcquireRetryDelay(int acquireRetryDelay) {
		setProperty("acquireRetryDelay", String.valueOf(acquireRetryDelay));
	}

	public void setAutoCommitOnClose(boolean autoCommitOnClose) {
		setProperty("autoCommitOnClose", String.valueOf(autoCommitOnClose));
	}

	public void setAutomaticTestTable(String automaticTestTable) {
		setProperty("automaticTestTable", automaticTestTable);
	}

	public void setBreakAfterAcquireFailure(boolean breakAfterAcquireFailure) {
		setProperty("breakAfterAcquireFailure", String.valueOf(breakAfterAcquireFailure));
	}

	public void setCheckoutTimeout(int checkoutTimeout) {
		config.setCheckoutTimeout(checkoutTimeout);
		refresh();
	}

	public void setConnectionCustomizerClassName(String connectionCustomizerClassName) {
		setProperty("connectionCustomizerClassName", connectionCustomizerClassName);
	}

	public void setConnectionTesterClassName(String connectionTesterClassName) {
		setProperty("connectionTesterClassName", connectionTesterClassName);
	}

	public void setDataSourceName(String dataSourceName) {
		setProperty("dataSourceName", dataSourceName);
	}

	public void setDebugUnreturnedConnectionStackTraces(boolean debugUnreturnedConnectionStackTraces) {
		setProperty("debugUnreturnedConnectionStackTraces", String.valueOf(debugUnreturnedConnectionStackTraces));
	}

	public void setDescription(String description) {
		setProperty("description", description);
	}

	public void setDriverClass(String driverClass) {
		config.setDriverClass(driverClass);
		refresh();
	}

	public void setFactoryClassLocation(String factoryClassLocation) {
		setProperty("factoryClassLocation", factoryClassLocation);
	}

	public void setForceIgnoreUnresolvedTransactions(boolean forceIgnoreUnresolvedTransactions) {
		setProperty("forceIgnoreUnresolvedTransactions", String.valueOf(forceIgnoreUnresolvedTransactions));
	}

	public void setIdleConnectionTestPeriod(int idleConnectionTestPeriod) {
		setProperty("idleConnectionTestPeriod", String.valueOf(idleConnectionTestPeriod));
	}

	public void setInitialPoolSize(int initialPoolSize) {
		config.setInitialPoolSize(initialPoolSize);
		refresh();
	}

	public void setJdbcUrl(String jdbcUrl) {
		config.setJdbcUrl(jdbcUrl);
		refresh();
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
		setProperty("maxAdministrativeTaskTime", String.valueOf(maxAdministrativeTaskTime));
	}

	public void setMaxConnectionAge(int maxConnectionAge) {
		setProperty("maxConnectionAge", String.valueOf(maxConnectionAge));
	}

	public void setMaxIdleTime(int maxIdleTime) {
		setProperty("maxIdleTime", String.valueOf(maxIdleTime));
	}

	public void setMaxIdleTimeExcessConnections(int maxIdleTimeExcessConnections) {
		setProperty("maxIdleTimeExcessConnections", String.valueOf(maxIdleTimeExcessConnections));
	}

	public void setMaxPoolSize(int maxPoolSize) {
		config.setMaxPoolSize(maxPoolSize);
		refresh();
	}

	public void setMaxStatements(int maxStatements) {
		setProperty("maxStatements", String.valueOf(maxStatements));
	}

	public void setMaxStatementsPerConnection(int maxStatementsPerConnection) {
		setProperty("maxStatementsPerConnection", String.valueOf(maxStatementsPerConnection));
	}

	public void setMinPoolSize(int minPoolSize) {
		config.setMinPoolSize(minPoolSize);
		refresh();
	}

	public void setNumHelperThreads(int numHelperThreads) {
		setProperty("numHelperThreads", String.valueOf(numHelperThreads));
	}

	public void setOverrideDefaultPassword(String overrideDefaultPassword) {
		setProperty("overrideDefaultPassword", overrideDefaultPassword);
	}

	public void setOverrideDefaultUser(String overrideDefaultUser) {
		setProperty("overrideDefaultUser", overrideDefaultUser);
	}

	public synchronized void setPassword(String password) {
		// innerDs 未加载的时候不需要 reload 逻辑
		if (innerDs == null) {
			config.setPassword(password);
			return;
		}

		if (atomicRefresh.setPassword(password)) {
			refreshUserAndPassword();
		}
	}

	public void setPreferredTestQuery(String preferredTestQuery) {
		setProperty("preferredTestQuery", preferredTestQuery);
	}

	public void setProperties(Properties properties) {
		for (Entry<Object, Object> item : properties.entrySet()) {
			setProperty(String.valueOf(item.getKey().toString()), String.valueOf(item.getValue()));
		}
	}

	private void setProperty(String name, String value) {
		Any any = null;
		for (Any a : config.getProperties()) {
			if (a.getName().equals(name)) {
				any = a;
				break;
			}
		}

		if (any == null) {
			any = new Any();
			config.getProperties().add(any);
		}

		any.setName(name);
		any.setValue(value);

		refresh();
	}

	public void setPropertyCycle(int propertyCycle) {
		setProperty("propertyCycle", String.valueOf(propertyCycle));
	}

	public void setTestConnectionOnCheckin(boolean testConnectionOnCheckin) {
		setProperty("testConnectionOnCheckin", String.valueOf(testConnectionOnCheckin));
	}

	public void setTestConnectionOnCheckout(boolean testConnectionOnCheckout) {
		setProperty("testConnectionOnCheckout", String.valueOf(testConnectionOnCheckout));
	}

	public void setUnreturnedConnectionTimeout(int unreturnedConnectionTimeout) {
		setProperty("unreturnedConnectionTimeout", String.valueOf(unreturnedConnectionTimeout));
	}

	public synchronized void setUser(String user) {
		// innerDs 未加载的时候不需要 reload 逻辑
		if (innerDs == null) {
			config.setUser(user);
			return;
		}

		if (atomicRefresh.setUser(user)) {
			refreshUserAndPassword();
		}
	}

	public void setUserOverridesAsString(String userOverridesAsString) {
		setProperty("userOverridesAsString", userOverridesAsString);
	}

	public void setUsesTraditionalReflectiveProxies(boolean usesTraditionalReflectiveProxies) {
		setProperty("usesTraditionalReflectiveProxies", String.valueOf(usesTraditionalReflectiveProxies));
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		return getInnerDs().unwrap(arg0);
	}

	public static class AtomicRefresh {
		private String newPassword;

		private String newUser;

		private String oldPassword;

		private String oldUser;

		public String getNewPassword() {
			return newPassword;
		}

		public String getNewUser() {
			return newUser;
		}

		private boolean needToRefresh() {
			// 帐号和密码都改了，就需要 refresh
			return (!StringUtils.equals(newUser, oldUser)) && (!StringUtils.equals(newPassword, oldPassword));
		}

		public void reset() {
			oldPassword = newPassword;
			oldUser = newUser;
		}

		public boolean setPassword(String password) {
			this.newPassword = password;
			return needToRefresh();
		}

		public boolean setUser(String user) {
			this.newUser = user;
			return needToRefresh();
		}
	}
}