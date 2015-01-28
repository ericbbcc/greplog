package com.dac.clock;

public class DefaultClock extends AbstractClock implements Clock{
	private String currentTime = "00:00:00";
	
	public DefaultClock(String currentTime, String currentTime2) {
		super(currentTime);
		currentTime = currentTime2;
	}

	@Override
	public String getCurrentTime() {
		return currentTime;
	}

	@Override
	public String pastSecond() {
		return pastSecond(1);
	}

	@Override
	public String pastMinute() {
		return pastMinute(1);
	}

	@Override
	public String pastHour() {
		return pastHour(1);
	}

	@Override
	public String pastSecond(int second) {
		int ss = getTimeInt();
		int newss = ss + second;
		return getTimeString(newss);
	}

	@Override
	public String pastMinute(int minute) {
		return getTimeString(minute * 60);
	}

	@Override
	public String pastHour(int hour) {
		return getTimeString(hour * 60 * 60);
	}
	
	
}
