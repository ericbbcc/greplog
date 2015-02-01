package com.greplog.clock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DefaultClock extends AbstractClock implements Clock{
	
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private String currentTime = "00:00:00";
	
	public String getTimeAccuracy(int interval){
		if(interval < 10)
			return currentTime;
		else if(interval < 60 && interval > 9)
			return currentTime.substring(0, currentTime.length() - 1) + "0";
		else if(interval >= 60 && interval < 60 * 10)
			return currentTime.substring(0,  currentTime.length() - 3) + ":00";
		else if(interval >= 60 * 10 && interval < 60 * 60)
			return currentTime.substring(0,  currentTime.length() - 4) + "0:00";
		else if(interval >= 60 * 60 && interval < 10 * 60 * 60)
			return currentTime.substring(0,  currentTime.length() - 5) + ":00:00";
		else 
			return "*";
	}
	
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
