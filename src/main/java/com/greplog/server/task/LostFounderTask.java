package com.greplog.server.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import com.greplog.cfg.CacheHolder;
import com.greplog.cfg.GrepLogConfig;

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
		List<String> logger_names =  getLoggerNames(new File(logger_src_dir));
	}
	
	private List<String> getLoggerNames(File f){
		List<String> loggerNames = new ArrayList<String>();
		if(f.isDirectory()){
			loggerNames.addAll(getLoggerNames(f));
		}
		String[] fnames = f.list();
		for(String s : fnames)
			loggerNames.add(s);
		return loggerNames;
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
