package com.greplog.server;

import com.greplog.cfg.GrepLogConfig;

public class GrepLogServer implements Server{
	
	private GrepLogConfig config;
	
	public GrepLogServer(GrepLogConfig config){
		this.config = config;
	}
	
	@Override
	public void startup() {
		startupFromConfig(config);
	}

	@Override
	public void shutdown() {
		
	}
	
	private void startupFromConfig(GrepLogConfig config){
		
	}

}
