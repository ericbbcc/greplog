package com.greplog.server;

import com.greplog.cfg.CacheHolder;
import com.greplog.cfg.GrepLogConfig;
import com.greplog.exception.ConfigException;

public class GrepLogMain {
	
	public static void main(String ...args){
		GrepLogConfig config = new GrepLogConfig();
		try{
			config.parse(args[0]);
			CacheHolder.newInstance().setConfig(config);
		}catch(ConfigException ce){
			System.err.println(ce);
            System.exit(2);
		}
		
	}
}
