package com.greplog.cfg;

import com.jcraft.jsch.UserInfo;

public class Robot implements UserInfo{
	public String hostIp ;
	public int port;
	public String username ;
	public String password ;
	
    public String getPassword(){ return password; }
    public boolean promptYesNo(String str){return true;}
    public String getPassphrase(){ return null; }
    public boolean promptPassphrase(String message){ return true; }
    public boolean promptPassword(String message){return true;}
    public void showMessage(String message){
    	System.out.println(message);
    }
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
    
    
}
