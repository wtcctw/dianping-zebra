package com.dianping.zebra.group.config;

import java.beans.PropertyChangeEvent;

public class AdvancedPropertyChangeEvent extends PropertyChangeEvent {

	private static final long serialVersionUID = 1L;

	// 1 : add , 2 : delete , 3 : update
	private int type;

	public AdvancedPropertyChangeEvent(Object source, String propertyName, Object oldValue, Object newValue, int type) {
		super(source, propertyName, oldValue, newValue);
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
