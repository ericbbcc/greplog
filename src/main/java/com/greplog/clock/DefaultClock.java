package com.greplog.clock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DefaultClock extends AbstractClock implements Clock{
	
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private String currentTime = "00:00:00";
	
	
	public DefaultClock(String currentTime) {
		super(currentTime);
		this.currentTime = currentTime;
	}

	@Override
	public String getCurrentTime() {
		lock.readLock().lock();
		try{
			return currentTime;
		}finally{
			lock.readLock().unlock();
		}
	}

	@Override
	public String pastSecond() {
		lock.writeLock().lock();
		try{
			currentTime = pastSecond(1);
		}finally{
			lock.writeLock().unlock();
		}
		return currentTime;
	}

	@Override
	public String pastMinute() {
		lock.writeLock().lock();
		try{
			currentTime = pastMinute(1);
		}finally{
			lock.writeLock().unlock();
		}
		return currentTime;
	}

	@Override
	public String pastHour() {
		lock.writeLock().lock();
		try{
			currentTime = pastHour(1);
		}finally{
			lock.writeLock().unlock();
		}
		return currentTime;
	}

	@Override
	public String pastSecond(int second) {
		lock.writeLock().lock();
		try{
			currentTime = getTimeString(getTimeInt() + second);
		}finally{
			lock.writeLock().unlock();
		}
		return currentTime;
	}

	@Override
	public String pastMinute(int minute) {
		lock.writeLock().lock();
		try{
			currentTime = getTimeString( getTimeInt() + minute * 60);
		}finally{
			lock.writeLock().unlock();
		}
		return currentTime;
	}

	@Override
	public String pastHour(int hour) {
		lock.writeLock().lock();
		try{
			currentTime = getTimeString(hour * 60 * 60);
		}finally{
			lock.writeLock().unlock();
		}
		return currentTime;
	}
	
	
}
