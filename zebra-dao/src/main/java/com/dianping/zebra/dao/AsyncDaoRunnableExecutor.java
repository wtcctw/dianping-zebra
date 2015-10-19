package com.dianping.zebra.dao;

import java.lang.reflect.Method;

import com.dianping.zebra.group.util.DaoContextHolder;

public class AsyncDaoRunnableExecutor<T> implements Runnable {

	private AsyncDaoCallback<T> callback;

	private Object mapper;

	private Method method;

	private Object[] args;

	public AsyncDaoRunnableExecutor(Object mapper, Method method, Object[] args, AsyncDaoCallback<T> callback) {
		this.mapper = mapper;
		this.method = method;
		this.args = args;
		this.callback = callback;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void run() {
		try {
			DaoContextHolder.setSqlName(method.getName());

			T result = (T) method.invoke(mapper, args);

			if (callback != null) {
				callback.onSuccess(result);
			}
		} catch (Exception e) {
			callback.onException(e);
		} finally {
			DaoContextHolder.clearSqlName();
		}
	}
}