package com.uxahz.code.proxy;

public class BuyHouseProxyTest {

	public static void main(String[] args) {
		BuyHouseProxy buyHouseProxy = new BuyHouseProxy(new BuyhouseImpl());
		buyHouseProxy.buy();
	}

}
