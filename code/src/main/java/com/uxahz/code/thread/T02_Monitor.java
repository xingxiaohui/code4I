package com.uxahz.code.thread;

import java.util.concurrent.locks.ReentrantLock;

public class T02_Monitor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Thread t1 = new Thread(()->{
//			T02_monitor.writer();
//		}); 
//		Thread t2 = new Thread(()->{
//			T02_monitor.reader();
//		});
//		t2.start();
//		t1.start();
		// 测试可重入锁
		
			Thread t = new Thread(()->{
				for(int i = 0; i<10000; i++) {
					writerLock();
				}
			});
			Thread t0 = new Thread(()->{
				for(int i = 0; i<10000; i++) {
					writerLock();
				}
			});
			t.run();
			t.start();
			t0.start();
			try {
				t.join();
				t0.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			printB();
	}
	
	static ReentrantLock lock = new ReentrantLock(true);
	static int a = 0;
	private static synchronized void writer() {
		a++;
		System.out.println("writer"+a);
	}
	
	private static synchronized void reader() {
		System.out.println("reader"+a);
	}
	
	static int b = 0;
	private static void writerLock() {
		b++;
		System.out.println("writerLock"+b);
		lock.lock();
		try {
			a++;
			System.out.println("    lock a"+a);
		} finally {
			lock.unlock();
		}
	}
	private static void printB() {
		System.out.println("lock ------------------"+b);
	}
}
