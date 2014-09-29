package com.dianping.zebra.group.filter;

import com.dianping.zebra.group.monitor.GroupDataSourceMBean;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;
import com.dianping.zebra.group.util.StringUtils;
import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.SQLParser;
import com.foundationdb.sql.parser.StatementNode;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dozer on 9/2/14.
 * Filters read metadata from this class
 */
public class JdbcMetaData implements Cloneable {

	private static final Logger log = LogManager.getLogger(JdbcMetaData.class);

	private List<StatementNode> batchedNode;

	private List<String> batchedSqls;

	private Connection connection;

	private DataSource dataSource;

	private String dataSourceId;

	private boolean isBatch;

	private boolean isPrepared;

	private boolean isTransaction;

	private String jdbcPassword;

	private String jdbcUrl;

	private String jdbcUsername;

	private StatementNode node;

	private Object params;

	private LinkedHashMap<String, Object> properties = new LinkedHashMap<String, Object>();

	private JdbcMetaData realJdbcMetaData;

	private String sql;

	public JdbcMetaData clone() {
		try {
			JdbcMetaData result = (JdbcMetaData) super.clone();
			result.properties = (LinkedHashMap<String, Object>) this.properties.clone();
			return result;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public List<StatementNode> getBatchedNode() {
		return batchedNode;
	}

	public void setBatchedNode(List<StatementNode> batchedNode) {
		this.batchedNode = batchedNode;
	}

	public List<String> getBatchedSqls() {
		return batchedSqls;
	}

	public void setBatchedSqls(List<String> batchedSqls) {
		this.batchedSqls = batchedSqls;
		if (batchedSqls != null) {
			this.batchedNode = new ArrayList<StatementNode>();
			StringBuffer sb = new StringBuffer();
			for (String sql : batchedSqls) {
				sb.append(sql);
				if (!sql.endsWith(";")) {
					sb.append(";");
				}
			}

			try {
				this.sql = sb.toString();
				this.batchedNode = new SQLParser().parseStatements(sb.toString());
			} catch (StandardException e) {
			}
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
	}

	public StatementNode getNode() {
		return node;
	}

	public void setNode(StatementNode node) {
		this.node = node;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public JdbcMetaData getRealJdbcMetaData() {
		return realJdbcMetaData;
	}

	public void setRealJdbcMetaData(JdbcMetaData realJdbcMetaData) {
		this.realJdbcMetaData = realJdbcMetaData;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;

		if (StringUtils.isBlank(sql)) {
			return;
		}

		try {
			node = new SQLParser().parseStatement(sql);
		} catch (StandardException e) {
			log.warn(e.getMessage(), e);
		}
	}

	public boolean isBatch() {
		return isBatch;
	}

	public void setBatch(boolean isBatch) {
		this.isBatch = isBatch;
	}

	public boolean isPrepared() {
		return isPrepared;
	}

	public void setPrepared(boolean isPrepared) {
		this.isPrepared = isPrepared;
	}

	public boolean isTransaction() {
		return isTransaction;
	}

	public void setTransaction(boolean isTransaction) {
		this.isTransaction = isTransaction;
	}

	public void setDataSourceProperties(DataSource dataSource) {
		if (dataSource instanceof GroupDataSourceMBean) {
			GroupDataSourceMBean ds = (GroupDataSourceMBean) dataSource;
			properties.put("AllDataSource",
					StringUtils.joinCollectionToString(ds.getConfig().getDataSourceConfigs().keySet(), ","));
		} else if (dataSource instanceof SingleDataSourceMBean) {
			SingleDataSourceMBean ds = (SingleDataSourceMBean) dataSource;
			properties.put("JdbcUrl", ds.getConfig().getJdbcUrl());
			properties.put("Username", ds.getConfig().getUsername());
			properties.put("Password",
					ds.getConfig().getPassword() != null ?
							StringUtils.repeat("*", ds.getConfig().getPassword().length()) :
							null);
			properties.put("DriverClass", ds.getConfig().getDriverClass());
			properties.put("CanRead", ds.getConfig().isCanRead());
			properties.put("CanWrite", ds.getConfig().isCanWrite());
			properties.put("Weight", ds.getConfig().getWeight());
		}
	}
}
