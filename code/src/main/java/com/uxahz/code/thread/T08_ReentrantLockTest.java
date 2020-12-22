package com.uxahz.code.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T08_ReentrantLockTest {
	private static Lock lock = new ReentrantLock();
	private static long l = 0l;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t = new Thread(()->{
			for(int i=0; i<1; i++) {
				add();
			}
		});
		Thread t1 = new Thread(()->{
			for(int i=0; i<1; i++) {
				add();
			}
		});
		t.start();
		t1.start();
		try {
			t.join();
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(get());
	}
	
	private static void add() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName());
			l = get()+1;
		} finally {
			lock.unlock();
		}
	}
	private static long get() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName());
			return l;
		} finally {
			lock.unlock();
		}
	}
	

}
