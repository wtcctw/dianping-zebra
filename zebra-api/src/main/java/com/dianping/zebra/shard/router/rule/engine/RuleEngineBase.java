package com.dianping.zebra.shard.router.rule.engine;

import java.io.UnsupportedEncodingException;
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

	public long crc32(Object str, String encode) throws UnsupportedEncodingException {
		CRC32 crc32 = new CRC32();
		crc32.update(String.valueOf(str).getBytes(encode));
		return crc32.getValue();
	}
}
