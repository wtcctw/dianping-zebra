package com.dianping.zebra.shard.router.rule.engine;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.CRC32;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class RuleEngineBase {

	public long crc32(Object str) throws UnsupportedEncodingException {
		return crc32(str, "utf-8");
	}

	public Date date(Object value) throws ParseException {
		if (value instanceof Date) {
			return (Date) value;
		}
		if (value instanceof String) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.SIMPLIFIED_CHINESE);
			return format.parse((String) value);
		}

		throw new IllegalArgumentException();
	}

	public long crc32(Object str, String encode) throws UnsupportedEncodingException {
		CRC32 crc32 = new CRC32();
		crc32.update(String.valueOf(str).getBytes(encode));
		return crc32.getValue();
	}
}
