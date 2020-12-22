package com.uxahz.code.designpatterns;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉式单例，构造器私有，初始不会在内存中创建对象实例，调用接口时如果为空再进行创建
 * 线程不安全
 */
public class D02_Singleton {
	
	public static void main(String[] args) {
		for(int i = 0; i<100; i++) {
			Thread t = new Thread(()-> {
				D02_Singleton sg = D02_Singleton.getInstence();
				System.out.println(sg.toString());
			});
			t.start();
		}
	}
	private static D02_Singleton INSTENCE;
	private D02_Singleton() {}
	public static D02_Singleton getInstence() {
		if(INSTENCE==null) {
			try {
				TimeUnit.MILLISECONDS.sleep(10);  //模拟执行延迟
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			INSTENCE = new D02_Singleton();
		}
		return INSTENCE;
	}
}
