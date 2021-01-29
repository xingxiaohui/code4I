package com.uxahz.code.proxy;

import java.lang.reflect.Proxy;

import org.junit.Test;

public class BuyHouseProxyTest {
	@Test
	public void test1() {
		BuyHouseProxy buyHouseProxy = new BuyHouseProxy(new BuyhouseImpl());
		buyHouseProxy.buy();
	}
	
	@Test
	public void test2() {
		BuyHouse buyHouse = new BuyhouseImpl();
		BuyHouse buyHouseProxy = (BuyHouse)Proxy.newProxyInstance(BuyHouse.class.getClassLoader(), 
				new Class[] {BuyHouse.class},new DynamicProxyHandler(buyHouse));
		buyHouseProxy.buy();
	}
}
