package com.greplog.clock;

public class TimeAccuracyTest {

	public static void main(String[] args) {
		
		System.out.println(new DefaultClock("12:34:56").getTimeAccuracy(600));

	}

}
