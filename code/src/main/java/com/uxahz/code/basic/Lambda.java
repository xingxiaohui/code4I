package com.uxahz.code.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

interface Massage {
	public void print(String msg);
}

public class Lambda {

	public static void main(String[] args) {
		Massage massage = new Massage() {
			public void print(String msg) {
				System.out.println(msg);
			}
		};
		massage.print("java 匿名内部类");
		Massage massageLambda = (s) -> System.out.println(s);
		massageLambda.print("Lambda 表达式");

		// Lambda 创建线程
		Thread thread = new Thread(() -> {
			for (int i = 0; i < 1; i++) {
				System.out.println("Lambad 创建线程" + i);
			}
		});
		thread.run();

		ArrayList<Integer> list = new ArrayList<>();
		Collections.addAll(list, 1, 2, 3, 4, 5);
		// lambda表达式 遍历集合
		list.forEach(element -> {
			if (element % 2 == 0) {
				System.out.println(element);
			}
		});
		// Lambda 表达式自定义集合元素排序
		ArrayList<Integer> list1 = new ArrayList<>();
		Collections.addAll(list1, 0,1,2,3,4,5,6,7,8);
		list1.sort((o1, o2) -> {
            int bitCount1 = Integer.bitCount(o1);
            int bitCount2 = Integer.bitCount(o2);
            // 根据数字的二进制包含的1个数排序，个数相同的按照大小排序
            return bitCount1 == bitCount2 ? o1-o2 : bitCount1-bitCount2;
        });
	}
}
