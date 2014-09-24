package com.dianping.zebra.group.filter.wall;

import com.dianping.zebra.group.filter.DefaultJdbcFilter;
import com.dianping.zebra.group.filter.JdbcMetaData;
import com.dianping.zebra.group.filter.delegate.FilterFunction;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Dozer on 9/24/14.
 */
public class WallFilter extends DefaultJdbcFilter {
	private final static int MAX_LENGTH = 5;

	private static String sha1(String input) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < Math.min(MAX_LENGTH, result.length); i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	private String addIdToSql(String sql) {
		try {
			return String.format("%s/*%s*/", sql, generateId(sql));
		} catch (NoSuchAlgorithmException e) {
			return sql;
		}
	}

	private String generateId(String sql) throws NoSuchAlgorithmException {
		return sha1(sql);
	}

	public int getOrder() {
		return MIN_ORDER;
	}

	@Override public <S> String sql(JdbcMetaData metaData, S source, FilterFunction<S, String> action) {
		String result = super.sql(metaData, source, action);

		result = addIdToSql(result);

		System.out.println(result);

		return result;
	}
}