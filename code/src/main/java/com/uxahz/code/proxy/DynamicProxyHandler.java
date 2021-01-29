package com.uxahz.code.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxyHandler implements InvocationHandler{
	private Object object;
	public DynamicProxyHandler(Object object) {
		this.object = object;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("买房前准备");
		Object result = method.invoke(object, args);
		System.out.println("物业交接");
		return result;
	}
}
