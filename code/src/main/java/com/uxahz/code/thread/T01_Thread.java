package com.uxahz.code.thread;

public class T01_Thread {

	private static  long  count = 0l;
	public static void main(String[] args) {
		Thread t1 = new Thread(()-> {
			for(int i=0; i<100000; i++) {
				addCount();
			}
		});
		Thread t2 = new Thread(()->{
			for(int i=0; i<100000; i++) {
				addCount();
			}
		}) ;
		
		Thread t3 = new Thread(()->{
			for(int i=0; i<100000; i++) {
				addCount();
			}
		}) ;
		
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
		System.out.println(getCount());
	}
	private static synchronized long getCount() {
		return count;
	}
	
	private static synchronized void addCount() {
		count++;
	}
}
