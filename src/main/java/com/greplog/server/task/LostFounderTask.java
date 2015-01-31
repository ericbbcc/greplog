package com.greplog.server.task;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String logger_src_dir = config.getLogger_src_dir();
		List<String> loggerNames = new ArrayList<>();
		fetchLoggerNames(new File(logger_src_dir), loggerNames);
		Map<String, Boolean> loggerNameMap = new HashMap<>();
		Pattern p = Pattern.compile("\\[[0-9]{2}:[0-9]{2}:[0-9]{2}\\]");
		for(String lName : loggerNames){
			Matcher m = p.matcher(lName);
			if(m.find()){
				loggerNameMap.put(m.group(), true);
			}
		}
		int cutting_interval = config.getCutting_interval();
		CacheHolder cache = CacheHolder.newInstance();
		DefaultClock clock = new DefaultClock(GrepLogConfig.START_TIME);
		while(!clock.getCurrentTime().equals(cache.getCurrentTime())){
			if(null == loggerNameMap.get(clock.getCurrentTime()))
				cache.addLostLogger(clock.getCurrentTime());
			clock.pastSecond(cutting_interval);
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
	
}
