package com.greplog.server.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import com.greplog.cfg.CacheHolder;
import com.greplog.cfg.GrepLogConfig;
import com.greplog.clock.DefaultClock;

/**
 * 查找缺失的日志分片 
 * @author luchen
 *
 */
public class LostFounderTask extends TimerTask{
	
	private GrepLogConfig config;
	private CacheHolder cache;
	
	

	public LostFounderTask(GrepLogConfig config, CacheHolder cache) {
		super();
		this.config = config;
		this.cache = cache;
	}

	@Override
	public void run() {
		int cutting_interval = config.getCutting_interval();
		String logger_src_dir = config.getLogger_src_dir();
		List<String> loggerNames = new ArrayList<>();
		fetchLoggerNames(new File(logger_src_dir), loggerNames);
		for(String lName : loggerNames){
			DefaultClock clock = new DefaultClock(GrepLogConfig.START_TIME);
		}
		
	}
	
	private List<String> fetchLoggerNames(File f, List<String> logger_names){
		File[] fs = f.listFiles();
		for(File tmpF : fs){
			if(tmpF.isDirectory()){
				logger_names.addAll(fetchLoggerNames(tmpF, logger_names));
			}else if(tmpF.isFile()){
				logger_names.add(tmpF.getName());
			}
		}
		return logger_names;
	}
	

	@Override
	public boolean cancel() {
		return super.cancel();
	}

	@Override
	public long scheduledExecutionTime() {
		return super.scheduledExecutionTime();
	}

}
