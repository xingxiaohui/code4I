package com.uxahz.code.designpatterns;
/**
 * 饿汉式单例，构造器私有，初始即在内存中创建一个对象实例，调用接口时返回
 * 线程安全
 */
public class D01_Singleton {
	
	public static void main(String[] args) {
		for(int i = 0; i<100; i++) {
			Thread t = new Thread(()-> {
				D01_Singleton sg = D01_Singleton.getInstence();
				System.out.println(sg.toString());
			});
			t.start();
		}
	}
	private static final D01_Singleton INSTENCE = new D01_Singleton();
	private D01_Singleton() {}
	public static D01_Singleton getInstence() {
		return INSTENCE;
	}
}
