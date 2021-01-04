package com.uxahz.code.jvm;

import java.util.HashMap;
import java.util.Map;

public class J01_InitTest {
	/**
	 * 打印java基本数据类型初始值
	 * 在java中基本类型的默认值是0，引用类型会默认为null
	 */
	public static void main(String[] args) {
		BasicData basicData = new BasicData();
//		System.out.println("byte:");
//		System.out.println(basicData.a);
//		System.out.println("short:");System.out.println(basicData.b);
//		System.out.println("int:");System.out.println(basicData.c);
//		System.out.println("long:");System.out.println(basicData.d);
//		System.out.println("float:");System.out.println(basicData.e);
//		System.out.println("double:");System.out.println(basicData.f);
//		System.out.println("boolean:");System.out.println(basicData.g);
//		System.out.println("char:");System.out.println(basicData.h);
//		System.out.println("String:");System.out.println(basicData.i);
		Map<Integer, User> userMap = new HashMap<Integer, User>();
		User user = new User();
		user.setId(0);
		for(int i=1; i<5; ++i) {
			User userTemp = new User();
			userTemp = user;
			userMap.put(i, userTemp);
			
		}
		
		for(int i=1; i<5; ++i) {
			System.out.println(userMap.get(i).getId());
		}
	}
}
class BasicData{
	byte a;
	short b;
	int c;
	long d;
	float e;
	double f;
	boolean g;
	char h;
	String i;
}
class User{
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
