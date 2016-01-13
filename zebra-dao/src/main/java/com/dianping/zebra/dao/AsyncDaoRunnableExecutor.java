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
		T result = null;
		boolean success = false;
		try {
			DaoContextHolder.setSqlName(mapper.getClass().getSimpleName() + "." + method.getName());

			result = (T) method.invoke(mapper, args);
			success = true;
		} catch (Exception e) {
			// 仅仅捕获数据库访问异常
			callback.onException(e);
		} finally {
			DaoContextHolder.clearSqlName();
		}

		if (success) {
			// 不要捕获在onSuccess中业务逻辑自身的异常
			callback.onSuccess(result);
		}
	}
}