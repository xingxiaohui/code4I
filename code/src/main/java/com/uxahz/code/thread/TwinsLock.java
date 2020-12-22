package com.uxahz.code.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
/**
 * 自定义同步锁，支持最多两个线程同时访问
 */
public class TwinsLock implements Lock{
	private final Sync sync = new Sync(2);
	private static final class Sync extends AbstractQueuedSynchronizer{
		Sync(int count){
			if(count<0) {
				throw new IllegalArgumentException("count must large than zero.");
			}
			setState(count);
		}
		public int tryAcquireShared(int reduceCount) {
			for(;;) {
				int courrent = getState();
				int newCount = courrent - reduceCount;
				if(newCount < 0 || compareAndSetState(courrent, newCount)) {
					return newCount;
				}
			}
		}
		public boolean tryReleaseShared(int returnCount) {
			for(;;) {
				int courrent = getState();
				int newCount = courrent + returnCount;
				if(compareAndSetState(courrent, newCount)) {
					return true;
				}
			}
		}
	}
	@Override
	public void lock() {
		sync.acquireShared(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
		sync.releaseShared(1);
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
