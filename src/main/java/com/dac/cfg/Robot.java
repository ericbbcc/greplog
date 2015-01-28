package com.dac.cfg;

import com.jcraft.jsch.UserInfo;

public class Robot implements UserInfo{
	public static String H = "192.168.130.74";
	public static String U = "shui.ren";
	public static String P = "OwdyeepIj3";
	
    public String getPassword(){ return P; }
    public boolean promptYesNo(String str){return true;}
    public String getPassphrase(){ return null; }
    public boolean promptPassphrase(String message){ return true; }
    public boolean promptPassword(String message){return true;}
    public void showMessage(String message){
    	System.out.println(message);
    }
}
