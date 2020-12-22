package com.uxahz.code.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T11_ConditionTest {
	static BoundedQueue<String> queue= new BoundedQueue<String>(10);
	public static void main(String[] args) {
		Thread t1 = new Thread(()->{
			while(true) {
				try {
					queue.add(""+System.currentTimeMillis());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(()->{
			while(true) {
				try {
					System.out.println(queue.remove());
					Thread.sleep(500);  		//添加慢，移除快
//					Thread.sleep(1500);			//添加快，移除慢
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class BoundedQueue<T>{
	private Object[] items;
	private int addIndex,removeIndex, size;
	private Lock lock = new ReentrantLock();
	private Condition notEmpty = lock.newCondition();
	private Condition notFull = lock.newCondition();
	public BoundedQueue(int size) {
		items = new Object[size];
	}
	
	public void add(T t) throws InterruptedException {
		lock.lock();
		try {
			while(size ==items.length) {
				System.out.println("队列满了~");
				notFull.await();
			}
			items[addIndex] = t;
			if(++addIndex == items.length) addIndex=0;
			++size;
			notEmpty.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public T remove() throws InterruptedException {
		lock.lock();
		try {
			while(size == 0) {
				System.out.println("队列空了~");
				notEmpty.await();
			}
			Object t = items[removeIndex];
			if(++removeIndex == items.length) removeIndex = 0;
			--size;
			notFull.signalAll();
			return (T)t;
		} finally {
			lock.unlock();
		}
	}
}
