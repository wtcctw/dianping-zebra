package com.dianping.zebra.group.filter;

import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.SQLParser;
import com.foundationdb.sql.parser.StatementNode;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Dozer on 9/2/14.
 * Filters read metadata from this class
 */
public class JdbcMetaData implements Cloneable {
	private final static SQLParser parser = new SQLParser();

	private List<StatementNode> batchedNode;

	private List<String> batchedSqls;

	private Connection connection;

	private DataSource dataSource;

	private String dataSourceId;

	private String jdbcPassword;

	private String jdbcRef;

	private String jdbcUrl;

	private String jdbcUsername;

	private StatementNode node;

	private Object params;

	private Properties properties;

	private JdbcMetaData realJdbcMetaData;

	private String sql;

	public JdbcMetaData clone() {
		try {
			return (JdbcMetaData) super.clone();
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
				this.batchedNode = parser.parseStatements(sb.toString());
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

	public String getJdbcRef() {
		return jdbcRef;
	}

	public void setJdbcRef(String jdbcRef) {
		this.jdbcRef = jdbcRef;
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

	public Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
		}
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
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

		try {
			node = parser.parseStatement(sql);
		} catch (StandardException e) {
		}
	}

	@Override public String toString() {
		return "JdbcMetaData{" +
				"batchedSqls=" + batchedSqls +
				", connection=" + connection +
				", dataSource=" + dataSource +
				", dataSourceId='" + dataSourceId + '\'' +
				", jdbcPassword='" + jdbcPassword + '\'' +
				", jdbcRef='" + jdbcRef + '\'' +
				", jdbcUrl='" + jdbcUrl + '\'' +
				", jdbcUsername='" + jdbcUsername + '\'' +
				", params=" + params +
				", properties=" + properties +
				", sql='" + sql + '\'' +
				'}';
	}
}
