package com.greplog.server.task;

import java.io.File;
import java.util.TimerTask;

import com.greplog.cfg.CacheHolder;
import com.greplog.clock.DefaultClock;
import com.greplog.ssh.SSHExec;

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
		String lostLoggerName = getLostLoggerName();
		File file = null;
		if(null != lostLoggerName && !lostLoggerName.trim().equals("")){
			file = new File(cache.getConfig().getLogger_src_dir(), lostLoggerName);
		}else{
			file = new File(cache.getConfig().getLogger_src_dir(), getLoggerName());
		}
		if(file != null){
			try{
				SSHExec.grep("", file);
			}catch(Exception e){
				
			}
		}
			
	}
	
	private String getLoggerName(){
		String currentTime = cache.getCurrentTime();
		DefaultClock clock = new DefaultClock(currentTime);
		cache.setCurrentTime(clock.pastSecond(cache.getConfig().getCutting_interval()));
		return currentTime;
	}
	
	private String getLostLoggerName(){
		String lostLogger = cache.getLostLogger();
		if( lostLogger != null && !lostLogger.trim().equals(""))
			return lostLogger;
		DefaultClock clock = new DefaultClock(cache.getCurrentTime());
		lostLogger = clock.getCurrentTime();
		cache.setCurrentTime(clock.pastSecond(cache.getConfig().getCutting_interval()));
		return lostLogger;
	}

}
