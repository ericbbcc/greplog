package com.greplog.server.task;

import java.util.TimerTask;

import com.greplog.cfg.CacheHolder;
import com.greplog.clock.DefaultClock;

/**
 * 下载服务器日志并分片存储在本地
 * @author luchen
 *
 */
public class GrepLogTask extends TimerTask{

	private CacheHolder cache;
	
	public GrepLogTask(){
		cache = CacheHolder.newInstance();
	}

	@Override
	public void run() {
		
	}
	private String getLostLoggerName(){
		String lostLogger = cache.getLostLogger();
		if( lostLogger != null && !lostLogger.equals(""))
			return lostLogger;
		DefaultClock clock = new DefaultClock(cache.getCurrentTime());
		lostLogger = clock.getCurrentTime();
		cache.setCurrentTime(clock.pastSecond(cache.getConfig().getCutting_interval()));
		return lostLogger;
	}

}
