package com.uxahz.code.thread;

import java.util.concurrent.TimeUnit;

public class T04_Join {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(10);
				System.out.println("t1   结束~");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		t1.start();
		System.out.println("t1  开始~");
		try {
			t1.join();
			t1.join(1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main 结束~");
	}

}
