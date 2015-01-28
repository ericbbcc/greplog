package com.dac.clock;

public class ClockTest {
	
	public static void main(String ...s){
		DefaultClock dc = new DefaultClock("00:00:00");
		dc.pastMinute(5);
		System.out.println(dc.getCurrentTime());
	}

}
