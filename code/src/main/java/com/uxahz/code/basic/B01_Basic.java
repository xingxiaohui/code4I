package com.uxahz.code.basic;

import org.junit.Test;

public class B01_Basic {
	@Test
	public void testPrint(){
		System.out.println("hello,world!");
	}
	@Test
	public void testLoop(){
		for(int i=0; i<10; i++){
			System.out.println(i);
		}
		int i=0;
		while(i<10){
			System.out.println(i);
			i++;
		}
	}
}
