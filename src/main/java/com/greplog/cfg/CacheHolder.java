package com.greplog.cfg;

import java.io.File;
import java.util.Queue;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.greplog.util.PersistenceUtils;

/**
 * 单例、缓存器
 * @author andy
 *
 */
public class CacheHolder {
	
	private static Logger LOG = LoggerFactory.getLogger(CacheHolder.class);
	/** 保存检索LostFounderTask任务检索出来丢失的日志分片 */
	private Queue<String> lostLoggers= new ConcurrentLinkedQueue<String>();
	/** 爬去日志工作线程池 */
	private Queue<TimerTask> grepLogTasksPool = new ConcurrentLinkedQueue<TimerTask>();
	
	private String currentTime = GrepLogConfig.START_TIME;
	
	private GrepLogConfig config;
	
	private static CacheHolder cache;
	
	private static Robot userInfo;
	
	
	public CacheHolder() {
		super();
		initRobot();
	}
	
	private void initRobot(){
		Robot robot = new Robot();
		robot.setPassword(config.getPassword());
		robot.setHostIp(config.getHost_ip());
		robot.setUsername(config.getUsername());
		robot.setPort(config.getPort());
		userInfo = robot;
	}

	public static CacheHolder newInstance(){
		if(cache == null){
			cache = new CacheHolder();
			return cache;
		}else
			return cache;
	}
	
	public Robot getUserInfo(){
		return userInfo;
	}
	
	public synchronized void addLostLogger(String loggerName){
		this.lostLoggers.add(loggerName);
	}
	
	public synchronized String getLostLogger(){
		if(this.lostLoggers.size() != 0)
			return this.lostLoggers.poll();
		return null;
	}
	
	public synchronized void setCurrentTime(String currentTime){
		try{
			PersistenceUtils.serialize(currentTime, new File(config.getConfig_dir_path()), "currentTime");
		}catch(Exception e){
			LOG.error("serialize currentTime error!");
		}
		this.currentTime = currentTime;
	}
	
	public synchronized String getCurrentTime(){
		return this.currentTime;
	}
	
	public synchronized void addGrepLogTask(TimerTask timerTask){
		this.grepLogTasksPool.add(timerTask);
	}
	
	public synchronized TimerTask getGrepLogTask(){
		return this.grepLogTasksPool.poll();
	}
	
	public void setConfig(GrepLogConfig config){
		this.config = config;
	}
	
	public GrepLogConfig getConfig(){
		return this.config;
	}
	
}
