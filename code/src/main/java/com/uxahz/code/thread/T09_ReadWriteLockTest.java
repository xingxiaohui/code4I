package com.uxahz.code.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T09_ReadWriteLockTest {
	static Cache cache = new Cache();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true) {
					String key= cache.put("1","lock");
					System.out.println(Thread.currentThread().getName()+"-put-"+key);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} ,"t1");
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
						System.out.println(Thread.currentThread().getName()+"--"+cache.get("1"));
					}
				}
		} ,"t2");
		Thread t3 = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"--"+cache.get("1"));
				}
			}
		} ,"t3");
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static class Cache{
		private Map<String, String> map = new HashMap<String, String>();
		private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		Lock readLock = lock.readLock();
		Lock writeLock = lock.writeLock();
		
		public String put(String key, String val) {
			readLock.lock();
			try {
				map.put(key, val);
			} finally {
				readLock.unlock();
			}
			return key;
		}
		public String get(String key) {
			writeLock.lock();
			try {
				return map.get(key);
			} finally {
				writeLock.unlock();
			}
		}
		
		
	}

}
