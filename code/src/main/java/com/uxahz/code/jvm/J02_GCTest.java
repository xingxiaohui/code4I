package com.uxahz.code.jvm;

public class J02_GCTest {
	
	public static void main(String[] args) {
		byte[] b = new byte[1024*1024*6];
		b=null;
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("开始GC");
		System.gc();
		
		try {
			Thread.sleep(150000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
