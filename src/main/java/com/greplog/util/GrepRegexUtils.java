package com.greplog.util;

public class GrepRegexUtils {
	
	public static String convertTime2SSHRegex(String curracyTime){
		if(curracyTime.endsWith(":00:00"))
			return "^\\[" + curracyTime.substring(0, 2);
		if(curracyTime.endsWith("0:00"))
			return "^\\[" + curracyTime.substring(0,  4);
		if(curracyTime.endsWith(":00"))
			return "^\\[" + curracyTime.substring(0,  5);
		if(curracyTime.endsWith("0"))
			return "^\\[" + curracyTime.substring(0,  7);
		return "^\\[" + curracyTime;
	}
	
	

}
