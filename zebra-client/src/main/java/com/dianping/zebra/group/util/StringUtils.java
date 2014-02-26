package com.dianping.zebra.group.util;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author Leo Liang
 * @author danson.liu
 * 
 */
public class StringUtils {

	private static final String EMPTY = "";

	private static final int PAD_LIMIT = 8192;

	public static boolean endsWithIgnoreCase(String s, String suffix) {
		return s.toLowerCase().trim().endsWith(suffix.toLowerCase());
	}

	/**
	 * <pre>
	 * Determines whether or not the sting 'searchIn' contains the string
	 * 'searchFor', disregarding case and leading whitespace
	 * 
	 * From mysql connector-j
	 * </pre>
	 * 
	 * @param searchIn
	 *           the string to search in
	 * @param searchFor
	 *           the string to search for
	 * 
	 * @return true if the string starts with 'searchFor' ignoring whitespace
	 */
	public static boolean startsWithIgnoreCaseAndWs(String searchIn, String searchFor) {
		return startsWithIgnoreCaseAndWs(searchIn, searchFor, 0);
	}

	/**
	 * <pre>
	 * Determines whether or not the sting 'searchIn' contains the string
	 * 'searchFor', disregarding case and leading whitespace
	 * 
	 * From mysql connector-j
	 * </pre>
	 * 
	 * @param searchIn
	 *           the string to search in
	 * @param searchFor
	 *           the string to search for
	 * @param beginPos
	 *           where to start searching
	 * 
	 * @return true if the string starts with 'searchFor' ignoring whitespace
	 */

	public static boolean startsWithIgnoreCaseAndWs(String searchIn, String searchFor, int beginPos) {
		if (searchIn == null) {
			return searchFor == null;
		}

		int inLength = searchIn.length();

		for (; beginPos < inLength; beginPos++) {
			if (!Character.isWhitespace(searchIn.charAt(beginPos))) {
				break;
			}
		}

		return startsWithIgnoreCase(searchIn, beginPos, searchFor);
	}

	/**
	 * <pre>
	 * Determines whether or not the string 'searchIn' contains the string
	 * 'searchFor', dis-regarding case starting at 'startAt' Shorthand for a
	 * String.regionMatch(...)
	 * 
	 * From mysql connector-j
	 * </pre>
	 * 
	 * @param searchIn
	 *           the string to search in
	 * @param startAt
	 *           the position to start at
	 * @param searchFor
	 *           the string to search for
	 * 
	 * @return whether searchIn starts with searchFor, ignoring case
	 */
	public static boolean startsWithIgnoreCase(String searchIn, int startAt, String searchFor) {
		return searchIn.regionMatches(true, startAt, searchFor, 0, searchFor.length());
	}

	/**
	 * <pre>
	 * Determines whether or not the string 'searchIn' contains the string
	 * 'searchFor', dis-regarding case. Shorthand for a String.regionMatch(...)
	 * 
	 * From mysql connector-j
	 * </pre>
	 * 
	 * @param searchIn
	 *           the string to search in
	 * @param searchFor
	 *           the string to search for
	 * 
	 * @return whether searchIn starts with searchFor, ignoring case
	 */
	public static boolean startsWithIgnoreCase(String searchIn, String searchFor) {
		return startsWithIgnoreCase(searchIn, 0, searchFor);
	}

