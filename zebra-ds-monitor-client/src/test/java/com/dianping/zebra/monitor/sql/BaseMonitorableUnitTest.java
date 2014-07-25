/**
 * Project: ${zebra-ds-monitor-client.aid}
 * 
 * File Created at 2011-11-3
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
package com.dianping.zebra.monitor.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLWarning;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;

/**
 * @author danson.liu
 *
 */
public abstract class BaseMonitorableUnitTest {

	protected static final Random RANDOM = new Random();

	protected static final String STR_ANY = "foo";

	protected static final Boolean BOOL_ANY = RANDOM.nextBoolean();

	protected static final Long LONG_ANY = RANDOM.nextLong();

	protected static final Integer INT_ANY = RANDOM.nextInt();

	protected static final Byte BYTE_ANY = (byte) RANDOM.nextInt(100);

	protected static final Short SHORT_ANY = (short) RANDOM.nextInt(100);

	protected static final Integer UINT_ANY = Math.abs(RANDOM.nextInt(100)) + 1;

	protected static final Float FLOAT_ANY = RANDOM.nextFloat();

	protected static final Double DOUBLE_ANY = RANDOM.nextDouble();

	protected static final BigDecimal DECIMAL_ANY = new BigDecimal(DOUBLE_ANY);

	protected static final Date DATE_ANY = new Date(Calendar.getInstance().getTimeInMillis());

	protected static final Time TIME_ANY = new Time(Calendar.getInstance().getTimeInMillis());

	protected static final Timestamp TIMESTAMP_ANY = new Timestamp(Calendar.getInstance().getTimeInMillis());

	protected static final Calendar CALENDAR_ANY = Calendar.getInstance();

	protected static final Object OBJECT_ANY = new Object();

	protected static final Map<String, Class<?>> MAP_ANY = new HashMap<String, Class<?>>();

	protected static final String SQL_ANY = "anysql";

	protected static final int[] INT_ARRARY_ANY = new int[] { RANDOM.nextInt(), RANDOM.nextInt() };

	protected static final byte[] BYTE_ARRARY_ANY = new byte[] { BYTE_ANY };

	protected static final String[] STR_ARRARY_ANY = new String[] { STR_ANY };

	protected Map<String, Object[]> innerConnMethodInvoked = new HashMap<String, Object[]>();

	protected Map<String, Object> innerConnMethodRetVals = new HashMap<String, Object>();

	protected void assertInnerMethodInvoked(String method, Object[] params) {
		assertTrue(innerConnMethodInvoked.containsKey(method));
		for (int i = 0; i < params.length; i++) {
			assertEquals(params[i], innerConnMethodInvoked.get(method)[i]);
		}
	}

	protected void assertInnerMethodNotInvoked(String method, Object[] params) {
		boolean include = innerConnMethodInvoked.containsKey(method);
		if (include) {
			for (int i = 0; i < params.length; i++) {
				Object actualParam = innerConnMethodInvoked.get(method)[i];
				if (((params[i] == null || actualParam == null) && params[i] != actualParam)
				      || !params[i].equals(actualParam)) {
					include = false;
					break;
				}
			}
		}
		assertFalse(include);
	}

	protected void assertInnerMethodInvokeAndRetValEquals(String method, Object[] params, Object retVal) {
		assertInnerMethodInvoked(method, params);
		if (!innerConnMethodRetVals.containsKey(method)) {
			Assert.fail("expect return value with method[" + method + "]");
		}
		assertEquals(innerConnMethodRetVals.get(method), retVal);
	}

	protected void resetInnerMethodInvokeRecord() {
		innerConnMethodInvoked.clear();
		innerConnMethodRetVals.clear();
	}

	protected Object createTraceableObject(Class<?> interfaceClazz) {
		return createTraceableObject(null, interfaceClazz);
	}

	protected Object createTraceableObject(Class<?>[] interfaceClazz) {
		return createTraceableObject(null, interfaceClazz);
	}

	protected Object createTraceableObject(Object source, Class<?> interfaceClazz) {
		return createTraceableObject(source, new Class<?>[] { interfaceClazz });
	}

	protected Object createTraceableObject(Object source, Class<?>[] interfaceClazz) {
		if (source == null && (interfaceClazz == null || interfaceClazz.length == 0)) {
			throw new IllegalArgumentException(
			      "Create traceable object must provide either source object or interfaceClazz at least.");
		}
		return Proxy.newProxyInstance(BaseMonitorableUnitTest.class.getClassLoader(),
		      interfaceClazz != null ? interfaceClazz : source.getClass().getInterfaces(), new TraceInvocationHandler(
		            source));
	}

	class TraceInvocationHandler implements InvocationHandler {

		@SuppressWarnings("unused")
		private Object targetObject;

		public TraceInvocationHandler(Object targetObject) {
			this.targetObject = targetObject;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			innerConnMethodInvoked.put(method.getName(), args);
			Object retVal = null;
			Class<?> returnType = method.getReturnType();
			if (returnType != Void.TYPE) {
				if (returnType == Boolean.class || returnType == Boolean.TYPE) {
					retVal = BOOL_ANY;
				} else if (returnType == String.class) {
					retVal = RANDOM.nextInt() + "";
				} else if (returnType == Integer.class || returnType == Integer.TYPE) {
					retVal = RANDOM.nextInt();
				} else if (returnType == Byte.class || returnType == Byte.TYPE) {
					retVal = (byte) RANDOM.nextInt(100);
				} else if (returnType == Short.class || returnType == Short.TYPE) {
					retVal = (short) RANDOM.nextInt(100);
				} else if (returnType == Long.class || returnType == Long.TYPE) {
					retVal = RANDOM.nextLong();
				} else if (returnType == Float.class || returnType == Float.TYPE) {
					retVal = RANDOM.nextFloat();
				} else if (returnType == Double.class || returnType == Double.TYPE) {
					retVal = RANDOM.nextDouble();
				} else if (returnType == BigDecimal.class) {
					retVal = DECIMAL_ANY;
				} else if (returnType == Date.class) {
					retVal = DATE_ANY;
				} else if (returnType == Time.class) {
					retVal = TIME_ANY;
				} else if (returnType == Timestamp.class) {
					retVal = TIMESTAMP_ANY;
				} else if (returnType == SQLWarning.class) {
					retVal = new SQLWarning();
				} else if (returnType == Object.class) {
					retVal = OBJECT_ANY;
				}
				innerConnMethodRetVals.put(method.getName(), retVal);
			}
			return retVal;
		}
	}

}
