package com.uxahz.code.thread;

import java.util.concurrent.TimeUnit;

public class T03_WaitNotify {
	private static volatile boolean flag = true ;
	static Object lock = new Object();
	public static void main(String[] args) {
		Thread t1 = new Thread(new Wait(), "Wait");
		Thread t2 = new Thread(new Notify(), "Notify");
		t1.start();
		t2.start();
	}
	
	static class Wait implements Runnable{
		@Override
		public void run() {
			synchronized (lock) {
				while(flag) {
					try {
						System.out.println("Wait 进入等待！");
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Wait 被唤醒了！");
			}
		}
	}
	static class Notify implements Runnable{
		@Override
		public void run() {
			synchronized(lock) {
				System.out.println("Notify 开始执行~");
				try {
					TimeUnit.SECONDS.sleep(5);
					flag = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Notify 开始唤醒其他线程~");
				lock.notifyAll();
			}
		}
	}

}
