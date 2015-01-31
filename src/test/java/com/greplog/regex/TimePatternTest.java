package com.greplog.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimePatternTest {
	public static void main(String ...s){
		String testStr = "[17:14:22] [pool-3-thread-4] |INFO|  c.t.framework.config.tops.zk.TopsZookeeperBalancer";
		Pattern p = Pattern.compile("\\[17:14:[0-9]{2}\\]");
		Matcher m = p.matcher(testStr);
		if(m.find())
			System.out.println(m.group());
		
		
	}

}
