package com.uxahz.code.basic;

import org.junit.Test;

public class B02_StringTest {
	@Test
	public void stringTest_1(){
		// 1. 以字面量形式赋值创建 String 对象，创建的对象被存放于字符串常量池中。
		String str1 = "a";
		String str2 = "a";
		System.out.println(str1 == str2);    // true
	}
	
	@Test
	public void stringTest_2(){
		//2. 使用 new 关键字创建 String 对象，创建的对象存放在堆中。
		String str1 = new String("a"); 
		String str2 = new String("a"); 
		String str3 = "a";
		System.out.println(str1 == str2);    // false
		System.out.println(str1 == str3);    // false
	}
	
	@Test
	public void stringTest_3(){
		//3. 使用常量拼接创建 String 对象，创建的对象存放于字符串常量池中，原理是编译优化；
		String str1 = "ab";
		String str2 = "a" + "b";    //编译优化后：String str1 = "ab";
		final String a = "a";       // 使用final修饰的变量编译时当作常量
		String str3 = a + "b";      //编译优化后：String str1 = "ab";
		System.out.println(str1 == str2);    // true
		System.out.println(str1 == str3);    // true
	}
	
	@Test
	public void stringTest_4(){
		//4. 创建 String 的拼接操作中含有一个及以上变量时，则创建的对象存放在堆中，原理是StringBuilder(jdk 1.5以前使用StringBuffer)的append操作。
		String a = "a";
		String str1 = "ab";
		String str2 = a + "b";
		System.out.println(str1 == str2);   // false
	}
	
	@Test
	public void stringTest_5(){
		//5. 当使用String对象的intern方法时，将判断字符串常量池中是否含有对应的字符串，如果没有则在字符串常量池中创建并返回对应地址，如果有则直接返回对应地址。
		String str1 = new String("a");      //对象
		String str2 = str1.intern();	    //字符串常量池
		String str3 = "a";				    //字符串常量池
		System.out.println(str1 == str3);   // false
		System.out.println(str2 == str3);   // true
	}
	@Test
	public void stringTest_6(){
		//使用连接符与append方法的效率对比
		long start = System.currentTimeMillis();
		String str1 = "";
		for(int i = 0; i< 100000; ++i){
			str1 += "a";
		}
		long end_1 = System.currentTimeMillis();
		System.out.println(end_1 - start);    //3855ms
		
		StringBuilder sb = new StringBuilder();		//优化点，构造方法可以设置长度，节省底层数组扩容时间，默认长度16
		for(int i = 0; i< 100000; ++i){
			sb.append("a");
		}
		long end_2 = System.currentTimeMillis();
		System.out.println(end_2 - end_1);	  //4ms
	}
	
	@Test
	public void stringTest_7(){
		
	}
}
