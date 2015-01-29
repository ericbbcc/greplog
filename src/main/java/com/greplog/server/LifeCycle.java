package com.greplog.server;

public interface LifeCycle {
	void init();
	void start();
	void shutdown();
	void destory();
}
