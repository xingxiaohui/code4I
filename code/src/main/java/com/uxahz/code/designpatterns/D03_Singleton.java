package com.uxahz.code.designpatterns;

import java.util.concurrent.TimeUnit;

/**
 * 双检锁单例，构造器私有，初始不会在内存中创建对象实例，调用接口时如果为空则当前线程获取锁
 * 进入同步代码块后再次校验是否已被其他线程初始化，如果没有则进行创建
 * 通过synchronized 保证同一时刻只有一个线程获得初始化对象方法
 * 通过volatile 禁止初始化过程的指令重排序，保证其他线程能拿到完全初始化的实例对象
 * 线程安全
 */
public class D03_Singleton {
	
	public static void main(String[] args) {
		for(int i = 0; i<100; i++) {
			Thread t = new Thread(()-> {
				D03_Singleton sg = D03_Singleton.getInstence();
				System.out.println(sg.toString());
			});
			t.start();
		}
	}
	private static volatile D03_Singleton INSTENCE;
	private D03_Singleton() {}
	public static D03_Singleton getInstence() {
		if(INSTENCE==null) {
			synchronized(D03_Singleton.class) {
				if(INSTENCE == null) {
					try {
						TimeUnit.MILLISECONDS.sleep(100);  //模拟执行延迟
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					INSTENCE = new D03_Singleton();
				}
			}
		}
		return INSTENCE;
	}
}
