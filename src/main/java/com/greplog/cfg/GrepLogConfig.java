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
	/** 日志服务器IP地址 */
	private String host_ip;
	/** 日志服务器SSH服务端口号 */
	private int port;
	/** SSH登录日志服务器的用户名 */
	private String username;
	/** SSH登录日志服务器的密码 */
	private String password;
	/** path to store indexer */
	private String indexer_dir;
	/** path where logger loaded */
	private String logger_src_dir;
	/** interval time for cutting logger */
	private int cutting_interval;
	/**  min count of threads */
	private int min_concurrent_lever;
	/** max count of threads */
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
	
}
