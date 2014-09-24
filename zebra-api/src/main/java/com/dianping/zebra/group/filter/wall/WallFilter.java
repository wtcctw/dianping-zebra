package com.dianping.zebra.group.filter.wall;

import com.dianping.zebra.group.filter.DefaultJdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;
import com.dianping.zebra.group.filter.delegate.FilterFunction;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dozer on 9/24/14.
 */
public class WallFilter extends DefaultJdbcFilter {
	protected final static int MAX_LENGTH = 5;

	protected static String sha1(String input) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < Math.min(MAX_LENGTH, result.length); i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	protected String addIdToSql(String sql) {
		try {
			return String.format("%s/*%s*/", sql, generateId(sql));
		} catch (NoSuchAlgorithmException e) {
			return sql;
		}
	}

	@Override public <S, T> T execute(JdbcMetaData metaData, S source, FilterFunctionWithSQLException<S, T> action)
			throws SQLException {
		System.out.println(metaData.getSql());
		return super.execute(metaData, source, action);
	}

	protected String generateId(String sql) throws NoSuchAlgorithmException {
		return sha1(sql);
	}

	protected String getIdFromSQL(String sql) {
		Pattern pattern = Pattern.compile("(.*)(\\/\\*)([a-zA-Z0-9]{10})(\\*\\/$)");
		Matcher matcher = pattern.matcher(sql);
		if (matcher.matches()) {
			return matcher.group(3);
		} else {
			return null;
		}
	}

	public int getOrder() {
		return MIN_ORDER;
	}

	@Override public <S> String sql(JdbcMetaData metaData, S source, FilterFunction<S, String> action) {
		String result = super.sql(metaData, source, action);
		result = addIdToSql(result);
		return result;
	}
}