package com.dianping.zebra.syncservice.util;

import com.dianping.puma.core.event.RowChangedEvent;

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
		throw new IllegalAccessError();
	}

	@Override
	public boolean isEmpty() {
		throw new IllegalAccessError();
	}

	@Override
	public boolean containsKey(Object key) {
		return event.getColumns().containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		throw new IllegalAccessError();
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
		throw new IllegalAccessError();
	}

	@Override
	public Object remove(Object key) {
		throw new IllegalAccessError();
	}

	@Override
	public void putAll(Map<? extends String, ?> m) {
		throw new IllegalAccessError();
	}

	@Override
	public void clear() {
		throw new IllegalAccessError();
	}

	@Override
	public Set<String> keySet() {
		throw new IllegalAccessError();
	}

	@Override
	public Collection<Object> values() {
		throw new IllegalAccessError();
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		throw new IllegalAccessError();
	}
}
