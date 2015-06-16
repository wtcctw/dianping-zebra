package com.dianping.zebra.admin.util;

import com.dianping.puma.core.event.RowChangedEvent;
import scala.NotImplementedError;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Dozer @ 6/11/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ColumnInfoWrap implements Map<String, Object> {
	private final RowChangedEvent event;

	public ColumnInfoWrap(RowChangedEvent event) {
		this.event = event;
	}

	@Override
	public int size() {
		throw new NotImplementedError();
	}

	@Override
	public boolean isEmpty() {
		throw new NotImplementedError();
	}

	@Override
	public boolean containsKey(Object key) {
		return event.getColumns().containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		throw new NotImplementedError();
	}

	@Override
	public Object get(Object key) {
		RowChangedEvent.ColumnInfo info = event.getColumns().get(key);
		if (info == null) {
			return null;
		}

		switch (event.getDmlType()) {
		case INSERT:
		case UPDATE:
			return info.getNewValue();
		default:
			return info.getOldValue();
		}
	}

	@Override
	public Object put(String key, Object value) {
		throw new NotImplementedError();
	}

	@Override
	public Object remove(Object key) {
		throw new NotImplementedError();
	}

	@Override
	public void putAll(Map<? extends String, ?> m) {
		throw new NotImplementedError();
	}

	@Override
	public void clear() {
		throw new NotImplementedError();
	}

	@Override
	public Set<String> keySet() {
		throw new NotImplementedError();
	}

	@Override
	public Collection<Object> values() {
		throw new NotImplementedError();
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		throw new NotImplementedError();
	}
}
