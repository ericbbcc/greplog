package com.greplog.cfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.grep.exception.ConfigException;

public class GrepLogConfig {
	private static final Logger LOG = LoggerFactory.getLogger(GrepLogConfig.class);
	/** 日志起始时间 */
	public static String START_TIME = "00:00:00";
	/** 日志服务器IP地址 */
	private String host_ip;
	/** 日志服务器SSH服务端口号 */
	private int port;
	/** SSH登录日志服务器的用户名 */
	private String username;
	/** SSH登录日志服务器的密码 */
	private String password;
	/** 存储索引的目录 */
	private String indexer_dir;
	/** 检索分片日志的目录 */
	private String logger_src_dir;
	/** 日志分片时间间隔 */
	private int cutting_interval;
	/** 最小并发工作线程数 */
	private int min_concurrent_lever;
	/** 最大并发工作线程数 */
	private int max_concurrent_lever;
	public void parse(String cfgPath) throws ConfigException{
		File configFile = new File(cfgPath);
		LOG.info("Reading configuration from:" + cfgPath);
		
		try{
			if(!configFile.exists()){
				throw new IllegalArgumentException(configFile.toString() +
						" file is missing");
			}
			Properties cfg = new Properties();
			FileInputStream in = new FileInputStream(configFile);
			try{
				cfg.load(in);
			}finally{
				in.close();
			}
			parseProperties(cfg);
		}catch(IOException e){
			throw new ConfigException("Error processing " + cfgPath, e);
		}catch(IllegalArgumentException e){
			throw new ConfigException("Error processing " + cfgPath, e);
		}
	}
	
	public void parseProperties(Properties pros){
		
	}

	public String getHost_ip() {
		return host_ip;
	}

	public void setHost_ip(String host_ip) {
		this.host_ip = host_ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIndexer_dir() {
		return indexer_dir;
	}

	public void setIndexer_dir(String indexer_dir) {
		this.indexer_dir = indexer_dir;
	}

	public String getLogger_src_dir() {
		return logger_src_dir;
	}

	public void setLogger_src_dir(String logger_src_dir) {
		this.logger_src_dir = logger_src_dir;
	}

	public int getCutting_interval() {
		return cutting_interval;
	}

	public void setCutting_interval(int cutting_interval) {
		this.cutting_interval = cutting_interval;
	}

	public int getMin_concurrent_lever() {
		return min_concurrent_lever;
	}

	public void setMin_concurrent_lever(int min_concurrent_lever) {
		this.min_concurrent_lever = min_concurrent_lever;
	}

	public int getMax_concurrent_lever() {
		return max_concurrent_lever;
	}

	public void setMax_concurrent_lever(int max_concurrent_lever) {
		this.max_concurrent_lever = max_concurrent_lever;
	}
	
	
	
}
