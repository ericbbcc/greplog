package com.greplog.clock;

public interface Clock {
	int max_seconds = 24 * 60 * 60 - 1;
	String getCurrentTime();
	String pastSecond();
	String pastMinute();
	String pastHour();
	String pastSecond(int second);
	String pastMinute(int minute);
	String pastHour(int hour);
}
