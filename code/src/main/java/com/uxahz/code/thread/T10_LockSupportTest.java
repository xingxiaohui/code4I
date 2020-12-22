package com.uxahz.code.thread;

import java.util.concurrent.locks.LockSupport;

public class T10_LockSupportTest {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						System.out.println("t1__park"+System.currentTimeMillis());
						LockSupport.parkNanos(1000);
//						LockSupport.park();
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		},"t1");
		t1.start();
		System.out.println("t1-start");
//		LockSupport.unpark(t1);
//		System.out.println("t1-unpark");
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
