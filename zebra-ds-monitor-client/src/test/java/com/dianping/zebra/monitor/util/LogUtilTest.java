package com.dianping.zebra.monitor.util;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class LogUtilTest {

	private static final String ERR_MSG = "error_foo";
	private static final RuntimeException THROWABLE = new RuntimeException(ERR_MSG);
	
	@Test
	public void testLogMonitorError() {
		PrintStream oldSysout = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream newSysout = new PrintStream(baos, true);
		try {
			System.setOut(newSysout);
			skip2NextLogTime(baos);
			LogUtil.logMonitorError(THROWABLE);
			String logContent = new String(baos.toByteArray());
			int firstErrIndex = logContent.indexOf(ERR_MSG);
			assertTrue(firstErrIndex != -1);
			int latestErrIndex = 0;
			for (int i = 0; i < LogUtil.LOG_FACTOR_ROUND_VALUE - 1; i++) {
				LogUtil.logMonitorError(THROWABLE);
				logContent = new String(baos.toByteArray());
				latestErrIndex = logContent.indexOf(ERR_MSG, firstErrIndex + 1);
				assertTrue(latestErrIndex == -1);
			}
			LogUtil.logMonitorError(THROWABLE);
			logContent = new String(baos.toByteArray());
			latestErrIndex = logContent.indexOf(ERR_MSG, firstErrIndex + 1);
			assertTrue(latestErrIndex != -1);
		} finally {
			System.setOut(oldSysout);
			newSysout.close();
		}
	}

	private void skip2NextLogTime(ByteArrayOutputStream baos) {
		boolean loged = false;
		try {
			LogUtil.logMonitorError(THROWABLE);
			while (!loged) {
				String logContent = new String(baos.toByteArray());
				loged = logContent.contains(ERR_MSG);
			}
			for (int i = 0; i < LogUtil.LOG_FACTOR_ROUND_VALUE - 1; i++) {
				LogUtil.logMonitorError(THROWABLE);
			}
		} finally {
			baos.reset();
		}
	}

}
