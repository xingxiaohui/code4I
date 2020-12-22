package com.uxahz.code.thread;

import java.util.concurrent.TimeUnit;

public class T05_ThreadLocal {
	private static final ThreadLocal<Long> tl = new ThreadLocal<Long>();
	private static final ThreadLocal<String> ts = new ThreadLocal<String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		set();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(get());
	}
	private static void set() {
		tl.set(System.currentTimeMillis());
	}
	
	private static long get() {
		return System.currentTimeMillis()-tl.get();
	}
}
