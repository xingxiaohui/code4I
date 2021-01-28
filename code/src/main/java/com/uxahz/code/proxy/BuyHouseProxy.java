package com.uxahz.code.proxy;

public class BuyHouseProxy implements BuyHouse {
	private BuyHouse buyHouse;
	public BuyHouseProxy(BuyHouse buyHouse) {
		this.buyHouse = buyHouse;
	}
	@Override
	public void buy() {
		System.out.println("看房选房");
		this.buyHouse.buy();
		System.out.println("物业交接");
	}
}
