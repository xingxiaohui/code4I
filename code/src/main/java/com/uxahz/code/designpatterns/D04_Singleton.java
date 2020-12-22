package com.uxahz.code.designpatterns;
/**
 *枚举单例 effective java 推荐
 * 线程安全
 * 写法简单
 * 枚举没有构造方法，所以也能避免反射创建多实例
 */
public enum D04_Singleton {
	INSTENCE;
	public D04_Singleton getInstence() {
		return INSTENCE;
	}
}
