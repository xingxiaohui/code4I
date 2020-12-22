package com.uxahz.code.thread;

import java.util.concurrent.locks.Lock;

public class T06_MutexTest {
	static Lock lock = new Mutex();
	private static long l = 0;
	public static void main(String[] args) {
		
		Thread t = new Thread(()->{
			for(int i=0; i<100000; i++) {
				getAndAdd();
			}
		});
		Thread t1 = new Thread(()->{
			for(int i=0; i<100000; i++) {
				getAndAdd();
			}
		});
		t.start();
		t1.start();
		try {
			t.join();
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("---"+l);
	}
	private static void getAndAdd() {
//		lock.lock();
		try {
			l++;
		} finally {
//			lock.unlock();
		}
	}
}
