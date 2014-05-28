package com.dianping.zebra.group.util;

/**
 * @author kezhu.wu
 */
public enum SqlType {
	SELECT(true, 0), //
	INSERT(false, 1), //
	UPDATE(false, 2), //
	DELETE(false, 3), //
	SELECT_FOR_UPDATE(false, 4), //
	REPLACE(false, 5), //
	TRUNCATE(false, 6), //
	CREATE(false, 7), //
	DROP(false, 8), //
	LOAD(false, 9), //
	MERGE(false, 10), //
	SHOW(true, 11), //
	DEFAULT_SQL_TYPE(false, -100), //
	;

	private boolean isRead;

	private int i;

	private SqlType(boolean isRead, int i) {
		this.isRead = isRead;
		this.i = i;
	}

	public int value() {
		return this.i;
	}

	public boolean isRead() {
		return isRead;
	}

	public static SqlType valueOf(int i) {
		for (SqlType t : values()) {
			if (t.value() == i) {
				return t;
			}
		}
		throw new IllegalArgumentException("Invalid SqlType:" + i);
	}

}