	/**
	 * <pre>
	 * Returns the given string, with comments removed
	 * 
	 * From mysql connector-j
	 * </pre>
	 * 
	 * @param src
	 *           the source string
	 * @param stringOpens
	 *           characters which delimit the "open" of a string
	 * @param stringCloses
	 *           characters which delimit the "close" of a string, in counterpart order to <code>stringOpens</code>
	 * @param slashStarComments
	 *           strip slash-star type "C" style comments
	 * @param slashSlashComments
	 *           strip slash-slash C++ style comments to end-of-line
	 * @param hashComments
	 *           strip #-style comments to end-of-line
	 * @param dashDashComments
	 *           strip "--" style comments to end-of-line
	 * @return the input string with all comment-delimited data removed
	 */
	public static String stripComments(String src, String stringOpens, String stringCloses, boolean slashStarComments,
	      boolean slashSlashComments, boolean hashComments, boolean dashDashComments) {
		if (src == null) {
			return null;
		}

		StringBuffer buf = new StringBuffer(src.length());

		// It's just more natural to deal with this as a stream
		// when parsing..This code is currently only called when
		// parsing the kind of metadata that developers are strongly
		// recommended to cache anyways, so we're not worried
		// about the _1_ extra object allocation if it cleans
		// up the code

		StringReader sourceReader = new StringReader(src);

		int contextMarker = Character.MIN_VALUE;
		boolean escaped = false;
		int markerTypeFound = -1;

		int ind = 0;

		int currentChar = 0;

		try {
			while ((currentChar = sourceReader.read()) != -1) {

				if (markerTypeFound != -1 && currentChar == stringCloses.charAt(markerTypeFound) && !escaped) {
					contextMarker = Character.MIN_VALUE;
					markerTypeFound = -1;
				} else if ((ind = stringOpens.indexOf(currentChar)) != -1 && !escaped
				      && contextMarker == Character.MIN_VALUE) {
					markerTypeFound = ind;
					contextMarker = currentChar;
				}

				if (contextMarker == Character.MIN_VALUE && currentChar == '/' && (slashSlashComments || slashStarComments)) {
					currentChar = sourceReader.read();
					if (currentChar == '*' && slashStarComments) {
						int prevChar = 0;
						while ((currentChar = sourceReader.read()) != '/' || prevChar != '*') {
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
						while ((currentChar = sourceReader.read()) != '\n' && currentChar != '\r' && currentChar >= 0)
							;
					}
				} else if (contextMarker == Character.MIN_VALUE && currentChar == '#' && hashComments) {
					// Slurp up everything until the newline
					while ((currentChar = sourceReader.read()) != '\n' && currentChar != '\r' && currentChar >= 0)
						;
				} else if (contextMarker == Character.MIN_VALUE && currentChar == '-' && dashDashComments) {
					currentChar = sourceReader.read();

					if (currentChar == -1 || currentChar != '-') {
						buf.append('-');

						if (currentChar != -1) {
							buf.append((char) currentChar);
						}

						continue;
					}

					// Slurp up everything until the newline

					while ((currentChar = sourceReader.read()) != '\n' && currentChar != '\r' && currentChar >= 0)
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

	public static String substringBefore(String str, String separator) {
		if (isEmpty(str) || separator == null) {
			return str;
		}
		if (separator.length() == 0) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	public static String substringAfter(String str, String separator) {
		if (isEmpty(str)) {
			return str;
		}
		if (separator == null) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return EMPTY;
		}
		return str.substring(pos + separator.length());
	}

	public static String substringBetween(String str, String open, String close) {
		if (str == null || open == null || close == null) {
			return null;
		}
		int start = str.indexOf(open);
		if (start != -1) {
			int end = str.indexOf(close, start + open.length());
			if (end != -1) {
				return str.substring(start + open.length(), end);
			}
		}
		return null;
	}

	public static String substringAfterLast(String str, String separator) {
		if (isEmpty(str)) {
			return str;
		}
		if (isEmpty(separator)) {
			return EMPTY;
		}
		int pos = str.lastIndexOf(separator);
		if (pos == -1 || pos == (str.length() - separator.length())) {
			return EMPTY;
		}
		return str.substring(pos + separator.length());
	}

	public static String substringBeforeLast(String str, String separator) {
		if (isEmpty(str) || isEmpty(separator)) {
			return str;
		}
		int pos = str.lastIndexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static String substring(String str, int start) {
		if (str == null) {
			return null;
		}

		// handle negatives, which means last n characters
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return EMPTY;
		}

		return str.substring(start);
	}

	public static String leftPad(String str, int size, char padChar) {
		if (str == null) {
			return null;
		}
		int pads = size - str.length();
		if (pads <= 0) {
			return str; // returns original String when possible
		}
		if (pads > PAD_LIMIT) {
			return leftPad(str, size, String.valueOf(padChar));
		}
		return padding(pads, padChar).concat(str);
	}

	public static String leftPad(String str, int size, String padStr) {
		if (str == null) {
			return null;
		}
		if (isEmpty(padStr)) {
			padStr = " ";
		}
		int padLen = padStr.length();
		int strLen = str.length();
		int pads = size - strLen;
		if (pads <= 0) {
			return str; // returns original String when possible
		}
		if (padLen == 1 && pads <= PAD_LIMIT) {
			return leftPad(str, size, padStr.charAt(0));
		}

		if (pads == padLen) {
			return padStr.concat(str);
		} else if (pads < padLen) {
			return padStr.substring(0, pads).concat(str);
		} else {
			char[] padding = new char[pads];
			char[] padChars = padStr.toCharArray();
			for (int i = 0; i < pads; i++) {
				padding[i] = padChars[i % padLen];
			}
			return new String(padding).concat(str);
		}
	}

	private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
		if (repeat < 0) {
			throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
		}
		final char[] buf = new char[repeat];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = padChar;
		}
		return new String(buf);
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static void main(String[] args) {
		String sql = "select * from \rTorder \nwhere a = b \r\nand c = 1";
		System.out.println(StringUtils.stripComments(sql, "'\"", "'\"", true, false, true, true).trim());
	}

}
