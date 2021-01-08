package com.uxahz.code.jvm;
/**
 * 设置java虚拟机栈深度，测试StackOverFlow异常
 * VM Args:-Xss128k
 *
   * 打印结果：
 *Exception in thread "main" length:992
 *java.lang.StackOverflowError
 */
public class J03_StackOverFlowErrorTest {
	private int length = 0;
	public static void main(String[] args) throws Throwable{
		// TODO Auto-generated method stub
		J03_StackOverFlowErrorTest j = new J03_StackOverFlowErrorTest();
		try {
			j.stackLeak();
		} catch (Throwable e) {
			System.out.println("length:" + j.length);  
			throw e;
		}

	}

	private void stackLeak() {
		length ++;
		stackLeak();
	}
}
