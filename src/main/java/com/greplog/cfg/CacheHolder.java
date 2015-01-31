package com.greplog.cfg;

import java.util.Queue;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 单例、缓存器
 * @author andy
 *
 */
public class CacheHolder {
	/** 保存检索LostFounderTask任务检索出来丢失的日志分片 */
	private Queue<String> lostLoggers= new ConcurrentLinkedQueue<String>();
	/** 爬去日志工作线程池 */
	private Set<TimerTask> grepLogTasksPool = new ConcurrentSkipListSet<TimerTask>();
	
	private String currentTime = GrepLogConfig.START_TIME;
	
	private static CacheHolder cache;
	
	public static CacheHolder newInstance(){
		if(cache == null){
			cache = new CacheHolder();
			return cache;
		}else
			return cache;
	}
	
	public synchronized void setCurrentTime(String currentTime){
		this.currentTime = currentTime;
	}
	
	public synchronized String getCurrentTime(){
		return this.currentTime;
	}
	
}
