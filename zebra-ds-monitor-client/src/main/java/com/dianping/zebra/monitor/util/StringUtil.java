/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-11-5
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.monitor.util;

import java.io.IOException;
import java.io.StringReader;

/**
 * 
 * @author danson.liu
 * 
 */
public class StringUtil {

	public static String stripComments(String src, String stringOpens,
			String stringCloses, boolean slashStarComments,
			boolean slashSlashComments, boolean hashComments,
			boolean dashDashComments) {
		if (src == null) {
			return null;
		}

		StringBuffer buf = new StringBuffer(src.length());
		StringReader sourceReader = new StringReader(src);

		int contextMarker = Character.MIN_VALUE;
		boolean escaped = false;
		int markerTypeFound = -1;

		int ind = 0;

		int currentChar = 0;

		try {
			while ((currentChar = sourceReader.read()) != -1) {

				if (markerTypeFound != -1
						&& currentChar == stringCloses.charAt(markerTypeFound)
						&& !escaped) {
					contextMarker = Character.MIN_VALUE;
					markerTypeFound = -1;
				} else if ((ind = stringOpens.indexOf(currentChar)) != -1
						&& !escaped && contextMarker == Character.MIN_VALUE) {
					markerTypeFound = ind;
					contextMarker = currentChar;
				}

				if (contextMarker == Character.MIN_VALUE && currentChar == '/'
						&& (slashSlashComments || slashStarComments)) {
					currentChar = sourceReader.read();
					if (currentChar == '*' && slashStarComments) {
						int prevChar = 0;
						while ((currentChar = sourceReader.read()) != '/'
								|| prevChar != '*') {
							if (currentChar == '\r') {

								currentChar = sourceReader.read();
								if (currentChar == '\n') {
									currentChar = sourceReader.read();
								}
							} else {
								if (currentChar == '\n') {

									currentChar = sourceReader.read();
								}
							}
							if (currentChar < 0)
								break;
							prevChar = currentChar;
						}
						continue;
					} else if (currentChar == '/' && slashSlashComments) {
						while ((currentChar = sourceReader.read()) != '\n'
								&& currentChar != '\r' && currentChar >= 0)
							;
					}
				} else if (contextMarker == Character.MIN_VALUE
						&& currentChar == '#' && hashComments) {
					// Slurp up everything until the newline
					while ((currentChar = sourceReader.read()) != '\n'
							&& currentChar != '\r' && currentChar >= 0)
						;
				} else if (contextMarker == Character.MIN_VALUE
						&& currentChar == '-' && dashDashComments) {
					currentChar = sourceReader.read();

					if (currentChar == -1 || currentChar != '-') {
						buf.append('-');

						if (currentChar != -1) {
							buf.append((char) currentChar);
						}

						continue;
					}

					// Slurp up everything until the newline

					while ((currentChar = sourceReader.read()) != '\n'
							&& currentChar != '\r' && currentChar >= 0)
						;
				}

				if (currentChar != -1) {
					buf.append((char) currentChar);
				}
			}
		} catch (IOException ioEx) {
			// we'll never see this from a StringReader
		}

		return buf.toString();
	}
	
	public static String formatSql(String sql) {
		if (sql == null) {
			return null;
		}

		StringBuilder buf = new StringBuilder(sql.length());
		StringReader sourceReader = new StringReader(sql.trim());
		int currentChar = 0;
		boolean lastIsSpaceNotInQuote = false;
		boolean inQuote = false;
		int lastQuote = 0;
		try {
			while ((currentChar = sourceReader.read()) != -1) {
				if (currentChar == '\'' || currentChar == '"') {
					if (inQuote) {
						if (lastQuote == currentChar) {
							inQuote = false;
						}
					} else {
						inQuote = true;
						lastQuote = currentChar;
					}
					buf.append((char) currentChar);
					lastIsSpaceNotInQuote = false;
				} else {
					if (!inQuote && (currentChar == '\t' || currentChar == '\n'
						|| currentChar == '\r' || currentChar == ' ')) {
						if (!lastIsSpaceNotInQuote) {
							buf.append(' ');
							lastIsSpaceNotInQuote = true;
						}
					} else {
						lastIsSpaceNotInQuote = false;
						buf.append((char) currentChar);
					}
				}
			}
		} catch (IOException e) {
		}
		return buf.toString();
	}
	
}
