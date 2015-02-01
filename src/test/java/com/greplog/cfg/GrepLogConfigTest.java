package com.greplog.cfg;

public class GrepLogConfigTest {

	public static void main(String[] args) {
		try{
			GrepLogConfig cfg = new GrepLogConfig();
			cfg.parse("/Users/andy/greplog/greplog.cfg");
			System.out.println("done!");
		}catch(Exception e){
		}
			
	}

}
