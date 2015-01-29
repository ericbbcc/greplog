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
	/** path to store indexer */
	private String indexer_dir;
	/** path where logger loaded */
	private String logger_src_dir;
	
	/** interval time for cutting logger */
	private int cutting_interval;
	
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
