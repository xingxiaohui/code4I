package com.uxahz.code.thread;

import java.util.Random;
import java.util.Scanner;
// 777抽奖机
public class T12_777Lucky {
	public static void main(String[] args) {
		String[] card = new String[]{"西瓜","芒果","香蕉","橘子","苹果","钻石","7"};
		while(true) {
			System.out.println("是否继续游戏？1.继续 2.退出");
			Scanner scanner = new Scanner(System.in);
			String code = scanner.next();
			if("1".equals(code)) {
				Thread t1 = new Thread(()->{
					Random random = new Random(System.currentTimeMillis());
					System.out.print("|-"+card[random.nextInt(7)]);
				});
				Thread t2 = new Thread(()->{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Random random = new Random(System.currentTimeMillis());
					System.out.print("-|-"+card[random.nextInt(7)]);
				});
				Thread t3 = new Thread(()->{
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Random random = new Random(System.currentTimeMillis());
					System.out.println("-|-"+card[random.nextInt(7)]+"-|");
				});
				t1.start();
				t2.start();
				t3.start();
				try {
					t1.join();
					t2.join();
					t3.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				break;
			}
		}
	}
}
