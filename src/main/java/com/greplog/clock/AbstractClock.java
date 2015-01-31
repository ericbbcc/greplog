package com.greplog.clock;

public abstract class AbstractClock {
	private String currentTime;
	
	public AbstractClock(String currentTime) {
		super();
		this.currentTime = currentTime;
	}
	
	protected int getTimeInt(){
		String[] hhmmss = this.currentTime.split(":");
		Integer h2s = Integer.parseInt(hhmmss[0]) * 60 * 60;
		Integer m2s = Integer.parseInt(hhmmss[1]) * 60;
		Integer s2s = Integer.parseInt(hhmmss[2]);
		return (h2s + m2s + s2s);
	}
	
	protected String getTimeString(int time){
		Integer h = time / (60 * 60);
		Integer m = (time - h * 60 * 60) / 60;
		Integer s = time - h * 60 * 60 - m * 60;
		StringBuilder sb = new StringBuilder();
		sb.append(h > 9 ? String.valueOf(h) : "0" + String.valueOf(h));
		sb.append(":");
		sb.append(m > 9 ? String.valueOf(m) : "0" + String.valueOf(m));
		sb.append(":");
		sb.append(s > 9 ? String.valueOf(s) : "0" + String.valueOf(s));
		return sb.toString();
	}
}
